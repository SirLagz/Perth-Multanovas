package net.sirlagz.perthmultanovasfree.util;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.util.Log;

import net.sirlagz.perthmultanovasfree.RssItem.RssItem;

public class RssReader {

	private String rssUrl;
	
	public RssReader(String rssUrl) {
		//Log.e("CAM Location","RssReader");
		this.rssUrl = rssUrl;
	}
	
	public List<RssItem> getItems() throws Exception {
		//Log.e("CAM Location","RssReader getItems()");
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		
		RssParserHandler handler = new RssParserHandler();
		
		saxParser.parse(rssUrl, handler);
		return handler.getItems();
	}
	
}
