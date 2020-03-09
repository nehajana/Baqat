package shubham.com.baqat.HomeFragment;

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

import android.support.v7.widget.LinearLayoutManager;

import com.squareup.picasso.Picasso;

import shubham.com.baqat.BussinessPromoterScreen.BussinessPromoterActivity;
import shubham.com.baqat.GettingMarriedScreen.GattingMarriedActivity;
import shubham.com.baqat.HomeFragment.ApiModel.HomeDataModel;
import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class HomeVerticle_RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<HomeDataModel> modelList;

    private OnItemClickListener mItemClickListener;


    public HomeVerticle_RecyclerViewAdapter(Context context, ArrayList<HomeDataModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<HomeDataModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_list_home_verticle, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final HomeDataModel model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

            //  genericViewHolder.imgUser.setImageResource(model.getImg());
            genericViewHolder.txt_title.setText(model.getCategoryName());
            // genericViewHolder.txt_description.setText();

            String description= model.getDescription().toString();

            Picasso.with(mContext).load(model.getWebImg())
                    .placeholder(R.drawable.baby_icon)
                    .into(genericViewHolder.imgUser);

            final String category_id=model.getCategoryId().toString();
            final String category_Name=model.getCategoryName().toString();

            genericViewHolder.Read_more_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


      Intent intent=new Intent(mContext, GattingMarriedActivity.class);
                    intent.putExtra("Category_id",category_id);
                    intent.putExtra("Category_name",category_Name);
                    mContext.startActivity(intent);



                }
            });

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                genericViewHolder.txt_description.setText(Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT));
            } else {
                genericViewHolder.txt_description.setText(Html.fromHtml(description));
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

    private HomeDataModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, HomeDataModel model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgUser;
        private TextView txt_title;
        private TextView txt_description;
        private Button Read_more_btn;


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

            this.imgUser = (ImageView) itemView.findViewById(R.id.imgUser);

            //this.imgUser = (ImageView) itemView.findViewById(R.id.imgUser);
            this.txt_title = (TextView) itemView.findViewById(R.id.txt_title);
            this.txt_description = (TextView) itemView.findViewById(R.id.txt_description);
            this.Read_more_btn = (Button) itemView.findViewById(R.id.Read_more_btn);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });

        }
    }

}

