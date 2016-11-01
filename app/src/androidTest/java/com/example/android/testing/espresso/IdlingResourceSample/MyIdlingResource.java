package com.example.android.testing.espresso.IdlingResourceSample;

import android.support.test.espresso.IdlingResource;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ezhong on 11/1/16.
 */

public class MyIdlingResource implements IdlingResource {
    private MainActivity mainActivity;
    private ResourceCallback resourceCallback;

    public MyIdlingResource(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setActivity(MainActivity loginActivity) {
        this.mainActivity = loginActivity;
    }

    @Override
    public String getName() {
        return "MainIdlingResource";
    }

    @Override
    public boolean isIdleNow() {
        boolean idle = isIdle();

        if (idle && resourceCallback != null) {
            resourceCallback.onTransitionToIdle();
        }
        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }


    private boolean isIdle() {
        if (mainActivity != null) {
            // Ugly way to check if activity is displaying progress. Would be better to add a public boolean on activity.
            TextView textView = (TextView) mainActivity.findViewById(R.id.textToBeChanged);
            String text = textView.getText().toString();
            return !(text.startsWith("Espresso") || text.startsWith("Waiting"));
         }
        return true;
    }
}
