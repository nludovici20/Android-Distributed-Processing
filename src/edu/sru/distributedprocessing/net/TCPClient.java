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
	static final char GET = 0;
	static final char CHANGE = 1;
	static final char INSERT = 2;
	private String lastTable;
	private int numFields;
	
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
		String data;
		boolean read;
		while (running)
		{
			try 
			{
				synchronized(sendLock)
				{
					read = in.ready();
					if(read)
						data = in.readLine();
				}
				if(read)
				{
					data = in.readLine();
					//handle data
					switch (data.charAt(0))
					{
					case GET:
						recieveRequest(lastTable, data);
					case CHANGE:
						changeRequest(data);
					case INSERT:
						insertRequest(data);
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
	
	private void insertRequest(String data) {
				
	}

	private void changeRequest(String data) {
		// TODO Auto-generated method stub
		
	}

	private void recieveRequest(String tableName, String data) {
		ArrayList<Record> rec = new ArrayList<Record>();
		for(int i = 0; i < Constants.db.getTables().length; i++)
		{
			if(tableName.equalsIgnoreCase(Constants.db.getTables()[i].getTableName()))
			{
				Constants.db.getTables()[i].deleteRecords();
				//parse data and insert to rec
				// Split data by "\0"?
				String[] temp = data.substring(1).split("\0");
				for(int j = 0; j < (temp.length); j++)
				{
					try
					{
						String id;
						id = temp[j];
						String[] fields = new String[numFields];
						for(int k = 0; k < fields.length; k++)
						{
							fields[k] = temp[++j];
						}
						
						rec.add(new Record(id, fields));
						//Log.d("TCP", field1 + " " + field2);
					}catch (Exception e)
					{
						
					}
				}
				Constants.db.getTables()[i].addRecords(rec);
				IntelliSyncActivity.ss.Update();
				Log.d("TCP", ""+ Constants.db.getTables()[i].getRecords().length);
			}
		}
		
	}

	public final void send(final String data)
	{
		synchronized(sendLock)
		{
			out.println(data);
		}
	}
	
	/*
	public final void sendDataRequest(String tablename, int index, String field1, String field2)
	{
		this.lastTable = tablename;
		String str = "\0" + tablename + "\0" +index + "\0" +	field1 + "\0" + field2;
		synchronized(sendLock)
		{
			out.println(str);
		}
	}*/
	
	public final void sendDataRequest(Table tbl)
	{		
		this.lastTable = tbl.getTableName();
		this.numFields = tbl.getFieldsInView().size();
		String str = "\0" + lastTable + "\0" + tbl.getIndex();
		
		for(int i = 0; i < numFields; i++)
		{
			str+="\0" + tbl.getFieldsInView().get(i);
		}
		
		synchronized(sendLock)
		{
			out.println(str);
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