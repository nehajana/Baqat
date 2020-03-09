package shubham.com.baqat.CareerScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import shubham.com.baqat.BussinessPromoterScreen.BussinessPromoterActivity;
import shubham.com.baqat.CareerScreen.ApiModel.CareerDataList;
import shubham.com.baqat.CareerScreen.ApiModel.CareerDataModel;
import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
import shubham.com.baqat.RestaurantsCateringScreen.ApiModel.CateringDataList;
import shubham.com.baqat.RestaurantsCateringScreen.ApiModel.CateringDataModel;
import shubham.com.baqat.RestaurantsCateringScreen.RestaurentCateringActivity;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;

import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class BussinessCareerActivity extends AppCompatActivity implements IApiResponse {

    private RecyclerView recyclerView;
    RelativeLayout back_layout;
    TextView text_career;
    ProgressBar progressbar;
    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;
    private BussinessCareerRecyclerViewAdapter mAdapter;

    private ArrayList<BussinessCareerAbstractModel> modelList = new ArrayList<>();
    ArrayList<CareerDataList>careerList= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bussiness_career);


        // ButterKnife.bind(this);
        findViews();
        //setAdapter();
        progressbar.setVisibility(View.VISIBLE);
        text_career.setVisibility(View.VISIBLE);

        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        careerMethod();
    }

    private void findViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        back_layout = (RelativeLayout) findViewById(R.id.back_layout);
        text_career = (TextView) findViewById(R.id.text_career);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);

    }



    private void setAdapter(ArrayList<CareerDataList>careerList) {

        mAdapter = new BussinessCareerRecyclerViewAdapter(BussinessCareerActivity.this, this.careerList);

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

     }

    public void careerMethod(){

        HashMap<String, String> map = new HashMap<>();

        ApiRequest apiRequest = new ApiRequest(BussinessCareerActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_CAREER, Constants.USER_CAREER,map, Request.Method.GET);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_CAREER.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                CareerDataModel finalArray = new Gson().fromJson(response,new TypeToken<CareerDataModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (finalArray.getErrorCode()== 2 && finalArray.getMessage().equalsIgnoreCase("Please Insert Valid Access Token"))
                {
                    Preference.clearPreference(this);

                    Intent intent=new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else  if (status.equalsIgnoreCase("0")){

                    // progressbar.setVisibility(View.GONE);

                    careerList= (ArrayList<CareerDataList>) finalArray.getData();

                    setAdapter(careerList);


                   // Toast.makeText(this, ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
                else {

                    Toast.makeText(this,finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            progressbar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "Please Check Network", Toast.LENGTH_SHORT).show();

    }


}
