package shubham.com.baqat.HarshitCreateAdd;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.seatgeek.placesautocomplete.DetailsCallback;
import com.seatgeek.placesautocomplete.OnPlaceSelectedListener;
import com.seatgeek.placesautocomplete.PlacesAutocompleteTextView;
import com.seatgeek.placesautocomplete.model.AddressComponent;
import com.seatgeek.placesautocomplete.model.AddressComponentType;
import com.seatgeek.placesautocomplete.model.Place;
import com.seatgeek.placesautocomplete.model.PlaceDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import shubham.com.baqat.CreateAddScreen.CreateAddActivity;
import shubham.com.baqat.CreateExecutivAddScren.createExecutiveModel;
import shubham.com.baqat.CreateNormalAddScreen.CategorySectionRecyclerViewAdapter;
import shubham.com.baqat.CreateNormalAddScreen.CreateNormalAdsCustomAdapter;
import shubham.com.baqat.CreateNormalAddScreen.CreateNormalAdsCustomAdapterFive;
import shubham.com.baqat.CreateNormalAddScreen.CreateNormalAdsCustomAdapterFour;
import shubham.com.baqat.CreateNormalAddScreen.CreateNormalAdsCustomAdapterThree;
import shubham.com.baqat.CreateNormalAddScreen.CreateNormalAdsCustomAdapterTwo;
import shubham.com.baqat.CreateNormalAddScreen.CustomAdapter;
import shubham.com.baqat.CreateNormalAddScreen.OccasionApiModel.OccasionDataModel;
import shubham.com.baqat.CreateNormalAddScreen.OccasionApiModel.SelectOccasionList;
import shubham.com.baqat.CreateNormalAddScreen.SubOccasionDataModel;
import shubham.com.baqat.CreateNormalAddScreen.SubOccasionModel;
import shubham.com.baqat.CreateTopNewsScreen.UploadImageTopNewsAbstractModel;
import shubham.com.baqat.CreateTopNewsScreen.UploadImageTopNewsRecyclerViewAdapter;
import shubham.com.baqat.HarshitCreateAdd.ApiModel.CreateNormal;
import shubham.com.baqat.HarshitCreateAdd.ApiModel.Info;
import shubham.com.baqat.HarshitCreateAdd.ApimodelKeyword.KeywordDataModel;
import shubham.com.baqat.HarshitCreateAdd.ApimodelKeyword.KeywordModel;
import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.PaymentScreen.PaypalActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
import shubham.com.baqat.UserProfile.UserProfileActivity;
import shubham.com.baqat.UserProfile.VolleyMultipartRequest;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;
import shubham.com.baqat.termsCondition.TermsCondition;

import static shubham.com.baqat.UserProfile.UserProfileActivity.autocomplete;

public class HarshitCreateNormalAdd extends AppCompatActivity implements IApiResponse,AdapterView.OnItemClickListener{


    CreateNormal getSectionOnefinalArray;
    CreateNormal getSectionTwofinalArray;
    CreateNormal getSectionThreefinalArray;
    CreateNormal getSectionFourfinalArray;
    CreateNormal getSectionFivefinalArray;

    Spinner spinner_pst_Keyword;
    int pos=0;

    ProgressBar mProgressBar;

    String getFinalCategoryFirstStr="";


    RecyclerView recycler_view_profile_looking;

    TextView txtOne,txtTwo,txtThree, txtFour,txtFive;
    private LinearLayout LLMotors;
    private LinearLayout LL_condition,LL_Three,LL_Four,LL_Five,LL_one, LL_Two;
    private LinearLayout LL_condition2;
    private LinearLayout LLType;
    private LinearLayout LL_type2;
    private LinearLayout LLUsage;
    private LinearLayout LLColor;
    private LinearLayout LLTransmissionType;
    private LinearLayout LLRegionSpecific;
    private LinearLayout LL_model;
    private LinearLayout LL_horsepower;
    private LinearLayout LLKilometers;
    private LinearLayout LLNoOfCylinder;
    private LinearLayout LLOffer;
    private LinearLayout LLWarranty;
    private LinearLayout LLYearOfMaking;
    private LinearLayout LLNumberOfDoor;
    private LinearLayout LLRentRecurrencePayment;
    private LinearLayout LLBedroom;
    private LinearLayout LLBathroom;
    private LinearLayout LLDevelopers;
    private LinearLayout LLStatus;
    private LinearLayout LLAvailableFrom;
    private LinearLayout LLAvailableTo;
    private LinearLayout LLSize;
    private LinearLayout LLGender;
    private LinearLayout LLNationality;
    private LinearLayout LLPosition;
    private LinearLayout LLExpectedStartDate;
    private LinearLayout LLDegree;
    private LinearLayout LLMonthlySalary;
    private LinearLayout LLBenifits;
    private LinearLayout LLCurrentPosition;
    private LinearLayout LLCurrentCompany;
    private LinearLayout LLNoticePeriod;
    private LinearLayout LLExpectedMonthlySalary;
    private LinearLayout LLResumeFilePath;
    private LinearLayout LLBodyTypeId;
    private LinearLayout LLFieldTypeId;
    private LinearLayout LLCareerLevelId;
    private LinearLayout LLCompanyFieldTypeId;
    private LinearLayout LLBrand;
    private LinearLayout LLMaidRoom;
    private LinearLayout LLSwimmingPool;
    private LinearLayout LLGarden;
    private LinearLayout LLPrivateParking;
    public   ArrayList<String> arrayCatId = new ArrayList<String>();
    boolean isCategory=false;
    boolean isSubCategory=false;

    private ArrayList<Info> modelList_info = new ArrayList<>();

    Spinner Spinner_condition;
    Spinner Spinner_condition2;
    Spinner Spinner_type_new;
    Spinner spinner_body_type_id;
    Spinner spinner_number_of_door;
    Spinner Spinner_bedRoom;
    Spinner Spinner_bathroom;
    Spinner Spinner_status;
    Spinner spinner_Private_Parking;


    ImageView immg_calender_availableTo;
    ImageView immg_calender_available_from;
    TextView txt_date_to;
    TextView txt_date_from;

    int count = 0;
    boolean isSelectedTwo=false;
    String catName = "";
    TextView txtcategory;

    ArrayList<SelectOccasionList> OccationList = new ArrayList<>();
    ArrayList<SubOccasionDataModel> SubOccationList = new ArrayList<>();
    Spinner spin;
    CategorySectionRecyclerViewAdapter mAdapterCategory;
    RecyclerView recycler_view_category;
    RelativeLayout RR_category;
    boolean isCategorySection = false;
    TextView txt_category_Name_title;
    TextView txt_category_Name_title2;


    RelativeLayout RR_upload_image;
    private static final int REQUEST_PERMISSIONS = 100;
    Bitmap bitmap;
    ProgressBar progressbar;
    private ArrayList<UploadImageTopNewsAbstractModel> modelList = new ArrayList<>();
    private RecyclerView recycler_view_upload_image_normalAdd;
    private UploadImageTopNewsRecyclerViewAdapter mAdapter;
    TextView txt_upload;
    LinearLayout LL_section_one;

    Spinner Spinner_type2;

    boolean isSetionOne=false;
    boolean isSetionTwo=false;
    boolean isSetionThree=false;
    boolean isSetionFour=false;
    boolean isSetionFive=false;

    Spinner spinner_offer;
    EditText edt_Region_Specific;

    //Section One
    EditText edt_color, edt_transmission_type, edt_model, edt_kilometers, edt_Number_of_Cylinder, edt_Horsepower, edt_Warranty,
            edt_year_of_making, edt_If_Rent_Recurrence_Of_Payment, edt_developers, edt_size, edt_gender, edt_nationality, edt_position, edt_expected_start_date, edt_degree, edt_monthly_salary, edt_benifits, edt_current_position, edt_current_company, edt_notice_period, edt_expected_monthly_salary, resume_file_path, edt_field_type_id, edt_career_level_id, edt_company_field_type_id, edt_brand, edt_maid_room, edt_swimming_poo, edt_garden;

    //Section Two
    private TextView txtCategoryNameTitle2;
    private LinearLayout LLCondition2;
    private Spinner SpinnerCondition2;
    private LinearLayout LLType2;
    private Spinner SpinnerType2;
    private LinearLayout LLUsage2;
    private Spinner spinnerUsage;
    private LinearLayout LLColor2;
    private EditText edtColor2;
    private LinearLayout LLTransmissionType2;
    private EditText edtTransmissionType2;
    private LinearLayout LLRegionSpecific2;
    private EditText edtRegionSpecific2;
    private LinearLayout LLOffer2;
    private Spinner spinnerOffer2;
    private Spinner spinner_usage;
    private LinearLayout LLModel2;
    private EditText edtModel2;
    private LinearLayout LLKilometers2;
    private EditText edtKilometers2;
    private LinearLayout LLNoOfCylinder2;
    private EditText edtNumberOfCylinder2;
    private LinearLayout LLHorsepower2;
    private EditText edtHorsepower2;
    private LinearLayout LLWarranty2;
    private EditText edtWarranty2;
    private LinearLayout LLYearOfMaking2;
    private EditText edtYearOfMaking2;
    private LinearLayout LLNumberOfDoor2;
    private Spinner spinnerNumberOfDoor2;
    private LinearLayout LLRentRecurrencePayment2;
    private EditText edtIfRentRecurrenceOfPayment2;
    private LinearLayout LLBedroom2;
    private Spinner SpinnerBedRoom2;
    private LinearLayout LLBathroom2;
    private Spinner SpinnerBathroom2;
    private LinearLayout LLDevelopers2;
    private EditText edtDevelopers2;
    private LinearLayout LLStatus2;
    private Spinner SpinnerStatus2;
    private LinearLayout LLAvailableFrom2;
    private TextView txtDateFrom2;
    private ImageView immgCalenderAvailableFrom2;
    private LinearLayout LLAvailableTo2;
    private TextView txtDateTo2;
    private ImageView immgCalenderAvailableTo2;
    private LinearLayout LLSize2;
    private EditText edtSize2;
    private LinearLayout LLGender2;
    private EditText edtGender2;
    private LinearLayout LLNationality2;
    private EditText edtNationality2;
    private LinearLayout LLPosition2;
    private EditText edtPosition2;
    private LinearLayout LLExpectedStartDate2;
    private EditText edtExpectedStartDate2;
    private LinearLayout LLDegree2;
    private EditText edtDegree2;
    private LinearLayout LLMonthlySalary2;
    private EditText edtMonthlySalary2;
    private LinearLayout LLBenifits2;
    private EditText edtBenifits2;
    private LinearLayout LLCurrentPosition2;
    private EditText edtCurrentPosition2;
    private LinearLayout LLCurrentCompany2;
    private EditText edtCurrentCompany2;
    private LinearLayout LLNoticePeriod2;
    private EditText edtNoticePeriod2;
    private LinearLayout LL_expected_monthly_salary2;
    private EditText edtExpectedMonthlySalary2;
    private LinearLayout LLResumeFilePath2;
    private LinearLayout LLBodyTypeId2;
    private Spinner spinnerBodyTypeId2;
    private LinearLayout LLFieldTypeId2;
    private LinearLayout LLCareerLevelId2;
    private LinearLayout LLCompanyFieldTypeId2;
    private LinearLayout LLBrand2;
    private EditText edtBrand2;
    private LinearLayout LLMaidRoom2;
    private EditText edtMaidRoom2;
    private LinearLayout LLSwimmingPool2;
    private EditText edtSwimmingPool2;
    private LinearLayout LLGarden2;
    private EditText edtGarden2;
    private LinearLayout LLPrivateParking2;
    private Spinner spinnerPrivateParking2;

    // Section Three
    private LinearLayout LLTwo;
    private TextView txtCategoryNameTitle3;
    private LinearLayout LLCondition3;
    private Spinner SpinnerCondition3;
    private LinearLayout LLType3;
    private Spinner SpinnerType3;
    private LinearLayout LLUsage3;
    private Spinner spinnerUsage3;
    private LinearLayout LLColor3;
    private EditText edtColor3;
    private LinearLayout LLTransmissionType3;
    private EditText edtTransmissionType3;
    private LinearLayout LLRegionSpecific3;
    private EditText edtRegionSpecific3;
    private LinearLayout LLOffer3;
    private Spinner spinnerOffer3;
    private LinearLayout LLModel3;
    private EditText edtModel3;
    private LinearLayout LLKilometers3;
    private EditText edtKilometers3;
    private LinearLayout LLNoOfCylinder3;
    private EditText edtNumberOfCylinder3;
    private LinearLayout LLHorsepower3;
    private EditText edtHorsepower3;
    private LinearLayout LLWarranty3;
    private EditText edtWarranty3;
    private LinearLayout LLYearOfMaking3;
    private EditText edtYearOfMaking3;
    private LinearLayout LLNumberOfDoor3;
    private Spinner spinnerNumberOfDoor3;
    private LinearLayout LLRentRecurrencePayment3;
    private EditText edtIfRentRecurrenceOfPayment3;
    private LinearLayout LLBedroom3;
    private Spinner SpinnerBedRoom3;
    private LinearLayout LLBathroom3;
    private Spinner SpinnerBathroom3;
    private LinearLayout LLDevelopers3;
    private EditText edtDevelopers3;
    private LinearLayout LLStatus3;
    private Spinner SpinnerStatus3;
    private LinearLayout LLAvailableFrom3;
    private TextView txtDateFrom3;
    private ImageView immgCalenderAvailableFrom3;
    private LinearLayout LLAvailableTo3;
    private TextView txtDateTo3;
    private ImageView immgCalenderAvailableTo3;
    private LinearLayout LLSize3;
    private EditText edtSize3;
    private LinearLayout LLGender3;
    private EditText edtGender3;
    private LinearLayout LLNationality3;
    private EditText edtNationality3;
    private LinearLayout LLPosition3;
    private EditText edtPosition3;
    private LinearLayout LLExpectedStartDate3;
    private EditText edtExpectedStartDate3;
    private LinearLayout LLDegree3;
    private EditText edtDegree3;
    private LinearLayout LLMonthlySalary3;
    private EditText edtMonthlySalary3;
    private LinearLayout LLBenifits3;
    private EditText edtBenifits3;
    private LinearLayout LLCurrentPosition3;
    private EditText edtCurrentPosition3;
    private LinearLayout LLCurrentCompany3;
    private EditText edtCurrentCompany3;
    private LinearLayout LLNoticePeriod3;
    private EditText edtNoticePeriod3;
    private LinearLayout LLExpectedMonthlySalary3;
    private EditText edtExpectedMonthlySalary3;
    private LinearLayout LLResumeFilePath3;
    private LinearLayout LLBodyTypeId3;
    private Spinner spinnerBodyTypeId3;
    private LinearLayout LLFieldTypeId3;
    private LinearLayout LLCareerLevelId3;
    private LinearLayout LLCompanyFieldTypeId3;
    private LinearLayout LLBrand3;
    private EditText edtBrand3;
    private LinearLayout LLMaidRoom3;
    private EditText edtMaidRoom3;
    private LinearLayout LLSwimmingPool3;
    private EditText edtSwimmingPool3;
    private LinearLayout LLGarden3;
    private EditText edtGarden3;
    private LinearLayout LLPrivateParking3;
    private Spinner spinnerPrivateParking3;


    //Section Four
    private TextView txtCategoryNameTitle4;
    private LinearLayout LLCondition4;
    private Spinner SpinnerCondition4;
    private LinearLayout LLType4;
    private Spinner SpinnerType4;
    private LinearLayout LLUsage4;
    private Spinner spinnerUsage4;
    private LinearLayout LLColor4;
    private EditText edtColor4;
    private LinearLayout LLTransmissionType4;
    private EditText edtTransmissionType4;
    private LinearLayout LLRegionSpecific4;
    private EditText edtRegionSpecific4;
    private LinearLayout LLOffer4;
    private Spinner spinnerOffer4;
    private LinearLayout LLModel4;
    private EditText edtModel4;
    private LinearLayout LLKilometers4;
    private EditText edtKilometers4;
    private LinearLayout LLNoOfCylinder4;
    private EditText edtNumberOfCylinder4;
    private LinearLayout LLHorsepower4;
    private EditText edtHorsepower4;
    private LinearLayout LLWarranty4;
    private EditText edtWarranty4;
    private LinearLayout LLYearOfMaking4;
    private EditText edtYearOfMaking4;
    private LinearLayout LLNumberOfDoor4;
    private Spinner spinnerNumberOfDoor4;
    private LinearLayout LLRentRecurrencePayment4;
    private EditText edtIfRentRecurrenceOfPayment4;
    private LinearLayout LLBedroom4;
    private Spinner SpinnerBedRoom4;
    private LinearLayout LLBathroom4;
    private Spinner SpinnerBathroom4;
    private LinearLayout LLDevelopers4;
    private EditText edtDevelopers4;
    private LinearLayout LLStatus4;
    private Spinner SpinnerStatus4;
    private LinearLayout LLAvailableFrom4;
    private TextView txtDateFrom4;
    private ImageView immgCalenderAvailableFrom4;
    private LinearLayout LLAvailableTo4;
    private TextView txtDateTo4;
    private ImageView immgCalenderAvailableTo4;
    private LinearLayout LLSize4;
    private EditText edtSize4;
    private LinearLayout LLGender4;
    private EditText edtGender4;
    private LinearLayout LLNationality4;
    private EditText edtNationality4;
    private LinearLayout LLPosition4;
    private EditText edtPosition4;
    private LinearLayout LLExpectedStartDate4;
    private EditText edtExpectedStartDate4;
    private LinearLayout LLDegree4;
    private EditText edtDegree4;
    private LinearLayout LLMonthlySalary4;
    private EditText edtMonthlySalary4;
    private LinearLayout LLBenifits4;
    private EditText edtBenifits4;
    private LinearLayout LLCurrentPosition4;
    private EditText edtCurrentPosition4;
    private LinearLayout LLCurrentCompany4;
    private EditText edtCurrentCompany4;
    private LinearLayout LLNoticePeriod4;
    private EditText edtNoticePeriod4;
    private LinearLayout LLExpectedMonthlySalary4;
    private EditText edtExpectedMonthlySalary4;
    private LinearLayout LLResumeFilePath4;
    private LinearLayout LLBodyTypeId4;
    private Spinner spinnerBodyTypeId4;
    private LinearLayout LLFieldTypeId4;
    private LinearLayout LLCareerLevelId4;
    private LinearLayout LLCompanyFieldTypeId4;
    private LinearLayout LLBrand4;
    private EditText edtBrand4;
    private LinearLayout LLMaidRoom4;
    private EditText edtMaidRoom4;
    private LinearLayout LLSwimmingPool4;
    private EditText edtSwimmingPool4;
    private LinearLayout LLGarden4;
    private EditText edtGarden4;
    private LinearLayout LLPrivateParking4;
    private Spinner spinnerPrivateParking4;

    //Section Five
    private TextView txtCategoryNameTitle5;
    private LinearLayout LLCondition5;
    private Spinner SpinnerCondition5;
    private LinearLayout LLType5;
    private Spinner SpinnerType5;
    private LinearLayout LLUsage5;
    private Spinner spinnerUsage5;
    private LinearLayout LLColor5;
    private EditText edtColor5;
    private LinearLayout LLTransmissionType5;
    private EditText edtTransmissionType5;
    private LinearLayout LLRegionSpecific5;
    private EditText edtRegionSpecific5;
    private LinearLayout LLOffer5;
    private Spinner spinnerOffer5;
    private LinearLayout LLModel5;
    private EditText edtModel5;
    private LinearLayout LLKilometers5;
    private EditText edtKilometers5;
    private LinearLayout LLNoOfCylinder5;
    private EditText edtNumberOfCylinder5;
    private LinearLayout LLHorsepower5;
    private EditText edtHorsepower5;
    private LinearLayout LLWarranty5;
    private EditText edtWarranty5;
    private LinearLayout LLYearOfMaking5;
    private EditText edtYearOfMaking5;
    private LinearLayout LLNumberOfDoor5;
    private Spinner spinnerNumberOfDoor5;
    private LinearLayout LLRentRecurrencePayment5;
    private EditText edtIfRentRecurrenceOfPayment5;
    private LinearLayout LLBedroom5;
    private Spinner SpinnerBedRoom5;
    private LinearLayout LLBathroom5;
    private Spinner SpinnerBathroom5;
    private LinearLayout LLDevelopers5;
    private EditText edtDevelopers5;
    private LinearLayout LLStatus5;
    private Spinner SpinnerStatus5;
    private LinearLayout LLAvailableFrom5;
    private TextView txtDateFrom5;
    private ImageView immgCalenderAvailableFrom5;
    private LinearLayout LLAvailableTo5;
    private TextView txtDateTo5;
    private ImageView immgCalenderAvailableTo5;
    private LinearLayout LLSize5;
    private EditText edtSize5;
    private LinearLayout LLGender5;
    private EditText edtGender5;
    private LinearLayout LLNationality5;
    private EditText edtNationality5;
    private LinearLayout LLPosition5;
    private EditText edtPosition5;
    private LinearLayout LLExpectedStartDate5;
    private EditText edtExpectedStartDate5;
    private LinearLayout LLDegree5;
    private EditText edtDegree5;
    private LinearLayout LLMonthlySalary5;
    private EditText edtMonthlySalary5;
    private LinearLayout LLBenifits5;
    private EditText edtBenifits5;
    private LinearLayout LLCurrentPosition5;
    private EditText edtCurrentPosition5;
    private LinearLayout LLCurrentCompany5;
    private EditText edtCurrentCompany5;
    private LinearLayout LLNoticePeriod5;
    private EditText edtNoticePeriod5;
    private LinearLayout LLExpectedMonthlySalary5;
    private EditText edtExpectedMonthlySalary5;
    private LinearLayout LLResumeFilePath5;
    private LinearLayout LLBodyTypeId5;
    private Spinner spinnerBodyTypeId5;
    private LinearLayout LLFieldTypeId5;
    private LinearLayout LLCareerLevelId5;
    private LinearLayout LLCompanyFieldTypeId5;
    private LinearLayout LLBrand5;
    private EditText edtBrand5;
    private LinearLayout LLMaidRoom5;
    private EditText edtMaidRoom5;
    private LinearLayout LLSwimmingPool5;
    private EditText edtSwimmingPool5;
    private LinearLayout LLGarden5;
    private EditText edtGarden5;
    private LinearLayout LLPrivateParking5;
    private Spinner spinnerPrivateParking5;


    EditText edt_field_type_id5,edt_career_level_id5,edt_company_field_type_id5;
    EditText edt_field_type_id2,edt_career_level_id2,edt_company_field_type_id2;
    EditText edt_field_type_id3,edt_career_level_id3,edt_company_field_type_id3;
    EditText edt_field_type_id4,edt_career_level_id4,edt_company_field_type_id4;

    TextView txt_listing_expiration;
    TextView txt_offer_valitity_start;
    TextView txt_offer_end;
    private int mYear, mMonth,mDay;
    String frmdate="";
    Button submit_btn_add;
    CheckBox checkbox;

    EditText edt_resume_file_path5;
    EditText edt_resume_file_path2;
    EditText edt_resume_file_path3;
    EditText edt_resume_file_path4;

    EditText ed_title,ed_contact_email,ed_contact_numbetr,ed_keyword,edt_description,
            ed_youtube_link,edt_listing_price_ads,edt_cmny_name,edt_webside,edt_specific,Offer_Detail,edt_Condition,edt_Location;

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

    RelativeLayout back_layout;
    TextView text_career;

    Keyword_RecyclerViewAdapter mAdapter_looking;

    String ZipCode_str="";
    String state_str="";
    String city_str="";
    String Location="";

    ImageView img_profile_looking;
    ArrayList<KeywordDataModel> modellistKeyword=new ArrayList<KeywordDataModel>();

    ArrayList<kewordAddModel> modellistKeyword1=new ArrayList<kewordAddModel>();

    ArrayList<String> arrStr=new ArrayList<String>();

    TextView txt_terms_conditon;

    public static final int MY_PERMISSIONS_REQUEST_CAMERA= 7;

    ImageView info_listing_start_date;
    ImageView info_listing_end_date;
    ImageView img_info_category;
    ImageView info_listing_field;
    PopupMenu popup;

    LinearLayout LL_available_to,LL_available_from;

    //Google Place Api  (Ram)
    private static final String LOG_TAG = "Google Places Autocomplete";
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";
    private static final String API_KEY = "AIzaSyB3_aRArcRZxO8Tz9nBWswQRMgR5HOKuGw";
    AutoCompleteTextView autoCompView;


    //Type
    private RecyclerView recycler_view_category_type;
    private TypeRecyclerViewAdapter mAdapter_category_type;
   // private ArrayList<TypeAbstractModel> modelList_Category_type = new ArrayList<>();
    RelativeLayout RR_category_Multi_type_slection;
    boolean isTypeSelection = true;

    String CategoryId="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_harshit_create_normal_add);
        setContentView(R.layout.activity_harshit_create_normal_add_new);


        //Google Place Api code
        autoCompView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompView.setAdapter(new GooglePlacesAutocompleteAdapter1(this, R.layout.list_item));
        autoCompView.setOnItemClickListener(this);
        RR_category_Multi_type_slection = (RelativeLayout) findViewById(R.id.RR_category_Multi_type_slection);
        recycler_view_category_type = (RecyclerView) findViewById(R.id.recycler_view_category_type);

        txt_date_to=(TextView) findViewById(R.id.txt_date_to);
        txt_date_from=(TextView) findViewById(R.id.txt_date_from);
        LL_available_to=(LinearLayout) findViewById(R.id.LL_available_to);
        LL_available_from=(LinearLayout) findViewById(R.id.LL_available_from);

        img_info_category=(ImageView) findViewById(R.id.img_info_category);
        info_listing_field=(ImageView) findViewById(R.id.info_listing_field);
        info_listing_start_date=(ImageView) findViewById(R.id.info_listing_start_date);
        info_listing_end_date=(ImageView) findViewById(R.id.info_listing_end_date);

        LL_available_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(HarshitCreateNormalAdd.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                view.setVisibility(View.VISIBLE);
                                String frmdate1 = (dayOfMonth+"-"+(monthOfYear+1)+"-"+year);
                                txt_date_to.setText(frmdate1);

                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                datePickerDialog.show();
            }
        });



        LL_available_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(HarshitCreateNormalAdd.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                //  RR_booking_Date.setVisibility( View.VISIBLE );
                                //  txt_time.setVisibility(View.VISIBLE);
                                view.setVisibility(View.VISIBLE);
                                String frmdate2 = (dayOfMonth+"-"+(monthOfYear+1)+"-"+year);
                                txt_date_from.setText(frmdate2);

                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                datePickerDialog.show();



            }
        });

        img_info_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popup = new PopupMenu(HarshitCreateNormalAdd.this, v);
                popup.inflate(R.menu.menu_category);
                popup.show();
            }
        });

        info_listing_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popup = new PopupMenu(HarshitCreateNormalAdd.this, v);
                popup.inflate(R.menu.menu_listing_field);

                popup.show();

            }
        });

        info_listing_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popup = new PopupMenu(HarshitCreateNormalAdd.this, v);
                popup.inflate(R.menu.menu_start_date);

                popup.show();

            }
        });

        info_listing_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popup = new PopupMenu(HarshitCreateNormalAdd.this, v);
                popup.inflate(R.menu.menu_end_date);

                popup.show();

            }
        });


        text_career=(TextView) findViewById(R.id.text_career);

        text_career.setVisibility(View.VISIBLE);
        text_career.setText("Create Ads");

        back_layout=(RelativeLayout) findViewById(R.id.back_layout);

        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        arrayCatId.clear();
        arrayCatId.add("0");
        arrayCatId.add("0");
        arrayCatId.add("0");
        arrayCatId.add("0");
        arrayCatId.add("0");

        findViews();

        String validy_month = Preference.get(HarshitCreateNormalAdd.this,Preference.key_valid_ads);

        int packageMonth = Integer.parseInt(validy_month);


        String Month = new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
        String Day = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        String Year = new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());

        int Monthnew= Integer.parseInt(Month);

        int FinalMonth= Monthnew+packageMonth;

        txt_listing_expiration.setText(Day+"-"+FinalMonth+"-"+Year);

        txt_offer_valitity_start.setText(Month+"-"+Day+"-"+Year);

        immg_calender_availableTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt_date_to.setText("");
            }
        });

        immg_calender_available_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt_date_from.setText("");

            }
        });

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String Parent_id=OccationList.get(position).getCategoryId().toString();

                //  Toast.makeText(CreateNormalAddActivity.this, ""+Parent_id, Toast.LENGTH_SHORT).show();
                count=0;
                CreateNormalSubAddSection(Parent_id);
                //  Toast.makeText(CreateNormalAddActivity.this, lookingforList.get(position).getProfileId(), Toast.LENGTH_LONG).show();

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });

        RR_upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    if ((ActivityCompat.shouldShowRequestPermissionRationale(HarshitCreateNormalAdd.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(HarshitCreateNormalAdd.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE))) {

                    } else {
                        ActivityCompat.requestPermissions(HarshitCreateNormalAdd.this,
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

        txt_offer_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(HarshitCreateNormalAdd.this,
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

                submit_btn_add.setEnabled(false);

                JSONObject jsontStr = getValueFromFields(getSectionOnefinalArray);

            //    System.out.println("JsnArray :"+jsontStr);


               // JSONObject jsontStr = getValueFromFields(getSectionOnefinalArray);
                /*JSONObject jsontStr2 = getValueFromFieldsTwo(getSectionTwofinalArray);
                JSONObject jsontStr3 = getValueFromFieldsThree(getSectionThreefinalArray);
                JSONObject jsontStr4 = getValueFromFieldsFour(getSectionThreefinalArray);
                JSONObject jsontStr5 = getValueFromFieldsFive(getSectionThreefinalArray);
*/
                // String str= modellistKeyword1.get(0).getKeywordname().toString();


                for(int i=0;i<modellistKeyword1.size();i++)
                {
                    arrStr.add(modellistKeyword1.get(i).getKeywordname().toString());
                }

                JSONArray obj=new JSONArray();

                if(jsontStr != null) {

                    obj.put(jsontStr);

                }
                /*else  if(jsontStr != null)
                {
                    obj.put(jsontStr2);
                }
                else  if(jsontStr != null)
                {
                    obj.put(jsontStr3);

                } else  if(jsontStr != null)
                {
                    obj.put(jsontStr4);

                }else  if(jsontStr != null)
                {
                    obj.put(jsontStr5);
                }*/

                JSONObject objMain2 = new JSONObject();

                try {

                    objMain2.put("data", obj);

                } catch (JSONException e) {

                    e.printStackTrace();
                }

                String FinalArray = objMain2.toString();

                System.out.println("final array :"+FinalArray);

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


                if(title.equalsIgnoreCase(""))
                {
                    Toast.makeText(HarshitCreateNormalAdd.this, "Please enter Title", Toast.LENGTH_SHORT).show();

                }else if(ContactEmail.equalsIgnoreCase(""))
                {
                    Toast.makeText(HarshitCreateNormalAdd.this, "Please enter Email", Toast.LENGTH_SHORT).show();

                }else if(ContactNumber.equalsIgnoreCase(""))
                {
                    Toast.makeText(HarshitCreateNormalAdd.this, "Please enter Contact", Toast.LENGTH_SHORT).show();

                }else if(!checkbox.isChecked())
                {
                    Toast.makeText(HarshitCreateNormalAdd.this, "Please Selected Terms Condition", Toast.LENGTH_SHORT).show();

                }else
                {
                    JSONObject objMainMedia = new JSONObject();

                    JSONArray objPastMedi = new JSONArray();
                    JSONObject objMain = null;
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

                    try {

                        objMainMedia.put("data", objPastMedi);

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }

                    String  mediaPAth = objMainMedia.toString();

                    CreateNormalAdsAddSection(title,ContactEmail,ContactNumber,Description,Description,
                            PriceAds,CompanyName,Webside,Specific,OfferDetaails,
                            Condition,"","","","","",FinalArray,mediaPAth,arrStr);

                }
            }
        });


        img_profile_looking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getkewordListshowdialogIn();
            }
        });


        txt_terms_conditon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(HarshitCreateNormalAdd.this, TermsCondition.class);
                startActivity(intent);


            }
        });

        RR_category_Multi_type_slection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isTypeSelection)
                {
                    recycler_view_category_type.setVisibility(View.VISIBLE);
                    isTypeSelection =false;

                }else {
                    recycler_view_category_type.setVisibility(View.GONE);
                    isTypeSelection =true;
                }
            }
        });

        setAdapter_Keyword();

        hideDefaultKeyboard();
        ed_title.requestFocus();
    }

    private void hideDefaultKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //you have got lot of methods here
    }

    // MultiType Selction
    private void setAdapter_multiselction_typ(ArrayList<Info> modelList_info) {
        mAdapter_category_type = new TypeRecyclerViewAdapter(HarshitCreateNormalAdd.this, modelList_info);
        recycler_view_category_type.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view_category_type.setLayoutManager(layoutManager);
        recycler_view_category_type.setAdapter(mAdapter_category_type);

        mAdapter_category_type.SetOnItemClickListener(new TypeRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Info model, ImageView img_check) {

                if(model.getisSlectedType())
                {
                    img_check.setVisibility(View.GONE);


                    model.setSlectedType(false);

                }else
                {
                    img_check.setVisibility(View.VISIBLE);
                    model.setSlectedType(true);

                }
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

        if (ContextCompat.checkSelfPermission(HarshitCreateNormalAdd.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)
                    HarshitCreateNormalAdd.this, Manifest.permission.CAMERA)) {

            } else {

                ActivityCompat.requestPermissions((Activity) HarshitCreateNormalAdd.this,
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

                        // txt_upload.setText(imageName);

                        //  isSelectedImage =true;

                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e); }

                }
        }
    }

    private void findViews() {

        txt_terms_conditon=(TextView) findViewById(R.id.txt_terms_conditon);

        recycler_view_profile_looking=(RecyclerView) findViewById(R.id.recycler_view_profile_looking);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        img_profile_looking = (ImageView) findViewById(R.id.img_profile_looking);

        //Section One
        edt_color =(EditText) findViewById(R.id.edt_color) ;
        edt_transmission_type=(EditText) findViewById(R.id.edt_transmission_type) ;
        edt_model=(EditText) findViewById(R.id.edt_model) ;
        edt_kilometers=(EditText) findViewById(R.id.edt_kilometers) ;
        edt_Number_of_Cylinder=(EditText) findViewById(R.id.edt_Number_of_Cylinder) ;
        edt_Horsepower=(EditText) findViewById(R.id.edt_Horsepower) ;
        edt_Warranty=(EditText) findViewById(R.id.edt_Warranty) ;
        edt_year_of_making=(EditText) findViewById(R.id.edt_year_of_making) ;
        edt_If_Rent_Recurrence_Of_Payment=(EditText) findViewById(R.id.edt_If_Rent_Recurrence_Of_Payment) ;
        edt_developers=(EditText) findViewById(R.id.edt_developers) ;
        edt_size=(EditText) findViewById(R.id.edt_size) ;
        edt_gender=(EditText) findViewById(R.id.edt_gender) ;
        edt_nationality=(EditText) findViewById(R.id.edt_nationality) ;
        edt_position=(EditText) findViewById(R.id.edt_position) ;
        edt_expected_start_date=(EditText) findViewById(R.id.edt_expected_start_date) ;
        edt_degree=(EditText) findViewById(R.id.edt_degree) ;
        edt_monthly_salary=(EditText) findViewById(R.id.edt_monthly_salary) ;
        edt_benifits=(EditText) findViewById(R.id.edt_benifits) ;
        edt_current_position=(EditText) findViewById(R.id.edt_current_position) ;
        edt_current_company=(EditText) findViewById(R.id.edt_current_company) ;
        edt_notice_period=(EditText) findViewById(R.id.edt_notice_period) ;
        edt_expected_monthly_salary=(EditText) findViewById(R.id.edt_expected_monthly_salary) ;
        resume_file_path=(EditText) findViewById(R.id.resume_file_path) ;
        edt_field_type_id=(EditText) findViewById(R.id.edt_field_type_id) ;
        edt_career_level_id=(EditText) findViewById(R.id.edt_career_level_id) ;
        edt_company_field_type_id=(EditText) findViewById(R.id.edt_company_field_type_id) ;
        edt_brand=(EditText) findViewById(R.id.edt_brand) ;
        edt_maid_room=(EditText) findViewById(R.id.edt_maid_room) ;
        edt_swimming_poo=(EditText) findViewById(R.id.edt_swimming_pool) ;
        edt_garden=(EditText) findViewById(R.id.edt_garden) ;

        edt_resume_file_path5=(EditText) findViewById(R.id.edt_resume_file_path5) ;
        edt_resume_file_path2=(EditText) findViewById(R.id.edt_resume_file_path2) ;
        edt_resume_file_path3=(EditText) findViewById(R.id.edt_resume_file_path3) ;
        edt_resume_file_path4=(EditText) findViewById(R.id.edt_resume_file_path4) ;






        checkbox= (CheckBox) findViewById(R.id.checkbox);
        ed_title  = (EditText) findViewById(R.id.ed_title);
        ed_contact_email  = (EditText) findViewById(R.id.ed_contact_email);
        ed_contact_numbetr  = (EditText) findViewById(R.id.ed_contact_numbetr);
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


        submit_btn_add = (Button) findViewById(R.id.submit_btn_add);
        txt_offer_valitity_start = (TextView) findViewById(R.id.txt_offer_valitity_start);
        txt_offer_end = (TextView) findViewById(R.id.txt_offer_end);

        Spinner_type2=(Spinner) findViewById(R.id.Spinner_type2);
        LL_one=(LinearLayout) findViewById(R.id.LL_One);
        LL_Two=(LinearLayout) findViewById(R.id.LL_Two);
        txt_upload = (TextView) findViewById(R.id.txt_upload);
        recycler_view_upload_image_normalAdd = (RecyclerView) findViewById(R.id.recycler_view_upload_image_normalAdd);

        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        RR_upload_image = (RelativeLayout) findViewById(R.id.RR_upload_image);
        RR_category=(RelativeLayout) findViewById(R.id.RR_category) ;
        recycler_view_category=(RecyclerView) findViewById(R.id.recycler_view_category);
        //   LL_AllCategory=(LinearLayout) findViewById(R.id.LL_AllCategory);

        txt_listing_expiration=(TextView) findViewById(R.id.txt_listing_expiration);
      /*  txtOne=(TextView) findViewById(R.id.txtOne);
        txtTwo=(TextView) findViewById(R.id.txtTwo);
        txtThree=(TextView) findViewById(R.id.txtThree);
        txtFour=(TextView) findViewById(R.id.txtFour);
        txtFive=(TextView) findViewById(R.id.txtFive);*/

        txtcategory=(TextView) findViewById(R.id.txtcategory);
        txt_category_Name_title=(TextView) findViewById(R.id.txt_category_Name_title);
        txt_category_Name_title2=(TextView) findViewById(R.id.txt_category_Name_title2);

        immg_calender_available_from=(ImageView) findViewById(R.id.immg_calender_available_from);
        immg_calender_availableTo=(ImageView) findViewById(R.id.immg_calender_availableTo);
        txt_date_to=(TextView) findViewById(R.id.txt_date_to);

        Spinner_condition=(Spinner) findViewById(R.id.Spinner_condition);
        Spinner_condition2=(Spinner) findViewById(R.id.Spinner_condition2);
        Spinner_type_new=(Spinner) findViewById(R.id.Spinner_type_new);


        Spinner_type_new.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> arg0)
            {


            }

        });


        spinner_body_type_id=(Spinner) findViewById(R.id.spinner_body_type_id);
        Spinner_status=(Spinner) findViewById(R.id.Spinner_status);
        spinner_offer=(Spinner) findViewById(R.id.spinner_offer);
        spin=(Spinner) findViewById(R.id.spinner);

        spinner_Private_Parking=(Spinner) findViewById(R.id.spinner_Private_Parking);
        spinner_number_of_door=(Spinner) findViewById(R.id.spinner_number_of_door);
        Spinner_bathroom=(Spinner) findViewById(R.id.Spinner_bathroom);
        Spinner_bedRoom=(Spinner) findViewById(R.id.Spinner_bedRoom);

        LL_Three = (LinearLayout)findViewById( R.id.LL_Three );
        LL_Four = (LinearLayout)findViewById( R.id.LL_Four );
        LL_Five = (LinearLayout)findViewById( R.id.LL_Five );

        LL_condition = (LinearLayout)findViewById( R.id.LL_condition );
        LL_condition2 = (LinearLayout)findViewById( R.id.LL_condition2 );
        LLMotors = (LinearLayout)findViewById( R.id.LL_Motors );
        LLType = (LinearLayout)findViewById( R.id.LL_type );
        LL_type2 = (LinearLayout)findViewById( R.id.LL_type2 );
        LLUsage = (LinearLayout)findViewById( R.id.LL_usage );
        LLColor = (LinearLayout)findViewById( R.id.LL_color );
        LLTransmissionType = (LinearLayout)findViewById( R.id.LL_transmission_type );
        LLRegionSpecific = (LinearLayout)findViewById( R.id.LL_region_specific );
        LL_model = (LinearLayout)findViewById( R.id.LL_model );
        LLOffer = (LinearLayout)findViewById( R.id.LL_offer );
        LLKilometers = (LinearLayout)findViewById( R.id.LL_kilometers );
        LLNoOfCylinder = (LinearLayout)findViewById( R.id.LL_no_of_cylinder );
        LL_horsepower = (LinearLayout)findViewById( R.id.LL_horsepower );
        LLWarranty = (LinearLayout)findViewById( R.id.LL_warranty );
        LLYearOfMaking = (LinearLayout)findViewById( R.id.LL_year_of_making );
        LLNumberOfDoor = (LinearLayout)findViewById( R.id.LL_number_of_door );
        LLRentRecurrencePayment = (LinearLayout)findViewById( R.id.LL_rent_recurrence_payment );
        LLBedroom = (LinearLayout)findViewById( R.id.LL_bedroom );
        LLBathroom = (LinearLayout)findViewById( R.id.LL_bathroom );
        LLDevelopers = (LinearLayout)findViewById( R.id.LL_developers );
        LLStatus = (LinearLayout)findViewById( R.id.LL_status );
        LLAvailableFrom = (LinearLayout)findViewById( R.id.LL_available_from );
        LLAvailableTo = (LinearLayout)findViewById( R.id.LL_available_to );
        LLSize = (LinearLayout)findViewById( R.id.LL_size );
        LLGender = (LinearLayout)findViewById( R.id.LL_gender );
        LLNationality = (LinearLayout)findViewById( R.id.LL_nationality );
        LLPosition = (LinearLayout)findViewById( R.id.LL_position );
        LLExpectedStartDate = (LinearLayout)findViewById( R.id.LL_expected_start_date );
        LLDegree = (LinearLayout)findViewById( R.id.LL_degree );
        LLMonthlySalary = (LinearLayout)findViewById( R.id.LL_monthly_salary );
        LLBenifits = (LinearLayout)findViewById( R.id.LL_benifits );
        LLCurrentPosition = (LinearLayout)findViewById( R.id.LL_current_position );
        LLCurrentCompany = (LinearLayout)findViewById( R.id.LL_current_company );
        LLNoticePeriod = (LinearLayout)findViewById( R.id.LL_notice_period );
        LLExpectedMonthlySalary = (LinearLayout)findViewById( R.id.LL_expected_monthly_salary );
        LLResumeFilePath = (LinearLayout)findViewById( R.id.LL_resume_file_path );
        LLBodyTypeId = (LinearLayout)findViewById( R.id.LL_body_type_id );
        LLFieldTypeId = (LinearLayout)findViewById( R.id.LL_field_type_id );
        LLCareerLevelId = (LinearLayout)findViewById( R.id.LL_career_level_id );
        LLCompanyFieldTypeId = (LinearLayout)findViewById( R.id.LL_company_field_type_id );
        LLBrand = (LinearLayout)findViewById( R.id.LL_brand );
        LLMaidRoom = (LinearLayout)findViewById( R.id.LL_maid_room );
        LLSwimmingPool = (LinearLayout)findViewById( R.id.LL_swimming_pool );
        LLGarden = (LinearLayout)findViewById( R.id.LL_garden );
        LLPrivateParking = (LinearLayout)findViewById( R.id.LL_private_parking );

        //Section Two
        LL_expected_monthly_salary2 = (LinearLayout)findViewById( R.id.LL_expected_monthly_salary2 );
        txtCategoryNameTitle2 = (TextView)findViewById( R.id.txt_category_Name_title2 );
        LLCondition2 = (LinearLayout)findViewById( R.id.LL_condition2 );
        SpinnerCondition2 = (Spinner)findViewById( R.id.Spinner_condition2 );
        LLType2 = (LinearLayout)findViewById( R.id.LL_type2 );
        SpinnerType2 = (Spinner)findViewById( R.id.Spinner_type2 );
        LLUsage2 = (LinearLayout)findViewById( R.id.LL_usage2 );
        spinnerUsage = (Spinner)findViewById( R.id.spinner_usage );
        LLColor2 = (LinearLayout)findViewById( R.id.LL_color2 );
        edtColor2 = (EditText)findViewById( R.id.edt_color2 );
        LLTransmissionType2 = (LinearLayout)findViewById( R.id.LL_transmission_type2 );
        edtTransmissionType2 = (EditText)findViewById( R.id.edt_transmission_type2 );
        LLRegionSpecific2 = (LinearLayout)findViewById( R.id.LL_region_specific2 );
        edtRegionSpecific2 = (EditText)findViewById( R.id.edt_Region_Specific2 );
        LLOffer2 = (LinearLayout)findViewById( R.id.LL_offer2 );
        spinnerOffer2 = (Spinner)findViewById( R.id.spinner_offer2 );
        spinner_usage = (Spinner)findViewById( R.id.spinner_usage );
        LLModel2 = (LinearLayout)findViewById( R.id.LL_model2 );
        edtModel2 = (EditText)findViewById( R.id.edt_model2 );
        LLKilometers2 = (LinearLayout)findViewById( R.id.LL_kilometers2 );
        edtKilometers2 = (EditText)findViewById( R.id.edt_kilometers2 );
        LLNoOfCylinder2 = (LinearLayout)findViewById( R.id.LL_no_of_cylinder2 );
        edtNumberOfCylinder2 = (EditText)findViewById( R.id.edt_Number_of_Cylinder2 );
        LLHorsepower2 = (LinearLayout)findViewById( R.id.LL_horsepower2 );
        edtHorsepower2 = (EditText)findViewById( R.id.edt_Horsepower2 );
        LLWarranty2 = (LinearLayout)findViewById( R.id.LL_warranty2 );
        edtWarranty2 = (EditText)findViewById( R.id.edt_Warranty2 );
        LLYearOfMaking2 = (LinearLayout)findViewById( R.id.LL_year_of_making2 );
        edtYearOfMaking2 = (EditText)findViewById( R.id.edt_year_of_making2 );
        LLNumberOfDoor2 = (LinearLayout)findViewById( R.id.LL_number_of_door2 );
        spinnerNumberOfDoor2 = (Spinner)findViewById( R.id.spinner_number_of_door2 );
        LLRentRecurrencePayment2 = (LinearLayout)findViewById( R.id.LL_rent_recurrence_payment2 );
        edtIfRentRecurrenceOfPayment2 = (EditText)findViewById( R.id.edt_If_Rent_Recurrence_Of_Payment2 );
        LLBedroom2 = (LinearLayout)findViewById( R.id.LL_bedroom2 );
        SpinnerBedRoom2 = (Spinner)findViewById( R.id.Spinner_bedRoom2 );
        LLBathroom2 = (LinearLayout)findViewById( R.id.LL_bathroom2 );
        SpinnerBathroom2 = (Spinner)findViewById( R.id.Spinner_bathroom2 );
        LLDevelopers2 = (LinearLayout)findViewById( R.id.LL_developers2 );
        edtDevelopers2 = (EditText)findViewById( R.id.edt_developers2 );
        LLStatus2 = (LinearLayout)findViewById( R.id.LL_status2 );
        SpinnerStatus2 = (Spinner)findViewById( R.id.Spinner_status2 );
        LLAvailableFrom2 = (LinearLayout)findViewById( R.id.LL_available_from2 );
        txtDateFrom2 = (TextView)findViewById( R.id.txt_date_from2 );
        immgCalenderAvailableFrom2 = (ImageView)findViewById( R.id.immg_calender_available_from2 );
        LLAvailableTo2 = (LinearLayout)findViewById( R.id.LL_available_to2 );
        txtDateTo2 = (TextView)findViewById( R.id.txt_date_to2 );
        immgCalenderAvailableTo2 = (ImageView)findViewById( R.id.immg_calender_availableTo2 );
        LLSize2 = (LinearLayout)findViewById( R.id.LL_size2 );
        edtSize2 = (EditText)findViewById( R.id.edt_size2 );
        LLGender2 = (LinearLayout)findViewById( R.id.LL_gender2 );
        edtGender2 = (EditText)findViewById( R.id.edt_gender2 );
        LLNationality2 = (LinearLayout)findViewById( R.id.LL_nationality2 );
        edtNationality2 = (EditText)findViewById( R.id.edt_nationality2 );
        LLPosition2 = (LinearLayout)findViewById( R.id.LL_position2 );
        edtPosition2 = (EditText)findViewById( R.id.edt_position2 );
        LLExpectedStartDate2 = (LinearLayout)findViewById( R.id.LL_expected_start_date2 );
        edtExpectedStartDate2 = (EditText)findViewById( R.id.edt_expected_start_date2 );
        LLDegree2 = (LinearLayout)findViewById( R.id.LL_degree2 );
        edtDegree2 = (EditText)findViewById( R.id.edt_degree2 );
        LLMonthlySalary2 = (LinearLayout)findViewById( R.id.LL_monthly_salary2 );
        edtMonthlySalary2 = (EditText)findViewById( R.id.edt_monthly_salary2 );
        LLBenifits2 = (LinearLayout)findViewById( R.id.LL_benifits2 );
        edtBenifits2 = (EditText)findViewById( R.id.edt_benifits2 );
        LLCurrentPosition2 = (LinearLayout)findViewById( R.id.LL_current_position2 );
        edtCurrentPosition2 = (EditText)findViewById( R.id.edt_current_position2 );
        LLCurrentCompany2 = (LinearLayout)findViewById( R.id.LL_current_company2 );
        edtCurrentCompany2 = (EditText)findViewById( R.id.edt_current_company2 );
        LLNoticePeriod2 = (LinearLayout)findViewById( R.id.LL_notice_period2 );
        edtNoticePeriod2 = (EditText)findViewById( R.id.edt_notice_period2 );
        LL_expected_monthly_salary2 = (LinearLayout)findViewById( R.id.LL_expected_monthly_salary2 );
        edtExpectedMonthlySalary2 = (EditText)findViewById( R.id.edt_expected_monthly_salary2 );
        LLResumeFilePath2 = (LinearLayout)findViewById( R.id.LL_resume_file_path2 );
        LLBodyTypeId2 = (LinearLayout)findViewById( R.id.LL_body_type_id2 );
        spinnerBodyTypeId2 = (Spinner)findViewById( R.id.spinner_body_type_id2 );
        LLFieldTypeId2 = (LinearLayout)findViewById( R.id.LL_field_type_id2 );
        LLCareerLevelId2 = (LinearLayout)findViewById( R.id.LL_career_level_id2 );
        LLCompanyFieldTypeId2 = (LinearLayout)findViewById( R.id.LL_company_field_type_id2 );
        LLBrand2 = (LinearLayout)findViewById( R.id.LL_brand2 );
        edtBrand2 = (EditText)findViewById( R.id.edt_brand2 );
        LLMaidRoom2 = (LinearLayout)findViewById( R.id.LL_maid_room2 );
        edtMaidRoom2 = (EditText)findViewById( R.id.edt_maid_room2 );
        LLSwimmingPool2 = (LinearLayout)findViewById( R.id.LL_swimming_pool2 );
        edtSwimmingPool2 = (EditText)findViewById( R.id.edt_swimming_pool2 );
        LLGarden2 = (LinearLayout)findViewById( R.id.LL_garden2 );
        edtGarden2 = (EditText)findViewById( R.id.edt_garden2 );
        LLPrivateParking2 = (LinearLayout)findViewById( R.id.LL_private_parking2 );
        spinnerPrivateParking2 = (Spinner)findViewById( R.id.spinner_Private_Parking2 );
        edt_Region_Specific = (EditText) findViewById( R.id.edt_Region_Specific );



        //Section Thee
        LLTwo = (LinearLayout)findViewById( R.id.LL_Two );
        txtCategoryNameTitle3 = (TextView)findViewById( R.id.txt_category_Name_title3 );
        LLCondition3 = (LinearLayout)findViewById( R.id.LL_condition3 );
        SpinnerCondition3 = (Spinner)findViewById( R.id.Spinner_condition3 );
        LLType3 = (LinearLayout)findViewById( R.id.LL_type3 );
        SpinnerType3 = (Spinner)findViewById( R.id.Spinner_type3 );
        LLUsage3 = (LinearLayout)findViewById( R.id.LL_usage3 );
        spinnerUsage3 = (Spinner)findViewById( R.id.spinner_usage3 );
        LLColor3 = (LinearLayout)findViewById( R.id.LL_color3 );
        edtColor3 = (EditText)findViewById( R.id.edt_color3 );
        LLTransmissionType3 = (LinearLayout)findViewById( R.id.LL_transmission_type3 );
        edtTransmissionType3 = (EditText)findViewById( R.id.edt_transmission_type3 );
        LLRegionSpecific3 = (LinearLayout)findViewById( R.id.LL_region_specific3 );
        edtRegionSpecific3 = (EditText)findViewById( R.id.edt_Region_Specific3 );
        LLOffer3 = (LinearLayout)findViewById( R.id.LL_offer3 );
        spinnerOffer3 = (Spinner)findViewById( R.id.spinner_offer3 );
        LLModel3 = (LinearLayout)findViewById( R.id.LL_model3 );
        edtModel3 = (EditText)findViewById( R.id.edt_model3 );
        LLKilometers3 = (LinearLayout)findViewById( R.id.LL_kilometers3 );
        edtKilometers3 = (EditText)findViewById( R.id.edt_kilometers3 );
        LLNoOfCylinder3 = (LinearLayout)findViewById( R.id.LL_no_of_cylinder3 );
        edtNumberOfCylinder3 = (EditText)findViewById( R.id.edt_Number_of_Cylinder3 );
        LLHorsepower3 = (LinearLayout)findViewById( R.id.LL_horsepower3 );
        edtHorsepower3 = (EditText)findViewById( R.id.edt_Horsepower3 );
        LLWarranty3 = (LinearLayout)findViewById( R.id.LL_warranty3 );
        edtWarranty3 = (EditText)findViewById( R.id.edt_Warranty3 );
        LLYearOfMaking3 = (LinearLayout)findViewById( R.id.LL_year_of_making3 );
        edtYearOfMaking3 = (EditText)findViewById( R.id.edt_year_of_making3 );
        LLNumberOfDoor3 = (LinearLayout)findViewById( R.id.LL_number_of_door3 );
        spinnerNumberOfDoor3 = (Spinner)findViewById( R.id.spinner_number_of_door3 );
        LLRentRecurrencePayment3 = (LinearLayout)findViewById( R.id.LL_rent_recurrence_payment3 );
        edtIfRentRecurrenceOfPayment3 = (EditText)findViewById( R.id.edt_If_Rent_Recurrence_Of_Payment3 );
        LLBedroom3 = (LinearLayout)findViewById( R.id.LL_bedroom3 );
        SpinnerBedRoom3 = (Spinner)findViewById( R.id.Spinner_bedRoom3 );
        LLBathroom3 = (LinearLayout)findViewById( R.id.LL_bathroom3 );
        SpinnerBathroom3 = (Spinner)findViewById( R.id.Spinner_bathroom3 );
        LLDevelopers3 = (LinearLayout)findViewById( R.id.LL_developers3 );
        edtDevelopers3 = (EditText)findViewById( R.id.edt_developers3 );
        LLStatus3 = (LinearLayout)findViewById( R.id.LL_status3 );
        SpinnerStatus3 = (Spinner)findViewById( R.id.Spinner_status3 );
        LLAvailableFrom3 = (LinearLayout)findViewById( R.id.LL_available_from3 );
        txtDateFrom3 = (TextView)findViewById( R.id.txt_date_from3 );
        immgCalenderAvailableFrom3 = (ImageView)findViewById( R.id.immg_calender_available_from3 );
        LLAvailableTo3 = (LinearLayout)findViewById( R.id.LL_available_to3 );
        txtDateTo3 = (TextView)findViewById( R.id.txt_date_to3 );
        immgCalenderAvailableTo3 = (ImageView)findViewById( R.id.immg_calender_availableTo3 );
        LLSize3 = (LinearLayout)findViewById( R.id.LL_size3 );
        edtSize3 = (EditText)findViewById( R.id.edt_size3 );
        LLGender3 = (LinearLayout)findViewById( R.id.LL_gender3 );
        edtGender3 = (EditText)findViewById( R.id.edt_gender3 );
        LLNationality3 = (LinearLayout)findViewById( R.id.LL_nationality3 );
        edtNationality3 = (EditText)findViewById( R.id.edt_nationality3 );
        LLPosition3 = (LinearLayout)findViewById( R.id.LL_position3 );
        edtPosition3 = (EditText)findViewById( R.id.edt_position3 );
        LLExpectedStartDate3 = (LinearLayout)findViewById( R.id.LL_expected_start_date3 );
        edtExpectedStartDate3 = (EditText)findViewById( R.id.edt_expected_start_date3 );
        LLDegree3 = (LinearLayout)findViewById( R.id.LL_degree3 );
        edtDegree3 = (EditText)findViewById( R.id.edt_degree3 );
        LLMonthlySalary3 = (LinearLayout)findViewById( R.id.LL_monthly_salary3 );
        edtMonthlySalary3 = (EditText)findViewById( R.id.edt_monthly_salary3 );
        LLBenifits3 = (LinearLayout)findViewById( R.id.LL_benifits3 );
        edtBenifits3 = (EditText)findViewById( R.id.edt_benifits3 );
        LLCurrentPosition3 = (LinearLayout)findViewById( R.id.LL_current_position3 );
        edtCurrentPosition3 = (EditText)findViewById( R.id.edt_current_position3 );
        LLCurrentCompany3 = (LinearLayout)findViewById( R.id.LL_current_company3 );
        edtCurrentCompany3 = (EditText)findViewById( R.id.edt_current_company3 );
        LLNoticePeriod3 = (LinearLayout)findViewById( R.id.LL_notice_period3 );
        edtNoticePeriod3 = (EditText)findViewById( R.id.edt_notice_period3 );
        LLExpectedMonthlySalary3 = (LinearLayout)findViewById( R.id.LL_expected_monthly_salary3 );
        edtExpectedMonthlySalary3 = (EditText)findViewById( R.id.edt_expected_monthly_salary3 );
        LLResumeFilePath3 = (LinearLayout)findViewById( R.id.LL_resume_file_path3 );
        LLBodyTypeId3 = (LinearLayout)findViewById( R.id.LL_body_type_id3 );
        spinnerBodyTypeId3 = (Spinner)findViewById( R.id.spinner_body_type_id3 );
        LLFieldTypeId3 = (LinearLayout)findViewById( R.id.LL_field_type_id3 );
        LLCareerLevelId3 = (LinearLayout)findViewById( R.id.LL_career_level_id3 );
        LLCompanyFieldTypeId3 = (LinearLayout)findViewById( R.id.LL_company_field_type_id3 );
        LLBrand3 = (LinearLayout)findViewById( R.id.LL_brand3 );
        edtBrand3 = (EditText)findViewById( R.id.edt_brand3 );
        LLMaidRoom3 = (LinearLayout)findViewById( R.id.LL_maid_room3 );
        edtMaidRoom3 = (EditText)findViewById( R.id.edt_maid_room3 );
        LLSwimmingPool3 = (LinearLayout)findViewById( R.id.LL_swimming_pool3 );
        edtSwimmingPool3 = (EditText)findViewById( R.id.edt_swimming_pool3 );
        LLGarden3 = (LinearLayout)findViewById( R.id.LL_garden3 );
        edtGarden3 = (EditText)findViewById( R.id.edt_garden3 );
        LLPrivateParking3 = (LinearLayout)findViewById( R.id.LL_private_parking3 );
        spinnerPrivateParking3 = (Spinner)findViewById( R.id.spinner_Private_Parking3 );

        //Section Four
        txtCategoryNameTitle4 = (TextView)findViewById( R.id.txt_category_Name_title4 );
        LLCondition4 = (LinearLayout)findViewById( R.id.LL_condition4 );
        SpinnerCondition4 = (Spinner)findViewById( R.id.Spinner_condition4 );
        LLType4 = (LinearLayout)findViewById( R.id.LL_type4 );
        SpinnerType4 = (Spinner)findViewById( R.id.Spinner_type4 );
        LLUsage4 = (LinearLayout)findViewById( R.id.LL_usage4 );
        spinnerUsage4 = (Spinner)findViewById( R.id.spinner_usage4 );
        LLColor4 = (LinearLayout)findViewById( R.id.LL_color4 );
        edtColor4 = (EditText)findViewById( R.id.edt_color4 );
        LLTransmissionType4 = (LinearLayout)findViewById( R.id.LL_transmission_type4 );
        edtTransmissionType4 = (EditText)findViewById( R.id.edt_transmission_type4 );
        LLRegionSpecific4 = (LinearLayout)findViewById( R.id.LL_region_specific4 );
        edtRegionSpecific4 = (EditText)findViewById( R.id.edt_Region_Specific4 );
        LLOffer4 = (LinearLayout)findViewById( R.id.LL_offer4 );
        spinnerOffer4 = (Spinner)findViewById( R.id.spinner_offer4 );
        LLModel4 = (LinearLayout)findViewById( R.id.LL_model4 );
        edtModel4 = (EditText)findViewById( R.id.edt_model4 );
        LLKilometers4 = (LinearLayout)findViewById( R.id.LL_kilometers4 );
        edtKilometers4 = (EditText)findViewById( R.id.edt_kilometers4 );
        LLNoOfCylinder4 = (LinearLayout)findViewById( R.id.LL_no_of_cylinder4 );
        edtNumberOfCylinder4 = (EditText)findViewById( R.id.edt_Number_of_Cylinder4 );
        LLHorsepower4 = (LinearLayout)findViewById( R.id.LL_horsepower4 );
        edtHorsepower4 = (EditText)findViewById( R.id.edt_Horsepower4 );
        LLWarranty4 = (LinearLayout)findViewById( R.id.LL_warranty4 );
        edtWarranty4 = (EditText)findViewById( R.id.edt_Warranty4 );
        LLYearOfMaking4 = (LinearLayout)findViewById( R.id.LL_year_of_making4 );
        edtYearOfMaking4 = (EditText)findViewById( R.id.edt_year_of_making4 );
        LLNumberOfDoor4 = (LinearLayout)findViewById( R.id.LL_number_of_door4 );
        spinnerNumberOfDoor4 = (Spinner)findViewById( R.id.spinner_number_of_door4 );
        LLRentRecurrencePayment4 = (LinearLayout)findViewById( R.id.LL_rent_recurrence_payment4 );
        edtIfRentRecurrenceOfPayment4 = (EditText)findViewById( R.id.edt_If_Rent_Recurrence_Of_Payment4 );
        LLBedroom4 = (LinearLayout)findViewById( R.id.LL_bedroom4 );
        SpinnerBedRoom4 = (Spinner)findViewById( R.id.Spinner_bedRoom4 );
        LLBathroom4 = (LinearLayout)findViewById( R.id.LL_bathroom4 );
        SpinnerBathroom4 = (Spinner)findViewById( R.id.Spinner_bathroom4 );
        LLDevelopers4 = (LinearLayout)findViewById( R.id.LL_developers4 );
        edtDevelopers4 = (EditText)findViewById( R.id.edt_developers4 );
        LLStatus4 = (LinearLayout)findViewById( R.id.LL_status4 );
        SpinnerStatus4 = (Spinner)findViewById( R.id.Spinner_status4 );
        LLAvailableFrom4 = (LinearLayout)findViewById( R.id.LL_available_from4 );
        txtDateFrom4 = (TextView)findViewById( R.id.txt_date_from4 );
        immgCalenderAvailableFrom4 = (ImageView)findViewById( R.id.immg_calender_available_from4 );
        LLAvailableTo4 = (LinearLayout)findViewById( R.id.LL_available_to4 );
        txtDateTo4 = (TextView)findViewById( R.id.txt_date_to4 );
        immgCalenderAvailableTo4 = (ImageView)findViewById( R.id.immg_calender_availableTo4 );
        LLSize4 = (LinearLayout)findViewById( R.id.LL_size4 );
        edtSize4 = (EditText)findViewById( R.id.edt_size4 );
        LLGender4 = (LinearLayout)findViewById( R.id.LL_gender4 );
        edtGender4 = (EditText)findViewById( R.id.edt_gender4 );
        LLNationality4 = (LinearLayout)findViewById( R.id.LL_nationality4 );
        edtNationality4 = (EditText)findViewById( R.id.edt_nationality4 );
        LLPosition4 = (LinearLayout)findViewById( R.id.LL_position4 );
        edtPosition4 = (EditText)findViewById( R.id.edt_position4 );
        LLExpectedStartDate4 = (LinearLayout)findViewById( R.id.LL_expected_start_date4 );
        edtExpectedStartDate4 = (EditText)findViewById( R.id.edt_expected_start_date4 );
        LLDegree4 = (LinearLayout)findViewById( R.id.LL_degree4 );
        edtDegree4 = (EditText)findViewById( R.id.edt_degree4 );
        LLMonthlySalary4 = (LinearLayout)findViewById( R.id.LL_monthly_salary4 );
        edtMonthlySalary4 = (EditText)findViewById( R.id.edt_monthly_salary4 );
        LLBenifits4 = (LinearLayout)findViewById( R.id.LL_benifits4 );
        edtBenifits4 = (EditText)findViewById( R.id.edt_benifits4 );
        LLCurrentPosition4 = (LinearLayout)findViewById( R.id.LL_current_position4 );
        edtCurrentPosition4 = (EditText)findViewById( R.id.edt_current_position4 );
        LLCurrentCompany4 = (LinearLayout)findViewById( R.id.LL_current_company4 );
        edtCurrentCompany4 = (EditText)findViewById( R.id.edt_current_company4 );
        LLNoticePeriod4 = (LinearLayout)findViewById( R.id.LL_notice_period4 );
        edtNoticePeriod4 = (EditText)findViewById( R.id.edt_notice_period4 );
        LLExpectedMonthlySalary4 = (LinearLayout)findViewById( R.id.LL_expected_monthly_salary4 );
        edtExpectedMonthlySalary4 = (EditText)findViewById( R.id.edt_expected_monthly_salary4 );
        LLResumeFilePath4 = (LinearLayout)findViewById( R.id.LL_resume_file_path4 );
        LLBodyTypeId4 = (LinearLayout)findViewById( R.id.LL_body_type_id4 );
        spinnerBodyTypeId4 = (Spinner)findViewById( R.id.spinner_body_type_id4 );
        LLFieldTypeId4 = (LinearLayout)findViewById( R.id.LL_field_type_id4 );
        LLCareerLevelId4 = (LinearLayout)findViewById( R.id.LL_career_level_id4 );
        LLCompanyFieldTypeId4 = (LinearLayout)findViewById( R.id.LL_company_field_type_id4 );
        LLBrand4 = (LinearLayout)findViewById( R.id.LL_brand4 );
        edtBrand4 = (EditText)findViewById( R.id.edt_brand4 );
        LLMaidRoom4 = (LinearLayout)findViewById( R.id.LL_maid_room4 );
        edtMaidRoom4 = (EditText)findViewById( R.id.edt_maid_room4 );
        LLSwimmingPool4 = (LinearLayout)findViewById( R.id.LL_swimming_pool4 );
        edtSwimmingPool4 = (EditText)findViewById( R.id.edt_swimming_pool4 );
        LLGarden4 = (LinearLayout)findViewById( R.id.LL_garden4 );
        edtGarden4 = (EditText)findViewById( R.id.edt_garden4 );
        LLPrivateParking4 = (LinearLayout)findViewById( R.id.LL_private_parking4 );
        spinnerPrivateParking4 = (Spinner)findViewById( R.id.spinner_Private_Parking4 );

        //Section Five
        txtCategoryNameTitle5 = (TextView)findViewById( R.id.txt_category_Name_title5 );
        LLCondition5 = (LinearLayout)findViewById( R.id.LL_condition5 );
        SpinnerCondition5 = (Spinner)findViewById( R.id.Spinner_condition5 );
        LLType5 = (LinearLayout)findViewById( R.id.LL_type5 );
        SpinnerType5 = (Spinner)findViewById( R.id.Spinner_type5 );
        LLUsage5 = (LinearLayout)findViewById( R.id.LL_usage5 );
        spinnerUsage5 = (Spinner)findViewById( R.id.spinner_usage5 );
        LLColor5 = (LinearLayout)findViewById( R.id.LL_color5 );
        edtColor5 = (EditText)findViewById( R.id.edt_color5 );
        LLTransmissionType5 = (LinearLayout)findViewById( R.id.LL_transmission_type5 );
        edtTransmissionType5 = (EditText)findViewById( R.id.edt_transmission_type5 );
        LLRegionSpecific5 = (LinearLayout)findViewById( R.id.LL_region_specific5 );
        edtRegionSpecific5 = (EditText)findViewById( R.id.edt_Region_Specific5 );
        LLOffer5 = (LinearLayout)findViewById( R.id.LL_offer5 );
        spinnerOffer5 = (Spinner)findViewById( R.id.spinner_offer5 );
        LLModel5 = (LinearLayout)findViewById( R.id.LL_model5 );
        edtModel5 = (EditText)findViewById( R.id.edt_model5 );
        LLKilometers5 = (LinearLayout)findViewById( R.id.LL_kilometers5 );
        edtKilometers5 = (EditText)findViewById( R.id.edt_kilometers5 );
        LLNoOfCylinder5 = (LinearLayout)findViewById( R.id.LL_no_of_cylinder5 );
        edtNumberOfCylinder5 = (EditText)findViewById( R.id.edt_Number_of_Cylinder5 );
        LLHorsepower5 = (LinearLayout)findViewById( R.id.LL_horsepower5 );
        edtHorsepower5 = (EditText)findViewById( R.id.edt_Horsepower5 );
        LLWarranty5 = (LinearLayout)findViewById( R.id.LL_warranty5 );
        edtWarranty5 = (EditText)findViewById( R.id.edt_Warranty5 );
        LLYearOfMaking5 = (LinearLayout)findViewById( R.id.LL_year_of_making5 );
        edtYearOfMaking5 = (EditText)findViewById( R.id.edt_year_of_making5 );
        LLNumberOfDoor5 = (LinearLayout)findViewById( R.id.LL_number_of_door5 );
        spinnerNumberOfDoor5 = (Spinner)findViewById( R.id.spinner_number_of_door5 );
        LLRentRecurrencePayment5 = (LinearLayout)findViewById( R.id.LL_rent_recurrence_payment5 );
        edtIfRentRecurrenceOfPayment5 = (EditText)findViewById( R.id.edt_If_Rent_Recurrence_Of_Payment5 );
        LLBedroom5 = (LinearLayout)findViewById( R.id.LL_bedroom5 );
        SpinnerBedRoom5 = (Spinner)findViewById( R.id.Spinner_bedRoom5 );
        LLBathroom5 = (LinearLayout)findViewById( R.id.LL_bathroom5 );
        SpinnerBathroom5 = (Spinner)findViewById( R.id.Spinner_bathroom5 );
        LLDevelopers5 = (LinearLayout)findViewById( R.id.LL_developers5 );
        edtDevelopers5 = (EditText)findViewById( R.id.edt_developers5 );
        LLStatus5 = (LinearLayout)findViewById( R.id.LL_status5 );
        SpinnerStatus5 = (Spinner)findViewById( R.id.Spinner_status5 );
        LLAvailableFrom5 = (LinearLayout)findViewById( R.id.LL_available_from5 );
        txtDateFrom5 = (TextView)findViewById( R.id.txt_date_from5 );
        immgCalenderAvailableFrom5 = (ImageView)findViewById( R.id.immg_calender_available_from5 );
        LLAvailableTo5 = (LinearLayout)findViewById( R.id.LL_available_to5 );
        txtDateTo5 = (TextView)findViewById( R.id.txt_date_to5 );
        immgCalenderAvailableTo5 = (ImageView)findViewById( R.id.immg_calender_availableTo5 );
        LLSize5 = (LinearLayout)findViewById( R.id.LL_size5 );
        edtSize5 = (EditText)findViewById( R.id.edt_size5 );
        LLGender5 = (LinearLayout)findViewById( R.id.LL_gender5 );
        edtGender5 = (EditText)findViewById( R.id.edt_gender5 );
        LLNationality5 = (LinearLayout)findViewById( R.id.LL_nationality5 );
        edtNationality5 = (EditText)findViewById( R.id.edt_nationality5 );
        LLPosition5 = (LinearLayout)findViewById( R.id.LL_position5 );
        edtPosition5 = (EditText)findViewById( R.id.edt_position5 );
        LLExpectedStartDate5 = (LinearLayout)findViewById( R.id.LL_expected_start_date5 );
        edtExpectedStartDate5 = (EditText)findViewById( R.id.edt_expected_start_date5 );
        LLDegree5 = (LinearLayout)findViewById( R.id.LL_degree5 );
        edtDegree5 = (EditText)findViewById( R.id.edt_degree5 );
        LLMonthlySalary5 = (LinearLayout)findViewById( R.id.LL_monthly_salary5 );
        edtMonthlySalary5 = (EditText)findViewById( R.id.edt_monthly_salary5 );
        LLBenifits5 = (LinearLayout)findViewById( R.id.LL_benifits5 );
        edtBenifits5 = (EditText)findViewById( R.id.edt_benifits5 );
        LLCurrentPosition5 = (LinearLayout)findViewById( R.id.LL_current_position5 );
        edtCurrentPosition5 = (EditText)findViewById( R.id.edt_current_position5 );
        LLCurrentCompany5 = (LinearLayout)findViewById( R.id.LL_current_company5 );
        edtCurrentCompany5 = (EditText)findViewById( R.id.edt_current_company5 );
        LLNoticePeriod5 = (LinearLayout)findViewById( R.id.LL_notice_period5 );
        edtNoticePeriod5 = (EditText)findViewById( R.id.edt_notice_period5 );
        LLExpectedMonthlySalary5 = (LinearLayout)findViewById( R.id.LL_expected_monthly_salary5 );
        edtExpectedMonthlySalary5 = (EditText)findViewById( R.id.edt_expected_monthly_salary5 );
        LLResumeFilePath5 = (LinearLayout)findViewById( R.id.LL_resume_file_path5 );
        LLBodyTypeId5 = (LinearLayout)findViewById( R.id.LL_body_type_id5 );
        spinnerBodyTypeId5 = (Spinner)findViewById( R.id.spinner_body_type_id5 );
        LLFieldTypeId5 = (LinearLayout)findViewById( R.id.LL_field_type_id5 );
        LLCareerLevelId5 = (LinearLayout)findViewById( R.id.LL_career_level_id5 );
        LLCompanyFieldTypeId5 = (LinearLayout)findViewById( R.id.LL_company_field_type_id5 );
        LLBrand5 = (LinearLayout)findViewById( R.id.LL_brand5 );
        edtBrand5 = (EditText)findViewById( R.id.edt_brand5 );
        LLMaidRoom5 = (LinearLayout)findViewById( R.id.LL_maid_room5 );
        edtMaidRoom5 = (EditText)findViewById( R.id.edt_maid_room5 );
        LLSwimmingPool5 = (LinearLayout)findViewById( R.id.LL_swimming_pool5 );
        edtSwimmingPool5 = (EditText)findViewById( R.id.edt_swimming_pool5 );
        LLGarden5 = (LinearLayout)findViewById( R.id.LL_garden5 );
        edtGarden5 = (EditText)findViewById( R.id.edt_garden5 );
        LLPrivateParking5 = (LinearLayout)findViewById( R.id.LL_private_parking5 );
        spinnerPrivateParking5 = (Spinner)findViewById( R.id.spinner_Private_Parking5 );

        edt_field_type_id5 =(EditText) findViewById(R.id.edt_field_type_id5) ;
        edt_career_level_id5 =(EditText) findViewById(R.id.edt_career_level_id5) ;
        edt_company_field_type_id5 =(EditText) findViewById(R.id.edt_company_field_type_id5) ;


        edt_field_type_id2 =(EditText) findViewById(R.id.edt_field_type_id2) ;
        edt_career_level_id2 =(EditText) findViewById(R.id.edt_career_level_id2) ;
        edt_company_field_type_id2 =(EditText) findViewById(R.id.edt_company_field_type_id2) ;


        edt_field_type_id3 =(EditText) findViewById(R.id.edt_field_type_id3) ;
        edt_career_level_id3 =(EditText) findViewById(R.id.edt_career_level_id3) ;
        edt_company_field_type_id3 =(EditText) findViewById(R.id.edt_company_field_type_id3) ;

        edt_field_type_id4 =(EditText) findViewById(R.id.edt_field_type_id4) ;
        edt_career_level_id4 =(EditText) findViewById(R.id.edt_career_level_id4) ;
        edt_company_field_type_id4 =(EditText) findViewById(R.id.edt_company_field_type_id4) ;


    }

    public void CreateNormalAdsAddSection(String title, String contactEmail, String contactNumber, String description, String youtubeLink, String priceAds, String companyName, String webside, String specific, String offerDetaails,
                                          String condition, String location, String country, String state, String city, String address, String jsonField, String mediaPAth, ArrayList<String> arrStr){

        String user_id=  Preference.get(HarshitCreateNormalAdd.this,Preference.KEY_USER_ID);

        String access_token = Preference.get(HarshitCreateNormalAdd.this,Preference.KEY_accessToken);

        HashMap<String, String> map = new HashMap<>();

        map.put("access_token",access_token);
        map.put("package_id","1");
        map.put("purchase_date","2019/12/10");
        map.put("package_expiry","2019/12/10");
        map.put("package_price","150");
        map.put("listing_title",title);
        map.put("listing_content","");
        map.put("isting_expiration_date","");
        map.put("listing_price","");
        map.put("specifics",specific);
        map.put("email",contactEmail);
        map.put("contact_number",contactNumber);
        map.put("company_name","");
        map.put("website","");
        map.put("location",Location);
        map.put("listing_status","");
        map.put("offer_details","");
        map.put("offer_valid_start","");
        map.put("offer_valid_end","");
        map.put("keyword", String.valueOf(arrStr));
        map.put("fields",jsonField);
        map.put("type_arr",jsonField);
        map.put("media",mediaPAth);

        ApiRequest apiRequest = new ApiRequest(HarshitCreateNormalAdd.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_Submitads, Constants.USER_Submitads,map, Request.Method.POST);
    }




    public void CreateNormalMethod(String category_id){

        HashMap<String, String> map = new HashMap<>();

        map.put("category_id",category_id);

        ApiRequest apiRequest = new ApiRequest(HarshitCreateNormalAdd.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_fieldsByCategoryId, Constants.USER_fieldsByCategoryId,map, Request.Method.POST);

    }

    public void KeyWordMethod(){

        HashMap<String, String> map = new HashMap<>();

        ApiRequest apiRequest = new ApiRequest(HarshitCreateNormalAdd.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_getKeyword, Constants.USER_getKeyword,map, Request.Method.GET);

    }

    public void CreateNormalAddSection(String category_id){

        isCategory=true;

        HashMap<String, String> map = new HashMap<>();

        map.put("category_id",category_id);
        map.put("is_mobile","1");

        ApiRequest apiRequest = new ApiRequest(HarshitCreateNormalAdd.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_Select_Occation, Constants.USER_Select_Occation,map, Request.Method.POST);

    }

    public void CreateNormalSubAddSection(String parent_category_id){

        isSubCategory=true;

        HashMap<String, String> map = new HashMap<>();

        map.put("parent_category_id",parent_category_id);

        map.put("is_mobile","1");

        ApiRequest apiRequest = new ApiRequest(HarshitCreateNormalAdd.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_Select_Occation_sub, Constants.USER_Select_Occation_sub,map, Request.Method.POST);
    }

    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_getKeyword.equalsIgnoreCase(tag_json_obj)) {

            if (!response.equalsIgnoreCase(null)) {

                KeywordModel finalArray = new Gson().fromJson(response, new TypeToken<KeywordModel>() {
                }.getType());

                String status = String.valueOf(finalArray.getErrorCode());

                if (status.equalsIgnoreCase("0")) {


                    modellistKeyword= (ArrayList<KeywordDataModel>) finalArray.getData();

                    KeyWord_addCustomAdapterSpinner  customAdapter1_Intrested=new KeyWord_addCustomAdapterSpinner(this,modellistKeyword);

                    spinner_pst_Keyword.setAdapter(customAdapter1_Intrested);

                }
            }
        } else if (Constants.USER_Submitads.equalsIgnoreCase(tag_json_obj)) {

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

                    Intent intent = new Intent(HarshitCreateNormalAdd.this, PaypalActivity.class);
                    startActivity(intent);
                }
            }


            submit_btn_add.setEnabled(true);

        } else if(Constants.USER_fieldsByCategoryId.equalsIgnoreCase(tag_json_obj)){
            mProgressBar.setVisibility(View.GONE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            if(isSetionOne)
            {

                if (!response.equalsIgnoreCase(null))
                {
                    getSectionOnefinalArray = new Gson().fromJson(response,new TypeToken<CreateNormal>(){}.getType());

                    String condition= getSectionOnefinalArray.getData().get(0).getCondition().getFlag().toString();

                    if(condition.equalsIgnoreCase("1"))
                    {
                        LL_condition.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionOnefinalArray.getData().get(0).getCondition().getInfo();
                        FuncSpinner(modelList_info,Spinner_condition);


                    }else if(condition.equalsIgnoreCase("0"))
                    {
                        LL_condition.setVisibility(View.GONE);

                    }
                    String type= getSectionOnefinalArray.getData().get(0).getType().getFlag().toString();
                    if(type.equalsIgnoreCase("1"))
                    {

                        modelList_info = (ArrayList<Info>) getSectionOnefinalArray.getData().get(0).getType().getInfo();

                        // FuncSpinner(modelList_info,Spinner_type_new);

                        CreateNormalAdsCustomAdapter customAdapter=new CreateNormalAdsCustomAdapter(HarshitCreateNormalAdd.this ,modelList_info);
                        Spinner_type_new.setAdapter(customAdapter);

                            setAdapter_multiselction_typ(modelList_info);

                        LLType.setVisibility(View.VISIBLE);

                    }else if(type.equalsIgnoreCase("0"))
                    {
                        LLType.setVisibility(View.GONE);
                    }
                    String usage= getSectionOnefinalArray.getData().get(0).getUsage().getFlag().toString();
                    if(usage.equalsIgnoreCase("1"))
                    {

                        LLUsage.setVisibility(View.VISIBLE);

                    }else if(usage.equalsIgnoreCase("0"))
                    {
                        LLUsage.setVisibility(View.GONE);

                    }
                    String color= getSectionOnefinalArray.getData().get(0).getColor().getFlag().toString();
                    if(color.equalsIgnoreCase("1"))
                    {
                        LLColor.setVisibility(View.VISIBLE);

                    }else if(color.equalsIgnoreCase("0"))
                    {
                        LLColor.setVisibility(View.GONE);
                    }
                    String transmission_type= getSectionOnefinalArray.getData().get(0).getTransmissionType().getFlag().toString();
                    if(transmission_type.equalsIgnoreCase("1"))
                    {
                        LLTransmissionType.setVisibility(View.VISIBLE);

                    }else if(transmission_type.equalsIgnoreCase("0"))
                    {
                        LLTransmissionType.setVisibility(View.GONE);
                    }
                    String region_specific= getSectionOnefinalArray.getData().get(0).getRegionSpecific().getFlag().toString();
                    if(region_specific.equalsIgnoreCase("1"))
                    {
                        LLRegionSpecific.setVisibility(View.VISIBLE);

                    }else if(region_specific.equalsIgnoreCase("0"))
                    {
                        LLRegionSpecific.setVisibility(View.GONE);
                    }
                    String offer= getSectionOnefinalArray.getData().get(0).getOffer().getFlag().toString();
                    if(offer.equalsIgnoreCase("1"))
                    {
                        modelList_info = (ArrayList<Info>) getSectionOnefinalArray.getData().get(0).getOffer().getInfo();
                        FuncSpinner(modelList_info,spinner_offer);

                        LLOffer.setVisibility(View.VISIBLE);

                    }else if(offer.equalsIgnoreCase("0"))
                    {
                        LLOffer.setVisibility(View.GONE);
                    }
                    String model= getSectionOnefinalArray.getData().get(0).getModel().getFlag().toString();
                    if(model.equalsIgnoreCase("1"))
                    {
                        LL_model.setVisibility(View.VISIBLE);

                    }else if(model.equalsIgnoreCase("0"))
                    {
                        LL_model.setVisibility(View.GONE);

                    }
                    String kilometers= getSectionOnefinalArray.getData().get(0).getKilometers().getFlag().toString();
                    if(kilometers.equalsIgnoreCase("1"))
                    {
                        LLKilometers.setVisibility(View.VISIBLE);

                    }else if(kilometers.equalsIgnoreCase("0"))
                    {
                        LLKilometers.setVisibility(View.GONE);
                    }
                    String no_of_cylinder= getSectionOnefinalArray.getData().get(0).getNoOfCylinder().getFlag().toString();
                    if(no_of_cylinder.equalsIgnoreCase("1"))
                    {
                        LLNoOfCylinder.setVisibility(View.VISIBLE);

                    }else if(no_of_cylinder.equalsIgnoreCase("0"))
                    {
                        LLNoOfCylinder.setVisibility(View.GONE);
                    }
                    String horsepower= getSectionOnefinalArray.getData().get(0).getHorsepower().getFlag().toString();
                    if(horsepower.equalsIgnoreCase("1"))
                    {
                        LL_horsepower.setVisibility(View.VISIBLE);

                    }else if(horsepower.equalsIgnoreCase("0"))
                    {
                        LL_horsepower.setVisibility(View.GONE);
                    }
                    String warranty= getSectionOnefinalArray.getData().get(0).getWarranty().getFlag().toString();
                    if(warranty.equalsIgnoreCase("1"))
                    {
                        LLWarranty.setVisibility(View.VISIBLE);

                    }else if(warranty.equalsIgnoreCase("0"))
                    {
                        LLWarranty.setVisibility(View.GONE);
                    }
                    String year_of_making= getSectionOnefinalArray.getData().get(0).getYearOfMaking().getFlag().toString();
                    if(year_of_making.equalsIgnoreCase("1"))
                    {
                        LLYearOfMaking.setVisibility(View.VISIBLE);

                    }else if(year_of_making.equalsIgnoreCase("0"))
                    {
                        LLYearOfMaking.setVisibility(View.GONE);

                    }
                    String number_of_door= getSectionOnefinalArray.getData().get(0).getNumberOfDoor().getFlag().toString();
                    if(number_of_door.equalsIgnoreCase("1"))
                    {
                        LLNumberOfDoor.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionOnefinalArray.getData().get(0).getNumberOfDoor().getInfo();

                        FuncSpinner(modelList_info,spinner_number_of_door);

                    }else if(number_of_door.equalsIgnoreCase("0"))
                    {
                        LLNumberOfDoor.setVisibility(View.GONE);
                    }
                    String rent_recurrence_payment= getSectionOnefinalArray.getData().get(0).getRentRecurrencePayment().getFlag().toString();
                    if(rent_recurrence_payment.equalsIgnoreCase("1"))
                    {
                        LLRentRecurrencePayment.setVisibility(View.VISIBLE);

                    }else if(rent_recurrence_payment.equalsIgnoreCase("0"))
                    {
                        LLRentRecurrencePayment.setVisibility(View.GONE);
                    }
                    String bedroom= getSectionOnefinalArray.getData().get(0).getBedroom().getFlag().toString();
                    if(bedroom.equalsIgnoreCase("1"))
                    {
                        LLBedroom.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionOnefinalArray.getData().get(0).getBedroom().getInfo();

                        FuncSpinner(modelList_info,Spinner_bathroom);

                    }else if(bedroom.equalsIgnoreCase("0"))
                    {
                        LLBedroom.setVisibility(View.GONE);
                    }

                    String bathroom= getSectionOnefinalArray.getData().get(0).getBathroom().getFlag().toString();
                    if(bathroom.equalsIgnoreCase("1"))
                    {
                        LLBathroom.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionOnefinalArray.getData().get(0).getBathroom().getInfo();

                        FuncSpinner(modelList_info,Spinner_bedRoom);


                    }else if(bathroom.equalsIgnoreCase("0"))
                    {
                        LLBathroom.setVisibility(View.GONE);
                    }
                    String developers= getSectionOnefinalArray.getData().get(0).getDevelopers().getFlag().toString();
                    if(developers.equalsIgnoreCase("1"))
                    {
                        LLDevelopers.setVisibility(View.VISIBLE);

                    }else if(developers.equalsIgnoreCase("0"))
                    {
                        LLDevelopers.setVisibility(View.GONE);

                    }
                    String status= getSectionOnefinalArray.getData().get(0).getStatus().getFlag().toString();
                    if(status.equalsIgnoreCase("1"))
                    {
                        LLStatus.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionOnefinalArray.getData().get(0).getStatus().getInfo();

                        FuncSpinner(modelList_info,Spinner_status);

                    }else if(status.equalsIgnoreCase("0"))
                    {
                        LLStatus.setVisibility(View.GONE);
                    }
                    String available_from= getSectionOnefinalArray.getData().get(0).getAvailableFrom().getFlag().toString();
                    if(available_from.equalsIgnoreCase("1"))
                    {
                        LLAvailableFrom.setVisibility(View.VISIBLE);

                    }else if(available_from.equalsIgnoreCase("0"))
                    {
                        LLAvailableFrom.setVisibility(View.GONE);
                    }
                    String available_to= getSectionOnefinalArray.getData().get(0).getAvailableTo().getFlag().toString();
                    if(available_to.equalsIgnoreCase("1"))
                    {
                        LLAvailableTo.setVisibility(View.VISIBLE);

                    }else if(available_to.equalsIgnoreCase("0"))
                    {
                        LLAvailableTo.setVisibility(View.GONE);
                    }
                    String size= getSectionOnefinalArray.getData().get(0).getSize().getFlag().toString();
                    if(size.equalsIgnoreCase("1"))
                    {
                        LLSize.setVisibility(View.VISIBLE);

                    }else if(size.equalsIgnoreCase("0"))
                    {
                        LLSize.setVisibility(View.GONE);
                    }
                    String gender= getSectionOnefinalArray.getData().get(0).getGender().getFlag().toString();
                    if(gender.equalsIgnoreCase("1"))
                    {
                        LLGender.setVisibility(View.VISIBLE);

                    }else if(gender.equalsIgnoreCase("0"))
                    {
                        LLGender.setVisibility(View.GONE);

                    }
                    String nationality= getSectionOnefinalArray.getData().get(0).getNationality().getFlag().toString();
                    if(nationality.equalsIgnoreCase("1"))
                    {
                        LLNationality.setVisibility(View.VISIBLE);

                    }else if(nationality.equalsIgnoreCase("0"))
                    {
                        LLNationality.setVisibility(View.GONE);
                    }
                    String position= getSectionOnefinalArray.getData().get(0).getPosition().getFlag().toString();
                    if(position.equalsIgnoreCase("1"))
                    {
                        LLPosition.setVisibility(View.VISIBLE);

                    }else if(position.equalsIgnoreCase("0"))
                    {
                        LLPosition.setVisibility(View.GONE);
                    }
                    String expected_start_date= getSectionOnefinalArray.getData().get(0).getExpectedStartDate().getFlag().toString();
                    if(expected_start_date.equalsIgnoreCase("1"))
                    {
                        LLExpectedStartDate.setVisibility(View.VISIBLE);

                    }else if(expected_start_date.equalsIgnoreCase("0"))
                    {
                        LLExpectedStartDate.setVisibility(View.GONE);
                    }
                    String degree= getSectionOnefinalArray.getData().get(0).getDegree().getFlag().toString();
                    if(degree.equalsIgnoreCase("1"))
                    {
                        LLDegree.setVisibility(View.VISIBLE);

                    }else if(degree.equalsIgnoreCase("0"))
                    {
                        LLDegree.setVisibility(View.GONE);

                    }
                    String monthly_salary= getSectionOnefinalArray.getData().get(0).getMonthlySalary().getFlag().toString();
                    if(monthly_salary.equalsIgnoreCase("1"))
                    {
                        LLMonthlySalary.setVisibility(View.VISIBLE);

                    }else if(monthly_salary.equalsIgnoreCase("0"))
                    {
                        LLMonthlySalary.setVisibility(View.GONE);
                    }
                    String benifits= getSectionOnefinalArray.getData().get(0).getBenifits().getFlag().toString();
                    if(benifits.equalsIgnoreCase("1"))
                    {
                        LLBenifits.setVisibility(View.VISIBLE);

                    }else if(benifits.equalsIgnoreCase("0"))
                    {
                        LLBenifits.setVisibility(View.GONE);
                    }
                    String current_position= getSectionOnefinalArray.getData().get(0).getCurrentPosition().getFlag().toString();
                    if(current_position.equalsIgnoreCase("1"))
                    {
                        LLCurrentPosition.setVisibility(View.VISIBLE);

                    }else if(current_position.equalsIgnoreCase("0"))
                    {
                        LLCurrentPosition.setVisibility(View.GONE);
                    }
                    String current_company= getSectionOnefinalArray.getData().get(0).getCurrentCompany().getFlag().toString();
                    if(current_company.equalsIgnoreCase("1"))
                    {
                        LLCurrentCompany.setVisibility(View.VISIBLE);

                    }else if(current_company.equalsIgnoreCase("0"))
                    {
                        LLCurrentCompany.setVisibility(View.GONE);
                    }
                    String notice_period= getSectionOnefinalArray.getData().get(0).getNoticePeriod().getFlag().toString();
                    if(notice_period.equalsIgnoreCase("1"))
                    {
                        LLNoticePeriod.setVisibility(View.VISIBLE);

                    }else if(notice_period.equalsIgnoreCase("0"))
                    {
                        LLNoticePeriod.setVisibility(View.GONE);
                    }
                    String expected_monthly_salary= getSectionOnefinalArray.getData().get(0).getExpectedMonthlySalary().getFlag().toString();
                    if(expected_monthly_salary.equalsIgnoreCase("1"))
                    {
                        LLExpectedMonthlySalary.setVisibility(View.VISIBLE);

                    }else if(expected_monthly_salary.equalsIgnoreCase("0"))
                    {
                        LLExpectedMonthlySalary.setVisibility(View.GONE);
                    }
                    String resume_file_path= getSectionOnefinalArray.getData().get(0).getResumeFilePath().getFlag().toString();
                    if(resume_file_path.equalsIgnoreCase("1"))
                    {
                        LLResumeFilePath.setVisibility(View.VISIBLE);

                    }else if(resume_file_path.equalsIgnoreCase("0"))
                    {
                        LLResumeFilePath.setVisibility(View.GONE);
                    }
                    String body_type_id= getSectionOnefinalArray.getData().get(0).getBodyTypeId().getFlag().toString();
                    if(body_type_id.equalsIgnoreCase("1"))
                    {
                        LLBodyTypeId.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionOnefinalArray.getData().get(0).getBodyTypeId().getInfo();

                        FuncSpinner(modelList_info,spinner_body_type_id);

                    }else if(body_type_id.equalsIgnoreCase("0"))
                    {
                        LLBodyTypeId.setVisibility(View.GONE);
                    }
                    String field_type_id= getSectionOnefinalArray.getData().get(0).getFieldTypeId().getFlag().toString();
                    if(field_type_id.equalsIgnoreCase("1"))
                    {
                        LLFieldTypeId.setVisibility(View.VISIBLE);

                    }else if(field_type_id.equalsIgnoreCase("0"))
                    {
                        LLFieldTypeId.setVisibility(View.GONE);
                    }
                    String career_level_id= getSectionOnefinalArray.getData().get(0).getCareerLevelId().getFlag().toString();
                    if(career_level_id.equalsIgnoreCase("1"))
                    {
                        LLCareerLevelId.setVisibility(View.VISIBLE);

                    }else if(career_level_id.equalsIgnoreCase("0"))
                    {
                        LLCareerLevelId.setVisibility(View.GONE);
                    }
                    String company_field_type_id= getSectionOnefinalArray.getData().get(0).getCompanyFieldTypeId().getFlag().toString();
                    if(company_field_type_id.equalsIgnoreCase("1"))
                    {
                        LLCompanyFieldTypeId.setVisibility(View.VISIBLE);

                    }else if(company_field_type_id.equalsIgnoreCase("0"))
                    {
                        LLCompanyFieldTypeId.setVisibility(View.GONE);
                    }
                    String brand= getSectionOnefinalArray.getData().get(0).getBrand().getFlag().toString();
                    if(brand.equalsIgnoreCase("1"))
                    {
                        LLBrand.setVisibility(View.VISIBLE);

                    }else if(brand.equalsIgnoreCase("0"))
                    {
                        LLBrand.setVisibility(View.GONE);
                    }
                    String maid_room= getSectionOnefinalArray.getData().get(0).getMaidRoom().getFlag().toString();
                    if(maid_room.equalsIgnoreCase("1"))
                    {
                        LLMaidRoom.setVisibility(View.VISIBLE);

                    }else if(maid_room.equalsIgnoreCase("0"))
                    {
                        LLMaidRoom.setVisibility(View.GONE);
                    }
                    String swimming_pool= getSectionOnefinalArray.getData().get(0).getSwimmingPool().getFlag().toString();
                    if(swimming_pool.equalsIgnoreCase("1"))
                    {
                        LLSwimmingPool.setVisibility(View.VISIBLE);

                    }else if(swimming_pool.equalsIgnoreCase("0"))
                    {
                        LLSwimmingPool.setVisibility(View.GONE);
                    }
                    String garden= getSectionOnefinalArray.getData().get(0).getGarden().getFlag().toString();
                    if(garden.equalsIgnoreCase("1"))
                    {
                        LLGarden.setVisibility(View.VISIBLE);

                    }else if(garden.equalsIgnoreCase("0"))
                    {
                        LLGarden.setVisibility(View.GONE);
                    }
                    String private_parking= getSectionOnefinalArray.getData().get(0).getPrivateParking().getFlag().toString();
                    if(private_parking.equalsIgnoreCase("1"))
                    {
                        LLPrivateParking.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionOnefinalArray.getData().get(0).getPrivateParking().getInfo();

                        FuncSpinner(modelList_info,spinner_Private_Parking);

                    }else if(private_parking.equalsIgnoreCase("0"))
                    {
                        LLPrivateParking.setVisibility(View.GONE);
                    }
                }
            }
            if(isSetionTwo)
            {
                if (!response.equalsIgnoreCase(null))
                {

                    getSectionTwofinalArray = new Gson().fromJson(response,new TypeToken<CreateNormal>(){}.getType());

                    String condition= getSectionTwofinalArray.getData().get(0).getCondition().getFlag().toString();

                    if(condition.equalsIgnoreCase("1"))
                    {
                        LL_condition2.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionTwofinalArray.getData().get(0).getCondition().getInfo();

                        FuncSpinner2(modelList_info,Spinner_condition2);

                    }else if(condition.equalsIgnoreCase("0"))
                    {
                        LL_condition2.setVisibility(View.GONE);
                    }
                    String type= getSectionTwofinalArray.getData().get(0).getType().getFlag().toString();

                    if(type.equalsIgnoreCase("1"))
                    {

                        modelList_info = (ArrayList<Info>) getSectionTwofinalArray.getData().get(0).getType().getInfo();
                        FuncSpinner2(modelList_info,Spinner_type2);

                        LL_type2.setVisibility(View.VISIBLE);

                    }else if(type.equalsIgnoreCase("0"))
                    {
                        LL_type2.setVisibility(View.GONE);
                    }
                    String usage= getSectionTwofinalArray.getData().get(0).getUsage().getFlag().toString();
                    if(usage.equalsIgnoreCase("1"))
                    {

                        LLUsage2.setVisibility(View.VISIBLE);

                    }else if(usage.equalsIgnoreCase("0"))
                    {
                        LLUsage2.setVisibility(View.GONE);

                    }
                    String color= getSectionTwofinalArray.getData().get(0).getColor().getFlag().toString();
                    if(color.equalsIgnoreCase("1"))
                    {
                        LLColor2.setVisibility(View.VISIBLE);

                    }else if(color.equalsIgnoreCase("0"))
                    {
                        LLColor2.setVisibility(View.GONE);
                    }
                    String transmission_type= getSectionTwofinalArray.getData().get(0).getTransmissionType().getFlag().toString();
                    if(transmission_type.equalsIgnoreCase("1"))
                    {
                        LLTransmissionType2.setVisibility(View.VISIBLE);

                    }else if(transmission_type.equalsIgnoreCase("0"))
                    {
                        LLTransmissionType2.setVisibility(View.GONE);
                    }
                    String region_specific= getSectionTwofinalArray.getData().get(0).getRegionSpecific().getFlag().toString();
                    if(region_specific.equalsIgnoreCase("1"))
                    {
                        LLRegionSpecific2.setVisibility(View.VISIBLE);

                    }else if(region_specific.equalsIgnoreCase("0"))
                    {
                        LLRegionSpecific2.setVisibility(View.GONE);
                    }
                    String offer= getSectionTwofinalArray.getData().get(0).getOffer().getFlag().toString();
                    if(offer.equalsIgnoreCase("1"))
                    {

                        LLOffer2.setVisibility(View.VISIBLE);

                    }else if(offer.equalsIgnoreCase("0"))
                    {
                        LLOffer2.setVisibility(View.GONE);
                    }
                    String model= getSectionTwofinalArray.getData().get(0).getModel().getFlag().toString();
                    if(model.equalsIgnoreCase("1"))
                    {
                        LLModel2.setVisibility(View.VISIBLE);

                    }else if(model.equalsIgnoreCase("0"))
                    {
                        LLModel2.setVisibility(View.GONE);

                    }
                    String kilometers= getSectionTwofinalArray.getData().get(0).getKilometers().getFlag().toString();
                    if(kilometers.equalsIgnoreCase("1"))
                    {
                        LLKilometers2.setVisibility(View.VISIBLE);

                    }else if(kilometers.equalsIgnoreCase("0"))
                    {
                        LLKilometers2.setVisibility(View.GONE);
                    }
                    String no_of_cylinder= getSectionTwofinalArray.getData().get(0).getNoOfCylinder().getFlag().toString();
                    if(no_of_cylinder.equalsIgnoreCase("1"))
                    {
                        LLNoOfCylinder2.setVisibility(View.VISIBLE);

                    }else if(no_of_cylinder.equalsIgnoreCase("0"))
                    {
                        LLNoOfCylinder2.setVisibility(View.GONE);
                    }
                    String horsepower= getSectionTwofinalArray.getData().get(0).getHorsepower().getFlag().toString();
                    if(horsepower.equalsIgnoreCase("1"))
                    {
                        LLHorsepower2.setVisibility(View.VISIBLE);

                    }else if(horsepower.equalsIgnoreCase("0"))
                    {
                        LLHorsepower2.setVisibility(View.GONE);
                    }
                    String warranty= getSectionTwofinalArray.getData().get(0).getWarranty().getFlag().toString();
                    if(warranty.equalsIgnoreCase("1"))
                    {
                        LLWarranty2.setVisibility(View.VISIBLE);

                    }else if(warranty.equalsIgnoreCase("0"))
                    {
                        LLWarranty2.setVisibility(View.GONE);
                    }
                    String year_of_making= getSectionTwofinalArray.getData().get(0).getYearOfMaking().getFlag().toString();
                    if(year_of_making.equalsIgnoreCase("1"))
                    {
                        LLYearOfMaking2.setVisibility(View.VISIBLE);

                    }else if(year_of_making.equalsIgnoreCase("0"))
                    {
                        LLYearOfMaking2.setVisibility(View.GONE);

                    }
                    String number_of_door= getSectionTwofinalArray.getData().get(0).getNumberOfDoor().getFlag().toString();
                    if(number_of_door.equalsIgnoreCase("1"))
                    {
                        LLNumberOfDoor2.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionTwofinalArray.getData().get(0).getNumberOfDoor().getInfo();

                        FuncSpinner2(modelList_info,spinnerNumberOfDoor2);

                    }else if(number_of_door.equalsIgnoreCase("0"))
                    {
                        LLNumberOfDoor2.setVisibility(View.GONE);
                    }
                    String rent_recurrence_payment= getSectionTwofinalArray.getData().get(0).getRentRecurrencePayment().getFlag().toString();
                    if(rent_recurrence_payment.equalsIgnoreCase("1"))
                    {
                        LLRentRecurrencePayment2.setVisibility(View.VISIBLE);

                    }else if(rent_recurrence_payment.equalsIgnoreCase("0"))
                    {
                        LLRentRecurrencePayment2.setVisibility(View.GONE);
                    }
                    String bedroom= getSectionTwofinalArray.getData().get(0).getBedroom().getFlag().toString();
                    if(bedroom.equalsIgnoreCase("1"))
                    {
                        LLBedroom2.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionTwofinalArray.getData().get(0).getBedroom().getInfo();

                        FuncSpinner2(modelList_info,SpinnerBathroom2);

                    }else if(bedroom.equalsIgnoreCase("0"))
                    {
                        LLBedroom2.setVisibility(View.GONE);
                    }

                    String bathroom= getSectionTwofinalArray.getData().get(0).getBathroom().getFlag().toString();
                    if(bathroom.equalsIgnoreCase("1"))
                    {
                        LLBathroom2.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionTwofinalArray.getData().get(0).getBathroom().getInfo();

                        FuncSpinner2(modelList_info,SpinnerBedRoom2);


                    }else if(bathroom.equalsIgnoreCase("0"))
                    {
                        LLBathroom2.setVisibility(View.GONE);
                    }
                    String developers= getSectionTwofinalArray.getData().get(0).getDevelopers().getFlag().toString();
                    if(developers.equalsIgnoreCase("1"))
                    {
                        LLDevelopers2.setVisibility(View.VISIBLE);

                    }else if(developers.equalsIgnoreCase("0"))
                    {
                        LLDevelopers2.setVisibility(View.GONE);

                    }
                    String status= getSectionTwofinalArray.getData().get(0).getStatus().getFlag().toString();
                    if(status.equalsIgnoreCase("1"))
                    {
                        LLStatus2.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionTwofinalArray.getData().get(0).getStatus().getInfo();

                        FuncSpinner2(modelList_info,SpinnerStatus2);

                    }else if(status.equalsIgnoreCase("0"))
                    {
                        LLStatus2.setVisibility(View.GONE);
                    }
                    String available_from= getSectionTwofinalArray.getData().get(0).getAvailableFrom().getFlag().toString();
                    if(available_from.equalsIgnoreCase("1"))
                    {
                        LLAvailableFrom2.setVisibility(View.VISIBLE);

                    }else if(available_from.equalsIgnoreCase("0"))
                    {
                        LLAvailableFrom2.setVisibility(View.GONE);
                    }
                    String available_to= getSectionTwofinalArray.getData().get(0).getAvailableTo().getFlag().toString();
                    if(available_to.equalsIgnoreCase("1"))
                    {
                        LLAvailableTo2.setVisibility(View.VISIBLE);

                    }else if(available_to.equalsIgnoreCase("0"))
                    {
                        LLAvailableTo2.setVisibility(View.GONE);
                    }
                    String size= getSectionTwofinalArray.getData().get(0).getSize().getFlag().toString();
                    if(size.equalsIgnoreCase("1"))
                    {
                        LLSize2.setVisibility(View.VISIBLE);

                    }else if(size.equalsIgnoreCase("0"))
                    {
                        LLSize2.setVisibility(View.GONE);
                    }
                    String gender= getSectionTwofinalArray.getData().get(0).getGender().getFlag().toString();
                    if(gender.equalsIgnoreCase("1"))
                    {
                        LLGender2.setVisibility(View.VISIBLE);

                    }else if(gender.equalsIgnoreCase("0"))
                    {
                        LLGender2.setVisibility(View.GONE);

                    }
                    String nationality= getSectionTwofinalArray.getData().get(0).getNationality().getFlag().toString();
                    if(nationality.equalsIgnoreCase("1"))
                    {
                        LLNationality2.setVisibility(View.VISIBLE);

                    }else if(nationality.equalsIgnoreCase("0"))
                    {
                        LLNationality2.setVisibility(View.GONE);
                    }
                    String position= getSectionTwofinalArray.getData().get(0).getPosition().getFlag().toString();
                    if(position.equalsIgnoreCase("1"))
                    {
                        LLPosition2.setVisibility(View.VISIBLE);

                    }else if(position.equalsIgnoreCase("0"))
                    {
                        LLPosition2.setVisibility(View.GONE);
                    }
                    String expected_start_date= getSectionTwofinalArray.getData().get(0).getExpectedStartDate().getFlag().toString();
                    if(expected_start_date.equalsIgnoreCase("1"))
                    {
                        LLExpectedStartDate2.setVisibility(View.VISIBLE);

                    }else if(expected_start_date.equalsIgnoreCase("0"))
                    {
                        LLExpectedStartDate2.setVisibility(View.GONE);
                    }
                    String degree= getSectionTwofinalArray.getData().get(0).getDegree().getFlag().toString();
                    if(degree.equalsIgnoreCase("1"))
                    {
                        LLDegree2.setVisibility(View.VISIBLE);

                    }else if(degree.equalsIgnoreCase("0"))
                    {
                        LLDegree2.setVisibility(View.GONE);

                    }
                    String monthly_salary= getSectionTwofinalArray.getData().get(0).getMonthlySalary().getFlag().toString();
                    if(monthly_salary.equalsIgnoreCase("1"))
                    {
                        LLMonthlySalary2.setVisibility(View.VISIBLE);

                    }else if(monthly_salary.equalsIgnoreCase("0"))
                    {
                        LLMonthlySalary2.setVisibility(View.GONE);
                    }
                    String benifits= getSectionTwofinalArray.getData().get(0).getBenifits().getFlag().toString();
                    if(benifits.equalsIgnoreCase("1"))
                    {
                        LLBenifits2.setVisibility(View.VISIBLE);

                    }else if(benifits.equalsIgnoreCase("0"))
                    {
                        LLBenifits2.setVisibility(View.GONE);
                    }
                    String current_position= getSectionTwofinalArray.getData().get(0).getCurrentPosition().getFlag().toString();
                    if(current_position.equalsIgnoreCase("1"))
                    {
                        LLCurrentPosition2.setVisibility(View.VISIBLE);

                    }else if(current_position.equalsIgnoreCase("0"))
                    {
                        LLCurrentPosition2.setVisibility(View.GONE);
                    }
                    String current_company= getSectionTwofinalArray.getData().get(0).getCurrentCompany().getFlag().toString();
                    if(current_company.equalsIgnoreCase("1"))
                    {
                        LLCurrentCompany2.setVisibility(View.VISIBLE);

                    }else if(current_company.equalsIgnoreCase("0"))
                    {
                        LLCurrentCompany2.setVisibility(View.GONE);
                    }
                    String notice_period= getSectionTwofinalArray.getData().get(0).getNoticePeriod().getFlag().toString();
                    if(notice_period.equalsIgnoreCase("1"))
                    {
                        LLNoticePeriod2.setVisibility(View.VISIBLE);

                    }else if(notice_period.equalsIgnoreCase("0"))
                    {
                        LLNoticePeriod2.setVisibility(View.GONE);
                    }
                    String expected_monthly_salary= getSectionTwofinalArray.getData().get(0).getExpectedMonthlySalary().getFlag().toString();
                    if(expected_monthly_salary.equalsIgnoreCase("1"))
                    {
                        LL_expected_monthly_salary2.setVisibility(View.VISIBLE);

                    }else if(expected_monthly_salary.equalsIgnoreCase("0"))
                    {
                        LL_expected_monthly_salary2.setVisibility(View.GONE);
                    }
                    String resume_file_path= getSectionTwofinalArray.getData().get(0).getResumeFilePath().getFlag().toString();
                    if(resume_file_path.equalsIgnoreCase("1"))
                    {
                        LLResumeFilePath2.setVisibility(View.VISIBLE);

                    }else if(resume_file_path.equalsIgnoreCase("0"))
                    {
                        LLResumeFilePath2.setVisibility(View.GONE);
                    }
                    String body_type_id= getSectionTwofinalArray.getData().get(0).getBodyTypeId().getFlag().toString();
                    if(body_type_id.equalsIgnoreCase("1"))
                    {
                        LLBodyTypeId2.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionTwofinalArray.getData().get(0).getBodyTypeId().getInfo();

                        FuncSpinner(modelList_info,spinner_body_type_id);

                    }else if(body_type_id.equalsIgnoreCase("0"))
                    {
                        LLBodyTypeId2.setVisibility(View.GONE);
                    }
                    String field_type_id= getSectionTwofinalArray.getData().get(0).getFieldTypeId().getFlag().toString();
                    if(field_type_id.equalsIgnoreCase("1"))
                    {
                        LLFieldTypeId2.setVisibility(View.VISIBLE);

                    }else if(field_type_id.equalsIgnoreCase("0"))
                    {
                        LLFieldTypeId2.setVisibility(View.GONE);
                    }
                    String career_level_id= getSectionTwofinalArray.getData().get(0).getCareerLevelId().getFlag().toString();
                    if(career_level_id.equalsIgnoreCase("1"))
                    {
                        LLCareerLevelId2.setVisibility(View.VISIBLE);

                    }else if(career_level_id.equalsIgnoreCase("0"))
                    {
                        LLCareerLevelId2.setVisibility(View.GONE);
                    }
                    String company_field_type_id= getSectionTwofinalArray.getData().get(0).getCompanyFieldTypeId().getFlag().toString();
                    if(company_field_type_id.equalsIgnoreCase("1"))
                    {
                        LLCompanyFieldTypeId2.setVisibility(View.VISIBLE);

                    }else if(company_field_type_id.equalsIgnoreCase("0"))
                    {
                        LLCompanyFieldTypeId2.setVisibility(View.GONE);
                    }
                    String brand= getSectionTwofinalArray.getData().get(0).getBrand().getFlag().toString();
                    if(brand.equalsIgnoreCase("1"))
                    {
                        LLBrand2.setVisibility(View.VISIBLE);

                    }else if(brand.equalsIgnoreCase("0"))
                    {
                        LLBrand2.setVisibility(View.GONE);
                    }
                    String maid_room= getSectionTwofinalArray.getData().get(0).getMaidRoom().getFlag().toString();
                    if(maid_room.equalsIgnoreCase("1"))
                    {
                        LLMaidRoom2.setVisibility(View.VISIBLE);

                    }else if(maid_room.equalsIgnoreCase("0"))
                    {
                        LLMaidRoom2.setVisibility(View.GONE);
                    }
                    String swimming_pool= getSectionTwofinalArray.getData().get(0).getSwimmingPool().getFlag().toString();
                    if(swimming_pool.equalsIgnoreCase("1"))
                    {
                        LLSwimmingPool2.setVisibility(View.VISIBLE);

                    }else if(swimming_pool.equalsIgnoreCase("0"))
                    {
                        LLSwimmingPool2.setVisibility(View.GONE);
                    }
                    String garden= getSectionTwofinalArray.getData().get(0).getGarden().getFlag().toString();
                    if(garden.equalsIgnoreCase("1"))
                    {
                        LLGarden2.setVisibility(View.VISIBLE);

                    }else if(garden.equalsIgnoreCase("0"))
                    {
                        LLGarden2.setVisibility(View.GONE);
                    }
                    String private_parking= getSectionTwofinalArray.getData().get(0).getPrivateParking().getFlag().toString();
                    if(private_parking.equalsIgnoreCase("1"))
                    {
                        LLPrivateParking2.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionTwofinalArray.getData().get(0).getPrivateParking().getInfo();

                        FuncSpinner2(modelList_info,spinnerPrivateParking2);

                    }else if(private_parking.equalsIgnoreCase("0"))
                    {
                        LLPrivateParking2.setVisibility(View.GONE);
                    }
                }
            }
            if(isSetionThree)
            {
                if (!response.equalsIgnoreCase(null))
                {

                    getSectionThreefinalArray = new Gson().fromJson(response,new TypeToken<CreateNormal>(){}.getType());

                    String condition= getSectionThreefinalArray.getData().get(0).getCondition().getFlag().toString();

                    if(condition.equalsIgnoreCase("1"))
                    {
                        LLCondition3.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionThreefinalArray.getData().get(0).getCondition().getInfo();

                        FuncSpinner3(modelList_info,SpinnerCondition3);

                    }else if(condition.equalsIgnoreCase("0"))
                    {
                        LLCondition3.setVisibility(View.GONE);
                    }
                    String type= getSectionThreefinalArray.getData().get(0).getType().getFlag().toString();

                    if(type.equalsIgnoreCase("1"))
                    {

                        modelList_info = (ArrayList<Info>) getSectionThreefinalArray.getData().get(0).getType().getInfo();

                        FuncSpinner3(modelList_info,SpinnerType3);

                        LLType3.setVisibility(View.VISIBLE);

                    }else if(type.equalsIgnoreCase("0"))
                    {
                        LLType3.setVisibility(View.GONE);
                    }
                    String usage= getSectionThreefinalArray.getData().get(0).getUsage().getFlag().toString();
                    if(usage.equalsIgnoreCase("1"))
                    {

                        LLUsage3.setVisibility(View.VISIBLE);

                    }else if(usage.equalsIgnoreCase("0"))
                    {
                        LLUsage3.setVisibility(View.GONE);

                    }
                    String color= getSectionThreefinalArray.getData().get(0).getColor().getFlag().toString();
                    if(color.equalsIgnoreCase("1"))
                    {
                        LLColor3.setVisibility(View.VISIBLE);

                    }else if(color.equalsIgnoreCase("0"))
                    {
                        LLColor3.setVisibility(View.GONE);
                    }
                    String transmission_type= getSectionThreefinalArray.getData().get(0).getTransmissionType().getFlag().toString();
                    if(transmission_type.equalsIgnoreCase("1"))
                    {
                        LLTransmissionType3.setVisibility(View.VISIBLE);

                    }else if(transmission_type.equalsIgnoreCase("0"))
                    {
                        LLTransmissionType3.setVisibility(View.GONE);
                    }
                    String region_specific= getSectionThreefinalArray.getData().get(0).getRegionSpecific().getFlag().toString();
                    if(region_specific.equalsIgnoreCase("1"))
                    {
                        LLRegionSpecific3.setVisibility(View.VISIBLE);

                    }else if(region_specific.equalsIgnoreCase("0"))
                    {
                        LLRegionSpecific3.setVisibility(View.GONE);
                    }
                    String offer= getSectionThreefinalArray.getData().get(0).getOffer().getFlag().toString();
                    if(offer.equalsIgnoreCase("1"))
                    {

                        LLOffer3.setVisibility(View.VISIBLE);

                    }else if(offer.equalsIgnoreCase("0"))
                    {
                        LLOffer3.setVisibility(View.GONE);
                    }
                    String model= getSectionThreefinalArray.getData().get(0).getModel().getFlag().toString();
                    if(model.equalsIgnoreCase("1"))
                    {
                        LLModel3.setVisibility(View.VISIBLE);

                    }else if(model.equalsIgnoreCase("0"))
                    {
                        LLModel3.setVisibility(View.GONE);

                    }
                    String kilometers= getSectionThreefinalArray.getData().get(0).getKilometers().getFlag().toString();
                    if(kilometers.equalsIgnoreCase("1"))
                    {
                        LLKilometers3.setVisibility(View.VISIBLE);

                    }else if(kilometers.equalsIgnoreCase("0"))
                    {
                        LLKilometers3.setVisibility(View.GONE);
                    }
                    String no_of_cylinder= getSectionThreefinalArray.getData().get(0).getNoOfCylinder().getFlag().toString();
                    if(no_of_cylinder.equalsIgnoreCase("1"))
                    {
                        LLNoOfCylinder3.setVisibility(View.VISIBLE);

                    }else if(no_of_cylinder.equalsIgnoreCase("0"))
                    {
                        LLNoOfCylinder3.setVisibility(View.GONE);
                    }
                    String horsepower= getSectionThreefinalArray.getData().get(0).getHorsepower().getFlag().toString();
                    if(horsepower.equalsIgnoreCase("1"))
                    {
                        LLHorsepower3.setVisibility(View.VISIBLE);

                    }else if(horsepower.equalsIgnoreCase("0"))
                    {
                        LLHorsepower3.setVisibility(View.GONE);
                    }
                    String warranty= getSectionThreefinalArray.getData().get(0).getWarranty().getFlag().toString();
                    if(warranty.equalsIgnoreCase("1"))
                    {
                        LLWarranty3.setVisibility(View.VISIBLE);

                    }else if(warranty.equalsIgnoreCase("0"))
                    {
                        LLWarranty3.setVisibility(View.GONE);
                    }
                    String year_of_making= getSectionThreefinalArray.getData().get(0).getYearOfMaking().getFlag().toString();
                    if(year_of_making.equalsIgnoreCase("1"))
                    {
                        LLYearOfMaking3.setVisibility(View.VISIBLE);

                    }else if(year_of_making.equalsIgnoreCase("0"))
                    {
                        LLYearOfMaking3.setVisibility(View.GONE);

                    }
                    String number_of_door= getSectionThreefinalArray.getData().get(0).getNumberOfDoor().getFlag().toString();
                    if(number_of_door.equalsIgnoreCase("1"))
                    {
                        LLNumberOfDoor3.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionThreefinalArray.getData().get(0).getNumberOfDoor().getInfo();

                        FuncSpinner3(modelList_info,spinnerNumberOfDoor3);

                    }else if(number_of_door.equalsIgnoreCase("0"))
                    {
                        LLNumberOfDoor3.setVisibility(View.GONE);
                    }
                    String rent_recurrence_payment= getSectionThreefinalArray.getData().get(0).getRentRecurrencePayment().getFlag().toString();
                    if(rent_recurrence_payment.equalsIgnoreCase("1"))
                    {
                        LLRentRecurrencePayment3.setVisibility(View.VISIBLE);

                    }else if(rent_recurrence_payment.equalsIgnoreCase("0"))
                    {
                        LLRentRecurrencePayment3.setVisibility(View.GONE);
                    }
                    String bedroom= getSectionThreefinalArray.getData().get(0).getBedroom().getFlag().toString();
                    if(bedroom.equalsIgnoreCase("1"))
                    {
                        LLBedroom3.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionThreefinalArray.getData().get(0).getBedroom().getInfo();

                        FuncSpinner3(modelList_info,SpinnerBathroom3);

                    }else if(bedroom.equalsIgnoreCase("0"))
                    {
                        LLBedroom3.setVisibility(View.GONE);
                    }

                    String bathroom= getSectionThreefinalArray.getData().get(0).getBathroom().getFlag().toString();
                    if(bathroom.equalsIgnoreCase("1"))
                    {
                        LLBathroom3.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionThreefinalArray.getData().get(0).getBathroom().getInfo();

                        FuncSpinner3(modelList_info,SpinnerBedRoom3);


                    }else if(bathroom.equalsIgnoreCase("0"))
                    {
                        LLBathroom3.setVisibility(View.GONE);
                    }
                    String developers= getSectionThreefinalArray.getData().get(0).getDevelopers().getFlag().toString();
                    if(developers.equalsIgnoreCase("1"))
                    {
                        LLDevelopers3.setVisibility(View.VISIBLE);

                    }else if(developers.equalsIgnoreCase("0"))
                    {
                        LLDevelopers3.setVisibility(View.GONE);

                    }
                    String status= getSectionThreefinalArray.getData().get(0).getStatus().getFlag().toString();
                    if(status.equalsIgnoreCase("1"))
                    {
                        LLStatus3.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionThreefinalArray.getData().get(0).getStatus().getInfo();

                        FuncSpinner3(modelList_info,SpinnerStatus3);

                    }else if(status.equalsIgnoreCase("0"))
                    {
                        LLStatus3.setVisibility(View.GONE);
                    }
                    String available_from= getSectionThreefinalArray.getData().get(0).getAvailableFrom().getFlag().toString();
                    if(available_from.equalsIgnoreCase("1"))
                    {
                        LLAvailableFrom3.setVisibility(View.VISIBLE);

                    }else if(available_from.equalsIgnoreCase("0"))
                    {
                        LLAvailableFrom3.setVisibility(View.GONE);
                    }
                    String available_to= getSectionThreefinalArray.getData().get(0).getAvailableTo().getFlag().toString();
                    if(available_to.equalsIgnoreCase("1"))
                    {
                        LLAvailableTo3.setVisibility(View.VISIBLE);

                    }else if(available_to.equalsIgnoreCase("0"))
                    {
                        LLAvailableTo3.setVisibility(View.GONE);
                    }
                    String size= getSectionThreefinalArray.getData().get(0).getSize().getFlag().toString();
                    if(size.equalsIgnoreCase("1"))
                    {
                        LLSize3.setVisibility(View.VISIBLE);

                    }else if(size.equalsIgnoreCase("0"))
                    {
                        LLSize3.setVisibility(View.GONE);
                    }
                    String gender= getSectionThreefinalArray.getData().get(0).getGender().getFlag().toString();
                    if(gender.equalsIgnoreCase("1"))
                    {
                        LLGender3.setVisibility(View.VISIBLE);

                    }else if(gender.equalsIgnoreCase("0"))
                    {
                        LLGender3.setVisibility(View.GONE);

                    }
                    String nationality= getSectionThreefinalArray.getData().get(0).getNationality().getFlag().toString();
                    if(nationality.equalsIgnoreCase("1"))
                    {
                        LLNationality3.setVisibility(View.VISIBLE);

                    }else if(nationality.equalsIgnoreCase("0"))
                    {
                        LLNationality3.setVisibility(View.GONE);
                    }
                    String position= getSectionThreefinalArray.getData().get(0).getPosition().getFlag().toString();
                    if(position.equalsIgnoreCase("1"))
                    {
                        LLPosition3.setVisibility(View.VISIBLE);

                    }else if(position.equalsIgnoreCase("0"))
                    {
                        LLPosition3.setVisibility(View.GONE);
                    }
                    String expected_start_date= getSectionThreefinalArray.getData().get(0).getExpectedStartDate().getFlag().toString();
                    if(expected_start_date.equalsIgnoreCase("1"))
                    {
                        LLExpectedStartDate3.setVisibility(View.VISIBLE);

                    }else if(expected_start_date.equalsIgnoreCase("0"))
                    {
                        LLExpectedStartDate3.setVisibility(View.GONE);
                    }
                    String degree= getSectionThreefinalArray.getData().get(0).getDegree().getFlag().toString();
                    if(degree.equalsIgnoreCase("1"))
                    {
                        LLDegree3.setVisibility(View.VISIBLE);

                    }else if(degree.equalsIgnoreCase("0"))
                    {
                        LLDegree3.setVisibility(View.GONE);

                    }
                    String monthly_salary= getSectionThreefinalArray.getData().get(0).getMonthlySalary().getFlag().toString();
                    if(monthly_salary.equalsIgnoreCase("1"))
                    {
                        LLMonthlySalary3.setVisibility(View.VISIBLE);

                    }else if(monthly_salary.equalsIgnoreCase("0"))
                    {
                        LLMonthlySalary3.setVisibility(View.GONE);
                    }
                    String benifits= getSectionThreefinalArray.getData().get(0).getBenifits().getFlag().toString();
                    if(benifits.equalsIgnoreCase("1"))
                    {
                        LLBenifits3.setVisibility(View.VISIBLE);

                    }else if(benifits.equalsIgnoreCase("0"))
                    {
                        LLBenifits3.setVisibility(View.GONE);
                    }
                    String current_position= getSectionThreefinalArray.getData().get(0).getCurrentPosition().getFlag().toString();
                    if(current_position.equalsIgnoreCase("1"))
                    {
                        LLCurrentPosition3.setVisibility(View.VISIBLE);

                    }else if(current_position.equalsIgnoreCase("0"))
                    {
                        LLCurrentPosition3.setVisibility(View.GONE);
                    }
                    String current_company= getSectionThreefinalArray.getData().get(0).getCurrentCompany().getFlag().toString();
                    if(current_company.equalsIgnoreCase("1"))
                    {
                        LLCurrentCompany3.setVisibility(View.VISIBLE);

                    }else if(current_company.equalsIgnoreCase("0"))
                    {
                        LLCurrentCompany3.setVisibility(View.GONE);
                    }
                    String notice_period= getSectionThreefinalArray.getData().get(0).getNoticePeriod().getFlag().toString();
                    if(notice_period.equalsIgnoreCase("1"))
                    {
                        LLNoticePeriod3.setVisibility(View.VISIBLE);

                    }else if(notice_period.equalsIgnoreCase("0"))
                    {
                        LLNoticePeriod3.setVisibility(View.GONE);
                    }
                    String expected_monthly_salary= getSectionThreefinalArray.getData().get(0).getExpectedMonthlySalary().getFlag().toString();
                    if(expected_monthly_salary.equalsIgnoreCase("1"))
                    {
                        LLExpectedMonthlySalary3.setVisibility(View.VISIBLE);

                    }else if(expected_monthly_salary.equalsIgnoreCase("0"))
                    {
                        LLExpectedMonthlySalary3.setVisibility(View.GONE);
                    }
                    String resume_file_path= getSectionThreefinalArray.getData().get(0).getResumeFilePath().getFlag().toString();
                    if(resume_file_path.equalsIgnoreCase("1"))
                    {
                        LLResumeFilePath3.setVisibility(View.VISIBLE);

                    }else if(resume_file_path.equalsIgnoreCase("0"))
                    {
                        LLResumeFilePath3.setVisibility(View.GONE);
                    }
                    String body_type_id= getSectionThreefinalArray.getData().get(0).getBodyTypeId().getFlag().toString();
                    if(body_type_id.equalsIgnoreCase("1"))
                    {
                        LLBodyTypeId3.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionThreefinalArray.getData().get(0).getBodyTypeId().getInfo();

                        FuncSpinner(modelList_info,spinner_body_type_id);

                    }else if(body_type_id.equalsIgnoreCase("0"))
                    {
                        LLBodyTypeId3.setVisibility(View.GONE);
                    }
                    String field_type_id= getSectionThreefinalArray.getData().get(0).getFieldTypeId().getFlag().toString();
                    if(field_type_id.equalsIgnoreCase("1"))
                    {
                        LLFieldTypeId3.setVisibility(View.VISIBLE);

                    }else if(field_type_id.equalsIgnoreCase("0"))
                    {
                        LLFieldTypeId3.setVisibility(View.GONE);
                    }
                    String career_level_id= getSectionThreefinalArray.getData().get(0).getCareerLevelId().getFlag().toString();
                    if(career_level_id.equalsIgnoreCase("1"))
                    {
                        LLCareerLevelId3.setVisibility(View.VISIBLE);

                    }else if(career_level_id.equalsIgnoreCase("0"))
                    {
                        LLCareerLevelId3.setVisibility(View.GONE);
                    }
                    String company_field_type_id= getSectionThreefinalArray.getData().get(0).getCompanyFieldTypeId().getFlag().toString();
                    if(company_field_type_id.equalsIgnoreCase("1"))
                    {
                        LLCompanyFieldTypeId3.setVisibility(View.VISIBLE);

                    }else if(company_field_type_id.equalsIgnoreCase("0"))
                    {
                        LLCompanyFieldTypeId3.setVisibility(View.GONE);
                    }
                    String brand= getSectionThreefinalArray.getData().get(0).getBrand().getFlag().toString();
                    if(brand.equalsIgnoreCase("1"))
                    {
                        LLBrand3.setVisibility(View.VISIBLE);

                    }else if(brand.equalsIgnoreCase("0"))
                    {
                        LLBrand3.setVisibility(View.GONE);
                    }
                    String maid_room= getSectionThreefinalArray.getData().get(0).getMaidRoom().getFlag().toString();
                    if(maid_room.equalsIgnoreCase("1"))
                    {
                        LLMaidRoom3.setVisibility(View.VISIBLE);

                    }else if(maid_room.equalsIgnoreCase("0"))
                    {
                        LLMaidRoom3.setVisibility(View.GONE);
                    }
                    String swimming_pool= getSectionThreefinalArray.getData().get(0).getSwimmingPool().getFlag().toString();
                    if(swimming_pool.equalsIgnoreCase("1"))
                    {
                        LLSwimmingPool3.setVisibility(View.VISIBLE);

                    }else if(swimming_pool.equalsIgnoreCase("0"))
                    {
                        LLSwimmingPool3.setVisibility(View.GONE);
                    }
                    String garden= getSectionThreefinalArray.getData().get(0).getGarden().getFlag().toString();
                    if(garden.equalsIgnoreCase("1"))
                    {
                        LLGarden3.setVisibility(View.VISIBLE);

                    }else if(garden.equalsIgnoreCase("0"))
                    {
                        LLGarden3.setVisibility(View.GONE);
                    }
                    String private_parking= getSectionThreefinalArray.getData().get(0).getPrivateParking().getFlag().toString();
                    if(private_parking.equalsIgnoreCase("1"))
                    {
                        LLPrivateParking3.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionThreefinalArray.getData().get(0).getPrivateParking().getInfo();

                        FuncSpinner3(modelList_info,spinnerPrivateParking3);

                    }else if(private_parking.equalsIgnoreCase("0"))
                    {
                        LLPrivateParking3.setVisibility(View.GONE);
                    }
                }
            }

            if(isSetionFour)
            {
                if (!response.equalsIgnoreCase(null))
                {

                    getSectionFourfinalArray = new Gson().fromJson(response,new TypeToken<CreateNormal>(){}.getType());

                    String condition= getSectionFourfinalArray.getData().get(0).getCondition().getFlag().toString();

                    if(condition.equalsIgnoreCase("1"))
                    {
                        LLCondition4.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionFourfinalArray.getData().get(0).getCondition().getInfo();

                        FuncSpinner4(modelList_info,SpinnerCondition4);

                    }else if(condition.equalsIgnoreCase("0"))
                    {
                        LLCondition4.setVisibility(View.GONE);
                    }
                    String type= getSectionFourfinalArray.getData().get(0).getType().getFlag().toString();

                    if(type.equalsIgnoreCase("1"))
                    {

                        modelList_info = (ArrayList<Info>) getSectionFourfinalArray.getData().get(0).getType().getInfo();

                        FuncSpinner4(modelList_info,SpinnerType4);

                        LLType4.setVisibility(View.VISIBLE);

                    }else if(type.equalsIgnoreCase("0"))
                    {
                        LLType4.setVisibility(View.GONE);
                    }
                    String usage= getSectionFourfinalArray.getData().get(0).getUsage().getFlag().toString();
                    if(usage.equalsIgnoreCase("1"))
                    {

                        LLUsage4.setVisibility(View.VISIBLE);

                    }else if(usage.equalsIgnoreCase("0"))
                    {
                        LLUsage4.setVisibility(View.GONE);

                    }
                    String color= getSectionFourfinalArray.getData().get(0).getColor().getFlag().toString();
                    if(color.equalsIgnoreCase("1"))
                    {
                        LLColor4.setVisibility(View.VISIBLE);

                    }else if(color.equalsIgnoreCase("0"))
                    {
                        LLColor4.setVisibility(View.GONE);
                    }
                    String transmission_type= getSectionFourfinalArray.getData().get(0).getTransmissionType().getFlag().toString();
                    if(transmission_type.equalsIgnoreCase("1"))
                    {
                        LLTransmissionType4.setVisibility(View.VISIBLE);

                    }else if(transmission_type.equalsIgnoreCase("0"))
                    {
                        LLTransmissionType4.setVisibility(View.GONE);
                    }
                    String region_specific= getSectionFourfinalArray.getData().get(0).getRegionSpecific().getFlag().toString();
                    if(region_specific.equalsIgnoreCase("1"))
                    {
                        LLRegionSpecific4.setVisibility(View.VISIBLE);

                    }else if(region_specific.equalsIgnoreCase("0"))
                    {
                        LLRegionSpecific4.setVisibility(View.GONE);
                    }
                    String offer= getSectionFourfinalArray.getData().get(0).getOffer().getFlag().toString();
                    if(offer.equalsIgnoreCase("1"))
                    {

                        LLOffer4.setVisibility(View.VISIBLE);

                    }else if(offer.equalsIgnoreCase("0"))
                    {
                        LLOffer4.setVisibility(View.GONE);
                    }
                    String model= getSectionFourfinalArray.getData().get(0).getModel().getFlag().toString();
                    if(model.equalsIgnoreCase("1"))
                    {
                        LLModel4.setVisibility(View.VISIBLE);

                    }else if(model.equalsIgnoreCase("0"))
                    {
                        LLModel4.setVisibility(View.GONE);

                    }
                    String kilometers= getSectionFourfinalArray.getData().get(0).getKilometers().getFlag().toString();
                    if(kilometers.equalsIgnoreCase("1"))
                    {
                        LLKilometers4.setVisibility(View.VISIBLE);

                    }else if(kilometers.equalsIgnoreCase("0"))
                    {
                        LLKilometers4.setVisibility(View.GONE);
                    }
                    String no_of_cylinder= getSectionFourfinalArray.getData().get(0).getNoOfCylinder().getFlag().toString();
                    if(no_of_cylinder.equalsIgnoreCase("1"))
                    {
                        LLNoOfCylinder4.setVisibility(View.VISIBLE);

                    }else if(no_of_cylinder.equalsIgnoreCase("0"))
                    {
                        LLNoOfCylinder4.setVisibility(View.GONE);
                    }
                    String horsepower= getSectionFourfinalArray.getData().get(0).getHorsepower().getFlag().toString();
                    if(horsepower.equalsIgnoreCase("1"))
                    {
                        LLHorsepower4.setVisibility(View.VISIBLE);

                    }else if(horsepower.equalsIgnoreCase("0"))
                    {
                        LLHorsepower4.setVisibility(View.GONE);
                    }
                    String warranty= getSectionFourfinalArray.getData().get(0).getWarranty().getFlag().toString();
                    if(warranty.equalsIgnoreCase("1"))
                    {
                        LLWarranty4.setVisibility(View.VISIBLE);

                    }else if(warranty.equalsIgnoreCase("0"))
                    {
                        LLWarranty4.setVisibility(View.GONE);
                    }
                    String year_of_making= getSectionFourfinalArray.getData().get(0).getYearOfMaking().getFlag().toString();
                    if(year_of_making.equalsIgnoreCase("1"))
                    {
                        LLYearOfMaking4.setVisibility(View.VISIBLE);

                    }else if(year_of_making.equalsIgnoreCase("0"))
                    {
                        LLYearOfMaking4.setVisibility(View.GONE);

                    }
                    String number_of_door= getSectionFourfinalArray.getData().get(0).getNumberOfDoor().getFlag().toString();
                    if(number_of_door.equalsIgnoreCase("1"))
                    {
                        LLNumberOfDoor4.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionFourfinalArray.getData().get(0).getNumberOfDoor().getInfo();

                        FuncSpinner4(modelList_info,spinnerNumberOfDoor4);

                    }else if(number_of_door.equalsIgnoreCase("0"))
                    {
                        LLNumberOfDoor4.setVisibility(View.GONE);
                    }
                    String rent_recurrence_payment= getSectionFourfinalArray.getData().get(0).getRentRecurrencePayment().getFlag().toString();
                    if(rent_recurrence_payment.equalsIgnoreCase("1"))
                    {
                        LLRentRecurrencePayment4.setVisibility(View.VISIBLE);

                    }else if(rent_recurrence_payment.equalsIgnoreCase("0"))
                    {
                        LLRentRecurrencePayment4.setVisibility(View.GONE);
                    }
                    String bedroom= getSectionFourfinalArray.getData().get(0).getBedroom().getFlag().toString();
                    if(bedroom.equalsIgnoreCase("1"))
                    {
                        LLBedroom4.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionFourfinalArray.getData().get(0).getBedroom().getInfo();

                        FuncSpinner4(modelList_info,SpinnerBathroom4);

                    }else if(bedroom.equalsIgnoreCase("0"))
                    {
                        LLBedroom4.setVisibility(View.GONE);
                    }

                    String bathroom= getSectionFourfinalArray.getData().get(0).getBathroom().getFlag().toString();
                    if(bathroom.equalsIgnoreCase("1"))
                    {
                        LLBathroom4.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionFourfinalArray.getData().get(0).getBathroom().getInfo();

                        FuncSpinner4(modelList_info,SpinnerBedRoom4);


                    }else if(bathroom.equalsIgnoreCase("0"))
                    {
                        LLBathroom4.setVisibility(View.GONE);
                    }
                    String developers= getSectionFourfinalArray.getData().get(0).getDevelopers().getFlag().toString();
                    if(developers.equalsIgnoreCase("1"))
                    {
                        LLDevelopers4.setVisibility(View.VISIBLE);

                    }else if(developers.equalsIgnoreCase("0"))
                    {
                        LLDevelopers4.setVisibility(View.GONE);

                    }
                    String status= getSectionFourfinalArray.getData().get(0).getStatus().getFlag().toString();
                    if(status.equalsIgnoreCase("1"))
                    {
                        LLStatus4.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionFourfinalArray.getData().get(0).getStatus().getInfo();

                        FuncSpinner4(modelList_info,SpinnerStatus4);

                    }else if(status.equalsIgnoreCase("0"))
                    {
                        LLStatus4.setVisibility(View.GONE);
                    }
                    String available_from= getSectionFourfinalArray.getData().get(0).getAvailableFrom().getFlag().toString();
                    if(available_from.equalsIgnoreCase("1"))
                    {
                        LLAvailableFrom4.setVisibility(View.VISIBLE);

                    }else if(available_from.equalsIgnoreCase("0"))
                    {
                        LLAvailableFrom4.setVisibility(View.GONE);
                    }
                    String available_to= getSectionFourfinalArray.getData().get(0).getAvailableTo().getFlag().toString();
                    if(available_to.equalsIgnoreCase("1"))
                    {
                        LLAvailableTo4.setVisibility(View.VISIBLE);

                    }else if(available_to.equalsIgnoreCase("0"))
                    {
                        LLAvailableTo4.setVisibility(View.GONE);
                    }
                    String size= getSectionFourfinalArray.getData().get(0).getSize().getFlag().toString();
                    if(size.equalsIgnoreCase("1"))
                    {
                        LLSize4.setVisibility(View.VISIBLE);

                    }else if(size.equalsIgnoreCase("0"))
                    {
                        LLSize4.setVisibility(View.GONE);
                    }
                    String gender= getSectionFourfinalArray.getData().get(0).getGender().getFlag().toString();
                    if(gender.equalsIgnoreCase("1"))
                    {
                        LLGender4.setVisibility(View.VISIBLE);

                    }else if(gender.equalsIgnoreCase("0"))
                    {
                        LLGender4.setVisibility(View.GONE);

                    }
                    String nationality= getSectionFourfinalArray.getData().get(0).getNationality().getFlag().toString();
                    if(nationality.equalsIgnoreCase("1"))
                    {
                        LLNationality4.setVisibility(View.VISIBLE);

                    }else if(nationality.equalsIgnoreCase("0"))
                    {
                        LLNationality4.setVisibility(View.GONE);
                    }
                    String position= getSectionFourfinalArray.getData().get(0).getPosition().getFlag().toString();
                    if(position.equalsIgnoreCase("1"))
                    {
                        LLPosition4.setVisibility(View.VISIBLE);

                    }else if(position.equalsIgnoreCase("0"))
                    {
                        LLPosition4.setVisibility(View.GONE);
                    }
                    String expected_start_date= getSectionFourfinalArray.getData().get(0).getExpectedStartDate().getFlag().toString();
                    if(expected_start_date.equalsIgnoreCase("1"))
                    {
                        LLExpectedStartDate4.setVisibility(View.VISIBLE);

                    }else if(expected_start_date.equalsIgnoreCase("0"))
                    {
                        LLExpectedStartDate4.setVisibility(View.GONE);
                    }
                    String degree= getSectionFourfinalArray.getData().get(0).getDegree().getFlag().toString();
                    if(degree.equalsIgnoreCase("1"))
                    {
                        LLDegree4.setVisibility(View.VISIBLE);

                    }else if(degree.equalsIgnoreCase("0"))
                    {
                        LLDegree4.setVisibility(View.GONE);

                    }
                    String monthly_salary= getSectionFourfinalArray.getData().get(0).getMonthlySalary().getFlag().toString();
                    if(monthly_salary.equalsIgnoreCase("1"))
                    {
                        LLMonthlySalary4.setVisibility(View.VISIBLE);

                    }else if(monthly_salary.equalsIgnoreCase("0"))
                    {
                        LLMonthlySalary4.setVisibility(View.GONE);
                    }
                    String benifits= getSectionFourfinalArray.getData().get(0).getBenifits().getFlag().toString();
                    if(benifits.equalsIgnoreCase("1"))
                    {
                        LLBenifits4.setVisibility(View.VISIBLE);

                    }else if(benifits.equalsIgnoreCase("0"))
                    {
                        LLBenifits4.setVisibility(View.GONE);
                    }
                    String current_position= getSectionFourfinalArray.getData().get(0).getCurrentPosition().getFlag().toString();
                    if(current_position.equalsIgnoreCase("1"))
                    {
                        LLCurrentPosition4.setVisibility(View.VISIBLE);

                    }else if(current_position.equalsIgnoreCase("0"))
                    {
                        LLCurrentPosition4.setVisibility(View.GONE);
                    }
                    String current_company= getSectionFourfinalArray.getData().get(0).getCurrentCompany().getFlag().toString();
                    if(current_company.equalsIgnoreCase("1"))
                    {
                        LLCurrentCompany4.setVisibility(View.VISIBLE);

                    }else if(current_company.equalsIgnoreCase("0"))
                    {
                        LLCurrentCompany4.setVisibility(View.GONE);
                    }
                    String notice_period= getSectionFourfinalArray.getData().get(0).getNoticePeriod().getFlag().toString();
                    if(notice_period.equalsIgnoreCase("1"))
                    {
                        LLNoticePeriod4.setVisibility(View.VISIBLE);

                    }else if(notice_period.equalsIgnoreCase("0"))
                    {
                        LLNoticePeriod4.setVisibility(View.GONE);
                    }
                    String expected_monthly_salary= getSectionFourfinalArray.getData().get(0).getExpectedMonthlySalary().getFlag().toString();
                    if(expected_monthly_salary.equalsIgnoreCase("1"))
                    {
                        LLExpectedMonthlySalary4.setVisibility(View.VISIBLE);

                    }else if(expected_monthly_salary.equalsIgnoreCase("0"))
                    {
                        LLExpectedMonthlySalary4.setVisibility(View.GONE);
                    }
                    String resume_file_path= getSectionFourfinalArray.getData().get(0).getResumeFilePath().getFlag().toString();
                    if(resume_file_path.equalsIgnoreCase("1"))
                    {
                        LLResumeFilePath4.setVisibility(View.VISIBLE);

                    }else if(resume_file_path.equalsIgnoreCase("0"))
                    {
                        LLResumeFilePath4.setVisibility(View.GONE);
                    }
                    String body_type_id= getSectionFourfinalArray.getData().get(0).getBodyTypeId().getFlag().toString();
                    if(body_type_id.equalsIgnoreCase("1"))
                    {
                        LLBodyTypeId4.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionFourfinalArray.getData().get(0).getBodyTypeId().getInfo();

                        FuncSpinner(modelList_info,spinner_body_type_id);

                    }else if(body_type_id.equalsIgnoreCase("0"))
                    {
                        LLBodyTypeId4.setVisibility(View.GONE);
                    }
                    String field_type_id= getSectionFourfinalArray.getData().get(0).getFieldTypeId().getFlag().toString();
                    if(field_type_id.equalsIgnoreCase("1"))
                    {
                        LLFieldTypeId4.setVisibility(View.VISIBLE);

                    }else if(field_type_id.equalsIgnoreCase("0"))
                    {
                        LLFieldTypeId4.setVisibility(View.GONE);
                    }
                    String career_level_id= getSectionFourfinalArray.getData().get(0).getCareerLevelId().getFlag().toString();
                    if(career_level_id.equalsIgnoreCase("1"))
                    {
                        LLCareerLevelId4.setVisibility(View.VISIBLE);

                    }else if(career_level_id.equalsIgnoreCase("0"))
                    {
                        LLCareerLevelId4.setVisibility(View.GONE);
                    }
                    String company_field_type_id= getSectionFourfinalArray.getData().get(0).getCompanyFieldTypeId().getFlag().toString();
                    if(company_field_type_id.equalsIgnoreCase("1"))
                    {
                        LLCompanyFieldTypeId4.setVisibility(View.VISIBLE);

                    }else if(company_field_type_id.equalsIgnoreCase("0"))
                    {
                        LLCompanyFieldTypeId4.setVisibility(View.GONE);
                    }
                    String brand= getSectionFourfinalArray.getData().get(0).getBrand().getFlag().toString();
                    if(brand.equalsIgnoreCase("1"))
                    {
                        LLBrand4.setVisibility(View.VISIBLE);

                    }else if(brand.equalsIgnoreCase("0"))
                    {
                        LLBrand4.setVisibility(View.GONE);
                    }
                    String maid_room= getSectionFourfinalArray.getData().get(0).getMaidRoom().getFlag().toString();
                    if(maid_room.equalsIgnoreCase("1"))
                    {
                        LLMaidRoom4.setVisibility(View.VISIBLE);

                    }else if(maid_room.equalsIgnoreCase("0"))
                    {
                        LLMaidRoom4.setVisibility(View.GONE);
                    }
                    String swimming_pool= getSectionFourfinalArray.getData().get(0).getSwimmingPool().getFlag().toString();
                    if(swimming_pool.equalsIgnoreCase("1"))
                    {
                        LLSwimmingPool4.setVisibility(View.VISIBLE);

                    }else if(swimming_pool.equalsIgnoreCase("0"))
                    {
                        LLSwimmingPool4.setVisibility(View.GONE);
                    }
                    String garden= getSectionFourfinalArray.getData().get(0).getGarden().getFlag().toString();
                    if(garden.equalsIgnoreCase("1"))
                    {
                        LLGarden4.setVisibility(View.VISIBLE);

                    }else if(garden.equalsIgnoreCase("0"))
                    {
                        LLGarden4.setVisibility(View.GONE);
                    }
                    String private_parking= getSectionFourfinalArray.getData().get(0).getPrivateParking().getFlag().toString();
                    if(private_parking.equalsIgnoreCase("1"))
                    {
                        LLPrivateParking4.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionFourfinalArray.getData().get(0).getPrivateParking().getInfo();

                        FuncSpinner4(modelList_info,spinnerPrivateParking4);

                    }else if(private_parking.equalsIgnoreCase("0"))
                    {
                        LLPrivateParking4.setVisibility(View.GONE);
                    }
                }
            }
            if(isSetionFive)
            {
                if (!response.equalsIgnoreCase(null))
                {

                    getSectionFivefinalArray = new Gson().fromJson(response,new TypeToken<CreateNormal>(){}.getType());

                    String condition= getSectionFivefinalArray.getData().get(0).getCondition().getFlag().toString();

                    if(condition.equalsIgnoreCase("1"))
                    {
                        LLCondition5.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionFivefinalArray.getData().get(0).getCondition().getInfo();

                        FuncSpinner5(modelList_info,SpinnerCondition5);

                    }else if(condition.equalsIgnoreCase("0"))
                    {
                        LLCondition5.setVisibility(View.GONE);
                    }
                    String type= getSectionFivefinalArray.getData().get(0).getType().getFlag().toString();

                    if(type.equalsIgnoreCase("1"))
                    {

                        modelList_info = (ArrayList<Info>) getSectionFivefinalArray.getData().get(0).getType().getInfo();

                        FuncSpinner5(modelList_info,SpinnerType5);

                        LLType5.setVisibility(View.VISIBLE);

                    }else if(type.equalsIgnoreCase("0"))
                    {
                        LLType5.setVisibility(View.GONE);
                    }
                    String usage= getSectionFivefinalArray.getData().get(0).getUsage().getFlag().toString();
                    if(usage.equalsIgnoreCase("1"))
                    {

                        LLUsage5.setVisibility(View.VISIBLE);

                    }else if(usage.equalsIgnoreCase("0"))
                    {
                        LLUsage5.setVisibility(View.GONE);

                    }
                    String color= getSectionFivefinalArray.getData().get(0).getColor().getFlag().toString();
                    if(color.equalsIgnoreCase("1"))
                    {
                        LLColor5.setVisibility(View.VISIBLE);

                    }else if(color.equalsIgnoreCase("0"))
                    {
                        LLColor5.setVisibility(View.GONE);
                    }
                    String transmission_type= getSectionFivefinalArray.getData().get(0).getTransmissionType().getFlag().toString();
                    if(transmission_type.equalsIgnoreCase("1"))
                    {
                        LLTransmissionType5.setVisibility(View.VISIBLE);

                    }else if(transmission_type.equalsIgnoreCase("0"))
                    {
                        LLTransmissionType5.setVisibility(View.GONE);
                    }
                    String region_specific= getSectionFivefinalArray.getData().get(0).getRegionSpecific().getFlag().toString();
                    if(region_specific.equalsIgnoreCase("1"))
                    {
                        LLRegionSpecific5.setVisibility(View.VISIBLE);

                    }else if(region_specific.equalsIgnoreCase("0"))
                    {
                        LLRegionSpecific5.setVisibility(View.GONE);
                    }
                    String offer= getSectionFivefinalArray.getData().get(0).getOffer().getFlag().toString();
                    if(offer.equalsIgnoreCase("1"))
                    {

                        LLOffer5.setVisibility(View.VISIBLE);

                    }else if(offer.equalsIgnoreCase("0"))
                    {
                        LLOffer5.setVisibility(View.GONE);
                    }
                    String model= getSectionFivefinalArray.getData().get(0).getModel().getFlag().toString();
                    if(model.equalsIgnoreCase("1"))
                    {
                        LLModel5.setVisibility(View.VISIBLE);

                    }else if(model.equalsIgnoreCase("0"))
                    {
                        LLModel5.setVisibility(View.GONE);

                    }
                    String kilometers= getSectionFivefinalArray.getData().get(0).getKilometers().getFlag().toString();
                    if(kilometers.equalsIgnoreCase("1"))
                    {
                        LLKilometers5.setVisibility(View.VISIBLE);

                    }else if(kilometers.equalsIgnoreCase("0"))
                    {
                        LLKilometers5.setVisibility(View.GONE);
                    }
                    String no_of_cylinder= getSectionFivefinalArray.getData().get(0).getNoOfCylinder().getFlag().toString();
                    if(no_of_cylinder.equalsIgnoreCase("1"))
                    {
                        LLNoOfCylinder5.setVisibility(View.VISIBLE);

                    }else if(no_of_cylinder.equalsIgnoreCase("0"))
                    {
                        LLNoOfCylinder5.setVisibility(View.GONE);
                    }
                    String horsepower= getSectionFivefinalArray.getData().get(0).getHorsepower().getFlag().toString();
                    if(horsepower.equalsIgnoreCase("1"))
                    {
                        LLHorsepower5.setVisibility(View.VISIBLE);

                    }else if(horsepower.equalsIgnoreCase("0"))
                    {
                        LLHorsepower5.setVisibility(View.GONE);
                    }
                    String warranty= getSectionFivefinalArray.getData().get(0).getWarranty().getFlag().toString();
                    if(warranty.equalsIgnoreCase("1"))
                    {
                        LLWarranty5.setVisibility(View.VISIBLE);

                    }else if(warranty.equalsIgnoreCase("0"))
                    {
                        LLWarranty5.setVisibility(View.GONE);
                    }
                    String year_of_making= getSectionFivefinalArray.getData().get(0).getYearOfMaking().getFlag().toString();
                    if(year_of_making.equalsIgnoreCase("1"))
                    {
                        LLYearOfMaking5.setVisibility(View.VISIBLE);

                    }else if(year_of_making.equalsIgnoreCase("0"))
                    {
                        LLYearOfMaking5.setVisibility(View.GONE);

                    }
                    String number_of_door= getSectionFivefinalArray.getData().get(0).getNumberOfDoor().getFlag().toString();
                    if(number_of_door.equalsIgnoreCase("1"))
                    {
                        LLNumberOfDoor5.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionFivefinalArray.getData().get(0).getNumberOfDoor().getInfo();

                        FuncSpinner5(modelList_info,spinnerNumberOfDoor5);

                    }else if(number_of_door.equalsIgnoreCase("0"))
                    {
                        LLNumberOfDoor5.setVisibility(View.GONE);
                    }
                    String rent_recurrence_payment= getSectionFivefinalArray.getData().get(0).getRentRecurrencePayment().getFlag().toString();
                    if(rent_recurrence_payment.equalsIgnoreCase("1"))
                    {
                        LLRentRecurrencePayment5.setVisibility(View.VISIBLE);

                    }else if(rent_recurrence_payment.equalsIgnoreCase("0"))
                    {
                        LLRentRecurrencePayment5.setVisibility(View.GONE);
                    }
                    String bedroom= getSectionFivefinalArray.getData().get(0).getBedroom().getFlag().toString();
                    if(bedroom.equalsIgnoreCase("1"))
                    {
                        LLBedroom5.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionFivefinalArray.getData().get(0).getBedroom().getInfo();

                        FuncSpinner5(modelList_info,SpinnerBathroom5);

                    }else if(bedroom.equalsIgnoreCase("0"))
                    {
                        LLBedroom5.setVisibility(View.GONE);
                    }

                    String bathroom= getSectionFivefinalArray.getData().get(0).getBathroom().getFlag().toString();
                    if(bathroom.equalsIgnoreCase("1"))
                    {
                        LLBathroom5.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionFivefinalArray.getData().get(0).getBathroom().getInfo();

                        FuncSpinner5(modelList_info,SpinnerBedRoom5);


                    }else if(bathroom.equalsIgnoreCase("0"))
                    {
                        LLBathroom5.setVisibility(View.GONE);
                    }
                    String developers= getSectionFivefinalArray.getData().get(0).getDevelopers().getFlag().toString();
                    if(developers.equalsIgnoreCase("1"))
                    {
                        LLDevelopers5.setVisibility(View.VISIBLE);

                    }else if(developers.equalsIgnoreCase("0"))
                    {
                        LLDevelopers5.setVisibility(View.GONE);

                    }
                    String status= getSectionFivefinalArray.getData().get(0).getStatus().getFlag().toString();
                    if(status.equalsIgnoreCase("1"))
                    {
                        LLStatus5.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionFivefinalArray.getData().get(0).getStatus().getInfo();

                        FuncSpinner5(modelList_info,SpinnerStatus5);

                    }else if(status.equalsIgnoreCase("0"))
                    {
                        LLStatus5.setVisibility(View.GONE);
                    }
                    String available_from= getSectionFivefinalArray.getData().get(0).getAvailableFrom().getFlag().toString();
                    if(available_from.equalsIgnoreCase("1"))
                    {
                        LLAvailableFrom5.setVisibility(View.VISIBLE);

                    }else if(available_from.equalsIgnoreCase("0"))
                    {
                        LLAvailableFrom5.setVisibility(View.GONE);
                    }
                    String available_to= getSectionFivefinalArray.getData().get(0).getAvailableTo().getFlag().toString();
                    if(available_to.equalsIgnoreCase("1"))
                    {
                        LLAvailableTo5.setVisibility(View.VISIBLE);

                    }else if(available_to.equalsIgnoreCase("0"))
                    {
                        LLAvailableTo5.setVisibility(View.GONE);
                    }
                    String size= getSectionFivefinalArray.getData().get(0).getSize().getFlag().toString();
                    if(size.equalsIgnoreCase("1"))
                    {
                        LLSize5.setVisibility(View.VISIBLE);

                    }else if(size.equalsIgnoreCase("0"))
                    {
                        LLSize5.setVisibility(View.GONE);
                    }
                    String gender= getSectionFivefinalArray.getData().get(0).getGender().getFlag().toString();
                    if(gender.equalsIgnoreCase("1"))
                    {
                        LLGender5.setVisibility(View.VISIBLE);

                    }else if(gender.equalsIgnoreCase("0"))
                    {
                        LLGender5.setVisibility(View.GONE);

                    }
                    String nationality= getSectionFivefinalArray.getData().get(0).getNationality().getFlag().toString();
                    if(nationality.equalsIgnoreCase("1"))
                    {
                        LLNationality5.setVisibility(View.VISIBLE);

                    }else if(nationality.equalsIgnoreCase("0"))
                    {
                        LLNationality5.setVisibility(View.GONE);
                    }
                    String position= getSectionFivefinalArray.getData().get(0).getPosition().getFlag().toString();
                    if(position.equalsIgnoreCase("1"))
                    {
                        LLPosition5.setVisibility(View.VISIBLE);

                    }else if(position.equalsIgnoreCase("0"))
                    {
                        LLPosition5.setVisibility(View.GONE);
                    }
                    String expected_start_date= getSectionFivefinalArray.getData().get(0).getExpectedStartDate().getFlag().toString();
                    if(expected_start_date.equalsIgnoreCase("1"))
                    {
                        LLExpectedStartDate5.setVisibility(View.VISIBLE);

                    }else if(expected_start_date.equalsIgnoreCase("0"))
                    {
                        LLExpectedStartDate5.setVisibility(View.GONE);
                    }
                    String degree= getSectionFivefinalArray.getData().get(0).getDegree().getFlag().toString();
                    if(degree.equalsIgnoreCase("1"))
                    {
                        LLDegree5.setVisibility(View.VISIBLE);

                    }else if(degree.equalsIgnoreCase("0"))
                    {
                        LLDegree5.setVisibility(View.GONE);

                    }
                    String monthly_salary= getSectionFivefinalArray.getData().get(0).getMonthlySalary().getFlag().toString();
                    if(monthly_salary.equalsIgnoreCase("1"))
                    {
                        LLMonthlySalary5.setVisibility(View.VISIBLE);

                    }else if(monthly_salary.equalsIgnoreCase("0"))
                    {
                        LLMonthlySalary5.setVisibility(View.GONE);
                    }
                    String benifits= getSectionFivefinalArray.getData().get(0).getBenifits().getFlag().toString();
                    if(benifits.equalsIgnoreCase("1"))
                    {
                        LLBenifits5.setVisibility(View.VISIBLE);

                    }else if(benifits.equalsIgnoreCase("0"))
                    {
                        LLBenifits5.setVisibility(View.GONE);
                    }
                    String current_position= getSectionFivefinalArray.getData().get(0).getCurrentPosition().getFlag().toString();
                    if(current_position.equalsIgnoreCase("1"))
                    {
                        LLCurrentPosition5.setVisibility(View.VISIBLE);

                    }else if(current_position.equalsIgnoreCase("0"))
                    {
                        LLCurrentPosition5.setVisibility(View.GONE);
                    }
                    String current_company= getSectionFivefinalArray.getData().get(0).getCurrentCompany().getFlag().toString();
                    if(current_company.equalsIgnoreCase("1"))
                    {
                        LLCurrentCompany5.setVisibility(View.VISIBLE);

                    }else if(current_company.equalsIgnoreCase("0"))
                    {
                        LLCurrentCompany5.setVisibility(View.GONE);
                    }
                    String notice_period= getSectionFivefinalArray.getData().get(0).getNoticePeriod().getFlag().toString();
                    if(notice_period.equalsIgnoreCase("1"))
                    {
                        LLNoticePeriod5.setVisibility(View.VISIBLE);

                    }else if(notice_period.equalsIgnoreCase("0"))
                    {
                        LLNoticePeriod5.setVisibility(View.GONE);
                    }
                    String expected_monthly_salary= getSectionFivefinalArray.getData().get(0).getExpectedMonthlySalary().getFlag().toString();
                    if(expected_monthly_salary.equalsIgnoreCase("1"))
                    {
                        LLExpectedMonthlySalary5.setVisibility(View.VISIBLE);

                    }else if(expected_monthly_salary.equalsIgnoreCase("0"))
                    {
                        LLExpectedMonthlySalary5.setVisibility(View.GONE);
                    }
                    String resume_file_path= getSectionFivefinalArray.getData().get(0).getResumeFilePath().getFlag().toString();
                    if(resume_file_path.equalsIgnoreCase("1"))
                    {
                        LLResumeFilePath5.setVisibility(View.VISIBLE);

                    }else if(resume_file_path.equalsIgnoreCase("0"))
                    {
                        LLResumeFilePath5.setVisibility(View.GONE);
                    }
                    String body_type_id= getSectionFivefinalArray.getData().get(0).getBodyTypeId().getFlag().toString();
                    if(body_type_id.equalsIgnoreCase("1"))
                    {
                        LLBodyTypeId5.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionFivefinalArray.getData().get(0).getBodyTypeId().getInfo();

                        FuncSpinner(modelList_info,spinner_body_type_id);

                    }else if(body_type_id.equalsIgnoreCase("0"))
                    {
                        LLBodyTypeId5.setVisibility(View.GONE);
                    }
                    String field_type_id= getSectionFivefinalArray.getData().get(0).getFieldTypeId().getFlag().toString();
                    if(field_type_id.equalsIgnoreCase("1"))
                    {
                        LLFieldTypeId5.setVisibility(View.VISIBLE);

                    }else if(field_type_id.equalsIgnoreCase("0"))
                    {
                        LLFieldTypeId5.setVisibility(View.GONE);
                    }
                    String career_level_id= getSectionFivefinalArray.getData().get(0).getCareerLevelId().getFlag().toString();
                    if(career_level_id.equalsIgnoreCase("1"))
                    {
                        LLCareerLevelId5.setVisibility(View.VISIBLE);

                    }else if(career_level_id.equalsIgnoreCase("0"))
                    {
                        LLCareerLevelId5.setVisibility(View.GONE);
                    }
                    String company_field_type_id= getSectionFivefinalArray.getData().get(0).getCompanyFieldTypeId().getFlag().toString();
                    if(company_field_type_id.equalsIgnoreCase("1"))
                    {
                        LLCompanyFieldTypeId5.setVisibility(View.VISIBLE);

                    }else if(company_field_type_id.equalsIgnoreCase("0"))
                    {
                        LLCompanyFieldTypeId5.setVisibility(View.GONE);
                    }
                    String brand= getSectionFivefinalArray.getData().get(0).getBrand().getFlag().toString();
                    if(brand.equalsIgnoreCase("1"))
                    {
                        LLBrand5.setVisibility(View.VISIBLE);

                    }else if(brand.equalsIgnoreCase("0"))
                    {
                        LLBrand5.setVisibility(View.GONE);
                    }
                    String maid_room= getSectionFivefinalArray.getData().get(0).getMaidRoom().getFlag().toString();
                    if(maid_room.equalsIgnoreCase("1"))
                    {
                        LLMaidRoom5.setVisibility(View.VISIBLE);

                    }else if(maid_room.equalsIgnoreCase("0"))
                    {
                        LLMaidRoom5.setVisibility(View.GONE);
                    }
                    String swimming_pool= getSectionFivefinalArray.getData().get(0).getSwimmingPool().getFlag().toString();
                    if(swimming_pool.equalsIgnoreCase("1"))
                    {
                        LLSwimmingPool5.setVisibility(View.VISIBLE);

                    }else if(swimming_pool.equalsIgnoreCase("0"))
                    {
                        LLSwimmingPool5.setVisibility(View.GONE);
                    }
                    String garden= getSectionFivefinalArray.getData().get(0).getGarden().getFlag().toString();
                    if(garden.equalsIgnoreCase("1"))
                    {
                        LLGarden5.setVisibility(View.VISIBLE);

                    }else if(garden.equalsIgnoreCase("0"))
                    {
                        LLGarden5.setVisibility(View.GONE);
                    }
                    String private_parking= getSectionFivefinalArray.getData().get(0).getPrivateParking().getFlag().toString();
                    if(private_parking.equalsIgnoreCase("1"))
                    {
                        LLPrivateParking5.setVisibility(View.VISIBLE);

                        modelList_info = (ArrayList<Info>) getSectionFivefinalArray.getData().get(0).getPrivateParking().getInfo();

                        FuncSpinner5(modelList_info,spinnerPrivateParking5);

                    }else if(private_parking.equalsIgnoreCase("0"))
                    {
                        LLPrivateParking5.setVisibility(View.GONE);
                    }
                }
            }

        }else  if (Constants.USER_Select_Occation.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                if(isCategory)
                {
                    OccasionDataModel finalArray = new Gson().fromJson(response,new TypeToken<OccasionDataModel>(){}.getType());

                    String status= String.valueOf(finalArray.getErrorCode());

                    if (status.equalsIgnoreCase("0")){

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
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    private void FuncSpinner(final ArrayList<Info> CategoryArray, Spinner SpinnerNew) {

        CreateNormalAdsCustomAdapter customAdapter=new CreateNormalAdsCustomAdapter(HarshitCreateNormalAdd.this ,CategoryArray);
        SpinnerNew.setAdapter(customAdapter);

        SpinnerNew.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                //   String value=CategoryArray[position].toString();

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });
    }


    private void FuncSpinner2(final ArrayList<Info> CategoryArray, Spinner SpinnerNew) {

        CreateNormalAdsCustomAdapterTwo customAdapter1=new CreateNormalAdsCustomAdapterTwo(HarshitCreateNormalAdd.this ,CategoryArray);
        SpinnerNew.setAdapter(customAdapter1);


        SpinnerNew.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                //   String value=CategoryArray[position].toString();

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });
    }


    private void FuncSpinner3(final ArrayList<Info> CategoryArray, Spinner SpinnerNew) {

        CreateNormalAdsCustomAdapterThree customAdapter3=new CreateNormalAdsCustomAdapterThree(HarshitCreateNormalAdd.this ,CategoryArray);

        SpinnerNew.setAdapter(customAdapter3);


        SpinnerNew.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                //   String value=CategoryArray[position].toString();

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });
    }

    private void FuncSpinner4(final ArrayList<Info> CategoryArray, Spinner SpinnerNew) {

        CreateNormalAdsCustomAdapterFour customAdapter4=new CreateNormalAdsCustomAdapterFour(HarshitCreateNormalAdd.this ,CategoryArray);

        SpinnerNew.setAdapter(customAdapter4);


        SpinnerNew.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                //   String value=CategoryArray[position].toString();

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });
    }


    private void FuncSpinner5(final ArrayList<Info> CategoryArray, Spinner SpinnerNew) {

        CreateNormalAdsCustomAdapterFive customAdapter5=new CreateNormalAdsCustomAdapterFive(HarshitCreateNormalAdd.this ,CategoryArray);

        SpinnerNew.setAdapter(customAdapter5);

        SpinnerNew.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //   Strig value=CategoryArray[position].toString();
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });
    }

    private void setAdapterCategory(ArrayList<SubOccasionDataModel> subOccationList) {

        mAdapterCategory = new CategorySectionRecyclerViewAdapter(HarshitCreateNormalAdd.this, subOccationList);
        recycler_view_category.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view_category.setLayoutManager(layoutManager);
        recycler_view_category.setAdapter(mAdapterCategory);

        mAdapterCategory.SetOnItemClickListener(new CategorySectionRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, SubOccasionDataModel model, ImageView img_check) {

                if(model.getChecked()){
                    //  LL_one.setVisibility(View.GONE);

                    if( arrayCatId.get(0) == model.getCategoryId()){
                        // Remove section 1
                        LL_one.setVisibility(View.GONE);

                        arrayCatId.set(0, "0");
                        //  txtOne.setText("");

                    } else  if( arrayCatId.get(1) == model.getCategoryId()){
                        // Remove section 2
                        LL_Two.setVisibility(View.GONE);
                        arrayCatId.set(1, "0");
                        // txtTwo.setText("");

                    }else  if( arrayCatId.get(2) == model.getCategoryId()){
                        // Remove section 3
                        LL_Three.setVisibility(View.GONE);
                        arrayCatId.set(2, "0");
                        // txtThree.setText("");

                    }else  if( arrayCatId.get(3) == model.getCategoryId()){
                        // Remove section 4
                        LL_Four.setVisibility(View.GONE);
                        arrayCatId.set(3, "0");
                        // txtFour.setText("");

                    }else  if( arrayCatId.get(4) == model.getCategoryId()){
                        // Remove section 5
                        LL_Five.setVisibility(View.GONE);
                        arrayCatId.set(4, "0");
                        // txtFive.setText("");

                    }

                    img_check.setVisibility(View.GONE);
                    model.setChecked(false);
                    count = count - 1;
                    if(count == 0){
                        arrayCatId.clear();
                        arrayCatId.add("0");
                        arrayCatId.add("0");
                        arrayCatId.add("0");
                        arrayCatId.add("0");
                        arrayCatId.add("0");
                        LL_one.setVisibility(View.GONE);
                        LL_Two.setVisibility(View.GONE);
                        LL_Three.setVisibility(View.GONE);
                        LL_Four.setVisibility(View.GONE);
                        LL_Five.setVisibility(View.GONE);

                        catName = "No Item Selected";

                        String valget= model.getCategoryId();

                        // Toast.makeText(HarshitCreateNormalAdd.this, ""+valget, Toast.LENGTH_SHORT).show();

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


                }else if(count <1) {

                    recycler_view_category.setVisibility(View.GONE);

                    //LL_section_one.setVisibility(View.VISIBLE);

                    CategoryId=model.getCategoryId().toString();

                //    Toast.makeText(HarshitCreateNormalAdd.this, ""+CategoryId, Toast.LENGTH_SHORT).show();

                    img_check.setVisibility(View.VISIBLE);
                    model.setChecked(true);
                    count = count + 1;

                    if(count == 1){

                        isSetionOne=true;
                        isSetionTwo=false;

                        CreateNormalMethod(model.getCategoryId());

                        mProgressBar.setVisibility(View.VISIBLE);

                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        catName = "1 Items Selected";

                        //   txtOne.setText(model.getCategoryName());

                        txt_category_Name_title.setText(model.getCategoryName());
                        LL_one.setVisibility(View.VISIBLE);
                        arrayCatId.set(0, model.getCategoryId());

                    }else  if(count == 2){

                        isSetionOne=false;
                        isSetionTwo=true;

                        mProgressBar.setVisibility(View.VISIBLE);

                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        CreateNormalMethod(model.getCategoryId());

                        //  txtTwo.setText(model.getCategoryName());
                        txtCategoryNameTitle2.setText(model.getCategoryName());


                        catName = "2 Items Selected";
                        LL_Two.setVisibility(View.VISIBLE);
                        arrayCatId.set(1, model.getCategoryId());


                    }else  if(count == 3){

                        LL_Three.setVisibility(View.VISIBLE);

                        mProgressBar.setVisibility(View.VISIBLE);

                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        isSetionOne=false;
                        isSetionTwo=false;
                        isSetionThree=true;

                        CreateNormalMethod(model.getCategoryId());

                        txtCategoryNameTitle3.setText(model.getCategoryName());

                        catName = "3 Items Selected";
                        arrayCatId.set(2, model.getCategoryId());
                        // txtThree.setText(model.getCategoryName());

                    }else if(count == 4){

                        mProgressBar.setVisibility(View.VISIBLE);

                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        isSetionOne=false;
                        isSetionTwo=false;
                        isSetionThree=false;
                        isSetionFour=true;

                        CreateNormalMethod(model.getCategoryId());

                        LL_Four.setVisibility(View.VISIBLE);
                        catName = "4 Items Selected";
                        arrayCatId.set(3, model.getCategoryId());
                        //txtFour.setText(model.getCategoryName());

                        txtCategoryNameTitle4.setText(model.getCategoryName());

                    }else if(count == 5){

                        mProgressBar.setVisibility(View.VISIBLE);

                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        isSetionOne=false;
                        isSetionTwo=false;
                        isSetionThree=false;
                        isSetionFour=false;
                        isSetionFive=true;

                        CreateNormalMethod(model.getCategoryId());

                        txtCategoryNameTitle5.setText(model.getCategoryName());

                        LL_Five.setVisibility(View.VISIBLE);
                        catName =  "5 Items Selected";
                        arrayCatId.set(4, model.getCategoryId());
                        // txtFive.setText(model.getCategoryName());
                    }

                    txtcategory.setText(catName);

                }else{

                    Toast.makeText(HarshitCreateNormalAdd.this, "you can only select 1 category.", Toast.LENGTH_SHORT).show();
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

                                Toast.makeText(HarshitCreateNormalAdd.this, message, Toast.LENGTH_SHORT).show();

                                progressbar.setVisibility(View.GONE);

                                JSONObject jsonObject=obj.getJSONObject("data");

                                String Imgurl = jsonObject.getString("url");

                                modelList.add(new UploadImageTopNewsAbstractModel(Imgurl));

                                mAdapter.updateList(modelList);


                            } else {

                                Toast.makeText(HarshitCreateNormalAdd.this, message, Toast.LENGTH_SHORT).show();

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
                String user_id=   Preference.get(HarshitCreateNormalAdd.this,Preference.KEY_USER_ID);

                String access_token =   Preference.get(HarshitCreateNormalAdd.this,Preference.KEY_accessToken);

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



    //Image Set MultpleImage Show
    private void setAdapter() {


        // modelList.add(new UploadImageTopNewsAbstractModel("https://baqat4u.theoneinfotech.com//uploads/users/listing/1580219699_1528647557.png"));

        // modelList.add(new UploadImageBlogAbstractModel("https:\\/\\/baqat4u.theoneinfotech.com\\/\\/uploads\\/users\\/listing\\/1580214562_1056677455.png"));
        mAdapter = new UploadImageTopNewsRecyclerViewAdapter(HarshitCreateNormalAdd.this, modelList);

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


    public JSONObject getValueFromFields(CreateNormal objArray){

        JSONArray objPastMedi = new JSONArray();
        JSONObject objMain = new JSONObject();

        try {
            objMain.put("category_id", CategoryId);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (objArray!=null)
        {
            String condition= objArray.getData().get(0).getCondition().getFlag().toString();

            if(condition.equalsIgnoreCase("1"))
            {
                String ConditionValue = objArray.getData().get(0).getCondition().getInfo().get(Spinner_condition.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("condition", ConditionValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(condition.equalsIgnoreCase("0"))
            {

            }
            String type= objArray.getData().get(0).getType().getFlag().toString();
            if(type.equalsIgnoreCase("1"))
            {

                /*String typeValue = objArray.getData().get(0).getType().getInfo().get(Spinner_type_new.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("type", typeValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                JsonArray jsr=new JsonArray();

                for (int i=0;i<modelList_info.size();i++)
                {
                    if(modelList_info.get(i).getisSlectedType())
                    {
                        jsr.add(Integer.parseInt(modelList_info.get(i).getId()));
                    }
                }
                String typeValue= String.valueOf(jsr);

                try {

                    objMain.put("type", typeValue);

                } catch (JSONException e) {

                    e.printStackTrace();

                }


            }else if(type.equalsIgnoreCase("0"))
            {

            }
            String usage= objArray.getData().get(0).getUsage().getFlag().toString();
            if(usage.equalsIgnoreCase("1"))
            {

                try {
                    objMain.put("usage", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(usage.equalsIgnoreCase("0"))
            {


            }
            String color= objArray.getData().get(0).getColor().getFlag().toString();
            if(color.equalsIgnoreCase("1"))
            {
                String Section_one_str_color=edt_color.getText().toString();

                try {
                    objMain.put("color", Section_one_str_color);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(color.equalsIgnoreCase("0"))
            {

            }
            String transmission_type= objArray.getData().get(0).getTransmissionType().getFlag().toString();
            if(transmission_type.equalsIgnoreCase("1"))
            {
                String Section_one_str_transmission_type = edt_transmission_type.getText().toString();

                try {
                    objMain.put("transmission_type", Section_one_str_transmission_type);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(transmission_type.equalsIgnoreCase("0"))
            {

            }
            String region_specific= objArray.getData().get(0).getRegionSpecific().getFlag().toString();
            if(region_specific.equalsIgnoreCase("1"))
            {
                String section_One_Region_Specific=edt_Region_Specific.getText().toString();

                try {

                    objMain.put("region_specific", section_One_Region_Specific);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(region_specific.equalsIgnoreCase("0"))
            {

            }
            String offer= objArray.getData().get(0).getOffer().getFlag().toString();
            if(offer.equalsIgnoreCase("1"))
            {
                String OfferValue = objArray.getData().get(0).getOffer().getInfo().get(spinner_offer.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("offer", OfferValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(offer.equalsIgnoreCase("0"))
            {

            }
            String model= objArray.getData().get(0).getModel().getFlag().toString();
            if(model.equalsIgnoreCase("1"))
            {

                String Section_one_str_model =edt_model.getText().toString();

                try {
                    objMain.put("model", Section_one_str_model);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(model.equalsIgnoreCase("0"))
            {


            }
            String kilometers= objArray.getData().get(0).getKilometers().getFlag().toString();
            if(kilometers.equalsIgnoreCase("1"))
            {

                String Section_one_str_Kilometer =edt_kilometers.getText().toString();

                try {
                    objMain.put("kilometers", Section_one_str_Kilometer);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(kilometers.equalsIgnoreCase("0"))
            {

            }
            String no_of_cylinder= objArray.getData().get(0).getNoOfCylinder().getFlag().toString();
            if(no_of_cylinder.equalsIgnoreCase("1"))
            {
                String Section_one_str_Number_of_cylinder =edt_Number_of_Cylinder.getText().toString();

                try {
                    objMain.put("no_of_cylinder", Section_one_str_Number_of_cylinder);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(no_of_cylinder.equalsIgnoreCase("0"))
            {

            }
            String horsepower= objArray.getData().get(0).getHorsepower().getFlag().toString();
            if(horsepower.equalsIgnoreCase("1"))
            {
                String Section_one_str_Horspower=edt_Horsepower.getText().toString();

                try {
                    objMain.put("horsepower", Section_one_str_Horspower);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(horsepower.equalsIgnoreCase("0"))
            {

            }
            String warranty= objArray.getData().get(0).getWarranty().getFlag().toString();
            if(warranty.equalsIgnoreCase("1"))
            {
                String Section_one_str_Warranty=edt_Warranty.getText().toString();

                try {
                    objMain.put("warranty", Section_one_str_Warranty);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(warranty.equalsIgnoreCase("0"))
            {

            }
            String year_of_making= objArray.getData().get(0).getYearOfMaking().getFlag().toString();
            if(year_of_making.equalsIgnoreCase("1"))
            {
                String Section_one_str_Year_of_making=edt_year_of_making.getText().toString();

                try {
                    objMain.put("year_of_making", Section_one_str_Year_of_making);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(year_of_making.equalsIgnoreCase("0"))
            {


            }
            String number_of_door= objArray.getData().get(0).getNumberOfDoor().getFlag().toString();
            if(number_of_door.equalsIgnoreCase("1"))
            {
                String number_of_doorValue = objArray.getData().get(0).getNumberOfDoor().getInfo().get(spinner_number_of_door.getSelectedItemPosition()).getValue().toString();


                try {
                    objMain.put("number_of_door", number_of_doorValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(number_of_door.equalsIgnoreCase("0"))
            {

            }
            String rent_recurrence_payment= objArray.getData().get(0).getRentRecurrencePayment().getFlag().toString();
            if(rent_recurrence_payment.equalsIgnoreCase("1"))
            {
                String Section_one_str_If_Rent_Recurrence_Of_Payment=edt_If_Rent_Recurrence_Of_Payment.getText().toString();

                try {
                    objMain.put("rent_recurrence_payment", Section_one_str_If_Rent_Recurrence_Of_Payment);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(rent_recurrence_payment.equalsIgnoreCase("0"))
            {

            }
            String bedroom= objArray.getData().get(0).getBedroom().getFlag().toString();
            if(bedroom.equalsIgnoreCase("1"))
            {

                String bedroomValue = objArray.getData().get(0).getBedroom().getInfo().get(Spinner_bedRoom.getSelectedItemPosition()).getValue().toString();


                try {
                    objMain.put("bedroom", bedroomValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(bedroom.equalsIgnoreCase("0"))
            {

            }
            String bathroom= objArray.getData().get(0).getBathroom().getFlag().toString();
            if(bathroom.equalsIgnoreCase("1"))
            {

                String bathroomValue = objArray.getData().get(0).getBathroom().getInfo().get(Spinner_bathroom.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("bathroom", bathroomValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(bathroom.equalsIgnoreCase("0"))
            {

            }
            String developers= objArray.getData().get(0).getDevelopers().getFlag().toString();
            if(developers.equalsIgnoreCase("1"))
            {
                String Section_one_str_developers =edt_developers.getText().toString();

                try {
                    objMain.put("developers", Section_one_str_developers);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(developers.equalsIgnoreCase("0"))
            {

            }
            String status= objArray.getData().get(0).getStatus().getFlag().toString();
            if(status.equalsIgnoreCase("1"))
            {
                String statusValue = objArray.getData().get(0).getStatus().getInfo().get(Spinner_status.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("status", statusValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(status.equalsIgnoreCase("0"))
            {

            }
            String available_from= objArray.getData().get(0).getAvailableFrom().getFlag().toString();
            if(available_from.equalsIgnoreCase("1"))
            {

                try {
                    objMain.put("available_from", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(available_from.equalsIgnoreCase("0"))
            {

            }
            String available_to= objArray.getData().get(0).getAvailableTo().getFlag().toString();
            if(available_to.equalsIgnoreCase("1"))
            {
                try {
                    objMain.put("available_to", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(available_to.equalsIgnoreCase("0"))
            {

            }
            String size= objArray.getData().get(0).getSize().getFlag().toString();
            if(size.equalsIgnoreCase("1"))
            {
                String Section_one_str_size =edt_size.getText().toString();

                try {
                    objMain.put("size", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(size.equalsIgnoreCase("0"))
            {

            }
            String gender= objArray.getData().get(0).getGender().getFlag().toString();
            if(gender.equalsIgnoreCase("1"))
            {
                String Section_one_str_gender =edt_gender.getText().toString();

                try {
                    objMain.put("gender", Section_one_str_gender);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(gender.equalsIgnoreCase("0"))
            {


            }
            String nationality= objArray.getData().get(0).getNationality().getFlag().toString();
            if(nationality.equalsIgnoreCase("1"))
            {
                String Section_one_str_nationality =edt_nationality.getText().toString();

                try {
                    objMain.put("nationality", Section_one_str_nationality);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(nationality.equalsIgnoreCase("0"))
            {

            }
            String position= objArray.getData().get(0).getPosition().getFlag().toString();
            if(position.equalsIgnoreCase("1"))
            {
                String Section_one_str_ =edt_position.getText().toString();

                try {
                    objMain.put("position", Section_one_str_);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(position.equalsIgnoreCase("0"))
            {

            }
            String expected_start_date= objArray.getData().get(0).getExpectedStartDate().getFlag().toString();
            if(expected_start_date.equalsIgnoreCase("1"))
            {

                String Section_one_str_position =edt_expected_start_date.getText().toString();

                try {
                    objMain.put("expected_start_date", Section_one_str_position);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(expected_start_date.equalsIgnoreCase("0"))
            {

            }
            String degree= objArray.getData().get(0).getDegree().getFlag().toString();
            if(degree.equalsIgnoreCase("1"))
            {
                String Section_one_str_degree =edt_degree.getText().toString();

                try {
                    objMain.put("degree", Section_one_str_degree);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(degree.equalsIgnoreCase("0"))
            {


            }
            String monthly_salary= objArray.getData().get(0).getMonthlySalary().getFlag().toString();
            if(monthly_salary.equalsIgnoreCase("1"))
            {
                String Section_one_str_monthly_salary= edt_monthly_salary.getText().toString();

                try {
                    objMain.put("monthly_salary", Section_one_str_monthly_salary);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(monthly_salary.equalsIgnoreCase("0"))
            {

            }
            String benifits= objArray.getData().get(0).getBenifits().getFlag().toString();
            if(benifits.equalsIgnoreCase("1"))
            {
                String Section_one_str_benifits =edt_benifits.getText().toString();

                try {
                    objMain.put("benifits", Section_one_str_benifits);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(benifits.equalsIgnoreCase("0"))
            {

            }
            String current_position= objArray.getData().get(0).getCurrentPosition().getFlag().toString();
            if(current_position.equalsIgnoreCase("1"))
            {

                String Section_one_str_current_position =edt_current_position.getText().toString();

                try {
                    objMain.put("current_position", Section_one_str_current_position);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(current_position.equalsIgnoreCase("0"))
            {

            }
            String current_company= objArray.getData().get(0).getCurrentCompany().getFlag().toString();
            if(current_company.equalsIgnoreCase("1"))
            {
                String Section_one_str_current_company =edt_current_company.getText().toString();

                try {
                    objMain.put("current_company", Section_one_str_current_company);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(current_company.equalsIgnoreCase("0"))
            {

            }
            String notice_period= objArray.getData().get(0).getNoticePeriod().getFlag().toString();
            if(notice_period.equalsIgnoreCase("1"))
            {
                String Section_one_str_notice_period=edt_notice_period.getText().toString();

                try {
                    objMain.put("notice_period", Section_one_str_notice_period);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(notice_period.equalsIgnoreCase("0"))
            {

            }
            String expected_monthly_salary= objArray.getData().get(0).getExpectedMonthlySalary().getFlag().toString();
            if(expected_monthly_salary.equalsIgnoreCase("1"))
            {
                String Section_one_str_expected_monthly_salary =edt_expected_monthly_salary.getText().toString();

                try {
                    objMain.put("expected_monthly_salary", Section_one_str_expected_monthly_salary);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(expected_monthly_salary.equalsIgnoreCase("0"))
            {

            }
            String resume_file_path= objArray.getData().get(0).getResumeFilePath().getFlag().toString();
            if(resume_file_path.equalsIgnoreCase("1"))
            {
                String section_four_resume_file_path=edt_resume_file_path4.getText().toString();

                try {
                    objMain.put("resume_file_path", section_four_resume_file_path);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(resume_file_path.equalsIgnoreCase("0"))
            {

            }
            String body_type_id= objArray.getData().get(0).getBodyTypeId().getFlag().toString();
            if(body_type_id.equalsIgnoreCase("1"))
            {

                String statusValue = objArray.getData().get(0).getBodyTypeId().getInfo().get(spinner_body_type_id.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("body_type_id", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(body_type_id.equalsIgnoreCase("0"))
            {

            }
            String field_type_id= objArray.getData().get(0).getFieldTypeId().getFlag().toString();
            if(field_type_id.equalsIgnoreCase("1"))
            {
                String Section_one_str_field_type_id=edt_field_type_id.getText().toString();

                try {
                    objMain.put("field_type_id", Section_one_str_field_type_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(field_type_id.equalsIgnoreCase("0"))
            {

            }
            String career_level_id= objArray.getData().get(0).getCareerLevelId().getFlag().toString();
            if(career_level_id.equalsIgnoreCase("1"))
            {
                String Section_one_str_career_level_id=edt_career_level_id.getText().toString();

                try {
                    objMain.put("career_level_id", Section_one_str_career_level_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(career_level_id.equalsIgnoreCase("0"))
            {

            }
            String company_field_type_id= objArray.getData().get(0).getCompanyFieldTypeId().getFlag().toString();
            if(company_field_type_id.equalsIgnoreCase("1"))
            {
                String Section_one_str_company_field_type_id=edt_company_field_type_id.getText().toString();

                try {
                    objMain.put("company_field_type_id", Section_one_str_company_field_type_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(company_field_type_id.equalsIgnoreCase("0"))
            {
            }
            String brand= objArray.getData().get(0).getBrand().getFlag().toString();
            if(brand.equalsIgnoreCase("1"))
            {
                String Section_one_str_brand= edt_brand.getText().toString();

                try {
                    objMain.put("brand", Section_one_str_brand);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(brand.equalsIgnoreCase("0"))
            {
            }
            String maid_room= objArray.getData().get(0).getMaidRoom().getFlag().toString();
            if(maid_room.equalsIgnoreCase("1"))
            {
                String Section_one_str_maid_room=edt_maid_room.getText().toString();
                try {
                    objMain.put("maid_room", Section_one_str_maid_room);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(maid_room.equalsIgnoreCase("0"))
            {
            }
            String swimming_pool= objArray.getData().get(0).getSwimmingPool().getFlag().toString();
            if(swimming_pool.equalsIgnoreCase("1"))
            {
                String Section_one_str_swimming_poo= edt_swimming_poo.getText().toString();

                try {
                    objMain.put("swimming_pool", Section_one_str_swimming_poo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(swimming_pool.equalsIgnoreCase("0"))
            {

            }
            String garden= objArray.getData().get(0).getGarden().getFlag().toString();
            if(garden.equalsIgnoreCase("1"))
            {

                String Section_one_str_garden= edt_garden.getText().toString();

                try {
                    objMain.put("garden", Section_one_str_garden);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(garden.equalsIgnoreCase("0"))
            {
            }
            String private_parking= objArray.getData().get(0).getPrivateParking().getFlag().toString();
            if(private_parking.equalsIgnoreCase("1"))
            {

                String PrivateValue = objArray.getData().get(0).getPrivateParking().getInfo().get(spinner_Private_Parking.getSelectedItemPosition()).getValue().toString();


                try {
                    objMain.put("private_parking", PrivateValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(private_parking.equalsIgnoreCase("0"))
            {

            }
        }

        objPastMedi.put(objMain);

        getFinalCategoryFirstStr = objPastMedi.toString();

        System.out.println("Harshit pastMedicalStr = " + getFinalCategoryFirstStr);

        return objMain;
    }

    public JSONObject getValueFromFieldsTwo(CreateNormal objArray){

        JSONObject objMain2 = new JSONObject();

        JSONArray objPastMedi = new JSONArray();
        JSONObject objMain = new JSONObject();

        try {
            objMain.put("category_id", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (objArray!=null)
        {
            String condition= objArray.getData().get(0).getCondition().getFlag().toString();

            if(condition.equalsIgnoreCase("1"))
            {

                String ConditionValue = objArray.getData().get(0).getCondition().getInfo().get(Spinner_condition2.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("condition", ConditionValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(condition.equalsIgnoreCase("0"))
            {

            }
            String type= objArray.getData().get(0).getType().getFlag().toString();
            if(type.equalsIgnoreCase("1"))
            {

                String typeValue = objArray.getData().get(0).getType().getInfo().get(Spinner_type2.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("type", typeValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(type.equalsIgnoreCase("0"))
            {

            }
            String usage= objArray.getData().get(0).getUsage().getFlag().toString();
            if(usage.equalsIgnoreCase("1"))
            {

                try {
                    objMain.put("usage", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(usage.equalsIgnoreCase("0"))
            {


            }
            String color= objArray.getData().get(0).getColor().getFlag().toString();
            if(color.equalsIgnoreCase("1"))
            {
                String Section_one_str_color=edtColor2.getText().toString();

                try {
                    objMain.put("color", Section_one_str_color);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(color.equalsIgnoreCase("0"))
            {

            }
            String transmission_type= objArray.getData().get(0).getTransmissionType().getFlag().toString();
            if(transmission_type.equalsIgnoreCase("1"))
            {
                String Section_one_str_transmission_type = edtTransmissionType2.getText().toString();

                try {
                    objMain.put("transmission_type", Section_one_str_transmission_type);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(transmission_type.equalsIgnoreCase("0"))
            {

            }
            String region_specific= objArray.getData().get(0).getRegionSpecific().getFlag().toString();
            if(region_specific.equalsIgnoreCase("1"))
            {
                String section_One_Region_Specific=edtRegionSpecific2.getText().toString();

                try {

                    objMain.put("region_specific", section_One_Region_Specific);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(region_specific.equalsIgnoreCase("0"))
            {

            }
            String offer= objArray.getData().get(0).getOffer().getFlag().toString();
            if(offer.equalsIgnoreCase("1"))
            {
                String OfferValue = objArray.getData().get(0).getOffer().getInfo().get(spinnerOffer2.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("offer", OfferValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(offer.equalsIgnoreCase("0"))
            {

            }
            String model= objArray.getData().get(0).getModel().getFlag().toString();
            if(model.equalsIgnoreCase("1"))
            {

                String Section_one_str_model =edtModel2.getText().toString();

                try {
                    objMain.put("model", Section_one_str_model);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(model.equalsIgnoreCase("0"))
            {


            }
            String kilometers= objArray.getData().get(0).getKilometers().getFlag().toString();
            if(kilometers.equalsIgnoreCase("1"))
            {

                String Section_one_str_Kilometer =edtKilometers2.getText().toString();

                try {
                    objMain.put("kilometers", Section_one_str_Kilometer);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(kilometers.equalsIgnoreCase("0"))
            {

            }
            String no_of_cylinder= objArray.getData().get(0).getNoOfCylinder().getFlag().toString();
            if(no_of_cylinder.equalsIgnoreCase("1"))
            {
                String Section_one_str_Number_of_cylinder =edtNumberOfCylinder2.getText().toString();

                try {
                    objMain.put("no_of_cylinder", Section_one_str_Number_of_cylinder);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(no_of_cylinder.equalsIgnoreCase("0"))
            {

            }
            String horsepower= objArray.getData().get(0).getHorsepower().getFlag().toString();
            if(horsepower.equalsIgnoreCase("1"))
            {
                String Section_one_str_Horspower=edtHorsepower2.getText().toString();

                try {
                    objMain.put("horsepower", Section_one_str_Horspower);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(horsepower.equalsIgnoreCase("0"))
            {

            }
            String warranty= objArray.getData().get(0).getWarranty().getFlag().toString();
            if(warranty.equalsIgnoreCase("1"))
            {
                String Section_one_str_Warranty=edtWarranty2.getText().toString();

                try {
                    objMain.put("warranty", Section_one_str_Warranty);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(warranty.equalsIgnoreCase("0"))
            {

            }
            String year_of_making= objArray.getData().get(0).getYearOfMaking().getFlag().toString();
            if(year_of_making.equalsIgnoreCase("1"))
            {
                String Section_one_str_Year_of_making=edtYearOfMaking2.getText().toString();

                try {
                    objMain.put("year_of_making", Section_one_str_Year_of_making);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(year_of_making.equalsIgnoreCase("0"))
            {


            }
            String number_of_door= objArray.getData().get(0).getNumberOfDoor().getFlag().toString();
            if(number_of_door.equalsIgnoreCase("1"))
            {
                String number_of_doorValue = objArray.getData().get(0).getNumberOfDoor().getInfo().get(spinnerNumberOfDoor2.getSelectedItemPosition()).getValue().toString();


                try {
                    objMain.put("number_of_door", number_of_doorValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(number_of_door.equalsIgnoreCase("0"))
            {

            }
            String rent_recurrence_payment= objArray.getData().get(0).getRentRecurrencePayment().getFlag().toString();
            if(rent_recurrence_payment.equalsIgnoreCase("1"))
            {
                String Section_one_str_If_Rent_Recurrence_Of_Payment=edtIfRentRecurrenceOfPayment2.getText().toString();

                try {
                    objMain.put("rent_recurrence_payment", Section_one_str_If_Rent_Recurrence_Of_Payment);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(rent_recurrence_payment.equalsIgnoreCase("0"))
            {

            }
            String bedroom= objArray.getData().get(0).getBedroom().getFlag().toString();
            if(bedroom.equalsIgnoreCase("1"))
            {

                String bedroomValue = objArray.getData().get(0).getBedroom().getInfo().get(SpinnerBathroom2.getSelectedItemPosition()).getValue().toString();


                try {
                    objMain.put("bedroom", bedroomValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(bedroom.equalsIgnoreCase("0"))
            {

            }
            String bathroom= objArray.getData().get(0).getBathroom().getFlag().toString();
            if(bathroom.equalsIgnoreCase("1"))
            {

                String bathroomValue = objArray.getData().get(0).getBathroom().getInfo().get(SpinnerBathroom2.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("bathroom", bathroomValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(bathroom.equalsIgnoreCase("0"))
            {

            }
            String developers= objArray.getData().get(0).getDevelopers().getFlag().toString();
            if(developers.equalsIgnoreCase("1"))
            {
                String Section_one_str_developers =edtDevelopers2.getText().toString();

                try {
                    objMain.put("developers", Section_one_str_developers);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(developers.equalsIgnoreCase("0"))
            {

            }
            String status= objArray.getData().get(0).getStatus().getFlag().toString();
            if(status.equalsIgnoreCase("1"))
            {
                String statusValue = objArray.getData().get(0).getStatus().getInfo().get(SpinnerStatus2.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("status", statusValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(status.equalsIgnoreCase("0"))
            {

            }
            String available_from= objArray.getData().get(0).getAvailableFrom().getFlag().toString();
            if(available_from.equalsIgnoreCase("1"))
            {

                try {
                    objMain.put("available_from", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(available_from.equalsIgnoreCase("0"))
            {

            }
            String available_to= objArray.getData().get(0).getAvailableTo().getFlag().toString();
            if(available_to.equalsIgnoreCase("1"))
            {
                try {
                    objMain.put("available_to", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(available_to.equalsIgnoreCase("0"))
            {

            }
            String size= objArray.getData().get(0).getSize().getFlag().toString();
            if(size.equalsIgnoreCase("1"))
            {
                String Section_one_str_size =edtSize2.getText().toString();

                try {
                    objMain.put("size", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(size.equalsIgnoreCase("0"))
            {

            }
            String gender= objArray.getData().get(0).getGender().getFlag().toString();
            if(gender.equalsIgnoreCase("1"))
            {
                String Section_one_str_gender =edtGender2.getText().toString();

                try {
                    objMain.put("gender", Section_one_str_gender);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(gender.equalsIgnoreCase("0"))
            {


            }
            String nationality= objArray.getData().get(0).getNationality().getFlag().toString();
            if(nationality.equalsIgnoreCase("1"))
            {
                String Section_one_str_nationality =edtNationality2.getText().toString();

                try {
                    objMain.put("nationality", Section_one_str_nationality);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(nationality.equalsIgnoreCase("0"))
            {

            }
            String position= objArray.getData().get(0).getPosition().getFlag().toString();
            if(position.equalsIgnoreCase("1"))
            {
                String Section_one_str_ =edtPosition2.getText().toString();

                try {
                    objMain.put("position", Section_one_str_);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(position.equalsIgnoreCase("0"))
            {

            }
            String expected_start_date= objArray.getData().get(0).getExpectedStartDate().getFlag().toString();
            if(expected_start_date.equalsIgnoreCase("1"))
            {

                String Section_one_str_position =edtExpectedStartDate2.getText().toString();

                try {
                    objMain.put("expected_start_date", Section_one_str_position);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(expected_start_date.equalsIgnoreCase("0"))
            {

            }
            String degree= objArray.getData().get(0).getDegree().getFlag().toString();
            if(degree.equalsIgnoreCase("1"))
            {
                String Section_one_str_degree =edtDegree2.getText().toString();

                try {
                    objMain.put("degree", Section_one_str_degree);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(degree.equalsIgnoreCase("0"))
            {


            }
            String monthly_salary= objArray.getData().get(0).getMonthlySalary().getFlag().toString();
            if(monthly_salary.equalsIgnoreCase("1"))
            {
                String Section_one_str_monthly_salary= edtMonthlySalary2.getText().toString();

                try {
                    objMain.put("monthly_salary", Section_one_str_monthly_salary);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(monthly_salary.equalsIgnoreCase("0"))
            {

            }
            String benifits= objArray.getData().get(0).getBenifits().getFlag().toString();
            if(benifits.equalsIgnoreCase("1"))
            {
                String Section_one_str_benifits =edtBenifits2.getText().toString();

                try {
                    objMain.put("benifits", Section_one_str_benifits);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(benifits.equalsIgnoreCase("0"))
            {

            }
            String current_position= objArray.getData().get(0).getCurrentPosition().getFlag().toString();
            if(current_position.equalsIgnoreCase("1"))
            {

                String Section_one_str_current_position =edtCurrentPosition2.getText().toString();

                try {
                    objMain.put("current_position", Section_one_str_current_position);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(current_position.equalsIgnoreCase("0"))
            {

            }
            String current_company= objArray.getData().get(0).getCurrentCompany().getFlag().toString();
            if(current_company.equalsIgnoreCase("1"))
            {
                String Section_one_str_current_company =edtCurrentCompany2.getText().toString();

                try {
                    objMain.put("current_company", Section_one_str_current_company);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(current_company.equalsIgnoreCase("0"))
            {

            }
            String notice_period= objArray.getData().get(0).getNoticePeriod().getFlag().toString();
            if(notice_period.equalsIgnoreCase("1"))
            {
                String Section_one_str_notice_period=edtNoticePeriod2.getText().toString();

                try {
                    objMain.put("notice_period", Section_one_str_notice_period);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(notice_period.equalsIgnoreCase("0"))
            {

            }
            String expected_monthly_salary= objArray.getData().get(0).getExpectedMonthlySalary().getFlag().toString();
            if(expected_monthly_salary.equalsIgnoreCase("1"))
            {
                String Section_one_str_expected_monthly_salary =edtExpectedMonthlySalary2.getText().toString();

                try {
                    objMain.put("expected_monthly_salary", Section_one_str_expected_monthly_salary);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(expected_monthly_salary.equalsIgnoreCase("0"))
            {

            }
            String resume_file_path= objArray.getData().get(0).getResumeFilePath().getFlag().toString();
            if(resume_file_path.equalsIgnoreCase("1"))
            {
                String section_four_resume_file_path=edt_resume_file_path2.getText().toString();

                try {
                    objMain.put("resume_file_path", section_four_resume_file_path);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(resume_file_path.equalsIgnoreCase("0"))
            {

            }
            String body_type_id= objArray.getData().get(0).getBodyTypeId().getFlag().toString();
            if(body_type_id.equalsIgnoreCase("1"))
            {

                String statusValue = objArray.getData().get(0).getBodyTypeId().getInfo().get(spinnerBodyTypeId2.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("body_type_id", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(body_type_id.equalsIgnoreCase("0"))
            {

            }
            String field_type_id= objArray.getData().get(0).getFieldTypeId().getFlag().toString();
            if(field_type_id.equalsIgnoreCase("1"))
            {
                String Section_one_str_field_type_id=edt_resume_file_path2.getText().toString();

                try {
                    objMain.put("field_type_id", Section_one_str_field_type_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(field_type_id.equalsIgnoreCase("0"))
            {

            }
            String career_level_id= objArray.getData().get(0).getCareerLevelId().getFlag().toString();
            if(career_level_id.equalsIgnoreCase("1"))
            {
                String Section_one_str_career_level_id=edt_career_level_id2.getText().toString();

                try {
                    objMain.put("career_level_id", Section_one_str_career_level_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(career_level_id.equalsIgnoreCase("0"))
            {

            }
            String company_field_type_id= objArray.getData().get(0).getCompanyFieldTypeId().getFlag().toString();
            if(company_field_type_id.equalsIgnoreCase("1"))
            {
                String Section_one_str_company_field_type_id=edt_company_field_type_id2.getText().toString();

                try {
                    objMain.put("company_field_type_id", Section_one_str_company_field_type_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(company_field_type_id.equalsIgnoreCase("0"))
            {
            }
            String brand= objArray.getData().get(0).getBrand().getFlag().toString();
            if(brand.equalsIgnoreCase("1"))
            {
                String Section_one_str_brand= edtBrand2.getText().toString();

                try {
                    objMain.put("brand", Section_one_str_brand);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(brand.equalsIgnoreCase("0"))
            {
            }
            String maid_room= objArray.getData().get(0).getMaidRoom().getFlag().toString();
            if(maid_room.equalsIgnoreCase("1"))
            {
                String Section_one_str_maid_room=edtMaidRoom2.getText().toString();
                try {
                    objMain.put("maid_room", Section_one_str_maid_room);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(maid_room.equalsIgnoreCase("0"))
            {
            }
            String swimming_pool= objArray.getData().get(0).getSwimmingPool().getFlag().toString();
            if(swimming_pool.equalsIgnoreCase("1"))
            {
                String Section_one_str_swimming_poo= edtSwimmingPool2.getText().toString();

                try {
                    objMain.put("swimming_pool", Section_one_str_swimming_poo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(swimming_pool.equalsIgnoreCase("0"))
            {

            }
            String garden= objArray.getData().get(0).getGarden().getFlag().toString();
            if(garden.equalsIgnoreCase("1"))
            {

                String Section_one_str_garden= edtGarden2.getText().toString();

                try {
                    objMain.put("garden", Section_one_str_garden);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(garden.equalsIgnoreCase("0"))
            {
            }
            String private_parking= objArray.getData().get(0).getPrivateParking().getFlag().toString();
            if(private_parking.equalsIgnoreCase("1"))
            {

                String PrivateValue = objArray.getData().get(0).getPrivateParking().getInfo().get(spinnerPrivateParking2.getSelectedItemPosition()).getValue().toString();


                try {
                    objMain.put("private_parking", PrivateValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(private_parking.equalsIgnoreCase("0"))
            {

            }
        }

        objPastMedi.put(objMain);

        getFinalCategoryFirstStr = objPastMedi.toString();

        System.out.println("Harshit pastMedicalStr = " + getFinalCategoryFirstStr);

        return objMain;
    }

    public JSONObject getValueFromFieldsThree(CreateNormal objArray){

        JSONObject objMain2 = new JSONObject();

        JSONArray objPastMedi = new JSONArray();
        JSONObject objMain = new JSONObject();

        try {
            objMain.put("category_id", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (objArray!=null)
        {
            String condition= objArray.getData().get(0).getCondition().getFlag().toString();

            if(condition.equalsIgnoreCase("1"))
            {
                String ConditionValue = objArray.getData().get(0).getCondition().getInfo().get(SpinnerCondition3.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("condition", ConditionValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(condition.equalsIgnoreCase("0"))
            {

            }
            String type= objArray.getData().get(0).getType().getFlag().toString();
            if(type.equalsIgnoreCase("1"))
            {

                String typeValue = objArray.getData().get(0).getType().getInfo().get(SpinnerType3.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("type", typeValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(type.equalsIgnoreCase("0"))
            {

            }
            String usage= objArray.getData().get(0).getUsage().getFlag().toString();
            if(usage.equalsIgnoreCase("1"))
            {

                try {
                    objMain.put("usage", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(usage.equalsIgnoreCase("0"))
            {


            }
            String color= objArray.getData().get(0).getColor().getFlag().toString();
            if(color.equalsIgnoreCase("1"))
            {
                String Section_one_str_color=edtColor3.getText().toString();

                try {
                    objMain.put("color", Section_one_str_color);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(color.equalsIgnoreCase("0"))
            {

            }
            String transmission_type= objArray.getData().get(0).getTransmissionType().getFlag().toString();
            if(transmission_type.equalsIgnoreCase("1"))
            {
                String Section_one_str_transmission_type = edtTransmissionType3.getText().toString();

                try {
                    objMain.put("transmission_type", Section_one_str_transmission_type);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(transmission_type.equalsIgnoreCase("0"))
            {

            }
            String region_specific= objArray.getData().get(0).getRegionSpecific().getFlag().toString();
            if(region_specific.equalsIgnoreCase("1"))
            {
                String section_One_Region_Specific=edtRegionSpecific3.getText().toString();

                try {

                    objMain.put("region_specific", section_One_Region_Specific);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(region_specific.equalsIgnoreCase("0"))
            {

            }
            String offer= objArray.getData().get(0).getOffer().getFlag().toString();
            if(offer.equalsIgnoreCase("1"))
            {
                String OfferValue = objArray.getData().get(0).getOffer().getInfo().get(spinnerOffer3.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("offer", OfferValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(offer.equalsIgnoreCase("0"))
            {

            }
            String model= objArray.getData().get(0).getModel().getFlag().toString();
            if(model.equalsIgnoreCase("1"))
            {

                String Section_one_str_model =edtModel3.getText().toString();

                try {
                    objMain.put("model", Section_one_str_model);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(model.equalsIgnoreCase("0"))
            {


            }
            String kilometers= objArray.getData().get(0).getKilometers().getFlag().toString();
            if(kilometers.equalsIgnoreCase("1"))
            {

                String Section_one_str_Kilometer =edtKilometers3.getText().toString();

                try {
                    objMain.put("kilometers", Section_one_str_Kilometer);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(kilometers.equalsIgnoreCase("0"))
            {

            }
            String no_of_cylinder= objArray.getData().get(0).getNoOfCylinder().getFlag().toString();
            if(no_of_cylinder.equalsIgnoreCase("1"))
            {
                String Section_one_str_Number_of_cylinder =edtNumberOfCylinder3.getText().toString();

                try {
                    objMain.put("no_of_cylinder", Section_one_str_Number_of_cylinder);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(no_of_cylinder.equalsIgnoreCase("0"))
            {

            }
            String horsepower= objArray.getData().get(0).getHorsepower().getFlag().toString();
            if(horsepower.equalsIgnoreCase("1"))
            {
                String Section_one_str_Horspower=edtHorsepower3.getText().toString();

                try {
                    objMain.put("horsepower", Section_one_str_Horspower);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(horsepower.equalsIgnoreCase("0"))
            {

            }
            String warranty= objArray.getData().get(0).getWarranty().getFlag().toString();
            if(warranty.equalsIgnoreCase("1"))
            {
                String Section_one_str_Warranty=edtWarranty3.getText().toString();

                try {
                    objMain.put("warranty", Section_one_str_Warranty);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(warranty.equalsIgnoreCase("0"))
            {

            }
            String year_of_making= objArray.getData().get(0).getYearOfMaking().getFlag().toString();
            if(year_of_making.equalsIgnoreCase("1"))
            {
                String Section_one_str_Year_of_making=edtYearOfMaking3.getText().toString();

                try {
                    objMain.put("year_of_making", Section_one_str_Year_of_making);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(year_of_making.equalsIgnoreCase("0"))
            {


            }
            String number_of_door= objArray.getData().get(0).getNumberOfDoor().getFlag().toString();
            if(number_of_door.equalsIgnoreCase("1"))
            {
                String number_of_doorValue = objArray.getData().get(0).getNumberOfDoor().getInfo().get(spinnerNumberOfDoor3.getSelectedItemPosition()).getValue().toString();


                try {
                    objMain.put("number_of_door", number_of_doorValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(number_of_door.equalsIgnoreCase("0"))
            {

            }
            String rent_recurrence_payment= objArray.getData().get(0).getRentRecurrencePayment().getFlag().toString();
            if(rent_recurrence_payment.equalsIgnoreCase("1"))
            {
                String Section_one_str_If_Rent_Recurrence_Of_Payment=edtIfRentRecurrenceOfPayment3.getText().toString();

                try {
                    objMain.put("rent_recurrence_payment", Section_one_str_If_Rent_Recurrence_Of_Payment);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(rent_recurrence_payment.equalsIgnoreCase("0"))
            {

            }
            String bedroom= objArray.getData().get(0).getBedroom().getFlag().toString();
            if(bedroom.equalsIgnoreCase("1"))
            {

                String bedroomValue = objArray.getData().get(0).getBedroom().getInfo().get(SpinnerBathroom3.getSelectedItemPosition()).getValue().toString();


                try {
                    objMain.put("bedroom", bedroomValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(bedroom.equalsIgnoreCase("0"))
            {

            }
            String bathroom= objArray.getData().get(0).getBathroom().getFlag().toString();
            if(bathroom.equalsIgnoreCase("1"))
            {

                String bathroomValue = objArray.getData().get(0).getBathroom().getInfo().get(SpinnerBathroom3.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("bathroom", bathroomValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(bathroom.equalsIgnoreCase("0"))
            {

            }
            String developers= objArray.getData().get(0).getDevelopers().getFlag().toString();
            if(developers.equalsIgnoreCase("1"))
            {
                String Section_one_str_developers =edtDevelopers3.getText().toString();

                try {
                    objMain.put("developers", Section_one_str_developers);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(developers.equalsIgnoreCase("0"))
            {

            }
            String status= objArray.getData().get(0).getStatus().getFlag().toString();
            if(status.equalsIgnoreCase("1"))
            {
                String statusValue = objArray.getData().get(0).getStatus().getInfo().get(SpinnerStatus3.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("status", statusValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(status.equalsIgnoreCase("0"))
            {

            }
            String available_from= objArray.getData().get(0).getAvailableFrom().getFlag().toString();
            if(available_from.equalsIgnoreCase("1"))
            {

                try {
                    objMain.put("available_from", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(available_from.equalsIgnoreCase("0"))
            {

            }
            String available_to= objArray.getData().get(0).getAvailableTo().getFlag().toString();
            if(available_to.equalsIgnoreCase("1"))
            {
                try {
                    objMain.put("available_to", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(available_to.equalsIgnoreCase("0"))
            {

            }
            String size= objArray.getData().get(0).getSize().getFlag().toString();
            if(size.equalsIgnoreCase("1"))
            {
                String Section_one_str_size =edtSize3.getText().toString();

                try {
                    objMain.put("size", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(size.equalsIgnoreCase("0"))
            {

            }
            String gender= objArray.getData().get(0).getGender().getFlag().toString();
            if(gender.equalsIgnoreCase("1"))
            {
                String Section_one_str_gender =edtGender3.getText().toString();

                try {
                    objMain.put("gender", Section_one_str_gender);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(gender.equalsIgnoreCase("0"))
            {


            }
            String nationality= objArray.getData().get(0).getNationality().getFlag().toString();
            if(nationality.equalsIgnoreCase("1"))
            {
                String Section_one_str_nationality =edtNationality3.getText().toString();

                try {
                    objMain.put("nationality", Section_one_str_nationality);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(nationality.equalsIgnoreCase("0"))
            {

            }
            String position= objArray.getData().get(0).getPosition().getFlag().toString();
            if(position.equalsIgnoreCase("1"))
            {
                String Section_one_str_ =edtPosition3.getText().toString();

                try {
                    objMain.put("position", Section_one_str_);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(position.equalsIgnoreCase("0"))
            {

            }
            String expected_start_date= objArray.getData().get(0).getExpectedStartDate().getFlag().toString();
            if(expected_start_date.equalsIgnoreCase("1"))
            {

                String Section_one_str_position =edtExpectedStartDate3.getText().toString();

                try {
                    objMain.put("expected_start_date", Section_one_str_position);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(expected_start_date.equalsIgnoreCase("0"))
            {

            }
            String degree= objArray.getData().get(0).getDegree().getFlag().toString();
            if(degree.equalsIgnoreCase("1"))
            {
                String Section_one_str_degree =edtDegree3.getText().toString();

                try {
                    objMain.put("degree", Section_one_str_degree);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(degree.equalsIgnoreCase("0"))
            {


            }
            String monthly_salary= objArray.getData().get(0).getMonthlySalary().getFlag().toString();
            if(monthly_salary.equalsIgnoreCase("1"))
            {
                String Section_one_str_monthly_salary= edtMonthlySalary3.getText().toString();

                try {
                    objMain.put("monthly_salary", Section_one_str_monthly_salary);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(monthly_salary.equalsIgnoreCase("0"))
            {

            }
            String benifits= objArray.getData().get(0).getBenifits().getFlag().toString();
            if(benifits.equalsIgnoreCase("1"))
            {
                String Section_one_str_benifits =edtBenifits3.getText().toString();

                try {
                    objMain.put("benifits", Section_one_str_benifits);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(benifits.equalsIgnoreCase("0"))
            {

            }
            String current_position= objArray.getData().get(0).getCurrentPosition().getFlag().toString();
            if(current_position.equalsIgnoreCase("1"))
            {

                String Section_one_str_current_position =edtCurrentPosition3.getText().toString();

                try {
                    objMain.put("current_position", Section_one_str_current_position);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(current_position.equalsIgnoreCase("0"))
            {

            }
            String current_company= objArray.getData().get(0).getCurrentCompany().getFlag().toString();
            if(current_company.equalsIgnoreCase("1"))
            {
                String Section_one_str_current_company =edtCurrentCompany3.getText().toString();

                try {
                    objMain.put("current_company", Section_one_str_current_company);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(current_company.equalsIgnoreCase("0"))
            {

            }
            String notice_period= objArray.getData().get(0).getNoticePeriod().getFlag().toString();
            if(notice_period.equalsIgnoreCase("1"))
            {
                String Section_one_str_notice_period=edtNoticePeriod3.getText().toString();

                try {
                    objMain.put("notice_period", Section_one_str_notice_period);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(notice_period.equalsIgnoreCase("0"))
            {

            }
            String expected_monthly_salary= objArray.getData().get(0).getExpectedMonthlySalary().getFlag().toString();
            if(expected_monthly_salary.equalsIgnoreCase("1"))
            {
                String Section_one_str_expected_monthly_salary =edtExpectedMonthlySalary3.getText().toString();

                try {
                    objMain.put("expected_monthly_salary", Section_one_str_expected_monthly_salary);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(expected_monthly_salary.equalsIgnoreCase("0"))
            {

            }
            String resume_file_path= objArray.getData().get(0).getResumeFilePath().getFlag().toString();
            if(resume_file_path.equalsIgnoreCase("1"))
            {
                String section_four_resume_file_path=edt_resume_file_path3.getText().toString();

                try {
                    objMain.put("resume_file_path", section_four_resume_file_path);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(resume_file_path.equalsIgnoreCase("0"))
            {

            }
            String body_type_id= objArray.getData().get(0).getBodyTypeId().getFlag().toString();
            if(body_type_id.equalsIgnoreCase("1"))
            {

                String statusValue = objArray.getData().get(0).getBodyTypeId().getInfo().get(spinnerBodyTypeId3.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("body_type_id", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(body_type_id.equalsIgnoreCase("0"))
            {

            }
            String field_type_id= objArray.getData().get(0).getFieldTypeId().getFlag().toString();
            if(field_type_id.equalsIgnoreCase("1"))
            {
                String Section_one_str_field_type_id=edt_resume_file_path3.getText().toString();

                try {
                    objMain.put("field_type_id", Section_one_str_field_type_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(field_type_id.equalsIgnoreCase("0"))
            {

            }
            String career_level_id= objArray.getData().get(0).getCareerLevelId().getFlag().toString();
            if(career_level_id.equalsIgnoreCase("1"))
            {
                String Section_one_str_career_level_id=edt_career_level_id3.getText().toString();

                try {
                    objMain.put("career_level_id", Section_one_str_career_level_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(career_level_id.equalsIgnoreCase("0"))
            {

            }
            String company_field_type_id= objArray.getData().get(0).getCompanyFieldTypeId().getFlag().toString();
            if(company_field_type_id.equalsIgnoreCase("1"))
            {
                String Section_one_str_company_field_type_id=edt_company_field_type_id3.getText().toString();

                try {
                    objMain.put("company_field_type_id", Section_one_str_company_field_type_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(company_field_type_id.equalsIgnoreCase("0"))
            {
            }
            String brand= objArray.getData().get(0).getBrand().getFlag().toString();
            if(brand.equalsIgnoreCase("1"))
            {
                String Section_one_str_brand= edtBrand3.getText().toString();

                try {
                    objMain.put("brand", Section_one_str_brand);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(brand.equalsIgnoreCase("0"))
            {
            }
            String maid_room= objArray.getData().get(0).getMaidRoom().getFlag().toString();
            if(maid_room.equalsIgnoreCase("1"))
            {
                String Section_one_str_maid_room=edtMaidRoom3.getText().toString();
                try {
                    objMain.put("maid_room", Section_one_str_maid_room);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(maid_room.equalsIgnoreCase("0"))
            {
            }
            String swimming_pool= objArray.getData().get(0).getSwimmingPool().getFlag().toString();
            if(swimming_pool.equalsIgnoreCase("1"))
            {
                String Section_one_str_swimming_poo= edtSwimmingPool3.getText().toString();

                try {
                    objMain.put("swimming_pool", Section_one_str_swimming_poo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(swimming_pool.equalsIgnoreCase("0"))
            {

            }
            String garden= objArray.getData().get(0).getGarden().getFlag().toString();
            if(garden.equalsIgnoreCase("1"))
            {

                String Section_one_str_garden= edtGarden3.getText().toString();

                try {
                    objMain.put("garden", Section_one_str_garden);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(garden.equalsIgnoreCase("0"))
            {
            }
            String private_parking= objArray.getData().get(0).getPrivateParking().getFlag().toString();
            if(private_parking.equalsIgnoreCase("1"))
            {

                String PrivateValue = objArray.getData().get(0).getPrivateParking().getInfo().get(spinnerPrivateParking3.getSelectedItemPosition()).getValue().toString();


                try {
                    objMain.put("private_parking", PrivateValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(private_parking.equalsIgnoreCase("0"))
            {

            }
        }

        objPastMedi.put(objMain);

        getFinalCategoryFirstStr = objPastMedi.toString();

        System.out.println("Harshit pastMedicalStr = " + getFinalCategoryFirstStr);

        return objMain;
    }

    public JSONObject getValueFromFieldsFour(CreateNormal objArray){

        JSONObject objMain2 = new JSONObject();

        JSONArray objPastMedi = new JSONArray();
        JSONObject objMain = new JSONObject();

        try {
            objMain.put("category_id", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (objArray!=null)
        {
            String condition= objArray.getData().get(0).getCondition().getFlag().toString();

            if(condition.equalsIgnoreCase("1"))
            {
                String ConditionValue = objArray.getData().get(0).getCondition().getInfo().get(SpinnerCondition4.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("condition", ConditionValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(condition.equalsIgnoreCase("0"))
            {

            }
            String type= objArray.getData().get(0).getType().getFlag().toString();
            if(type.equalsIgnoreCase("1"))
            {

                String typeValue = objArray.getData().get(0).getType().getInfo().get(SpinnerType4.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("type", typeValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(type.equalsIgnoreCase("0"))
            {

            }
            String usage= objArray.getData().get(0).getUsage().getFlag().toString();
            if(usage.equalsIgnoreCase("1"))
            {

                try {
                    objMain.put("usage", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(usage.equalsIgnoreCase("0"))
            {


            }
            String color= objArray.getData().get(0).getColor().getFlag().toString();
            if(color.equalsIgnoreCase("1"))
            {
                String Section_one_str_color=edtColor4.getText().toString();

                try {
                    objMain.put("color", Section_one_str_color);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(color.equalsIgnoreCase("0"))
            {

            }
            String transmission_type= objArray.getData().get(0).getTransmissionType().getFlag().toString();
            if(transmission_type.equalsIgnoreCase("1"))
            {
                String Section_one_str_transmission_type = edtTransmissionType4.getText().toString();

                try {
                    objMain.put("transmission_type", Section_one_str_transmission_type);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(transmission_type.equalsIgnoreCase("0"))
            {

            }
            String region_specific= objArray.getData().get(0).getRegionSpecific().getFlag().toString();
            if(region_specific.equalsIgnoreCase("1"))
            {
                String section_One_Region_Specific=edtRegionSpecific4.getText().toString();

                try {

                    objMain.put("region_specific", section_One_Region_Specific);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(region_specific.equalsIgnoreCase("0"))
            {

            }
            String offer= objArray.getData().get(0).getOffer().getFlag().toString();
            if(offer.equalsIgnoreCase("1"))
            {
                String OfferValue = objArray.getData().get(0).getOffer().getInfo().get(spinnerOffer4.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("offer", OfferValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(offer.equalsIgnoreCase("0"))
            {

            }
            String model= objArray.getData().get(0).getModel().getFlag().toString();
            if(model.equalsIgnoreCase("1"))
            {

                String Section_one_str_model =edtModel4.getText().toString();

                try {
                    objMain.put("model", Section_one_str_model);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(model.equalsIgnoreCase("0"))
            {


            }
            String kilometers= objArray.getData().get(0).getKilometers().getFlag().toString();
            if(kilometers.equalsIgnoreCase("1"))
            {

                String Section_one_str_Kilometer =edtKilometers4.getText().toString();

                try {
                    objMain.put("kilometers", Section_one_str_Kilometer);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(kilometers.equalsIgnoreCase("0"))
            {

            }
            String no_of_cylinder= objArray.getData().get(0).getNoOfCylinder().getFlag().toString();
            if(no_of_cylinder.equalsIgnoreCase("1"))
            {
                String Section_one_str_Number_of_cylinder =edtNumberOfCylinder4.getText().toString();

                try {
                    objMain.put("no_of_cylinder", Section_one_str_Number_of_cylinder);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(no_of_cylinder.equalsIgnoreCase("0"))
            {

            }
            String horsepower= objArray.getData().get(0).getHorsepower().getFlag().toString();
            if(horsepower.equalsIgnoreCase("1"))
            {
                String Section_one_str_Horspower=edtHorsepower4.getText().toString();

                try {
                    objMain.put("horsepower", Section_one_str_Horspower);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(horsepower.equalsIgnoreCase("0"))
            {

            }
            String warranty= objArray.getData().get(0).getWarranty().getFlag().toString();
            if(warranty.equalsIgnoreCase("1"))
            {
                String Section_one_str_Warranty=edtWarranty4.getText().toString();

                try {
                    objMain.put("warranty", Section_one_str_Warranty);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(warranty.equalsIgnoreCase("0"))
            {

            }
            String year_of_making= objArray.getData().get(0).getYearOfMaking().getFlag().toString();
            if(year_of_making.equalsIgnoreCase("1"))
            {
                String Section_one_str_Year_of_making=edtYearOfMaking4.getText().toString();

                try {
                    objMain.put("year_of_making", Section_one_str_Year_of_making);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(year_of_making.equalsIgnoreCase("0"))
            {


            }
            String number_of_door= objArray.getData().get(0).getNumberOfDoor().getFlag().toString();
            if(number_of_door.equalsIgnoreCase("1"))
            {
                String number_of_doorValue = objArray.getData().get(0).getNumberOfDoor().getInfo().get(spinnerNumberOfDoor4.getSelectedItemPosition()).getValue().toString();


                try {
                    objMain.put("number_of_door", number_of_doorValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(number_of_door.equalsIgnoreCase("0"))
            {

            }
            String rent_recurrence_payment= objArray.getData().get(0).getRentRecurrencePayment().getFlag().toString();
            if(rent_recurrence_payment.equalsIgnoreCase("1"))
            {
                String Section_one_str_If_Rent_Recurrence_Of_Payment=edtIfRentRecurrenceOfPayment4.getText().toString();

                try {
                    objMain.put("rent_recurrence_payment", Section_one_str_If_Rent_Recurrence_Of_Payment);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(rent_recurrence_payment.equalsIgnoreCase("0"))
            {

            }
            String bedroom= objArray.getData().get(0).getBedroom().getFlag().toString();
            if(bedroom.equalsIgnoreCase("1"))
            {

                String bedroomValue = objArray.getData().get(0).getBedroom().getInfo().get(SpinnerBathroom4.getSelectedItemPosition()).getValue().toString();


                try {
                    objMain.put("bedroom", bedroomValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(bedroom.equalsIgnoreCase("0"))
            {

            }
            String bathroom= objArray.getData().get(0).getBathroom().getFlag().toString();
            if(bathroom.equalsIgnoreCase("1"))
            {

                String bathroomValue = objArray.getData().get(0).getBathroom().getInfo().get(SpinnerBathroom4.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("bathroom", bathroomValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(bathroom.equalsIgnoreCase("0"))
            {

            }
            String developers= objArray.getData().get(0).getDevelopers().getFlag().toString();
            if(developers.equalsIgnoreCase("1"))
            {
                String Section_one_str_developers =edtDevelopers4.getText().toString();

                try {
                    objMain.put("developers", Section_one_str_developers);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(developers.equalsIgnoreCase("0"))
            {

            }
            String status= objArray.getData().get(0).getStatus().getFlag().toString();
            if(status.equalsIgnoreCase("1"))
            {
                String statusValue = objArray.getData().get(0).getStatus().getInfo().get(SpinnerStatus4.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("status", statusValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(status.equalsIgnoreCase("0"))
            {

            }
            String available_from= objArray.getData().get(0).getAvailableFrom().getFlag().toString();
            if(available_from.equalsIgnoreCase("1"))
            {

                try {
                    objMain.put("available_from", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(available_from.equalsIgnoreCase("0"))
            {

            }
            String available_to= objArray.getData().get(0).getAvailableTo().getFlag().toString();
            if(available_to.equalsIgnoreCase("1"))
            {
                try {
                    objMain.put("available_to", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(available_to.equalsIgnoreCase("0"))
            {

            }
            String size= objArray.getData().get(0).getSize().getFlag().toString();
            if(size.equalsIgnoreCase("1"))
            {
                String Section_one_str_size =edtSize4.getText().toString();

                try {
                    objMain.put("size", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(size.equalsIgnoreCase("0"))
            {

            }
            String gender= objArray.getData().get(0).getGender().getFlag().toString();
            if(gender.equalsIgnoreCase("1"))
            {
                String Section_one_str_gender =edtGender4.getText().toString();

                try {
                    objMain.put("gender", Section_one_str_gender);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(gender.equalsIgnoreCase("0"))
            {


            }
            String nationality= objArray.getData().get(0).getNationality().getFlag().toString();
            if(nationality.equalsIgnoreCase("1"))
            {
                String Section_one_str_nationality =edtNationality4.getText().toString();

                try {
                    objMain.put("nationality", Section_one_str_nationality);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(nationality.equalsIgnoreCase("0"))
            {

            }
            String position= objArray.getData().get(0).getPosition().getFlag().toString();
            if(position.equalsIgnoreCase("1"))
            {
                String Section_one_str_ =edtPosition4.getText().toString();

                try {
                    objMain.put("position", Section_one_str_);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(position.equalsIgnoreCase("0"))
            {

            }
            String expected_start_date= objArray.getData().get(0).getExpectedStartDate().getFlag().toString();
            if(expected_start_date.equalsIgnoreCase("1"))
            {

                String Section_one_str_position =edtExpectedStartDate4.getText().toString();

                try {
                    objMain.put("expected_start_date", Section_one_str_position);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(expected_start_date.equalsIgnoreCase("0"))
            {

            }
            String degree= objArray.getData().get(0).getDegree().getFlag().toString();
            if(degree.equalsIgnoreCase("1"))
            {
                String Section_one_str_degree =edtDegree4.getText().toString();

                try {
                    objMain.put("degree", Section_one_str_degree);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(degree.equalsIgnoreCase("0"))
            {


            }
            String monthly_salary= objArray.getData().get(0).getMonthlySalary().getFlag().toString();
            if(monthly_salary.equalsIgnoreCase("1"))
            {
                String Section_one_str_monthly_salary= edtMonthlySalary4.getText().toString();

                try {
                    objMain.put("monthly_salary", Section_one_str_monthly_salary);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(monthly_salary.equalsIgnoreCase("0"))
            {

            }
            String benifits= objArray.getData().get(0).getBenifits().getFlag().toString();
            if(benifits.equalsIgnoreCase("1"))
            {
                String Section_one_str_benifits =edtBenifits4.getText().toString();

                try {
                    objMain.put("benifits", Section_one_str_benifits);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(benifits.equalsIgnoreCase("0"))
            {

            }
            String current_position= objArray.getData().get(0).getCurrentPosition().getFlag().toString();
            if(current_position.equalsIgnoreCase("1"))
            {

                String Section_one_str_current_position =edtCurrentPosition4.getText().toString();

                try {
                    objMain.put("current_position", Section_one_str_current_position);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(current_position.equalsIgnoreCase("0"))
            {

            }
            String current_company= objArray.getData().get(0).getCurrentCompany().getFlag().toString();
            if(current_company.equalsIgnoreCase("1"))
            {
                String Section_one_str_current_company =edtCurrentCompany4.getText().toString();

                try {
                    objMain.put("current_company", Section_one_str_current_company);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(current_company.equalsIgnoreCase("0"))
            {

            }
            String notice_period= objArray.getData().get(0).getNoticePeriod().getFlag().toString();
            if(notice_period.equalsIgnoreCase("1"))
            {
                String Section_one_str_notice_period=edtNoticePeriod4.getText().toString();

                try {
                    objMain.put("notice_period", Section_one_str_notice_period);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(notice_period.equalsIgnoreCase("0"))
            {

            }
            String expected_monthly_salary= objArray.getData().get(0).getExpectedMonthlySalary().getFlag().toString();
            if(expected_monthly_salary.equalsIgnoreCase("1"))
            {
                String Section_one_str_expected_monthly_salary =edtExpectedMonthlySalary4.getText().toString();

                try {
                    objMain.put("expected_monthly_salary", Section_one_str_expected_monthly_salary);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(expected_monthly_salary.equalsIgnoreCase("0"))
            {

            }
            String resume_file_path= objArray.getData().get(0).getResumeFilePath().getFlag().toString();
            if(resume_file_path.equalsIgnoreCase("1"))
            {
                String section_four_resume_file_path=edt_resume_file_path4.getText().toString();

                try {
                    objMain.put("resume_file_path", section_four_resume_file_path);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(resume_file_path.equalsIgnoreCase("0"))
            {

            }
            String body_type_id= objArray.getData().get(0).getBodyTypeId().getFlag().toString();
            if(body_type_id.equalsIgnoreCase("1"))
            {

                String statusValue = objArray.getData().get(0).getBodyTypeId().getInfo().get(spinnerBodyTypeId4.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("body_type_id", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(body_type_id.equalsIgnoreCase("0"))
            {

            }
            String field_type_id= objArray.getData().get(0).getFieldTypeId().getFlag().toString();
            if(field_type_id.equalsIgnoreCase("1"))
            {
                String Section_one_str_field_type_id=edt_resume_file_path4.getText().toString();

                try {
                    objMain.put("field_type_id", Section_one_str_field_type_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(field_type_id.equalsIgnoreCase("0"))
            {

            }
            String career_level_id= objArray.getData().get(0).getCareerLevelId().getFlag().toString();
            if(career_level_id.equalsIgnoreCase("1"))
            {
                String Section_one_str_career_level_id=edt_career_level_id4.getText().toString();

                try {
                    objMain.put("career_level_id", Section_one_str_career_level_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(career_level_id.equalsIgnoreCase("0"))
            {

            }
            String company_field_type_id= objArray.getData().get(0).getCompanyFieldTypeId().getFlag().toString();
            if(company_field_type_id.equalsIgnoreCase("1"))
            {
                String Section_one_str_company_field_type_id=edt_company_field_type_id4.getText().toString();

                try {
                    objMain.put("company_field_type_id", Section_one_str_company_field_type_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(company_field_type_id.equalsIgnoreCase("0"))
            {
            }
            String brand= objArray.getData().get(0).getBrand().getFlag().toString();
            if(brand.equalsIgnoreCase("1"))
            {
                String Section_one_str_brand= edtBrand4.getText().toString();

                try {
                    objMain.put("brand", Section_one_str_brand);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(brand.equalsIgnoreCase("0"))
            {
            }
            String maid_room= objArray.getData().get(0).getMaidRoom().getFlag().toString();
            if(maid_room.equalsIgnoreCase("1"))
            {
                String Section_one_str_maid_room=edtMaidRoom4.getText().toString();
                try {
                    objMain.put("maid_room", Section_one_str_maid_room);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(maid_room.equalsIgnoreCase("0"))
            {
            }
            String swimming_pool= objArray.getData().get(0).getSwimmingPool().getFlag().toString();
            if(swimming_pool.equalsIgnoreCase("1"))
            {
                String Section_one_str_swimming_poo= edtSwimmingPool4.getText().toString();

                try {
                    objMain.put("swimming_pool", Section_one_str_swimming_poo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(swimming_pool.equalsIgnoreCase("0"))
            {

            }
            String garden= objArray.getData().get(0).getGarden().getFlag().toString();
            if(garden.equalsIgnoreCase("1"))
            {

                String Section_one_str_garden= edtGarden4.getText().toString();

                try {
                    objMain.put("garden", Section_one_str_garden);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(garden.equalsIgnoreCase("0"))
            {
            }
            String private_parking= objArray.getData().get(0).getPrivateParking().getFlag().toString();
            if(private_parking.equalsIgnoreCase("1"))
            {

                String PrivateValue = objArray.getData().get(0).getPrivateParking().getInfo().get(spinnerPrivateParking4.getSelectedItemPosition()).getValue().toString();


                try {
                    objMain.put("private_parking", PrivateValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(private_parking.equalsIgnoreCase("0"))
            {

            }
        }

        objPastMedi.put(objMain);

        getFinalCategoryFirstStr = objPastMedi.toString();

        System.out.println("Harshit pastMedicalStr = " + getFinalCategoryFirstStr);

        return objMain;
    }

    public JSONObject getValueFromFieldsFive(CreateNormal objArray){

        JSONObject objMain2 = new JSONObject();

        JSONArray objPastMedi = new JSONArray();
        JSONObject objMain = new JSONObject();

        try {
            objMain.put("category_id", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (objArray!=null)
        {
            String condition= objArray.getData().get(0).getCondition().getFlag().toString();

            if(condition.equalsIgnoreCase("1"))
            {
                String ConditionValue = objArray.getData().get(0).getCondition().getInfo().get(SpinnerCondition5.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("condition", ConditionValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(condition.equalsIgnoreCase("0"))
            {

            }
            String type= objArray.getData().get(0).getType().getFlag().toString();
            if(type.equalsIgnoreCase("1"))
            {

                String typeValue = objArray.getData().get(0).getType().getInfo().get(SpinnerType5.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("type", typeValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(type.equalsIgnoreCase("0"))
            {

            }
            String usage= objArray.getData().get(0).getUsage().getFlag().toString();
            if(usage.equalsIgnoreCase("1"))
            {

                try {
                    objMain.put("usage", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(usage.equalsIgnoreCase("0"))
            {


            }
            String color= objArray.getData().get(0).getColor().getFlag().toString();
            if(color.equalsIgnoreCase("1"))
            {
                String Section_one_str_color=edtColor5.getText().toString();

                try {
                    objMain.put("color", Section_one_str_color);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(color.equalsIgnoreCase("0"))
            {

            }
            String transmission_type= objArray.getData().get(0).getTransmissionType().getFlag().toString();
            if(transmission_type.equalsIgnoreCase("1"))
            {
                String Section_one_str_transmission_type = edtTransmissionType5.getText().toString();

                try {
                    objMain.put("transmission_type", Section_one_str_transmission_type);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(transmission_type.equalsIgnoreCase("0"))
            {

            }
            String region_specific= objArray.getData().get(0).getRegionSpecific().getFlag().toString();
            if(region_specific.equalsIgnoreCase("1"))
            {
                String section_One_Region_Specific=edtRegionSpecific5.getText().toString();

                try {

                    objMain.put("region_specific", section_One_Region_Specific);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(region_specific.equalsIgnoreCase("0"))
            {

            }
            String offer= objArray.getData().get(0).getOffer().getFlag().toString();
            if(offer.equalsIgnoreCase("1"))
            {
                String OfferValue = objArray.getData().get(0).getOffer().getInfo().get(spinnerOffer5.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("offer", OfferValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(offer.equalsIgnoreCase("0"))
            {

            }
            String model= objArray.getData().get(0).getModel().getFlag().toString();
            if(model.equalsIgnoreCase("1"))
            {

                String Section_one_str_model =edtModel5.getText().toString();

                try {
                    objMain.put("model", Section_one_str_model);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(model.equalsIgnoreCase("0"))
            {


            }
            String kilometers= objArray.getData().get(0).getKilometers().getFlag().toString();
            if(kilometers.equalsIgnoreCase("1"))
            {

                String Section_one_str_Kilometer =edtKilometers5.getText().toString();

                try {
                    objMain.put("kilometers", Section_one_str_Kilometer);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(kilometers.equalsIgnoreCase("0"))
            {

            }
            String no_of_cylinder= objArray.getData().get(0).getNoOfCylinder().getFlag().toString();
            if(no_of_cylinder.equalsIgnoreCase("1"))
            {
                String Section_one_str_Number_of_cylinder =edtNumberOfCylinder5.getText().toString();

                try {
                    objMain.put("no_of_cylinder", Section_one_str_Number_of_cylinder);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(no_of_cylinder.equalsIgnoreCase("0"))
            {

            }
            String horsepower= objArray.getData().get(0).getHorsepower().getFlag().toString();
            if(horsepower.equalsIgnoreCase("1"))
            {
                String Section_one_str_Horspower=edtHorsepower5.getText().toString();

                try {
                    objMain.put("horsepower", Section_one_str_Horspower);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(horsepower.equalsIgnoreCase("0"))
            {

            }
            String warranty= objArray.getData().get(0).getWarranty().getFlag().toString();
            if(warranty.equalsIgnoreCase("1"))
            {
                String Section_one_str_Warranty=edtWarranty5.getText().toString();

                try {
                    objMain.put("warranty", Section_one_str_Warranty);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(warranty.equalsIgnoreCase("0"))
            {

            }
            String year_of_making= objArray.getData().get(0).getYearOfMaking().getFlag().toString();
            if(year_of_making.equalsIgnoreCase("1"))
            {
                String Section_one_str_Year_of_making=edtYearOfMaking5.getText().toString();

                try {
                    objMain.put("year_of_making", Section_one_str_Year_of_making);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(year_of_making.equalsIgnoreCase("0"))
            {


            }
            String number_of_door= objArray.getData().get(0).getNumberOfDoor().getFlag().toString();
            if(number_of_door.equalsIgnoreCase("1"))
            {
                String number_of_doorValue = objArray.getData().get(0).getNumberOfDoor().getInfo().get(spinnerNumberOfDoor5.getSelectedItemPosition()).getValue().toString();


                try {
                    objMain.put("number_of_door", number_of_doorValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(number_of_door.equalsIgnoreCase("0"))
            {

            }
            String rent_recurrence_payment= objArray.getData().get(0).getRentRecurrencePayment().getFlag().toString();
            if(rent_recurrence_payment.equalsIgnoreCase("1"))
            {
                String Section_one_str_If_Rent_Recurrence_Of_Payment=edtIfRentRecurrenceOfPayment5.getText().toString();

                try {
                    objMain.put("rent_recurrence_payment", Section_one_str_If_Rent_Recurrence_Of_Payment);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(rent_recurrence_payment.equalsIgnoreCase("0"))
            {

            }
            String bedroom= objArray.getData().get(0).getBedroom().getFlag().toString();
            if(bedroom.equalsIgnoreCase("1"))
            {

                String bedroomValue = objArray.getData().get(0).getBedroom().getInfo().get(SpinnerBathroom5.getSelectedItemPosition()).getValue().toString();


                try {
                    objMain.put("bedroom", bedroomValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(bedroom.equalsIgnoreCase("0"))
            {

            }
            String bathroom= objArray.getData().get(0).getBathroom().getFlag().toString();
            if(bathroom.equalsIgnoreCase("1"))
            {

                String bathroomValue = objArray.getData().get(0).getBathroom().getInfo().get(SpinnerBathroom5.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("bathroom", bathroomValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(bathroom.equalsIgnoreCase("0"))
            {

            }
            String developers= objArray.getData().get(0).getDevelopers().getFlag().toString();
            if(developers.equalsIgnoreCase("1"))
            {
                String Section_one_str_developers =edtDevelopers5.getText().toString();

                try {
                    objMain.put("developers", Section_one_str_developers);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(developers.equalsIgnoreCase("0"))
            {

            }
            String status= objArray.getData().get(0).getStatus().getFlag().toString();
            if(status.equalsIgnoreCase("1"))
            {
                String statusValue = objArray.getData().get(0).getStatus().getInfo().get(SpinnerStatus5.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("status", statusValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(status.equalsIgnoreCase("0"))
            {

            }
            String available_from= objArray.getData().get(0).getAvailableFrom().getFlag().toString();
            if(available_from.equalsIgnoreCase("1"))
            {

                try {
                    objMain.put("available_from", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(available_from.equalsIgnoreCase("0"))
            {

            }
            String available_to= objArray.getData().get(0).getAvailableTo().getFlag().toString();
            if(available_to.equalsIgnoreCase("1"))
            {
                try {
                    objMain.put("available_to", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(available_to.equalsIgnoreCase("0"))
            {

            }
            String size= objArray.getData().get(0).getSize().getFlag().toString();
            if(size.equalsIgnoreCase("1"))
            {
                String Section_one_str_size =edtSize5.getText().toString();

                try {
                    objMain.put("size", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(size.equalsIgnoreCase("0"))
            {

            }
            String gender= objArray.getData().get(0).getGender().getFlag().toString();
            if(gender.equalsIgnoreCase("1"))
            {
                String Section_one_str_gender =edtGender5.getText().toString();

                try {
                    objMain.put("gender", Section_one_str_gender);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(gender.equalsIgnoreCase("0"))
            {


            }
            String nationality= objArray.getData().get(0).getNationality().getFlag().toString();
            if(nationality.equalsIgnoreCase("1"))
            {
                String Section_one_str_nationality =edtNationality5.getText().toString();

                try {
                    objMain.put("nationality", Section_one_str_nationality);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(nationality.equalsIgnoreCase("0"))
            {

            }
            String position= objArray.getData().get(0).getPosition().getFlag().toString();
            if(position.equalsIgnoreCase("1"))
            {
                String Section_one_str_ =edtPosition5.getText().toString();

                try {
                    objMain.put("position", Section_one_str_);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(position.equalsIgnoreCase("0"))
            {

            }
            String expected_start_date= objArray.getData().get(0).getExpectedStartDate().getFlag().toString();
            if(expected_start_date.equalsIgnoreCase("1"))
            {

                String Section_one_str_position =edtExpectedStartDate5.getText().toString();

                try {
                    objMain.put("expected_start_date", Section_one_str_position);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(expected_start_date.equalsIgnoreCase("0"))
            {

            }
            String degree= objArray.getData().get(0).getDegree().getFlag().toString();
            if(degree.equalsIgnoreCase("1"))
            {
                String Section_one_str_degree =edtDegree5.getText().toString();

                try {
                    objMain.put("degree", Section_one_str_degree);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(degree.equalsIgnoreCase("0"))
            {


            }
            String monthly_salary= objArray.getData().get(0).getMonthlySalary().getFlag().toString();
            if(monthly_salary.equalsIgnoreCase("1"))
            {
                String Section_one_str_monthly_salary= edtMonthlySalary5.getText().toString();

                try {
                    objMain.put("monthly_salary", Section_one_str_monthly_salary);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(monthly_salary.equalsIgnoreCase("0"))
            {

            }
            String benifits= objArray.getData().get(0).getBenifits().getFlag().toString();
            if(benifits.equalsIgnoreCase("1"))
            {
                String Section_one_str_benifits =edtBenifits5.getText().toString();

                try {
                    objMain.put("benifits", Section_one_str_benifits);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(benifits.equalsIgnoreCase("0"))
            {

            }
            String current_position= objArray.getData().get(0).getCurrentPosition().getFlag().toString();
            if(current_position.equalsIgnoreCase("1"))
            {

                String Section_one_str_current_position =edtCurrentPosition5.getText().toString();

                try {
                    objMain.put("current_position", Section_one_str_current_position);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(current_position.equalsIgnoreCase("0"))
            {

            }
            String current_company= objArray.getData().get(0).getCurrentCompany().getFlag().toString();
            if(current_company.equalsIgnoreCase("1"))
            {
                String Section_one_str_current_company =edtCurrentCompany5.getText().toString();

                try {
                    objMain.put("current_company", Section_one_str_current_company);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(current_company.equalsIgnoreCase("0"))
            {

            }
            String notice_period= objArray.getData().get(0).getNoticePeriod().getFlag().toString();
            if(notice_period.equalsIgnoreCase("1"))
            {
                String Section_one_str_notice_period=edtNoticePeriod5.getText().toString();

                try {
                    objMain.put("notice_period", Section_one_str_notice_period);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(notice_period.equalsIgnoreCase("0"))
            {

            }
            String expected_monthly_salary= objArray.getData().get(0).getExpectedMonthlySalary().getFlag().toString();
            if(expected_monthly_salary.equalsIgnoreCase("1"))
            {
                String Section_one_str_expected_monthly_salary =edtExpectedMonthlySalary5.getText().toString();

                try {
                    objMain.put("expected_monthly_salary", Section_one_str_expected_monthly_salary);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(expected_monthly_salary.equalsIgnoreCase("0"))
            {

            }
            String resume_file_path= objArray.getData().get(0).getResumeFilePath().getFlag().toString();
            if(resume_file_path.equalsIgnoreCase("1"))
            {
                String section_four_resume_file_path=edt_resume_file_path5.getText().toString();

                try {
                    objMain.put("resume_file_path", section_four_resume_file_path);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(resume_file_path.equalsIgnoreCase("0"))
            {

            }
            String body_type_id= objArray.getData().get(0).getBodyTypeId().getFlag().toString();
            if(body_type_id.equalsIgnoreCase("1"))
            {

                String statusValue = objArray.getData().get(0).getBodyTypeId().getInfo().get(spinnerBodyTypeId5.getSelectedItemPosition()).getValue().toString();

                try {
                    objMain.put("body_type_id", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(body_type_id.equalsIgnoreCase("0"))
            {

            }
            String field_type_id= objArray.getData().get(0).getFieldTypeId().getFlag().toString();
            if(field_type_id.equalsIgnoreCase("1"))
            {
                String Section_one_str_field_type_id=edt_resume_file_path5.getText().toString();

                try {
                    objMain.put("field_type_id", Section_one_str_field_type_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(field_type_id.equalsIgnoreCase("0"))
            {

            }
            String career_level_id= objArray.getData().get(0).getCareerLevelId().getFlag().toString();
            if(career_level_id.equalsIgnoreCase("1"))
            {
                String Section_one_str_career_level_id=edt_career_level_id5.getText().toString();

                try {
                    objMain.put("career_level_id", Section_one_str_career_level_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(career_level_id.equalsIgnoreCase("0"))
            {

            }
            String company_field_type_id= objArray.getData().get(0).getCompanyFieldTypeId().getFlag().toString();
            if(company_field_type_id.equalsIgnoreCase("1"))
            {
                String Section_one_str_company_field_type_id=edt_company_field_type_id5.getText().toString();

                try {
                    objMain.put("company_field_type_id", Section_one_str_company_field_type_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(company_field_type_id.equalsIgnoreCase("0"))
            {
            }
            String brand= objArray.getData().get(0).getBrand().getFlag().toString();
            if(brand.equalsIgnoreCase("1"))
            {
                String Section_one_str_brand= edtBrand5.getText().toString();

                try {
                    objMain.put("brand", Section_one_str_brand);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(brand.equalsIgnoreCase("0"))
            {
            }
            String maid_room= objArray.getData().get(0).getMaidRoom().getFlag().toString();
            if(maid_room.equalsIgnoreCase("1"))
            {
                String Section_one_str_maid_room=edtMaidRoom5.getText().toString();
                try {
                    objMain.put("maid_room", Section_one_str_maid_room);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(maid_room.equalsIgnoreCase("0"))
            {
            }
            String swimming_pool= objArray.getData().get(0).getSwimmingPool().getFlag().toString();
            if(swimming_pool.equalsIgnoreCase("1"))
            {
                String Section_one_str_swimming_poo= edtSwimmingPool5.getText().toString();

                try {
                    objMain.put("swimming_pool", Section_one_str_swimming_poo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(swimming_pool.equalsIgnoreCase("0"))
            {

            }
            String garden= objArray.getData().get(0).getGarden().getFlag().toString();
            if(garden.equalsIgnoreCase("1"))
            {

                String Section_one_str_garden= edtGarden5.getText().toString();

                try {
                    objMain.put("garden", Section_one_str_garden);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(garden.equalsIgnoreCase("0"))
            {
            }
            String private_parking= objArray.getData().get(0).getPrivateParking().getFlag().toString();
            if(private_parking.equalsIgnoreCase("1"))
            {

                String PrivateValue = objArray.getData().get(0).getPrivateParking().getInfo().get(spinnerPrivateParking5.getSelectedItemPosition()).getValue().toString();


                try {
                    objMain.put("private_parking", PrivateValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else if(private_parking.equalsIgnoreCase("0"))
            {

            }
        }

        objPastMedi.put(objMain);

        getFinalCategoryFirstStr = objPastMedi.toString();

        System.out.println("Harshit pastMedicalStr = " + getFinalCategoryFirstStr);

        return objMain;
    }



    private void setAdapter_Keyword() {

        mAdapter_looking = new Keyword_RecyclerViewAdapter( this, modellistKeyword1);

        recycler_view_profile_looking.setHasFixedSize( true );
        // use a linear layout manager
        //LinearLayoutManager layoutManager = new LinearLayoutManager( this );

        recycler_view_profile_looking.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true) );

        recycler_view_profile_looking.setAdapter( mAdapter_looking );

       /* mAdapter_looking.SetOnItemClickListener( new Keyword_RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, kewordAddModel model) {

            }
        });*/
    }


    private  void getkewordListshowdialogIn()
    {
        KeyWordMethod();

        final Button exit_button;
        final ProgressBar progressbar;
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();
        dialog.setContentView(R.layout.alert_dailodbox_keyword );
        spinner_pst_Keyword = dialog.findViewById(R.id.spinner_pst_intrested);
        exit_button =(Button) dialog.findViewById( R.id.exit_button );
        progressbar =(ProgressBar) dialog.findViewById( R.id.progressbar );
        progressbar.setVisibility(View.VISIBLE);

        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String keywordAdd= modellistKeyword.get(pos).getKeyword();

                modellistKeyword1.add(new kewordAddModel(keywordAdd));

                mAdapter_looking.updateList(modellistKeyword1);

                dialog.dismiss();
            }
        });

        spinner_pst_Keyword.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                progressbar.setVisibility(View.GONE);

                pos = position;
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });

        dialog.setCanceledOnTouchOutside(true);
    }

    private  void ShowInformation()
    {
        KeyWordMethod();

        final Button exit_button;
        final ProgressBar progressbar;
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();
        dialog.setContentView(R.layout.alert_search_icon );
        spinner_pst_Keyword = dialog.findViewById(R.id.spinner_pst_intrested);
        exit_button =(Button) dialog.findViewById( R.id.exit_button );
        progressbar =(ProgressBar) dialog.findViewById( R.id.progressbar );
        progressbar.setVisibility(View.VISIBLE);

        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.setCanceledOnTouchOutside(true);
    }








    //Google Api Place
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String address = (String) adapterView.getItemAtPosition(i);
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
}

class GooglePlacesAutocompleteAdapter1 extends ArrayAdapter implements Filterable {

    private ArrayList resultList;

    public GooglePlacesAutocompleteAdapter1(Context context, int textViewResourceId) {
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
