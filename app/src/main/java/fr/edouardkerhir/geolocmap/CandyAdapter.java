package fr.edouardkerhir.geolocmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class CandyAdapter extends ArrayAdapter<bonbonItemInfoWindow> {


    public CandyAdapter(Context context, ArrayList<bonbonItemInfoWindow> candyPlace) {
        super(context, 0, candyPlace);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CandySingleton.getInstance();
        ArrayList<CandyModel> arraycandyModel = CandySingleton.getInstance().getCandyArrayList();


        // Get the data item for this position
        bonbonItemInfoWindow bonbon = getItem(position);

        int bonbonIndex = bonbon.getIndexSingleton();
        int bonbonCount = bonbon.getNbEachCandy();


        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_custom_info_winidow, parent, false);
        }


        // Lookup view for data population
        ImageView candyImg = convertView.findViewById(R.id.item_img);
        TextView candyNb = (TextView) convertView.findViewById(R.id.item_number);


        // Populate the data into the template view using the data object
        candyImg.setImageResource(arraycandyModel.get(bonbonIndex).getImage());
        candyNb.setText(": x"+bonbonCount);
        // Return the completed view to render on screen
        return convertView;
    }

}
