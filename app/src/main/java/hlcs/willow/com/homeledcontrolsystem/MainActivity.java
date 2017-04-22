package hlcs.willow.com.homeledcontrolsystem;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.jrummyapps.android.colorpicker.ColorPickerDialogListener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class MainActivity extends AppCompatActivity implements ColorPickerDialogListener {
    RecyclerView recyclerView;
    public static RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Type listType = new TypeToken<ArrayList<LightStrip>>() {}.getType();
    String [] location, IP, mode;

    public static ArrayList<LightStrip> lightStrip = new ArrayList<LightStrip>();
    LightStrip selectedLightStrp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set the defualt color to black
        LEDColor ledColor;
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/



        /*    READ FROM LOCAL STORAGE HERE */
        FileInputStream fis;
        String content = "";
        try {
            fis = openFileInput("lightstrip.txt");
            byte[] input = new byte[fis.available()];
            while (fis.read(input) != -1) {
                content += new String(input);
            }
            }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("CONTENT", content);


        lightStrip = gson.fromJson(content, listType);

        recyclerView = (RecyclerView)findViewById(R.id.strip_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new LightStripAdapter(lightStrip);
        recyclerView.setAdapter(adapter);
        String[] items = new String[]{"1", "2", "three"};
        ArrayAdapter<CharSequence> spinnerAdapter =  ArrayAdapter.createFromResource(this,
                R.array.modes, android.R.layout.simple_spinner_item);


        final AlertDialog.Builder dBuilder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.new_connection_dialog, null);
        final EditText connectionIp = (EditText) view.findViewById(R.id.led_connection_ip_value);
        final EditText connectionName = (EditText) view.findViewById(R.id.led_connection_name_value);
        final Button  connectButton = (Button) view.findViewById(R.id.dialog_connect_button);
        final Spinner spinnerList = (Spinner) view.findViewById(R.id.dialog_spinner);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        spinnerList.setAdapter(spinnerAdapter);

        dBuilder.setView(view);
        spinnerList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       final AlertDialog dialog = dBuilder.create();

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                dialog.show();
            }
        });



        connectButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // add light strip with to recycler view
                lightStrip.add(new LightStrip( connectionName.getText().toString(), spinnerList.getSelectedItem().toString(), connectionIp.getText().toString(), new LEDColor(0,0,0,"solid")));
                //clear the text of the ip field
                connectionIp.setText("");
                connectionName.setText("");
                //update dataset
                adapter.notifyDataSetChanged();
                //close the dialog
                dialog.dismiss();
                //save the updated data to disk
                saveData(lightStrip);

            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        try{
           // new GetFromServer(backgroundImg, this, adapter).execute();
        }catch (Exception e){
            Log.e("It broke", e.toString());
        }
        return true;
    }

    @Override public void onColorSelected(int dialogId, int color) {


                //convert hex to RGB
                int newColor = (int)Long.parseLong(Integer.toHexString(color), 16);
                //get rgb values of the string
                int r = (newColor >> 16) & 0xFF;
                int g = (newColor >> 8) & 0xFF;
                int b = (newColor >> 0) & 0xFF;
                String mode = "strobe";
                LEDColor colorObj = new LEDColor(r,g,b, mode);
                Toast.makeText(this, "Selected LEDColor R:" + r + " G: " + g + " B: " + b, Toast.LENGTH_SHORT).show();
                try{
                    //change this to send the entire lightstrip
                    new PostToServer(colorObj, lightStrip.get(dialogId).getIP() ).execute();
                }catch(Exception e){
                    Toast.makeText(this, "ERROR:" + e, Toast.LENGTH_SHORT).show();
                    //break if error and don't set color
                    Log.e("Soemthing broke: ", e.toString());
                }
                //set the attributes of the things
                TextView mode_text = (TextView) findViewById(R.id.mode_text);
                mode_text.setText(mode);
                lightStrip.get(dialogId).setColor(colorObj);
                adapter.notifyDataSetChanged();
                StoreData sd = new StoreData();

                sd.saveData(lightStrip, MainActivity.this);



    }
    @Override public void onDialogDismissed(int dialogId) {

    }
    public void deleteLightStrip(int selectedLS, Context ctx){
        lightStrip.remove(selectedLS);
        adapter.notifyDataSetChanged();
        //save changes
        StoreData sd = new StoreData();
        sd.saveData(lightStrip, ctx );
    }
    public void editLightStrip(int selectedLs, View view){
        //add popoup to lightstrip here
        final AlertDialog.Builder dBuilder = new AlertDialog.Builder(view.getContext());
        View v = getLayoutInflater().inflate(R.layout.new_connection_dialog, null);
        final EditText connectionIp = (EditText) v.findViewById(R.id.led_connection_ip_value);
        final EditText connectionName = (EditText) v.findViewById(R.id.led_connection_name_value);
        final Button  connectButton = (Button) v.findViewById(R.id.dialog_connect_button);

        dBuilder.setView(view);
        final AlertDialog dialog = dBuilder.create();
        connectButton.setText(R.string.save_changes_button);
        dialog.show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar wilxl
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveData(ArrayList<LightStrip> lightStrip){
        String jsonStr = gson.toJson(lightStrip, listType);

        //store lightstrip data here
        try {
            Log.i("What I'm storing", jsonStr);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("lightstrip.txt", MainActivity.MODE_PRIVATE));
            outputStreamWriter.write(jsonStr);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
