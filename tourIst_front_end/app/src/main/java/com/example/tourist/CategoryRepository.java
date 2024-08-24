package com.example.tourist;

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

public class CategoryRepository {

    public void getAllCategories(ExecutorService srv, Handler uiHandler) {

        srv.execute(()-> {
            try {

                List<Category> data = new ArrayList<>();
                URL url = new URL("http://10.0.2.2:8080/categories/list");

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

                    JSONObject current = arr.getJSONObject(i);

                    Category cat = new Category(current.getString("categoryName"));

                    data.add(cat);
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


}
