package shubham.com.baqat.RestaurantsCateringScreen;

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

import shubham.com.baqat.CakeTalkScreen.CakeTalkActivity;
import shubham.com.baqat.FilterScreen.FilterActivity;
import shubham.com.baqat.GettingMarriedScreen.ApiModel.GettingDataModel;
import shubham.com.baqat.GettingMarriedScreen.ApiModel.GettingMarriedDataList;
import shubham.com.baqat.GettingMarriedScreen.GattingMarriedActivity;
import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
import shubham.com.baqat.RestaurantsCateringScreen.ApiModel.CateringDataList;
import shubham.com.baqat.RestaurantsCateringScreen.ApiModel.CateringDataModel;
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


public class RestaurentCateringActivity extends AppCompatActivity  implements IApiResponse {

    private RecyclerView recycler_view_restaurent;
    TextView text_career;
    ProgressBar progressbar;

    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;
    private RestaurentCateringRecyclerViewAdapter mAdapter;

    private ArrayList<RestaurentCateringAbstractModel> modelList = new ArrayList<>();
    ArrayList<CateringDataList>CateringdList = new ArrayList<>();

    String Category_id;
    String Category_Name;
    TextView txt_empty;
    RelativeLayout back_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurent_catering);

        findView();

        text_career.setVisibility(View.VISIBLE);
        text_career.setText("Restaurant & Catering");

        Intent intent=getIntent();

        if(intent!=null)
        {
            Category_id=intent.getStringExtra("Category_id").toString();
            Category_Name=intent.getStringExtra("Category_name").toString();
            text_career.setText(Category_Name);
            progressbar.setVisibility(View.VISIBLE);
            //getCategoryByParentId(Category_id);

            advanceSearch("dubai",Category_id);

        }

        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void findView() {
        back_layout = (RelativeLayout) findViewById(R.id.back_layout);
        txt_empty=(TextView) findViewById(R.id.txt_empty);
        text_career=(TextView) findViewById(R.id.text_career);
        progressbar=(ProgressBar) findViewById(R.id.progressbar);
        recycler_view_restaurent = (RecyclerView) findViewById(R.id.recycler_view_restaurent);
    }


    private void setAdapter(ArrayList<CateringDataList> CateringdList) {

        mAdapter = new RestaurentCateringRecyclerViewAdapter(RestaurentCateringActivity.this, CateringdList);
        recycler_view_restaurent.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager =new GridLayoutManager(this, 2);
        recycler_view_restaurent.setLayoutManager(layoutManager);
        recycler_view_restaurent.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new RestaurentCateringRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, CateringDataList model) {

                Intent intent=new Intent(RestaurentCateringActivity.this,CakeTalkActivity.class);
                intent.putExtra("listing_id",model.getListingId());
                intent.putExtra("Media_ThumbPath",model.getMediaThumbPath());
                startActivity(intent);
            }
        });


    }

    public void  getCategoryByParentId(String parent_category_id){

        HashMap<String, String> map = new HashMap<>();

        map.put("parent_category_id",parent_category_id);

        map.put("is_mobile","1");

        ApiRequest apiRequest = new ApiRequest(RestaurentCateringActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_getCategoryByParentId, Constants.USER_getCategoryByParentId,map, Request.Method.POST);

    }

    public void advanceSearch(String location,String Category_id){

        String country_name= Preference.get(RestaurentCateringActivity.this,Preference.key_filter_location);
        String minimum_value= Preference.get(RestaurentCateringActivity.this,Preference.key_minimumPriceValue);
        String maximum_value=  Preference.get(RestaurentCateringActivity.this,Preference.key_maximumPriceValue);

      /*  is_mobile:1
        pageLimit:
        pageNo:
        keyword:food
        category_id:10
        price_min:100
        price_max:400
        access_token:
        sorting:1
        location:dubai
        filter: */

        HashMap<String, String> map = new HashMap<>();
        //  map.put("category_id","10");
        map.put("category_id",Category_id);
        map.put("is_mobile","1");
        map.put("pageLimit","");
        map.put("pageNo","");
        map.put("keyword","Food");

        map.put("price_min",minimum_value);
        map.put("price_max",maximum_value);
//        map.put("access_token","");
        map.put("sorting","1");
        map.put("location",location);
        map.put("filter","{\n" +
                " \t\t\"data\": [{\n" +
                " \t\t\t\"filter_key\": \"type\",\n" +
                " \t\t\t\"filter_value\": \"'International', 'Veg'\"\n" +
                " \t\t},{\n" +
                " \t\t\t\"filter_key\": \"condition\",\n" +
                " \t\t\t\"filter_value\": \"'Used'\"\n" +
                " \t\t}]\n" +
                "\t}");



        ApiRequest apiRequest = new ApiRequest(RestaurentCateringActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_advanceSearch, Constants.USER_advanceSearch,map, Request.Method.POST);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_advanceSearch.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                progressbar.setVisibility(View.GONE);

                CateringDataModel finalArray = new Gson().fromJson(response,new TypeToken<CateringDataModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (finalArray.getErrorCode()== 2 && finalArray.getMessage().equalsIgnoreCase("Please Insert Valid Access Token"))
                {
                    Preference.clearPreference(this);
                    Intent intent=new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else  if (status.equalsIgnoreCase("0")){

                    progressbar.setVisibility(View.GONE);

                    txt_empty.setVisibility(View.GONE);

                    CateringdList= (ArrayList<CateringDataList>) finalArray.getData();

                    setAdapter(CateringdList);


                    //  Toast.makeText(this, ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
                else {

                    txt_empty.setVisibility(View.VISIBLE);

                    //  Toast.makeText(this,finalArray.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "Please Check Network", Toast.LENGTH_SHORT).show();

    }


}
