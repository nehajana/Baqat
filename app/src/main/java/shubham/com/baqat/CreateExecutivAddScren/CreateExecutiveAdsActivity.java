package shubham.com.baqat.CreateExecutivAddScren;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import shubham.com.baqat.CreateAddScreen.CreateAddActivity;
import shubham.com.baqat.CreateBologAdsScreen.CreateBlogActivity;
import shubham.com.baqat.CreateTopNewsScreen.CreateTopNewsAds;
import shubham.com.baqat.LoginSceen.ApiModel.LoginModel;
import shubham.com.baqat.PaymentScreen.PaypalActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;

public class CreateExecutiveAdsActivity extends AppCompatActivity  implements IApiResponse {
    RelativeLayout back_layout;
    TextView text_career;
    EditText ed_title,ed_deatils,ed_webLink;
    Button submit_btn_executiveAdd;
    CheckBox checkbox_executive;

    TextView txt_amt_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_executive_ads);

        txt_amt_number=(TextView) findViewById(R.id.txt_amt_number);

        checkbox_executive=(CheckBox) findViewById(R.id.checkbox_executive);
        ed_title=(EditText) findViewById(R.id.ed_title);
        ed_deatils=(EditText) findViewById(R.id.ed_deatils);
        ed_webLink=(EditText) findViewById(R.id.ed_webLink);
        text_career=(TextView) findViewById(R.id.text_career);
        submit_btn_executiveAdd=(Button) findViewById(R.id.submit_btn);
        text_career.setVisibility(View.VISIBLE);
        text_career.setText("Create Ads");

        back_layout=(RelativeLayout) findViewById(R.id.back_layout);

        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        String monthValid = Preference.get(CreateExecutiveAdsActivity.this,Preference.key_valid_ads);

        String Price =  Preference.get(CreateExecutiveAdsActivity.this,Preference.key_price_TopNewsAds);

        txt_amt_number.setText(Price+" AED");

        submit_btn_executiveAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String title=ed_title.getText().toString();
                String details=ed_deatils.getText().toString();
                String web_link=ed_webLink.getText().toString();

                if(title.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateExecutiveAdsActivity.this, "Please Enter Title", Toast.LENGTH_SHORT).show();

                }else if(details.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateExecutiveAdsActivity.this, "Please Enter details", Toast.LENGTH_SHORT).show();

                }else if(!checkbox_executive.isChecked())
                {
                    Toast.makeText(CreateExecutiveAdsActivity.this, "Please Selected Terms Condition", Toast.LENGTH_SHORT).show();

                }else
                {
                    CreateExecutiveAddSection(title,details,web_link,"2");

                    /*Intent intent=new Intent(CreateExecutiveAdsActivity.this,PaypalActivity.class);
                    startActivity(intent);*/
                }
            }
        });
    }

    public void CreateExecutiveAddSection(String title, String details, String web_link,String package_id){

        String user_id= Preference.get(CreateExecutiveAdsActivity.this,Preference.KEY_USER_ID);

        String access_token = Preference.get(CreateExecutiveAdsActivity.this,Preference.KEY_accessToken);

        HashMap<String, String> map = new HashMap<>();

        map.put("access_token",access_token);
        map.put("is_mobile","1");
        map.put( "logged_in_user_id",user_id);
        map.put("package_id" , package_id);
        map.put( "purchase_date", "0");
        map.put("package_expiry", "0");
        map.put("package_price" ,  "0");
        map.put( "listing_title" , title);
        map.put("listing_content","0");
        map.put("listing_expiration_date" ,"0");
        map.put("listing_price", "0");
        map.put("specifics", "0");
        map.put( "email", "0");
        map.put( "contact_number" ,"0");
        map.put("company_name","0");
        map.put( "website", web_link);
        map.put("location","0");
        map.put("listing_status","0");
        map.put("offer_details","0");

        map.put( "offer_valid_start","0");
        map.put( "offer_valid_end","0");
        map.put("condition","0");
        map.put("type","0");
        map.put("usage","0");
        map.put("color","0");
        map.put( "transmission_type","0");
        map.put("region_specific","0");
        map.put("offer","0");
        map.put("model","0");
        map.put("kilometers","0");
        map.put("no_of_cylinder" ,"0");
        map.put("horsepower","0");
        map.put( "warranty","0");
        map.put("rent_recurrence_payment","0");
        map.put("bedroom","0");
        map.put("bathroom","0");
        map.put("developers","0");
        map.put("current_status","0");
        map.put("available_from" ,"0");
        map.put("available_to" ,"0");
        map.put( "size","0");
        map.put("gender","0");
        map.put("nationality","0");
        map.put( "position","0");
        map.put( "expected_start_date","0");
        map.put( "degree","0");
        map.put( "monthly_salary","0");
        map.put( "benifits","0");
        map.put( "current_position","0");
        map.put("current_company","0");
        map.put("notice_period","0");
        map.put("expected_monthly_salary","0");
        map.put("resume_file_path","0");
        map.put("category","0");
        map.put("keyword","0");
        map.put("media","0");

        System.out.println("map = : "+map);

        ApiRequest apiRequest = new ApiRequest(CreateExecutiveAdsActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_Submitads, Constants.USER_Submitads,map, Request.Method.POST);

    }

    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_Submitads.equalsIgnoreCase(tag_json_obj)) {

            if (!response.equalsIgnoreCase(null)) {

                System.out.println("Submit Ads Executive = : "+response);

                createExecutiveModel finalArray = new Gson().fromJson(response, new TypeToken<createExecutiveModel>() {
                }.getType());

                String status = String.valueOf(finalArray.getErrorCode());

                if (status.equalsIgnoreCase("0")) {

                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CreateExecutiveAdsActivity.this, PaypalActivity.class);
                    startActivity(intent);

                }
                else
                {

                }
            }
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "Please Check Network..", Toast.LENGTH_SHORT).show();
    }
}
