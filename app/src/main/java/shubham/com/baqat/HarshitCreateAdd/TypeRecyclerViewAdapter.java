package shubham.com.baqat.HarshitCreateAdd;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import android.support.v7.widget.LinearLayoutManager;

import shubham.com.baqat.HarshitCreateAdd.ApiModel.Info;
import shubham.com.baqat.R;


/**
 * A custom adapter to use with the RecyclerView widget.
 */

public class TypeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<Info> modelList;
    private OnItemClickListener mItemClickListener;

    public TypeRecyclerViewAdapter(Context context, ArrayList<Info> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<Info> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_type_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final Info model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.itemTxtTitle.setText(model.getValue());
            //   genericViewHolder.itemTxtMessage.setText(model.getMessage());
        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private Info getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, Info model, ImageView img_check);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_check;
        private TextView itemTxtTitle;
        private TextView itemTxtMessage;

        public ViewHolder(final View itemView) {
            super(itemView);
            // ButterKnife.bind(this, itemView);
            this.img_check = (ImageView) itemView.findViewById(R.id.img_check);
            this.itemTxtTitle = (TextView) itemView.findViewById(R.id.textView);
            //   this.itemTxtMessage = (TextView) itemView.findViewById(R.id.item_txt_message);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()),img_check );
                }
            });
        }
    }

}

