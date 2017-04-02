package hlcs.willow.com.homeledcontrolsystem;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.jrummyapps.android.colorpicker.ColorPickerDialogListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ColorPickerDialogListener {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    String [] location, color, ID, mode;

    ArrayList<LightStrip> lightStrip = new ArrayList<LightStrip>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        location = getResources().getStringArray(R.array.locations);
        color = getResources().getStringArray(R.array.colors);
        ID = getResources().getStringArray(R.array.IDs);
        mode = getResources().getStringArray(R.array.modes);

        int count = 0;

        for(String Location : location){
            LightStrip ls = new LightStrip(location[count], mode[count], color[count],ID[count]);
            count++;
            lightStrip.add(ls);
        }

        recyclerView = (RecyclerView)findViewById(R.id.strip_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new LightStripAdapter(lightStrip);

        recyclerView.setAdapter(adapter);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        ImageView backgroundImg = (ImageView) findViewById(R.id.color_image);
        try{
            new GetFromServer(backgroundImg, this, adapter).execute();
        }catch (Exception e){
            Log.e("It broke", e.toString());
        }
        return true;
    }
    @Override public void onColorSelected(int dialogId, int color) {
        Log.i("Its not broke", ":yet");
        switch (dialogId) {
            case 0:
                // We got result from the dialog that is shown when clicking on the icon in the action bar.

                //convert hex to RGB
                int newColor = (int)Long.parseLong(Integer.toHexString(color), 16);
                //get rgb values of the string
                int r = (newColor >> 16) & 0xFF;
                int g = (newColor >> 8) & 0xFF;
                int b = (newColor >> 0) & 0xFF;
                LEDColor colorObj = new LEDColor(r,g,b, "strobe");
                Toast.makeText(this, "Selected LEDColor R:" + r + " G: " + g + " B: " + b, Toast.LENGTH_SHORT).show();
                try{
                    new PostToServer(colorObj).execute();
                }catch(Exception e){
                    Toast.makeText(this, "ERROR:" + e, Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
    @Override public void onDialogDismissed(int dialogId) {

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
}
