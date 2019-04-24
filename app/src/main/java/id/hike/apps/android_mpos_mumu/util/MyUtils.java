package id.hike.apps.android_mpos_mumu.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.Settings;
import androidx.core.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.app.BaseApplication;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by root on 05/09/17.
 */

public class MyUtils {
    private static final String[] permissions = {Manifest.permission.READ_PHONE_STATE};
    private static final int RC_PHONESTATE = 13;

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static void scanFileToGallery(Uri uri, Context ctx) {
        ctx.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
    }

    public static int isEvenOrOdd(int num) {
        if ((num & 1) == 0) {
            return Cfg.EVEN_NUM;
        } else {
            return Cfg.ODD_NUM;
        }
    }

    public static String getThisMethodNameForError() {
        /*ini untuk menentukan layer mana yang dilog methodnya.
        kalo method ini dipake diclass lain, maka STACK_TRACE_POSITION = 1.
        kalo STACK_TRACE_POSITION = 0, ya berarti getThisMethodNameForError() yang didapat.*/
        final int STACK_TRACE_POSITION = 3;

        Throwable stack = new Throwable().fillInStackTrace();
        StackTraceElement[] trace = stack.getStackTrace();
        return trace[STACK_TRACE_POSITION].getClassName()
                + "\n=> " + trace[STACK_TRACE_POSITION].getMethodName()
                + "\n=> " + trace[STACK_TRACE_POSITION].getLineNumber()
                + " ERROR";
    }

    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }

    /**
     * #.###.###.###
     */
    public static String formatCurrency(Long number) {
        // Format curency
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();
        formatter.applyPattern("#,###,###,###");
        String formattedString = formatter.format(number);
        return formattedString.replaceAll(",", ".");
    }

    /**
     * convert dp to px.
     * return 0.75 if it's LDPI
     * return 1.0 if it's MDPI
     * return 1.5 if it's HDPI
     * return 2.0 if it's XHDPI
     * return 3.0 if it's XXHDPI
     * return 4.0 if it's XXXHDPI
     */
    public float checkDensity(Context ctx, int dp) {
        switch (ctx.getResources().getDisplayMetrics().densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                return convertDpToPixel(dp);
            case DisplayMetrics.DENSITY_MEDIUM:
                return convertDpToPixel(dp);
            case DisplayMetrics.DENSITY_HIGH:
                return convertDpToPixel(dp);
            case DisplayMetrics.DENSITY_XHIGH:
                return convertDpToPixel(dp);
        }
        return 0;
    }

    /**
     * Validation of Phone Number
     */
    public final static boolean isValidPhoneNumber(CharSequence target) {
        return android.util.Patterns.PHONE.matcher(target).matches();
    }

    //--------------------CONVERT DP TO PX VICEVERSA---------------------------
    public static float convertPixelsToDp(float px) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return Math.round(dp);
    }

    public static float convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    /**
     * SHA-1 Generator
     * @param text
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String SHA1(String text)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-1");
        byte[] sha1hash = new byte[40];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        sha1hash = md.digest();
        return convertToHex(sha1hash);
    }

    public static String getIMEI(Context activity) {
        TelephonyManager mTelephonyManager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            EasyPermissions.requestPermissions((Activity) activity, "Izin pembacaan state phone dibutuhkan",
                    RC_PHONESTATE, permissions);
        }
        String deviceid = mTelephonyManager.getDeviceId();
            Log.d("msg", "DeviceImei " + deviceid);

            return deviceid;
    }

    public static String getDeviceId(Context context){
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static void resetTransaction(Context ctx) {
        ((BaseApplication) ctx.getApplicationContext()).secPrefs.edit().putString(Cfg.prefsKostumerName_STR, null).apply();
        ((BaseApplication) ctx.getApplicationContext()).secPrefs.edit().putLong(Cfg.prefsKostumerId_Long, Cfg.defaultKostumerId).apply();
        ((BaseApplication) ctx.getApplicationContext()).secPrefs.edit().putString(Cfg.prefsTeleponKostumerStr, null).apply();
        ((BaseApplication) ctx.getApplicationContext()).secPrefs.edit().putString(Cfg.prefsNegoDisc_Str, null).apply();
        ((BaseApplication) ctx.getApplicationContext()).secPrefs.edit().putString(Cfg.prefsEmailKostumer_Str, null).apply();
        ((BaseApplication) ctx.getApplicationContext()).secPrefs.edit().putString(Cfg.prefsTunaiStr, null).apply();
    }

    public static String getUserAgent() {
        return System.getProperty("http.agent");
    }

    public static ArrayList getSuggestion(long cost) {
        Long[] arrPecahan = new Long[]{1000L, 2000L, 5000L, 10000L, 20000L, 50000L, 100000L};

        Set<Long> suggestions = new HashSet<>();
        for (int i = 0; i < arrPecahan.length; i++) {
            Long sug = ((cost / arrPecahan[i]) * arrPecahan[i]) + arrPecahan[i];
            if (sug > cost) {

                suggestions.add(sug);
            }

        }
        ArrayList<Long> sorted = new ArrayList<>();
        for (Long sug : suggestions) {
            sorted.add(sug);
        }

        sorted.add(cost);
        Collections.sort(sorted);

        for (Long sug : sorted) {
            System.out.println(sug);
        }

        return sorted;
    }

     public static String getUpTime() {
        Calendar c = Calendar.getInstance();

        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH));
        String week = String.valueOf(c.get(Calendar.WEEK_OF_MONTH));
        String day = String.valueOf(c.get(Calendar.DAY_OF_WEEK));

        String hours = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String minutes = String.valueOf(c.get(Calendar.MINUTE));
        String seconds = String.valueOf(c.get(Calendar.SECOND));

        return year + "/" + month + "/" + week + "/" + day + " " + hours + ":" + minutes + ":" + seconds;
    }

    public static String getTransDate() {
        Calendar c = Calendar.getInstance();
        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH));
        String week = String.valueOf(c.get(Calendar.WEEK_OF_MONTH));
        return year + "-" + month + "-" + week;
    }

    public static Long getImageSize(String path){
        File file = new File(path);
        long length = file.length();
        return length;
    }

    public static String getProvider(String numb){
        String provider = "undefined";
        switch (numb){
            case "0821":// kartu simpati
            case "0822":
            case "0823":/// kartu as
            case "0851":
            case "0852":
            case "0853":
            case "0811":// kartu halo
            case "0812":
            case "0813":
                provider = "TELKOMSEL";
                break;
            case "0814":
            case "0815":// kartu mentari
            case "0816":
            case "0858":
            case "0855":
            case "0856":// kartu im3
            case "0857":
                provider = "INDOSAT";
                break;
            case "0817":// kartu xl axiata
            case "0818":
            case "0819":
            case "0859":
            case "0877":
            case "0878":
                provider = "XL";
                break;
            case "0831": // kartu Axis
            case "0832":
            case "0833":
            case "0838":
                provider = "AXIS";
                break;
            case "0881": // kartu smartfren
            case "0882":
            case "0883":
            case "0884":
            case "0885":
            case "0886":
            case "0887":
            case "0888":
            case "0889":
                provider = "SMARTFREN";
                break;
            case "0895":// Kartu tri
            case "0896":
            case "0897":
            case "0898":
            case "0899":
                provider = "THREE";
                break;
        }

        return provider;

    }

    // Di itu base activity juga ada
    /*public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }*/
}
