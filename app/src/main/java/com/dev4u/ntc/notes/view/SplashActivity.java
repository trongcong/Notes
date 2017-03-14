package com.dev4u.ntc.notes.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.dev4u.ntc.notes.R;
import com.dev4u.ntc.notes.view.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    private static final int SPLASH_DELAY = 200;

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initIntentByLogin();
            }
        }, SPLASH_DELAY);
    }

    private void initIntentByLogin() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
