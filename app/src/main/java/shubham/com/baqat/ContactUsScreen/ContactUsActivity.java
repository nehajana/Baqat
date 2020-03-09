package shubham.com.baqat.ContactUsScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import shubham.com.baqat.ContactUsScreen.Apimodel.ContactUsmodel;
import shubham.com.baqat.HomeBottomScreen.HomeBottomActivity;
import shubham.com.baqat.HomeSearchApi.Apimodel.HomeSearchModel;
import shubham.com.baqat.HomeSearchApi.Apimodel.HomedataSearchModel;
import shubham.com.baqat.HomeSearchApi.HomeSearch;
import shubham.com.baqat.R;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;

public class ContactUsActivity extends AppCompatActivity implements IApiResponse {

    RelativeLayout back_layout;
    TextView text_career;
    ProgressBar progressbar;
    EditText ed_name,ed_email,ed_mobile,ed_message;
    Button Send_message__btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        ed_name=(EditText) findViewById(R.id.ed_name);
        ed_email=(EditText) findViewById(R.id.ed_email);
        ed_mobile=(EditText) findViewById(R.id.ed_mobile);
        ed_message=(EditText) findViewById(R.id.ed_message);
        Send_message__btn=(Button) findViewById(R.id.Send_message__btn);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);


        text_career=(TextView) findViewById(R.id.text_career);
        back_layout=(RelativeLayout) findViewById(R.id.back_layout);

        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Send_message__btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=ed_name.getText().toString();
                String email=ed_email.getText().toString();
                String mobile=ed_mobile.getText().toString();
                String mesaage=ed_message.getText().toString();

                if(name.equalsIgnoreCase(""))
                {
                    Toast.makeText(ContactUsActivity.this, "Please Enter Your Name.", Toast.LENGTH_SHORT).show();

                }else if(!isValidEmail(email))
                {
                    Toast.makeText(ContactUsActivity.this, "Please Enter Valid Email.", Toast.LENGTH_SHORT).show();

                }else  if(mobile.equalsIgnoreCase(""))
                {
                    Toast.makeText(ContactUsActivity.this, "Please Enter Your Mobile.", Toast.LENGTH_SHORT).show();

                }else  if(mesaage.equalsIgnoreCase(""))
                {
                    Toast.makeText(ContactUsActivity.this, "Please Enter Your Message.", Toast.LENGTH_SHORT).show();

                }else
                {
                    ContactUsMethod(name,email,mobile,mesaage);
                }

            }
        });



    }





    public void ContactUsMethod(String email,String mobile_number,String full_name,String message){

        progressbar.setVisibility(View.VISIBLE);
        HashMap<String, String> map = new HashMap<>();

        map.put("email",email);
        map.put("mobile_number",mobile_number);
        map.put("full_name",full_name);
        map.put("message",message);

        ApiRequest apiRequest = new ApiRequest(ContactUsActivity.this,this);

        apiRequest.postRequest( Constants.BASE_URL + Constants.USER_contactUs, Constants.USER_contactUs,map, Request.Method.POST);
    }



    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_contactUs.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                ContactUsmodel finalArray = new Gson().fromJson(response,new TypeToken<ContactUsmodel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (status.equalsIgnoreCase("0")){

                    Toast.makeText(this, "SuccessFull", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(ContactUsActivity.this, HomeBottomActivity.class);
                    startActivity(intent);

                }
                else {

                }
            }

            progressbar.setVisibility(View.GONE);
        }

    }


    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "Please Enter Check..", Toast.LENGTH_SHORT).show();
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}
