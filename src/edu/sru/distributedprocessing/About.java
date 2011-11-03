/*
 * Class that displays our About page.
 */

package edu.sru.distributedprocessing;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class About extends Activity {
	TextView about_text;
	
	@Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        about_text = (TextView)findViewById(R.id.about);
        about_text.setText(readXML("about.xml", "text"));
	}
	
	private String readXML(String file, String tag_name)
	{
        try {
			InputStream is = getAssets().open("about.xml");
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(is);
			doc.getDocumentElement ().normalize ();
			
			NodeList tutorialText = doc.getElementsByTagName(tag_name);
			
			Element myText = (Element) tutorialText.item(0);
			
			return ((Node)myText.getChildNodes().item(0)).getNodeValue().trim();
			
		} catch (Exception e)
		{
			Log.v("ADP", "Error Reading Assets file");
		}
		
		return null;
	}

}
