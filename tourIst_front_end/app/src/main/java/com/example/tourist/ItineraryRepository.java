package com.example.tourist;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class ItineraryRepository {
    public void getAllItineraries(ExecutorService srv, Handler uiHandler) {
        srv.execute(()-> {
            try {
                List<Itinerary> data = new ArrayList<>();

                URL url = new URL("http://10.0.2.2:8080/home/itineraries");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader
                        = new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()));
                String line = "";
                StringBuilder buffer = new StringBuilder(); // to concatenate

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                JSONArray arr = new JSONArray(buffer.toString());
                for (int i = 0; i <arr.length() ; i++) {

                    List<Destination> dest_data = new ArrayList<>();

                    JSONObject current = arr.getJSONObject(i);

                    JSONArray dest_arr = current.getJSONArray("list");
                    for (int j = 0; j <dest_arr.length() ; j++) {

                        Destination dest = new Destination();
                        dest_data.add(dest);

                    }

                    Itinerary it = new Itinerary(current.getString("id"),
                            current.getString("name"),
                            dest_data);

                    data.add(it);
                }

                // we got the info so can disconnect it

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void getItineraryById(ExecutorService srv, Handler uiHandler, String id) {
        srv.execute(()-> {
            try {
                List<Destination> data = new ArrayList<>();

                URL url = new URL("http://10.0.2.2:8080/home/itineraries/" + id);

                HttpURLConnection conn =(HttpURLConnection)url.openConnection();

                BufferedReader reader
                        = new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()));
                StringBuilder buffer = new StringBuilder();
                String line ="";
                while((line=reader.readLine())!=null){
                    buffer.append(line);
                }
                JSONObject curr_it = new JSONObject(buffer.toString());
                JSONArray arr = curr_it.getJSONArray("list");

                for (int i = 0; i <arr.length() ; i++) {

                    JSONObject current = arr.getJSONObject(i);

                    Destination dest = new Destination(current.getString("id"),
                            current.getString("name"),
                            current.getString("text"),
                            current.getString("link"),
                            current.getString("image"),
                            new Category(current.getJSONObject("category").getString("categoryName")));
                    data.add(dest);
                }
                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void createItinerary(ExecutorService srv, String title) {
        srv.execute(()->{
            URL url = null;
            try {
                url = new URL("http://10.0.2.2:8080/home/itineraryheader/create");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/JSON");
                conn.setRequestProperty("title", title);

                int responseCode = conn.getResponseCode();
                Log.d("HTTP Response", "Server responded with: " + responseCode);

                // Optionally read the response message if needed
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                        String line;
                        StringBuilder response = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        Log.d("HTTP Response", "Response from server: " + response.toString());
                    }
                } else {
                    Log.d("HTTP Response", "Request failed with HTTP code: " + responseCode);
                }
                conn.disconnect();

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void addItinerary(ExecutorService srv, String title, String name) {
        srv.execute(()->{
            URL url = null;
            try {
                url = new URL("http://10.0.2.2:8080/home/itineraryheader/add");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/JSON");
                conn.setRequestProperty("title", title);
                conn.setRequestProperty("name", name);
                Log.d("Navigation", "title: " + title + "name: " + name);

                int responseCode = conn.getResponseCode();
                Log.d("HTTP Response", "Server responded with: " + responseCode);

                // Optionally read the response message if needed
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                        String line;
                        StringBuilder response = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        Log.d("HTTP Response", "Response from server: " + response.toString());
                    }
                } else {
                    Log.d("HTTP Response", "Request failed with HTTP code: " + responseCode);
                }
                conn.disconnect();

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void dropItinerary(ExecutorService srv, String title) {
        srv.execute(()->{
            URL url = null;
            try {
                url = new URL("http://10.0.2.2:8080/home/itineraryheader/remove?title=" + title);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/JSON");
                conn.setRequestProperty("title", title);


                int responseCode = conn.getResponseCode();
                Log.d("HTTP Response", "Server responded with: " + responseCode);

                // Optionally read the response message if needed
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                        String line;
                        StringBuilder response = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        Log.d("HTTP Response", "Response from server: " + response.toString());
                    }
                } else {
                    Log.d("HTTP Response", "Request failed with HTTP code: " + responseCode);
                }
                conn.disconnect();

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
