package com.ahmed.customapp.MainApp.Helpers;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.ahmed.customapp.R;
import com.google.android.material.snackbar.Snackbar;

import java.net.URLEncoder;

public class OpenSocialMediaHelper {

    //TAG
    private static final String TAG = "OpenSocialMediaHelper";


    private Context context;
    private Intent intent;

    //TODO: Try to make INSTAGRAM_URL static member
    public String INSTAGRAM_URL = "https://instagram.com/ahmeedmohameedd";

    //Urls
    public final static String LINKEDIN_URL = "https://www.linkedin.com/in/ahmed-mohamed-177923165";
    public final static String GITHUB_URL = "https://github.com/AhmedAbdEllatiif";
    public static final String FB_URL_Browser = " https://www.facebook.com/profile.php?id=100001249766856";
    private static final String FB_URL_APP = "fb://profile/100001249766856";
    private final static String WHATSAPP_URL = "https://api.whatsapp.com/send?phone=" + "+0201124466700" + "&text=";


    //Packages names
    public static final String INSTAGRAM_PACKAGE_NAME = "com.instagram.android";
    public static final String LINKEDIN_PACKAGE_NAME = "com.linkedin.android";
    public static final String FB_PACKAGE_NAME = "com.facebook.katana";


    public OpenSocialMediaHelper(Context context) {
        this.context = context;
        intent = new Intent(Intent.ACTION_VIEW);
    }



    /**
     * To open the selected app
     * */
    public void openSelectedApp(String AppPackageName,String url){
        if (isAppInstalled(AppPackageName)){
            switch (AppPackageName){
                case INSTAGRAM_PACKAGE_NAME: openInstagram(); break;
                case LINKEDIN_PACKAGE_NAME: openLinkedIn(); break;
                case FB_PACKAGE_NAME: open_FB(); break;
            }
        }else {
            openWithBrowser(url);
        }
    }

    /**
     * To open url with browser if the app is not installed
     * */
    private void openWithBrowser(String url) {
        try {
            if (isValidUrl(url)) {
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
            }
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, R.string.txt_no_browser_to_open, Toast.LENGTH_LONG).show();
        }
    }




    /**
     * Open Instagram APp
     */
    //http://stackoverflow.com/questions/21505941/intent-to-open-instagram-user-profile-on-android
    private void openInstagram() {
            if (INSTAGRAM_URL.endsWith("/")) {
                INSTAGRAM_URL = INSTAGRAM_URL.substring(0, INSTAGRAM_URL.length() - 1);
            }
            final String username = INSTAGRAM_URL.substring(INSTAGRAM_URL.lastIndexOf("/") + 1);
            intent.setData(Uri.parse("http://instagram.com/_u/" + username));
            intent.setPackage(INSTAGRAM_PACKAGE_NAME);
            context.startActivity(intent);
    }


    /**
     * To open linkedin App
     */
    private void openLinkedIn() {
            intent.setData(Uri.parse(LINKEDIN_URL));
            intent.setPackage("com.linkedin.android");
            context.startActivity(intent);
    }

    /**
     * To open FB_App
     * */
    private void open_FB(){
        intent.setData(Uri.parse(FB_URL_APP));
        intent.setPackage(FB_PACKAGE_NAME);
        context.startActivity(intent);
    }


    /**
     * Open openWhatsApp
     */
    public void openWhatsApp(View view) {
        try {

            PackageManager packageManager = context.getPackageManager();
            Intent i = new Intent(Intent.ACTION_VIEW);
            String url = WHATSAPP_URL + URLEncoder.encode("", "UTF-8");
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null) {
                context.startActivity(i);
            }// >>  PackageNotFound
            else {
                Log.e(TAG, "openWhatsApp: PackageNotFound ");
                makeSnackBar(view, context.getString(R.string.txt_whats_app_not_installed));
            }
        } catch (Exception e) {
            Log.e(TAG, "openWhatsApp: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * To check the url
     * Return true if url is valid
     * show toast and Return false if url not valid
     */
    private boolean isValidUrl(String url) {
        if (URLUtil.isValidUrl(url)) {
            return true;
        }
        Toast.makeText(context, R.string.some_thing_wrong, Toast.LENGTH_LONG).show();
        return false;
    }

    /**
     * To check if package name exists
     */
    private boolean isAppInstalled(String packageName) {
        if (packageName != null){
            PackageManager pm = context.getPackageManager();
            try {
                pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
                return true;
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "isValidPackage: " + packageName + " package not found");
            }
        }

        return false;
    }







    private void makeSnackBar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(view.getResources().getColor(R.color.colorPrimary, null));
        View snackBarView = snackbar.getView();
        TextView tv = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setTextColor(ContextCompat.getColor(view.getContext(), R.color.white));
        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        snackbar.show();
    }


}
