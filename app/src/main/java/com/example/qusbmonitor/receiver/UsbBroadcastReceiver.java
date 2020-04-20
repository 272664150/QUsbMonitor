package com.example.qusbmonitor.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.storage.StorageManager;

import com.example.qusbmonitor.config.Constants;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 监听U盘挂载、卸载广播
 */
public class UsbBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case Intent.ACTION_MEDIA_MOUNTED:
                try {
                    Class storeManagerClazz = Class.forName("android.os.storage.StorageManager");
                    Method getVolumesMethod = storeManagerClazz.getMethod("getVolumes");

                    StorageManager storageManager =
                            (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
                    List<?> volumeInfos = (List<?>) getVolumesMethod.invoke(storageManager);
                    if (volumeInfos != null) {
                        Class volumeInfoClazz = Class.forName("android.os.storage.VolumeInfo");
                        Method getFsUuidMethod = volumeInfoClazz.getMethod("getFsUuid");
                        Field pathField = volumeInfoClazz.getDeclaredField("path");

                        for (Object volumeInfo : volumeInfos) {
                            String uuid = (String) getFsUuidMethod.invoke(volumeInfo);
                            if (uuid != null) {
                                Constants.USB_MOUNT_PATH = (String) pathField.get(volumeInfo);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Intent.ACTION_MEDIA_UNMOUNTED:
                Constants.USB_MOUNT_PATH = "";
                break;
            default:
                break;
        }
    }
}
