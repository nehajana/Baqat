package shubham.com.baqat.Singupscreen;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import shubham.com.baqat.HomeBottomScreen.HomeBottomActivity;
import shubham.com.baqat.LoginSceen.LoginActivity;
import shubham.com.baqat.MainActivity;
import shubham.com.baqat.Preference;
import shubham.com.baqat.R;
import shubham.com.baqat.Singupscreen.ApiModel.SignUpModel;
import shubham.com.baqat.Singupscreen.ApiModel.SocialLoginModel;
import shubham.com.baqat.Volley.ApiRequest;
import shubham.com.baqat.Volley.Constants;
import shubham.com.baqat.Volley.IApiResponse;

public class SignUpActivity extends AppCompatActivity implements IApiResponse,GoogleApiClient.OnConnectionFailedListener{

    private TextView txtSignIn;
    private EditText edtFirstName;
    private EditText edtLastName;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private RelativeLayout RRSbtBtn;
    private Button SignUp_btn;
    private LinearLayout LLSocial;
    private ImageView imgGoogle;
    private ImageView imgFb;

    String frstName;
    String lastName;
    String email;
    String password;
    String confirmPassword;
    ProgressBar progressbar;
    //Google
    private SignInButton signInButton;
    FirebaseAuth mAuth;
    private final static int RC_SIGN_IN = 1;
    private GoogleSignInApi mGoogleSignInClient;
    private GoogleApiClient googleApiClient;
    FirebaseAuth.AuthStateListener mAuthLitner;
    // TextView txt_edit;
    ImageView img_google;

    //FaceBook
    LoginButton loginButton;
    private CallbackManager callbackManager;
    private static final String TAG = "MainActivity";
    private static final String EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());

        mAuth = FirebaseAuth.getInstance();

        findViews();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        callbackManager = CallbackManager.Factory.create();

        loginButton.setReadPermissions(Arrays.asList(EMAIL));

        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.camerrecordapp.android.mylerningapp", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // progressbar.setVisibility(View.VISIBLE);
        //
        //        //    txt_edit=(TextView) findViewById( R.id.txt_edit );

        imgFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               LoginManager.getInstance().logInWithReadPermissions(SignUpActivity.this, Arrays.asList("public_profile"));

              /*  Intent intent=new Intent(getApplicationContext(),facebooklogin.class);
                startActivity(intent);*/
            }
        });


        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code

                Log.d(TAG, "facebook:onSuccess:" + loginResult);

                Toast.makeText(SignUpActivity.this, "Success", Toast.LENGTH_SHORT).show();

                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(SignUpActivity.this, "cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(SignUpActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        img_google.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, RC_SIGN_IN);
            }
        } );


        SignUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valiDation();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent( data );
            handleSignInResult( result );
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {

            GoogleSignInAccount account = result.getSignInAccount();

            Toast.makeText( this, "Login Successful", Toast.LENGTH_SHORT ).show();

            String UserName=account.getDisplayName().toString();
            String Email=account.getEmail().toString();
            String Social_Id=account.getId().toString();
            Uri Profile=account.getPhotoUrl();

            SocialLoginMethod(Email,UserName,Social_Id, String.valueOf(Profile),"Googgle");

           /* Preference.save(SignUpActivity.this,Preference.KEY_Type,"Google_sign");

            Intent intent=new Intent(SignUpActivity.this,HomeBottomActivity.class);
            startActivity(intent);*/


        } else {

            Toast.makeText( this, "Login Unsuccessful", Toast.LENGTH_SHORT ).show();

        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private void findViews() {

        loginButton = (LoginButton) findViewById(R.id.login_button);

        signInButton = (SignInButton) findViewById( R.id.btn_google );
        img_google = (ImageView) findViewById( R.id.img_google );
        progressbar = (ProgressBar) findViewById( R.id.progressbar );
        imgFb = (ImageView) findViewById( R.id.img_fb );

        txtSignIn = (TextView)findViewById( R.id.txt_sign_in );
        edtFirstName = (EditText)findViewById( R.id.edt_first_name );
        edtLastName = (EditText)findViewById( R.id.edt_Last_name );
        edtEmail = (EditText)findViewById( R.id.edt_email );
        edtPassword = (EditText)findViewById( R.id.edt_password );
        edtConfirmPassword = (EditText)findViewById( R.id.edt_confirm_password );
        RRSbtBtn = (RelativeLayout)findViewById( R.id.RR_sbt_btn );
        SignUp_btn = (Button)findViewById( R.id.edt_sign_up );
        LLSocial = (LinearLayout)findViewById( R.id.LL_social );
        imgGoogle = (ImageView)findViewById( R.id.img_google );
        imgFb = (ImageView)findViewById( R.id.img_fb );
    }

    private void valiDation() {

        frstName=edtFirstName.getText().toString();
        lastName=edtLastName.getText().toString();
        email=edtEmail.getText().toString();
        password=edtPassword.getText().toString();
        confirmPassword=edtConfirmPassword.getText().toString();

        if(frstName.equalsIgnoreCase("")){

            Toast.makeText(SignUpActivity.this, "Please enter first name.", Toast.LENGTH_SHORT).show();

        }else if(lastName.equalsIgnoreCase("")){

            Toast.makeText(SignUpActivity.this, "Please enter Last Name.", Toast.LENGTH_SHORT).show();

        }else if(!isValidEmail(email)){

            Toast.makeText(SignUpActivity.this, "Please enter correct email address.", Toast.LENGTH_SHORT).show();

        }
        else  if (!isValidPassword(password.trim())) {

            Toast.makeText(SignUpActivity.this, "InValid Password", Toast.LENGTH_SHORT).show();

        }else  if(confirmPassword.equalsIgnoreCase("")){

            Toast.makeText(SignUpActivity.this, "Please enter Confirm password.", Toast.LENGTH_SHORT).show();

        }else  if(!confirmPassword.equalsIgnoreCase(password)){

            Toast.makeText(SignUpActivity.this, "Don't match password.", Toast.LENGTH_SHORT).show();

        }
        else {

            signUpMethod(frstName,lastName,email,password,confirmPassword);
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }


    public void signUpMethod(String first_name, String last_name,String email,String password,String cpassword){

        HashMap<String, String> map = new HashMap<>();
        map.put("first_name",first_name);
        map.put("last_name",last_name);
        map.put("email",email);
        map.put("password",password);
        map.put("cpassword",cpassword);

        ApiRequest apiRequest = new ApiRequest(SignUpActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_SINGUP, Constants.USER_SINGUP,map, Request.Method.POST);

    }


    public void SocialLoginMethod(String email,String user_name,String social_id,String profile_picture,String social_login_from){

        HashMap<String, String> map = new HashMap<>();
        map.put("email",email);
        map.put("user_name",user_name);
        map.put("social_id",social_id);
        map.put("profile_picture",profile_picture);
        map.put("social_login_from",social_login_from);

        ApiRequest apiRequest = new ApiRequest(SignUpActivity.this,this);

        apiRequest.postRequest(Constants.BASE_URL + Constants.USER_sociallogin, Constants.USER_sociallogin,map, Request.Method.POST);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_SINGUP.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                SignUpModel finalArray = new Gson().fromJson(response,new TypeToken<SignUpModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (status.equalsIgnoreCase("0")){

                    // progressbar.setVisibility(View.GONE);

                    Toast.makeText(this, ""+finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
                else {

                    Toast.makeText(this, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        } else if (Constants.USER_sociallogin.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                SocialLoginModel finalArray = new Gson().fromJson(response,new TypeToken<SocialLoginModel>(){}.getType());

                String status= String.valueOf(finalArray.getErrorCode());

                if (status.equalsIgnoreCase("0")){

                    String user_id=finalArray.getData().get(0).getUserId();

                    String user_Name=finalArray.getData().get(0).getFirstName();

                    String access_token=finalArray.getData().get(0).getAccessToken();

                    String profileImage=finalArray.getData().get(0).getProfilePicture();

                    Preference.save(SignUpActivity.this,Preference.key_profileImage,profileImage);

                    Preference.save(SignUpActivity.this,Preference.KEY_USER_ID,user_id);

                    Preference.save(SignUpActivity.this,Preference.key_UserName,user_Name);

                    Preference.save(SignUpActivity.this,Preference.KEY_accessToken,access_token);

                    Preference.save(SignUpActivity.this,Preference.KEY_LoginType,"Google");

                    Intent intent=new Intent(SignUpActivity.this,HomeBottomActivity.class);
                    startActivity(intent);


                }
                else {

                    Toast.makeText(this, finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Please Check Network", Toast.LENGTH_SHORT).show();
    }




    //FaceBook

    private void handleFacebookAccessToken(AccessToken token) {

        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(SignUpActivity.this, "Success..", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            // updateUI(null);
                        }
                    }
                });
    }
}
