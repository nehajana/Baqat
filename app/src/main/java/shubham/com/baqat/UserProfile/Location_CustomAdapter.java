package shubham.com.baqat.UserProfile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import shubham.com.baqat.R;
import shubham.com.baqat.UserProfile.ApiModel.CountryDataModel;

public class Location_CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<LocationDataModel> countryNames;
    LayoutInflater inflter;

    public Location_CustomAdapter(Context applicationContext, ArrayList<LocationDataModel> countryNames) {
        this.context = applicationContext;
        this.countryNames = countryNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return countryNames.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_items, null);
      //  ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        //icon.setImageResource(flags[i]);
        names.setText(countryNames.get(i).getStateName());

        return view;
    }
}