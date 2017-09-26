package com.example.bhaskal.myapplication.customview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bhaskal.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends Activity {

    private static final String TAG = "RecyclerActivity";

    private Button addChatItemButton;
    private ChatAdapter chatAdapter;
    private List<String> chatItems;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        chatItems = new ArrayList<>();
        chatAdapter = new ChatAdapter(this, R.layout.chatrow, chatItems);
        addChatItemButton = (Button) findViewById(R.id.addItemButton);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(chatAdapter);

        String firstItem = "counter: 0";
        chatItems.add(firstItem);
//        chatAdapter.add(firstItem);
        chatAdapter.notifyDataSetChanged();

        addChatItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++counter;
                String newItem = "counter: " + counter;
                Log.i(TAG, "adding new counter -- " + newItem);
                chatItems.add(newItem);
//                chatAdapter.add(newItem);
                chatAdapter.notifyDataSetChanged();
            }
        });
    }

    private class ChatAdapter extends ArrayAdapter<String> {
        private List<String> chatItems;

        public ChatAdapter(Context context, int resource, List<String> chatItems) {
            super(context, resource, chatItems);
            this.chatItems = chatItems;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                v = getLayoutInflater().inflate(R.layout.chatrow, null);
            }

            TextView chatRowTxt = (TextView) v.findViewById(R.id.chatRowText);
            if (chatRowTxt != null) {
                Log.i(TAG, "adding new row in list on position: " + position);
                chatRowTxt.setText("chat " + position);
            }

//            TextView playerNameView = (TextView) v.findViewById(R.id.playerNameInChatBox);
//            if (playerNameView != null) {
//                playerNameView.setText("playername" + position);
//            }
//            TextView chatContentBoxView = (TextView) v.findViewById(R.id.chatContentTextBox);
//            if (chatContentBoxView != null) {
//                chatContentBoxView.setText("chatcontent " + position);
//            }
//            TextView timestampView = (TextView) v.findViewById(R.id.chattimestamp);
//            if (timestampView != null) {
//                timestampView.setText("00:" + position);
//            }

            return v;
        }
    }
}
