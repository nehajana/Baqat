package shubham.com.baqat.HarshitCreateAdd;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class Keyword_RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<kewordAddModel> modelList;

    private OnItemClickListener mItemClickListener;


    public Keyword_RecyclerViewAdapter(Context context, ArrayList<kewordAddModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<kewordAddModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.item_recycler_list_keyword, viewGroup, false );

        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final kewordAddModel model = getItem( position );
            ViewHolder genericViewHolder = (ViewHolder) holder;

          genericViewHolder.txt_lorem.setText(model.getKeywordname());

            genericViewHolder.img_cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(modelList.size()>0)
                    {
                        modelList.remove(position);

                        notifyItemRangeChanged(position,modelList.size());
                    }else
                    {
                        modelList.clear();
                        notifyDataSetChanged();

                    }


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

    private kewordAddModel getItem(int position) {
        return modelList.get( position );
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, kewordAddModel model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgUser;
        private TextView txt_lorem;
        private ImageView img_cross;


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
            super( itemView );

            // ButterKnife.bind(this, itemView);
            this.txt_lorem = (TextView) itemView.findViewById( R.id.txt_lorem );
            this.img_cross = (ImageView) itemView.findViewById( R.id.img_cross );
            /*this.itemTxtTitle = (TextView) itemView.findViewById( R.id.item_txt_title );
            this.itemTxtMessage = (TextView) itemView.findViewById( R.id.item_txt_message );*/


            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
               //     mItemClickListener.onItemClick( itemView, getAdapterPosition(), modelList.get( getAdapterPosition() ) );
                }
            } );

        }
    }

}

