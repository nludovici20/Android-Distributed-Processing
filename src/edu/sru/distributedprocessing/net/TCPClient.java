package edu.sru.distributedprocessing.net;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter; 
import java.net.InetAddress;
import java.net.Socket; 
import java.util.ArrayList;

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
	private final Object sendLock = new Object();
	static final char GET_TABLE = 0;
	static final char GET_RECORD = 1;
	static final char CHANGE = 2;
	static final char INSERT = 3;
	static final char DELETE = 4;
	private String lastTable;
	private int numFields;
	//private Queue<String> sendQueue = new Queue<String>();
	
	public TCPClient(final String host, final int port) throws IOException
	{
		this.host = host;
		this.port = port;InetAddress serverAddr = InetAddress.getByName(host); 
		Log.d("TCP", "C: Connecting..."); 
		this.socket = new Socket(serverAddr, port);
		this.in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//this.out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(socket.getOutputStream())),true);
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
				//synchronized(sendLock)
				{
					read = in.ready();
					if(read)
						data = in.readLine();
				}
				if(read)
				{
					//handle data
					switch (data.charAt(0))
					{
					case GET_TABLE:
						recieveTableRequest(lastTable, data);
					case GET_RECORD:
						recieveRecordRequest(data);
					case CHANGE:
						recieveChangeRequest(data);
					case INSERT:
						recieveInsertRequest(data);
					case DELETE:
						recieveDeleteRequest(data);
					default:
						Log.v("TCP", "Default Case");
						
					}
					Log.d("TCP",data);
				}
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}				
	}
	
	private void recieveDeleteRequest(String data) {
		// TODO Auto-generated method stub
		
	}

	private void recieveRecordRequest(String data) {
		// TODO Auto-generated method stub
		
	}

	private void recieveInsertRequest(String data) {
				
	}

	private void recieveChangeRequest(String data) {
		// TODO Auto-generated method stub
		
	}

	private void recieveTableRequest(String tableName, String data) {
		ArrayList<Record> rec = new ArrayList<Record>();
		for(int i = 0; i < Constants.db.getTables().length; i++)
		{
			if(tableName.equalsIgnoreCase(Constants.db.getTables()[i].getTableName()))
			{
				Constants.db.getTables()[i].deleteRecords();
				
				String[] temp = data.substring(1).split("\0");
				for(int j = 0; j < (temp.length); j++)
				{
					try
					{
						String[] fields = new String[2];
						for(int k = 0; k < fields.length -1; k++)
						{
							fields[0] = temp[j];
							fields[1] = temp[++j];
							Log.d("TCP", fields[0].toString() + " " + fields[1].toString());
						}
						
						rec.add(new Record(fields));
					}catch (Exception e)
					{
						Log.d("TCP", "ERROR Creating Record");
					}
				}
				Constants.db.getTables()[i].addRecords(rec);
				//IntelliSyncActivity.ss.refreshListItems(); //notify list values changed
				//Log.d("TCP", ""+ Constants.db.getTables()[i].getRecords().length);
			}
		}
		
	}

	public final void send(final String data)
	{
		//synchronized(sendLock)
		{
			out.println(data);
		}
	}
	
	
	public final void sendDataRequest(String tablename, int index, String field1, String field2)
	{
		this.lastTable = tablename;
		String str = "\0" + tablename + "\0" +index + "\0" + field1 + "\0" + field2;
		//synchronized(sendLock)
		{
			out.println(str);
		}
	}
	
	public final void sendDataRequest(Table tbl)
	{		
		this.lastTable = tbl.getTableName();
		if(tbl.getFieldsInView().size() < 2)
		{
			this.numFields = 2;
		}else
		{
			this.numFields = tbl.getFieldsInView().size();
		}
		String str = "\0" + lastTable + "\0" + tbl.getIndex();

		for(int i = 0; i < numFields; i++)
		{
			str+="\0" + tbl.getDBName(tbl.getFieldsInView().get(i));
		}
		
		Log.d("TCP", "In Send, Before SendLock");
		//synchronized(sendLock)
		{
			Log.d("TCP", "SendLock");
			out.println(str);
			Log.d("TCP", "Sent Successful");
		}
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