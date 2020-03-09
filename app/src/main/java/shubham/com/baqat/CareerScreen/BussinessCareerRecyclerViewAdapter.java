package shubham.com.baqat.CareerScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


import shubham.com.baqat.BussinessPromoterScreen.BussinessPromoterActivity;
import shubham.com.baqat.CareerScreen.ApiModel.CareerDataList;
import shubham.com.baqat.R;
import shubham.com.baqat.UserProfile.UserProfileActivity;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class BussinessCareerRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<CareerDataList> modelList;

    private OnItemClickListener mItemClickListener;


    public BussinessCareerRecyclerViewAdapter(Context context, ArrayList<CareerDataList> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<CareerDataList> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bussiness_career_item_recycler_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final CareerDataList model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

           String Description=(model.getJobDescription().toString());
           genericViewHolder.txt_bussiness_title.setText(model.getJobTitle());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                genericViewHolder.txt_contant.setText(Html.fromHtml(Description, Html.FROM_HTML_MODE_COMPACT));

            } else {

                genericViewHolder.txt_contant.setText(Html.fromHtml(Description));
            }

            genericViewHolder.btn_readmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(mContext, BussinessPromoterActivity.class);
                    intent.putExtra("jobTitile",model.getJobTitle());
                    intent.putExtra("jobDescription",model.getJobDescription());
                    intent.putExtra("jobId",model.getId());
                    mContext.startActivity(intent);

                }
            });

        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;

    }

    private CareerDataList getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, CareerDataList model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgUser;
        private TextView txt_bussiness_title;
        private TextView txt_contant;
        private Button btn_readmore;

        public ViewHolder(final View itemView) {
            super(itemView);

            this.txt_contant = (TextView) itemView.findViewById(R.id.txt_contant);
            this.txt_bussiness_title = (TextView) itemView.findViewById(R.id.txt_bussiness_title);
            this.btn_readmore = (Button) itemView.findViewById(R.id.btn_readmore);

        }
    }

}

