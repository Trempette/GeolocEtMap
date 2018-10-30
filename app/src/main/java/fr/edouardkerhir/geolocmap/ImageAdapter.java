package fr.edouardkerhir.geolocmap;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class ImageAdapter extends PagerAdapter {
    private int[] list;
    private Context mContext;

    ImageAdapter(Context context,String bonbon) {
        mContext = context;
        if(bonbon.equals("Tagada")) {
            list = new int[]
                    {R.drawable.tagada,
                    R.drawable.tagada1,
                    R.drawable.tagada2};

        }else if(bonbon.equals("Dagibus")) {
            list = new int[]
                    {R.drawable.dragibus,
                    R.drawable.dragibus1,
                            R.drawable.dragibus2};
        }else if(bonbon.equals("Schtrumpf")) {
            list = new int[]
                    {R.drawable.schtroumpfs,
                    R.drawable.schtroumpfs1,
                    R.drawable.schtroumpfs2};
        }else if(bonbon.equals("Crocodile")) {
            list = new int[]
                    {R.drawable.crocodile,
                            R.drawable.crocodile1,
                    R.drawable.crocodile2};
        }else if(bonbon.equals("Chamalow")) {
            list = new int[]
                    {R.drawable.chamlow,
                            R.drawable.chamlow1,
                            R.drawable.chamlow2};
        }else if(bonbon.equals("Carambar")) {
            list = new int[]
                    {R.drawable.carambar,
                     R.drawable.carambar1,
                     R.drawable.carambar2};
        }else if(bonbon.equals("Reglisse")) {
            list = new int[]
                    {R.drawable.baton,
                            R.drawable.baton1,
                    R.drawable.baton2};
        }else if(bonbon.equals("Koala")) {
            list = new int[]
                    {R.drawable.lutti,
                            R.drawable.lutti1,
                            R.drawable.lutti2};
        }else if(bonbon.equals("Scoubidou")) {
            list = new int[]
                    {R.drawable.lasso,
                            R.drawable.lasso1,
                            R.drawable.lasso2};
        }else {
            list = new int[]
                    {R.drawable.cola,
                            R.drawable.cola1,
                            R.drawable.cola2};
        }
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        ImageView candy = new ImageView(mContext);
        candy.setScaleType(ImageView.ScaleType.CENTER_CROP);
        candy.setImageDrawable(mContext.getResources().getDrawable(list[position]));
        container.addView(candy, 0);
        return candy;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
