package kk.play.volleytest;

import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.TextView;

public class NetworkJSONLoader {
	Context context;
	TextView display;

	public NetworkJSONLoader(Context context, TextView display) {
		super();
		this.context = context;
		this.display = display;
	}

	public void requestJSON(String url) {
		String tag = "json";
		final ProgressDialog pDialog = new ProgressDialog(context);
		pDialog.setMessage("loading....");
		pDialog.show();

		JsonObjectRequest jsonObjectReq = new JsonObjectRequest(Method.GET,
				url, null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						display.setText(response.toString());
						pDialog.hide();
					}

				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						display.setText(error.toString());
						pDialog.hide();
					}
				});
		AppController.getInstance().addToRequestQueue(jsonObjectReq,tag);
	}

}
