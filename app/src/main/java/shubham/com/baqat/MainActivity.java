package shubham.com.baqat;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.prefs.Preferences;

import shubham.com.baqat.HomeBottomScreen.HomeBottomActivity;
import shubham.com.baqat.HomeFragment.HomeFragment;
import shubham.com.baqat.LoginSceen.LoginActivity;

public class MainActivity extends AppCompatActivity {

   // private static int SPLASH_TIME_OUT = 3000;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.White));
        }

        userID =Preference.get(this,Preference.KEY_USER_ID);

        //userID = Preference.get(this,Preference.KEY_USER_ID);
        new Handler().postDelayed( new Runnable() {
            public void run() {

                if(userID == null || userID.equalsIgnoreCase("0"))
                {
                    Intent intent = new Intent(MainActivity.this, HomeBottomActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {

                    Intent intent = new Intent(MainActivity.this, HomeBottomActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000 );

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);*/
    }
}
