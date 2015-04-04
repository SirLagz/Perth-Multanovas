package net.sirlagz.perthmultanovasfree;


import net.sirlagz.perthmultanovasfree.R;
import net.sirlagz.perthmultanovasfree.data.GetRSSDataTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ListActivity extends Activity {
	
	private ListActivity local;
	private static final int RESULT_SETTINGS = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		//Log.e("CAM Location", "ListActivity onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
		
		local = this;
		
		//Log.e("CAM Location activity","onCreate " + local.toString());
		GetRSSDataTask task = new GetRSSDataTask(local, local, findViewById(R.id.listView1));
    	SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
    	String sort = sharedPrefs.getString("pref_SortOrder","");
    	Log.e("CAM Location",sort);
		
		task.execute(sort);
		//task.execute("http://perthtraffic.sirlagz.net/xml/camfeed1.php");
		//Log.e("CAM Location", Thread.currentThread().getName());
	}

	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.list, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.action_refresh:
	        	GetRSSDataTask task = new GetRSSDataTask(local, local, findViewById(R.id.listView1));
	        	SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
	        	String sort = sharedPrefs.getString("pref_SortOrder","");
	        	Log.e("CAM Location",sort);
	        	task.execute(sort);
	            return true;
	        case R.id.action_options:
	        	Log.e("CAM Location", "pressing options");
	        	Intent i = new Intent(this, SettingsActivity.class);
	        	startActivityForResult(i, RESULT_SETTINGS);
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
