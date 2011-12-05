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
import android.util.Log;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.dialogs.AuthenticationDialog;
import edu.sru.distributedprocessing.tableobjects.Table;

/**
 * This class is used to handle file manipulation throughout the application.
 * It allows for read/write ability of both .xml, and .txt files.
 * 
 * @author Nick Ludovici
 */
public class FileManager {
	public static String username, password;
	
	/**
	 * Method that reads the database file for saved attributes (Starting Index, Fields in View)
	 * 
	 * @param context the context calling this method.
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
	
	/**
	 * Method that saves the database and the attributes needed for resuming the application if closed.
	 * The Database file is saved as <database name>.txt and is private to all other applications.
	 * 
	 * @param context the context calling the method.
	 * @param tables the tables that need their attributes saved.
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
	
	/**
	 * Method that saves a configuration file that contains: username, password, server IP, and port Num
	 * These attributes are used in authenticating the client with the server.
	 * 
	 * @param context the context that called this method.
	 * @param info the info that is to be saved to the config file.
	 * @param filename the name of the file that is to be saved.
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
	
	/**
	 * Method that reads a saved configuration file, and returns the attributes to the user for authentication with the server.
	 * 
	 * @param act the activity that called this method.
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
	
	/**
	 * Method that reads an XML file and returns information found at a specified tag.
	 * 
	 * @param act the activity that called this method.
	 * @param file the name of the file to be read.
	 * @param tag_name the xml tag that is to be read from the xml file.
	 * @return the information located at the specified tag_name.
	 */
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
