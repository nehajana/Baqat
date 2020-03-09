package shubham.com.baqat.CreateNormalAddScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import shubham.com.baqat.R;


public class CustomAdapter2 extends BaseAdapter  {
    Context context;
    ArrayList<SubOccasionDataModel> countryNames2;
    LayoutInflater inflter;
    public  static ImageView img_check;
    Spinner spinner;

    public CustomAdapter2(Context applicationContext, ArrayList<SubOccasionDataModel> countryNames, Spinner spinner) {
        this.context = applicationContext;
        this.countryNames2 = countryNames;
        this.spinner =  spinner;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return countryNames2.size();
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
        view = inflter.inflate(R.layout.custom_spinner_items2, null);
      //  ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        img_check = (ImageView) view.findViewById(R.id.img_check);
        //icon.setImageResource(flags[i]);
        names.setText(countryNames2.get(i).getCategoryName());

        return view;
    }


}