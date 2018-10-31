package fr.edouardkerhir.geolocmap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<CandyModel> {

    ImageAdapter imageAdapter;

    public ListAdapter(Context context, ArrayList<CandyModel> list) {
        super(context, 0, list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ListViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item, parent, false);
            viewHolder = new ListViewHolder();
            viewHolder.candyName = convertView.findViewById(R.id.candyName);
            viewHolder.popUpButton = convertView.findViewById(R.id.popUpButton);
            viewHolder.drawerInfo = convertView.findViewById(R.id.drawer_Info);
            viewHolder.poidCandy = convertView.findViewById(R.id.tv_poid_candy);
            viewHolder.ivCandy = convertView.findViewById(R.id.iv_candy);
            viewHolder.viewp = convertView.findViewById(R.id.vp);
            convertView.setTag(viewHolder);
        }

        final CandyModel candy = getItem(position);
        viewHolder = (ListViewHolder) convertView.getTag();
        viewHolder.candyName.setText(candy.getNom());
        viewHolder.ivCandy.setImageDrawable(getContext().getResources().getDrawable(candy.getImage()));
        viewHolder.poidCandy.setText(String.valueOf((double) candy.getPoids()));

        final ListViewHolder finalViewHolder = viewHolder;
        viewHolder.popUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalViewHolder.drawerInfo.getVisibility() == View.VISIBLE) {
                    finalViewHolder.drawerInfo.setVisibility(View.GONE);
                } else {
                    finalViewHolder.drawerInfo.setVisibility(View.VISIBLE);
                }
            }
        });

        String bonbon = candy.getNom();
        imageAdapter = new ImageAdapter(getContext(),bonbon);
        viewHolder.viewp.setAdapter(imageAdapter);


        return convertView;
    }


    class ListViewHolder {
        public TextView candyName;
        public TextView nbCandy;
        public TextView poidCandy;
        public ConstraintLayout drawerInfo;
        public ImageButton popUpButton;
        public ImageView ivCandy;
        public ViewPager viewp;
    }
}
