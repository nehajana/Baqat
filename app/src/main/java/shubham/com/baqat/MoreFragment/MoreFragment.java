package shubham.com.baqat.MoreFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import java.util.HashMap;

import shubham.com.baqat.BlogScreen.BlogActivity;
import shubham.com.baqat.CareerScreen.BussinessCareerActivity;
import shubham.com.baqat.ContactUsScreen.ContactUsActivity;
import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.R;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;
import shubham.com.baqat.forgetActivity.ForgetActivity;

public class MoreFragment extends Fragment implements IApiResponse {

    RelativeLayout back_layout;
    ImageView img_back;
    View rootView;
    RelativeLayout RR_career;
     RelativeLayout RR_blog,RR_contactus;
     Button btn_readmore;
    TextView text_title;
    public static MoreFragment newInstance(String param1, String param2) {
        MoreFragment fragment = new MoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.activity_more_fragment, container, false);

        findViews();
        text_title.setVisibility(View.VISIBLE);
        text_title.setText("More");


        back_layout.setVisibility(View.GONE);

        RR_career.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(getActivity(),BussinessCareerActivity.class);
                startActivity(intent);
            }
        });

        RR_blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
  Intent intent=new Intent(getActivity(),BlogActivity.class);
                startActivity(intent);
            }
        });

        RR_contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
       Intent intent=new Intent(getActivity(),ContactUsActivity.class);
                startActivity(intent);
            }
        });


        //setAdapter_Schdule();
        return  rootView;

    }

    private void findViews() {
        text_title=(TextView) rootView.findViewById(R.id.text_title);
        RR_career=(RelativeLayout) rootView.findViewById(R.id.RR_career);
        RR_blog=(RelativeLayout) rootView.findViewById(R.id.RR_blog);
        RR_contactus=(RelativeLayout) rootView.findViewById(R.id.RR_contactus);
        btn_readmore=(Button) rootView.findViewById(R.id.btn_readmore);
        back_layout=(RelativeLayout) rootView.findViewById(R.id.back_layout);
        img_back=(ImageView) rootView.findViewById(R.id.img_back);

    }



    public void loginMethod(String useremail, String pass,String gplus_id,String address,String mobile,String name){

        HashMap<String, String> map = new HashMap<>();
        map.put("email",useremail);
        map.put("password",pass);

        ApiRequest apiRequest = new ApiRequest(getActivity(),this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_LOGIN, Constants.USER_LOGIN,map, Request.Method.POST);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {
        if (Constants.USER_LOGIN.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                //LoginModel finalArray = new Gson().fromJson(response,new TypeToken<LoginModel>(){}.getType());
                // String status= String.valueOf(finalArray.getStatus());
               /* if (status.equalsIgnoreCase("true")){
                    Toast.makeText(this, ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, finalArray.getMessage(), Toast.LENGTH_SHORT).show();
                }*/
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getActivity(), "Please Check Network", Toast.LENGTH_SHORT).show();
    }


}
