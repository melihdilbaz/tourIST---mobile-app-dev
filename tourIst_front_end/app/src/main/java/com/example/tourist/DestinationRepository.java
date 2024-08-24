package com.example.tourist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class DestinationRepository {

    public void getAllDestinationsByCategoryName(ExecutorService srv, Handler uiHandler, String catName) {

        srv.execute(()->{
            try {
                List<Destination> data = new ArrayList<>();

                URL url = new URL("http://10.0.2.2:8080/home/destinations/getall?categoryName=" + catName);
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

                JSONArray arr = new JSONArray(buffer.toString());

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
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


        });
    }

    public void downloadImage(ExecutorService srv, Handler uiHandler, String path){

        srv.submit(()->{
            try {
                URL url =
                        new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                Bitmap bmp = BitmapFactory.decodeStream(conn.getInputStream());

                Message msg = new Message();
                msg.obj = bmp;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void getDestination(ExecutorService srv, Handler uiHandler, String id) {
        srv.execute(()-> {
            try {
                URL url = new URL("http://10.0.2.2:8080/home/destinations?id=" + id);
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

                JSONObject current = new JSONObject(buffer.toString());

                Destination dest = new Destination(current.getString("id"),
                        current.getString("name"),
                        current.getString("text"),
                        current.getString("link"),
                        current.getString("image"),
                        new Category(current.getJSONObject("category").getString("categoryName")));

                Message msg = new Message();
                msg.obj = dest;
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
}
