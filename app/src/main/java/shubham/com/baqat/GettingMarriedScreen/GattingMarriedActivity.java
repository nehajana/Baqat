package shubham.com.baqat.GettingMarriedScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import shubham.com.baqat.CreateNormalAddScreen.CreateNormalAddActivity;
import shubham.com.baqat.CreateNormalAddScreen.CustomAdapter;
import shubham.com.baqat.CreateNormalAddScreen.OccasionApiModel.OccasionDataModel;
import shubham.com.baqat.CreateNormalAddScreen.OccasionApiModel.SelectOccasionList;
import shubham.com.baqat.FilterScreen.FilterActivity;
import shubham.com.baqat.GettingMarriedScreen.ApiModel.GettingDataModel;
import shubham.com.baqat.GettingMarriedScreen.ApiModel.GettingMarriedDataList;
import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
import shubham.com.baqat.RestaurantsCateringScreen.RestaurentCateringActivity;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;

import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class GattingMarriedActivity extends AppCompatActivity  implements IApiResponse {

    private RecyclerView recyclerView;

    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;
    RelativeLayout back_layout;
    RelativeLayout RR_filter;
    RelativeLayout RR_search;
    TextView text_career;
    ProgressBar progressbar;
    private GattingMarriedRecyclerViewAdapter mAdapter;

    private ArrayList<GattingMarriedAbstractModel> modelList = new ArrayList<>();
    ArrayList<GettingMarriedDataList> GettingMarriedList = new ArrayList<>();


    String Category_id="";
    String Category_Name="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gatting_married);

        findViews();

        text_career.setVisibility(View.VISIBLE);
        RR_filter.setVisibility(View.VISIBLE);
        RR_search.setVisibility(View.VISIBLE);
        progressbar.setVisibility(View.VISIBLE);

        Intent intent=getIntent();

        if(intent!=null)
        {
            Category_id=intent.getStringExtra("Category_id").toString();
            Category_Name=intent.getStringExtra("Category_name").toString();

            text_career.setText(Category_Name);

            gattingMarriedMethod(Category_id);
        }


        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        RR_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(GattingMarriedActivity.this, FilterActivity.class);
                intent.putExtra("Category_id",Category_id);
                intent.putExtra("Category_name",Category_Name);
                startActivity(intent);

            }
        });
    }

    private void findViews() {

        RR_filter=(RelativeLayout) findViewById(R.id.RR_filter);
        RR_search=(RelativeLayout) findViewById(R.id.RR_search);
        back_layout=(RelativeLayout) findViewById(R.id.back_layout);
        progressbar=(ProgressBar) findViewById(R.id.progressbar);
        text_career=(TextView) findViewById(R.id.text_career);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }


    private void setAdapter(ArrayList<GettingMarriedDataList> gettingMarriedList) {


        mAdapter = new GattingMarriedRecyclerViewAdapter(GattingMarriedActivity.this, gettingMarriedList);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager =new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);


        mAdapter.SetOnItemClickListener(new GattingMarriedRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, GettingMarriedDataList model) {

                //handle item click events here
                Intent intent=new Intent(GattingMarriedActivity.this, RestaurentCateringActivity.class);
                intent.putExtra("Category_id",model.getCategoryId());
                intent.putExtra("Category_name",model.getCategoryName());
                startActivity(intent);

            }
        });


    }


    public void gattingMarriedMethod(String parent_category_id){

        HashMap<String, String> map = new HashMap<>();

        map.put("parent_category_id",parent_category_id);

        map.put("is_mobile","1");


        ApiRequest apiRequest = new ApiRequest(GattingMarriedActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_Select_Gatting_Married, Constants.USER_Select_Gatting_Married,map, Request.Method.POST);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_Select_Gatting_Married.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                GettingDataModel finalArray = new Gson().fromJson(response,new TypeToken<GettingDataModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (finalArray.getErrorCode()== 2 && finalArray.getMessage().equalsIgnoreCase("Please Insert Valid Access Token"))
                {
                    Preference.clearPreference(this);
                    Intent intent=new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else   if (status.equalsIgnoreCase("0")){

                    progressbar.setVisibility(View.GONE);

                    GettingMarriedList= (ArrayList<GettingMarriedDataList>) finalArray.getData();

                    setAdapter(GettingMarriedList);

                    //Toast.makeText(this, ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
                else {

                    Toast.makeText(this,finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "Please Check Network", Toast.LENGTH_SHORT).show();

    }


}
