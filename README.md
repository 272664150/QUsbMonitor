# QUsbMonitor

1、系统级权限
##
 android:sharedUserId="android.uid.system"

 <uses-permission android:name="android.permission.WRITE_MEDIA_STORAGE" />
 <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />

2、关键代码
##
 UsbBroadcastReceiver.java