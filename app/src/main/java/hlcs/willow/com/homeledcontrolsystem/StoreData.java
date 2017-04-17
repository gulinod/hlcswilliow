package hlcs.willow.com.homeledcontrolsystem;

import android.content.Context;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


/**
 * Created by dan on 4/17/17.
 */

public class StoreData {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Type listType = new TypeToken<ArrayList<LightStrip>>() {}.getType();

    public void saveData(ArrayList<LightStrip> lightStripArrayList, Context ctx){

        String jsonStr = gson.toJson(lightStripArrayList, listType);

        //store lightstrip data here
        try {
            FileOutputStream fos = ctx.openFileOutput("lightstrip.txt", ctx.MODE_PRIVATE);
            Log.i("What I'm storing", jsonStr);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write(jsonStr);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

    }
}
