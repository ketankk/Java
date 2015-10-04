package kk.play.volleytest;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.TextView;

public class NetworkStringLoader {
	Context context;
	TextView display;
	String tag = "string_req";

	public NetworkStringLoader(Context context, TextView display) {
		super();
		this.context = context;
		this.display = display;
	}

	public void requestString(String url) {
		final ProgressDialog pDialog = new ProgressDialog(context);

		pDialog.setMessage("आप kekai कुंज के अंदर हैं...");
		pDialog.show();
		StringRequest strReq = new StringRequest(Method.GET, url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						display.setText(response);
						pDialog.hide();
					}

				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {

						display.setText(error.toString());
						pDialog.hide();
					}
				});

		AppController.getInstance().addToRequestQueue(strReq, tag);
	}

}
