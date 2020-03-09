package shubham.com.baqat.PaymentScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import shubham.com.baqat.HomeBottomScreen.HomeBottomActivity;
import shubham.com.baqat.R;

public class ConfirmationActivity extends AppCompatActivity {

    Button payment_done_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        payment_done_btn=(Button) findViewById(R.id.payment_done_btn);

        payment_done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ConfirmationActivity.this, HomeBottomActivity.class);
                startActivity(intent);
            }
        });

        //Getting Intent
        //  Intent intent = getIntent();

       /* try {

            JSONObject jsonDetails = new JSONObject(intent.getStringExtra("PaymentDetails"));
            //Displaying payment details
            showDetails(jsonDetails.getJSONObject("response"), intent.getStringExtra("PaymentAmount"));

        } catch (JSONException e) {

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }*/
    }

    private void showDetails(JSONObject jsonDetails, String paymentAmount) throws JSONException {
        //Views
        TextView textViewId = (TextView) findViewById(R.id.paymentId);
        TextView textViewStatus = (TextView) findViewById(R.id.paymentStatus);
        TextView textViewAmount = (TextView) findViewById(R.id.paymentAmount);

        //Showing the details from json object
        textViewId.setText(jsonDetails.getString("id"));
        textViewStatus.setText(jsonDetails.getString("state"));
        textViewAmount.setText(paymentAmount + " USD");
    }

}