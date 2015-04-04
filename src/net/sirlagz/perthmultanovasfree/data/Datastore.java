package net.sirlagz.perthmultanovasfree.data;

import android.app.Activity;
import android.util.Log;
import android.content.Context;
import android.content.SharedPreferences;

public class Datastore extends Activity {
	
	static String inputstring;
	Context context;
	
	public Datastore(Context context) {
		this.context = context;
		//Log.e("CAM Location Datastore","Created Datastore object");
		//SaveString(key, inputstring);
	}

	public void SaveData(String key, String value) {
		SharedPreferences prefs = context.getSharedPreferences(inputstring,MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(key,value);
		editor.commit();
	}
	
	public String GetData(String key) {
		SharedPreferences prefs = getPreferences(MODE_PRIVATE);
		String result = prefs.getString(key , "No Data found...");
		return result;
	}
}
