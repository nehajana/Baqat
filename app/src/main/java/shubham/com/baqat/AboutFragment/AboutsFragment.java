package shubham.com.baqat.AboutFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import shubham.com.baqat.R;

public class AboutsFragment extends Fragment{
    RelativeLayout back_layout;
    View rootView;
    TextView text_title;
    ProgressBar progressbar;

    public static AboutsFragment newInstance(String param1, String param2) {
        AboutsFragment fragment = new AboutsFragment();
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

        findViews();
        progressbar.setVisibility(View.VISIBLE);
        text_title.setVisibility(View.VISIBLE);
        text_title.setText("About Us");
        WebView mywebview = (WebView) rootView.findViewById(R.id.webView);
        mywebview.loadUrl("https://migration.baqat-4u.com/aboutus/aboutmobile");
        back_layout.setVisibility(View.GONE);

        mywebview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                progressbar.setVisibility(View.GONE);
            }
        });


        //setAdapter_Schdule();
        return  rootView;
    }

    private void findViews() {
        text_title=(TextView) rootView.findViewById(R.id.text_title);
        back_layout=(RelativeLayout) rootView.findViewById(R.id.back_layout);
        progressbar = (ProgressBar) rootView.findViewById(R.id.progressbar);
    }


}
