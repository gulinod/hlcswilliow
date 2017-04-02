package hlcs.willow.com.homeledcontrolsystem;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jrummyapps.android.colorpicker.*;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by dan on 2/19/17.
 */

public class LightStripAdapter extends RecyclerView.Adapter<LightStripAdapter.LightStripViewHolder>  {
    private static final int DIALOG_ID = 0;
    Activity activity;
    ArrayList<LightStrip> lightStrip = new ArrayList<LightStrip>();

    public LightStripAdapter(ArrayList<LightStrip> lightStrip){
        this.lightStrip = lightStrip;
    }

    @Override
    public LightStripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ViewGroup innerParent = parent;
        activity = (Activity) parent.getContext();
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent,false);
        LightStripViewHolder lightStripViewHolder= new LightStripViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                            ColorPickerDialog.newBuilder()
                                    .setDialogType(ColorPickerDialog.TYPE_CUSTOM)
                                    .setAllowPresets(false)
                                    .setDialogId(0)
                                    .setColor(Color.BLACK)
                                    .setShowAlphaSlider(false)
                                    .show(activity);
                    Log.i("HEREERERe","Clicked!");
                }
            });
        return lightStripViewHolder;
    }
    @Override
    public void onBindViewHolder(LightStripViewHolder holder, int position) {
        LightStrip ls = lightStrip.get(position);
        holder.location_text.setText(ls.getLocation());
        holder.color_text.setText(ls.getColor());
        holder.mode_text.setText(ls.getMode());
        holder.ID.setText(ls.getID());

    }

    @Override
    public int getItemCount() {
        return lightStrip.size();
    }

    public static class LightStripViewHolder extends RecyclerView.ViewHolder {

        TextView location_text, mode_text, color_text,  ID;

        public LightStripViewHolder(View view){
            super(view);
            location_text = (TextView)view.findViewById(R.id.location_text);
            mode_text= (TextView)view.findViewById(R.id.mode_text);
            color_text = (TextView)view.findViewById(R.id.color_text);
            ID = (TextView)view.findViewById(R.id.strip_id_text);

        }

    }
}
