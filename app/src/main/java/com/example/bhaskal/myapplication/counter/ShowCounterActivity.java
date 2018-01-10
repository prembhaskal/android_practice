package com.example.bhaskal.myapplication.counter;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.app.Activity;

import com.example.bhaskal.myapplication.R;

public class ShowCounterActivity extends Activity implements LoaderManager.LoaderCallbacks<CounterData>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_counter);
    }

    @Override
    public Loader<CounterData> onCreateLoader(int id, Bundle args) {
        return new CounterLoader(getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<CounterData> loader, CounterData data) {

    }

    @Override
    public void onLoaderReset(Loader<CounterData> loader) {

    }
}

class CounterLoader extends AsyncTaskLoader<CounterData> {

    public CounterLoader(Context context) {
        super(context);
    }

    @Override
    public CounterData loadInBackground() {
        return null;
    }
}

class CounterData {
    private final int counterValue;

    CounterData(int counterValue) {
        this.counterValue = counterValue;
    }

    public int getCounterValue() {
        return counterValue;
    }
}
