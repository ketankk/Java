package kk.play.maplogin;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class UpdateProfilePicTask extends AsyncTask<String, Void, Bitmap>{

	WeakReference<ImageView> profileView;
	public UpdateProfilePicTask(ImageView image	){
		profileView=new WeakReference<ImageView>(image);
	}
	
	@Override
	protected Bitmap doInBackground(String... params) {
Bitmap profilePic=null;
try {
	URL downloadURL=new URL(params[0]);
	HttpURLConnection conn=(HttpURLConnection)downloadURL.openConnection();
	int responseCode=conn.getResponseCode();
	
	if(responseCode!=200){
		throw new Exception("Error in connection");
	}
	InputStream stream=conn.getInputStream();
	profilePic=BitmapFactory.decodeStream(stream);
	

} catch ( Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} 

return profilePic;
	}
	
	@Override
	public void onPostExecute(Bitmap result) {
		if(result!=null && profileView!=null){
			ImageView view=profileView.get();
			if(view!=null)view.setImageBitmap(result);
		}
		
	}
	
	
	

}
