package shubham.com.baqat.forgetActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;
import shubham.com.baqat.forgetActivity.Apimodel.forgetModel;
import shubham.com.baqat.forgetActivity.OtpForgetPassword.OtpForgetgetActvity;

public class ForgetActivity extends AppCompatActivity implements IApiResponse {

    EditText ed_email;
    Button btn_submit;
    String email="";

    ImageView img_back;
   private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        btn_submit=(Button) findViewById(R.id.btn_submit);
        img_back=(ImageView) findViewById(R.id.img_back);
        progressbar=(ProgressBar) findViewById(R.id.progressbar);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        ed_email=(EditText) findViewById(R.id.ed_email);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email=ed_email.getText().toString();

                if(email.equalsIgnoreCase(""))
                {
                    Toast.makeText(ForgetActivity.this, "Please Enter your email", Toast.LENGTH_SHORT).show();

                }else
                {
                    progressbar.setVisibility(View.VISIBLE);

                    forgetMethod(email);
                }
            }
        });

    }


    public void forgetMethod(String email){

        HashMap<String, String> map = new HashMap<>();

        map.put("email",email);
        map.put("is_mobile","1");

        ApiRequest apiRequest = new ApiRequest(this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_FORGETPASSWORD, Constants.USER_FORGETPASSWORD,map, Request.Method.POST);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_FORGETPASSWORD.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                progressbar.setVisibility(View.GONE);

                forgetModel finalArray = new Gson().fromJson(response,new TypeToken<forgetModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (status.equalsIgnoreCase("0")){

                    //Toast.makeText(this, ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                    Intent intent =new Intent(ForgetActivity.this,OtpForgetgetActvity.class)
                            .putExtra("email",email);
                    startActivity(intent);
                }
                else {

                    progressbar.setVisibility(View.GONE);

                    Toast.makeText(this, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "Please Check Network", Toast.LENGTH_SHORT).show();

        progressbar.setVisibility(View.GONE);

    }
}
