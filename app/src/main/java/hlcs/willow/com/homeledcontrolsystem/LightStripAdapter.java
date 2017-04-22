package hlcs.willow.com.homeledcontrolsystem;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
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
    int dialogId=0;

    public LightStripAdapter(ArrayList<LightStrip> lightStrip){
        this.lightStrip = lightStrip;
    }

    @Override
    public LightStripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ViewGroup innerParent = parent;
        activity = (Activity) parent.getContext();
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false);

        final LightStripViewHolder lightStripViewHolder = new LightStripViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    //
                   Log.i("ssda",Integer.toString(lightStripViewHolder.getPosition()));
                            ColorPickerDialog.newBuilder()
                                    .setDialogType(ColorPickerDialog.TYPE_CUSTOM)
                                    .setAllowPresets(false)
                                    .setDialogId(lightStripViewHolder.getPosition())
                                    .setColor(Color.BLACK)
                                    .setShowAlphaSlider(false)
                                    .show(activity);
                    //Log.i("HEREERERe","Clicked!");
                }
            });
        ///Long click action\\\
        view.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {

                CharSequence options[] = new CharSequence[] {"Delete", "Edit"};
                final MainActivity mainActivity = new MainActivity();
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Light Strip Options");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                mainActivity.deleteLightStrip(lightStripViewHolder.getPosition(), view.getContext());
                                break;
                            case 1:
                                //call the edit lightstrip method here
                                mainActivity.editLightStrip(lightStripViewHolder.getPosition(), view);
                                break;
                        }
;
                    }
                });
                builder.show();
                return true;
            }
        });
        return lightStripViewHolder;
    }
    @Override
    public void onBindViewHolder(LightStripViewHolder holder, int position) {

        holder.itemView.setLongClickable(true);
        LightStrip ls = lightStrip.get(position);
        holder.location_text.setText(ls.getLocation());
        holder.mode_text.setText(ls.getMode());
        holder.ID.setText(ls.getIP());
        holder.colorImage.setBackgroundColor(Color.rgb(ls.getColor().getRed(), ls.getColor().getGreen(), ls.getColor().getBlue()));

        dialogId = position;

        //new GetFromServer()

    }

    @Override
    public int getItemCount() {
        return lightStrip.size();
    }

    public static class LightStripViewHolder extends RecyclerView.ViewHolder {

        TextView location_text, mode_text, color_text,  ID;
        ImageView colorImage;

        public LightStripViewHolder(View view){
            super(view);
            location_text = (TextView)view.findViewById(R.id.location_text);
            mode_text= (TextView)view.findViewById(R.id.mode_text);;
            ID = (TextView)view.findViewById(R.id.strip_id_text);
            colorImage = (ImageView)view.findViewById(R.id.color_image);

        }

    }

}
