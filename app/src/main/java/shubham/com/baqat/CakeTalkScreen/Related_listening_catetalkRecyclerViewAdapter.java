package shubham.com.baqat.CakeTalkScreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import shubham.com.baqat.R;
import shubham.com.baqat.RestaurantsCateringScreen.RestaurentCateringRecyclerViewAdapter;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class Related_listening_catetalkRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> modelList;

    private OnItemClickListener mItemClickListener;


    public Related_listening_catetalkRecyclerViewAdapter(Context context, List<String> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(List<String> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_related_listening_cake_tal, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {

           ViewHolder genericViewHolder = (ViewHolder) holder;

            String img_Url_ad=modelList.get(position).toString();
            System.out.println("img_Url_ad :"+img_Url_ad);

            if(!img_Url_ad.equalsIgnoreCase(""))
            {
                Picasso.with(mContext).load(img_Url_ad)
                        .placeholder(R.drawable.defaultprofile)
                        .into(genericViewHolder.img_food);
            }


          //  final RelatedListingAbstractModel model = getItem(position);
          //  ViewHolder genericViewHolder = (ViewHolder) holder;

           /* genericViewHolder.itemTxtTitle.setText(model.getTitle());
            genericViewHolder.itemTxtMessage.setText(model.getMessage());*/


        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private String getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, RelatedListingAbstractModel model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_food;
        private TextView itemTxtTitle;
        private TextView itemTxtMessage;


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
            this.img_food = (ImageView) itemView.findViewById(R.id.img_food);

           /* this.img_food = (ImageView) itemView.findViewById(R.id.img_food);
           /* this.imgUser = (ImageView) itemView.findViewById(R.id.img_user);
            this.itemTxtTitle = (TextView) itemView.findViewById(R.id.item_txt_title);
            this.itemTxtMessage = (TextView) itemView.findViewById(R.id.item_txt_message);
*/

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));


                }
            });

        }
    }

}

