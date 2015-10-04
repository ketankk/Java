package kk.play.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class GcmMessageHandler extends IntentService{

	String mes;
	private Handler handler;
	public GcmMessageHandler() {
		super("GCMhandlerMsg");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extra=intent.getExtras();
		//GoogleCloudMessaging gcm=	GoogleCloudMessaging.getInstance(this);
	}

}
