package edu.sru.distributedprocessing.net;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter; 
import java.net.InetAddress;
import java.net.Socket; 
import java.util.ArrayList;
import java.util.HashMap;

import edu.sru.distributedprocessing.IntelliSyncActivity;
import edu.sru.distributedprocessing.shippingscreen.ShippingScreen;
import edu.sru.distributedprocessing.tableobjects.Record;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tools.Constants;
import android.util.Log;
  
public class TCPClient extends Thread
{
	private final String host;
	private final int port;
	private final Socket socket;
	private final BufferedReader in;
	private final PrintWriter out;
	private boolean running;
	//private final Object sendLock = new Object();
	static final char GET_TABLE = 0;
	static final char GET_RECORD = 1;
	static final char CHANGE = 2;
	static final char INSERT = 3;
	static final char DELETE = 4;
	private String lastTable;
	private int numFields;
	
	public TCPClient(final String host, final int port) throws IOException
	{
		this.host = host;
		this.port = port;InetAddress serverAddr = InetAddress.getByName(host); 
		Log.d("ADP", "TCPClient.class - C: Connecting..."); 
		this.socket = new Socket(serverAddr, port);
		this.in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(),true);
	}
	
	public final void run() 
	{ 
		running = true;
		String data = null;
		boolean read;
		while (running)
		{
			try 
			{
				if(in.ready())
				{
					data = in.readLine(); //get data sent
					
					/*
					 * Decide which request to execute
					 */
					switch (data.charAt(0))
					{
					case GET_TABLE:
						recieveTableRequest(lastTable, data);
					case GET_RECORD:
						recieveRecordRequest(lastTable, data);
					case CHANGE:
						recieveChangeRequest(data);
					case INSERT:
						recieveInsertRequest(data);
					case DELETE:
						recieveDeleteRequest(data);
					default:
						Log.v("ADP", "TCPClient.class - Default Case");
						
					}
					Log.v("ADP","TCPClient.class - " + data);
				}
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}				
	}
	
	/*
	 * Recieve data and handle deletion of a record
	 * data contains a tablename, and an index to delete
	 */
	private void recieveDeleteRequest(String data) {
		String[] temp = data.substring(1).split("\4");
		
		//temp[0] is tablename
		//temp[n+1] is index of deleted record
		for(int i = 1; i < temp.length; i++)
		{
			//delete record at index passed in
			Constants.db.getTable(temp[0]).deleteRecord(Integer.parseInt(temp[i]));
		}
		
	}
	
	/*
	 * send a deletion request to the database
	 */
	public final void sendDeleteRequest(String tablename, int indexOfDeletedRecord)
	{
		String str = "\4" + tablename + "\4" + indexOfDeletedRecord; //construct the string
		out.println(str); //send str to server
	}

	private void recieveRecordRequest(String tableName, String data) {
		Constants.record.clear();
		String[] temp = data.substring(1).split("\1");
		int length = Constants.db.getTable(tableName).getFields().length;
		for(int i = 0; i < length; i++)
		{
			Constants.record.put(Constants.db.getTable(tableName).getFields()[i], temp[i]);
			Log.v("ADP", "TCPClient.class - Field: " + Constants.db.getTable(tableName).getFields()[i] + " Value: " + temp[i]);
		}
		
	}
	
	/*
	 * pull in an entire record from database
	 */
	public void getRecordRequest(String tablename, int indexOfRecord)
	{
		this.lastTable = tablename; //set table in view
		String str = "\1" + tablename + "\1" + indexOfRecord; //construct appropriate string
		out.println(str); //send to server
	}

	/*
	 * recieve inserted data and handle accordingly
	 */
	private void recieveInsertRequest(String data) {
		String[] temp = data.substring(1).split("\3"); //split data by designated char
		
		//loop until no more elements in temp
		for(int i = 0; i < temp.length; i++)
		{
			String[] fields = new String[2]; //create a new String[]
			fields[0] = temp[i]; //temp[i] = fieldInView1 value
			fields[1] = temp[++i]; //temp[++i] = fieldInView2 value
			Constants.db.getTable(this.lastTable).addRecord(new Record(fields));
			Log.d("ADP", "TCPClient.class - " + fields[0] + " " + fields[1]);
		}
	}
	
	/*
	 * Send a newly inserted Record from client to the server
	 */
	public void sendInsertRequest(String tablename, int indexOfNewRecord, String[] rec)
	{
		this.lastTable = tablename; //set table in view
		String str = "\3" + tablename + "\3" + indexOfNewRecord; //create string to send
		for(int i = 0; i < rec.length; i++)
		{
			str+="\3" + rec[i]; //append string to send with elements of new record
		} 
		out.println(str); //send request to server
	}

	private void recieveChangeRequest(String data) {
		//tablename, id of record changed, fieldInView1, fieldInView2 <- changes to inview
		String[] temp = data.substring(1).split("\2");
		ArrayList<Record> rec = new ArrayList<Record>();
		
		for(int i = 0; i < temp.length; i++)
		{
			//temp[++i] = id of record changed
			for(int j = 0; j < Constants.db.getTable(temp[i]).getRecords().length; j++)
			{
				//if at the record to be inserted, insert else add original record
				if(j == Integer.parseInt(temp[++i]))
				{
					String inView1 = temp[++i];
					String inView2 = temp[++i];
					rec.add(new Record(""+j, inView1, inView2));
					Log.v("ADP", "TCPClient.class - changed record: ID - " + j + " Field1.value: " + inView1 + " Field2.value: " + inView2);
				}else
				{
					rec.add(Constants.db.getTable(temp[i]).getRecords()[j]);
				}
				
			}
			
		}
		
	}
	
	public void sendChangeRequest(String tablename, String[] rec)
	{
		String str = "\2" + tablename; //tablename where record is getting changed
		for(int i = 0; i < rec.length; i++)
		{
			/*
			 * loop through records (including ID # 
			 * and add to string to be sent to database to update
			 */
			str+="\2" + rec[i];
		}
		
		out.println(str);
	}

	/*
	 * recieve an entire table for the fieldsInView()
	 */
	private void recieveTableRequest(String tableName, String data) {
		ArrayList<Record> rec = new ArrayList<Record>(); //record holders
		
		//loop through tables
		for(int i = 0; i < Constants.db.getTables().length; i++)
		{
			//find table selected
			if(tableName.equalsIgnoreCase(Constants.db.getTables()[i].getTableName()))
			{
				Constants.db.getTables()[i].deleteRecords(); //clear out any old records
				
				//split data recieved by appropriate char indicator
				String[] temp = data.substring(1).split("\0");
				for(int j = 0; j < (temp.length); j++)
				{
					try
					{
						String[] fields = new String[2]; //fields array
						for(int k = 0; k < fields.length -1; k++)
						{
							fields[0] = temp[j]; //temp[j] = fieldInView(1)
							fields[1] = temp[++j]; //temp[++j] = fieldInView(2)
							//log data
							Log.v("ADP", "TCPClient.class - " + fields[0].toString() + " " + fields[1].toString());
						}
						
						rec.add(new Record(fields));
					}catch (Exception e)
					{
						Log.v("ADP", "ERROR Creating Record");
					}
				}
				Constants.db.getTables()[i].addRecords(rec);
				
				try
				{
					IntelliSyncActivity.ss.refreshListItems(); //notify list values changed
				}catch (Exception e)
				{
				
				}
			}
		}
		
	}
	
	/*
	 * send the server a data request - entire table
	 */
	public final void sendDataRequest(Table tbl)
	{		
		this.lastTable = tbl.getTableName(); //current table in view
		if(tbl.getFieldsInView().size() < 2)
		{
			this.numFields = 2; //num fields in view
		}else
		{
			this.numFields = tbl.getFieldsInView().size();
		}
		
		String str = "\0" + lastTable + "\0" + tbl.getIndex(); //construct string

		for(int i = 0; i < numFields; i++)
		{
			str+="\0" + tbl.getDBName(tbl.getFieldsInView().get(i)); //append string
		}
 
		out.println(str); //send request to server
	}

	public final void send(final String data)
	{
		out.println(data);
	}
	
	public final void finish() throws InterruptedException, IOException
	{
		socket.close();
		running = false;
		this.join();
	}	
	
	public final String getHost()
	{
		return host;
	}
	
	public final int getPort()
	{
		return port;
	}
}