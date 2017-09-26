package com.example.bhaskal.myapplication.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bhaskal.myapplication.R;

/**
 * Created by bhaskal on 11-09-2017.
 */

//
public class ChatBoxOther extends LinearLayout {

    public ChatBoxOther(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflate(context, R.layout.other_chat_box, this);

        setOrientation(VERTICAL);


//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(R.layout.other_chat_box, this, true);


        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ChatBoxOther, 0, 0);

        String playerName = a.getString(R.styleable.ChatBoxOther_playerName);
        String chatContent = a.getString(R.styleable.ChatBoxOther_chatContent);
        String chatTimeStamp = a.getString(R.styleable.ChatBoxOther_timeStamp);
        setBackground(a.getDrawable(R.styleable.ChatBoxOther_backgroundimage));

        TextView playerNameView = (TextView) findViewById(R.id.playerNameInChatBox);
        TextView chatContentBoxView = (TextView) findViewById(R.id.chatContentTextBox);
        TextView timestampView = (TextView) findViewById(R.id.chattimestamp);

        if (playerNameView != null)
            playerNameView.setText(playerName);
        if (chatContentBoxView != null)
            chatContentBoxView.setText(chatContent);
        if (timestampView != null)
            timestampView.setText(chatTimeStamp);
    }

    public ChatBoxOther(Context context) {
        this(context, null);
    }

}
