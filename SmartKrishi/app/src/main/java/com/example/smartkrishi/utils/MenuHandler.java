package com.example.smartkrishi.utils;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.smartkrishi.MainActivity;
import com.example.smartkrishi.R;
import com.example.smartkrishi.fragments.NewsActivity;

public class MenuHandler{
    private final Activity activity;

    public MenuHandler(Activity activity) {
        this.activity = activity;
    }


    public boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.nav_home) {
            navigateTo(MainActivity.class);
            Toast.makeText(activity, "Home clicked", Toast.LENGTH_SHORT).show();

        } else if (itemId == R.id.nav_news) {
            navigateTo(NewsActivity.class);


        } else if (itemId == R.id.nav_pest_detect) {
            Toast.makeText(activity, "Pest Detect clicked", Toast.LENGTH_SHORT).show();
            // Open Camera or Pest Detection Activity if needed

        } else if (itemId == R.id.nav_market) {
            Toast.makeText(activity, "Marketplace clicked", Toast.LENGTH_SHORT).show();

        } else if (itemId == R.id.nav_settings) {
            Toast.makeText(activity, "Settings clicked", Toast.LENGTH_SHORT).show();

        }

        return false;  // Default case if no menu item matches
    }
    private void navigateTo(Class<?> targetActivity) {
        Intent intent = new Intent(activity, targetActivity);
        activity.startActivity(intent);
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
