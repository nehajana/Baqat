package shubham.com.baqat.HomeSearchApi;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import shubham.com.baqat.HomeSearchApi.Apimodel.HomeSearchModel;
import shubham.com.baqat.HomeSearchApi.Apimodel.HomedataSearchModel;
import shubham.com.baqat.R;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;

import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class HomeSearch extends AppCompatActivity implements IApiResponse {

    CharSequence charS;
    private RecyclerView recyclerView;
    int pageNo = 1;
    static boolean loadmore=true;
    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;
    public HomeSearch_RecyclerViewAdapter mAdapter;

    private ArrayList<HomedataSearchModel> modelList = new ArrayList<HomedataSearchModel>();
    RelativeLayout RR_ProgressBar;
    ProgressBar progressbar;
    EditText edt_search;
    int pastVisiblesItems, visibleItemCount, totalItemCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search_recycleview);

        // ButterKnife.bind(this);
        findViews();

      //  getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_);

        edt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideSoftKeyboard(HomeSearch.this);
                    return true;
                }
                return false;
            }
        });

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                // Toast.makeText( HomeProductListSearch.this, "", Toast.LENGTH_SHORT ).show();

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                // Toast.makeText( HomeProductListSearch.this, ""+charSequence, Toast.LENGTH_SHORT ).show();
                if(charSequence.length()<=0)
                {
                    modelList.clear();
                    mAdapter.updateList(modelList);
                   setAdapter(modelList);
                    recyclerView.setVisibility( View.INVISIBLE );

                }else
                {
                    recyclerView.setVisibility( View.VISIBLE );
                    charS=  charSequence;
                    System.out.println("");
                    modelList.clear();
                    setAdapter(modelList);
                    pageNo = 1;
                    SearchProductMethod(charSequence+"",String.valueOf(pageNo) );
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

              filter(editable.toString());
            }
        });


    }


    private void findViews() {
        edt_search=(EditText) findViewById(R.id.edt_search);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        RR_ProgressBar = (RelativeLayout) findViewById(R.id.RR_ProgressBar);
    }



  void filter(String text) {

        List<HomedataSearchModel> temp = new ArrayList();
        for (HomedataSearchModel d : modelList) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.getFirstName().toLowerCase().contains(text.toString().toLowerCase())) {
                temp.add(d);
            }
            //update recyclerview
            mAdapter.updateList((ArrayList<HomedataSearchModel>) temp);
        }
    }


    private void setAdapter(ArrayList<HomedataSearchModel> modelList) {

        mAdapter = new HomeSearch_RecyclerViewAdapter(HomeSearch.this, modelList);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager =new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new HomeSearch_RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, HomedataSearchModel model) {
            }
        });


    /*    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                // super.onScrolled(recyclerView, dx, dy);
                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisiblePosition == recyclerView.getChildCount()) {
                    if (loadmore) {
                        RR_ProgressBar.setVisibility(View.VISIBLE);
                        loadmore = false;
                        pageNo = pageNo+ 1;
                        System.out.println("pageNo = " + pageNo);
                        SearchProductMethod(charS+"", String.valueOf(pageNo) );
                    }
                }
            }
        });*/

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) { // scroll down
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
                    if (loadmore) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
// action
                            RR_ProgressBar.setVisibility(View.VISIBLE);
                            loadmore = false;
                            pageNo = pageNo+ 1;
                            System.out.println("pageNo = " + pageNo);
                            SearchProductMethod(charS+"", String.valueOf(pageNo) );
                        }
                    }
                }
            }
        });


    }


    public void SearchProductMethod(String keyword, String pageNo){
        if(pageNo.equalsIgnoreCase("1")){
            progressbar.setVisibility(View.VISIBLE);
        }else{
            progressbar.setVisibility(View.GONE);
        }

        HashMap<String, String> map = new HashMap<>();

        map.put("keyword",keyword);
        map.put("pageLimit","4");
        map.put("pageNo",pageNo);

        ApiRequest apiRequest = new ApiRequest(HomeSearch.this,this);

        apiRequest.postRequest( Constants.BASE_URL + Constants.USER_searchByKeyword, Constants.USER_searchByKeyword,map, Request.Method.POST);
    }



    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_searchByKeyword.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                progressbar.setVisibility(View.GONE);

                HomeSearchModel finalArray = new Gson().fromJson(response,new TypeToken<HomeSearchModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (status.equalsIgnoreCase("0")){

                    if(modelList == null  || modelList.size() == 0) {
                        modelList.addAll(finalArray.getData());
                        setAdapter(modelList);

                    }else{
                        modelList.addAll(finalArray.getData());
                        mAdapter.notifyDataSetChanged();
                    }

                    loadmore=true;
                    // Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                }
                else {

                    // Toast.makeText(this, "Data Not Fount", Toast.LENGTH_SHORT).show();
                }
            }

            RR_ProgressBar.setVisibility(View.GONE);
        }

    }


    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "Please Enter Check..", Toast.LENGTH_SHORT).show();
    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

}
