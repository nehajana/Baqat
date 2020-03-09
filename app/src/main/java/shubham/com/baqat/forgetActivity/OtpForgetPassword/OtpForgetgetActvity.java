package shubham.com.baqat.forgetActivity.OtpForgetPassword;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.R;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;
import shubham.com.baqat.forgetActivity.Apimodel.forgetModel;
import shubham.com.baqat.forgetActivity.ForgetActivity;
import shubham.com.baqat.forgetActivity.ResetModel;

public class OtpForgetgetActvity extends AppCompatActivity implements IApiResponse {

    String email;
    TextView txt_account;
    TextInputEditText edt_NewPassword;
    TextInputEditText edtConfirmPassword;
    Button bttn_save;

    EditText edt_otp1,edt_otp2,edt_otp3,edt_otp4,edt_otp5,edt_otp6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_forgetget_actvity);

        findView();

        Intent intent=getIntent();

        if(intent !=null)
        {
              email=intent.getStringExtra("email").toString();

                txt_account.setText(email);
        }

        bttn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String NewPassword = edt_NewPassword.getText().toString().trim();
                String ConfirmPassword = edtConfirmPassword.getText().toString().trim();

                String str_otp_one=edt_otp1.getText().toString();
                String str_otp_Two=edt_otp2.getText().toString();
                String str_otp_Three=edt_otp3.getText().toString();
                String str_otp_Four=edt_otp4.getText().toString();
                String str_otp_Five=edt_otp5.getText().toString();
                String str_otp_six=edt_otp6.getText().toString();

                if(NewPassword.equalsIgnoreCase("")){
                    Toast.makeText(OtpForgetgetActvity.this, "Please enter new password.", Toast.LENGTH_SHORT).show();

                }else if(ConfirmPassword.equalsIgnoreCase("")){
                    Toast.makeText(OtpForgetgetActvity.this, "Please enter confirm password.", Toast.LENGTH_SHORT).show();


                }else{

                    String finalotp=str_otp_one+str_otp_Two+str_otp_Three+str_otp_Four+str_otp_Five+str_otp_six;

                    resetPasswordApi(finalotp,NewPassword,ConfirmPassword);
                }
            }
        });


        edt_otp1.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                if (s.length() ==1) {
                    edt_otp2.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {


                if (s.length() ==1) {
                    edt_otp1.requestFocus();
                }

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
        });

        edt_otp2.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    edt_otp3.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                if (s.length() ==1) {
                    edt_otp1.requestFocus();
                }

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }
        });
        edt_otp3.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    edt_otp4.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

                if (s.length() ==1) {
                    edt_otp2.requestFocus();
                }

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
        });

        edt_otp4.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    edt_otp5.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

                if (s.length() ==1) {
                    edt_otp2.requestFocus();
                }

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
        });

        edt_otp5.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    edt_otp6.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
        });


    }

    private void findView() {

        txt_account=(TextView) findViewById(R.id.txt_account);
        edt_NewPassword= (TextInputEditText) findViewById( R.id.password );
        edtConfirmPassword=(TextInputEditText) findViewById(R.id.edtConfirmPassword);
        bttn_save=(Button) findViewById(R.id.bttn_save);

        edt_otp1=(EditText) findViewById(R.id.edt_otp1);
        edt_otp2=(EditText) findViewById(R.id.edt_otp2);
        edt_otp3=(EditText) findViewById(R.id.edt_otp3);
        edt_otp4=(EditText) findViewById(R.id.edt_otp4);
        edt_otp5=(EditText) findViewById(R.id.edt_otp5);
        edt_otp6=(EditText) findViewById(R.id.edt_otp6);

    }


    public void resetPasswordApi( String reset_code,String newPassoword, String confirmPassword){

        HashMap<String, String> map = new HashMap<>();

        map.put("email",email);
        map.put("reset_code",reset_code);
        map.put("is_mobile","1");
        map.put("new_password",newPassoword);
        map.put("confirm_new_password",confirmPassword);

        ApiRequest apiRequest = new ApiRequest(this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_resetPassword, Constants.USER_resetPassword,map, Request.Method.POST);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_resetPassword.equalsIgnoreCase(tag_json_obj)){

            if (response !=null) {

                ResetModel finalArray = new Gson().fromJson(response,new TypeToken<ResetModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (status.equalsIgnoreCase("0")){

                    //Toast.makeText(this, ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                    Intent intent =new Intent(OtpForgetgetActvity.this,LoginActivity.class);
                    startActivity(intent);

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
