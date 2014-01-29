package SoundMonitor.Common.common;

import java.io.*;
import java.net.*;

public class Connection implements IConnection
{
	private final Thread _thread;
	private final IConnector _connector;
	private final PrintWriter _writer;
	private final BufferedReader _reader;
	private final String _remoteAddress;
	private boolean _run = true;
	private String _friendlyName = null;

	public Connection(Socket socket, IConnector connector) throws IOException
	{
		_connector = connector;
		_thread = new Thread(this);
		_writer = new PrintWriter(socket.getOutputStream(), true); //what does true control???
		_reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		_remoteAddress = socket.getRemoteSocketAddress().toString();
	}
	
	public String toString()
	{
		return friendlyName();
	}
	
	public String friendlyName()
	{
		return _friendlyName != null 
				? _friendlyName
				: _remoteAddress;
	}
	
	public void startThread()
	{
		_thread.start();
	}
	
	public void run()
	{
		try
		{
			_connector.connectionActive(this);
			
			String line;
			while ((line = _reader.readLine()) != null)
			{
				_dataReceived(line);
				
				if (!_run)
					return;
			}
		}
		catch (IOException exc)
		{
			_connector.connectionError(this, exc);
		}
		finally
		{
			_connector.disconnected(this);
		}
	}

	private void _dataReceived(String line)
	{
		if (line.startsWith("IDENTIFY: "))
			_processIdentityChange(line.substring("IDENTIFY: ".length()));
		else
			_connector.dataReceived(this, new DataPacket(line));
	}
	
	private void _processIdentityChange(String friendlyName)
	{
		_friendlyName  = friendlyName;
		_connector.nameChange(this);
	}

	public void send(IDataPacket data) throws ObjectDisposedException
	{
		if (_writer == null)
			throw new ObjectDisposedException();
		
		_writer.write(data.toString());
		_writer.flush();
	}
	
	public void dispose()
	{
		try
		{
			_reader.close();
		} 
		catch (IOException e)
		{ }
		
		_writer.close();
	}

	public void identifyAs(String friendlyName)
	{
		if (friendlyName == null || friendlyName == "")
			return;
		
		try
		{
			send(new DataPacket("IDENTIFY: " + friendlyName));
		}
		catch (ObjectDisposedException e)
		{ }
	}
}