package shubham.com.baqat.FilterScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import shubham.com.baqat.CreateNormalAddScreen.OccasionApiModel.SelectOccasionList;
import shubham.com.baqat.CreateNormalAddScreen.SubOccasionDataModel;
import shubham.com.baqat.R;


public class CustomAdapterSub extends BaseAdapter  {
    Context context;
    ArrayList<SubOccasionDataModel> OccationList;
    LayoutInflater inflter;

    public CustomAdapterSub(Context applicationContext, ArrayList<SubOccasionDataModel> OccationList) {
        this.context = applicationContext;
        this.OccationList = OccationList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return OccationList.size();
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
        names.setText(OccationList.get(i).getCategoryName());
        return view;
    }
}