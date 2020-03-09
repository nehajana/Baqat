package shubham.com.baqat.CreateNormalAddScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import shubham.com.baqat.HarshitCreateAdd.ApiModel.Info;
import shubham.com.baqat.R;


public class CreateNormalAdsCustomAdapterFour extends BaseAdapter  {
    Context context;
    ArrayList<Info> OccationList;
    LayoutInflater inflter;

    public CreateNormalAdsCustomAdapterFour(Context applicationContext, ArrayList<Info> OccationList) {
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
        view = inflter.inflate(R.layout.createnormal_custom_spinner_items_four, null);
      //  ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        //icon.setImageResource(flags[i]);
        names.setText(OccationList.get(i).getValue());
        return view;
    }
}