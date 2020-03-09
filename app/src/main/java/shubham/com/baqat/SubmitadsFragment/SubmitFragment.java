package shubham.com.baqat.SubmitadsFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import java.util.HashMap;

import shubham.com.baqat.R;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;

public class SubmitFragment extends Fragment implements IApiResponse {

    View rootView;

    public static SubmitFragment newInstance(String param1, String param2) {
        SubmitFragment fragment = new SubmitFragment();
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
        rootView = inflater.inflate(R.layout.activity_abouts_fragment, container, false);

        //setAdapter_Schdule();
        return  rootView;
    }

    private void findViews() {


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
