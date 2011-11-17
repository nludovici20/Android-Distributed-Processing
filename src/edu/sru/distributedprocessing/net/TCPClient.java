package edu.sru.distributedprocessing.net;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import android.util.Log;
import edu.sru.distributedprocessing.IntelliSyncActivity;
import edu.sru.distributedprocessing.tableobjects.Record;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tools.Constants;
  
public class TCPClient extends Thread
{
	private final String host;
	private final int port;
	private final Socket socket;
	private final BufferedReader in;
	private final PrintWriter out;
	private boolean running;
	//private final Object sendLock = new Object();
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
					case Message.Type.GET_TABLE:
						recieveTableRequest(lastTable, data);
						break;
					case Message.Type.GET_RECORD:
						recieveRecordRequest(lastTable, data);
						break;
					case Message.Type.GET_CHANGE:
						recieveChangeRequest(data);
						break;
					case Message.Type.GET_INSERT:
						recieveInsertRequest(data);
						break;
					case Message.Type.GET_DELETE:
						recieveDeleteRequest(data);
						break;
					default:
						
						Log.v("ADP", "TCPClient.class - Default Case");	
					}
					
//					try{
//						IntelliSyncActivity.refresh();
//					}catch(Exception e)
//					{
//						Log.v("ADP", "TCPClient.class - Error Refreshing");
//					}
					
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
	 * recieve an entire table for the fieldsInView()
	 */
	private void recieveTableRequest(String tableName, String data) 
	{
		Log.v("ADP", "/******** Recieve Table Request ********\"");
		ArrayList<Record> rec = new ArrayList<Record>(); //record holders
		
		//loop through tables
		for(int i = 0; i < Constants.db.getTables().length; i++)
		{
			//find table selected
			if(tableName.equalsIgnoreCase(Constants.db.getTables()[i].getTableName()))
			{
				try{
					Constants.db.getTables()[i].deleteRecords(); //clear out any old records
				}catch(Exception e)
				{
					Log.v("ADP", "TCPClient.class - Error deleting records");
				}
				
				//split data recieved by appropriate char indicator
				String[] temp = data.substring(1).split(""+Message.Type.GET_TABLE);
				for(int j = 0; j < (temp.length); j++)
				{
					try
					{
						String id = null;
						String[] fields = new String[2]; //fields array
						for(int k = 0; k < fields.length - 1; k++)
						{
							
							id = temp[j]; 	//temp[j] = id
							fields[0] = temp[++j]; 	//temp[++j] = fieldInView(1)
							fields[1] = temp[++j]; //temp[++j] = fieldInView(2)
							//log data
							Log.v("ADP", "TCPClient.class - " + id + " " + fields[0].toString() + " " + fields[1].toString());
						}
						
						rec.add(new Record(id, fields));
					}catch (Exception e)
					{
						Log.v("ADP", "ERROR Creating Record");
					}
				}
				Constants.db.getTables()[i].addRecords(rec);
			}
		}
		Log.v("ADP", "/******** End Recieve Table Request ********\"");
		
	}
	
	/*
	 * send the server a data request - entire table
	 */
	public final void sendTableRequest(Table tbl)
	{		
		char msgChar = Message.Type.GET_TABLE;
		
		Log.v("ADP", "/******** Send Table Request ********\"");
		this.lastTable = tbl.getTableName(); //current table in view
		if(tbl.getFieldsInView().size() < 2)
		{
			this.numFields = 2; //num fields in view
		}else
		{
			this.numFields = tbl.getFieldsInView().size();
		}
		
		String str = msgChar + lastTable  + msgChar + tbl.getIndex() + msgChar + tbl.getDBName(tbl.getFields()[0]); //construct string

		for(int i = 0; i < numFields; i++)
		{
			str+=msgChar + tbl.getDBName(tbl.getFieldsInView().get(i)); //append string
		}
		Log.v("ADP", "TCPClient.class - Sent: " + str);
		out.println(str); //send request to server
		Log.v("ADP", "/******** End Send Table Request ********\"");
	}
	
	private void recieveRecordRequest(String tableName, String data) 
	{
		Log.v("ADP", "/******** Recieve Record Request ********\"");
		Constants.record.clear();
		String[] temp = data.substring(1).split(""+Message.Type.GET_RECORD);
		int length = Constants.db.getTable(tableName).getFields().length;
		for(int i = 0; i < length; i++)
		{
			Constants.record.put(Constants.db.getTable(tableName).getFields()[i], temp[i]);
			Log.v("ADP", "TCPClient.class - Field: " + Constants.db.getTable(tableName).getFields()[i] + " Value: " + temp[i]);
		}
		Log.v("ADP", "/******** End Recieve Record Request ********\"");
	}
	
	/*
	 * pull in an entire record from database
	 */
	public void sendRecordRequest(String tablename, int indexOfRecord)
	{
		Constants.record.clear();
		char msgChar = Message.Type.GET_RECORD;
		Log.v("ADP", "/******** send Record Request ********\"");
		this.lastTable = tablename; //set table in view
		String str = msgChar + tablename + msgChar + indexOfRecord; //construct appropriate string
		out.println(str); //send to server
		Log.v("ADP", str);
		Log.v("ADP", "/******** End send Record Request ********\"");
	}
	
	private void recieveChangeRequest(String data) 
	{
		Log.v("ADP", "/******** Recieve Change Request ********\"");
		//tablename, id of record changed, fieldInView1, fieldInView2 <- changes to inview
		String[] temp = data.substring(1).split(""+Message.Type.GET_CHANGE);
		String[] inView = new String[2];
		for(int i = 0; i < temp.length; i++)
		{
			//temp[++i] = id of record changed
			for(int j = 0; j < Constants.db.getTable(lastTable).getRecords().length; j++)
			{
				//if at the record to be inserted, insert else add original record
				if(Constants.db.getTable(lastTable).getRecords()[j].getID().equalsIgnoreCase(temp[i]))
				{
					inView[0] = temp[i];
					inView[1] = temp[++i];
					//Constants.db.getTable(lastTable).changeRecordAt(j, inView);
					IntelliSyncActivity.changeRecordAt(j, inView);
					Log.v("ADP", "TCPClient.class - changed record: ID - " + j + " Field1.value: " + inView[0] + " Field2.value: " + inView[1]);
				}else
				{
					//nothing
				}
				
			}
			
		}
		Log.v("ADP", "/******** End Recieve Change Request ********\"");
		
	}
	
	public void sendChangeRequest(String tablename, String[] rec)
	{
		this.lastTable = tablename;
		char msgChar = Message.Type.GET_CHANGE;
		Log.v("ADP", "/******** Send Change Request ********\"");
		String str = "";
		for(int i = 0; i < rec.length; i++)
		{
			/*
			 * loop through records (including ID # 
			 * and add to string to be sent to database to update
			 */
			str+=msgChar + rec[i];
		}
		
		out.println(str);
		Log.v("ADP", "TCPClient.class - Sent: " + str);
		Log.v("ADP", "/******** End Send Change Request ********\"");
	}
	
	/*
	 * recieve inserted data and handle accordingly
	 */
	private void recieveInsertRequest(String data)
	{
		Log.v("ADP", "/******** Recieve Insert Request ********\"");
		String[] temp = data.substring(1).split(""+Message.Type.GET_INSERT); //split data by designated char
		Log.v("ADP", data);
		//loop until no more elements in temp
		for(int i = 0; i < temp.length; i++)
		{
			String[] fields = new String[2]; //create a new String[]
			String index = temp[i];
			fields[0] = temp[++i]; //temp[i] = fieldInView1 value
			fields[1] = temp[++i]; //temp[++i] = fieldInView2 value
			IntelliSyncActivity.insertRecordAt(index, fields);	
			Log.d("ADP", "TCPClient.class - New Record:" + index + " " + fields[0] + " " + fields[1]);
		}
		Log.v("ADP", "/******** End ReceiveInsert Request ********\"");
	}
	
	/*
	 * Send a newly inserted Record from client to the server
	 */
	public void sendInsertRequest(String tablename, String[] rec)
	{
		char msgChar = Message.Type.GET_INSERT;
		Log.v("ADP", "/******** Send Insert Request ********\"");
		this.lastTable = tablename; //set table in view
		String str = ""; //create string to send
		for(int i = 0; i < rec.length-1; i++)
		{
			if(rec[i].equalsIgnoreCase(""))
			{
				str+=msgChar + "\0";
			}else
			{
				str+= msgChar + rec[i]; //append string to send with elements of new record
			}
		} 
		str+=msgChar;
		out.println(str); //send request to server
		Log.v("ADP", "TCPClient.class - Sent: " + str);
		Log.v("ADP", "/******** End Send Insert Request ********\"");
	}
	
	/*
	 * Recieve data and handle deletion of a record
	 * data contains a tablename, and an index to delete
	 */
	private void recieveDeleteRequest(String data)
	{
		Log.v("ADP", "/******** Recieve Delete Request ********\"");
		String[] temp = data.substring(1).split(""+Message.Type.GET_DELETE);
		//temp[0] is tablename
		//temp[n+1] is index of deleted record
		for(int i = 0; i < temp.length; i++)
		{
			//delete record at index passed in
			IntelliSyncActivity.deleteRecordAt(temp[i]);
			Log.v("ADP", "TCPClient.class - Deleting record at index " + temp[i]);
		}
		Log.v("ADP", "/******** End Recieve Delete Request ********\"");	
	}
	
	/*
	 * send a deletion request to the database
	 */
	public final void sendDeleteRequest(String tablename, int indexOfDeletedRecord)
	{
		char msgChar = Message.Type.GET_DELETE;
		Log.v("ADP", "/******** Send Delete Request ********\"");
		String str = msgChar + tablename + msgChar + indexOfDeletedRecord; //construct the string
		out.println(str); //send str to server
		Log.v("ADP", str);
		Log.v("ADP", "/******** END Send Delete Request ********\"");
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