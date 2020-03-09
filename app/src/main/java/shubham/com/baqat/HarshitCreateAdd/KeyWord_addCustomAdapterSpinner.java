package shubham.com.baqat.HarshitCreateAdd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import shubham.com.baqat.HarshitCreateAdd.ApimodelKeyword.KeywordDataModel;
import shubham.com.baqat.R;

public class KeyWord_addCustomAdapterSpinner extends BaseAdapter {
    Context context;
    ArrayList<KeywordDataModel> OfferingforList;
    LayoutInflater inflter;

    public KeyWord_addCustomAdapterSpinner(Context applicationContext, ArrayList<KeywordDataModel> OfferingforList) {
        this.context = applicationContext;
        this.OfferingforList = OfferingforList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return OfferingforList.size();
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
        view = inflter.inflate( R.layout.custom_spinner_items, null);
      //  ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        //icon.setImageResource(flags[i]);
       names.setText(OfferingforList.get(i).getKeyword());

        return view;

    }
}