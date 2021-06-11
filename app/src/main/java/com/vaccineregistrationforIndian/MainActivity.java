package com.vaccineregistrationforIndian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.vaccineregistrationforIndian.ads.Nelsoft_AdmobBannerAds;
import com.vaccineregistrationforIndian.ads.Nelsoft_UtilsMAIN;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button slotbtn,regiButton,lang,sharapp;
    private String TAG = MainActivity.class.getSimpleName();
    private AdView mAdView;
    private String selectedlang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slotbtn=(Button)findViewById(R.id.slotbtn);
        regiButton=(Button)findViewById(R.id.regibtn);
        lang=(Button)findViewById(R.id.lang);
        sharapp=(Button)findViewById(R.id.sharapp);



        if(Nelsoft_UtilsMAIN.isOnline(MainActivity.this)){
            LinearLayout linearLayoust = findViewById(R.id.ad_view);
            Nelsoft_AdmobBannerAds.lordAdmobBannerAds(getApplicationContext(), linearLayoust, AdSize.LARGE_BANNER, "GBanner");
        }


        selectedlang="en";

        clicklistner();
    }





    private void clicklistner() {
        slotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, Web_View.class);
                myintent.putExtra("url","https://www.cowin.gov.in/home");
                startActivity(myintent);
            }
        });

        regiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, Web_View.class);
                myintent.putExtra("url","https://selfregistration.cowin.gov.in/");
                startActivity(myintent);
            }
        });

        lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        sharapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Vaccine Registration : https://play.google.com/store/apps/details?id=com.vaccineregistration");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }


    public void showDialog(){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog);

        Button english = (Button) dialog.findViewById(R.id.english);
        Button gujarati = (Button) dialog.findViewById(R.id.gujarati);
        Button hindi = (Button) dialog.findViewById(R.id.hindi);

        english.setBackground(getResources().getDrawable(R.drawable.shadow_button_layer_list));
        gujarati.setBackground(getResources().getDrawable(R.drawable.shadow_button_layer_list));
        hindi.setBackground(getResources().getDrawable(R.drawable.shadow_button_layer_list));

        if(selectedlang.equals("en"))
        {
            english.setBackground(getResources().getDrawable(R.drawable.greyback));
        }else if (selectedlang.equals("gu"))
        {
            gujarati.setBackground(getResources().getDrawable(R.drawable.greyback));
        }else if (selectedlang.equals("hi")){
            hindi.setBackground(getResources().getDrawable(R.drawable.greyback));
        }

        Log.d("language",selectedlang.toString());
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Locale locale1 = new Locale("en");
                Locale.setDefault(locale1);
                Configuration newConfig1 = new Configuration();
                newConfig1.locale = locale1;
                onConfigurationChanged(newConfig1);
                selectedlang="en";
            }
        });
        gujarati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Locale locale = new Locale("gu");
                Locale.setDefault(locale);
                Configuration newConfig = new Configuration();
                newConfig.locale = locale;
                onConfigurationChanged(newConfig);
                selectedlang="gu";
            }
        });
        hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Locale locale2 = new Locale("hi");
                Locale.setDefault(locale2);
                Configuration newConfig2 = new Configuration();
                newConfig2.locale = locale2;
                onConfigurationChanged(newConfig2);
                selectedlang="hi";
            }
        });

        dialog.show();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_gujrati:

//                setLocale(getApplicationContext(),"gu");
                return true;
            case R.id.action_english:



//                setLocale(getApplicationContext(), Locale.getDefault().getLanguage());
                return true;

            case R.id.action_hindi:


//                setLocale(getApplicationContext(),"hi");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        getBaseContext().getResources().updateConfiguration(newConfig, getBaseContext().getResources().getDisplayMetrics());
        setContentView(R.layout.activity_main);

        slotbtn=(Button)findViewById(R.id.slotbtn);
        regiButton=(Button)findViewById(R.id.regibtn);
        lang=(Button)findViewById(R.id.lang);
        sharapp=(Button)findViewById(R.id.sharapp);

        if(Nelsoft_UtilsMAIN.isOnline(MainActivity.this)){
            LinearLayout linearLayoust = findViewById(R.id.ad_view);
            Nelsoft_AdmobBannerAds.lordAdmobBannerAds(getApplicationContext(), linearLayoust, AdSize.LARGE_BANNER, "GBanner");
        }

        clicklistner();

    }


    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}