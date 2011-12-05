package edu.sru.distributedprocessing.net;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import android.app.Activity;
import android.util.Log;
import edu.sru.distributedprocessing.IntelliSyncActivity;
import edu.sru.distributedprocessing.loadingscreen.AuthenticateLoading;
import edu.sru.distributedprocessing.loadingscreen.InsertLoading;
import edu.sru.distributedprocessing.loadingscreen.RecordLoading;
import edu.sru.distributedprocessing.loadingscreen.TableLoading;
import edu.sru.distributedprocessing.tableobjects.Record;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tools.Constants;
import edu.sru.distributedprocessing.tools.FileManager;
  
/**
 * This class manages the TCP connection between the server and a client.
 * It runs its main loop on its own thread where it checks for incoming messages,
 * and handles them depending on the first character in the message.
 * 
 * @author Nick Ludovici
 */
/**
 * @author Nick
 *
 */
/**
 * @author Nick
 *
 */
public class TCPClient extends Thread
{
	private final String host;
	private final int port;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private boolean running;
	//private final Object sendLock = new Object();
	private String lastTable;
	private int numFields;
	private Activity act;
	private boolean wasKicked = false;
	public static boolean isConnected = false;
	private String lastTableRequestMSG;
	
	/**
	 * @param act the current activity in view trying to create the TCP Client thread.
	 * @param host the address of where the server is located.
	 * @param port the port on which the server is listening for incoming requests.
	 * @throws Exception
	 */
	public TCPClient(Activity act, final String host, final int port) throws Exception
	{
		wasKicked = false;
		this.act = act;
		this.host = host;
		this.port = port;
		Log.d("ADP", "TCPClient.class - C: Connecting..."); 
		connect();
	}
	
	/**
	 * This methods handles setting up (handshaking) between the client and server.
	 * 
	 * @throws Exception
	 */
	public void connect() throws Exception
	{
		Log.v("ADP", "Connecting");
		InetAddress serverAddr = InetAddress.getByName(host); 
		this.socket = new Socket(serverAddr, port);
		this.in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(),true);
		wasKicked = false;
		isConnected = true;
	}
	
	/** 
	 * The main loop that handles incoming messages from the server.
	 * 
	 * @see java.lang.Thread#run()
	 */
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
					case Message.Type.AUTHENTICATE:
						Log.v("ADP", data);
						AuthenticateLoading.waiting = false;
						wasKicked = false;
						break;
					case Message.Type.CONNECTION:
						Log.v("ADP", data);
						socket.close();
						wasKicked = true;
						isConnected = false;
						break;
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
	
	/**
	 * This method handles the servers message for a table request.
	 * 
	 * The information passed in from the server is broken up into records, and inserted into the corresponding table.
	 * 
	 * @param tableName the name of the table that was requested by the client.
	 * @param data the entire table's data sent to the client by the server from the database.
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
		TableLoading.waiting = false;
		Log.v("ADP", "/******** End Recieve Table Request ********\"");
		
	}
	
	/**
	 * This method sends the server a table request.
	 * 
	 * From the argument passed in, the fields in view, and index is extracted and sent to the server.
	 * 
	 * @param tbl the table that is currently being requested.
	 */
	public final void sendTableRequest(Table tbl)
	{		
		TableLoading.waiting = true;
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
		send(str); //send request to server
		this.lastTableRequestMSG = str;
		Log.v("ADP", "/******** End Send Table Request ********\"");
	}
	
	/**
	 * This method handles the servers message for a record request.
	 * 
	 * The information passed in from the server is added to a global record located in Constants.java.
	 * 
	 * @param tableName the name of the table that was requested by the client.
	 * @param data the entire record sent to the client by the server from the database.
	 */
	private void recieveRecordRequest(String tableName, String data) 
	{
		Log.v("ADP", "/******** Recieve Record Request ********\"");
		String[] temp = data.substring(1).split(""+Message.Type.GET_RECORD);
		int length = Constants.db.getTable(tableName).getFields().length;
		for(int i = 0; i < length; i++)
		{
			Constants.record.put(Constants.db.getTable(tableName).getFields()[i], temp[i]);
			Log.v("ADP", "TCPClient.class - Field: " + Constants.db.getTable(tableName).getFields()[i] + " Value: " + temp[i]);
		}
		RecordLoading.waiting = false;
		Log.v("ADP", "/******** End Recieve Record Request ********\"");
	}
	
	/**
	 * This method sends the server a Record request.
	 * 
	 * The current table, and ID of the record requested is prepared in a request and sent to the server.
	 * 
	 * @param tablename the name of the table that the record requested is in.
	 * @param indexOfRecord the ID of the record that has been requested.
	 */
	public void sendRecordRequest(String tablename, int indexOfRecord)
	{
		RecordLoading.waiting = true;
		Constants.record.clear();
		char msgChar = Message.Type.GET_RECORD;
		Log.v("ADP", "/******** send Record Request ********\"");
		this.lastTable = tablename; //set table in view
		String str = msgChar + tablename + msgChar + indexOfRecord; //construct appropriate string
		send(str); //send to server
		Log.v("ADP", str);
		Log.v("ADP", "/******** End send Record Request ********\"");
	}
	
	/**
	 * This method handles the servers message for a change request.
	 * 
	 * The information passed in from the server is broken up into records, and the changes are made to the corresponding table.
	 * 
	 * @param data the entire records data sent to the client by the server from the database.
	 */
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
		InsertLoading.waiting = false;
		Log.v("ADP", "/******** End Recieve Change Request ********\"");
		
	}
	
	/**
	 * This method sends the server a change request.
	 * 
	 * The table where the record is located, as well as the changes that have been made are prepared in the request message, and sent to the server.
	 * 
	 * @param tablename the name of the table where the record is held in.
	 * @param rec the entire records data that has been changed on the client and needs to be updated the database.
	 */
	public void sendChangeRequest(String tablename, String[] rec)
	{
		InsertLoading.waiting = true;
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
		
		send(str);
		Log.v("ADP", "TCPClient.class - Sent: " + str);
		Log.v("ADP", "/******** End Send Change Request ********\"");
	}
	
	/**
	 * This method handles the servers message for an insert request.
	 * 
	 * The information passed in from the server is broken up into records, and inserted into the corresponding table.
	 * 
	 * @param data the entire record's data to be inserted sent to the client by the server from the database.
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
		InsertLoading.waiting = false;
		Log.v("ADP", "/******** End ReceiveInsert Request ********\"");
	}
	
	/**
	 * This method sends the server an insert request.
	 * 
	 * The table where the record is located, as well as the attributes that have been added are prepared in the request message, and sent to the server.
	 * 
	 * @param tablename the name of the table where the new record is to be held in.
	 * @param rec the entire records data that has been inserted on the client and needs to be inserted in the database.
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
		send(str); //send request to server
		Log.v("ADP", "TCPClient.class - Sent: " + str);
		Log.v("ADP", "/******** End Send Insert Request ********\"");
	}
	
	/**
	 * This method handles the servers message for a delete request.
	 * 
	 * The information passed in from the server gives the client instructions on which record that has been deleted.
	 * 
	 * @param data the entire table's data sent to the client by the server from the database.
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
	
	
	/**
	 * This method is used to send a delete request to the server.
	 * 
	 * The table where the record has been deleted, as well as its ID is sent to the server in a request message.
	 * 
	 * @param tablename the table where the record needs to be deleted from.
	 * @param indexOfDeletedRecord the ID of the record that is to be deleted.
	 */
	public final void sendDeleteRequest(String tablename, int indexOfDeletedRecord)
	{
		char msgChar = Message.Type.GET_DELETE;
		Log.v("ADP", "/******** Send Delete Request ********\"");
		String str = msgChar + tablename + msgChar + indexOfDeletedRecord; //construct the string
		send(str); //send str to server
		Log.v("ADP", str);
		Log.v("ADP", "/******** END Send Delete Request ********\"");
	}
	
	/**
	 * This method is used to alert the server that the client has logged off and to stop tracking it.
	 */
	public final void sendLogOffRequest()
	{
		Log.v("ADP", "/********** Send Log Off Request **********/");
		send(""+Message.Type.CONNECTION);
		Log.v("ADP", "/********** End Log Off Request **********/");
	}
	
	/**
	 * This method is used to ensure connection between the client and server.
	 * It also sends requests to the server.
	 * 
	 * @param data the data to be sent from the client to the server.
	 */
	public final void send(final String data)
	{
		if(wasKicked)
		{
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Log.v("ADP", "Re-Estabolishing connection...");
				connect();
				out.println(Message.Type.AUTHENTICATE + FileManager.username + Message.Type.AUTHENTICATE + FileManager.password + Message.Type.AUTHENTICATE);
				if(!this.lastTableRequestMSG.equalsIgnoreCase(data))
				{
					out.println(this.lastTableRequestMSG);
				}
			} catch (Exception e) {
				//kill program?
			}
		}
		out.println(data);
	}
		
	/**
	 * This method cleanly closes the TCP socket, and terminates the TCP thread.
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public final void finish() throws InterruptedException, IOException
	{
		socket.close();
		running = false;
		this.join();
	}	
	
	/**
	 * An accessor method used to retrieve the address to the server machine.
	 * 
	 * @return the address of the machine where the server is located.
	 */
	public final String getHost()
	{
		return host;
	}
	
	/**
	 * An accessor method used to retrieve the servers port number.
	 * 
	 * @return the port that the server is listening on.
	 */
	public final int getPort()
	{
		return port;
	}

}