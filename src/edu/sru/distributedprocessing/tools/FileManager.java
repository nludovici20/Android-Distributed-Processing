package edu.sru.distributedprocessing.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.dialogs.AuthenticationDialog;
import edu.sru.distributedprocessing.loadingscreen.AuthenticateLoading;
import edu.sru.distributedprocessing.net.Authenticate;
import edu.sru.distributedprocessing.tableobjects.Table;

public class FileManager {
	public static String username, password;
	
	/*
	 * Method that reads the db file for saved attributes (Starting Index, Fields in View)
	 */
	public static void readTextFile(Context context)
	{
		String line;
		int index;
		// try opening the myfilename.txt
		try 
		{
			// open the file for reading
			FileInputStream fis = context.openFileInput(Constants.db.getDBName() + ".txt");
			// if file the available for reading
			if (fis != null)
			{
				// prepare the file for reading
				InputStreamReader inputreader = new InputStreamReader(fis);
				BufferedReader buffreader = new BufferedReader(inputreader);
				
				// read every line of the file into the line-variable, one line at the time
				while (( line = buffreader.readLine()) != null)
				{
					String[] temp = line.split("  ");
					//read starting index for each table and set accordingly
					for(int i = 0; i < Constants.db.getTables().length; i++)
					{
						if(Constants.db.getTables()[i].getTableName().equalsIgnoreCase(temp[0]))
						{
							Constants.db.getTables()[i].getFieldsInView().clear();
							Constants.db.getTables()[i].setStartingIndex(Integer.parseInt(temp[1]));
							Constants.db.getTables()[i].addField(temp[2]);
							Constants.db.getTables()[i].addField(temp[3]);
						}
					}
				}
			}
			// close the file again
			fis.close();
			Log.v("ADP", "FileManager.class - Successfully read from file");
		}
		catch (Exception e)
		{
			Log.v("ADP", "FileManager.class - File not read");
			
		}
		
	}
	
	/*
	 * Method that saves a db attributes 
	 * Input: Tables in DB
	 * Output: File called <dbName>.txt with each tables starting index, fields in view
	 */
	public static void saveTextFile(Context context, Table[] tables)
	{
		//create file here and save data
		try
		{
			//open file for writing
			OutputStreamWriter out = new OutputStreamWriter(context.openFileOutput(Constants.db.getDBName() + ".txt", context.MODE_PRIVATE));
			for(int i = 0; i < tables.length; i++)
			{
				try
				{
					//write attributes if declared
					out.write(tables[i].getTableName() + "  " + tables[i].getIndex() + "  " + tables[i].getFieldsInView().get(0) + "  " + tables[i].getFieldsInView().get(1));
					out.write("\r\n");
				}catch (Exception e)
				{
					try
					{
						//write general attributes if undeclared
						out.write(tables[i].getTableName() + "  " + tables[i].getIndex() + "  " + tables[i].getFields()[0] + "  " + tables[i].getFields()[1]);
						out.write("\r\n");
						Log.v("ADP", "FileManager.class - Default write");
					}catch(Exception ex)
					{
						Log.v("ADP", "FileManager.class - Error write");
					}
				}
			}
			
			out.close();
			Log.v("ADP", "FileManager.class - File Created Successfully");
		}
		catch(Exception e)
		{
			Log.v("ADP", "FileManager.class - Error creating file");
		}
	}
	
	/*
	 * Method to save config file of network attributes (username, password, server ip, port num)
	 */
	public static void saveConfigFile(Context context, String[] info, String filename)
	{
		//create file here and save data
		try
		{
			OutputStreamWriter out = new OutputStreamWriter(context.openFileOutput(filename + ".txt", context.MODE_PRIVATE));
			for(int i = 0; i < info.length; i++)
			{
				try
				{
					out.write(info[i]);
					out.write("\r\n");
				}catch (Exception e)
				{
					Log.v("ADP", "FileManager.class - Error writing to config file");
					
				}
			}
			
			out.close();
			Log.v("ADP", "FileManager.class - Config File Created Successfully");
		}
		catch(Exception e)
		{
			Log.v("ADP", "FileManager.class - Error creating config file");
		}
	}
	
	/*
	 * Method to retrieve network attributes
	 * Ouput: Username, Password, IP address, port #
	 */
	public static void readConfigFile(Activity act)
	{
		String line;
		int index = 0;
		String[] info = new String[4];
		// try opening the myfilename.txt
		try 
		{
			// open the file for reading
			FileInputStream fis = act.getBaseContext().openFileInput("Config.txt");
			// if file the available for reading
			if (fis != null)
			{
				// prepare the file for reading
				InputStreamReader inputreader = new InputStreamReader(fis);
				BufferedReader buffreader = new BufferedReader(inputreader);
				// read every line of the file into the line-variable, on line at the time
				while (( line = buffreader.readLine()) != null)
				{
					info[index] = line;
					index++;
				}
					AuthenticationDialog authenticate = new AuthenticationDialog(act, R.style.CustomDialogTheme, info[0], info[1], info[2], info[3]);
		        	authenticate.show();
			}
			// close the file again
			fis.close();
			Log.v("ADP", "FileManager.class - Successfully read config file");
		}
		catch (Exception e)
		{
			Log.v("ADP", "FileManager.class - Config file not read");
			AuthenticationDialog authenticate = new AuthenticationDialog(act, R.style.CustomDialogTheme);
	        authenticate.show();
			
		}
	}
	
	//Reads the about.xml file for about page
	public static String readXML(Activity act ,String file, String tag_name)
	{
        try {
			InputStream is = act.getAssets().open(file);
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
