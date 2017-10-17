package com.valkyria.trebusz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FirstSetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_setup);
    }

    public void getBack(View view) {
        finish();
    }
}
