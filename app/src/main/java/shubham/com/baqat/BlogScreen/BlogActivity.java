package shubham.com.baqat.BlogScreen;

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

import shubham.com.baqat.BlogScreen.ApiModel.BlogDataList;
import shubham.com.baqat.BlogScreen.ApiModel.BlogDataModel;
import shubham.com.baqat.CareerScreen.ApiModel.CareerDataList;
import shubham.com.baqat.CareerScreen.ApiModel.CareerDataModel;
import shubham.com.baqat.CareerScreen.BussinessCareerActivity;
import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
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


public class BlogActivity extends AppCompatActivity implements IApiResponse {

    private RecyclerView recyclerView;
    RelativeLayout back_layout;
    TextView text_career;

    private BlogActivityRecyclerViewAdapter mAdapter;

    private ArrayList<BlogActivityAbstractModel> modelList = new ArrayList<>();
    ArrayList<BlogDataList> blogList= new ArrayList<>();

    ProgressBar progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        findViews();
        text_career.setVisibility(View.VISIBLE);
        text_career.setText("Blog");
        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        blogMethod();
    }

    private void findViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        back_layout = (RelativeLayout) findViewById(R.id.back_layout);
        text_career = (TextView) findViewById(R.id.text_career);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
    }


    private void setAdapter(ArrayList<BlogDataList> blogList) {

        mAdapter = new BlogActivityRecyclerViewAdapter(BlogActivity.this, this.blogList);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
      /*  mAdapter.SetOnItemClickListener(new BlogActivityRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, BlogDataList model) {
                //handle item click events here
                Toast.makeText(BlogActivity.this, "Hey " + model.getCityName(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    public void blogMethod(){

        progressbar.setVisibility(View.VISIBLE);

        HashMap<String, String> map = new HashMap<>();

        ApiRequest apiRequest = new ApiRequest(BlogActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_BLOG, Constants.USER_BLOG,map, Request.Method.GET);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_BLOG.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                progressbar.setVisibility(View.GONE);

                BlogDataModel finalArray = new Gson().fromJson(response,new TypeToken<BlogDataModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (finalArray.getErrorCode()== 2 && finalArray.getMessage().equalsIgnoreCase("Please Insert Valid Access Token"))
                {
                    Preference.clearPreference(this);
                    Intent intent=new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else    if (status.equalsIgnoreCase("0")){
                    blogList= (ArrayList<BlogDataList>) finalArray.getData();
                    setAdapter(blogList);
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
