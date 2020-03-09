package shubham.com.baqat.FilterScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

import shubham.com.baqat.CreateNormalAddScreen.CustomAdapter;
import shubham.com.baqat.CreateNormalAddScreen.OccasionApiModel.OccasionDataModel;
import shubham.com.baqat.CreateNormalAddScreen.OccasionApiModel.SelectOccasionList;
import shubham.com.baqat.CreateNormalAddScreen.SubOccasionDataModel;
import shubham.com.baqat.CreateNormalAddScreen.SubOccasionModel;
import shubham.com.baqat.FilterScreen.Model.DataItem;
import shubham.com.baqat.FilterScreen.Model.SubCategoryItem;
import shubham.com.baqat.GettingMarriedScreen.GattingMarriedActivity;
import shubham.com.baqat.HarshitCreateAdd.HarshitCreateNormalAdd;
import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.MainActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
import shubham.com.baqat.RestaurantsCateringScreen.RestaurentCateringActivity;
import shubham.com.baqat.UserProfile.ApiModel.CountryDataModel;
import shubham.com.baqat.UserProfile.ApiModel.CountryModel;
import shubham.com.baqat.UserProfile.Country_CustomAdapter;
import shubham.com.baqat.UserProfile.UserProfileActivity;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;

public class FilterActivity extends AppCompatActivity implements IApiResponse {


    private ExpandableListView lvCategory;

    private ArrayList<DataItem> arCategory;
    private ArrayList<SubCategoryItem> arSubCategory;
    private ArrayList<ArrayList<SubCategoryItem>> arSubCategoryFinal;

    private ArrayList<HashMap<String, String>> parentItems;
    private ArrayList<ArrayList<HashMap<String, String>>> childItems;
    private MyCategoriesExpandableListAdapter myCategoriesExpandableListAdapter;


    RelativeLayout back_layout;
    RelativeLayout RR_Applyfilter1;
    Spinner spinner_country;
    EditText edt_minimum_price;
    EditText edt_maximum_price;
    Spinner spinner_occation;
    Spinner spinner_category;

    boolean isSubCategory=false;
    boolean isCategory=false;

    String Category_id="";
    String Category_Name="";

    ArrayList<SelectOccasionList> OccationList = new ArrayList<>();
    ArrayList<SubOccasionDataModel> SubOccationList = new ArrayList<>();

    String categoryId="";
    String country_name="";

    private ArrayList<CountryDataModel> modelList_CountryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        findView();

        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        countryMethod();

        CreateNormalAddSection("1");

        spinner_occation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                categoryId=OccationList.get(position).getCategoryId();

                Category_Name=OccationList.get(position).getCategoryName();

                CreateNormalSubAddSection(categoryId);

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }

        });

        spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                country_name=modelList_CountryList.get(position).getCountryName();

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }

        });

        RR_Applyfilter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  String minimum_value=edt_minimum_price.getText().toString();
                String maximum_value=edt_maximum_price.getText().toString();

                Preference.save(FilterActivity.this,Preference.key_filter_location,country_name);
                Preference.save(FilterActivity.this,Preference.key_minimumPriceValue,minimum_value);
                Preference.save(FilterActivity.this,Preference.key_maximumPriceValue,maximum_value);

                Intent intent=new Intent(FilterActivity.this, RestaurentCateringActivity.class);
                intent.putExtra("Category_id",categoryId);
                intent.putExtra("Category_name",Category_Name);
                startActivity(intent);
*/
                Intent intent = new Intent(FilterActivity.this,CheckedActivity.class);
                startActivity(intent);
            }
        });






        setupReferences();



    }

    private void findView() {

        RR_Applyfilter1=(RelativeLayout) findViewById(R.id.RR_applyFilter1);
        back_layout=(RelativeLayout) findViewById(R.id.back_layout);
        spinner_country=(Spinner) findViewById(R.id.spinner_Location);
        spinner_occation=(Spinner) findViewById(R.id.spinner_occation);
        spinner_category=(Spinner) findViewById(R.id.spinner_category);
        edt_minimum_price=(EditText) findViewById(R.id.edt_minimum_price);
        edt_maximum_price=(EditText) findViewById(R.id.edt_maximum_price);

    }


    public void countryMethod(){

        String access_token =   Preference.get(FilterActivity.this,Preference.KEY_accessToken);

        HashMap<String, String> map = new HashMap<>();

        ApiRequest apiRequest = new ApiRequest(this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_getCountry, Constants.USER_getCountry,map, Request.Method.GET);
    }

    public void CreateNormalSubAddSection(String parent_category_id){

        isSubCategory=true;

        HashMap<String, String> map = new HashMap<>();

        map.put("parent_category_id",parent_category_id);

        map.put("is_mobile","1");

        ApiRequest apiRequest = new ApiRequest(FilterActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_Select_Occation_sub, Constants.USER_Select_Occation_sub,map, Request.Method.POST);
    }

    public void CreateNormalAddSection(String category_id){

        isCategory=true;

        HashMap<String, String> map = new HashMap<>();

        map.put("category_id",category_id);
        map.put("is_mobile","1");

        ApiRequest apiRequest = new ApiRequest(FilterActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_Select_Occation, Constants.USER_Select_Occation,map, Request.Method.POST);

    }

    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_getCountry.equalsIgnoreCase(tag_json_obj)) {

            if (!response.equalsIgnoreCase(null)) {

                CountryModel finalArray2 = new Gson().fromJson(response, new TypeToken<CountryModel>() {
                }.getType());

                String status = String.valueOf(finalArray2.getErrorCode());

                if (finalArray2.getErrorCode()== 2 && finalArray2.getMessage().equalsIgnoreCase("Please Insert Valid Access Token"))
                {
                    Preference.clearPreference(this);
                    Intent intent=new Intent(FilterActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else if (status.equalsIgnoreCase("0")) {

                    modelList_CountryList= (ArrayList<CountryDataModel>) finalArray2.getData();

                    Country_CustomAdapter customAdapter=new Country_CustomAdapter(getApplicationContext(),modelList_CountryList);

                    spinner_country.setAdapter(customAdapter);

                } else {

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
                        spinner_occation.setAdapter(customAdapter);

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

                        CustomAdapterSub customAdapter=new CustomAdapterSub(getApplicationContext(),SubOccationList);
                        spinner_category.setAdapter(customAdapter);

                        // setAdapterCategory(SubOccationList);

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


    private void setupReferences() {

        lvCategory = findViewById(R.id.lvCategory);
        arCategory = new ArrayList<>();
        arSubCategory = new ArrayList<>();
        parentItems = new ArrayList<>();
        childItems = new ArrayList<>();

        DataItem dataItem = new DataItem();
        dataItem.setCategoryId("1");
        dataItem.setCategoryName("Adventure");

        arSubCategory = new ArrayList<>();
        for(int i = 1; i < 6; i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName("Adventure: "+i);
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("2");
        dataItem.setCategoryName("Art");
        arSubCategory = new ArrayList<>();
        for(int j = 1; j < 6; j++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(j));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName("Art: "+j);
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("3");
        dataItem.setCategoryName("Cooking");
        arSubCategory = new ArrayList<>();
        for(int k = 1; k < 6; k++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(k));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName("Cooking: "+k);
            arSubCategory.add(subCategoryItem);
        }

        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        Log.d("TAG", "setupReferences: "+arCategory.size());

        for(DataItem data : arCategory){
//                        Log.i("Item id",item.id);
            ArrayList<HashMap<String, String>> childArrayList =new ArrayList<HashMap<String, String>>();
            HashMap<String, String> mapParent = new HashMap<String, String>();

            mapParent.put(ConstantManager.Parameter.CATEGORY_ID,data.getCategoryId());
            mapParent.put(ConstantManager.Parameter.CATEGORY_NAME,data.getCategoryName());

            int countIsChecked = 0;
            for(SubCategoryItem subCategoryItem : data.getSubCategory()) {

                HashMap<String, String> mapChild = new HashMap<String, String>();
                mapChild.put(ConstantManager.Parameter.SUB_ID,subCategoryItem.getSubId());
                mapChild.put(ConstantManager.Parameter.SUB_CATEGORY_NAME,subCategoryItem.getSubCategoryName());
                mapChild.put(ConstantManager.Parameter.CATEGORY_ID,subCategoryItem.getCategoryId());
                mapChild.put(ConstantManager.Parameter.IS_CHECKED,subCategoryItem.getIsChecked());

                if(subCategoryItem.getIsChecked().equalsIgnoreCase(ConstantManager.CHECK_BOX_CHECKED_TRUE)) {

                    countIsChecked++;
                }
                childArrayList.add(mapChild);
            }

            if(countIsChecked == data.getSubCategory().size()) {

                data.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_TRUE);
            }else {
                data.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            }

            mapParent.put(ConstantManager.Parameter.IS_CHECKED,data.getIsChecked());
            childItems.add(childArrayList);
            parentItems.add(mapParent);

        }

        ConstantManager.parentItems = parentItems;
        ConstantManager.childItems = childItems;

        myCategoriesExpandableListAdapter = new MyCategoriesExpandableListAdapter(this,parentItems,childItems,false);
        lvCategory.setAdapter(myCategoriesExpandableListAdapter);
    }
}
