package kk.play.wish;




public class MainActivity {
	Button submit;
	EditText username;
	EditText password;
	TextView name1,pass1;
	String name;
	String pass;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		username=(EditText)findViewById(R.id.user_name);
		password=(EditText)findViewById(R.id.password);
		submit=(Button)findViewById(R.id.button12);
		
		name1=(TextView)findViewById(R.id.name);
		pass1=(TextView)findViewById(R.id.pass);
		
		
		
		final String text="kk";
		//text=password.getText().toString()+username.getText().toString();
		
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this,text, Toast.LENGTH_LONG).show();
				name1.setText(username.getText());
				pass1.setText(password.getText());
				
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
