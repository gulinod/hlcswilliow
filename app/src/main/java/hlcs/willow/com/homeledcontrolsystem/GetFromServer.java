package hlcs.willow.com.homeledcontrolsystem;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
//import okhttp3.RequestBody;
import okhttp3.Response;

public class GetFromServer extends AsyncTask<Void, Void, Void> {
    public  LEDColor color = new LEDColor(34,42,104, "solid");
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private ImageView image;
    private Context cont;
    private RecyclerView.Adapter ad;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public GetFromServer(ImageView image, Context cont, RecyclerView.Adapter ad) {
        this.cont = cont;
        this.image = image;
        this.ad = ad;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    String url = "http://150.156.208.124:5003/api/v1/basic-color";

    //queue.add(postRequest);
    @Override
    protected Void doInBackground(Void... params) {
        // Make a new OkHttpClient (Maybe use a final - activity wide one?)
        OkHttpClient client = new OkHttpClient();
        // Let's make a request!
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            //Log.i("Look what we have here:", response.body().string());
            try{
               color = gson.fromJson(response.body().string(), LEDColor.class);
                Log.i("Making color", color.toString());
            }catch (Exception e){
                Log.e("Exception::::", e.toString());
            }

            //return response.body().string();
        }catch (IOException e){
            Log.e("ERROR", e.toString());
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void result) {
        ad.notifyDataSetChanged();
        //Log.i("This is the i", this.image.toString());;
        Log.i("updating adapter", "Ccosd");
        try{
            //update the background to the latest color
            this.image.setBackgroundColor(Color.rgb(color.getRed(), color.getGreen(), color.getBlue()));
            //Toast.makeText(cont, "Selected LEDColor R:" +  color.getRed() + " G: " + color.getGreen() + " B: " + color.getBlue(), Toast.LENGTH_LONG).show();


        }catch (Exception e){
            Log.e("Exception:::>", e.toString());

        }

    }
}