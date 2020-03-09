package shubham.com.baqat.BussinessPromoterScreen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import shubham.com.baqat.CareerScreen.ApiModel.CareerDataList;
import shubham.com.baqat.CareerScreen.ApiModel.CareerDataModel;
import shubham.com.baqat.CareerScreen.BussinessCareerActivity;
import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;

public class BussinessPromoterActivity extends AppCompatActivity  implements IApiResponse{

    RelativeLayout back_layout;
    TextView text_career;
    Button apply_job;

    String jobTitle="";
    String jobDescription="";
    String jobId="";
    TextView txt_job_description;
    ProgressBar progressbar;
    EditText ed_fname;
    EditText ed_Lastname;
    EditText ed_email;
    EditText ed_mobile;
    RelativeLayout RR_upload_File;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bussiness_promoter);

        findview();


        text_career.setVisibility(View.VISIBLE);
        text_career.setText("Business Promoter");

        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent intent=getIntent();

        if(intent !=null)
        {
             jobTitle=intent.getStringExtra("jobTitile").toString();
             jobDescription=intent.getStringExtra("jobDescription").toString();
             jobId=intent.getStringExtra("jobId").toString();

        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            txt_job_description.setText(Html.fromHtml(jobDescription, Html.FROM_HTML_MODE_COMPACT));

        } else {

            txt_job_description.setText(Html.fromHtml(jobDescription));
        }

        apply_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Fname=ed_fname.getText().toString();
                String Lastname=ed_Lastname.getText().toString();
                String email=ed_email.getText().toString();
                String mobile=ed_mobile.getText().toString();

                if(Fname.equalsIgnoreCase(""))
                {
                    Toast.makeText(BussinessPromoterActivity.this, "Please Enter Your First Name", Toast.LENGTH_SHORT).show();

                }else  if(Lastname.equalsIgnoreCase(""))
                {
                    Toast.makeText(BussinessPromoterActivity.this, "Please Enter Your Last Name", Toast.LENGTH_SHORT).show();


                }else  if(email.equalsIgnoreCase(""))
                {
                    Toast.makeText(BussinessPromoterActivity.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();


                }else  if(mobile.equalsIgnoreCase(""))
                {
                    Toast.makeText(BussinessPromoterActivity.this, "Please Enter Your Mobile Number", Toast.LENGTH_SHORT).show();

                }else
                {
                      aPPLIJOBMethod(jobId,Fname,Lastname,email,mobile,"vhvhv",".txt");
                }
            }
        });

        RR_upload_File.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Files.getContentUri("pdf"));
                startActivityForResult(pickPhoto, 1);*/


            }
        });



}

    private void findview() {

        back_layout = (RelativeLayout) findViewById(R.id.back_layout);
        text_career = (TextView) findViewById(R.id.text_career);
        apply_job = (Button) findViewById(R.id.apply_job);
        txt_job_description = (TextView) findViewById(R.id.txt_job_description);
        ed_fname = (EditText) findViewById(R.id.ed_fname);
        ed_Lastname = (EditText) findViewById(R.id.ed_Lastname);
        ed_email = (EditText) findViewById(R.id.ed_email);
        ed_mobile = (EditText) findViewById(R.id.ed_mobile);
        RR_upload_File = (RelativeLayout) findViewById(R.id.RR_upload_File);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedFileURI = data.getData();
                File file = new File(selectedFileURI.getPath().toString());
                Log.d("", "File : " + file.getName());
            String  uploadedFileName = file.getName().toString();
                StringTokenizer     tokens = new StringTokenizer(uploadedFileName, ":");
               String first = tokens.nextToken();
              String  file_1 = tokens.nextToken().trim();
               // txt_file_name_1.setText(file_1);
            }
        }
    }


    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    1);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void aPPLIJOBMethod(String job_id,String first_name,String last_name,String email,String phone_number,
                               String skill,String resume){
        progressbar.setVisibility(View.VISIBLE);
        HashMap<String, String> map = new HashMap<>();

        map.put("job_id",job_id);
        map.put("first_name",first_name);
        map.put("last_name",last_name);
        map.put("email",email);
        map.put("phone_number",phone_number);
        map.put("skill",skill);
        map.put("resume",resume);
        map.put("is_mobile","1");

        ApiRequest apiRequest = new ApiRequest(BussinessPromoterActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_APPLY_JOB, Constants.USER_APPLY_JOB,map, Request.Method.GET);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_APPLY_JOB.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                CareerDataModel finalArray = new Gson().fromJson(response,new TypeToken<CareerDataModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (finalArray.getErrorCode()== 2 && finalArray.getMessage().equalsIgnoreCase("Please Insert Valid Access Token"))
                {
                    Preference.clearPreference(this);

                    Intent intent=new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else  if (status.equalsIgnoreCase("0")){

                    Toast.makeText(this, ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
                else {

                    Toast.makeText(this,finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }

        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "Please Check Network", Toast.LENGTH_SHORT).show();
        progressbar.setVisibility(View.GONE);
    }

}
