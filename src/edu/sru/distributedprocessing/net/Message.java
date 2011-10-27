package edu.sru.distributedprocessing.net;

public class Message 
{
	public class Type
	{
		//TO SERVER: table name, index, field1, field2,...
		//FROM SERVER: each set of fields starting at index
		public final static char GET_TABLE = '\0';
		
		//TO SERVER: table name, index
		//FROM SERVER: fields
		public final static char GET_RECORD = '\1';
		
		//TO SERVER: table name, index, fields
		//FROM SERVER: table name, index, fields
		public final static char GET_CHANGE = '\2';
		
		//TO SERVER: table name, index, fields
		//FROM SERVER: table name, index, fields
		public final static char GET_INSERT = '\3';
		
		
		//TO SERVER: table name, index
		//FROM SERVER: table name, index
		public final static char GET_DELETE = '\4';
	}
}
