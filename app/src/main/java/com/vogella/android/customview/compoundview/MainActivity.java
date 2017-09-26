package com.vogella.android.customview.compoundview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.bhaskal.myapplication.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compound_view_vogella);
    }

//    public void onClicked(View view) {
//        String text = view.getId() == R.id.view1 ? "Background" : "Foreground";
//        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
//    }

}
