package shubham.com.baqat.HomeBottomScreen;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import shubham.com.baqat.AboutFragment.AboutsFragment;
import shubham.com.baqat.CreateAddScreen.CreateAddActivity;
import shubham.com.baqat.HomeFragment.HomeFragment;
import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.MoreFragment.MoreFragment;
import shubham.com.baqat.Preference;
import shubham.com.baqat.ProfileFragment.ProfileFragment;
import shubham.com.baqat.R;

public class HomeBottomActivity extends AppCompatActivity {

    Fragment fragment;

    RelativeLayout RR_profile,RR_more,RR_home,RR_offer;

    ImageView img_home,img_about,img_Profile,img_more,img_home_popup;
    TextView txt_Home,txt_about,txt_postadd,txt_profile,txt_more;

    boolean isHome =true;
    boolean isAbout =true;
    boolean isProfile =true;
    boolean isMore =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_bottom);

        isHome =true;
        isAbout =true;
        isProfile =true;
        isMore =true;

        findview();

        RR_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isProfile)
                {

                    String userID = Preference.get(HomeBottomActivity.this,Preference.KEY_USER_ID);

                    if(userID == null || userID.equalsIgnoreCase("0")) {

                        Intent intent=new Intent(HomeBottomActivity.this, LoginActivity.class);
                        startActivity(intent);

                    }else
                    {
                        img_home.setImageResource(R.drawable.home);
                        img_about.setImageResource(R.drawable.about_us);
                        img_Profile.setImageResource(R.drawable.img_profile_blue);
                        img_more.setImageResource(R.drawable.more);

                        txt_Home.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        txt_about.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        txt_postadd.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        txt_profile.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                        txt_more.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));

                        fragment = new ProfileFragment();
                        loadFragment(fragment);
                    }

                    isHome = true;
                    isAbout =true;
                    isProfile =false;
                    isMore =true;

                }

            }
        });

        RR_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isMore) {

                    img_home.setImageResource(R.drawable.home);
                    img_about.setImageResource(R.drawable.about_us);
                    img_Profile.setImageResource(R.drawable.profile_icon);
                    img_more.setImageResource(R.drawable.img_more_blue);

                    txt_Home.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    txt_about.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    txt_postadd.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    txt_profile.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    txt_more.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));

                    fragment = new MoreFragment();
                    loadFragment(fragment);

                    isHome = true;
                    isAbout =true;
                    isProfile =true;
                    isMore =false;

                }

            }
        });

        img_home_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isHome = true;
                isAbout =true;
                isProfile =true;
                isMore =true;

                String userID = Preference.get(HomeBottomActivity.this,Preference.KEY_USER_ID);

                if(userID == null || userID.equalsIgnoreCase("0")) {

                    Intent intent=new Intent(HomeBottomActivity.this, LoginActivity.class);
                    startActivity(intent);

                }else
                {
                /*    img_home.setImageResource(R.drawable.home);
                    img_about.setImageResource(R.drawable.about_us);
                    img_Profile.setImageResource(R.drawable.profile_icon);
                    img_more.setImageResource(R.drawable.more);

                    txt_Home.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    txt_about.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    txt_postadd.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                    txt_profile.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    txt_more.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));*/

                    Intent intent=new Intent(HomeBottomActivity.this,CreateAddActivity.class);
                    startActivity(intent);
                }
            }
        });

        RR_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isHome)
                {

                    img_home.setImageResource(R.drawable.home_blue);
                    img_about.setImageResource(R.drawable.about_us);
                    img_Profile.setImageResource(R.drawable.profile_icon);
                    img_more.setImageResource(R.drawable.more);

                    txt_Home.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                    txt_about.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    txt_postadd.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    txt_profile.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    txt_more.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));

                    fragment = new HomeFragment();
                    loadFragment(fragment);

                    isHome = false;
                    isAbout =true;
                    isProfile =true;
                    isMore =true;

                }

            }
        });

        RR_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isAbout)
                {

                    img_home.setImageResource(R.drawable.home);
                    img_about.setImageResource(R.drawable.img_about_blue);
                    img_Profile.setImageResource(R.drawable.profile_icon);
                    img_more.setImageResource(R.drawable.more);

                    txt_Home.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    txt_about.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                    txt_postadd.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    txt_profile.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    txt_more.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));

                    fragment = new AboutsFragment();
                    loadFragment(fragment);

                    isHome = true;
                    isAbout =false;
                    isProfile =true;
                    isMore =true;

                }

            }
        });








        fragment = new HomeFragment();
        loadFragment(fragment);
    }

    private void findview() {

        RR_profile=(RelativeLayout) findViewById(R.id.RR_profile);
        RR_more=(RelativeLayout) findViewById(R.id.RR_more);
        RR_home=(RelativeLayout) findViewById(R.id.RR_home);
        RR_offer=(RelativeLayout) findViewById(R.id.RR_offer);

        txt_Home=(TextView) findViewById(R.id.txt_Home);
        txt_about=(TextView) findViewById(R.id.txt_about);
        txt_postadd=(TextView) findViewById(R.id.txt_postadd);
        txt_profile=(TextView) findViewById(R.id.txt_profile);
        txt_more=(TextView) findViewById(R.id.txt_more);

        img_home_popup=(ImageView)findViewById(R.id.img_home_popup);
        img_home=(ImageView)findViewById(R.id.img_home);
        img_about=(ImageView)findViewById(R.id.img_about);
        img_Profile=(ImageView)findViewById(R.id.img_Profile);
        img_more=(ImageView)findViewById(R.id.img_more);

    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack("home");
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
