package shubham.com.baqat.ProfileFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import shubham.com.baqat.CreateAddScreen.CreateAddActivity;
import shubham.com.baqat.HomeBottomScreen.HomeBottomActivity;
import shubham.com.baqat.LoginSceen.ApiModel.LoginModel;
import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.OrderHistory.OrderHistoryActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
import shubham.com.baqat.UserProfile.UserProfileActivity;
import shubham.com.baqat.Utils.Preferences;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;

public class ProfileFragment extends Fragment implements IApiResponse,GoogleApiClient.OnConnectionFailedListener{

    View rootView;
    RelativeLayout RR_navigation;
    PopupMenu popup;
    RelativeLayout back_layout;
    TextView txt_name;
    TextView txt_number_listing,txt_number_active,txt_number_expired,txt_number_pending_listing;
    ProgressBar progressbar;
    TextView text_title;

    public static ImageView user_profile;

    //Google
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
    FirebaseAuth mAuth;

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_profile_fragment, container, false);


        findViews();
        progressbar.setVisibility(View.VISIBLE);

        text_title.setVisibility(View.VISIBLE);

        text_title.setText("Profile");

        String UserProfile = Preference.get(getActivity(),Preference.key_profileImage);

        if(UserProfile != null && !UserProfile.trim().equalsIgnoreCase("")) {
            Picasso.with(getActivity()).load(UserProfile)
                    .placeholder(R.drawable.defaultprofile)
                    .into(user_profile);
        }
        String UserName= Preference.get(getActivity(),Preference.key_UserName);

        txt_name.setText( UserName);

        RR_navigation.setVisibility(View.VISIBLE);
        back_layout.setVisibility(View.GONE);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        RR_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popup = new PopupMenu(getActivity(), v);
                popup.inflate(R.menu.menu_main);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id)
                        {
                            case R.id.Edit_Profile:
                                Intent intent=new Intent(getActivity(), UserProfileActivity.class);
                                startActivity(intent);
                                break;

                                case R.id.order_History:
                               Intent intent2=new Intent(getActivity(), OrderHistoryActivity.class);
                                startActivity(intent2);
                                break;
                                case R.id.Submit_Ads:
                        Intent intent3=new Intent(getActivity(), CreateAddActivity.class);
                                startActivity(intent3);
                                break;
                            case R.id.Logut:

                                String sign_type = Preference.get(getActivity(),Preference.KEY_Type);

                                if (sign_type.equalsIgnoreCase("Google_sign"))
                                {
                                    FirebaseAuth.getInstance().signOut();

                                    Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                                            new ResultCallback<Status>() {
                                                @Override
                                                public void onResult(Status status) {
                                                    if (status.isSuccess()) {

                                                        Preference.clearPreference(getActivity());

                                                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                                                        startActivity(intent);

                                                    } else {

                                                        Toast.makeText(getActivity(), "Session not close", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });

                                }else
                                {

                                    Preference.clearPreference(getActivity());

                                    Intent intent1=new Intent(getActivity(), LoginActivity.class);
                                    startActivity(intent1);
                                    getActivity().finish();
                                }
                                break;
                        }
                        return true;
                    }
                });

                popup.show();
            }
        });
        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        ProfileMethod();

        //setAdapter_Schdule();



        return  rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                   // String returnValue = data.getStringExtra("some_key");
                    ProfileMethod();
                }


        }
    }

    @Override
    public void onPause() {
        super.onPause();
        googleApiClient.stopAutoManage(getActivity());
        googleApiClient.disconnect();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private void findViews() {

        RR_navigation=(RelativeLayout) rootView.findViewById(R.id.RR_navigation);
        back_layout=(RelativeLayout) rootView.findViewById(R.id.back_layout);
        txt_name=(TextView) rootView.findViewById(R.id.txt_name);
        user_profile=(ImageView) rootView.findViewById(R.id.user_profile);

        txt_number_listing=(TextView) rootView.findViewById(R.id.txt_number_listing);
        txt_number_active=(TextView) rootView.findViewById(R.id.txt_number_active);
        txt_number_expired=(TextView) rootView.findViewById(R.id.txt_number_expired);
        txt_number_pending_listing=(TextView) rootView.findViewById(R.id.txt_number_pending_listing);
        text_title=(TextView) rootView.findViewById(R.id.text_title);
        progressbar = (ProgressBar) rootView.findViewById(R.id.progressbar);
    }



    public void ProfileMethod(){

        String user_id=   Preference.get(getActivity(),Preference.KEY_USER_ID);

        String access_token =   Preference.get(getActivity(),Preference.KEY_accessToken);

        HashMap<String,String> map = new HashMap<>();

        map.put("access_token",access_token);

        map.put("logged_in_user_id",user_id);

        ApiRequest apiRequest = new ApiRequest(getActivity(),this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_dashBoard, Constants.USER_dashBoard,map, Request.Method.POST);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_dashBoard.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                ProfileModel finalArray = new Gson().fromJson(response,new TypeToken<ProfileModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (status.equalsIgnoreCase("0")){

                    String active_listing=finalArray.getData().get(0).getActiveListing().toString();
                    String expired_list=finalArray.getData().get(0).getExpiredListing().toString();
                    String pending_list=finalArray.getData().get(0).getPendingApproval().toString();
                    String total_listing=finalArray.getData().get(0).getTotalListing().toString();

                    txt_number_listing.setText(total_listing);
                    txt_number_active.setText(active_listing);
                    txt_number_expired.setText(expired_list);
                    txt_number_pending_listing.setText(pending_list);


               //     Toast.makeText(getActivity(), ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
                else {

                    Toast.makeText(getActivity(), ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }

        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getActivity(), "Please Check Network", Toast.LENGTH_SHORT).show();
    }



}
