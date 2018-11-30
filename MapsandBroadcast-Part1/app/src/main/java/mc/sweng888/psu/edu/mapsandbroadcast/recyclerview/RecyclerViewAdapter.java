package mc.sweng888.psu.edu.mapsandbroadcast.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mc.sweng888.psu.edu.mapsandbroadcast.R;
import mc.sweng888.psu.edu.mapsandbroadcast.model.MapLocation;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;

    // Data input for the RecyclerView
    // For now, you will not handle the images withing the ImageView.
    private ArrayList<MapLocation> locationDataList = null;

    private static final String TAG = "RecyclerViewAdapter";

    public RecyclerViewAdapter(Context context, ArrayList<MapLocation> locationDataList) {
        this.context = context;
        this.locationDataList = locationDataList;
    }

    // Creating inner class as ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder{

        // Elements defined in the UI
        CardView parentLayout;

        // For now, we don't need the imageView.
        // The images will be customized later on.
        ImageView imageView;

        TextView textViewLatitude;
        TextView textViewLongitude;
        TextView textViewLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.cardView);
            textViewLatitude = itemView.findViewById(R.id.txt_view_latitute);
            textViewLongitude = itemView.findViewById(R.id.txt_view_longitute);
            textViewLocation =  itemView.findViewById(R.id.text_view_location);

            // TODO ImageView should get information from Firebase.
        }
    }

    // Methods inherited from ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        // Implement to logic for inflating the view based on the customized layout.
        // This will be the logic for pretty much all the RecycerView adapters
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_layout, parent, false);

        // Set the view to be represented into the ViewHolder object.
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    // The method implementaiton might change based on the root layout.
    // Everytime a new item is inflated on the view it will be called.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        Log.d(TAG, "onBindViewHolder has been called");
        MapLocation locationData = locationDataList.get(position);

        viewHolder.textViewLatitude.setText("Lat: "+locationData.getLatitude().toString());
        viewHolder.textViewLongitude.setText("Long: "+locationData.getLongitude().toString());
        viewHolder.textViewLocation.setText("Location: "+String.valueOf(locationData.getLocation()));

        Toast.makeText(context, locationData.getLocation(), Toast.LENGTH_SHORT );
    }

    @Override
    public int getItemCount() {
        return locationDataList.size();
    }
}