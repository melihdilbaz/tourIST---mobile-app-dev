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

public class CommentRepository {

    public void getAllCommentsByDestinationId(ExecutorService srv, Handler uiHandler, String id) {
        srv.execute(()-> {
            try {
                List<Comment> data = new ArrayList<>();

                URL url = new URL("http://10.0.2.2:8080/home/comments");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");
                conn.setRequestProperty("id",id);

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

                    Comment comm = new Comment(current.getString("id"),
                            current.getString("name"),
                            current.getString("text"));

                    data.add(comm);
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

    public void postComment(ExecutorService srv, Handler uiHandler, String id, Comment comment) {
        srv.execute(()->{
            URL url = null;
            try {
                url = new URL("http://10.0.2.2:8080/home/post");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                Log.d("Navigation", "output comment is properties : " + comment.toString());

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/JSON");
                conn.setRequestProperty("id", id);

                JSONObject outputData  = new JSONObject();

                Log.d("Navigation", "output comment is properties : " + comment.toString());

                outputData.put("name",comment.getName());
                outputData.put("text",comment.getText());

                BufferedOutputStream writer =
                        new BufferedOutputStream(conn.getOutputStream());
                writer.write(outputData.toString().getBytes(StandardCharsets.UTF_8));
                writer.flush();

                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();

                String line ="";

                while((line=reader.readLine())!=null){
                    buffer.append(line);
                }

                String retVal = buffer.toString();
                conn.disconnect();

                Message msg = new Message();
                msg.obj = retVal;
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
