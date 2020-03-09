package shubham.com.baqat.GettingMarriedScreen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import android.support.v7.widget.LinearLayoutManager;

import com.squareup.picasso.Picasso;

import shubham.com.baqat.GettingMarriedScreen.ApiModel.GettingMarriedDataList;
import shubham.com.baqat.R;
import shubham.com.baqat.RestaurantsCateringScreen.RestaurentCateringActivity;

/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class GattingMarriedRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<GettingMarriedDataList> modelList;

    private OnItemClickListener mItemClickListener;


    public GattingMarriedRecyclerViewAdapter(Context context, ArrayList<GettingMarriedDataList> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<GettingMarriedDataList> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gatting_married_item_recycler_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final GettingMarriedDataList model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.txt_event.setText(model.getCategoryName());
            String ImageUrl=model.getWebImg();

            Picasso.with(mContext).load(ImageUrl)
                    .placeholder(R.drawable.restaurants_catering)
                   .into(genericViewHolder.img_restaurants_catering);

           // genericViewHolder.txt_catering.setText(model.getCategoryName());
          // genericViewHolder.img_restaurants_catering.setImageResource(model.getWebImg());
        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private GettingMarriedDataList getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, GettingMarriedDataList model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgUser;
        private TextView txt_event;
        private ImageView img_restaurants_catering;
        private RelativeLayout RR_wedding_event;

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
            this.img_restaurants_catering = (ImageView) itemView.findViewById(R.id.img_restaurants_catering);
            this.RR_wedding_event = (RelativeLayout) itemView.findViewById(R.id.RR_wedding_event);

            //this.imgUser = (ImageView) itemView.findViewById(R.id.img_user);
            this.txt_event = (TextView) itemView.findViewById(R.id.txt_event);


            itemView.setOnClickListener(
                    new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });

        }
    }

}

