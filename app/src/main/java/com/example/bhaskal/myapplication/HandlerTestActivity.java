package com.example.bhaskal.myapplication;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import static com.example.bhaskal.myapplication.Constants.SIMPLE_BG_TASK;

public class HandlerTestActivity extends AppCompatActivity {

    private TextView bgDataView;
    private Button startBGTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);

        bgDataView = (TextView) findViewById(R.id.dataFromBackend);
        startBGTaskButton = (Button) findViewById(R.id.startBgTask);

        

    }

    public void onStartTaskClick(View view) {
        if (view instanceof Button && view.getId() == R.id.startBgTask) {
            BGTaskManager.INSTANCE.scheduleBGTask(bgDataView);
        }
    }

}

class BGRunnableTask implements Runnable {

    private static final String TAG = BGRunnableTask.class.getSimpleName();

    private final SimpleBGTask simpleBGTask;

    BGRunnableTask(SimpleBGTask simpleBGTask) {
        this.simpleBGTask = simpleBGTask;
    }

    @Override
    public void run() {
        Log.i(TAG, "Started the Background runnable task ");

        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int val = new Random().nextInt(1000);
            simpleBGTask.setBgData("iteration" + i + ": -->" + val + "");

            BGTaskManager.INSTANCE.updateUIWithTask(simpleBGTask);
        }

    }
}

class SimpleBGTask {
    private final TextView bgDataView;

    private String bgData;

    public SimpleBGTask(TextView bgDataView) {
        this.bgDataView = bgDataView;
    }

    public TextView getBgDataView() {
        return bgDataView;
    }

    public String getBgData() {
        return bgData;
    }

    public void setBgData(String bgData) {
        this.bgData = bgData;
    }
}

enum BGTaskManager {

    INSTANCE;

    private final Handler mHandler;
    private static final String TAG = BGTaskManager.class.getSimpleName();

    BGTaskManager() {
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.i(TAG, "Handle Message invoked");

                switch (msg.what) {
                    case SIMPLE_BG_TASK:
                        SimpleBGTask simpleBGTask = (SimpleBGTask) msg.obj;
                        TextView bgDataView = simpleBGTask.getBgDataView();
                        String bgData = simpleBGTask.getBgData();
                        bgDataView.setText(bgData);
                        break;
                    default:
                        super.handleMessage(msg);
                }
            }
        };
    }

    public void updateUIWithTask(SimpleBGTask simpleBGTask) {
        Log.i(TAG, "Sent simple bg task message to handler message target");
        Message message = mHandler.obtainMessage(SIMPLE_BG_TASK, simpleBGTask);
        message.sendToTarget();
    }

    public void scheduleBGTask(TextView bgTaskView) {
        Thread thread = new Thread(new BGRunnableTask(new SimpleBGTask(bgTaskView)));
        thread.start();
    }
}

interface Constants {
    int SIMPLE_BG_TASK = 1;
}