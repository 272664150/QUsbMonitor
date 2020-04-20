package com.example.qusbmonitor;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qusbmonitor.config.Constants;

public class MainActivity extends AppCompatActivity {

    private TextView mUsbPathTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsbPathTv = findViewById(R.id.tv_path);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 挂载、卸载USB都会弹窗，Activity生命周期变动
        mUsbPathTv.setText("Usb路径： " + Constants.USB_MOUNT_PATH);
    }
}
