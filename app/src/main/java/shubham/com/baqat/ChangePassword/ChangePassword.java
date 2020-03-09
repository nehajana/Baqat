package shubham.com.baqat.ChangePassword;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
import shubham.com.baqat.UserProfile.ApiModel.UserProfielModel;
import shubham.com.baqat.UserProfile.UserProfileActivity;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;

public class ChangePassword extends AppCompatActivity implements IApiResponse {

    TextInputEditText edt_oldPassword,edt_newPassword,edt_newCPassword;

    Button Save_btn;
    RelativeLayout RR_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        findView();


        Save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String OldPassword=edt_oldPassword.getText().toString();
                String NewPassword=edt_newPassword.getText().toString();
                String ConfirmNewPassword=edt_newCPassword.getText().toString();

                if(OldPassword.equalsIgnoreCase(""))
                {
                    Toast.makeText(ChangePassword.this, "Please Enter Old Password", Toast.LENGTH_SHORT).show();
                }else if (NewPassword.equalsIgnoreCase(""))
                {
                    Toast.makeText(ChangePassword.this, "Please Enter New Password", Toast.LENGTH_SHORT).show();

                }else if (!ConfirmNewPassword.equalsIgnoreCase(NewPassword))
                {
                    Toast.makeText(ChangePassword.this, "Don't Match password", Toast.LENGTH_SHORT).show();
                }else
                {
                    chamgeProfilepasswordMethod(OldPassword,NewPassword,ConfirmNewPassword);
                }
            }
        });

        RR_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

    }

    private void findView() {

        RR_back=(RelativeLayout) findViewById(R.id.RR_back);
        edt_oldPassword=(TextInputEditText) findViewById(R.id.edt_oldPassword);
        edt_newPassword=(TextInputEditText) findViewById(R.id.edt_newPassword);
        edt_newCPassword=(TextInputEditText) findViewById(R.id.edt_newCPassword);
        Save_btn=(Button) findViewById(R.id.Save_btn);

    }

    public void chamgeProfilepasswordMethod(String current_password,String new_password,String confirm_new_password){

        String user_id=   Preference.get(ChangePassword.this,Preference.KEY_USER_ID);

        HashMap<String, String> map = new HashMap<>();

        map.put("logged_in_user_id",user_id);
        map.put("current_password",current_password);
        map.put("new_password",new_password);
        map.put("confirm_new_password",confirm_new_password);
        map.put("is_mobile","1");

        ApiRequest apiRequest = new ApiRequest(this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_changepassword, Constants.USER_changepassword,map, Request.Method.POST);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_changepassword.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

               // progressbar.setVisibility(View.GONE);

                ChangeModel finalArray = new Gson().fromJson(response,new TypeToken<ChangeModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (finalArray.getErrorCode()== 2 && finalArray.getMessage().equalsIgnoreCase("Please Insert Valid Access Token"))
                {
                    Preference.clearPreference(this);
                    Intent intent=new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else   if (status.equalsIgnoreCase("0")){

                    Intent intent=new Intent(ChangePassword.this,UserProfileActivity.class);
                    startActivity(intent);

                    Toast.makeText(this, ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
                else {

                    Toast.makeText(this, finalArray.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "Please Check Network", Toast.LENGTH_SHORT).show();

    }
}
