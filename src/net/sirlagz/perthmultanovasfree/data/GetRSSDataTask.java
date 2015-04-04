package net.sirlagz.perthmultanovasfree.data;

import java.util.List;

import net.sirlagz.perthmultanovasfree.R;
import net.sirlagz.perthmultanovasfree.RssItem.RssItem;
import net.sirlagz.perthmultanovasfree.listeners.ListListener;
import net.sirlagz.perthmultanovasfree.util.RssReader;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class GetRSSDataTask extends AsyncTask<String, Void, List<RssItem> > {
	Context context;
	View view;
	Activity activity;
	String inputstring;
	
	private ProgressDialog progress;
		
	public GetRSSDataTask(Context context,Activity activity, View view) {
		progress = new ProgressDialog(context);
		progress.setMessage("Loading...");
		this.context = context;
		this.view = view;
		this.activity = activity;
	}
	
	protected void onPreExcute() {
		progress.show();
	}
	
	@Override
	protected void onPostExecute(List<RssItem> result) {
		if(result == null) {
			Toast.makeText(context, "Unable to retrieve data at this time. Please try again later", Toast.LENGTH_LONG).show();
			return;
		}		
		Datastore ds = new Datastore(this.context);
		ds.SaveData("CamLocs",result.toString());
		if(activity.toString().contains("ListActivity")) {
			ListView CamLocations = (ListView)view.findViewById(R.id.listView1);
			ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(this.context, android.R.layout.simple_list_item_1,result);
			CamLocations.setAdapter(adapter);
			CamLocations.setOnItemClickListener(new ListListener(result, this.activity));
		} else {
			//Log.e("CAM Location onPostExe","No Activity Match");
		}
		progress.dismiss();
	}	
	
	@Override
	protected List<RssItem> doInBackground(String... urls) {
		try {
			RssReader rssReader = new RssReader(urls[0]);
			return rssReader.getItems();
		} catch(Exception e) {
			Log.e("CAM Location Exception", e.getMessage());
		}
		return null;
	}
}