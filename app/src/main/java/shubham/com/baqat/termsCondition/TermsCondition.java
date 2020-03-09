package shubham.com.baqat.termsCondition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import shubham.com.baqat.R;

public class TermsCondition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_condition);

        WebView mywebview = (WebView) findViewById(R.id.webView);
        mywebview.loadUrl("https://migration.baqat-4u.com/mobile/termsnconditions");


    }
}
