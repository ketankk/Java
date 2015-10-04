package kk.play.maplogin;


import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.Plus.PlusOptions;
import com.google.android.gms.plus.model.people.Person;

public class MainActivity extends Activity implements ConnectionCallbacks,
		OnClickListener, OnConnectionFailedListener {
	private static final int ERROR_DIALOG_REQUEST_CODE = 11;
	private static final int SIGN_IN_REQUEST_CODE = 10;
	private GoogleApiClient mGoogleApiClient;
	private boolean mIntentInProgress;
	private ConnectionResult mConnectionResult;
	private boolean mSignInClicked;

	private LinearLayout userOptionsLayout;
	// layout for showing signed in user info
	private LinearLayout userInfoLayout;

	private ImageView userProfilePic;
	private TextView userName;
	private TextView userEmail;
	private TextView userLocation;
	private TextView userTagLine;
	private TextView userAboutMe;
	private TextView userBirthday;

	Button signOutButton;
	Button userInfoButton;
	Button sharePostButton;
	Button shareMediaButton;
	Button revokeAccessButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.sign_in_button).setOnClickListener(this);
		signOutButton = (Button) findViewById(R.id.sign_out_button);
		signOutButton.setOnClickListener(this);
		userInfoButton = (Button) findViewById(R.id.show_userinfo_button);
		userInfoButton.setOnClickListener(this);
		sharePostButton = (Button) findViewById(R.id.share_post_button);
		sharePostButton.setOnClickListener(this);
		shareMediaButton = (Button) findViewById(R.id.share_media_button);
		shareMediaButton.setOnClickListener(this);
		revokeAccessButton = (Button) findViewById(R.id.revoke_access_button);
		revokeAccessButton.setOnClickListener(this);
		findViewById(R.id.user_options_button).setOnClickListener(this);

		userOptionsLayout = (LinearLayout) findViewById(R.id.user_options_layout);
		userInfoLayout = (LinearLayout) findViewById(R.id.user_info_layout);

		userProfilePic = (ImageView) findViewById(R.id.user_profile_pic);
		userName = (TextView) findViewById(R.id.user_name);
		userEmail = (TextView) findViewById(R.id.user_email);
		userLocation = (TextView) findViewById(R.id.user_location);

		mGoogleApiClient = buildGoogleApiClient();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private GoogleApiClient buildGoogleApiClient() {
		return new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(Plus.API, PlusOptions.builder().build())
				.addScope(Plus.SCOPE_PLUS_LOGIN).build();
	}@Override
	   protected void onStart() {
	      super.onStart();
	      // make sure to initiate connection
	      mGoogleApiClient.connect();
	   }
	 
	   @Override
	   protected void onStop() {
	      super.onStop();
	      // disconnect api if it is connected
	      if (mGoogleApiClient.isConnected())
	         mGoogleApiClient.disconnect();
	   }
	   @Override
		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.sign_in_button)
				processSignIn();
			else if (id == R.id.sign_out_button)
				processSignOut();
			else if (id == R.id.show_userinfo_button) {
				userOptionsLayout.setVisibility(View.INVISIBLE);
				userInfoLayout.setVisibility(View.VISIBLE);
			} else if (id == R.id.share_post_button)
				processSharePost();
			else if (id == R.id.share_media_button)
				processShareMedia();
			else if (id == R.id.revoke_access_button)
				processRevokeRequest();
			else if (id == R.id.user_options_button) {
				userInfoLayout.setVisibility(View.INVISIBLE);
				userOptionsLayout.setVisibility(View.VISIBLE);
			}
		}
	@Override
	public void onConnected(Bundle connectionHint) {
		mSignInClicked = false;
		Log.d("inside on connected", "inside on conn");
		Toast.makeText(getApplicationContext(), "Signed in Successfully",
				Toast.LENGTH_LONG).show();
		processUserInfoAndUpdateUi();
		processUIUpdate(true);

	}

	@Override
	public void onConnectionSuspended(int cause) {
		mGoogleApiClient.connect();

	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {

		if (!result.hasResolution()) {
			GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
					ERROR_DIALOG_REQUEST_CODE).show();
			return;
		}
		if (!mIntentInProgress) {
			mConnectionResult = result;
			if (mSignInClicked) {
				progressSignInError();
			}
		}
	}

	

	private void processRevokeRequest() {
if(mGoogleApiClient.isConnected()){
	Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
	Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient).setResultCallback(new ResultCallbacks<Result>() {

		@Override
		public void onFailure(Status arg0) {
			Toast.makeText(getApplicationContext(),
                    "User permissions revoked",
                    Toast.LENGTH_LONG).show();
              mGoogleApiClient = buildGoogleApiClient();
              mGoogleApiClient.connect();
              processUIUpdate(false);			
              
		}

		@Override
		public void onSuccess(Result arg0) {
			Toast.makeText(getApplicationContext(),
                    "User permissions revoked",
                    Toast.LENGTH_LONG).show();
              mGoogleApiClient = buildGoogleApiClient();
              mGoogleApiClient.connect();
              processUIUpdate(false);			
		}
	});
}
	}

	private void processShareMedia() {
		// TODO Auto-generated method stub

	}

	private void processSharePost() {
		// TODO Auto-generated method stub

	}

	private void processUIUpdate(boolean isUserSignedIn) {
		Log.d("UIupdate", "ggg");

		if(isUserSignedIn){
			signOutButton.setEnabled(true);
			userInfoButton.setEnabled(true);
	         sharePostButton.setEnabled(true);
	         shareMediaButton.setEnabled(true);
	         revokeAccessButton.setEnabled(true);
	      } else {
	         signOutButton.setEnabled(false);
	         userInfoButton.setEnabled(false);
	         sharePostButton.setEnabled(false);
	         shareMediaButton.setEnabled(false);
	         revokeAccessButton.setEnabled(false);
	      
		}

	}

	private void processUserInfoAndUpdateUi() {

		Person signedInUser=Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
		if(signedInUser!=null){
			if(signedInUser.hasDisplayName()){
				String userName=signedInUser.getDisplayName();
				this.userName.setText(userName);
			}
			if(signedInUser.hasTagline()){
				String tagLine=signedInUser.getTagline();
				this.userTagLine.setText("Tag lIne"+tagLine);
				this.userTagLine.setVisibility(View.VISIBLE);
			}if (signedInUser.hasAboutMe()) {
	            String aboutMe = signedInUser.getAboutMe();
	            this.userAboutMe.setText("About Me: " + aboutMe);
	            this.userAboutMe.setVisibility(View.VISIBLE);
	         }
	 
	         if (signedInUser.hasBirthday()) {
	            String birthday = signedInUser.getBirthday();
	            this.userBirthday.setText("DOB " + birthday);
	            this.userBirthday.setVisibility(View.VISIBLE);
	         }
	 
	         if (signedInUser.hasCurrentLocation()) {
	            String userLocation = signedInUser.getCurrentLocation();
	            this.userLocation.setText("Location: " + userLocation);
	            this.userLocation.setVisibility(View.VISIBLE);
	         }
	         
	         
	         if(signedInUser.hasImage()){
	        	 String userProfilePicUrl=signedInUser.getImage().getUrl();
	        	 int profilePicRequestSize=250;
	        	 userProfilePicUrl=userProfilePicUrl.substring(0,userProfilePicUrl.length()-2)+profilePicRequestSize;
	        	 new UpdateProfilePicTask(userProfilePic).execute(userProfilePicUrl);
	         }
	         
		}
		
		
		
		
		
		
	}

	private void processSignOut() {
		if (mGoogleApiClient.isConnected()) {
			Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
			mGoogleApiClient.disconnect();
			mGoogleApiClient.connect();
			processUIUpdate(false);

		}
	}

	private void processSignIn() {

		if (!mGoogleApiClient.isConnecting()) {
			progressSignInError();

			mSignInClicked = true;
			Intent intent=new Intent(getApplicationContext(),MapActivity.class);
			Log.d("intent", "starting...");
			startActivity(intent);
		}
	}

	private void progressSignInError() {

		if (mConnectionResult != null && mConnectionResult.hasResolution()){
			mIntentInProgress = true;
		try {		
			mConnectionResult.startResolutionForResult(this,
					SIGN_IN_REQUEST_CODE);
		} catch (SendIntentException e) {
			mIntentInProgress = false;
			mGoogleApiClient.connect();
		}
	}}
	@Override
	
	
	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	

}
