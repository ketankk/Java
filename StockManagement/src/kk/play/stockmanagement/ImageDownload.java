package kk.play.stockmanagement;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class ImageDownload extends AsyncTask<String, Void, Bitmap> {
	ImageView imageView;

	public ImageDownload(ImageView imageView) {
		super();
this.imageView=imageView;	}

	@Override
	protected Bitmap doInBackground(String... urls) {
		String urldisplay = urls[0];
        Bitmap bitmapImage = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            bitmapImage = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bitmapImage;
	}
	
	protected void onPostExecute(Bitmap result){
		imageView.setImageBitmap(result);
	}
	

}
