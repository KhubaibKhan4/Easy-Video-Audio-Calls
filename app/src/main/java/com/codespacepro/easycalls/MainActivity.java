package com.codespacepro.easycalls;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;

public class MainActivity extends AppCompatActivity {

    TextInputEditText UserId;
    Button Continue;
    final  static String APP_SIGN="f29251ac5e7f532772fb5ddb79678ae21e5c7c32a0a09fb724918ae7cb6dc6e8";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        UserId = (TextInputEditText) findViewById(R.id.edit_UserId);
        Continue = (Button) findViewById(R.id.continuebtn);



        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userid = UserId.getText().toString().trim();
                if (userid.isEmpty()){
                    return;
                }

                //Call Service
                setContinue(userid);
                Intent intent = new Intent(MainActivity.this,CallsActivity.class);
                intent.putExtra("userID",userid);
                startActivity(intent);
            }
        });


    }
    void setContinue(String userid){
        Application application =getApplication(); // Android's application context
        long appID =762565521 ;   // yourAppID
        String appSign =APP_SIGN;  // yourAppSign
        String userName =userid;   // yourUserName

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
        callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true;
        ZegoNotificationConfig notificationConfig = new ZegoNotificationConfig();
        notificationConfig.sound = "zego_uikit_sound_call";
        notificationConfig.channelID = "CallInvitation";
        notificationConfig.channelName = "CallInvitation";
        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userid, userName,callInvitationConfig);
    }

    @Override
    protected void onDestroy() {
        ZegoUIKitPrebuiltCallInvitationService.unInit();
        super.onDestroy();
    }
}