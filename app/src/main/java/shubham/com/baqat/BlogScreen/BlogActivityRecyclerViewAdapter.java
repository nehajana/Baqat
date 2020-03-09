package shubham.com.baqat.BlogScreen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.support.v7.widget.LinearLayoutManager;

import shubham.com.baqat.BlogScreen.ApiModel.BlogDataList;
import shubham.com.baqat.CareerScreen.ApiModel.CareerDataList;
import shubham.com.baqat.R;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class BlogActivityRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<BlogDataList> modelList;

    private OnItemClickListener mItemClickListener;


    public BlogActivityRecyclerViewAdapter(Context context, ArrayList<BlogDataList> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<BlogDataList> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.blog_item_recycler_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final BlogDataList model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

            String content=model.getListingContent().toString();
            if(model.getAuthor_name() == null || model.getAuthor_name().equalsIgnoreCase("")){
                genericViewHolder.txt_baqat.setText("Unknown");
            }else {
                genericViewHolder.txt_baqat.setText(model.getAuthor_name());
            }
            genericViewHolder.txt_bussiness.setText(model.getListingTitle());

            genericViewHolder.txt_date.setText(model.getCreatedAt());
            genericViewHolder.btn_readmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(model.getWebsite()));
                    mContext.startActivity(browserIntent);

                }
            });

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                genericViewHolder.txt_contant.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_COMPACT));

            } else {

                genericViewHolder.txt_contant.setText(Html.fromHtml(content));
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

    private BlogDataList getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, BlogDataList model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgUser;
        private TextView txt_contant;
        private TextView itemTxtMessage;
        private TextView btn_readmore,txt_baqat,txt_bussiness,txt_date;

        public ViewHolder(final View itemView) {
            super(itemView);

            this.txt_contant = (TextView) itemView.findViewById(R.id.txt_contant);
            this.btn_readmore = (TextView) itemView.findViewById(R.id.btn_readmore);
            this.txt_bussiness = (TextView) itemView.findViewById(R.id.txt_bussiness);
            this.txt_baqat = (TextView) itemView.findViewById(R.id.txt_baqat);
            this.txt_date = (TextView) itemView.findViewById(R.id.txt_date);

           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));


                }
            });*/

        }
    }

}

