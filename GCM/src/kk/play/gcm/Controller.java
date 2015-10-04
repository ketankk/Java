package kk.play.gcm;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.AlreadyConnectedException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.google.android.gcm.GCMRegistrar;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.PowerManager;
import android.util.Log;

public class Controller extends Application {

	private final int MAX_ATTEMPTS = 5;
	private final int BACKOFF_MILLI_SECONDS = 2000;
	private final Random random = new Random();

	void Register(final Context context, String name, String email,
			final String regId) throws IOException {
		Log.i(Config.TAG, "Registering Device id= " + regId);

		String serverUrl = Config.SERVER_URL;
		Map<String, String> params = new HashMap<String, String>();
		params.put("regId", regId);
		params.put("email", email);
		params.put("name", name);

		for (int i = 0; i < MAX_ATTEMPTS; i++) {
			displayMessageOnScreen(context, "hello");
			post(serverUrl, params);
			GCMRegistrar.setRegisteredOnServer(context, true);
			String message = "context.getString(R.string.server_registered)";
			displayMessageOnScreen(context, message);
			return;
		}

	}

	private static void post(String serverUrl, Map<String, String> params) throws IOException {
		URL url=null;
		try {
			url = new URL(serverUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder bodyBuilder=new StringBuilder();
		Iterator<Entry<String, String>> iterator=params.entrySet().iterator();
		
		
		while(iterator.hasNext()){
			Entry<String, String>param=iterator.next();
			bodyBuilder.append(param.getKey()).append('=').append(param.getValue());
			if(iterator.hasNext())
				bodyBuilder.append('&');
		}
		String body=bodyBuilder.toString();
		Log.v(Config.TAG, "posting "+ body+" to"+ url);
		
		byte[] bytes=body.getBytes();
		HttpURLConnection conn=null;
		try {
			conn=(HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setFixedLengthStreamingMode(bytes.length);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded;charset=UTF-8");
			OutputStream out=conn.getOutputStream();
			out.write(bytes);
			out.close();
			int status =conn.getResponseCode();
			if(status!=200){
				throw new IOException("Satatus= "+status);
			}
	           
		} finally{
			if(conn!=null){
				conn.disconnect();
			}
		}
	}

	void displayMessageOnScreen(Context context, String message) {
		Intent intent = new Intent(Config.DISPLAY_MESSAGE_ACTION);
		intent.putExtra(Config.EXTRA_MESSAGE, message);
		context.sendBroadcast(intent);
	}

	@SuppressWarnings("deprecation")
	void showAlertDialogue(Context context, String title, String message,
			Boolean status) {

		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);

		if (status != null) {
			alertDialog.setIcon((status) ? R.drawable.abc_ab_share_pack_mtrl_alpha
					: R.drawable.abc_ab_share_pack_mtrl_alpha);
		}
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub

			}
		});
		alertDialog.show();

	}

	private PowerManager.WakeLock wakeLock;

	void acquireWakeLock(Context context) {
		if (wakeLock != null)
			wakeLock.release();

		PowerManager pm = (PowerManager) context
				.getSystemService(Context.POWER_SERVICE);

		wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
				| PowerManager.ACQUIRE_CAUSES_WAKEUP
				| PowerManager.ON_AFTER_RELEASE, "WakeLock");
		wakeLock.acquire();

	}

	void releaseWakeLock() {
		if (wakeLock != null)
			wakeLock.release();
		wakeLock = null;
	}

	public boolean isConnectingToInternet() {
		ConnectivityManager connntectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connntectivity != null) {
			NetworkInfo[] info = connntectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED)
						return true;
				}
			}
		}
		return false;
	}

}
