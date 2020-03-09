package shubham.com.baqat.HomeSearchApi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import android.support.v7.widget.LinearLayoutManager;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import shubham.com.baqat.HomeSearchApi.Apimodel.HomedataSearchModel;
import shubham.com.baqat.R;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class HomeSearch_RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<HomedataSearchModel> modelList;

    private OnItemClickListener mItemClickListener;


    public HomeSearch_RecyclerViewAdapter(Context context, ArrayList<HomedataSearchModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<HomedataSearchModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_list_home_search, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final HomedataSearchModel model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

           genericViewHolder.user_name.setText(model.getFirstName());

           genericViewHolder.txt_cake_talk.setText(model.getListingTitle());
           genericViewHolder.txt_city.setText(model.getAddress());
           genericViewHolder.txt_price.setText(model.getListingPrice());
           genericViewHolder.txt_time.setText(model.getCreatedAt());

           String UserProfile=model.getProfilePicture().toString();
           String media_path=model.getMediaThumbPath().toString();

           if(!UserProfile.equalsIgnoreCase(""))
           {
               Picasso.with(mContext).load(UserProfile)
                       .placeholder(R.drawable.defaultprofile)
                       .into(genericViewHolder.img_circle);

           }
           if(!media_path.equalsIgnoreCase(""))
           {
               Picasso.with(mContext).load(media_path)
                       .placeholder(R.drawable.imagecamera)
                       .into(genericViewHolder.img_food);
           }

           String Online_user=model.getOnline().toString();

           if(Online_user.equalsIgnoreCase("Yes"))
           {
               genericViewHolder.img_online.setImageResource(R.drawable.online);
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

    private HomedataSearchModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, HomedataSearchModel model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView img_circle;
        private ImageView img_food;
        private ImageView img_online;
        private TextView user_name;
        private TextView txt_price;
        private TextView txt_city;
        private TextView txt_cake_talk,txt_time;


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

            this.user_name = (TextView) itemView.findViewById(R.id.user_name);
            this.txt_price = (TextView) itemView.findViewById(R.id.txt_price);
            this.txt_cake_talk = (TextView) itemView.findViewById(R.id.txt_cake_talk);
            this.txt_city = (TextView) itemView.findViewById(R.id.txt_city);
            this.img_online = (ImageView) itemView.findViewById(R.id.img_online);
            this.img_food = (ImageView) itemView.findViewById(R.id.img_food);
            this.txt_time=(TextView) itemView.findViewById(R.id.txt_time);
            this.img_circle=(CircleImageView) itemView.findViewById(R.id.img_circle);
         //   this.imgUser = (ImageView) itemView.findViewById(R.id.img_user);
         //   this.user_name = (TextView) itemView.findViewById(R.id.user_name);
         //   this.itemTxtMessage = (TextView) itemView.findViewById(R.id.item_txt_message);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));


                }
            });

        }
    }

}

