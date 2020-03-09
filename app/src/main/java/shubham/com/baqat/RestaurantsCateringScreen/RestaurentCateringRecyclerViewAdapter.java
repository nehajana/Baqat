package shubham.com.baqat.RestaurantsCateringScreen;

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

import de.hdodenhof.circleimageview.CircleImageView;
import shubham.com.baqat.CakeTalkScreen.CakeTalkActivity;
import shubham.com.baqat.CreateBologAdsScreen.CreateBlogActivity;
import shubham.com.baqat.R;
import shubham.com.baqat.RestaurantsCateringScreen.ApiModel.CateringDataList;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class RestaurentCateringRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<CateringDataList> modelList;

    private OnItemClickListener mItemClickListener;


    public RestaurentCateringRecyclerViewAdapter(Context context, ArrayList<CateringDataList> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<CateringDataList> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reataurent_catering_item_recycler_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {

            final CateringDataList model = getItem(position);

            ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.user_name.setText(model.getFirstName());

            genericViewHolder.txt_cake_talk.setText(model.getListingTitle());

            genericViewHolder.txt_city.setText(model.getAddress());
            genericViewHolder.txt_price.setText(model.getListingPrice());


            String UserImag=model.getProfilePicture().toString();
            String MediaPath=model.getMediaThumbPath().toString();


            if(!UserImag.equalsIgnoreCase(""))
            {
                Picasso.with(mContext).load(UserImag)
                        .placeholder(R.drawable.defaultprofile)
                        .into(genericViewHolder.img_circle);
            }

            if(!MediaPath.equalsIgnoreCase(""))
            {
                Picasso.with(mContext).load(MediaPath)
                        .placeholder(R.drawable.imagecamera)
                        .into(genericViewHolder.img_food);
            }

            String isOnline=model.getOnline().toString();

            if(isOnline.equalsIgnoreCase("Yes"))
            {
                genericViewHolder.img_online.setImageResource(R.drawable.online);

            }else
            {
                genericViewHolder.img_online.setImageResource(R.drawable.circle_gray_bg);
            }

        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private CateringDataList getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, CateringDataList model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView img_circle;
        private TextView user_name;
        private TextView txt_cake_talk;
        private TextView txt_city;
        private TextView txt_price;
        private RelativeLayout RR_cake;
        private ImageView img_online;
        private ImageView img_food;


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

            this.img_circle = (CircleImageView) itemView.findViewById(R.id.img_circle);
            this.user_name = (TextView) itemView.findViewById(R.id.user_name);
             this.txt_cake_talk = (TextView) itemView.findViewById(R.id.txt_cake_talk);
             this.txt_city = (TextView) itemView.findViewById(R.id.txt_city);
             this.txt_price = (TextView) itemView.findViewById(R.id.txt_price);
             this.img_online = (ImageView) itemView.findViewById(R.id.img_online);
             this.img_food = (ImageView) itemView.findViewById(R.id.img_food);

            this.RR_cake = (RelativeLayout) itemView.findViewById(R.id.RR_cake);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));


                }
            });

        }
    }

}

