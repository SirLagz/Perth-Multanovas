package net.sirlagz.perthmultanovasfree.util;

import java.util.ArrayList;
import java.util.List;

import net.sirlagz.perthmultanovasfree.RssItem.RssItem;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class RssParserHandler extends DefaultHandler {
	
	private List<RssItem> rssItems;
	private RssItem currentItem;
	private boolean parsingTitle;
	private boolean parsingLink;
	
	public RssParserHandler() {
		//Log.e("CAM Location","RssParserHandler");
		rssItems = new ArrayList<RssItem>();
	}
	
	public List<RssItem> getItems() {
		//Log.e("CAM Location","RssParserHandler getItems");
		
		return rssItems;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("item".equals(qName)) {
			currentItem = new RssItem();
		} else if("title".equals(qName)) {
			parsingTitle = true;
		} else if("link".equals(qName)) {
			parsingLink = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
			if("item".equals(qName)) {
				rssItems.add(currentItem);
				currentItem = null;
			} else if("title".equals(qName)) {
				parsingTitle = false;
			} else if("link".equals(qName)) {
				parsingLink = false;
			}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(parsingTitle) {
			if(currentItem != null) {
				currentItem.setTitle(new String(ch, start, length));
			}
		} else if (parsingLink) {
			if(currentItem != null) {
				currentItem.setLink(new String(ch,start,length));
				parsingLink = false;
			}
		}
	}
}
