package shubham.com.baqat;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {
    public static final String APP_PREF = "KapsiePreferences";

    public static SharedPreferences sp;
    public static String KEY_USER_ID = "id";
    public static String KEY_PARENT_ID = "parent_category_id";
    public static String GET_VIMEO = "/config";
    public static String KEY_Category_ID = "category_id";
    public static String KEY_USER_Video_ID = "Video_id";
    public static String KEY_Type= "name";
    public static String KEY_OrderiD= "name";
    public static String KEY_amount = "amount";
    public static String KEY_Address_api= "name";
    public static String KEY_isKeepMe = "isKeepMe";
    public static String KEY_Email = "email";
    public static String KEY_PROFILE_PIC = "pic";
    public static String KEY_accessToken = "login";
    public static String KEY_LoginType = "add";
    public static String key_filter_location = "location";
    public static String key_minimumPriceValue = ",minimumPriceValue";
    public static String key_maximumPriceValue = "maximumPriceValue";
    public static String key_price_TopNewsAds = " price_TopNewsAds";
    public static String key_valid_ads = "check";
    public static String key_PlaceUser_name = "name_place";
    public static String key_PlaceUser_email = "email_place";
    public static String key_PlaceUser_address = "address_place";

    public static String key_UserName = "key_Title";
    public static String key_profileImage = "description";
    public static String key_price = "price";
    public static String key_Currency = "Currency1";
    public static String key_isFromMyvideolist = "isFromMyvideolist";
    public static String key_Category = "Category";
    public static String Key_shipping_charge = "shipping";
    public static String key_stock = "stock";
    public static String ADD_VIEW_COUNT = "addViewCount";

    //------------- Payment gateway-----------------------
    public static String TYPE = "type";
    //public static String PAY_FOR_CHANNEL = "http://ixorainfotech.in/apicollection/featuring/Webservice/letsPayForChannel";
    public static String PAY_FOR_CHANNEL = "http://ixorainfotech.in/apicollection/featurringfood/Webservice/letsPayForproducts";




    //-----------------------------------




    public static String get(Context context , String key) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        String userId = sp.getString(key, "0");
        return userId;
    }
    public static void save(Context context, String key, String value) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static void saveInt(Context context, String key, int value) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public static int getInt(Context context , String key) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        int userId = sp.getInt(key,0);
        return userId;
    }


    public static boolean saveBool(Context context, String key, Boolean value) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.commit();
        return false;
    }

    public static Boolean getBool(Context context , String key) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        return sp.getBoolean(key,false);
    }

    public static void clearPreference(Context context) {
        sp = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.apply();
    }
}
