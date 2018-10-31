package fr.edouardkerhir.geolocmap;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CitrouilleAdapter extends ArrayAdapter<CandyModel> {


    public CitrouilleAdapter(@NonNull Context context, ArrayList<CandyModel> candyList) {
        super(context, 0,candyList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CandyModel candy = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.candy_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.iv_candy);
        Drawable candyPics = ContextCompat.getDrawable(getContext(), candy.getImage());
        imageView.setImageDrawable(candyPics);

        TextView candyName = convertView.findViewById(R.id.tv_nom_candy);
        candyName.setText(candy.getNom());

        TextView candyNumber = convertView.findViewById(R.id.tv_nb_candy);
        candyNumber.setText(candy.getNbCandy());

        return convertView;
    }
}
