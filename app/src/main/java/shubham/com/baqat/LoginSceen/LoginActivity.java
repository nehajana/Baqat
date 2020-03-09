package shubham.com.baqat.LoginSceen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import shubham.com.baqat.HomeBottomScreen.HomeBottomActivity;
import shubham.com.baqat.LoginSceen.ApiModel.LoginModel;
import shubham.com.baqat.MainActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
import shubham.com.baqat.Singupscreen.SignUpActivity;
import shubham.com.baqat.UserProfile.UserProfileActivity;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;
import shubham.com.baqat.forgetActivity.ForgetActivity;

public class LoginActivity extends AppCompatActivity  implements IApiResponse {

    TextView txt_sing_up;
    TextView txt_forgetpassword;
    Button Login_btn;
    EditText userName_etxt;
    EditText edt_password;
    String email;
    String password;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        txt_sing_up=(TextView) findViewById(R.id.txt_sing_up);
        txt_forgetpassword=(TextView) findViewById(R.id.txt_forgetpassword);
        Login_btn=(Button) findViewById(R.id.Login_btn);
        edt_password=(EditText) findViewById(R.id.edt_password);
        userName_etxt=(EditText) findViewById(R.id.userName_etxt);
        progressbar=(ProgressBar) findViewById(R.id.progressbar);


        txt_sing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           valiDation();

            }
        });

        txt_forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LoginActivity.this, ForgetActivity.class);
                startActivity(intent);
            }
        });

    }



    private void valiDation() {

         email = userName_etxt.getText().toString();
         password = edt_password.getText().toString();

        if(!isValidEmail(email)){

            Toast.makeText(LoginActivity.this, "Please enter correct email address.", Toast.LENGTH_SHORT).show();

        }else if(password.equalsIgnoreCase("")){

            Toast.makeText(LoginActivity.this, "Please enter Password.", Toast.LENGTH_SHORT).show();

        }else{

            progressbar.setVisibility(View.VISIBLE);

            loginMethod(email,password);
        }
    }

    public static boolean isValidEmail(CharSequence target) {

        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void loginMethod(String email, String password){

        HashMap<String, String> map = new HashMap<>();

        map.put("email",email);
        map.put("password",password);
        map.put("is_mobile","1");

        ApiRequest apiRequest = new ApiRequest(LoginActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_LOGIN, Constants.USER_LOGIN,map, Request.Method.POST);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_LOGIN.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                System.out.println("Login Responce = : "+ response);

                LoginModel finalArray = new Gson().fromJson(response,new TypeToken<LoginModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (status.equalsIgnoreCase("0")){

                    Toast.makeText(this, ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                    progressbar.setVisibility(View.GONE);

                    String user_id=finalArray.getData().get(0).getUserId();

                    String user_Name=finalArray.getData().get(0).getFirstName();

                    String access_token=finalArray.getData().get(0).getAccessToken();

                    String profileImage=finalArray.getData().get(0).getProfilePicture();

                    Preference.save(LoginActivity.this,Preference.key_profileImage,profileImage);

                    Preference.save(LoginActivity.this,Preference.KEY_USER_ID,user_id);

                    Preference.save(LoginActivity.this,Preference.key_UserName,user_Name);

                    Preference.save(LoginActivity.this,Preference.KEY_accessToken,access_token);

                    Preference.save(LoginActivity.this,Preference.KEY_LoginType,"login");

                    Intent intent=new Intent(LoginActivity.this, HomeBottomActivity.class);
                    startActivity(intent);

                }
                else {

                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(this,finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        progressbar.setVisibility(View.GONE);

        Toast.makeText(this, "Please Check Network", Toast.LENGTH_SHORT).show();

    }

}
