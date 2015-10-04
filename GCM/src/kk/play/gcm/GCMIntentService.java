package kk.play.gcm;

import java.io.IOException;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {
	public static final int notifyID = 9001;
	private static final String TAG = "GCMIntentService";
	private Controller aController = null;

	public GCMIntentService() {
		super(Config.GOOGLE_SENDER_ID);
	}

	@Override
	protected void onError(Context arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onMessage(Context context, Intent intent) {

		if (aController == null)
			aController = (Controller) getApplicationContext();
		Log.i("TAG", "Rcvd msg");
		String message = intent.getExtras().getString("price");
		aController.displayMessageOnScreen(context, message);
		generateNotification(context, message);

	}

	/*private static void generateNotification(Context context, String message) {
		int icon = R.drawable.ic_launcher;
		long when = System.currentTimeMillis();
		
		Notification notification=new Notification(icon,message,when);
		
		
		String title=context.getString(R.string.app_name);
		
		Intent notificationIntent=new Intent(context,MainActivity.class);
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
		 NotificationManager notificationManager = (NotificationManager)
	                context.getSystemService(Context.NOTIFICATION_SERVICE);
	        
		PendingIntent intent=PendingIntent.getActivity(context, 0, notificationIntent, 0);
		//notification.setLatestEventInfo(context,title,message,intent);
		notification.flags|=Notification.FLAG_AUTO_CANCEL;
		notification.defaults|=Notification.DEFAULT_SOUND;
		notification.defaults|=Notification.DEFAULT_VIBRATE;
		notificationManager.notify(0,notification);
		
		
		

	}*/

	private static void generateNotification(Context context,String msg) {
        Intent resultIntent = new Intent(context, MainActivity.class);
        resultIntent.putExtra("msg", msg);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0,
                resultIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder mNotifyBuilder;
        NotificationManager mNotificationManager;

        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotifyBuilder = new NotificationCompat.Builder(context)
                .setContentTitle("Alert")
                .setContentText("You've received new message.")
                .setSmallIcon(R.drawable.ic_launcher);
        // Set pending intent
        mNotifyBuilder.setContentIntent(resultPendingIntent);

        // Set Vibrate, Sound and Light            
        int defaults = 0;
        defaults = defaults | Notification.DEFAULT_LIGHTS;
        defaults = defaults | Notification.DEFAULT_VIBRATE;
        defaults = defaults | Notification.DEFAULT_SOUND;

        mNotifyBuilder.setDefaults(defaults);
        // Set the content for Notification 
        mNotifyBuilder.setContentText("New message from Server");
        // Set autocancel
        mNotifyBuilder.setAutoCancel(true);
        // Post a notification
        mNotificationManager.notify(notifyID, mNotifyBuilder.build());
}
	
	
	@Override
	protected void onRegistered(Context context, String registrationId) {

		if (aController == null)
			aController = (Controller) getApplicationContext();
		Log.i(TAG, "Device Registered " + registrationId);
		aController.displayMessageOnScreen(context,
				"You are registered with GCM");

		Log.d(TAG, MainActivity.name);
		try {
			aController.Register(context, MainActivity.name, MainActivity.email,
					registrationId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		// TODO Auto-generated method stub

	}

}
