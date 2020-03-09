package shubham.com.baqat.PaymentScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;

import shubham.com.baqat.CreateAddScreen.CreateAddActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;

public class PaypalActivity extends AppCompatActivity implements View.OnClickListener,IApiResponse {

    private Button buttonPay;
    private TextView txt_amnt;
    private EditText editTextAmount;
    //Payment Amount
    private String paymentAmount="";
    public static final int PAYPAL_REQUEST_CODE = 123;
    private static PayPalConfiguration config;
    String paymentDetails;
    RelativeLayout back_layout;
    TextView text_career;

    String amount;
    String PaymentId="";
    TextView txt_pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);

        buttonPay = (Button) findViewById(R.id.buttonPay);
        back_layout = (RelativeLayout) findViewById(R.id.back_layout);
        text_career = (TextView) findViewById(R.id.text_career);
        txt_pay = (TextView) findViewById(R.id.txt_pay);


    //    text_career.setText("Create Normal Ads package");
       // text_career.setText("Executive Ads package");
        //text_career.setText("Blog Ads package");
        //text_career.setText("Top News Ads package");

        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        paymentAmount = Preference.get(PaypalActivity.this,Preference.key_price_TopNewsAds);

        txt_pay.setText(paymentAmount+"AED");


        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getPayment();

            }
        });

                config = new PayPalConfiguration()
                // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
                // or live (ENVIRONMENT_PRODUCTION)
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(PayPalConfig.PAYPAL_CLIENT_ID);


        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

    }

    @Override
    public void onClick(View v) {


    }


    private void getPayment() {

        double paymentAmount1= Double.parseDouble(paymentAmount);

        double finalpaymentAmount=(paymentAmount1*0.27);

        String finalamt= String.valueOf(finalpaymentAmount);

        //Getting the amount from editText
        if(finalamt.equalsIgnoreCase(""))
        {

            Toast.makeText(this, "Plese enter Price", Toast.LENGTH_SHORT).show();

        }else
        {
            //Creating a paypalpayment
            PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(finalamt)), "USD", "Simplified Coding Fee",
                    PayPalPayment.PAYMENT_INTENT_SALE);
            //Creating Paypal Payment activity intent
            Intent intent = new Intent(this, PaymentActivity.class);
            //putting the paypal configuration to the intent
            intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
            //Puting paypal payment to the intent
            intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
            //Starting the intent activity for result
            //the request code will be used on the method onActivityResult
            startActivityForResult(intent, PAYPAL_REQUEST_CODE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //If the result is from paypal
        if (requestCode == PAYPAL_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                //if confirmation is not null
                if (confirm != null) {
                    try {
                        //Getting the payment details
                        paymentDetails = confirm.toJSONObject().toString(4);

                        Log.i("paymentExample", paymentDetails);

                        //Starting a new activity for the payment details and also putting the payment details with intent
                        try {

                            JSONObject jsonDetails = new JSONObject(paymentDetails);

                            JSONObject jsonDetails1 =jsonDetails.getJSONObject("response");

                            PaymentId = jsonDetails1.getString("id");

                        } catch (JSONException e) {

                            e.printStackTrace();


                        }

                        Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(PaypalActivity.this, ConfirmationActivity.class)
                                .putExtra("PaymentId", PaymentId);
                        startActivity(intent);

                      /*  String UserId=Preference.get(PaypalActivity.this,Preference.KEY_USER_ID);
                        String name=Preference.get(PaypalActivity.this,Preference.key_PlaceUser_name);
                        String email=Preference.get(PaypalActivity.this,Preference.key_PlaceUser_email);
                        String address=Preference.get(PaypalActivity.this,Preference.key_PlaceUser_address);

                        Placeorder(UserId,PaymentId,address,"","",name,
                                email);*/

                    } catch (JSONException e) {

                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {

                Log.i("paymentExample", "The user canceled.");

            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {

                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }

    public void Placeorder(String user_id, String transaction_id, String delivery_address, String latitude,
                           String longitude, String name, String email){

        HashMap<String, String> map = new HashMap<>();

        map.put("user_id",user_id);
        map.put("transaction_id",transaction_id);
        map.put("delivery_address",delivery_address);
        map.put("latitude",latitude);
        map.put("longitude",longitude);
        map.put("name",name);
        map.put("email",email);

        ApiRequest apiRequest = new ApiRequest(PaypalActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_BLOG, Constants.USER_BLOG,map, Request.Method.POST);
    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_BLOG.equalsIgnoreCase(tag_json_obj)){

            /*if (!response.equalsIgnoreCase(null)) {

                PlaceoderModel finalArray = new Gson().fromJson(response,new TypeToken<PlaceoderModel>(){}.getType());

                String message=finalArray.getMessage();
                Boolean message1=finalArray.getStatus();

                if(message1 == true)
                {
                    Intent intent=new Intent(PaypalActivity.this, OrderConfirmedActivity.class)
                            .putExtra("PaymentId", PaymentId);
                    startActivity(intent);

                    Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
                }
               // startActivity(new Intent(this, OrderConfirmedActivity.class));
            }*/
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "Please Check Your Internet Connection..", Toast.LENGTH_SHORT).show();
    }
}
