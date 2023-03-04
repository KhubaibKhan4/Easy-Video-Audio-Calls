package com.codespacepro.easycalls;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class CallsActivity extends AppCompatActivity {

    TextView DisplayID;
    TextInputEditText UserID;
    ZegoSendCallInvitationButton VideoCall,AudioCall;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_calls);

        DisplayID = (TextView) findViewById(R.id.heyuserid);
        UserID = (TextInputEditText) findViewById(R.id.UserId);
        VideoCall = (ZegoSendCallInvitationButton) findViewById(R.id.video_view);
        AudioCall = (ZegoSendCallInvitationButton) findViewById(R.id.audio_view);


        String userid = getIntent().getStringExtra("userID");
        DisplayID.setText("Hey  "+userid);

        UserID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String targetUserid = UserID.getText().toString().trim();
                setAudioCall(targetUserid);
                setVideoCall(targetUserid);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

     void setAudioCall(String targetUserid) {
         AudioCall.setIsVideoCall(false);
         AudioCall.setResourceID("zego_uikit_call");
         AudioCall.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserid)));
    }
    void setVideoCall(String targetUserid){
        VideoCall.setIsVideoCall(true);
        VideoCall.setResourceID("zego_uikit_call");
        VideoCall.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserid)));
    }
}