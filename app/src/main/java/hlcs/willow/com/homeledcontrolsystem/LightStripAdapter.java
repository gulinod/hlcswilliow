package hlcs.willow.com.homeledcontrolsystem;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dan on 2/19/17.
 */

public class LightStripAdapter extends RecyclerView.Adapter<LightStripAdapter.LightStripViewHolder> {

    ArrayList<LightStrip> lightStrip = new ArrayList<LightStrip>();

    public LightStripAdapter(ArrayList<LightStrip> lightStrip){
        this.lightStrip = lightStrip;
    }

    @Override
    public LightStripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent,false);
        LightStripViewHolder lightStripViewHolder= new LightStripViewHolder(view);

        return lightStripViewHolder;
    }

    @Override
    public void onBindViewHolder(LightStripViewHolder holder, int position) {

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
