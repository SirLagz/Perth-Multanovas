package net.sirlagz.perthmultanovasfree.listeners;

import java.util.List;

import net.sirlagz.perthmultanovasfree.RssItem.RssItem;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ListListener implements OnItemClickListener {

	List<RssItem> listItems;
	Activity activity;
	
	public ListListener(List<RssItem> listItems, Activity activity) {
		//Log.e("CAM Location","ListListener");
		this.listItems = listItems;
		this.activity = activity;
		
	}
	
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		//Log.e("CAM Location","ListListener onItemClick");
		//Log.e("CAM Location activity","ListListener " + activity.toString());
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(listItems.get(pos).getLink()));
		//Log.e("CAM Location Link","Link - "+ listItems.get(pos).getLink());
		//Log.e("CAM Location","onItemClick "+ i);
		activity.startActivity(i);
		
	}
	
}
