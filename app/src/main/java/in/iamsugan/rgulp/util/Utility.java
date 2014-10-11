package in.iamsugan.rgulp.util;

import android.app.Activity;
import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by sugan on 11/10/14.
 */
public class Utility {
    // Checks if the network is available
    public boolean isNetworkAvailable() {
        Application.
        ConnectivityManager connectivityManager = (ConnectivityManager) Application.getgetSystemService(Application.getC.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
