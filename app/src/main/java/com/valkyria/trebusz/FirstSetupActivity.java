package com.valkyria.trebusz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class FirstSetupActivity extends AppCompatActivity {

    Spinner spinWczyt;
    ArrayAdapter<CharSequence> arrayWczyt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_setup);
        wczytywanieSpinnerow();

    }

    private void spin(int spinId, int arrayId){
        spinWczyt = (Spinner) findViewById(spinId);
        arrayWczyt = ArrayAdapter.createFromResource(this,arrayId, android.R.layout.simple_spinner_item);
        arrayWczyt.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinWczyt.setAdapter(arrayWczyt);
    }

    private void wczytywanieSpinnerow() {
        spin(R.id.wydzialspin,R.array.wydzial_arr);
        spin(R.id.kierunekspin,R.array.kierunek_arr);
        spin(R.id.grupaspin,R.array.grupa_arr);
        spin(R.id.jezykspin,R.array.jezyk_arr);
        spin(R.id.poziomspin,R.array.poziom_arr);
        spin(R.id.grupajspin,R.array.grupa_j_arr);
        spin(R.id.jezykspin2,R.array.jezyk_arr);
        spin(R.id.poziomspin2,R.array.poziom_arr);
        spin(R.id.grupajspin2,R.array.grupa_j_arr);
    }

    public void getBack(View view) {
        finish();
    }
}