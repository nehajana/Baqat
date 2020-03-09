package shubham.com.baqat.HomeFragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import shubham.com.baqat.GettingMarriedScreen.GattingMarriedActivity;
import shubham.com.baqat.HomeFragment.ApiModel.HomeAdsDataModel;
import shubham.com.baqat.HomeFragment.ApiModel.HomeAdsModel;
import shubham.com.baqat.HomeFragment.ApiModel.HomeDataModel;
import shubham.com.baqat.HomeFragment.ApiModel.HomeModel;
import shubham.com.baqat.HomeFragment.ApiModel.TopNewsDataModel;
import shubham.com.baqat.HomeFragment.ApiModel.TopNewsModel;
import shubham.com.baqat.HomeSearchApi.HomeSearch;
import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.MainActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
import shubham.com.baqat.Singupscreen.SignUpActivity;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;

public class HomeFragment extends Fragment  implements IApiResponse {

    View rootView;
    private RecyclerView recyclerView;
    private RecyclerView recycler_view_ads;
    private HomeVerticle_RecyclerViewAdapter mAdapter;
    private HomeAds_RecyclerViewAdapter mAdapter_ads;
    private ArrayList<HomeDataModel> modelList = new ArrayList<>();
    private ArrayList<HomeAdsDataModel> modelList_ads = new ArrayList<>();

    List<TopNewsDataModel> image_list;
    ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    Timer swipeTimer;

    ProgressBar progressbar;

    RelativeLayout RR_serchbar;
    TextView edt_search;


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_home_fragment, container, false);

        findViews();

        recyclerView.setNestedScrollingEnabled(false);

        progressbar.setVisibility(View.VISIBLE);

        image_list = new ArrayList<TopNewsDataModel>();

        recycler_view_ads=(RecyclerView) rootView.findViewById(R.id.recycler_view_ads);

        RR_serchbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(), HomeSearch.class);
                startActivity(intent);
            }
        });

        edt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent intent=new Intent(getActivity(), HomeSearch.class);
                startActivity(intent);
            }
        });


        homeMethod();

        topNewsMethod();

        adsMethod();

        return  rootView;
    }

    private void findViews() {
        edt_search=(TextView)  rootView.findViewById(R.id.edt_search);
        RR_serchbar = (RelativeLayout) rootView.findViewById(R.id.RR_serchbar);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recycler_view_ads = (RecyclerView) rootView.findViewById(R.id.recycler_view_ads);
        progressbar = (ProgressBar) rootView.findViewById(R.id.progressbar);
    }

    private void setAdapter(ArrayList<HomeDataModel> modelList) {

        mAdapter = new HomeVerticle_RecyclerViewAdapter(getActivity(), this.modelList);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(new HomeVerticle_RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, HomeDataModel model) {
                //handle item click events here
             //   Toast.makeText(getActivity(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }

   private void setAdapter_Ads(ArrayList<HomeAdsDataModel> modelList_ads) {

       mAdapter_ads = new HomeAds_RecyclerViewAdapter(getActivity(), this.modelList_ads);
        recycler_view_ads.setHasFixedSize(true);

        // use a linear layout manager
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
       recycler_view_ads.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
       recycler_view_ads.setAdapter(mAdapter_ads);
       mAdapter_ads.SetOnItemClickListener(new HomeAds_RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, HomeAdsDataModel model) {
                //handle item click events here
                //   Toast.makeText(getActivity(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();

            }
        });

    }


    private void init(List<TopNewsDataModel> image_list) {

        // CirclePageIndicator indicator = null;

     /*   for(int i=0;i<image_list.size() ;i++) {
            //ImagesArray.add(IMAGES[i]);
            //    indicator = (CirclePageIndicator) rootView.findViewById(R.id.indicator);
            //indicator.setViewPager(mPager);
            final float density = getResources().getDisplayMetrics().density;
            //Set circle indicator radius
            //  indicator.setRadius(5 * density);
        }*/

        mPager = (ViewPager) rootView.findViewById(R.id.pager);

        mPager.setAdapter(new SlidingImage_Adapter(getActivity(), this.image_list,mPager));

    /*    mPager.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
             *//*   if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;*//*
                Toast.makeText(getActivity(), "sadfsad"+mPager.getCurrentItem(), Toast.LENGTH_SHORT).show();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(browserIntent);
            }
        });
*/
        NUM_PAGES = this.image_list.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {

            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 4000);

    }

    public void homeMethod(){

        HashMap<String, String> map = new HashMap<>();
        map.put("is_mobile","1");
        ApiRequest apiRequest = new ApiRequest(getActivity(),this);
        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_HOME, Constants.USER_HOME,map, Request.Method.POST);

    }

    public void topNewsMethod(){

        HashMap<String, String> map = new HashMap<>();

        map.put("is_mobile","1");

        ApiRequest apiRequest = new ApiRequest(getActivity(),this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_HOME_TopNewsAds, Constants.USER_HOME_TopNewsAds,map, Request.Method.POST);

    }

    public void adsMethod(){

        HashMap<String, String> map = new HashMap<>();

        map.put("is_mobile","1");

        ApiRequest apiRequest = new ApiRequest(getActivity(),this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_HOME_ExecutiveAds, Constants.USER_HOME_ExecutiveAds,map, Request.Method.POST);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_HOME.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                HomeModel finalArray = new Gson().fromJson(response,new TypeToken<HomeModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (finalArray.getErrorCode()== 2 && finalArray.getMessage().equalsIgnoreCase("Please Insert Valid Access Token"))
                {
                    Preference.clearPreference(getActivity());
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else   if (status.equalsIgnoreCase("0")){

                 //   Toast.makeText(getActivity(), ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                    modelList= (ArrayList<HomeDataModel>) finalArray.getData();

                    progressbar.setVisibility(View.GONE);

                    setAdapter(modelList);
                }
                else {

                    Toast.makeText(getActivity(), finalArray.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }else if (Constants.USER_HOME_TopNewsAds.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                TopNewsModel finalArray = new Gson().fromJson(response,new TypeToken<TopNewsModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (finalArray.getErrorCode()== 2 && finalArray.getMessage().equalsIgnoreCase("Please Insert Valid Access Token"))
                {
                    Preference.clearPreference(getActivity());
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else   if (status.equalsIgnoreCase("0")){

                    //Toast.makeText(getActivity(), ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                    progressbar.setVisibility(View.GONE);

                    image_list=finalArray.getData();

                    init(image_list);

                }
                else {

                    Toast.makeText(getActivity(), finalArray.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }else if (Constants.USER_HOME_ExecutiveAds.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                HomeAdsModel finalArray = new Gson().fromJson(response,new TypeToken<HomeAdsModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (finalArray.getErrorCode()== 2 && finalArray.getMessage().equalsIgnoreCase("Please Insert Valid Access Token"))
                {
                    Preference.clearPreference(getActivity());
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else   if (status.equalsIgnoreCase("0")){

                    progressbar.setVisibility(View.GONE);

                    modelList_ads= (ArrayList<HomeAdsDataModel>) finalArray.getData();

                    setAdapter_Ads(modelList_ads);

                }
                else {

                    Toast.makeText(getActivity(), finalArray.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {


        Toast.makeText(getActivity(), "Please Check Network", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (swipeTimer != null){

            swipeTimer.cancel();
        }
    }

}
