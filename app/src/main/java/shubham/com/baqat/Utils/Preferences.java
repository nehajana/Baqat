package shubham.com.baqat.Utils;

import android.content.Context;
import android.content.SharedPreferences;


public class Preferences {

    public static final String APP_PREF = "KapsiePreferences";
    public static SharedPreferences sp;
    public static String KEY_USER_ID = "userId";
    public static String KEY_USER_NAME = "userName";
    public static String KEY_CUSTOMER_ID = "customerId";
    public static String KEY_ORDER_ID = "orderId";
    public static String KEY_ORDER_Date = "orderDate";
    public static String KEY_ORDER_Time = "orderTime";
    public static String KEY_email = "shopName";
    public static String KEY_SHOP_Id = "ShopId";
    public static String KEY_USER_POINT = "userpoint";
    public static String KEY_USER_SEVICES = "services";
    public static String KEY_USER_BOOKING = "bookingDate";
    public static String KEY_BOOKING_TIME = "bookingTime";
    public static String KEY_USER_MOBILE = "mobile";
    public static String KEY_FIRST_NAME = "firstname";
    public static String KEY_LAST_NAME = "lastname";
    public static String KEY_MOBILE = "mobile";
    public static String KEY_PROFILE_PIC = "profilePic";
    public static String KEY_ISVERIFIED = "isVerified";
    public static String KEY_TOKEN = "keyytoken";
    public static String KEY_CURRENT_ADDRESS = "keycaddress";
    public static String KEY_LATITUDE = "keylatitude";
    public static String KEY_LONGITUDE = "keylongitude";
    public static String KEY_ROLE = "keyRole";
    public static String KEY_NAVIGATION_SELLER = "keyNavigationSeller";
    public static String KEY_NAVIGATION_MANU = "keyNavigationManu";
    public static String KEY_SELLER_ID_HOME = "keySellerIdHome";

    public static String SELLER_LAT = "sellerLat";
    public static String FAVORITE_TYPE = "sellerLat";
    public static String SELLER_LONG = "sellerLong";
    public static String KEY_BUSINESS_NAME = "keybusinessname";

    public static String KEY_SKIP_BUYER = "keyskipbuer";
    public static String KEY_SKIP_SELLER = "keyskipseller";
    public static String KEY_SKIP_MANU = "keyskipmanu";
    public static String KEY_BUYER_CART_COUNT = "keybuyercartcount";
    public static String KEY_PREFRENCE_CART_COUNT = "keybuyerPrefrencecartcount";
    public static String KEY_CategoryId = "keysellercartcount";
    public static String KEY_SELLER_ISHYBRID = "keyishybrid";
    public static String KEY_BECOME_SELLER_ID = "keybecomesellerid";
    public static String CART_CLICK_POS = "cartkeypostion";
    public static String ISLOWDISTANCE = "isLowDistance";
    public static String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";


    public static String KEY_PREFERENCE_SEARCH = "keysearhc";
    public static String KEY_Reward = "keyreward";


    public static void save(Context context, String key, String value) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static String get(Context context , String key) {
        sp = context.getSharedPreferences(APP_PREF, 0);
        String userId = sp.getString(key, "0");
        return userId;
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
