package hlcs.willow.com.homeledcontrolsystem;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Created by dan on 4/12/17.
 */

public class  StoreData extends AppCompatActivity {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Type listType = new TypeToken<ArrayList<LightStrip>>() {}.getType();

}
