package hlcs.willow.com.homeledcontrolsystem;
import android.app.Application;
import android.os.AsyncTask;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import okhttp3.Call;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Call;
import okhttp3.Callback;

/**
 * Created by dan on 3/5/17.
 */

public class PostToServer extends AsyncTask<String, Void, String> {
    private LEDColor color;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    LightStripAdapter.LightStripViewHolder ls;
    public String url;

    public PostToServer(LEDColor color, String url) {
        this.color = color;
        this.url = "http://" + url + "/api/v1/basic-color";
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    //String url = "http://150.156.208.124:5003/api/v1/basic-color";

    //queue.add(postRequest);
    @Override
    protected String doInBackground(String... params) {

        //Log.i("String should be", url);

        // Make a new OkHttpClient (Maybe use a final - activity wide one?)
        OkHttpClient client = new OkHttpClient();
        // Set the request body.......
        RequestBody body = RequestBody.create(JSON, gson.toJson(color));
        // Let's make a request!
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        // Please please pretty please... Async calls.
        try {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }
                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    } else {
                        throw new IOException("You were successful! " + response);
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
