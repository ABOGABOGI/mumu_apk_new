package id.hike.apps.android_mpos_mumu.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by getwiz on 30/05/17.
 */

public class UnitConversion {

    public UnitConversion(Context context) {
        this.context = context;
    }

    private static String formatYMDHhMmSs = "yyyy-MM-dd HH:mm:ss";


    static Context context;

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    public static int convertDpToPixel2(Context mContext, int yourdpmeasure) {
        Resources r = mContext.getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                yourdpmeasure,
                r.getDisplayMetrics()
        );

        return px;
    }

    private static DecimalFormat getDefaultDecimalFormatter(){
        return new DecimalFormat("###,###,###,###,###,###,###,###");
    }

    private static String changeCommaWithDot(String money){
        return money.replace(",", ".");
    }

    /**
     * Contoh: Rp 123,000,000
     */
    public static String format2Rupiah(long price) {
        DecimalFormat formatter = getDefaultDecimalFormatter();
        return "Rp " + changeCommaWithDot(formatter.format(price));
    }

    /**
     * Contoh: 123,000,000
     */
    public static String format2Rupiah2(long price) {
        DecimalFormat formatter = getDefaultDecimalFormatter();
        return changeCommaWithDot(formatter.format(price));
    }

    /**
     * Contoh: 123,000,000
     */
    public static String format2Rupiah2(String price) {
        Long price2 = Long.valueOf(price);
        DecimalFormat formatter = new DecimalFormat("###,###,###,###,###,###,###,###");
        return formatter.format(price2);
    }

    public static String formatTanggal = "dd/MM/yyyy HH:mm";

    private static SimpleDateFormat simpleTanggalFormat = new SimpleDateFormat(formatTanggal);

    public static String ConvertMilliSecondsToJam(Long milliSeconds) {
        DateTime date = new DateTime(milliSeconds);
        return date.getHourOfDay() + ":" + date.getMinuteOfHour();
    }

    public static String ConvertMilliSecondsToJamPpob(Long milliSeconds) {
        DateTime date = new DateTime(milliSeconds);
        date = date.minusHours(7);
        return date.getHourOfDay() + ":" + date.getMinuteOfHour();
    }

    public static String ConvertMilliSecondsToWaktuLengkap(String milliSeconds) {
        Calendar calendar = Calendar.getInstance();
        String formatJam2 = "EEEE";
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(formatJam2);
        calendar.setTimeInMillis(Long.parseLong(milliSeconds));
        String dayName = simpleDateFormat2.format(calendar.getTime());

        switch (dayName) {
            case "Sunday":
                dayName = "Minggu";
                break;
            case "Monday":
                dayName = "Senin";
                break;
            case "Tuesday":
                dayName = "Selasa";
                break;
            case "Wednesday":
                dayName = "Rabu";
                break;
            case "Thursday":
                dayName = "Kamis";
                break;
            case "Friday":
                dayName = "Jum'at";
                break;
            case "Saturday":
                dayName = "Sabtu";
                break;
        }

        String formatJam = "dd MMMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatJam);
        calendar.setTimeInMillis(Long.parseLong(milliSeconds));
        return dayName + ", " + simpleDateFormat.format(calendar.getTime());
    }

    public static String ConvertMilliSecondsToTanggal(String milliSeconds) {
        Long millisecondsLong = Long.parseLong(milliSeconds);
        DateTime date = new DateTime(millisecondsLong);
        String pattern = "dd/MM/yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
        return formatter.print(date);
    }

    public static String ConvertMilliSecondsToTime(String milliSeconds) {
        String formatTanggalTanpaJam = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatTanggalTanpaJam);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(milliSeconds));
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * yyyy-MM-dd
     *
     * @param milliSeconds
     * @return
     */
    public static String ConvertMilliSecondsToTime2(String milliSeconds) {
        String formatTanggalTanpaJam = "dd-MM-yyyy, HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatTanggalTanpaJam);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(milliSeconds));
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String ConvertMilliSecondsToAktivityTime(Long milliseconds){
        DateTime date = new DateTime(milliseconds);
        DateTime.Property dayOfWeek = date.dayOfWeek();
        String hari = dayOfWeek.getAsText(Locale.getDefault());

        DateTimeFormatter dtf = DateTimeFormat.forPattern("d MMM, yyyy");
        String dateString = dtf.print(date);

        return hari + ", "+dateString;
    }

    public static String ConvertMilliSecondsToDayName(long milliSeconds) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(milliSeconds);
        final int intHari = cal.get(Calendar.DAY_OF_WEEK) - 1;
        String namaHari = "";
        switch (intHari) {
            case 0:
                namaHari = "Minggu";
                break;
            case 1:
                namaHari = "Senin";
                break;
            case 2:
                namaHari = "Selasa";
                break;
            case 3:
                namaHari = "Rabu";
                break;
            case 4:
                namaHari = "Kamis";
                break;
            case 5:
                namaHari = "Jum'at";
                break;
            case 6:
                namaHari = "Sabtu";
                break;
            default:
                namaHari = "-";
                break;
        }

        return namaHari;
    }

    public static String dateToHourMinuteSeconds(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(formatYMDHhMmSs);
        return sdf.format(date);
    }

    public static Date dateFromHourMinuteSeconds(String sDate){
        Date date = null;
        try{
            date = new SimpleDateFormat(formatYMDHhMmSs).parse(sDate);
        }catch (Exception ex){
            Log.e("mposTag", "error konversi : "+ex.getMessage());
        }
        return date;

    }
}