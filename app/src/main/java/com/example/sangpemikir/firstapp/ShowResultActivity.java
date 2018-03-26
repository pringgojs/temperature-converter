package com.example.sangpemikir.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowResultActivity extends AppCompatActivity {
    TextView txtResult;
    TextView txtConvertTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        txtResult = (TextView) findViewById(R.id.txtResult);
        txtConvertTo = (TextView) findViewById(R.id.txtConvertTo);
        Bundle bundle = getIntent().getExtras();
        txtResult.setText(bundle.getString("result"));
        txtConvertTo.setText(bundle.getString("convertToSimbol"));

        setTitle("Result"); // set new title
        getSupportActionBar().setElevation(0); // remove shadow in actionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // set button back in actionBar
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
