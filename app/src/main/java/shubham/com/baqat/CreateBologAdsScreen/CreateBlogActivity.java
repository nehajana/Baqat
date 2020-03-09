package shubham.com.baqat.CreateBologAdsScreen;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import shubham.com.baqat.CreateAddScreen.CreateAddActivity;
import shubham.com.baqat.CreateExecutivAddScren.CreateExecutiveAdsActivity;
import shubham.com.baqat.CreateExecutivAddScren.createExecutiveModel;
import shubham.com.baqat.CreateNormalAddScreen.CreateNormalAddActivity;
import shubham.com.baqat.HomeBottomScreen.HomeBottomActivity;
import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.MainActivity;
import shubham.com.baqat.PaymentScreen.PaypalActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
import shubham.com.baqat.UserProfile.UserProfileActivity;
import shubham.com.baqat.UserProfile.VolleyMultipartRequest;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;

public class CreateBlogActivity extends AppCompatActivity  implements IApiResponse {

    RelativeLayout back_layout;
    TextView text_career;
    EditText ed_name,ed_webLink;
    TextView frm_edt_date;
    TextView to_edt_date;
    Button submit_btn;
    Calendar dateSelected = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;
    private int mYear, mMonth,mDay;
    String frmdate="";
    String Todate="";
    RelativeLayout RR_upload_image;
    private static final int REQUEST_PERMISSIONS = 100;
    Bitmap bitmap;
    TextView txt_upload;
    boolean isSelectedImage = false;
    CheckBox checkbox;

    ProgressBar progressbar;
    private RecyclerView recycler_view_upload_image_blog;
    private UploadImageBlogRecyclerViewAdapter mAdapter;
    private ArrayList<UploadImageBlogAbstractModel> modelList = new ArrayList<>();

    String Imgurl="";
    ImageView img_user_updated;
    TextView txt_price;
    TextView txt_month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_blog);

        recycler_view_upload_image_blog = (RecyclerView) findViewById(R.id.recycler_view_upload_image_blog);
        text_career=(TextView) findViewById(R.id.text_career);
        ed_name=(EditText) findViewById(R.id.ed_name);
        ed_webLink=(EditText) findViewById(R.id.ed_webLink);
        frm_edt_date=(TextView) findViewById(R.id.frm_edt_date);
        to_edt_date=(TextView) findViewById(R.id.to_edt_date);
        submit_btn=(Button) findViewById(R.id.submit_btn);
        RR_upload_image=(RelativeLayout) findViewById(R.id.RR_upload_image);
        txt_upload=(TextView) findViewById(R.id.txt_upload);
        checkbox=(CheckBox) findViewById(R.id.checkbox);
        progressbar=(ProgressBar) findViewById(R.id.progressbar);
        img_user_updated=(ImageView) findViewById(R.id.img_user_updated);
        txt_price=(TextView) findViewById(R.id.txt_price);
        txt_month=(TextView) findViewById(R.id.txt_month);

        text_career.setVisibility(View.VISIBLE);
        text_career.setText("Create Ads");


        back_layout=(RelativeLayout) findViewById(R.id.back_layout);

        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        String month = Preference.get(CreateBlogActivity.this,Preference.key_valid_ads);

        String price = Preference.get(CreateBlogActivity.this,Preference.key_price_TopNewsAds);

        txt_price.setText(price+" AED");
        txt_month.setText(month+" Days");


        RR_upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    if ((ActivityCompat.shouldShowRequestPermissionRationale(CreateBlogActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(CreateBlogActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE))) {

                    } else {
                        ActivityCompat.requestPermissions(CreateBlogActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_PERMISSIONS);
                    }
                }else {

                    choosePhotoFromGallary();
                }


            }
        });

        frm_edt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog    datePickerDialog = new DatePickerDialog(CreateBlogActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                //  RR_booking_Date.setVisibility( View.VISIBLE );
                                //  txt_time.setVisibility(View.VISIBLE);
                                view.setVisibility(View.VISIBLE);
                                frmdate = (dayOfMonth+"-"+(monthOfYear+1)+"-"+year);
                                frm_edt_date.setText(frmdate);

                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                datePickerDialog.show();


            }
        });

        to_edt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog    datePickerDialog = new DatePickerDialog(CreateBlogActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                //  RR_booking_Date.setVisibility( View.VISIBLE );
                                //  txt_time.setVisibility(View.VISIBLE);
                                view.setVisibility(View.VISIBLE);
                                Todate = (dayOfMonth+"-"+(monthOfYear+1)+"-"+year);
                                to_edt_date.setText(Todate);

                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                datePickerDialog.show();

            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              JSONArray objPastMedi = new JSONArray();

                String title=ed_name.getText().toString();
                String web_link=ed_webLink.getText().toString();

                if(title.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateBlogActivity.this, "Please Enter Title", Toast.LENGTH_SHORT).show();

                }else  if(!isSelectedImage)
                {
                    Toast.makeText(CreateBlogActivity.this, "Please Upload Image", Toast.LENGTH_SHORT).show();

                }else if(frmdate.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateBlogActivity.this, "Please Slected From Date", Toast.LENGTH_SHORT).show();

                }else if(Todate.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateBlogActivity.this, "Please Slected To Date", Toast.LENGTH_SHORT).show();

                }else if(!checkbox.isChecked())
                {
                    Toast.makeText(CreateBlogActivity.this, "Please Selected Terms Condition", Toast.LENGTH_SHORT).show();

                }else
                {
                  /*  JSONObject objMain = null;
                    for (int j = 0; j < modelList.size(); j++) {
                        String imgURl = modelList.get(j).getImgURl().toString();
                        try {
                            objMain = new JSONObject();
                            objMain.put("media_path", imgURl);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        objPastMedi.put(objMain);
                    }

                    mediaPAth = objPastMedi.toString();*/


                    CreateBlogAddSection(title,web_link,bitmap,frmdate,Todate,"3",Imgurl);

                }
            }
        });

        setAdapter();
    }


    private void setAdapter() {
       // modelList.add(new UploadImageBlogAbstractModel("https:\\/\\/baqat4u.theoneinfotech.com\\/\\/uploads\\/users\\/listing\\/1580214562_1056677455.png"));
        mAdapter = new UploadImageBlogRecyclerViewAdapter(CreateBlogActivity.this, modelList);

        recycler_view_upload_image_blog.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view_upload_image_blog.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
       // recycler_view_upload_image_blog.setLayoutManager(layoutManager);
        recycler_view_upload_image_blog.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new UploadImageBlogRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, UploadImageBlogAbstractModel model) {
                //handle item click events here
              //  Toast.makeText(CreateBlogActivity.this, "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                //    takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {

        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 1);
    }

    private void takePhotoFromCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(cameraIntent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {


                    Uri selectedImage = data.getData();

                    bitmap = (Bitmap) data.getExtras().get("data");

                    //AddMultiPleImageMethod(bitmap);

                    progressbar.setVisibility(View.VISIBLE);

                    UpdateBlogImageUpload(bitmap);

                    File f = new File(String.valueOf(selectedImage));
                    String imageName = f.getName();

                   // txt_upload.setText(imageName);

                    isSelectedImage =true;

                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {

                    Uri selectedImage = data.getData();

                    try {

                        progressbar.setVisibility(View.VISIBLE);

                        //   isImage=true;
                        bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), selectedImage);
                        // ImageUrl = getFileDataFromDrawable1(bitmap);
                        //    ImageUrl=getPath(selectedImage);
                        //   user_profile.setImageBitmap(bitmap);

                        UpdateBlogImageUpload(bitmap);

                        File f = new File(String.valueOf(selectedImage));
                        String imageName = f.getName();

                    //    txt_upload.setText(imageName);

                        isSelectedImage =true;

                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e); }

                }
        }
    }


    public void CreateBlogAddSection(String title, String web_link, Bitmap bitmap, String frmdate, String Todate,String package_id,String mediaPAth){

        String user_id=  Preference.get(CreateBlogActivity.this,Preference.KEY_USER_ID);

        String access_token = Preference.get(CreateBlogActivity.this,Preference.KEY_accessToken);

        HashMap<String, String> map = new HashMap<>();

        map.put("access_token",access_token);
        map.put( "logged_in_user_id",user_id);
        map.put("package_id" , package_id);
        map.put("is_mobile","1");
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
        map.put("available_from" ,frmdate);
        map.put("available_to" ,Todate);
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
        map.put("media",mediaPAth);

        ApiRequest apiRequest = new ApiRequest(CreateBlogActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_Submitads, Constants.USER_Submitads,map, Request.Method.POST);

    }




    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_Submitads.equalsIgnoreCase(tag_json_obj)) {

            if (!response.equalsIgnoreCase(null)) {

                createExecutiveModel finalArray = new Gson().fromJson(response, new TypeToken<createExecutiveModel>() {
                }.getType());

                String status = String.valueOf(finalArray.getErrorCode());

                if (finalArray.getErrorCode()== 2 && finalArray.getMessage().equalsIgnoreCase("Please Insert Valid Access Token"))
                {
                    Preference.clearPreference(this);
                    Intent intent=new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else   if (status.equalsIgnoreCase("0")) {

                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CreateBlogActivity.this, PaypalActivity.class);
                    startActivity(intent);

                }
            }
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "Please Check Network", Toast.LENGTH_SHORT).show();
    }


    public void UpdateBlogImageUpload(final Bitmap bitmap){
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, Constants.BASE_URL+Constants.USER_addImages,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {

                            JSONObject obj = new JSONObject(new String(response.data));

                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                            String status = String.valueOf(obj.getString("errorCode"));

                            if (status.equalsIgnoreCase("0")) {

                                progressbar.setVisibility(View.GONE);

                                JSONObject jsonObject=obj.getJSONObject("data");

                                Imgurl = jsonObject.getString("url");

                                img_user_updated.setVisibility(View.VISIBLE);

                                Picasso.with(CreateBlogActivity.this).load(Imgurl)
                                        .into(img_user_updated);

                              /*  modelList.add(new UploadImageBlogAbstractModel(Imgurl));
                                mAdapter.updateList(modelList);*/

                                Toast.makeText(CreateBlogActivity.this, "Success", Toast.LENGTH_SHORT).show();

                                /*progressbar.setVisibility(View.GONE);

                                Intent intent=new Intent(UserProfileActivity.this, HomeBottomActivity.class);
                                startActivity(intent)*/;

                              /*  String user_Name=obj.getData.get(0).getFirstName();

                                Preference.save(UserProfileActivity.this,Preference.key_UserName,user_Name);

                                Toast.makeText(this, "" + obj.getMessage(), Toast.LENGTH_SHORT).show();*/

                            } else {

                                progressbar.setVisibility(View.GONE);

                                Toast.makeText(CreateBlogActivity.this, "UnSuccess", Toast.LENGTH_SHORT).show();

                                // Toast.makeText(this, obj.getMessage(), Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("GotError",""+error.getMessage());
                    }
                }) {


            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                if(bitmap != null && !bitmap.equals("")) {
                    params.put("image", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                }
                return params;
            }


            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();
                String user_id=   Preference.get(CreateBlogActivity.this,Preference.KEY_USER_ID);

                String access_token =   Preference.get(CreateBlogActivity.this,Preference.KEY_accessToken);

                map.put("access_token",access_token);
                map.put("is_mobile","1");

                return map;
            }


        };

        //adding the request to volley
        volleyMultipartRequest.setShouldCache(false);
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }


    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }


}
