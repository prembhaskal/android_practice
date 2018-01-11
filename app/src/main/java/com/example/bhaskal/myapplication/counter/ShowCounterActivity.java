package com.example.bhaskal.myapplication.counter;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

import com.example.bhaskal.myapplication.R;

public class ShowCounterActivity extends Activity implements LoaderManager.LoaderCallbacks<CounterData> {

    private static final String TAG = ShowCounterActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_counter);

        Loader<CounterData> counterDataLoader = getLoaderManager().initLoader(1, null, this);
        Log.i(TAG, "in method onCreate ");
    }

    @Override
    public Loader<CounterData> onCreateLoader(int id, Bundle args) {
        Log.i(TAG, "in method onCreateLoader");
        return new CounterLoader(getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<CounterData> loader, CounterData data) {
        Log.i(TAG, "in method onLoadFinished --> received data " + data.toString());
    }

    @Override
    public void onLoaderReset(Loader<CounterData> loader) {
        Log.i(TAG, "in method onLoaderReset");
    }
}

class CounterLoader extends AsyncTaskLoader<CounterData> {
    private static final String TAG = CounterLoader.class.getSimpleName();
    private final CounterController counterController;

    CounterLoader(Context context) {
        super(context);
        Log.i(TAG, "Starting the CounterController ");
        counterController = CounterController.getInstance();
        counterController.start();

        counterController.addObserver(new CounterObserver() {
            @Override
            public void notifyChanged() {
                Log.i(TAG, "Notified...");
                Log.i(TAG, "current counter value is " +counterController.getCounterValue());
                dataChanged();
            }
        });
    }

    private void dataChanged() {
        if (isStarted()) {
            forceLoad();
        }
    }

    @Override
    public CounterData loadInBackground() {
        Log.i(TAG, "in load background method");
        return new CounterData(counterController.getCounterValue());
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        // TODO -- print whether MAIN thread or Worked thread.
        Log.i(TAG, "in method onStartLoading");

        forceLoad();
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

    @Override
    public String toString() {
        return "CounterData{" +
                "counterValue=" + counterValue +
                '}';
    }
}
