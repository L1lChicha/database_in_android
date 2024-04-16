package com.example.lab2;

import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;




public class SecondActivity extends Activity {
    String brand,model, androidVersion, site;

    private PhoneListAdapter mAdapter;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Button cancelButton = findViewById(R.id.cancel_button);
        Button saveButton = findViewById(R.id.save_button);
        Button siteButton = findViewById(R.id.site_button);

        EditText manufacturerText = findViewById(R.id.manufacturer_EditText);
        EditText modelText = findViewById(R.id.model_editText);
        EditText androidVersionText = findViewById(R.id.androidVersion_editText);
        EditText siteText = findViewById(R.id.site_editText);
        Validation(manufacturerText, modelText, androidVersionText, siteText);

        Intent intentPos = getIntent();
        if (intentPos.hasExtra("position")) {

            Intent intent= new Intent();
            int position = intent.getIntExtra("position", -1);
            intent = getIntent();
            long phoneId = intent.getLongExtra("phone_id", -1);
            String phoneBrand = intent.getStringExtra("phone_brand");
            String phoneModel = intent.getStringExtra("phone_model");
            String phoneAndroidVersion = intent.getStringExtra("phone_android_version");
            String phoneWebsite = intent.getStringExtra("phone_website");

            manufacturerText.setText(phoneBrand);
            modelText.setText(phoneModel);
            androidVersionText.setText(phoneAndroidVersion);
            siteText.setText(phoneWebsite);

            saveButton.setText("Update");


            Validation(manufacturerText, modelText, androidVersionText, siteText);
            saveButton.setOnClickListener(v -> {

                brand = String.valueOf(manufacturerText.getText());
                model = String.valueOf(modelText.getText());
                androidVersion = String.valueOf(androidVersionText.getText());
                site = String.valueOf(siteText.getText());


                Phone phone = new Phone(phoneId, brand, model, androidVersion, site);
                PhoneViewModel viewModel = new PhoneViewModel(getApplication());
                viewModel.update(phone);
                finish();

            });

        } else {
            Validation(manufacturerText, modelText, androidVersionText, siteText);
            saveButton.setOnClickListener(v -> {

                brand = String.valueOf(manufacturerText.getText());
                model = String.valueOf(modelText.getText());
                androidVersion = String.valueOf(androidVersionText.getText());
                site = String.valueOf(siteText.getText());

                Intent returnIntent = new Intent();

                returnIntent.putExtra("model", model);
                returnIntent.putExtra("brand", brand);
                returnIntent.putExtra("androidVersion", androidVersion);
                returnIntent.putExtra("site", site);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

            });
        }



        cancelButton.setOnClickListener(v -> finish());

        siteButton.setOnClickListener(v -> {
            webView = findViewById(R.id.webView);
            site = String.valueOf(siteText.getText());
            site.startsWith("http://");
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.loadUrl(site);

        });
    }

    public void Validation(EditText brand, EditText model, EditText version, EditText site){
        Button saveButton = findViewById(R.id.save_button);


        if(brand.getText().toString().isEmpty() || model.getText().toString().isEmpty()
                || version.getText().toString().isEmpty() || site.getText().toString().isEmpty()){
            saveButton.setVisibility(View.INVISIBLE);
        }else{
            saveButton.setVisibility(View.VISIBLE);
        }

        brand.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    brand.setError("Manufacturer can't be empty");
                    saveButton.setVisibility(View.INVISIBLE);

                } else {
                    brand.setError(null);
                    if( model.getText().toString().isEmpty()
                            || version.getText().toString().isEmpty() || site.getText().toString().isEmpty()){
                        saveButton.setVisibility(View.INVISIBLE);
                    }else{
                        saveButton.setVisibility(View.VISIBLE);
                    }

                }
            }
        });

        model.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    model.setError("Model can't be empty");
                    saveButton.setVisibility(View.INVISIBLE);

                } else {
                    model.setError(null);
                    if(brand.getText().toString().isEmpty()
                            || version.getText().toString().isEmpty() || site.getText().toString().isEmpty()){
                        saveButton.setVisibility(View.INVISIBLE);
                    }else{
                        saveButton.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        version.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    version.setError("Version can't be empty");
                    saveButton.setVisibility(View.INVISIBLE);

                } else {
                    version.setError(null);
                    if(brand.getText().toString().isEmpty() || model.getText().toString().isEmpty()
                             || site.getText().toString().isEmpty()){
                        saveButton.setVisibility(View.INVISIBLE);
                    }else{
                        saveButton.setVisibility(View.VISIBLE);
                    }

                }
            }
        });

        site.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    site.setError("Site can't be empty");
                    saveButton.setVisibility(View.INVISIBLE);


                } else {
                    site.setError(null);
                    if(brand.getText().toString().isEmpty() || model.getText().toString().isEmpty()
                            || version.getText().toString().isEmpty() ){
                        saveButton.setVisibility(View.INVISIBLE);
                    }else{
                        saveButton.setVisibility(View.VISIBLE);
                    }

                }
            }
        });


    }


}
