package shubham.com.baqat.CreateNormalAddScreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import android.support.v7.widget.LinearLayoutManager;

import org.w3c.dom.Text;

import shubham.com.baqat.CreateNormalAddScreen.SubOccasionDataModel;
import shubham.com.baqat.R;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class CategorySectionRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<SubOccasionDataModel> modelList;

    private OnItemClickListener mItemClickListener;


    public CategorySectionRecyclerViewAdapter(Context context, ArrayList<SubOccasionDataModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<SubOccasionDataModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final SubOccasionDataModel model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

            if(modelList.get(position).getChecked()){
                genericViewHolder.img_check.setVisibility(View.VISIBLE);
            }else{
                genericViewHolder.img_check.setVisibility(View.GONE);

            }

              genericViewHolder.textView.setText(model.getCategoryName());
          //  genericViewHolder.itemTxtMessage.setText(model.getMessage());


        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private SubOccasionDataModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, SubOccasionDataModel model, ImageView img_check);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView img_check;

        public ViewHolder(final View itemView) {
            super(itemView);

            this.textView = (TextView) itemView.findViewById(R.id.textView);
            this.img_check = (ImageView) itemView.findViewById(R.id.img_check);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()),img_check );
                }
            });

        }
    }

}

