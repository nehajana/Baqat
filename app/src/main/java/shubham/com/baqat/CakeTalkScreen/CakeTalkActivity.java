package shubham.com.baqat.CakeTalkScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import shubham.com.baqat.CakeTalkScreen.ApiModel.CategoryDetailsModel;
import shubham.com.baqat.CreateBologAdsScreen.CreateBlogActivity;
import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.ProfileFragment.ProfileModel;
import shubham.com.baqat.R;
import shubham.com.baqat.RestaurantsCateringScreen.ApiModel.CateringDataList;
import shubham.com.baqat.RestaurantsCateringScreen.ApiModel.CateringDataModel;
import shubham.com.baqat.RestaurantsCateringScreen.RestaurentCateringAbstractModel;
import shubham.com.baqat.RestaurantsCateringScreen.RestaurentCateringActivity;
import shubham.com.baqat.RestaurantsCateringScreen.RestaurentCateringRecyclerViewAdapter;
import shubham.com.baqat.UserProfile.UserProfileActivity;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;

public class CakeTalkActivity extends AppCompatActivity implements IApiResponse {

    RecyclerView recycler_view_relatedListing;
    ImageView img_options;
    private RecyclerView recycler_view_restaurent;

    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;

    RelativeLayout RR_write_review_UP_down;
    RelativeLayout RR_write_review;
    ProgressBar progressbar;
    private Related_listening_catetalkRecyclerViewAdapter mAdapter;

    private ArrayList<RelatedListingAbstractModel> modelList = new ArrayList<>();
    boolean isShowWriteReview= false;
    CardView cars_write_review;

    ImageView img_cake;
    TextView user_name;
    TextView txt_rating;
    TextView txt_name;
    TextView txt_member_date;
    TextView txt_costomer_rivew;
    TextView txt_title;
    TextView txt_date;
    TextView txt_view;
    TextView txt_description_detain;
    TextView user_contact;
    TextView txt_webLink;
    TextView txt_city;
    CircleImageView img_profile;

    String Listing_id="";
    String Media_ThumbPath="";
    Button btn_sign_up;
    EditText edt_commet;
    RatingBar ratingBar;
    LinearLayout LL_rating;
    PopupMenu popup;

    RelativeLayout back_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_talk);

        findViews();

        Intent intent=getIntent();

        if(intent !=null)
        {
            Listing_id=intent.getStringExtra("listing_id").toString();
            Media_ThumbPath=intent.getStringExtra("Media_ThumbPath").toString();

            Picasso.with(CakeTalkActivity.this).load(Media_ThumbPath)
                    .placeholder(R.drawable.defaultprofile)
                    .into(img_cake);
        }

        img_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popup = new PopupMenu(CakeTalkActivity.this, v);
                popup.inflate(R.menu.menu_isfav);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id)
                        {
                            case R.id.id_share:
                                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                                sharingIntent.setType("text/plain");
                                String shareBody = "Your body here";
                                String shareSub = "Your subject here";
                                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                                startActivity(Intent.createChooser(sharingIntent, "Share using"));

                                break;

                            case R.id.id_favorite:

                                saveFavoriteMethod(Listing_id);

                                break;

                            case R.id.id_Restaurants:

                                break;
                        }
                        return true;
                    }
                });

                popup.show();
            }
        });

        RR_write_review_UP_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(isShowWriteReview) {

                    cars_write_review.setVisibility(View.GONE);

                    isShowWriteReview= false;
                }
                else
                {
                    cars_write_review.setVisibility(View.VISIBLE);

                    isShowWriteReview= true;

                }


            }
        });


        cars_write_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String comment = edt_commet.getText().toString();

                float rating = ratingBar.getRating();

                Toast.makeText(CakeTalkActivity.this, ""+rating, Toast.LENGTH_SHORT).show();

                if(comment.equalsIgnoreCase(""))
                {
                    Toast.makeText(CakeTalkActivity.this, "Please enter Comment", Toast.LENGTH_SHORT).show();

                }else
                {
                    SubmitReviewMethod(Listing_id,comment, String.valueOf(rating));

                }
            }
        });

        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        showDetailsAdsMethod(Listing_id);


    }


    private void  findViews() {

        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        back_layout = (RelativeLayout) findViewById(R.id.back_layout);

        btn_sign_up=(Button) findViewById(R.id.btn_sign_up);
        edt_commet=(EditText) findViewById(R.id.edt_commet);
        LL_rating=(LinearLayout) findViewById(R.id.LL_rating);

        recycler_view_relatedListing = (RecyclerView) findViewById(R.id.recycler_view_relatedListing);
        RR_write_review_UP_down = (RelativeLayout) findViewById(R.id.RR_write_review_UP_down);
        RR_write_review = (RelativeLayout) findViewById(R.id.RR_write_review);
        cars_write_review = (CardView) findViewById(R.id.cars_write_review);
        img_cake = (ImageView) findViewById(R.id.img_cake);
        user_name = (TextView) findViewById(R.id.user_name);
        txt_date = (TextView) findViewById(R.id.txt_date);
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_view = (TextView) findViewById(R.id.txt_view);
        txt_description_detain = (TextView) findViewById(R.id.txt_description_detain);
        user_contact = (TextView) findViewById(R.id.user_contact);
        txt_webLink = (TextView) findViewById(R.id.txt_webLink);
        txt_city = (TextView) findViewById(R.id.txt_city);
        txt_costomer_rivew = (TextView) findViewById(R.id.txt_costomer_rivew);
        txt_rating = (TextView) findViewById(R.id.txt_rating);
        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_member_date = (TextView) findViewById(R.id.txt_member_date);
        img_profile = (CircleImageView) findViewById(R.id.img_profile);
        img_options=(ImageView) findViewById(R.id.img_options);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
    }


    private void setAdapter(List<String> filePath) {

      //  modelList.add(new RelatedListingAbstractModel("Android", "Hello " + " Android"));

        mAdapter = new Related_listening_catetalkRecyclerViewAdapter(CakeTalkActivity.this, filePath);
        recycler_view_relatedListing.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);

        recycler_view_relatedListing.setLayoutManager(layoutManager);
        recycler_view_relatedListing.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new Related_listening_catetalkRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, RelatedListingAbstractModel model) {
                //handle item click events here
             //   Toast.makeText(CakeTalkActivity.this, "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void showDetailsAdsMethod(String listing_id){
        progressbar.setVisibility(View.VISIBLE);

        String user_id=   Preference.get(CakeTalkActivity.this,Preference.KEY_USER_ID);
        String access_token =   Preference.get(CakeTalkActivity.this,Preference.KEY_accessToken);

        HashMap<String, String> map = new HashMap<>();
        map.put("access_token",access_token);
        map.put("listing_id",listing_id);

        ApiRequest apiRequest = new ApiRequest(CakeTalkActivity.this,this);
        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_listingDetailAds, Constants.USER_listingDetailAds,map, Request.Method.POST);

    }

    public void SubmitReviewMethod(String listing_id,String comment,String rating){
        progressbar.setVisibility(View.VISIBLE);
        String user_id=   Preference.get(CakeTalkActivity.this,Preference.KEY_USER_ID);
        String access_token =   Preference.get(CakeTalkActivity.this,Preference.KEY_accessToken);

        HashMap<String, String> map = new HashMap<>();
        map.put("access_token",access_token);
        map.put("logged_in_user_id",user_id);
        map.put("comment",comment);
        map.put("rating",rating);
        map.put("listing_id",listing_id);

        ApiRequest apiRequest = new ApiRequest(CakeTalkActivity.this,this);
        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_submitReview, Constants.USER_submitReview,map, Request.Method.POST);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_listingDetailAds.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                CategoryDetailsModel finalArray = new Gson().fromJson(response,new TypeToken<CategoryDetailsModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (finalArray.getErrorCode()== 2 && finalArray.getMessage().equalsIgnoreCase("Please Insert Valid Access Token"))
                {
                    Preference.clearPreference(this);
                    Intent intent=new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else  if (status.equalsIgnoreCase("0")){

                     List<String> filePath=finalArray.getData().get(0).getMediaPath();

                     setAdapter(filePath);


                    user_name.setText(finalArray.getData().get(0).getListingTitle());
                    txt_date.setText(finalArray.getData().get(0).getListingExpirationDate());
                    txt_view.setText("Views :"+finalArray.getData().get(0).getListingViews());
                    txt_description_detain.setText(finalArray.getData().get(0).getListingContent());
                    user_contact.setText(finalArray.getData().get(0).getContactNumber());
                    txt_title.setText(finalArray.getData().get(0).getListingTitle());
                    txt_webLink.setText(finalArray.getData().get(0).getWebsite());
                    txt_costomer_rivew.setText("Costomer Review "+finalArray.getData().get(0).getTotalUserRating());
                    txt_rating.setText(finalArray.getData().get(0).getAvgRating()+"out of"+finalArray.getData().get(0).getTotalUserRating());
                    txt_name.setText(finalArray.getData().get(0).getFirstName()+" "+finalArray.getData().get(0).getLastName());
                    txt_member_date.setText(finalArray.getData().get(0).getListingExpirationDate());

                    Toast.makeText(this, ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                    String Profile_img=finalArray.getData().get(0).getProfilePicture().toString();

               /*     Picasso.with(CakeTalkActivity.this).load(Profile_img)
                            .placeholder(R.drawable.defaultprofile)
                            .into(img_profile);*/

                }
                else {

                    Toast.makeText(this,finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }


                progressbar.setVisibility(View.GONE);
            }
        }else if (Constants.USER_submitReview.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                ProfileModel finalArray = new Gson().fromJson(response,new TypeToken<ProfileModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (status.equalsIgnoreCase("0")){

                 Toast.makeText(CakeTalkActivity.this, ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
                else {

                    Toast.makeText(CakeTalkActivity.this, ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            progressbar.setVisibility(View.GONE);
        } else if (Constants.USER_addFavoriteAds.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

            /*    ProfileModel finalArray = new Gson().fromJson(response,new TypeToken<ProfileModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (status.equalsIgnoreCase("0")){

                 Toast.makeText(CakeTalkActivity.this, ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
                else {

                    Toast.makeText(CakeTalkActivity.this, ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();
                }*/
            }
            progressbar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "Please Check Network", Toast.LENGTH_SHORT).show();

    }



    public void saveFavoriteMethod(String listing_id){
        progressbar.setVisibility(View.VISIBLE);
        String user_id=   Preference.get(CakeTalkActivity.this,Preference.KEY_USER_ID);
        String access_token =   Preference.get(CakeTalkActivity.this,Preference.KEY_accessToken);

        HashMap<String, String> map = new HashMap<>();
        map.put("access_token",access_token);
        map.put("logged_in_user_id",user_id);
        map.put("listing_id",listing_id );
        map.put("add_fav","1" );

        ApiRequest apiRequest = new ApiRequest(CakeTalkActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_addFavoriteAds, Constants.USER_addFavoriteAds,map, Request.Method.POST);
    }

}
