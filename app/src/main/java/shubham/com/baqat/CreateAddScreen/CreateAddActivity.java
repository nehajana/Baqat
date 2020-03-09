package shubham.com.baqat.CreateAddScreen;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

import shubham.com.baqat.CreateAddScreen.ApiModel.CreateAdsDataList;
import shubham.com.baqat.CreateAddScreen.ApiModel.CreateAdsDataModel;
import shubham.com.baqat.CreateBologAdsScreen.CreateBlogActivity;
import shubham.com.baqat.CreateExecutivAddScren.CreateExecutiveAdsActivity;
import shubham.com.baqat.CreateNormalAddScreen.CreateNormalAddActivity;
import shubham.com.baqat.CreateTopNewsScreen.CreateTopNewsAds;
import shubham.com.baqat.GettingMarriedScreen.ApiModel.GettingMarriedDataList;
import shubham.com.baqat.HarshitCreateAdd.HarshitCreateNormalAdd;
import shubham.com.baqat.HomeBottomScreen.HomeBottomActivity;
import shubham.com.baqat.LoginSceen.ApiModel.LoginModel;
import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
import shubham.com.baqat.UserProfile.UserProfileActivity;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;

public class CreateAddActivity extends AppCompatActivity implements IApiResponse{

    CardView card_normal_ads,card_executive_ads,card_blog_ads,card_topNews_ads;
    ImageView img_select_normal,img_executive,img_Blog,img_Top_news;
    ImageView img_regular_ad,img_regular_add,img_rupgrad_ad,img_executive_ad;
    LinearLayout LL_executive_blog;
    LinearLayout LL_Top_news;
    CreateAdsDataModel finalArray;
    // Regular 1 month
    TextView txtNormalAds,txt_regularAd,txt_regularAd_months,txt_regular_amount;

    // Regular 3 month
    TextView txt_regularAdd,txt_regularAdd_months,txt_regular_ads_amount;

    // upgrade 1 month
    TextView txt_upgraderAd,txt_upgraderAd_months,txt_upgrade_amount;

    RelativeLayout RR_normal,RR_executive;
    LinearLayout LL_normal;
    RelativeLayout back_layout;
    TextView text_career;
    Button proceed_btn;
    RelativeLayout RR_one,RR_right,RR_center;

    RelativeLayout RR_regular_ad,RR_regular_add,RR_upgrad_ad,RR_executive_month;

    LinearLayout LL_normal_ads_details,LL_executive_ads;

    CardView card_regular_add;
    View vw_dummy;
    ImageView img_arrow_down,img_arrow_up,img_arrow_down_exe,img_arrow_up_exe;

    ArrayList<CreateAdsDataList>AdsList=new ArrayList();


    boolean isNormanlAds =true;
    boolean isExecutiveAds =false;
    boolean isBlogAds =false;
    boolean isTopNewsAds =false;

    TextView txt_executive_message;
    TextView txt_executive_month;
    TextView txt_executive_price;

    TextView txt_blog_month_ads;
    TextView txt_blog_price_ads;
    TextView txt_blog_message_ads;

    TextView txtTop_news_month_ads;
    TextView txt_Top_news_price_ads;
    TextView txt_view_Top_news_ad_message_ads;

    ProgressBar progressbar;

    String price_TopNewsAds="";
    String price_BlogAds="";
    String Craete_Month_validy="";
    String Craete_second_Month_validy="";

    String price_executiveAds="";
    String price_normalAds="75";

    String valid_month="1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_add);

        text_career=(TextView) findViewById(R.id.text_career);
        card_regular_add=(CardView) findViewById(R.id.card_regular_add);
        vw_dummy=(View) findViewById(R.id.vw_dummy);
        img_arrow_down=(ImageView) findViewById(R.id.img_arrow_down);
        img_arrow_up=(ImageView) findViewById(R.id.img_arrow_up);


        img_arrow_down_exe=(ImageView) findViewById(R.id.img_arrow_down_exe);
        img_arrow_up_exe=(ImageView) findViewById(R.id.img_arrow_up_exe);

        text_career.setVisibility(View.VISIBLE);
        text_career.setText("Create Ads");


        Preference.save(CreateAddActivity.this,Preference.key_valid_ads,"1");

        FindView();

        progressbar.setVisibility(View.VISIBLE);

        // -------- Normal Ads ----------------
        RR_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNormalAdsSection();
                hideExecutiveSection();

                isNormanlAds =true;
                isExecutiveAds =false;
                isBlogAds =false;
                isTopNewsAds =false;

                Preference.save(CreateAddActivity.this,Preference.key_valid_ads,valid_month);

                Preference.save(CreateAddActivity.this,Preference.key_price_TopNewsAds,price_normalAds);
            }
        });


        // Regular Ads 1 month
        RR_regular_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Intent intent =new Intent(CreateAddActivity.this, CreateNormalAddActivity.class);
                startActivity(intent);*/

                price_normalAds ="150";

                valid_month="1";

                Preference.save(CreateAddActivity.this,Preference.key_valid_ads,valid_month);

                Preference.save(CreateAddActivity.this,Preference.key_price_TopNewsAds,price_normalAds);


                img_regular_ad.setImageResource(R.drawable.radio_button_selected);
                img_regular_add.setImageResource(R.drawable.radio_button_unselected);
                img_rupgrad_ad.setImageResource(R.drawable.radio_button_unselected);

                isNormanlAds =true;
                isExecutiveAds =false;
                isBlogAds =false;
                isTopNewsAds =false;

            }
        });

        // Regular Ads 3 month
        RR_regular_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Intent intent =new Intent(CreateAddActivity.this, CreateNormalAddActivity.class);
                startActivity(intent);
*/
                price_normalAds ="50";

                valid_month="3";

                Preference.save(CreateAddActivity.this,Preference.key_valid_ads,valid_month);

                Preference.save(CreateAddActivity.this,Preference.key_price_TopNewsAds,price_normalAds);

                img_regular_ad.setImageResource(R.drawable.radio_button_unselected);
                img_regular_add.setImageResource(R.drawable.radio_button_selected);
                img_rupgrad_ad.setImageResource(R.drawable.radio_button_unselected);


                isNormanlAds =true;
                isExecutiveAds =false;
                isBlogAds =false;
                isTopNewsAds =false;



            }
        });

        // upgrade Ads 1 month
        RR_upgrad_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                Intent intent =new Intent(CreateAddActivity.this, CreateNormalAddActivity.class);
                startActivity(intent);*/

                img_regular_ad.setImageResource(R.drawable.radio_button_unselected);
                img_regular_add.setImageResource(R.drawable.radio_button_unselected);
                img_rupgrad_ad.setImageResource(R.drawable.radio_button_selected);


                isNormanlAds =true;
                isExecutiveAds =false;
                isBlogAds =false;
                isTopNewsAds =false;


            }
        });


        //-------- Executive Ads ----------------

        RR_executive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              /*  Intent intent =new Intent(CreateAddActivity.this, CreateExecutiveAdsActivity.class);
                startActivity(intent);*/

                //Hide Normal Ads
                hideNormalAdsSection();

                // Show Exective Ads
                showExecutiveAdsSection();


                isExecutiveAds =true;
                isNormanlAds =false;
                isBlogAds =false;
                isTopNewsAds =false;

            }
        });


        // -------- Blog Ads ----------------
        card_blog_ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

           /*     Intent intent=new Intent(CreateAddActivity.this, CreateBlogActivity.class);
                startActivity(intent);*/

                LL_executive_blog.setVisibility(View.VISIBLE);
                LL_Top_news.setVisibility(View.GONE);

                img_select_normal.setImageResource(R.drawable.radio_button_unselected);
                img_executive.setImageResource(R.drawable.radio_button_unselected);
                img_Blog.setImageResource(R.drawable.radio_button_selected);
                img_Top_news.setImageResource(R.drawable.radio_button_unselected);

                hideNormalAdsSection();
                hideExecutiveSection();

                isBlogAds =true;
                isExecutiveAds =false;
                isNormanlAds =false;
                isTopNewsAds =false;

            }
        });


        //-------- Top New Ads ----------------

        card_topNews_ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                  /*  Intent intent=new Intent(CreateAddActivity.this, CreateTopNewsAds.class);
                    startActivity(intent);*/
                LL_executive_blog.setVisibility(View.GONE);
                LL_Top_news.setVisibility(View.VISIBLE);
                img_select_normal.setImageResource(R.drawable.radio_button_unselected);
                img_executive.setImageResource(R.drawable.radio_button_unselected);
                img_Blog.setImageResource(R.drawable.radio_button_unselected);
                img_Top_news.setImageResource(R.drawable.radio_button_selected);

                hideNormalAdsSection();
                hideExecutiveSection();

                isTopNewsAds =true;
                isBlogAds =false;
                isExecutiveAds =false;
                isNormanlAds =false;

            }
        });


        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        proceed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(isNormanlAds)
                {

                    Preference.save(CreateAddActivity.this,Preference.key_price_TopNewsAds,price_normalAds);

                  //  String normalAdsMonths=finalArray.getData().get(0).getAdsValidity().toString();
                    int months = 0;
                    String names = "30 Days";
                    String[] namesList = names.split("");

                    String checkStr = namesList [1];

                    if(checkStr.equalsIgnoreCase("Days") ){
                        String days = namesList [0];
                        months =  Integer.parseInt(days) / 30 ;

                    }else  if(checkStr.equalsIgnoreCase("Months") ){
                        months = Integer.parseInt(namesList [0]);
                    }

                   // Toast.makeText(CreateAddActivity.this, ""+checkStr, Toast.LENGTH_SHORT).show();

                    Intent intent =new Intent(CreateAddActivity.this, HarshitCreateNormalAdd.class);
                    startActivity(intent);

                }else if(isExecutiveAds)
                {
                    valid_month="1";

                    Preference.save(CreateAddActivity.this,Preference.key_valid_ads,"1");

                    Preference.save(CreateAddActivity.this,Preference.key_price_TopNewsAds,price_executiveAds);

                    Intent intent =new Intent(CreateAddActivity.this, CreateExecutiveAdsActivity.class);
                    startActivity(intent);

                }else if(isBlogAds)
                {

                    valid_month="3";

                    Preference.save(CreateAddActivity.this,Preference.key_valid_ads,"90");

                    Preference.save(CreateAddActivity.this,Preference.key_price_TopNewsAds,price_BlogAds);

                    Intent intent=new Intent(CreateAddActivity.this, CreateBlogActivity.class);
                    startActivity(intent);

                }else if(isTopNewsAds)
                {

                    Preference.save(CreateAddActivity.this,Preference.key_valid_ads,"1");

                    Preference.save(CreateAddActivity.this,Preference.key_price_TopNewsAds,price_TopNewsAds);

                    Intent intent=new Intent(CreateAddActivity.this, CreateTopNewsAds.class);
                    startActivity(intent);

                }
            }
        });

        createAdsMethod();
    }

    private void showExecutiveAdsSection() {
        LL_Top_news.setVisibility(View.GONE);

        LL_executive_blog.setVisibility(View.GONE);
        img_executive.setImageResource(R.drawable.radio_button_selected);
        img_Blog.setImageResource(R.drawable.radio_button_unselected);
        img_Top_news.setImageResource(R.drawable.radio_button_unselected);

        LL_executive_ads.setVisibility(View.VISIBLE);

        img_arrow_down_exe.setVisibility(View.GONE);
        img_arrow_up_exe.setVisibility(View.VISIBLE);

        img_executive_ad.setImageResource(R.drawable.radio_button_selected);
    }

    private void hideNormalAdsSection() {

        img_select_normal.setImageResource(R.drawable.radio_button_unselected);
        img_regular_ad.setImageResource(R.drawable.radio_button_unselected);
        img_regular_add.setImageResource(R.drawable.radio_button_unselected);
        img_rupgrad_ad.setImageResource(R.drawable.radio_button_unselected);
        LL_normal_ads_details.setVisibility(View.GONE);
        vw_dummy.setVisibility(View.GONE);

        img_arrow_down.setVisibility(View.VISIBLE);
        img_arrow_up.setVisibility(View.GONE);
    }

    private void showNormalAdsSection() {
        LL_executive_blog.setVisibility(View.GONE);
        LL_executive_blog.setVisibility(View.GONE);
        LL_Top_news.setVisibility(View.GONE);

        img_arrow_down.setVisibility(View.GONE);
        img_arrow_up.setVisibility(View.VISIBLE);
        vw_dummy.setVisibility(View.VISIBLE);
        LL_normal_ads_details.setVisibility(View.VISIBLE);
        img_select_normal.setImageResource(R.drawable.radio_button_selected);
        img_executive.setImageResource(R.drawable.radio_button_unselected);
        img_Blog.setImageResource(R.drawable.radio_button_unselected);
        img_Top_news.setImageResource(R.drawable.radio_button_unselected);
        img_regular_ad.setImageResource(R.drawable.radio_button_selected);
        img_regular_add.setImageResource(R.drawable.radio_button_unselected);
        img_rupgrad_ad.setImageResource(R.drawable.radio_button_unselected);

    }

    private void hideExecutiveSection() {

        img_arrow_down_exe.setVisibility(View.VISIBLE);
        img_arrow_up_exe.setVisibility(View.GONE);

        LL_executive_ads.setVisibility(View.GONE);
        img_executive_ad.setImageResource(R.drawable.radio_button_unselected);

    }

    private void FindView() {

        progressbar=(ProgressBar) findViewById(R.id.progressbar);
        txtTop_news_month_ads=(TextView) findViewById(R.id.txtTop_news_month_ads);
        txt_Top_news_price_ads=(TextView) findViewById(R.id.txt_Top_news_price_ads);
        txt_view_Top_news_ad_message_ads=(TextView) findViewById(R.id.txt_view_Top_news_ad_message_ads);

        txt_executive_message=(TextView) findViewById(R.id.txt_executive_message);
        txt_executive_month=(TextView) findViewById(R.id.txt_executive_month);
        txt_executive_price=(TextView) findViewById(R.id.txt_executive_price);

        txt_blog_month_ads=(TextView) findViewById(R.id.txt_blog_month_ads);
        txt_blog_price_ads=(TextView) findViewById(R.id.txt_blog_price_ads);
        txt_blog_message_ads=(TextView) findViewById(R.id.txt_blog_message_ads);

        RR_normal=(RelativeLayout) findViewById(R.id.RR_normal);
        RR_executive=(RelativeLayout) findViewById(R.id.RR_executive);
        LL_normal=(LinearLayout) findViewById(R.id.LL_normal);
        card_normal_ads=(CardView) findViewById(R.id.card_normal_ads);
        card_executive_ads=(CardView) findViewById(R.id.card_executive_ads);
        card_blog_ads=(CardView) findViewById(R.id.card_blog_ads);
        card_topNews_ads=(CardView) findViewById(R.id.card_topNews_ads_ads);
        back_layout=(RelativeLayout) findViewById(R.id.back_layout);

        RR_regular_ad=(RelativeLayout) findViewById(R.id.RR_regular_ad);
        RR_regular_add=(RelativeLayout) findViewById(R.id.RR_regular_add);
        RR_upgrad_ad=(RelativeLayout) findViewById(R.id.RR_upgrad_ad);
        RR_executive_month=(RelativeLayout) findViewById(R.id.RR_executive_month);

        RR_one=(RelativeLayout) findViewById(R.id.RR_one);
        RR_right=(RelativeLayout) findViewById(R.id.RR_right);
        RR_center=(RelativeLayout) findViewById(R.id.RR_center);

        img_select_normal=(ImageView) findViewById(R.id.img_select_normal);
        img_executive=(ImageView) findViewById(R.id.img_executive);
        img_Blog=(ImageView) findViewById(R.id.img_Blog);
        img_Top_news=(ImageView) findViewById(R.id.img_Top_news);
        img_regular_ad=(ImageView) findViewById(R.id.img_regular_ad);
        img_regular_add=(ImageView) findViewById(R.id.img_regular_add);
        img_rupgrad_ad=(ImageView) findViewById(R.id.img_rupgrad_ad);
        img_executive_ad=(ImageView) findViewById(R.id.img_executive_ad);

        LL_normal_ads_details=(LinearLayout) findViewById(R.id.LL_normal_ads_details);
        LL_executive_blog=(LinearLayout) findViewById(R.id.LL_executive_blog);
        LL_Top_news=(LinearLayout) findViewById(R.id.LL_Top_news);
        LL_executive_ads=(LinearLayout) findViewById(R.id.LL_executive_ads);
        proceed_btn=(Button) findViewById(R.id.proceed_btn);

        // Regular
        txtNormalAds=(TextView) findViewById(R.id.txtNormalAds);
        txt_regularAd=(TextView) findViewById(R.id.txt_regularAd);
        txt_regularAd_months=(TextView) findViewById(R.id.txt_regularAd_months);
        txt_regular_amount=(TextView) findViewById(R.id.txt_regular_amount);

        // Regular
        txt_regularAdd =(TextView) findViewById(R.id.txt_regularAdd);
        txt_regularAdd_months =(TextView) findViewById(R.id.txt_regularAdd_months);
        txt_regular_ads_amount =(TextView) findViewById(R.id.txt_regular_ads_amount);

        // upgrade
        txt_upgraderAd =(TextView) findViewById(R.id.txt_upgraderAd);
        txt_upgraderAd_months =(TextView) findViewById(R.id.txt_upgraderAd_months);
        txt_upgrade_amount =(TextView) findViewById(R.id.txt_upgrade_amount);


    }


    public void createAdsMethod(){

        HashMap<String, String> map = new HashMap<>();

        ApiRequest apiRequest = new ApiRequest(CreateAddActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_GET_PACKAGE_DETAIL, Constants.USER_GET_PACKAGE_DETAIL,map, Request.Method.GET);

    }
    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_GET_PACKAGE_DETAIL.equalsIgnoreCase(tag_json_obj)){

            progressbar.setVisibility(View.GONE);

            if (!response.equalsIgnoreCase(null)) {

                finalArray = new Gson().fromJson(response,new TypeToken<CreateAdsDataModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (finalArray.getErrorCode()== 2 && finalArray.getMessage().equalsIgnoreCase("Please Insert Valid Access Token"))
                {
                    Preference.clearPreference(this);
                    Intent intent=new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else  if (status.equalsIgnoreCase("0")){


                    price_executiveAds=finalArray.getData().get(1).getPackagePrice().toString();
                    String month_executiveAds=finalArray.getData().get(1).getAdsValidity().toString();
                    String month_executiveAdsCurrency=finalArray.getData().get(1).getCurrencyCode().toString();

                    price_BlogAds=finalArray.getData().get(2).getPackagePrice().toString();
                    String month_BloAds=finalArray.getData().get(2).getAdsValidity().toString();
                    String month_BloAdsCurrency=finalArray.getData().get(2).getCurrencyCode().toString();



                    price_TopNewsAds=finalArray.getData().get(3).getPackagePrice().toString();
                    String month_TopNewsAds=finalArray.getData().get(3).getAdsValidity().toString();
                    String month_TopNewsAdsCurrency=finalArray.getData().get(3).getCurrencyCode().toString();




                    Craete_Month_validy=finalArray.getData().get(4).getCurrencyCode().toString();
                    Craete_second_Month_validy=finalArray.getData().get(5).getCurrencyCode().toString();

                    txt_executive_price.setText(price_executiveAds+" "+month_executiveAdsCurrency);
                    txt_executive_month.setText(month_executiveAds);

                    txt_blog_price_ads.setText(price_BlogAds+" "+month_BloAdsCurrency);
                    txt_blog_month_ads.setText(month_BloAds);

                    txt_Top_news_price_ads.setText(price_TopNewsAds+" "+month_TopNewsAdsCurrency);
                    txtTop_news_month_ads.setText(month_TopNewsAds);
                    //txt_executive_message.setText("");

                    // Regular 1 month
                    // txtNormalAds,txt_regularAd,txt_regularAd_months,txt_regular_amount;



// Regular 3 month
                    //TextView txt_regularAdd,txt_regularAdd_months,txt_regular_ads_amount;

                   /* txt_regularAdd.setText(finalArray.getData().get(0).getPackageType());
                    txt_regularAdd_months.setText(finalArray.getData().get(1).getPackageType());
                    txt_regular_ads_amount.setText(finalArray.getData().get(2).getPackageType());*/



// upgrade 1 month
                    // TextView txt_upgraderAd,txt_upgraderAd_months,txt_upgrade_amount;


                    /*txt_upgraderAd.setText(finalArray.getData().get(0).getPackageType());
                    txt_upgraderAd_months.setText(finalArray.getData().get(1).getPackageType());
                    txt_upgrade_amount.setText(finalArray.getData().get(2).getPackageType());
*/

                    AdsList= (ArrayList<CreateAdsDataList>) finalArray.getData();

                    //Toast.makeText(this, ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
                else {

                    //
                    // Toast.makeText(this,finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        progressbar.setVisibility(View.GONE);

    }
}
