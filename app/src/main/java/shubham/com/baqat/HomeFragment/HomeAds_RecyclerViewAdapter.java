package shubham.com.baqat.HomeFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import shubham.com.baqat.HomeFragment.ApiModel.HomeAdsDataModel;
import shubham.com.baqat.HomeFragment.ApiModel.HomeDataModel;
import shubham.com.baqat.R;

/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class HomeAds_RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<HomeAdsDataModel> modelList;

    private OnItemClickListener mItemClickListener;


    public HomeAds_RecyclerViewAdapter(Context context, ArrayList<HomeAdsDataModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<HomeAdsDataModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_list_home_ads, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final HomeAdsDataModel model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.txt_description.setText(model.getListingTitle());

            Picasso.with(mContext).load(model.getMediaPath())
                    .placeholder(R.drawable.baby_icon)
                    .into(genericViewHolder.imgUser_ads);

        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private HomeAdsDataModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, HomeAdsDataModel model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgUser_ads;
        private TextView txt_description;
      //  private TextView txt_description;


        // @BindView(R.id.img_user)
        // ImageView imgUser;
        // @BindView(R.id.item_txt_title)
        // TextView itemTxtTitle;
        // @BindView(R.id.item_txt_message)
        // TextView itemTxtMessage;
        // @BindView(R.id.radio_list)
        // RadioButton itemTxtMessage;
        // @BindView(R.id.check_list)
        // CheckBox itemCheckList;
        public ViewHolder(final View itemView) {
            super(itemView);

            // ButterKnife.bind(this, itemView);

            this.imgUser_ads = (ImageView) itemView.findViewById(R.id.imgUser_ads);

            //this.imgUser = (ImageView) itemView.findViewById(R.id.imgUser);
            this.txt_description = (TextView) itemView.findViewById(R.id.txt_description);
          //  this.txt_description = (TextView) itemView.findViewById(R.id.txt_description);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(modelList.get(getAdapterPosition()).getWebsite()));
                    mContext.startActivity(browserIntent);

                   // mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });

        }
    }

}

