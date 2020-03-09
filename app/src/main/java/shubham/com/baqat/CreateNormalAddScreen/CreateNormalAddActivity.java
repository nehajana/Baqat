package shubham.com.baqat.CreateNormalAddScreen;

import android.Manifest;
import android.app.DatePickerDialog;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import shubham.com.baqat.CreateExecutivAddScren.createExecutiveModel;
import shubham.com.baqat.CreateNormalAddScreen.OccasionApiModel.OccasionDataModel;
import shubham.com.baqat.CreateNormalAddScreen.OccasionApiModel.SelectOccasionList;
import shubham.com.baqat.CreateTopNewsScreen.UploadImageTopNewsAbstractModel;
import shubham.com.baqat.CreateTopNewsScreen.UploadImageTopNewsRecyclerViewAdapter;
import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.PaymentScreen.PaypalActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
import shubham.com.baqat.UserProfile.VolleyMultipartRequest;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;

public class CreateNormalAddActivity extends AppCompatActivity  implements IApiResponse,AdapterView.OnItemSelectedListener{


    String[] countryNames={"Select Occasion","India","China","Australia","Portugle","America","New Zealand"};
    String[] countryNames2={"Select Categories","India","China","Australia","Portugle","America","New Zealand"};

    String[] GettingMarried_type_1={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_2={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_3={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_4={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_5={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_6={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_7={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_8={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_9={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_10={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_11={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_12={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_13={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_14={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_15={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_16={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_17={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_18={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_19={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_20={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_21={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_22={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_23={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_24={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_25={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_26={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_27={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_28={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_29={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_30={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_31={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_32={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_33={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_34={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_35={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_36={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_37={"Veg","NonVeg","Chinese","Thai"};
    String[] GettingMarried_type_38={"Veg","NonVeg","Chinese","Thai"};

    //------------------ Expecting a baby Array ----------------------------

    String[] expectingABaby_type_1={"Veg","NonVeg","Chinese","Thai"};
    String[] expectingABaby_type_2={"Veg","NonVeg","Chinese","Thai"};
    String[] expectingABaby_type_3={"Veg","NonVeg","Chinese","Thai"};
    String[] expectingABaby_type_4={"Veg","NonVeg","Chinese","Thai"};
    String[] expectingABaby_type_5={"Veg","NonVeg","Chinese","Thai"};
    String[] expectingABaby_type_6={"Veg","NonVeg","Chinese","Thai"};
    String[] expectingABaby_type_7={"Veg","NonVeg","Chinese","Thai"};
    String[] expectingABaby_type_8={"Veg","NonVeg","Chinese","Thai"};
    String[] expectingABaby_type_9={"Veg","NonVeg","Chinese","Thai"};
    String[] expectingABaby_type_10={"Veg","NonVeg","Chinese","Thai"};
    String[] expectingABaby_type_11={"Veg","NonVeg","Chinese","Thai"};
    String[] expectingABaby_type_12={"Veg","NonVeg","Chinese","Thai"};

    //------------------Shopping ----------------------------

    String[] Shoppin_type_1={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_2={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_3={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_4={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_5={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_6={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_7={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_8={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_9={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_10={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_11={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_12={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_13={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_14={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_15={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_16={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_17={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_18={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_19={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_20={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_21={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_22={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_23={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_24={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_25={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_26={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_27={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_28={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_29={"Veg","NonVeg","Chinese","Thai"};
    String[] Shoppin_type_30={"Veg","NonVeg","Chinese","Thai"};

    String[] fundWellBeing_type_1={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_2={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_3={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_4={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_5={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_6={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_7={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_8={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_9={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_10={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_11={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_12={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_13={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_14={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_15={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_16={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_17={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_18={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_19={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_20={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_21={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_22={"Veg","NonVeg","Chinese","Thai"};
    String[] fundWellBeing_type_23={"Veg","NonVeg","Chinese","Thai"};

    String[] planningCelebration_type_1={"Veg","NonVeg","Chinese","Thai"};
    String[] planningCelebration_type_2={"Veg","NonVeg","Chinese","Thai"};
    String[] planningCelebration_type_3={"Veg","NonVeg","Chinese","Thai"};
    String[] planningCelebration_type_4={"Veg","NonVeg","Chinese","Thai"};
    String[] planningCelebration_type_5={"Veg","NonVeg","Chinese","Thai"};
    String[] planningCelebration_type_6={"Veg","NonVeg","Chinese","Thai"};
    String[] planningCelebration_type_7={"Veg","NonVeg","Chinese","Thai"};
    String[] planningCelebration_type_8={"Veg","NonVeg","Chinese","Thai"};
    String[] planningCelebration_type_9={"Veg","NonVeg","Chinese","Thai"};
    String[] planningCelebration_type_10={"Veg","NonVeg","Chinese","Thai"};
    String[] planningCelebration_type_11={"Veg","NonVeg","Chinese","Thai"};
    String[] planningCelebration_type_12={"Veg","NonVeg","Chinese","Thai"};
    String[] planningCelebration_type_13={"Veg","NonVeg","Chinese","Thai"};
    String[] planningCelebration_type_14={"Veg","NonVeg","Chinese","Thai"};
    String[] planningCelebration_type_15={"Veg","NonVeg","Chinese","Thai"};
    String[] planningCelebration_type_16={"Veg","NonVeg","Chinese","Thai"};

    String[]  planningVocation_type_1={"Veg","NonVeg","Chinese","Thai"};
    String[]  planningVocation_type_2={"Veg","NonVeg","Chinese","Thai"};
    String[]  planningVocation_type_3={"Veg","NonVeg","Chinese","Thai"};
    String[]  planningVocation_type_4={"Veg","NonVeg","Chinese","Thai"};
    String[]  planningVocation_type_5={"Veg","NonVeg","Chinese","Thai"};
    String[]  planningVocation_type_6={"Veg","NonVeg","Chinese","Thai"};
    String[]  planningVocation_type_7={"Veg","NonVeg","Chinese","Thai"};
    String[]  planningVocation_type_8={"Veg","NonVeg","Chinese","Thai"};
    String[]  planningVocation_type_9={"Veg","NonVeg","Chinese","Thai"};
    String[]  planningVocation_type_10={"Veg","NonVeg","Chinese","Thai"};
    String[]  planningVocation_type_11={"Veg","NonVeg","Chinese","Thai"};
    String[]  planningVocation_type_12={"Veg","NonVeg","Chinese","Thai"};
    String[]  planningVocation_type_13={"Veg","NonVeg","Chinese","Thai"};
    String[]  planningVocation_type_14={"Veg","NonVeg","Chinese","Thai"};
    String[]  planningVocation_type_15={"Veg","NonVeg","Chinese","Thai"};
    String[]  planningVocation_type_16={"Veg","NonVeg","Chinese","Thai"};
    String[]  planningVocation_type_17={"Veg","NonVeg","Chinese","Thai"};
    String[]  planningVocation_type_18={"Veg","NonVeg","Chinese","Thai"};
    String[]  planningVocation_type_19={"Veg","NonVeg","Chinese","Thai"};

      // <--startingBusiness_type_arrY-->

    String[]  startingBusiness_type_1={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_2={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_3={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_4={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_5={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_6={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_7={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_8={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_9={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_10={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_11={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_12={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_13={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_14={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_15={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_16={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_17={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_18={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_19={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_20={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_21={"Veg","NonVeg","Chinese","Thai"};
    String[]  startingBusiness_type_22={"Veg","NonVeg","Chinese","Thai"};


   // Moving or Relocating aRRAY

    String[]  MovingRelocatingSpinner_type_1={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_2={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_3={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_4={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_5={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_6={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_7={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_8={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_9={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_10={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_11={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_12={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_13={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_14={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_15={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_16={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_17={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_18={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_19={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_20={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_21={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_22={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_23={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_24={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_25={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_26={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_27={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_28={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_29={"Veg","NonVeg","Chinese","Thai"};
    String[]  MovingRelocatingSpinner_type_30={"Veg","NonVeg","Chinese","Thai"};


    /*Education And Career Array */

    String[]  educationCareerSpinner_type_1={"Veg","NonVeg","Chinese","Thai"};
    String[]  educationCareerSpinner_type_2={"Veg","NonVeg","Chinese","Thai"};
    String[]  educationCareerSpinner_type_3={"Veg","NonVeg","Chinese","Thai"};
    String[]  educationCareerSpinner_type_4={"Veg","NonVeg","Chinese","Thai"};
    String[]  educationCareerSpinner_type_5={"Veg","NonVeg","Chinese","Thai"};
    String[]  educationCareerSpinner_type_6={"Veg","NonVeg","Chinese","Thai"};
    String[]  educationCareerSpinner_type_7={"Veg","NonVeg","Chinese","Thai"};
    String[]  educationCareerSpinner_type_8={"Veg","NonVeg","Chinese","Thai"};





    String country_str;
    String country_str2;
    RelativeLayout back_layout;
    TextView text_career;
    ArrayList<SelectOccasionList> OccationList = new ArrayList<>();
    ArrayList<SubOccasionDataModel> SubOccationList = new ArrayList<>();
    int count = 0;
    String catName = "";
    Spinner spin;
    // Spinner spin2;

    boolean isCategory=false;
    boolean isSubCategory=false;
    Button submit_btn_add;
    private static final int REQUEST_PERMISSIONS = 100;

    RelativeLayout RR_upload_image;
    RelativeLayout RR_video_upload;
    Bitmap bitmap;
    TextView txt_upload;
    TextView txt_listing_expiration;
    TextView txt_offer_valitity_start;
    TextView txt_offer_end;
    private static final int SELECT_VIDEO = 3;
    private int mYear, mMonth,mDay;
    String frmdate="";

    private RecyclerView recycler_view_upload_image_normalAdd;
    private UploadImageTopNewsRecyclerViewAdapter mAdapter;
    private ArrayList<UploadImageTopNewsAbstractModel> modelList = new ArrayList<>();
    String mediaPAth;
    ProgressBar progressbar;

    EditText ed_title;
    EditText ed_contact_email;
    EditText ed_contact_numbetr;
    EditText ed_keyword;
    EditText edt_description;
    EditText ed_youtube_link;
    EditText edt_listing_price_ads;
    EditText edt_cmny_name;
    EditText edt_webside;
    EditText edt_specific;
    EditText Offer_Detail;
    EditText edt_Condition;
    EditText edt_Location;
   // EditText edt_Country;
  //  EditText edt_State;
    EditText edt_City;
    EditText edt_Address;
    CheckBox checkbox;

    //-------------- Getting Married -----------------
    LinearLayout LL_categories_one;
    LinearLayout LL_Restaurants_Catering;
    LinearLayout LL_Fashion;
    LinearLayout LL_Flowers;
    LinearLayout LL_Photography;
    LinearLayout LL_Motors;
    LinearLayout LL_Hotels_Resortd;
    LinearLayout LL_Properties;
    LinearLayout LL_Beauty_SPA;
    LinearLayout LL_Home_Garden;
    LinearLayout LL_Events_Planners;
    LinearLayout LL_Home_Appliances;
    TextView txtcategory;

    private Spinner gettingMarriedSpinner1;
    private Spinner spinnerGettingMarriedSpinner2;
    private Spinner spinnerGettingMarriedSpinner3;
    private Spinner spinnerGettingMarriedSpinner4;
    private Spinner spinnerGettingMarriedSpinner5;
    private Spinner spinnerGettingMarriedSpinner6;
    private Spinner spinnerGettingMarriedSpinner7;
    private Spinner spinnerGettingMarriedSpinner8;
    private Spinner spinnerGettingMarriedSpinner9;
    private Spinner spinnerGettingMarriedSpinner10;
    private Spinner spinnerGettingMarriedSpinner11;
    private Spinner spinnerGettingMarriedSpinner12;
    private Spinner spinnerGettingMarriedSpinner13;
    private Spinner spinnerGettingMarriedSpinner14;
    private Spinner spinnerGettingMarriedSpinner15;
    private Spinner spinnerGettingMarriedSpinner16;
    private Spinner spinnerGettingMarriedSpinner17;
    private Spinner spinnerGettingMarriedSpinner18;
    private Spinner spinnerGettingMarriedSpinner19;
    private Spinner spinnerGettingMarriedSpinner20;
    private Spinner spinnerGettingMarriedSpinner21;
    private Spinner spinnerGettingMarriedSpinner22;
    private Spinner spinnerGettingMarriedSpinner23;
    private Spinner spinnerGettingMarriedSpinner24;
    private Spinner spinnerGettingMarriedSpinner25;
    private Spinner spinnerGettingMarriedSpinner26;
    private Spinner spinnerGettingMarriedSpinner27;
    private Spinner spinnerGettingMarriedSpinner28;
    private Spinner spinnerGettingMarriedSpinner29;
    private Spinner spinnerGettingMarriedSpinner30;
    private Spinner spinnerGettingMarriedSpinner31;
    private Spinner spinnerGettingMarriedSpinner32;
    private Spinner spinnerGettingMarriedSpinner33;
    private Spinner spinnerGettingMarriedSpinner34;
    private Spinner spinnerGettingMarriedSpinner35;
    private Spinner spinnerGettingMarriedSpinner36;
    private Spinner spinnerGettingMarriedSpinner37;
    private Spinner spinnerGettingMarriedSpinner38;

    //------------------ Expecting a baby ----------------------------
    private LinearLayout LLExpectingABaby1;
    private Spinner expectingABabySpinner1;
    private Spinner expectingABabySpinner2;
    private Spinner expectingABabySpinner3;
    private LinearLayout LLExpectingABaby2;
    private Spinner expectingABabySpinner4;
    private Spinner expectingABabySpinner5;
    private LinearLayout LLExpectingABaby3;
    private Spinner expectingABabySpinner6;
    private LinearLayout LLExpectingABaby4;
    private Spinner expectingABabySpinner7;
    private LinearLayout LLExpectingABaby5;
    private Spinner expectingABabySpinner8;
    private LinearLayout LLExpectingABaby6;
    private Spinner expectingABabySpinner9;
    private LinearLayout LLExpectingABaby7;
    private Spinner expectingABabySpinner10;
    private Spinner expectingABabySpinner11;
    private Spinner expectingABabySpinner12;

    //------------------Shopping ----------------------------
    private LinearLayout LLShopping1;
    private Spinner ShoppingSpinner1;
    private LinearLayout LLShopping2;
    private Spinner ShoppingSpinner2;
    private Spinner ShoppingSpinner4;
    private Spinner ShoppingSpinner5;
    private LinearLayout LLShopping3;
    private Spinner ShoppingSpinner6;
    private Spinner ShoppingSpinner7;
    private Spinner ShoppingSpinner8;
    private LinearLayout LLShopping4;
    private Spinner ShoppingSpinner9;
    private Spinner ShoppingSpinner10;
    private Spinner ShoppingSpinner11;
    private LinearLayout LLShopping5;
    private LinearLayout LLShopping6;
    private Spinner ShoppingSpinner12;
    private Spinner ShoppingSpinner13;
    private LinearLayout LLShopping7;
    private Spinner ShoppingSpinner14;
    private Spinner ShoppingSpinner15;
    private LinearLayout LLShopping8;
    private Spinner ShoppingSpinner16;
    private LinearLayout LLShopping9;
    private Spinner ShoppingSpinner17;
    private Spinner ShoppingSpinner18;
    private Spinner ShoppingSpinner19;
    private Spinner ShoppingSpinner20;
    private Spinner ShoppingSpinner21;
    private LinearLayout LLShopping10;
    private Spinner ShoppingSpinner22;
    private Spinner ShoppingSpinner23;
    private LinearLayout LLShopping11;
    private Spinner ShoppingSpinner24;
    private Spinner ShoppingSpinner25;
    private LinearLayout LLShopping12;
    private Spinner ShoppingSpinner26;
    private Spinner ShoppingSpinner27;
    private Spinner ShoppingSpinner28;
    private LinearLayout LLShopping13;
    private Spinner ShoppingSpinner29;
    private Spinner ShoppingSpinner30;

// --------------- Fund Well Being --------------

    private LinearLayout LLFundWellBeing1;
    private Spinner fundWellBeingSpinner1;
    private Spinner fundWellBeingSpinner2;
    private Spinner fundWellBeingSpinner3;
    private LinearLayout LLFundWellBeing2;
    private Spinner fundWellBeingSpinner4;
    private LinearLayout LLFundWellBeing3;
    private Spinner fundWellBeingSpinner5;
    private LinearLayout LLFundWellBeing4;
    private LinearLayout LLFundWellBeing5;
    private Spinner fundWellBeingSpinner6;
    private Spinner fundWellBeingSpinner7;
    private LinearLayout LLFundWellBeing6;
    private Spinner fundWellBeingSpinner8;
    private LinearLayout LLFundWellBeing7;
    private Spinner fundWellBeingSpinner9;
    private LinearLayout LLFundWellBeing8;
    private Spinner fundWellBeingSpinner10;
    private Spinner fundWellBeingSpinner11;
    private LinearLayout LLFundWellBeing9;
    private Spinner fundWellBeingSpinner12;
    private Spinner fundWellBeingSpinner13;
    private Spinner fundWellBeingSpinner14;
    private Spinner fundWellBeingSpinner15;
    private Spinner fundWellBeingSpinner16;
    private Spinner fundWellBeingSpinner17;
    private Spinner fundWellBeingSpinner18;
    private Spinner fundWellBeingSpinner19;
    private Spinner fundWellBeingSpinner20;
    private Spinner fundWellBeingSpinner21;


    // --------------- planning Celebration --------------

    private LinearLayout LLPlaningCelebration1;
    private LinearLayout LLPlaningCelebration2;
    private LinearLayout LLPlaningCelebration3;
    private LinearLayout LLPlaningCelebration4;
    private LinearLayout LLPlaningCelebration5;
    private LinearLayout LLPlaningCelebration6;
    private LinearLayout LLPlaningCelebration7;
    private Spinner planningCelebrationSpinner1;
    private Spinner planningCelebrationSpinner2;
    private Spinner planningCelebrationSpinner3;
    private Spinner planningCelebrationSpinner4;
    private Spinner planningCelebrationSpinner5;
    private Spinner planningCelebrationSpinner6;
    private Spinner planningCelebrationSpinner7;
    private Spinner planningCelebrationSpinner8;
    private Spinner planningCelebrationSpinner9;
    private Spinner planningCelebrationSpinner10;
    private Spinner planningCelebrationSpinner11;
    private Spinner planningCelebrationSpinner12;
    private Spinner planningCelebrationSpinner13;
    private Spinner planningCelebrationSpinner14;
    private Spinner planningCelebrationSpinner15;
    private Spinner planningCelebrationSpinner16;

    // --------------- planning Vocation --------------

    private LinearLayout LLPlanningVocation1;
    private LinearLayout LL_planning_vocation_2;
    private LinearLayout LL_planning_vocation_3;
    private LinearLayout LL_planning_vocation_4;
    private Spinner planningVocationSpinner1;
    private Spinner planningVocationSpinner2;
    private Spinner planningVocationSpinner3;
    private Spinner planningVocationSpinner4;
    private Spinner planningVocationSpinner5;
    private Spinner planningVocationSpinner6;
    private Spinner planningVocationSpinner7;
    private Spinner planningVocationSpinner8;
    private Spinner planningVocationSpinner9;
    private Spinner planningVocationSpinner10;
    private Spinner planningVocationSpinner11;
    private Spinner planningVocationSpinner12;
    private Spinner planningVocationSpinner13;
    private Spinner planningVocationSpinner14;
    private Spinner planningVocationSpinner15;
    private Spinner planningVocationSpinner16;
    private Spinner planningVocationSpinner17;
    private Spinner planningVocationSpinner18;
    private Spinner planningVocationSpinner19;



    // --------------- Starting Business --------------

    private LinearLayout LLStartingBusiness1;
    private LinearLayout LLShowroomsOffices1;
    private Spinner startingBusinessSpinner1;
    private Spinner startingBusinessSpinner2;
    private Spinner startingBusinessSpinner3;
    private LinearLayout LLShowroomsOffices2;
    private Spinner startingBusinessSpinner4;
    private Spinner startingBusinessSpinner5;
    private LinearLayout LLShowroomsOffices3;
    private Spinner startingBusinessSpinner6;
    private Spinner startingBusinessSpinner7;
    private Spinner startingBusinessSpinner8;
    private Spinner startingBusinessSpinner9;
    private LinearLayout LLShowroomsOffices4;
    private Spinner startingBusinessSpinner10;
    private Spinner startingBusinessSpinner11;
    private Spinner startingBusinessSpinner12;
    private LinearLayout LLShowroomsOffices5;
    private Spinner startingBusinessSpinner13;
    private Spinner startingBusinessSpinner14;
    private Spinner startingBusinessSpinner15;
    private Spinner startingBusinessSpinner16;
    private Spinner startingBusinessSpinner17;
    private LinearLayout LLShowroomsOffices6;
    private Spinner startingBusinessSpinner18;
    private LinearLayout LLShowroomsOffices7;
    private Spinner startingBusinessSpinner19;
    private LinearLayout LLShowroomsOffices8;
    private Spinner startingBusinessSpinner20;
    private Spinner startingBusinessSpinner21;
    private Spinner startingBusinessSpinner22;

    // ------------------------ Moving or Relocating -------------------------

    private LinearLayout LLMovingRelocating1;
    private Spinner MovingRelocatingSpinner1;
    private Spinner MovingRelocatingSpinner3;
    private Spinner MovingRelocatingSpinner4;
    private Spinner MovingRelocatingSpinner5;
    private Spinner MovingRelocatingSpinner6;
    private Spinner MovingRelocatingSpinner7;
    private Spinner MovingRelocatingSpinner8;
    private Spinner MovingRelocatingSpinner9;
    private Spinner MovingRelocatingSpinner10;
    private Spinner MovingRelocatingSpinner11;
    private LinearLayout LLMovingRelocating2;
    private Spinner MovingRelocatingSpinner12;
    private Spinner MovingRelocatingSpinner13;
    private Spinner MovingRelocatingSpinner14;
    private Spinner MovingRelocatingSpinner15;
    private Spinner MovingRelocatingSpinner16;
    private Spinner MovingRelocatingSpinner17;
    private Spinner MovingRelocatingSpinner18;
    private Spinner MovingRelocatingSpinner19;
    private Spinner MovingRelocatingSpinner20;
    private Spinner MovingRelocatingSpinner21;
    private LinearLayout LLMovingRelocating3;
    private Spinner MovingRelocatingSpinner22;
    private Spinner MovingRelocatingSpinner23;
    private Spinner MovingRelocatingSpinner24;
    private Spinner MovingRelocatingSpinner25;
    private Spinner MovingRelocatingSpinner26;
    private Spinner MovingRelocatingSpinner27;
    private Spinner MovingRelocatingSpinner28;
    private Spinner MovingRelocatingSpinner29;
    private Spinner MovingRelocatingSpinner30;

//------------------  Education And Career ------------------

    private LinearLayout LLEducationAndCareer1;
    private Spinner educationCareerSpinner1;
    private Spinner educationCareerSpinner2;
    private LinearLayout LLEducationAndCareer2;
    private Spinner educationCareerSpinner3;
    private Spinner educationCareerSpinner4;
    private Spinner educationCareerSpinner5;
    private LinearLayout LLEducationAndCareer3;
    private Spinner educationCareerSpinner6;
    private LinearLayout LLEducationAndCareer4;
    private Spinner educationCareerSpinner7;
    private LinearLayout LLEducationAndCareer5;
    private Spinner educationCareerSpinner8;

    private RecyclerView recycler_view_category;
    private CategorySectionRecyclerViewAdapter mAdapterCategory;

    RelativeLayout RR_category;
    boolean isCategorySection = false;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_normal_add);

        findview();

        text_career.setVisibility(View.VISIBLE);
        text_career.setText("Create Ads");

        //spin.setOnItemSelectedListener(this);
        //spin2.setOnItemSelectedListener(this);

        back_layout=(RelativeLayout) findViewById(R.id.back_layout);

        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                String Parent_id=OccationList.get(position).getCategoryId().toString();

                //  Toast.makeText(CreateNormalAddActivity.this, ""+Parent_id, Toast.LENGTH_SHORT).show();

                CreateNormalSubAddSection(Parent_id);
                //  Toast.makeText(CreateNormalAddActivity.this, lookingforList.get(position).getProfileId(), Toast.LENGTH_LONG).show();

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });


        txt_listing_expiration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateNormalAddActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                //  RR_booking_Date.setVisibility( View.VISIBLE );
                                //  txt_time.setVisibility(View.VISIBLE);
                                view.setVisibility(View.VISIBLE);
                                frmdate = (dayOfMonth+"-"+(monthOfYear+1)+"-"+year);
                                txt_listing_expiration.setText(frmdate);

                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                datePickerDialog.show();

            }
        });

        txt_offer_valitity_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateNormalAddActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                //  RR_booking_Date.setVisibility( View.VISIBLE );
                                //  txt_time.setVisibility(View.VISIBLE);
                                view.setVisibility(View.VISIBLE);
                                frmdate = (dayOfMonth+"-"+(monthOfYear+1)+"-"+year);
                                txt_offer_valitity_start.setText(frmdate);

                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                datePickerDialog.show();

            }
        });

        txt_offer_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateNormalAddActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                //  RR_booking_Date.setVisibility( View.VISIBLE );
                                //  txt_time.setVisibility(View.VISIBLE);
                                view.setVisibility(View.VISIBLE);
                                frmdate = (dayOfMonth+"-"+(monthOfYear+1)+"-"+year);
                                txt_offer_end.setText(frmdate);

                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                datePickerDialog.show();

            }
        });

        submit_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title=ed_title.getText().toString();
                String ContactEmail=ed_contact_email.getText().toString();
                String ContactNumber=ed_contact_numbetr.getText().toString();
                String ContacKeyword=ed_keyword.getText().toString();
                String Description=edt_description.getText().toString();
                String YoutubeLink=ed_youtube_link.getText().toString();
                String PriceAds=edt_listing_price_ads.getText().toString();
                String CompanyName=edt_cmny_name.getText().toString();
                String Webside=edt_webside.getText().toString();
                String Specific=edt_specific.getText().toString();
                String OfferDetaails=Offer_Detail.getText().toString();
                String Condition=edt_Condition.getText().toString();
                String Location=edt_Location.getText().toString();
              //  String Country= edt_Country.getText().toString();
               // String State=edt_State.getText().toString();
                //String City=edt_City.getText().toString();
             //   String Address=edt_Address.getText().toString();

                if(title.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateNormalAddActivity.this, "Please enter Title", Toast.LENGTH_SHORT).show();

                }else if(ContactEmail.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateNormalAddActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();

                }else if(ContactNumber.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateNormalAddActivity.this, "Please enter Contact", Toast.LENGTH_SHORT).show();

                }else if(Description.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateNormalAddActivity.this, "Please enter Description", Toast.LENGTH_SHORT).show();

                }else if(YoutubeLink.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateNormalAddActivity.this, "Please enter YoutubeLink", Toast.LENGTH_SHORT).show();

                }else if(PriceAds.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateNormalAddActivity.this, "Please enter PriceAds", Toast.LENGTH_SHORT).show();

                }else if(CompanyName.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateNormalAddActivity.this, "Please enter Company Name", Toast.LENGTH_SHORT).show();

                }else if(Webside.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateNormalAddActivity.this, "Please enter Webside", Toast.LENGTH_SHORT).show();

                }else if(Specific.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateNormalAddActivity.this, "Please enter Specific", Toast.LENGTH_SHORT).show();

                }else if(OfferDetaails.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateNormalAddActivity.this, "Please enter Offer Details", Toast.LENGTH_SHORT).show();

                }else if(Condition.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateNormalAddActivity.this, "Please enter Condition", Toast.LENGTH_SHORT).show();

                }else if(Location.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateNormalAddActivity.this, "Please enter Location", Toast.LENGTH_SHORT).show();

                }/*else if(Country.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateNormalAddActivity.this, "Please enter Country", Toast.LENGTH_SHORT).show();

                }*//*else if(City.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateNormalAddActivity.this, "Please enter City", Toast.LENGTH_SHORT).show();


                }*//*else if(State.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateNormalAddActivity.this, "Please enter State", Toast.LENGTH_SHORT).show();

                }*/ /*else if(Address.equalsIgnoreCase(""))
                {
                    Toast.makeText(CreateNormalAddActivity.this, "Please enter Address", Toast.LENGTH_SHORT).show();

                }*/else if(!checkbox.isChecked())
                {
                    Toast.makeText(CreateNormalAddActivity.this, "Please Selected Terms Condition", Toast.LENGTH_SHORT).show();

                }else
                {
                    CreateNormalAdsAddSection(title,ContactEmail,ContactNumber,Description,YoutubeLink,PriceAds,
                            CompanyName,Webside,Specific,OfferDetaails,Condition,Location,"","","","");
                }

           /*     Intent intent=new Intent(CreateNormalAddActivity.this,PaypalActivity.class);
                startActivity(intent);*/
            }
        });

        RR_video_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select a Video "), SELECT_VIDEO);
            }
        });


        RR_upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    if ((ActivityCompat.shouldShowRequestPermissionRationale(CreateNormalAddActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(CreateNormalAddActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE))) {

                    } else {
                        ActivityCompat.requestPermissions(CreateNormalAddActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_PERMISSIONS);
                    }
                }else {

                    showPictureDialog();
                }

            }
        });

        setAdapter();

        CreateNormalAddSection("1");


        ButterKnife.bind(this);

        mAutocomplete.setOnPlaceSelectedListener(new OnPlaceSelectedListener() {
            @Override
            public void onPlaceSelected(final Place place) {
                mAutocomplete.getDetailsFor(place, new DetailsCallback() {
                    @Override
                    public void onSuccess(final PlaceDetails details) {
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
                                        mCity.setText(component.long_name);
                                        break;
                                    case ADMINISTRATIVE_AREA_LEVEL_1:
                                        mState.setText(component.short_name);
                                        break;
                                    case ADMINISTRATIVE_AREA_LEVEL_2:
                                        break;
                                    case COUNTRY:
                                        break;
                                    case POSTAL_CODE:
                                        mZip.setText(component.long_name);
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

    }


    private void setAdapterCategory(ArrayList<SubOccasionDataModel> subOccationList) {

        mAdapterCategory = new CategorySectionRecyclerViewAdapter(CreateNormalAddActivity.this, subOccationList);
        recycler_view_category.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view_category.setLayoutManager(layoutManager);
        recycler_view_category.setAdapter(mAdapterCategory);

        mAdapterCategory.SetOnItemClickListener(new CategorySectionRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, SubOccasionDataModel model, ImageView img_check) {

                if(model.getChecked()){
                    img_check.setVisibility(View.GONE);
                    model.setChecked(false);
                    count = count - 1;
                    if(count == 0){
                        catName = "No Item Selected";
                    }
                    if(count == 1){
                        catName = "1 Items Selected";
                    }else  if(count == 2){
                        catName = "2 Items Selected";
                    }else  if(count == 3){
                        catName = "3 Items Selected";
                    }else if(count == 4){
                        catName = "4 Items Selected";
                    }
                    txtcategory.setText(catName);


                    //------------------ Getting Married Visible -----------------------

                    if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") &&  SubOccationList.get(position).getCategoryId().equalsIgnoreCase("10"))
                    {
                        LL_Restaurants_Catering.setVisibility(View.GONE);

                    }else   if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("11"))
                    {
                        LL_Fashion.setVisibility(View.GONE);

                    }else if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("12"))
                    {
                        LL_Flowers.setVisibility(View.GONE);

                    }else  if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("13"))
                    {
                        LL_Photography.setVisibility(View.GONE);

                    }else if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("14"))
                    {
                        LL_Motors.setVisibility(View.GONE);

                    }else  if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("15"))
                    {
                        LL_Hotels_Resortd.setVisibility(View.GONE);

                    }else if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("16"))
                    {
                        LL_Properties.setVisibility(View.GONE);


                    }else  if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("17"))
                    {
                        LL_Properties.setVisibility(View.GONE);

                    }else  if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("18"))
                    {
                        LL_Beauty_SPA.setVisibility(View.GONE);

                    }else   if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("19"))
                    {

                        Toast.makeText(CreateNormalAddActivity.this, "No Service,cleaning", Toast.LENGTH_SHORT).show();

                    }else   if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("20"))
                    {
                        LL_Home_Garden.setVisibility(View.GONE);

                    }else  if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("21"))
                    {
                        LL_Events_Planners.setVisibility(View.GONE);

                    }else   if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("22"))
                    {
                        Toast.makeText(CreateNormalAddActivity.this, "No Govt.Data", Toast.LENGTH_SHORT).show();

                    }else  if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("23"))
                    {
                        LL_Home_Appliances.setVisibility(View.GONE);

                    }

                    //------------------ Expecting a baby Gone ---------------------------

                    if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") &&  SubOccationList.get(position).getCategoryId().equalsIgnoreCase("24"))
                    {
                        LLExpectingABaby1.setVisibility(View.GONE);

                    }else  if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("25"))
                    {

                        LLExpectingABaby2.setVisibility(View.GONE);

                    }else   if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("26"))
                    {
                        Toast.makeText(CreateNormalAddActivity.this, "No Data Child Care", Toast.LENGTH_SHORT).show();

                    }else   if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("27"))
                    {
                        Toast.makeText(CreateNormalAddActivity.this, "No Data Planing Areas", Toast.LENGTH_SHORT).show();

                    }else   if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("28"))
                    {
                        LLExpectingABaby3.setVisibility(View.GONE);

                    }else   if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("29"))
                    {
                        LLExpectingABaby4.setVisibility(View.GONE);

                    }else   if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("30"))
                    {
                        LLExpectingABaby5.setVisibility(View.GONE);

                    }else   if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("31"))
                    {
                        Toast.makeText(CreateNormalAddActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();

                    } else  if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("32"))
                    {
                        LLExpectingABaby6.setVisibility(View.GONE);

                    }else   if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("33"))
                    {
                        LLExpectingABaby7.setVisibility(View.GONE);
                    }


                    //------------------ Shopping Gone ---------------------------

                    if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") &&  SubOccationList.get(position).getCategoryId().equalsIgnoreCase("34"))
                    {
                        LLShopping1.setVisibility(View.GONE);


                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("35"))
                    {
                        LLShopping2.setVisibility(View.GONE);

                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("36"))
                    {
                        LLShopping3.setVisibility(View.GONE);


                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("37"))
                    {
                        LLShopping4.setVisibility(View.GONE);


                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("38"))
                    {
                        Toast.makeText(CreateNormalAddActivity.this, "No Data Pets", Toast.LENGTH_SHORT).show();

                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("39"))
                    {
                        LLShopping5.setVisibility(View.GONE);

                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("40"))
                    {
                        LLShopping6.setVisibility(View.GONE);

                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("41"))
                    {
                        LLShopping7.setVisibility(View.GONE);

                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("42"))
                    {
                        LLShopping8.setVisibility(View.GONE);

                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("43"))
                    {
                        LLShopping9.setVisibility(View.GONE);

                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("44"))
                    {
                        Toast.makeText(CreateNormalAddActivity.this, "No Data Travel", Toast.LENGTH_SHORT).show();

                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("45"))
                    {
                        LLShopping10.setVisibility(View.GONE);

                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("46"))
                    {
                        LLShopping11.setVisibility(View.GONE);

                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("47"))
                    {
                        LLShopping12.setVisibility(View.GONE);

                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("48"))
                    {
                        LLShopping13.setVisibility(View.GONE);

                    }

                    // --------------- Fund Well Being --------------

                    if(OccationList.get(3).getCategoryId().equalsIgnoreCase("4") &&  SubOccationList.get(position).getCategoryId().equalsIgnoreCase("49"))
                    {
                        LLFundWellBeing1.setVisibility(View.GONE);

                    }else  if(OccationList.get(3).getCategoryId().equalsIgnoreCase("4") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("50"))
                    {
                        LLFundWellBeing2.setVisibility(View.GONE);

                    }else  if(OccationList.get(3).getCategoryId().equalsIgnoreCase("4") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("51"))
                    {
                        LLFundWellBeing3.setVisibility(View.GONE);

                    }else  if(OccationList.get(3).getCategoryId().equalsIgnoreCase("4") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("52"))
                    {
                        LLFundWellBeing4.setVisibility(View.GONE);

                    }else  if(OccationList.get(3).getCategoryId().equalsIgnoreCase("4") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("53"))
                    {
                        LLFundWellBeing5.setVisibility(View.GONE);

                    }else  if(OccationList.get(3).getCategoryId().equalsIgnoreCase("4") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("54"))
                    {
                        LLFundWellBeing6.setVisibility(View.GONE);

                    }else  if(OccationList.get(3).getCategoryId().equalsIgnoreCase("4") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("55"))
                    {
                        LLFundWellBeing7.setVisibility(View.GONE);


                    }else  if(OccationList.get(3).getCategoryId().equalsIgnoreCase("4") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("56"))
                    {

                        LLFundWellBeing8.setVisibility(View.GONE);

                    }else  if(OccationList.get(3).getCategoryId().equalsIgnoreCase("4") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("57"))
                    {
                        LLFundWellBeing9.setVisibility(View.GONE);

                    }

                    // --------------- planning Celebration --------------

                    if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") &&  SubOccationList.get(position).getCategoryId().equalsIgnoreCase("58"))
                    {
                        LLPlaningCelebration1.setVisibility(View.GONE);


                    }else  if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("59"))
                    {
                        LLPlaningCelebration2.setVisibility(View.GONE);

                    }
                    else  if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("60"))
                    {
                        LLPlaningCelebration3.setVisibility(View.GONE);
                    }
                    else  if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("61"))
                    {
                        LLPlaningCelebration4.setVisibility(View.GONE);
                    }
                    else  if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("62"))
                    {
                        LLPlaningCelebration5.setVisibility(View.GONE);
                    }
                    else  if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("63"))
                    {
                        LLPlaningCelebration6.setVisibility(View.GONE);

                    }
                    else  if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("64"))
                    {
                        LLPlaningCelebration7.setVisibility(View.GONE);

                    }
                    else  if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("65"))
                    {


                    }
                    else  if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("66"))
                    {

                    }
                    else  if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("67"))
                    {

                    }

                    // --------------- planning Vocation --------------

                    if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") &&  SubOccationList.get(position).getCategoryId().equalsIgnoreCase("68"))
                    {
                        LLPlanningVocation1.setVisibility(View.GONE);

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("69"))
                    {

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("70"))
                    {
                        LL_planning_vocation_2.setVisibility(View.GONE);

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("71"))
                    {

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("72"))
                    {
                        LL_planning_vocation_3.setVisibility(View.GONE);

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("73"))
                    {
                        LL_planning_vocation_4.setVisibility(View.GONE);
                    }

                    // --------------- Starting Business --------------

                    if(OccationList.get(6).getCategoryId().equalsIgnoreCase("7") &&  SubOccationList.get(position).getCategoryId().equalsIgnoreCase("74"))
                    {
                        LLShowroomsOffices1.setVisibility(View.GONE);

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("75"))
                    {
                        LLShowroomsOffices2.setVisibility(View.GONE);

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("76"))
                    {
                        Toast.makeText(CreateNormalAddActivity.this, "", Toast.LENGTH_SHORT).show();

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("77"))
                    {
                        LLShowroomsOffices3.setVisibility(View.GONE);

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("78"))
                    {
                        LLShowroomsOffices4.setVisibility(View.GONE);

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("79"))
                    {
                        LLShowroomsOffices5.setVisibility(View.GONE);

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("80"))
                    {
                        LLShowroomsOffices6.setVisibility(View.GONE);

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("81"))
                    {
                        LLShowroomsOffices7.setVisibility(View.GONE);

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("82"))
                    {
                        LLShowroomsOffices8.setVisibility(View.GONE);

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("83"))
                    {
                        LLShowroomsOffices8.setVisibility(View.GONE);

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("84"))
                    {

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("85"))
                    {

                    }

                    //------------------  Education And Career ------------------

                    if(OccationList.get(7).getCategoryId().equalsIgnoreCase("8") &&  SubOccationList.get(position).getCategoryId().equalsIgnoreCase("86"))
                    {
                        LLEducationAndCareer1.setVisibility(View.GONE);

                    }else  if(OccationList.get(7).getCategoryId().equalsIgnoreCase("8") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("87"))
                    {
                        LLEducationAndCareer2.setVisibility(View.GONE);

                    }else  if(OccationList.get(7).getCategoryId().equalsIgnoreCase("8") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("88"))
                    {
                        LLEducationAndCareer3.setVisibility(View.GONE);

                    }else  if(OccationList.get(7).getCategoryId().equalsIgnoreCase("8") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("89"))
                    {
                        LLEducationAndCareer4.setVisibility(View.GONE);

                    }else  if(OccationList.get(7).getCategoryId().equalsIgnoreCase("8") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("90"))
                    {
                        LLEducationAndCareer5.setVisibility(View.GONE);

                    }


                    // ------------------------ Moving or Relocating -------------------------

                    if(OccationList.get(8).getCategoryId().equalsIgnoreCase("9") &&  SubOccationList.get(position).getCategoryId().equalsIgnoreCase("91"))
                    {

                    }else  if(OccationList.get(8).getCategoryId().equalsIgnoreCase("9") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("92"))
                    {
                        LLMovingRelocating1.setVisibility(View.GONE);

                    }else  if(OccationList.get(8).getCategoryId().equalsIgnoreCase("9") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("93"))
                    {
                        LLMovingRelocating2.setVisibility(View.GONE);

                    }else  if(OccationList.get(8).getCategoryId().equalsIgnoreCase("9") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("94"))
                    {
                        // Toast.makeText(CreateNormalAddActivity.this, "Govt.", Toast.LENGTH_SHORT).show();

                    }else  if(OccationList.get(8).getCategoryId().equalsIgnoreCase("9") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("95"))
                    {
                        // Toast.makeText(CreateNormalAddActivity.this, "Nursing.", Toast.LENGTH_SHORT).show();

                    }else  if(OccationList.get(8).getCategoryId().equalsIgnoreCase("9") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("96"))
                    {
                        //  Toast.makeText(CreateNormalAddActivity.this, "Services.", Toast.LENGTH_SHORT).show();

                    }else  if(OccationList.get(8).getCategoryId().equalsIgnoreCase("9") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("97"))
                    {
                        LLMovingRelocating3.setVisibility(View.GONE);
                    }


                }else if(count <5) {
                    img_check.setVisibility(View.VISIBLE);
                    model.setChecked(true);
                    count = count + 1;

                    if(count == 1){
                        catName = "1 Items Selected";
                    }else  if(count == 2){
                        catName = "2 Items Selected";
                    }else  if(count == 3){
                        catName = "3 Items Selected";
                    }else if(count == 4){
                        catName = "4 Items Selected";
                    }else if(count == 5){
                        catName =  "5 Items Selected";
                    }

                    txtcategory.setText(catName);

                    //------------------ Getting Married Visible -----------------------

                    if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") &&  SubOccationList.get(position).getCategoryId().equalsIgnoreCase("10"))
                    {
                        LL_Restaurants_Catering.setVisibility(View.VISIBLE);

                        FuncSpinner(GettingMarried_type_1,gettingMarriedSpinner1);

                    }else  if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("11"))
                    {
                        LL_Fashion.setVisibility(View.VISIBLE);

                        FuncSpinner(GettingMarried_type_2,spinnerGettingMarriedSpinner2);
                        FuncSpinner(GettingMarried_type_3,spinnerGettingMarriedSpinner3);
                        FuncSpinner(GettingMarried_type_4,spinnerGettingMarriedSpinner4);

                    }else if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("12"))
                    {
                        LL_Flowers.setVisibility(View.VISIBLE);
                        FuncSpinner(GettingMarried_type_5,spinnerGettingMarriedSpinner5);


                    }else if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("13"))
                    {
                        LL_Photography.setVisibility(View.VISIBLE);
                        FuncSpinner(GettingMarried_type_6,spinnerGettingMarriedSpinner6);

                    }else if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("14"))
                    {
                        LL_Motors.setVisibility(View.VISIBLE);
                        FuncSpinner(GettingMarried_type_7,spinnerGettingMarriedSpinner7);
                        FuncSpinner(GettingMarried_type_8,spinnerGettingMarriedSpinner8);
                        FuncSpinner(GettingMarried_type_9,spinnerGettingMarriedSpinner9);
                        FuncSpinner(GettingMarried_type_10,spinnerGettingMarriedSpinner10);
                        FuncSpinner(GettingMarried_type_11,spinnerGettingMarriedSpinner11);

                    }else  if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("15"))
                    {
                        LL_Hotels_Resortd.setVisibility(View.VISIBLE);

                        FuncSpinner(GettingMarried_type_12,spinnerGettingMarriedSpinner12);
                        FuncSpinner(GettingMarried_type_13,spinnerGettingMarriedSpinner13);
                        FuncSpinner(GettingMarried_type_14,spinnerGettingMarriedSpinner14);
                        FuncSpinner(GettingMarried_type_15,spinnerGettingMarriedSpinner15);
                        FuncSpinner(GettingMarried_type_16,spinnerGettingMarriedSpinner16);
                        FuncSpinner(GettingMarried_type_17,spinnerGettingMarriedSpinner17);
                        FuncSpinner(GettingMarried_type_18,spinnerGettingMarriedSpinner18);


                    }else if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("16"))
                    {
                        LL_Properties.setVisibility(View.VISIBLE);
                        FuncSpinner(GettingMarried_type_19,spinnerGettingMarriedSpinner19);
                        FuncSpinner(GettingMarried_type_21,spinnerGettingMarriedSpinner21);
                        FuncSpinner(GettingMarried_type_22,spinnerGettingMarriedSpinner22);
                        FuncSpinner(GettingMarried_type_23,spinnerGettingMarriedSpinner23);
                        FuncSpinner(GettingMarried_type_24,spinnerGettingMarriedSpinner24);
                        FuncSpinner(GettingMarried_type_25,spinnerGettingMarriedSpinner25);
                        FuncSpinner(GettingMarried_type_26,spinnerGettingMarriedSpinner26);


                    }else  if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("17"))
                    {
                        LL_Properties.setVisibility(View.VISIBLE);
                        FuncSpinner(GettingMarried_type_19,spinnerGettingMarriedSpinner19);
                        FuncSpinner(GettingMarried_type_21,spinnerGettingMarriedSpinner21);
                        FuncSpinner(GettingMarried_type_22,spinnerGettingMarriedSpinner22);
                        FuncSpinner(GettingMarried_type_23,spinnerGettingMarriedSpinner23);
                        FuncSpinner(GettingMarried_type_24,spinnerGettingMarriedSpinner24);
                        FuncSpinner(GettingMarried_type_25,spinnerGettingMarriedSpinner25);
                        FuncSpinner(GettingMarried_type_26,spinnerGettingMarriedSpinner26);

                    }else  if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("18"))
                    {
                        LL_Beauty_SPA.setVisibility(View.VISIBLE);
                        FuncSpinner(GettingMarried_type_27,spinnerGettingMarriedSpinner27);

                    }else  if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("19"))
                    {

                        Toast.makeText(CreateNormalAddActivity.this, "No Service,cleaning", Toast.LENGTH_SHORT).show();

                    }else  if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("20"))
                    {
                        LL_Home_Garden.setVisibility(View.VISIBLE);
                        FuncSpinner(GettingMarried_type_27,spinnerGettingMarriedSpinner27);

                    }else  if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("21"))
                    {

                        LL_Events_Planners.setVisibility(View.VISIBLE);
                        FuncSpinner(GettingMarried_type_28,spinnerGettingMarriedSpinner28);
                        FuncSpinner(GettingMarried_type_29,spinnerGettingMarriedSpinner29);
                        FuncSpinner(GettingMarried_type_30,spinnerGettingMarriedSpinner30);

                    }else  if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("22"))
                    {
                        Toast.makeText(CreateNormalAddActivity.this, "No Govt.Data", Toast.LENGTH_SHORT).show();

                    }else   if(OccationList.get(0).getCategoryId().equalsIgnoreCase("1") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("23"))
                    {
                        LL_Home_Appliances.setVisibility(View.VISIBLE);
                    }


                    //------------------ Expecting a baby ---------------------------
                    if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") &&  SubOccationList.get(position).getCategoryId().equalsIgnoreCase("24"))
                    {
                        LLExpectingABaby1.setVisibility(View.VISIBLE);
                        FuncSpinner(expectingABaby_type_1,expectingABabySpinner1);
                        FuncSpinner(expectingABaby_type_2,expectingABabySpinner2);
                        FuncSpinner(expectingABaby_type_3,expectingABabySpinner3);


                    }else  if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("25"))
                    {
                        LLExpectingABaby2.setVisibility(View.VISIBLE);
                        FuncSpinner(expectingABaby_type_4,expectingABabySpinner4);
                        FuncSpinner(expectingABaby_type_5,expectingABabySpinner5);

                    }else   if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("26"))
                    {
                        Toast.makeText(CreateNormalAddActivity.this, "No Data Child Care", Toast.LENGTH_SHORT).show();

                    }else   if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("27"))
                    {
                        Toast.makeText(CreateNormalAddActivity.this, "No Data Planing Areas", Toast.LENGTH_SHORT).show();

                    }else   if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("28"))
                    {
                        LLExpectingABaby3.setVisibility(View.VISIBLE);

                        FuncSpinner(expectingABaby_type_6,expectingABabySpinner6);


                    }else   if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("29"))
                    {
                        LLExpectingABaby4.setVisibility(View.VISIBLE);
                        FuncSpinner(expectingABaby_type_7,expectingABabySpinner7);


                    }else   if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("30"))
                    {
                        LLExpectingABaby5.setVisibility(View.VISIBLE);
                        FuncSpinner(expectingABaby_type_8,expectingABabySpinner8);


                    }else   if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("31"))
                    {
                        Toast.makeText(CreateNormalAddActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();

                    } else  if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("32"))
                    {
                        LLExpectingABaby6.setVisibility(View.VISIBLE);
                        FuncSpinner(expectingABaby_type_9,expectingABabySpinner9);


                    }else   if(OccationList.get(1).getCategoryId().equalsIgnoreCase("2") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("33"))
                    {
                        LLExpectingABaby7.setVisibility(View.VISIBLE);
                        FuncSpinner(expectingABaby_type_10,expectingABabySpinner10);
                        FuncSpinner(expectingABaby_type_11,expectingABabySpinner11);
                        FuncSpinner(expectingABaby_type_12,expectingABabySpinner12);
                    }




                    //------------------Shopping ----------------------------
                    if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") &&  SubOccationList.get(position).getCategoryId().equalsIgnoreCase("34"))
                    {
                        LLShopping1.setVisibility(View.VISIBLE);
                        FuncSpinner(Shoppin_type_1,ShoppingSpinner1);

                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("35"))
                    {
                        LLShopping2.setVisibility(View.VISIBLE);

                        FuncSpinner(Shoppin_type_2,ShoppingSpinner2);
                        FuncSpinner(Shoppin_type_4,ShoppingSpinner4);
                        FuncSpinner(Shoppin_type_5,ShoppingSpinner5);


                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("36"))
                    {
                        LLShopping3.setVisibility(View.VISIBLE);

                        FuncSpinner(Shoppin_type_6,ShoppingSpinner6);
                        FuncSpinner(Shoppin_type_7,ShoppingSpinner7);
                        FuncSpinner(Shoppin_type_8,ShoppingSpinner8);


                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("37"))
                    {
                        LLShopping4.setVisibility(View.VISIBLE);

                        FuncSpinner(Shoppin_type_9,ShoppingSpinner9);
                        FuncSpinner(Shoppin_type_10,ShoppingSpinner10);
                        FuncSpinner(Shoppin_type_11,ShoppingSpinner11);


                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("38"))
                    {
                        Toast.makeText(CreateNormalAddActivity.this, "No Data Pets", Toast.LENGTH_SHORT).show();

                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("39"))
                    {
                        LLShopping5.setVisibility(View.VISIBLE);


                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("40"))
                    {
                        LLShopping6.setVisibility(View.VISIBLE);

                        FuncSpinner(Shoppin_type_12,ShoppingSpinner12);
                        FuncSpinner(Shoppin_type_13,ShoppingSpinner13);


                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("41"))
                    {
                        LLShopping7.setVisibility(View.VISIBLE);

                        FuncSpinner(Shoppin_type_14,ShoppingSpinner14);
                        FuncSpinner(Shoppin_type_15,ShoppingSpinner15);



                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("42"))
                    {
                        LLShopping8.setVisibility(View.VISIBLE);


                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("43"))
                    {
                        LLShopping9.setVisibility(View.VISIBLE);


                        FuncSpinner(Shoppin_type_16,ShoppingSpinner16);
                        FuncSpinner(Shoppin_type_17,ShoppingSpinner17);
                        FuncSpinner(Shoppin_type_18,ShoppingSpinner18);
                        FuncSpinner(Shoppin_type_19,ShoppingSpinner19);
                        FuncSpinner(Shoppin_type_20,ShoppingSpinner20);


                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("44"))
                    {
                        Toast.makeText(CreateNormalAddActivity.this, "No Data Travel", Toast.LENGTH_SHORT).show();

                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("45"))
                    {
                        LLShopping10.setVisibility(View.VISIBLE);

                        FuncSpinner(Shoppin_type_21,ShoppingSpinner21);
                        FuncSpinner(Shoppin_type_22,ShoppingSpinner22);


                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("46"))
                    {
                        LLShopping11.setVisibility(View.VISIBLE);

                        FuncSpinner(Shoppin_type_23,ShoppingSpinner23);
                        FuncSpinner(Shoppin_type_24,ShoppingSpinner24);

                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("47"))
                    {
                        LLShopping12.setVisibility(View.VISIBLE);

                        FuncSpinner(Shoppin_type_25,ShoppingSpinner25);
                        FuncSpinner(Shoppin_type_26,ShoppingSpinner26);
                        FuncSpinner(Shoppin_type_27,ShoppingSpinner27);


                    }else  if(OccationList.get(2).getCategoryId().equalsIgnoreCase("3") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("48"))
                    {
                        LLShopping13.setVisibility(View.VISIBLE);

                        FuncSpinner(Shoppin_type_28,ShoppingSpinner28);
                        FuncSpinner(Shoppin_type_29,ShoppingSpinner29);
                        FuncSpinner(Shoppin_type_30,ShoppingSpinner30);


                    }


                    // --------------- Fund Well Being --------------

                    if(OccationList.get(3).getCategoryId().equalsIgnoreCase("4") &&  SubOccationList.get(position).getCategoryId().equalsIgnoreCase("49"))
                    {
                        //LLFundWellBeing1.setVisibility(View.VISIBLE);

                        Toast.makeText(CreateNormalAddActivity.this, "No Data", Toast.LENGTH_SHORT).show();


                    }else  if(OccationList.get(3).getCategoryId().equalsIgnoreCase("4") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("50"))
                    {
                        LLFundWellBeing2.setVisibility(View.VISIBLE);

                        FuncSpinner(fundWellBeing_type_4,fundWellBeingSpinner4);


                    }else  if(OccationList.get(3).getCategoryId().equalsIgnoreCase("4") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("51"))
                    {
                        LLFundWellBeing3.setVisibility(View.VISIBLE);

                        FuncSpinner(fundWellBeing_type_5,fundWellBeingSpinner5);



                    }else  if(OccationList.get(3).getCategoryId().equalsIgnoreCase("4") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("52"))
                    {
                        LLFundWellBeing4.setVisibility(View.VISIBLE);

                    }else  if(OccationList.get(3).getCategoryId().equalsIgnoreCase("4") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("53"))
                    {
                        LLFundWellBeing5.setVisibility(View.VISIBLE);

                        FuncSpinner(fundWellBeing_type_6,fundWellBeingSpinner6);
                        FuncSpinner(fundWellBeing_type_7,fundWellBeingSpinner7);




                    }else  if(OccationList.get(3).getCategoryId().equalsIgnoreCase("4") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("54"))
                    {
                        LLFundWellBeing6.setVisibility(View.VISIBLE);

                        FuncSpinner(fundWellBeing_type_8,fundWellBeingSpinner8);




                    }else  if(OccationList.get(3).getCategoryId().equalsIgnoreCase("4") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("55"))
                    {
                        LLFundWellBeing7.setVisibility(View.VISIBLE);

                        FuncSpinner(fundWellBeing_type_9,fundWellBeingSpinner9);



                    }else  if(OccationList.get(3).getCategoryId().equalsIgnoreCase("4") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("56"))
                    {
                        LLFundWellBeing8.setVisibility(View.VISIBLE);

                        FuncSpinner(fundWellBeing_type_10,fundWellBeingSpinner10);
                        FuncSpinner(fundWellBeing_type_11,fundWellBeingSpinner11);


                    }else  if(OccationList.get(3).getCategoryId().equalsIgnoreCase("4") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("57"))
                    {
                        LLFundWellBeing9.setVisibility(View.VISIBLE);

                        FuncSpinner(fundWellBeing_type_12,fundWellBeingSpinner12);
                        FuncSpinner(fundWellBeing_type_13,fundWellBeingSpinner13);
                        FuncSpinner(fundWellBeing_type_14,fundWellBeingSpinner14);
                        FuncSpinner(fundWellBeing_type_15,fundWellBeingSpinner15);
                        FuncSpinner(fundWellBeing_type_16,fundWellBeingSpinner16);
                        FuncSpinner(fundWellBeing_type_17,fundWellBeingSpinner17);
                        FuncSpinner(fundWellBeing_type_18,fundWellBeingSpinner18);
                        FuncSpinner(fundWellBeing_type_19,fundWellBeingSpinner19);
                        FuncSpinner(fundWellBeing_type_20,fundWellBeingSpinner20);
                        FuncSpinner(fundWellBeing_type_21,fundWellBeingSpinner21);

                    }

                    // --------------- planning Celebration --------------

                    if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") &&  SubOccationList.get(position).getCategoryId().equalsIgnoreCase("58"))
                    {
                        LLPlaningCelebration1.setVisibility(View.VISIBLE);

                    }else  if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("59"))
                    {
                        LLPlaningCelebration2.setVisibility(View.VISIBLE);

                        FuncSpinner(planningCelebration_type_1,planningCelebrationSpinner1);

                    }
                    else  if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("60"))
                    {
                        LLPlaningCelebration3.setVisibility(View.VISIBLE);

                        FuncSpinner(planningCelebration_type_1,planningCelebrationSpinner2);
                        FuncSpinner(planningCelebration_type_1,planningCelebrationSpinner3);
                        FuncSpinner(planningCelebration_type_1,planningCelebrationSpinner4);
                        FuncSpinner(planningCelebration_type_1,planningCelebrationSpinner5);
                        FuncSpinner(planningCelebration_type_1,planningCelebrationSpinner6);
                        FuncSpinner(planningCelebration_type_1,planningCelebrationSpinner7);
                        FuncSpinner(planningCelebration_type_1,planningCelebrationSpinner8);
                        FuncSpinner(planningCelebration_type_1,planningCelebrationSpinner9);
                        FuncSpinner(planningCelebration_type_1,planningCelebrationSpinner10);

                    }
                    else  if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("61"))
                    {
                        LLPlaningCelebration4.setVisibility(View.VISIBLE);

                        FuncSpinner(planningCelebration_type_1,planningCelebrationSpinner11);

                    }
                    else  if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("62"))
                    {
                        LLPlaningCelebration5.setVisibility(View.VISIBLE);

                        FuncSpinner(planningCelebration_type_1,planningCelebrationSpinner12);


                    }
                    else  if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("63"))
                    {
                        LLPlaningCelebration6.setVisibility(View.VISIBLE);

                        FuncSpinner(planningCelebration_type_1,planningCelebrationSpinner13);


                    }
                    else  if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("64"))
                    {
                        LLPlaningCelebration7.setVisibility(View.VISIBLE);

                        FuncSpinner(planningCelebration_type_1,planningCelebrationSpinner14);
                        FuncSpinner(planningCelebration_type_1,planningCelebrationSpinner15);
                        FuncSpinner(planningCelebration_type_1,planningCelebrationSpinner16);

                    }
                    else  if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("65"))
                    {


                    }
                    else  if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("66"))
                    {

                    }
                    else  if(OccationList.get(4).getCategoryId().equalsIgnoreCase("5") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("67"))
                    {

                    }

                    // --------------- planning Vocation --------------

                    if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") &&  SubOccationList.get(position).getCategoryId().equalsIgnoreCase("68"))
                    {
                        LLPlanningVocation1.setVisibility(View.VISIBLE);

                        FuncSpinner(planningVocation_type_1,planningVocationSpinner1);
                        FuncSpinner(planningVocation_type_2,planningVocationSpinner2);
                        FuncSpinner(planningVocation_type_3,planningVocationSpinner3);
                        FuncSpinner(planningVocation_type_4,planningVocationSpinner4);
                        FuncSpinner(planningVocation_type_5,planningVocationSpinner5);


                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("69"))
                    {

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("70"))
                    {
                        LL_planning_vocation_2.setVisibility(View.VISIBLE);

                        FuncSpinner(planningVocation_type_6,planningVocationSpinner6);
                        FuncSpinner(planningVocation_type_7,planningVocationSpinner7);
                        FuncSpinner(planningVocation_type_8,planningVocationSpinner8);
                        FuncSpinner(planningVocation_type_9,planningVocationSpinner9);
                        FuncSpinner(planningVocation_type_10,planningVocationSpinner10);
                        FuncSpinner(planningVocation_type_11,planningVocationSpinner11);
                        FuncSpinner(planningVocation_type_12,planningVocationSpinner12);
                        FuncSpinner(planningVocation_type_13,planningVocationSpinner13);
                        FuncSpinner(planningVocation_type_14,planningVocationSpinner14);
                        FuncSpinner(planningVocation_type_15,planningVocationSpinner15);


                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("71"))
                    {

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("72"))
                    {
                        LL_planning_vocation_3.setVisibility(View.VISIBLE);

                        FuncSpinner(planningVocation_type_16,planningVocationSpinner16);
                        FuncSpinner(planningVocation_type_17,planningVocationSpinner17);
                        FuncSpinner(planningVocation_type_18,planningVocationSpinner18);


                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("73"))
                    {
                        LL_planning_vocation_4.setVisibility(View.VISIBLE);

                        FuncSpinner(planningVocation_type_19,planningVocationSpinner19);
                    }




                    // --------------- Starting Business --------------

                    if(OccationList.get(6).getCategoryId().equalsIgnoreCase("7") &&  SubOccationList.get(position).getCategoryId().equalsIgnoreCase("74"))
                    {
                        LLShowroomsOffices1.setVisibility(View.VISIBLE);

                        FuncSpinner(startingBusiness_type_1,startingBusinessSpinner1);
                        FuncSpinner(startingBusiness_type_2,startingBusinessSpinner2);
                        FuncSpinner(startingBusiness_type_3,startingBusinessSpinner3);


                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("75"))
                    {
                        LLShowroomsOffices2.setVisibility(View.VISIBLE);

                        FuncSpinner(startingBusiness_type_4,startingBusinessSpinner4);
                        FuncSpinner(startingBusiness_type_5,startingBusinessSpinner5);


                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("76"))
                    {
                        Toast.makeText(CreateNormalAddActivity.this, "", Toast.LENGTH_SHORT).show();

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("77"))
                    {
                        LLShowroomsOffices3.setVisibility(View.VISIBLE);

                        FuncSpinner(startingBusiness_type_6,startingBusinessSpinner6);
                        FuncSpinner(startingBusiness_type_7,startingBusinessSpinner7);
                        FuncSpinner(startingBusiness_type_8,startingBusinessSpinner8);
                        FuncSpinner(startingBusiness_type_9,startingBusinessSpinner9);
                        FuncSpinner(startingBusiness_type_10,startingBusinessSpinner10);
                        FuncSpinner(startingBusiness_type_11,startingBusinessSpinner11);


                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("78"))
                    {
                        LLShowroomsOffices4.setVisibility(View.VISIBLE);

                        FuncSpinner(startingBusiness_type_12,startingBusinessSpinner12);
                        FuncSpinner(startingBusiness_type_13,startingBusinessSpinner13);


                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("79"))
                    {
                        LLShowroomsOffices5.setVisibility(View.VISIBLE);

                        FuncSpinner(startingBusiness_type_14,startingBusinessSpinner14);
                        FuncSpinner(startingBusiness_type_15,startingBusinessSpinner15);
                        FuncSpinner(startingBusiness_type_16,startingBusinessSpinner16);
                        FuncSpinner(startingBusiness_type_17,startingBusinessSpinner17);
                        FuncSpinner(startingBusiness_type_18,startingBusinessSpinner18);

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("80"))
                    {
                        LLShowroomsOffices6.setVisibility(View.VISIBLE);

                        FuncSpinner(startingBusiness_type_19,startingBusinessSpinner19);



                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("81"))
                    {
                        LLShowroomsOffices7.setVisibility(View.VISIBLE);

                        FuncSpinner(startingBusiness_type_20,startingBusinessSpinner20);


                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("82"))
                    {
                        LLShowroomsOffices8.setVisibility(View.VISIBLE);

                        FuncSpinner(startingBusiness_type_21,startingBusinessSpinner21);


                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("83"))
                    {
                        LLShowroomsOffices8.setVisibility(View.VISIBLE);

                        FuncSpinner(startingBusiness_type_21,startingBusinessSpinner22);

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("84"))
                    {

                    }else  if(OccationList.get(5).getCategoryId().equalsIgnoreCase("6") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("85"))
                    {

                    }

                    //------------------  Education And Career ------------------

                    if(OccationList.get(7).getCategoryId().equalsIgnoreCase("8") &&  SubOccationList.get(position).getCategoryId().equalsIgnoreCase("86"))
                    {
                        LLEducationAndCareer1.setVisibility(View.VISIBLE);

                        FuncSpinner(educationCareerSpinner_type_1,educationCareerSpinner1);
                        FuncSpinner(educationCareerSpinner_type_2,educationCareerSpinner2);
                        FuncSpinner(educationCareerSpinner_type_3,educationCareerSpinner3);
                        FuncSpinner(educationCareerSpinner_type_4,educationCareerSpinner4);
                        FuncSpinner(educationCareerSpinner_type_5,educationCareerSpinner5);
                        FuncSpinner(educationCareerSpinner_type_6,educationCareerSpinner6);


                    }else  if(OccationList.get(7).getCategoryId().equalsIgnoreCase("8") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("87"))
                    {
                        LLEducationAndCareer2.setVisibility(View.VISIBLE);

                        FuncSpinner(educationCareerSpinner_type_7,educationCareerSpinner7);
                        FuncSpinner(educationCareerSpinner_type_8,educationCareerSpinner8);

                    }else  if(OccationList.get(7).getCategoryId().equalsIgnoreCase("8") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("88"))
                    {
                        LLEducationAndCareer3.setVisibility(View.VISIBLE);

                    }else  if(OccationList.get(7).getCategoryId().equalsIgnoreCase("8") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("89"))
                    {
                        LLEducationAndCareer4.setVisibility(View.VISIBLE);

                    }else  if(OccationList.get(7).getCategoryId().equalsIgnoreCase("8") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("90"))
                    {
                        LLEducationAndCareer5.setVisibility(View.VISIBLE);

                    }


                    // ------------------------ Moving or Relocating -------------------------

                    if(OccationList.get(8).getCategoryId().equalsIgnoreCase("9") &&  SubOccationList.get(position).getCategoryId().equalsIgnoreCase("91"))
                    {

                    }else  if(OccationList.get(8).getCategoryId().equalsIgnoreCase("9") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("92"))
                    {
                        LLMovingRelocating1.setVisibility(View.VISIBLE);

                        FuncSpinner(MovingRelocatingSpinner_type_1,MovingRelocatingSpinner1);
                        FuncSpinner(MovingRelocatingSpinner_type_3,MovingRelocatingSpinner3);
                        FuncSpinner(MovingRelocatingSpinner_type_4,MovingRelocatingSpinner4);
                        FuncSpinner(MovingRelocatingSpinner_type_5,MovingRelocatingSpinner5);
                        FuncSpinner(MovingRelocatingSpinner_type_6,MovingRelocatingSpinner6);
                        FuncSpinner(MovingRelocatingSpinner_type_7,MovingRelocatingSpinner7);
                        FuncSpinner(MovingRelocatingSpinner_type_8,MovingRelocatingSpinner8);
                        FuncSpinner(MovingRelocatingSpinner_type_9,MovingRelocatingSpinner9);
                        FuncSpinner(MovingRelocatingSpinner_type_10,MovingRelocatingSpinner10);
                        FuncSpinner(MovingRelocatingSpinner_type_11,MovingRelocatingSpinner11);



                    }else  if(OccationList.get(8).getCategoryId().equalsIgnoreCase("9") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("93"))
                    {
                        LLMovingRelocating2.setVisibility(View.VISIBLE);

                        FuncSpinner(MovingRelocatingSpinner_type_12,MovingRelocatingSpinner12);
                        FuncSpinner(MovingRelocatingSpinner_type_13,MovingRelocatingSpinner13);
                        FuncSpinner(MovingRelocatingSpinner_type_14,MovingRelocatingSpinner14);
                        FuncSpinner(MovingRelocatingSpinner_type_15,MovingRelocatingSpinner15);
                        FuncSpinner(MovingRelocatingSpinner_type_16,MovingRelocatingSpinner16);
                        FuncSpinner(MovingRelocatingSpinner_type_17,MovingRelocatingSpinner17);
                        FuncSpinner(MovingRelocatingSpinner_type_18,MovingRelocatingSpinner18);
                        FuncSpinner(MovingRelocatingSpinner_type_19,MovingRelocatingSpinner19);
                        FuncSpinner(MovingRelocatingSpinner_type_20,MovingRelocatingSpinner20);
                        FuncSpinner(MovingRelocatingSpinner_type_21,MovingRelocatingSpinner21);


                    }else  if(OccationList.get(8).getCategoryId().equalsIgnoreCase("9") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("94"))
                    {
                       // Toast.makeText(CreateNormalAddActivity.this, "Govt.", Toast.LENGTH_SHORT).show();

                    }else  if(OccationList.get(8).getCategoryId().equalsIgnoreCase("9") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("95"))
                    {
                       // Toast.makeText(CreateNormalAddActivity.this, "Nursing.", Toast.LENGTH_SHORT).show();

                    }else  if(OccationList.get(8).getCategoryId().equalsIgnoreCase("9") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("96"))
                    {
                      //  Toast.makeText(CreateNormalAddActivity.this, "Services.", Toast.LENGTH_SHORT).show();

                    }else  if(OccationList.get(8).getCategoryId().equalsIgnoreCase("9") && SubOccationList.get(position).getCategoryId().equalsIgnoreCase("97"))
                    {
                        LLMovingRelocating3.setVisibility(View.VISIBLE);

                        FuncSpinner(MovingRelocatingSpinner_type_22,MovingRelocatingSpinner22);
                        FuncSpinner(MovingRelocatingSpinner_type_23,MovingRelocatingSpinner23);
                        FuncSpinner(MovingRelocatingSpinner_type_24,MovingRelocatingSpinner24);
                        FuncSpinner(MovingRelocatingSpinner_type_25,MovingRelocatingSpinner25);
                        FuncSpinner(MovingRelocatingSpinner_type_26,MovingRelocatingSpinner26);
                        FuncSpinner(MovingRelocatingSpinner_type_27,MovingRelocatingSpinner27);
                        FuncSpinner(MovingRelocatingSpinner_type_28,MovingRelocatingSpinner28);
                        FuncSpinner(MovingRelocatingSpinner_type_29,MovingRelocatingSpinner29);
                        FuncSpinner(MovingRelocatingSpinner_type_30,MovingRelocatingSpinner30);

                    }


                }else{

                    Toast.makeText(CreateNormalAddActivity.this, "you can only select 5 categories.", Toast.LENGTH_SHORT).show();
                }


            }
        });

        RR_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isCategorySection)
                {
                    recycler_view_category.setVisibility(View.GONE);

                    isCategorySection = false;

                }else
                {
                    recycler_view_category.setVisibility(View.VISIBLE);

                    isCategorySection = true;

                }

            }
        });

    }

    private void findview() {

        txtcategory=(TextView) findViewById(R.id.txtcategory);
        RR_category=(RelativeLayout) findViewById(R.id.RR_category);
        recycler_view_category = (RecyclerView) findViewById(R.id.recycler_view_category);
        recycler_view_upload_image_normalAdd = (RecyclerView) findViewById(R.id.recycler_view_upload_image_normalAdd);
        submit_btn_add = (Button) findViewById(R.id.submit_btn_add);
        txt_upload = (TextView) findViewById(R.id.txt_upload);
        txt_listing_expiration = (TextView) findViewById(R.id.txt_listing_expiration);
        txt_offer_valitity_start = (TextView) findViewById(R.id.txt_offer_valitity_start);
        txt_offer_end = (TextView) findViewById(R.id.txt_offer_end);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);

        spin = (Spinner) findViewById(R.id.spinner);
        RR_upload_image = (RelativeLayout) findViewById(R.id.RR_upload_image);
        RR_video_upload = (RelativeLayout) findViewById(R.id.RR_video_upload);
        /*     spin2 = (Spinner) findViewById(R.id.spinner2);*/
        text_career=(TextView) findViewById(R.id.text_career);

        //Getting Married
        LL_categories_one  = (LinearLayout) findViewById(R.id.LL_categories_one);
        LL_Restaurants_Catering  = (LinearLayout) findViewById(R.id.LL_Restaurants_Catering);
        LL_Fashion  = (LinearLayout) findViewById(R.id.LL_Fashion);
        LL_Flowers  = (LinearLayout) findViewById(R.id.LL_Flowers);
        LL_Photography  = (LinearLayout) findViewById(R.id.LL_Photography);
        LL_Motors  = (LinearLayout) findViewById(R.id.LL_Motors);
        LL_Hotels_Resortd  = (LinearLayout) findViewById(R.id.LL_Hotels_Resortd);
        LL_Properties  = (LinearLayout) findViewById(R.id.LL_Properties);
        LL_Beauty_SPA  = (LinearLayout) findViewById(R.id.LL_Beauty_SPA);
        LL_Home_Garden  = (LinearLayout) findViewById(R.id.LL_Home_Garden);
        LL_Events_Planners  = (LinearLayout) findViewById(R.id.LL_Events_Planners);
        LL_Home_Appliances  = (LinearLayout) findViewById(R.id.LL_Home_Appliances);


        ed_title  = (EditText) findViewById(R.id.ed_title);
        ed_contact_email  = (EditText) findViewById(R.id.ed_contact_email);
        ed_keyword  = (EditText) findViewById(R.id.ed_keyword);
        edt_description  = (EditText) findViewById(R.id.edt_description);
        ed_youtube_link  = (EditText) findViewById(R.id.ed_youtube_link);
        edt_listing_price_ads  = (EditText) findViewById(R.id.edt_listing_price_ads);
        edt_cmny_name  = (EditText) findViewById(R.id.edt_cmny_name);
        edt_webside  = (EditText) findViewById(R.id.edt_webside);
        edt_specific  = (EditText) findViewById(R.id.edt_specific);
        Offer_Detail  = (EditText) findViewById(R.id.Offer_Detail);
        edt_Condition  = (EditText) findViewById(R.id.edt_Condition);
        edt_Location  = (EditText) findViewById(R.id.edt_Location);
       // edt_Country  = (EditText) findViewById(R.id.edt_Country);
       // edt_State  = (EditText) findViewById(R.id.edt_State);
        //edt_City  = (EditText) findViewById(R.id.edt_City);
        //edt_Address  = (EditText) findViewById(R.id.edt_Address);
        ed_contact_numbetr  = (EditText) findViewById(R.id.ed_contact_numbetr);
        checkbox  = (CheckBox) findViewById(R.id.checkbox);

        // -------------- Getting Married -----------
        gettingMarriedSpinner1 = (Spinner)findViewById( R.id.getting_married_spinner1 );
        spinnerGettingMarriedSpinner2 = (Spinner)findViewById( R.id.spinner_getting_married_spinner2 );
        spinnerGettingMarriedSpinner3 = (Spinner)findViewById( R.id.spinner_getting_married_spinner3 );
        spinnerGettingMarriedSpinner4 = (Spinner)findViewById( R.id.spinner_getting_married_spinner4 );
        spinnerGettingMarriedSpinner5 = (Spinner)findViewById( R.id.spinner_getting_married_spinner5 );
        spinnerGettingMarriedSpinner6 = (Spinner)findViewById( R.id.spinner_getting_married_spinner6 );
        spinnerGettingMarriedSpinner7 = (Spinner)findViewById( R.id.spinner_getting_married_spinner7 );
        spinnerGettingMarriedSpinner8 = (Spinner)findViewById( R.id.spinner_getting_married_spinner8 );
        spinnerGettingMarriedSpinner9 = (Spinner)findViewById( R.id.spinner_getting_married_spinner9 );
        spinnerGettingMarriedSpinner10 = (Spinner)findViewById( R.id.spinner_getting_married_spinner10 );
        spinnerGettingMarriedSpinner11 = (Spinner)findViewById( R.id.spinner_getting_married_spinner11 );
        spinnerGettingMarriedSpinner12 = (Spinner)findViewById( R.id.spinner_getting_married_spinner12 );
        spinnerGettingMarriedSpinner13 = (Spinner)findViewById( R.id.spinner_getting_married_spinner13 );
        spinnerGettingMarriedSpinner14 = (Spinner)findViewById( R.id.spinner_getting_married_spinner14 );
        spinnerGettingMarriedSpinner15 = (Spinner)findViewById( R.id.spinner_getting_married_spinner15 );
        spinnerGettingMarriedSpinner16 = (Spinner)findViewById( R.id.spinner_getting_married_spinner16 );
        spinnerGettingMarriedSpinner17 = (Spinner)findViewById( R.id.spinner_getting_married_spinner17 );
        spinnerGettingMarriedSpinner18 = (Spinner)findViewById( R.id.spinner_getting_married_spinner18 );
        spinnerGettingMarriedSpinner19 = (Spinner)findViewById( R.id.spinner_getting_married_spinner19 );
        spinnerGettingMarriedSpinner20 = (Spinner)findViewById( R.id.spinner_getting_married_spinner20 );
        spinnerGettingMarriedSpinner21 = (Spinner)findViewById( R.id.spinner_getting_married_spinner21 );
        spinnerGettingMarriedSpinner22 = (Spinner)findViewById( R.id.spinner_getting_married_spinner22 );
        spinnerGettingMarriedSpinner23 = (Spinner)findViewById( R.id.spinner_getting_married_spinner23 );
        spinnerGettingMarriedSpinner24 = (Spinner)findViewById( R.id.spinner_getting_married_spinner24 );
        spinnerGettingMarriedSpinner25 = (Spinner)findViewById( R.id.spinner_getting_married_spinner25 );
        spinnerGettingMarriedSpinner26 = (Spinner)findViewById( R.id.spinner_getting_married_spinner26 );
        spinnerGettingMarriedSpinner27 = (Spinner)findViewById( R.id.spinner_getting_married_spinner27 );
        spinnerGettingMarriedSpinner28 = (Spinner)findViewById( R.id.spinner_getting_married_spinner28 );
        spinnerGettingMarriedSpinner29 = (Spinner)findViewById( R.id.spinner_getting_married_spinner29 );
        spinnerGettingMarriedSpinner30 = (Spinner)findViewById( R.id.spinner_getting_married_spinner30 );
        spinnerGettingMarriedSpinner31 = (Spinner)findViewById( R.id.spinner_getting_married_spinner31 );
        spinnerGettingMarriedSpinner32 = (Spinner)findViewById( R.id.spinner_getting_married_spinner32 );
        spinnerGettingMarriedSpinner33 = (Spinner)findViewById( R.id.spinner_getting_married_spinner33 );
        spinnerGettingMarriedSpinner34 = (Spinner)findViewById( R.id.spinner_getting_married_spinner34 );
        spinnerGettingMarriedSpinner35 = (Spinner)findViewById( R.id.spinner_getting_married_spinner35 );
        spinnerGettingMarriedSpinner36 = (Spinner)findViewById( R.id.spinner_getting_married_spinner36 );
        spinnerGettingMarriedSpinner37 = (Spinner)findViewById( R.id.spinner_getting_married_spinner37 );
        spinnerGettingMarriedSpinner38 = (Spinner)findViewById( R.id.spinner_getting_married_spinner38 );

        //----------- Expecting a baby -------------
        LLExpectingABaby1 = (LinearLayout)findViewById( R.id.LL_Expecting_a_Baby_1 );
        expectingABabySpinner1 = (Spinner)findViewById( R.id.expecting_a_baby_spinner1 );
        expectingABabySpinner2 = (Spinner)findViewById( R.id.expecting_a_baby_spinner2 );
        expectingABabySpinner3 = (Spinner)findViewById( R.id.expecting_a_baby_spinner3 );
        LLExpectingABaby2 = (LinearLayout)findViewById( R.id.LL_Expecting_a_Baby_2 );
        expectingABabySpinner4 = (Spinner)findViewById( R.id.expecting_a_baby_spinner4 );
        expectingABabySpinner5 = (Spinner)findViewById( R.id.expecting_a_baby_spinner5 );
        LLExpectingABaby3 = (LinearLayout)findViewById( R.id.LL_Expecting_a_Baby_3 );
        expectingABabySpinner6 = (Spinner)findViewById( R.id.expecting_a_baby_spinner6 );
        LLExpectingABaby4 = (LinearLayout)findViewById( R.id.LL_Expecting_a_Baby_4 );
        expectingABabySpinner7 = (Spinner)findViewById( R.id.expecting_a_baby_spinner7 );
        LLExpectingABaby5 = (LinearLayout)findViewById( R.id.LL_Expecting_a_Baby_5 );
        expectingABabySpinner8 = (Spinner)findViewById( R.id.expecting_a_baby_spinner8 );
        LLExpectingABaby6 = (LinearLayout)findViewById( R.id.LL_Expecting_a_Baby_6 );
        expectingABabySpinner9 = (Spinner)findViewById( R.id.expecting_a_baby_spinner9 );
        LLExpectingABaby7 = (LinearLayout)findViewById( R.id.LL_Expecting_a_Baby_7 );
        expectingABabySpinner10 = (Spinner)findViewById( R.id.expecting_a_baby_spinner10 );
        expectingABabySpinner11 = (Spinner)findViewById( R.id.expecting_a_baby_spinner11 );
        expectingABabySpinner12 = (Spinner)findViewById( R.id.expecting_a_baby_spinner12 );


// --------------- Shopping --------------

        LLShopping1 = (LinearLayout)findViewById( R.id.LL_shopping_1 );
        ShoppingSpinner1 = (Spinner)findViewById( R.id.Shopping_spinner_1 );
        LLShopping2 = (LinearLayout)findViewById( R.id.LL_shopping_2 );
        ShoppingSpinner2 = (Spinner)findViewById( R.id.Shopping_spinner_2 );
        ShoppingSpinner4 = (Spinner)findViewById( R.id.Shopping_spinner_4 );
        ShoppingSpinner5 = (Spinner)findViewById( R.id.Shopping_spinner_5 );
        LLShopping3 = (LinearLayout)findViewById( R.id.LL_shopping_3 );
        ShoppingSpinner6 = (Spinner)findViewById( R.id.Shopping_spinner_6 );
        ShoppingSpinner7 = (Spinner)findViewById( R.id.Shopping_spinner_7 );
        ShoppingSpinner8 = (Spinner)findViewById( R.id.Shopping_spinner_8 );
        LLShopping4 = (LinearLayout)findViewById( R.id.LL_shopping_4 );
        ShoppingSpinner9 = (Spinner)findViewById( R.id.Shopping_spinner_9 );
        ShoppingSpinner10 = (Spinner)findViewById( R.id.Shopping_spinner_10 );
        ShoppingSpinner11 = (Spinner)findViewById( R.id.Shopping_spinner_11 );
        LLShopping5 = (LinearLayout)findViewById( R.id.LL_shopping_5 );
        LLShopping6 = (LinearLayout)findViewById( R.id.LL_shopping_6 );
        ShoppingSpinner12 = (Spinner)findViewById( R.id.Shopping_spinner_12 );
        ShoppingSpinner13 = (Spinner)findViewById( R.id.Shopping_spinner_13 );
        LLShopping7 = (LinearLayout)findViewById( R.id.LL_shopping_7 );
        ShoppingSpinner14 = (Spinner)findViewById( R.id.Shopping_spinner_14 );
        ShoppingSpinner15 = (Spinner)findViewById( R.id.Shopping_spinner_15 );
        LLShopping8 = (LinearLayout)findViewById( R.id.LL_shopping_8 );
        ShoppingSpinner16 = (Spinner)findViewById( R.id.Shopping_spinner_16 );
        LLShopping9 = (LinearLayout)findViewById( R.id.LL_shopping_9 );
        ShoppingSpinner17 = (Spinner)findViewById( R.id.Shopping_spinner_17 );
        ShoppingSpinner18 = (Spinner)findViewById( R.id.Shopping_spinner_18 );
        ShoppingSpinner19 = (Spinner)findViewById( R.id.Shopping_spinner_19 );
        ShoppingSpinner20 = (Spinner)findViewById( R.id.Shopping_spinner_20 );
        ShoppingSpinner21 = (Spinner)findViewById( R.id.Shopping_spinner_21 );
        LLShopping10 = (LinearLayout)findViewById( R.id.LL_shopping_10 );
        ShoppingSpinner22 = (Spinner)findViewById( R.id.Shopping_spinner_22 );
        ShoppingSpinner23 = (Spinner)findViewById( R.id.Shopping_spinner_23 );
        LLShopping11 = (LinearLayout)findViewById( R.id.LL_shopping_11 );
        ShoppingSpinner24 = (Spinner)findViewById( R.id.Shopping_spinner_24 );
        ShoppingSpinner25 = (Spinner)findViewById( R.id.Shopping_spinner_25 );
        LLShopping12 = (LinearLayout)findViewById( R.id.LL_shopping_12 );
        ShoppingSpinner26 = (Spinner)findViewById( R.id.Shopping_spinner_26 );
        ShoppingSpinner27 = (Spinner)findViewById( R.id.Shopping_spinner_27 );
        ShoppingSpinner28 = (Spinner)findViewById( R.id.Shopping_spinner_28 );
        LLShopping13 = (LinearLayout)findViewById( R.id.LL_shopping_13 );
        ShoppingSpinner29 = (Spinner)findViewById( R.id.Shopping_spinner_29 );
        ShoppingSpinner30 = (Spinner)findViewById( R.id.Shopping_spinner_30 );


        // --------------- Fund Well Being --------------

        LLFundWellBeing1 = (LinearLayout)findViewById( R.id.LL_fund_well_being_1 );
        fundWellBeingSpinner1 = (Spinner)findViewById( R.id.fund_well_being_spinner_1 );
        fundWellBeingSpinner2 = (Spinner)findViewById( R.id.fund_well_being_spinner_2 );
        fundWellBeingSpinner3 = (Spinner)findViewById( R.id.fund_well_being_spinner_3 );
        LLFundWellBeing2 = (LinearLayout)findViewById( R.id.LL_fund_well_being_2 );
        fundWellBeingSpinner4 = (Spinner)findViewById( R.id.fund_well_being_spinner_4 );
        LLFundWellBeing3 = (LinearLayout)findViewById( R.id.LL_fund_well_being_3 );
        fundWellBeingSpinner5 = (Spinner)findViewById( R.id.fund_well_being_spinner_5 );
        LLFundWellBeing4 = (LinearLayout)findViewById( R.id.LL_fund_well_being_4 );
        LLFundWellBeing5 = (LinearLayout)findViewById( R.id.LL_fund_well_being_5 );
        fundWellBeingSpinner6 = (Spinner)findViewById( R.id.fund_well_being_spinner_6 );
        fundWellBeingSpinner7 = (Spinner)findViewById( R.id.fund_well_being_spinner_7 );
        LLFundWellBeing6 = (LinearLayout)findViewById( R.id.LL_fund_well_being_6 );
        fundWellBeingSpinner8 = (Spinner)findViewById( R.id.fund_well_being_spinner_8 );
        LLFundWellBeing7 = (LinearLayout)findViewById( R.id.LL_fund_well_being_7 );
        fundWellBeingSpinner9 = (Spinner)findViewById( R.id.fund_well_being_spinner_9 );
        LLFundWellBeing8 = (LinearLayout)findViewById( R.id.LL_fund_well_being_8 );
        fundWellBeingSpinner10 = (Spinner)findViewById( R.id.fund_well_being_spinner_10 );
        fundWellBeingSpinner11 = (Spinner)findViewById( R.id.fund_well_being_spinner_11 );
        LLFundWellBeing9 = (LinearLayout)findViewById( R.id.LL_fund_well_being_9 );
        fundWellBeingSpinner12 = (Spinner)findViewById( R.id.fund_well_being_spinner_12 );
        fundWellBeingSpinner13 = (Spinner)findViewById( R.id.fund_well_being_spinner_13 );
        fundWellBeingSpinner14 = (Spinner)findViewById( R.id.fund_well_being_spinner_14 );
        fundWellBeingSpinner15 = (Spinner)findViewById( R.id.fund_well_being_spinner_15 );
        fundWellBeingSpinner16 = (Spinner)findViewById( R.id.fund_well_being_spinner_16 );
        fundWellBeingSpinner17 = (Spinner)findViewById( R.id.fund_well_being_spinner_17 );
        fundWellBeingSpinner18 = (Spinner)findViewById( R.id.fund_well_being_spinner_18 );
        fundWellBeingSpinner19 = (Spinner)findViewById( R.id.fund_well_being_spinner_19 );
        fundWellBeingSpinner20 = (Spinner)findViewById( R.id.fund_well_being_spinner_20 );
        fundWellBeingSpinner21 = (Spinner)findViewById( R.id.fund_well_being_spinner_21 );

        // --------------- planning Celebration --------------

        LLPlaningCelebration1 = (LinearLayout)findViewById( R.id.LL_planing_celebration_1 );
        LLPlaningCelebration2 = (LinearLayout)findViewById( R.id.LL_planing_celebration_2 );
        LLPlaningCelebration3 = (LinearLayout)findViewById( R.id.LL_planing_celebration_3 );
        LLPlaningCelebration4 = (LinearLayout)findViewById( R.id.LL_planing_celebration_4 );
        LLPlaningCelebration5 = (LinearLayout)findViewById( R.id.LL_planing_celebration_5 );
        LLPlaningCelebration6 = (LinearLayout)findViewById( R.id.LL_planing_celebration_6 );
        LLPlaningCelebration7 = (LinearLayout)findViewById( R.id.LL_planing_celebration_7 );
        planningCelebrationSpinner1 = (Spinner)findViewById( R.id.planning_celebration_spinner_1 );
        planningCelebrationSpinner2 = (Spinner)findViewById( R.id.planning_celebration_spinner_2 );
        planningCelebrationSpinner3 = (Spinner)findViewById( R.id.planning_celebration_spinner_3 );
        planningCelebrationSpinner4 = (Spinner)findViewById( R.id.planning_celebration_spinner_4 );
        planningCelebrationSpinner5 = (Spinner)findViewById( R.id.planning_celebration_spinner_5 );
        planningCelebrationSpinner6 = (Spinner)findViewById( R.id.planning_celebration_spinner_6 );
        planningCelebrationSpinner7 = (Spinner)findViewById( R.id.planning_celebration_spinner_7 );
        planningCelebrationSpinner8 = (Spinner)findViewById( R.id.planning_celebration_spinner_8 );
        planningCelebrationSpinner9 = (Spinner)findViewById( R.id.planning_celebration_spinner_9 );
        planningCelebrationSpinner10 = (Spinner)findViewById( R.id.planning_celebration_spinner_10 );
        planningCelebrationSpinner11 = (Spinner)findViewById( R.id.planning_celebration_spinner_11 );
        planningCelebrationSpinner12 = (Spinner)findViewById( R.id.planning_celebration_spinner_12 );
        planningCelebrationSpinner13 = (Spinner)findViewById( R.id.planning_celebration_spinner_13 );
        planningCelebrationSpinner14 = (Spinner)findViewById( R.id.planning_celebration_spinner_14 );
        planningCelebrationSpinner15 = (Spinner)findViewById( R.id.planning_celebration_spinner_15 );
        planningCelebrationSpinner16 = (Spinner)findViewById( R.id.planning_celebration_spinner_16 );

        // --------------- planning Vocation --------------

        LLPlanningVocation1 = (LinearLayout)findViewById( R.id.LL_planning_vocation_1 );
        LL_planning_vocation_2 = (LinearLayout)findViewById( R.id.LL_planning_vocation_2 );
        LL_planning_vocation_3 = (LinearLayout)findViewById( R.id.LL_planning_vocation_3 );
        LL_planning_vocation_4 = (LinearLayout)findViewById( R.id.LL_planning_vocation_4 );
        planningVocationSpinner1 = (Spinner)findViewById( R.id.planning_vocation_spinner_1 );
        planningVocationSpinner2 = (Spinner)findViewById( R.id.planning_vocation_spinner_2 );
        planningVocationSpinner3 = (Spinner)findViewById( R.id.planning_vocation_spinner_3 );
        planningVocationSpinner4 = (Spinner)findViewById( R.id.planning_vocation_spinner_4 );
        planningVocationSpinner5 = (Spinner)findViewById( R.id.planning_vocation_spinner_5 );
        planningVocationSpinner6 = (Spinner)findViewById( R.id.planning_vocation_spinner_6 );
        planningVocationSpinner7 = (Spinner)findViewById( R.id.planning_vocation_spinner_7 );
        planningVocationSpinner8 = (Spinner)findViewById( R.id.planning_vocation_spinner_8 );
        planningVocationSpinner9 = (Spinner)findViewById( R.id.planning_vocation_spinner_9 );
        planningVocationSpinner10 = (Spinner)findViewById( R.id.planning_vocation_spinner_10 );
        planningVocationSpinner11 = (Spinner)findViewById( R.id.planning_vocation_spinner_11 );
        planningVocationSpinner12 = (Spinner)findViewById( R.id.planning_vocation_spinner_12 );
        planningVocationSpinner13 = (Spinner)findViewById( R.id.planning_vocation_spinner_13 );
        planningVocationSpinner14 = (Spinner)findViewById( R.id.planning_vocation_spinner_14 );
        planningVocationSpinner15 = (Spinner)findViewById( R.id.planning_vocation_spinner_15 );
        planningVocationSpinner16 = (Spinner)findViewById( R.id.planning_vocation_spinner_16 );
        planningVocationSpinner17 = (Spinner)findViewById( R.id.planning_vocation_spinner_17 );
        planningVocationSpinner18 = (Spinner)findViewById( R.id.planning_vocation_spinner_18 );
        planningVocationSpinner19 = (Spinner)findViewById( R.id.planning_vocation_spinner_19 );

        // --------------- Starting Business --------------

        LLStartingBusiness1 = (LinearLayout)findViewById( R.id.LL_starting_business_1 );
        LLShowroomsOffices1 = (LinearLayout)findViewById( R.id.LL_showrooms_offices_1 );
        startingBusinessSpinner1 = (Spinner)findViewById( R.id.starting_business_spinner_1 );
        startingBusinessSpinner2 = (Spinner)findViewById( R.id.starting_business_spinner_2 );
        startingBusinessSpinner3 = (Spinner)findViewById( R.id.starting_business_spinner_3 );
        LLShowroomsOffices2 = (LinearLayout)findViewById( R.id.LL_showrooms_offices_2 );
        startingBusinessSpinner4 = (Spinner)findViewById( R.id.starting_business_spinner_4 );
        startingBusinessSpinner5 = (Spinner)findViewById( R.id.starting_business_spinner_5 );
        LLShowroomsOffices3 = (LinearLayout)findViewById( R.id.LL_showrooms_offices_3 );
        startingBusinessSpinner6 = (Spinner)findViewById( R.id.starting_business_spinner_6 );
        startingBusinessSpinner7 = (Spinner)findViewById( R.id.starting_business_spinner_7 );
        startingBusinessSpinner8 = (Spinner)findViewById( R.id.starting_business_spinner_8 );
        startingBusinessSpinner9 = (Spinner)findViewById( R.id.starting_business_spinner_9 );
        LLShowroomsOffices4 = (LinearLayout)findViewById( R.id.LL_showrooms_offices_4 );
        startingBusinessSpinner10 = (Spinner)findViewById( R.id.starting_business_spinner_10 );
        startingBusinessSpinner11 = (Spinner)findViewById( R.id.starting_business_spinner_11 );
        startingBusinessSpinner12 = (Spinner)findViewById( R.id.starting_business_spinner_12 );
        LLShowroomsOffices5 = (LinearLayout)findViewById( R.id.LL_showrooms_offices_5 );
        startingBusinessSpinner13 = (Spinner)findViewById( R.id.starting_business_spinner_13 );
        startingBusinessSpinner14 = (Spinner)findViewById( R.id.starting_business_spinner_14 );
        startingBusinessSpinner15 = (Spinner)findViewById( R.id.starting_business_spinner_15 );
        startingBusinessSpinner16 = (Spinner)findViewById( R.id.starting_business_spinner_16 );
        startingBusinessSpinner17 = (Spinner)findViewById( R.id.starting_business_spinner_17 );
        LLShowroomsOffices6 = (LinearLayout)findViewById( R.id.LL_showrooms_offices_6 );
        startingBusinessSpinner18 = (Spinner)findViewById( R.id.starting_business_spinner_18 );
        LLShowroomsOffices7 = (LinearLayout)findViewById( R.id.LL_showrooms_offices_7 );
        startingBusinessSpinner19 = (Spinner)findViewById( R.id.starting_business_spinner_19 );
        LLShowroomsOffices8 = (LinearLayout)findViewById( R.id.LL_showrooms_offices_8 );
        startingBusinessSpinner20 = (Spinner)findViewById( R.id.starting_business_spinner_20 );
        startingBusinessSpinner21 = (Spinner)findViewById( R.id.starting_business_spinner_21 );
        startingBusinessSpinner22 = (Spinner)findViewById( R.id.starting_business_spinner_22 );



        // ------------------------ Moving or Relocating -------------------------

        LLMovingRelocating1 = (LinearLayout)findViewById( R.id.LL_Moving_Relocating_1 );
        MovingRelocatingSpinner1 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_1 );
        MovingRelocatingSpinner1 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_2 );
        MovingRelocatingSpinner3 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_3 );
        MovingRelocatingSpinner4 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_4 );
        MovingRelocatingSpinner5 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_5 );
        MovingRelocatingSpinner6 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_6 );
        MovingRelocatingSpinner7 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_7 );
        // MovingRelocatingSpinner8 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_8 );
        MovingRelocatingSpinner9 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_9 );
        MovingRelocatingSpinner10 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_10 );
        MovingRelocatingSpinner11 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_11 );
        LLMovingRelocating2 = (LinearLayout)findViewById( R.id.LL_Moving_Relocating_2 );
        MovingRelocatingSpinner12 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_12 );
        MovingRelocatingSpinner13 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_13 );
        MovingRelocatingSpinner14 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_14 );
        MovingRelocatingSpinner15 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_15 );
        MovingRelocatingSpinner16 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_16 );
        MovingRelocatingSpinner17 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_17 );
        MovingRelocatingSpinner18 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_18 );
        MovingRelocatingSpinner19 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_19 );
        MovingRelocatingSpinner20 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_20 );
        MovingRelocatingSpinner21 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_21 );
        LLMovingRelocating3 = (LinearLayout)findViewById( R.id.LL_Moving_Relocating_3 );
        MovingRelocatingSpinner22 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_22 );
        MovingRelocatingSpinner23 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_23 );
        MovingRelocatingSpinner24 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_24 );
        MovingRelocatingSpinner25 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_25 );
        MovingRelocatingSpinner26 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_26 );
        MovingRelocatingSpinner27 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_27 );
        MovingRelocatingSpinner28 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_28 );
        MovingRelocatingSpinner29 = (Spinner)findViewById( R.id.Moving_Relocating_spinner_29 );


        //------------------  Education And Career ------------------

        LLEducationAndCareer1 = (LinearLayout)findViewById( R.id.LL_Education_And_Career_1 );
        educationCareerSpinner1 = (Spinner)findViewById( R.id.education_career_spinner_1 );
        educationCareerSpinner2 = (Spinner)findViewById( R.id.education_career_spinner_2 );
        LLEducationAndCareer2 = (LinearLayout)findViewById( R.id.LL_Education_And_Career_2 );
        educationCareerSpinner3 = (Spinner)findViewById( R.id.education_career_spinner_3 );
        educationCareerSpinner4 = (Spinner)findViewById( R.id.education_career_spinner_4 );
        educationCareerSpinner5 = (Spinner)findViewById( R.id.education_career_spinner_5 );
        LLEducationAndCareer3 = (LinearLayout)findViewById( R.id.LL_Education_And_Career_3 );
        educationCareerSpinner6 = (Spinner)findViewById( R.id.education_career_spinner_6 );
        LLEducationAndCareer4 = (LinearLayout)findViewById( R.id.LL_Education_And_Career_4 );
        educationCareerSpinner7 = (Spinner)findViewById( R.id.education_career_spinner_7 );
        LLEducationAndCareer5 = (LinearLayout)findViewById( R.id.LL_Education_And_Career_5 );
        educationCareerSpinner8 = (Spinner)findViewById( R.id.education_career_spinner_8 );

    }

    private void setAdapter() {

        // modelList.add(new UploadImageTopNewsAbstractModel("https://baqat4u.theoneinfotech.com//uploads/users/listing/1580219699_1528647557.png"));

        // modelList.add(new UploadImageBlogAbstractModel("https:\\/\\/baqat4u.theoneinfotech.com\\/\\/uploads\\/users\\/listing\\/1580214562_1056677455.png"));
        mAdapter = new UploadImageTopNewsRecyclerViewAdapter(CreateNormalAddActivity.this, modelList);

        recycler_view_upload_image_normalAdd.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view_upload_image_normalAdd.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        // recycler_view_upload_image_blog.setLayoutManager(layoutManager);
        recycler_view_upload_image_normalAdd.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new UploadImageTopNewsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, UploadImageTopNewsAbstractModel model) {
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
        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_VIDEO)
            {
                System.out.println("SELECT_VIDEO");

                Uri selectedImageUri = data.getData();

                Toast.makeText(this, "VideoSuccess"+selectedImageUri, Toast.LENGTH_SHORT).show();

                //selectedPath = getPath(selectedImageUri);
                // System.out.println("SELECT_VIDEO Path : " + selectedPath);

                //   uploadVideo(selectedPath);
            }
        }
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {

                    Uri selectedImage = data.getData();

                    bitmap = (Bitmap) data.getExtras().get("data");

                    progressbar.setVisibility(View.VISIBLE);

                    UpdateTopNesImageUpload(bitmap);

                    File f = new File(String.valueOf(selectedImage));
                    String imageName = f.getName();

                    //txt_upload.setText(imageName);

                    // isSelectedImage =true;

                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {

                    Uri selectedImage = data.getData();

                    try {

                        //   isImage=true;
                        bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), selectedImage);
                        // ImageUrl = getFileDataFromDrawable1(bitmap);
                        //    ImageUrl=getPath(selectedImage);
                        //   user_profile.setImageBitmap(bitmap);

                        progressbar.setVisibility(View.VISIBLE);

                        UpdateTopNesImageUpload(bitmap);

                        File f = new File(String.valueOf(selectedImage));
                        String imageName = f.getName();

                        txt_upload.setText(imageName);

                        //  isSelectedImage =true;

                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e); }

                }
        }
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

       /* Toast.makeText(getApplicationContext(), countryNames[position], Toast.LENGTH_LONG).show();

        country_str=countryNames[position];
         country_str2=countryNames[position];
*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void CreateNormalAdsAddSection(String title, String contactEmail, String contactNumber, String description, String youtubeLink, String priceAds, String companyName, String webside, String specific, String offerDetaails,
                                          String condition, String location, String country, String state, String city, String address){

        String user_id=  Preference.get(CreateNormalAddActivity.this,Preference.KEY_USER_ID);

        String access_token = Preference.get(CreateNormalAddActivity.this,Preference.KEY_accessToken);

        HashMap<String, String> map = new HashMap<>();

        map.put("access_token",access_token);
        map.put( "logged_in_user_id",user_id);
        map.put("package_id" , "1");
        map.put( "purchase_date", "0");
        map.put("package_expiry", "0");
        map.put("package_price" ,  "0");
        map.put( "listing_title" , title);
        map.put("listing_content","0");
        map.put("listing_expiration_date" ,"0");
        map.put("listing_price", "0");
        map.put("specifics", specific);
        map.put( "email", contactEmail);
        map.put( "contact_number" ,"0");
        map.put("company_name",contactNumber);
        map.put( "website", "");
        map.put("location",location);
        map.put("listing_status","0");
        map.put("offer_details",offerDetaails);

        map.put( "offer_valid_start","0");
        map.put( "offer_valid_end","0");
        map.put("condition",condition);
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
        map.put("available_from" ,"");
        map.put("available_to" ,"");
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
        map.put("media","");

        ApiRequest apiRequest = new ApiRequest(CreateNormalAddActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_Submitads, Constants.USER_Submitads,map, Request.Method.POST);

    }



    public void CreateNormalAddSection(String category_id){

        isCategory=true;

        HashMap<String, String> map = new HashMap<>();

        map.put("category_id",category_id);
        map.put("is_mobile","1");

        ApiRequest apiRequest = new ApiRequest(CreateNormalAddActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_Select_Occation, Constants.USER_Select_Occation,map, Request.Method.POST);

    }

    public void CreateNormalSubAddSection(String parent_category_id){

        isSubCategory=true;

        HashMap<String, String> map = new HashMap<>();

        map.put("parent_category_id",parent_category_id);

        map.put("is_mobile","1");

        ApiRequest apiRequest = new ApiRequest(CreateNormalAddActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_Select_Occation_sub, Constants.USER_Select_Occation_sub,map, Request.Method.POST);
    }

    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_Select_Occation.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                if(isCategory)
                {
                    OccasionDataModel finalArray = new Gson().fromJson(response,new TypeToken<OccasionDataModel>(){}.getType());

                    String status= String.valueOf(finalArray.getErrorCode());

                    if (finalArray.getErrorCode() == 2 && finalArray.getMessage().equalsIgnoreCase("Please Insert Valid Access Token"))
                    {
                        Preference.clearPreference(this);
                        Intent intent=new Intent(this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    }else  if (status.equalsIgnoreCase("0")){

                        OccationList= (ArrayList<SelectOccasionList>) finalArray.getData();

                        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),OccationList);
                        spin.setAdapter(customAdapter);

                        isCategory=false;

                        // Toast.makeText(this, ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else {

                        //  Toast.makeText(this,finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                } else if(isSubCategory)
                {

                    SubOccasionModel finalArray1 = new Gson().fromJson(response,new TypeToken<SubOccasionModel>(){}.getType());

                    String status= String.valueOf(finalArray1.getErrorCode());

                    if (status.equalsIgnoreCase("0")){

                        SubOccationList= (ArrayList<SubOccasionDataModel>) finalArray1.getData();

                        setAdapterCategory(SubOccationList);

         /*               CustomAdapter2 customAdapter2= new CustomAdapter2(getApplicationContext(),SubOccationList,spin2);
                        spin2.setAdapter(customAdapter2);*/

                        //   Toast.makeText(this, ""+finalArray1.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                    else {

                        //    Toast.makeText(this,finalArray1.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        }else  if (Constants.USER_Submitads.equalsIgnoreCase(tag_json_obj)) {

            if (!response.equalsIgnoreCase(null)) {

                createExecutiveModel finalArray = new Gson().fromJson(response, new TypeToken<createExecutiveModel>() {
                }.getType());

                String status = String.valueOf(finalArray.getErrorCode());

                if (status.equalsIgnoreCase("0")) {

                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CreateNormalAddActivity.this, PaypalActivity.class);
                    startActivity(intent);

                }
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Please Check Network", Toast.LENGTH_SHORT).show();
    }



    public void UpdateTopNesImageUpload(final Bitmap bitmap){
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, Constants.BASE_URL+Constants.USER_addImages,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {

                            JSONObject obj = new JSONObject(new String(response.data));

                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                            String status = String.valueOf(obj.getString("errorCode"));

                            String message = String.valueOf(obj.getString("errorCode"));

                            if (status.equalsIgnoreCase("0")) {

                                Toast.makeText(CreateNormalAddActivity.this, message, Toast.LENGTH_SHORT).show();

                                progressbar.setVisibility(View.GONE);

                                JSONObject jsonObject=obj.getJSONObject("data");

                                String Imgurl = jsonObject.getString("url");

                                modelList.add(new UploadImageTopNewsAbstractModel(Imgurl));

                                mAdapter.updateList(modelList);


                            } else {

                                Toast.makeText(CreateNormalAddActivity.this, message, Toast.LENGTH_SHORT).show();

                                //Toast.makeText(CreateNormalAddActivity.this, "UnSuccess", Toast.LENGTH_SHORT).show();

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
                String user_id=   Preference.get(CreateNormalAddActivity.this,Preference.KEY_USER_ID);

                String access_token =   Preference.get(CreateNormalAddActivity.this,Preference.KEY_accessToken);

                map.put("access_token",access_token);

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



    private void FuncSpinner(final String[] CategoryArray, Spinner SpinnerNew) {

      //  CreateNormalAdsCustomAdapter customAdapter=new CreateNormalAdsCustomAdapter(getApplicationContext(),CategoryArray);
       // SpinnerNew.setAdapter(customAdapter);


        SpinnerNew.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                String value=CategoryArray[position].toString();

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });
    }

}
