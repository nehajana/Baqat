package shubham.com.baqat.UserProfile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.seatgeek.placesautocomplete.DetailsCallback;
import com.seatgeek.placesautocomplete.OnPlaceSelectedListener;
import com.seatgeek.placesautocomplete.PlacesAutocompleteTextView;
import com.seatgeek.placesautocomplete.model.AddressComponent;
import com.seatgeek.placesautocomplete.model.AddressComponentType;
import com.seatgeek.placesautocomplete.model.Place;
import com.seatgeek.placesautocomplete.model.PlaceDetails;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import shubham.com.baqat.ChangePassword.ChangePassword;
import shubham.com.baqat.HomeBottomScreen.HomeBottomActivity;
import shubham.com.baqat.HomeFragment.ApiModel.HomeAdsDataModel;
import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.MainActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.ProfileFragment.ProfileFragment;
import shubham.com.baqat.R;
import shubham.com.baqat.UserProfile.ApiModel.CountryDataModel;
import shubham.com.baqat.UserProfile.ApiModel.CountryModel;
import shubham.com.baqat.UserProfile.ApiModel.UpdateProfile;
import shubham.com.baqat.UserProfile.ApiModel.UserProfielModel;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;
import shubham.com.baqat.Volley.MultipartRequest;
import shubham.com.baqat.forgetActivity.ForgetActivity;
import shubham.com.baqat.forgetActivity.OtpForgetPassword.OtpForgetgetActvity;

import static shubham.com.baqat.UserProfile.UserProfileActivity.autocomplete;

public class UserProfileActivity extends AppCompatActivity implements IApiResponse,AdapterView.OnItemSelectedListener,AdapterView.OnItemClickListener{

    RelativeLayout RR_changePassword;
    ProgressBar progressbar;
    RelativeLayout back_layout;
    Button btn_save,btn_cancle;
    EditText ed_fname,ed_lname,ed_email,ed_mobile,ed_country;

    //Spinner Country Code
    Spinner spinner_country;
    Spinner spinner_location;
    String[] countryNames={"India","China","Australia","Portugle","America","New Zealand"};
    private ArrayList<CountryDataModel> modelList_CountryList = new ArrayList<>();
    private ArrayList<LocationDataModel> modelList_location = new ArrayList<>();
    String country_str;

    ImageView user_profile;
    RelativeLayout RR_profile;
    private static final int REQUEST_PERMISSIONS = 100;
    String ImageUrl="";
    Bitmap bitmap;
    String picturePath="";
    boolean isImage=false;
    String ZipCode_str="";
    String state_str="";
    String city_str="";
    String address;

    //Placeholder
    @BindView(R.id.autocomplete)
    PlacesAutocompleteTextView mAutocomplete;

    @BindView(R.id.street)
    TextView mStreet;

    @BindView(R.id.city)
    TextView mCity;

    @BindView(R.id.state)
    TextView mState;

    @BindView(R.id.zip)
    TextView mZip;

    TextView text_title;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA= 7;


    //Google Place Api  (Ram)
    private static final String LOG_TAG = "Google Places Autocomplete";
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";
    private static final String API_KEY = "AIzaSyB3_aRArcRZxO8Tz9nBWswQRMgR5HOKuGw";
    AutoCompleteTextView autoCompView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        findView();

        //Google Place Api code
        autoCompView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompView.setAdapter(new GooglePlacesAutocompleteAdapter(this, R.layout.list_item));
        autoCompView.setOnItemClickListener(this);


        text_title.setVisibility(View.VISIBLE);

        text_title.setText("MyProfile");

        ed_email.setEnabled(false);

        back_layout.setVisibility(View.VISIBLE);

        progressbar.setVisibility(View.VISIBLE);

        RR_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(UserProfileActivity.this,ChangePassword.class);
                startActivity(intent);
            }
        });

        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fName=ed_fname.getText().toString();
                String lName=ed_lname.getText().toString();
                String mobile=ed_mobile.getText().toString();
                String access_token =   Preference.get(UserProfileActivity.this,Preference.KEY_accessToken);

                if(mobile.equalsIgnoreCase(""))
                {
                    Toast.makeText(UserProfileActivity.this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();

                }else if(fName.equalsIgnoreCase(""))
                {
                    Toast.makeText(UserProfileActivity.this, "Please Enter First Name", Toast.LENGTH_SHORT).show();

                }else if(lName.equalsIgnoreCase(""))
                {

                    Toast.makeText(UserProfileActivity.this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();

                }else if(!isImage)
                {
                    Toast.makeText(UserProfileActivity.this, "Please insert Image", Toast.LENGTH_SHORT).show();

                }else
                {
                    progressbar.setVisibility(View.VISIBLE);

                    UpdateProfileMethod(fName,lName,mobile,bitmap);
                }


            }
        });

        RR_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    if ((ActivityCompat.shouldShowRequestPermissionRationale(UserProfileActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(UserProfileActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE))) {

                    } else {
                        ActivityCompat.requestPermissions(UserProfileActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_PERMISSIONS);
                    }
                }else  {

                    showPictureDialog();
                }

            }
        });

        String user_id=   Preference.get(UserProfileActivity.this,Preference.KEY_USER_ID);

        UserProfileMethod(user_id);

        //countryMethod();

        spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                String countryId=modelList_CountryList.get(position).getCountryId().toString();

                //LocationMethod(countryId);

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });


        ButterKnife.bind(this);

       mAutocomplete.setOnPlaceSelectedListener(new OnPlaceSelectedListener() {
            @Override
            public void onPlaceSelected(final Place place) {
                mAutocomplete.getDetailsFor(place, new DetailsCallback() {
                    @Override
                    public void onSuccess(final PlaceDetails details) {
                       String address1 = details.name;
                        Log.d("test", "details " + details);
                        mStreet.setText(details.name);
                        for (AddressComponent component : details.address_components) {
                            for (AddressComponentType type : component.types) {
                                switch (type) {
                                    case STREET_NUMBER:
                                        break;
                                    case ROUTE:
                                        break;
                                    case NEIGHBORHOOD:
                                        break;
                                    case SUBLOCALITY_LEVEL_1:
                                        break;
                                    case SUBLOCALITY:
                                        break;
                                    case LOCALITY:
                                        ZipCode_str=component.long_name.toString();
                                        mCity.setText(ZipCode_str);
                                        break;
                                    case ADMINISTRATIVE_AREA_LEVEL_1:
                                         state_str=component.short_name.toString();
                                        mState.setText(state_str);
                                        break;
                                    case ADMINISTRATIVE_AREA_LEVEL_2:
                                        break;
                                    case COUNTRY:
                                        break;
                                    case POSTAL_CODE:
                                        ZipCode_str=component.short_name.toString();
                                        mZip.setText(ZipCode_str);
                                        break;
                                    case POLITICAL:
                                        break;
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(final Throwable failure) {
                        Log.d("test", "failure " + failure);
                    }
                });
            }
        });


        // mAutocomplete.setCountries("AU", "NZ");

        //hideKeyboard(UserProfileActivity.this);
        hideDefaultKeyboard();
        ed_fname.requestFocus();
    }


    private void hideDefaultKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //you have got lot of methods here
    }

    private void findView() {

        RR_changePassword=(RelativeLayout) findViewById(R.id.RR_changePassword);
        ed_fname=(EditText) findViewById(R.id.ed_fname);
        ed_lname=(EditText) findViewById(R.id.ed_lname);
        ed_email=(EditText) findViewById(R.id.ed_email);
        ed_mobile=(EditText) findViewById(R.id.ed_mobile);
        progressbar=(ProgressBar) findViewById(R.id.progressbar);
        back_layout=(RelativeLayout) findViewById(R.id.back_layout);
        btn_save=(Button) findViewById(R.id.btn_save);
        btn_cancle=(Button) findViewById(R.id.btn_cancle);
        RR_profile=(RelativeLayout) findViewById(R.id.RR_profile);
        user_profile=(ImageView) findViewById(R.id.user_profile);
        text_title=(TextView) findViewById(R.id.text_title);
        //Spinner Country Code
        spinner_country =findViewById(R.id.spinner_country);

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
                                takePhotoFromCamera();
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

        if (ContextCompat.checkSelfPermission(UserProfileActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)
                    UserProfileActivity.this, Manifest.permission.CAMERA)) {


            } else {
                ActivityCompat.requestPermissions((Activity) UserProfileActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);
            }

        }else
        {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            if (cameraIntent.resolveActivity(getPackageManager()) != null)
                startActivityForResult(cameraIntent, 0);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {

                    bitmap = (Bitmap) data.getExtras().get("data");
                    // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                    Uri tempUri = getImageUri(getApplicationContext(), bitmap);

                    isImage=true;
                    // Uri selectedImage = data.getData();

                    //   ImageUrl = getFileDataFromDrawable1(photo);
                    ImageUrl=getPath(tempUri);

                    user_profile.setImageBitmap(bitmap);

                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {

                    Uri selectedImage = data.getData();

                    try {

                        isImage=true;

                        bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), selectedImage);
                        // ImageUrl = getFileDataFromDrawable1(bitmap);
                        ImageUrl=getPath(selectedImage);
                        user_profile.setImageBitmap(bitmap);

                    } catch (IOException e) {

                        Log.i("TAG", "Some exception " + e); }

                }
        }
    }


    /*public String getFileDataFromDrawable1(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] imaBytes =byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imaBytes,Base64.DEFAULT);
    }*/

    private String getPath(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(getApplicationContext(),    contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }



    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {

        // Toast.makeText(getApplicationContext(), countryNames[position], Toast.LENGTH_LONG).show();

        country_str=countryNames[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }




    public void UserProfileMethod(String user_id){

        String access_token =   Preference.get(UserProfileActivity.this,Preference.KEY_accessToken);

        HashMap<String, String> map = new HashMap<>();

        map.put("logged_in_user_id",user_id);

        map.put("access_token",access_token);

        map.put("is_mobile","1");

        ApiRequest apiRequest = new ApiRequest(this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_GETPROFILE, Constants.USER_GETPROFILE,map, Request.Method.POST);
    }

    public void countryMethod(){

        String access_token =   Preference.get(UserProfileActivity.this,Preference.KEY_accessToken);

        HashMap<String, String> map = new HashMap<>();

        ApiRequest apiRequest = new ApiRequest(this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_getCountry, Constants.USER_getCountry,map, Request.Method.GET);
    }

    public void LocationMethod(String country_id){

        HashMap<String, String> map = new HashMap<>();

        map.put("country_id",country_id);

        ApiRequest apiRequest = new ApiRequest(this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_getStateByCountryId, Constants.USER_getStateByCountryId,map, Request.Method.POST);
    }



/*    public void UpdateProfileMethod(String first_name,String last_name,String phone_no,Bitmap bitmap){

        String user_id=   Preference.get(UserProfileActivity.this,Preference.KEY_USER_ID);

        String access_token =   Preference.get(UserProfileActivity.this,Preference.KEY_accessToken);

        HashMap<String, String> map = new HashMap<>();

        map.put("is_mobile","1");
        map.put("access_token",access_token);
        map.put("first_name",first_name);
        map.put("last_name",last_name);
        map.put("date_of_birth","1993/01/02");
        map.put("mobile_number",phone_no);
        map.put("address","indore");
        map.put("latitude","22.27");
        map.put("longitude","75.87");
        map.put("country_id","1");
        map.put("state_id","1");
        map.put("city_id","1");
        map.put("zip_code","452001");
        map.put("web_link","www.theoneinfotech.com");
        map.put("logged_in_user_id",user_id);


        ApiRequest apiRequest = new ApiRequest(this,this);

       // apiRequest.postRequest(Constants.BASE_URL + Constants.USER_edituserprofile, Constants.USER_edituserprofile,map, Request.Method.POST);
        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_edituserprofile, Constants.USER_edituserprofile,map, Request.Method.POST);
    }*/


    @Override
    public void onResultReceived(String response, String tag_json_obj) {



        if (Constants.USER_GETPROFILE.equalsIgnoreCase(tag_json_obj)) {

            if (!response.equalsIgnoreCase(null)) {

                progressbar.setVisibility(View.GONE);

                UserProfielModel finalArray = new Gson().fromJson(response, new TypeToken<UserProfielModel>() {
                }.getType());

                String status = String.valueOf(finalArray.getErrorCode());

                if (finalArray.getErrorCode() == 2 && finalArray.getMessage().equalsIgnoreCase("Please Insert Valid Access Token"))
                {
                    Preference.clearPreference(this);
                    Intent intent=new Intent(UserProfileActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else if (status.equalsIgnoreCase("0")) {

                    String fname = finalArray.getData().get(0).getFirstName();
                    String lname = finalArray.getData().get(0).getLastName();
                    String email = finalArray.getData().get(0).getEmail();
                    String mobile = finalArray.getData().get(0).getMobileNumber();
                    String profileImage = finalArray.getData().get(0).getProfilePicture();
                    address = finalArray.getData().get(0).getAddress();

                    mAutocomplete.setText(address);
                    autoCompView.setText(address);

                    if(!profileImage.equalsIgnoreCase(""))
                    {
                        isImage = true;
                    }


                    Preference.save(UserProfileActivity.this,Preference.key_profileImage,profileImage);

                    if(profileImage != null && !profileImage.trim().equalsIgnoreCase("")) {
                        Picasso.with(UserProfileActivity.this).load(profileImage)
                                .placeholder(R.drawable.defaultprofile)
                                .into(user_profile);
                    }
                    ed_fname.setText(fname);
                    ed_lname.setText(lname);
                    ed_email.setText(email);
                    ed_mobile.setText(mobile);

                    // Toast.makeText(this, "" + finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                } else {


                    // Toast.makeText(this, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        } else if (Constants.USER_edituserprofile.equalsIgnoreCase(tag_json_obj)) {

            if (!response.equalsIgnoreCase(null)) {

                UpdateProfile finalArray1 = new Gson().fromJson(response, new TypeToken<UpdateProfile>() {
                }.getType());

                String status = String.valueOf(finalArray1.getErrorCode());

                if (finalArray1.getErrorCode()== 2 && finalArray1.getMessage().equalsIgnoreCase("Please Insert Valid Access Token"))
                {
                    Preference.clearPreference(this);
                    Intent intent=new Intent(UserProfileActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else if (status.equalsIgnoreCase("0")) {

                    progressbar.setVisibility(View.GONE);

                    Intent intent=new Intent(UserProfileActivity.this, HomeBottomActivity.class);
                    startActivity(intent);

                    String user_Name=finalArray1.getData().get(0).getFirstName();

                    Preference.save(UserProfileActivity.this,Preference.key_UserName,user_Name);

                    // Toast.makeText(this, "" + finalArray1.getMessage(), Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(this, finalArray1.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }else if (Constants.USER_getCountry.equalsIgnoreCase(tag_json_obj)) {

            if (!response.equalsIgnoreCase(null)) {

                CountryModel finalArray2 = new Gson().fromJson(response, new TypeToken<CountryModel>() {
                }.getType());

                String status = String.valueOf(finalArray2.getErrorCode());

                if (finalArray2.getErrorCode()== 2 && finalArray2.getMessage().equalsIgnoreCase("Please Insert Valid Access Token"))
                {
                    Preference.clearPreference(this);
                    Intent intent=new Intent(UserProfileActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else if (status.equalsIgnoreCase("0")) {

                    progressbar.setVisibility(View.GONE);

                    modelList_CountryList= (ArrayList<CountryDataModel>) finalArray2.getData();

                    Country_CustomAdapter customAdapter=new Country_CustomAdapter(getApplicationContext(),modelList_CountryList);

                    spinner_country.setAdapter(customAdapter);


                } else {

                }
            }
        }else if (Constants.USER_getStateByCountryId.equalsIgnoreCase(tag_json_obj)) {

            if (!response.equalsIgnoreCase(null)) {

                LocationModel finalArray2 = new Gson().fromJson(response, new TypeToken<LocationModel>() {
                }.getType());

                String status = String.valueOf(finalArray2.getErrorCode());

                if (finalArray2.getErrorCode()== 2 && finalArray2.getMessage().equalsIgnoreCase("Please Insert Valid Access Token"))
                {
                    Preference.clearPreference(this);
                    Intent intent=new Intent(UserProfileActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }/*else if (status.equalsIgnoreCase("0")) {

                    progressbar.setVisibility(View.GONE);

                    modelList_location= (ArrayList<LocationDataModel>) finalArray2.getData();

                    Location_CustomAdapter customAdapter=new Location_CustomAdapter(getApplicationContext(),modelList_location);

                    spinner_location.setAdapter(customAdapter);


                } */else {


                }
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "Please Check Network", Toast.LENGTH_SHORT).show();
    }


    public void UpdateProfileMethod(final String first_name, final String last_name, final String phone_no, final Bitmap bitmap){

        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, Constants.BASE_URL+Constants.USER_edituserprofile,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {

                            JSONObject obj = new JSONObject(new String(response.data));

                            progressbar.setVisibility(View.GONE);

                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                            String status = String.valueOf(obj.getString("errorCode"));

                            if (status.equalsIgnoreCase("0")){

                                progressbar.setVisibility(View.GONE);

                                JSONArray jsonArray = obj.optJSONArray("data");

                                JSONObject jsonObject = jsonArray.getJSONObject(0);

                                String img_Url = jsonObject.optString("profile_picture").toString();

                                Preference.save(UserProfileActivity.this,Preference.key_profileImage,img_Url);


                                String FullName = first_name +" "+ last_name;
                                Preference.save(UserProfileActivity.this,Preference.key_UserName,FullName);

                               /* Intent intent=new Intent(UserProfileActivity.this, ProfileFragment.class);
                                startActivity(intent);*/


                                /*Intent resultIntent = new Intent();
                                // TODO Add extras or a data URI to this intent as appropriate.
                                resultIntent.putExtra("some_key", "String data");
                                setResult(Activity.RESULT_OK, resultIntent);
                                finish();*/

                              /*  String user_Name=obj.getData.get(0).getFirstName();

                                Preference.save(UserProfileActivity.this,Preference.key_UserName,user_Name);

                                Toast.makeText(this, "" + obj.getMessage(), Toast.LENGTH_SHORT).show();*/

                            } else {

                                progressbar.setVisibility(View.GONE);

                                //   Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

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
                        progressbar.setVisibility(View.GONE);
                        //   Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("GotError",""+error.getMessage());
                    }
                }) {


            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                if(bitmap != null && !bitmap.equals("")) {
                    params.put("profile_picture", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                }
                return params;
            }


            @Override
            protected Map<String, String> getParams() {

                Map<String, String> map = new HashMap<>();

                String user_id=   Preference.get(UserProfileActivity.this,Preference.KEY_USER_ID);

                String access_token =   Preference.get(UserProfileActivity.this,Preference.KEY_accessToken);

                map.put("is_mobile","1");
                map.put("access_token",access_token);
                map.put("first_name",first_name);
                map.put("last_name",last_name);
                map.put("date_of_birth","1993/01/02");
                map.put("mobile_number",phone_no);
                map.put("location",address);
                map.put("latitude","22.27");
                map.put("longitude","75.87");
                map.put("web_link","www.theoneinfotech.com");
                map.put("logged_in_user_id",user_id);

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

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }




    //Google Api Place
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
         address = (String) adapterView.getItemAtPosition(i);
        Toast.makeText(this, address, Toast.LENGTH_SHORT).show();
    }
    @SuppressLint("LongLogTag")
    public static ArrayList autocomplete(String input) {
        ArrayList resultList = null;

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?key=" + API_KEY);
            sb.append("&components=country:ua");
            sb.append("&input=" + URLEncoder.encode(input, "utf8"));

            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error processing Places API URL", e);
            return resultList;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to Places API", e);
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            // Extract the Place descriptions from the results
            resultList = new ArrayList(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                System.out.println(predsJsonArray.getJSONObject(i).getString("description"));
                System.out.println("============================================================");
                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Cannot process JSON results", e);
        }

        return resultList;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}

class GooglePlacesAutocompleteAdapter extends ArrayAdapter implements Filterable {

    private ArrayList resultList;

    public GooglePlacesAutocompleteAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public String getItem(int index) {
        return (String) resultList.get(index);
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    // Retrieve the autocomplete results.
                    resultList = autocomplete(constraint.toString());

                    // Assign the data to the FilterResults
                    filterResults.values = resultList;
                    filterResults.count = resultList.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }



}
