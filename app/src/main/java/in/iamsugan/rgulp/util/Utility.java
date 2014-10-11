package in.iamsugan.rgulp.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by sugan on 11/10/14.
 */
public class Utility {
    static Context context;
    private static Utility instance = new Utility();

    public static Utility getInstance(Context ctx) {
        context = ctx.getApplicationContext();
        return instance;
    }

    // Checks if the network is available
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}