package SoundMonitor.Common.common;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class SoundServer implements ISoundServer
{
	private final ServerSocket _socket;
	private final ArrayList<IConnection> _clients = new ArrayList<IConnection>();
	private final IServerEvents _events;
	private final IServerSettings _settings;
	
	public SoundServer(
		IServerEvents events,
		IServerSettings settings)
	{
		_events = events;
		_settings = settings;
		ServerSocket socket = null;
		
		try
		{
			socket = new ServerSocket(_settings.port());
		}
		catch (IOException exc)
		{
			_events.serverError(this, exc);
		}
		
		_socket = socket;
	}
	
	public void run()
	{
		if (_socket == null || _socket.isClosed())
		{
			_events.serverError(this, new ObjectDisposedException());
			return;
		}
	
		try
		{
			while(!_socket.isClosed())
			{
				IConnection client = new Connection(_socket.accept(), this);
				client.startThread();
				_clients.add(client);
				_events.clientConnected(this, client);
			}
		}
		catch (IOException exc)
		{
			_events.serverError(this, exc);
		}
	}
	
	public void disconnected(IConnection client)
	{
		if (_socket.isClosed())
			return;
	
		_clients.remove(client);
		_events.clientDisconnected(this, client);
	}
	
	public void dataReceived(IConnection client, IDataPacket data)
	{
		_events.dataReceived(this, client, data);
	}
	
	public void connectionError(IConnection client, IOException error)
	{
		_events.connectionError(this, client, error);
	}
	
	public void close() throws IOException
	{
		if (_socket != null)
			_socket.close();
		_clients.clear();
	}
	
	public void dispose()
	{
		try
		{
			close();
		}
		catch (IOException e)
		{}
	}
	
	public void send(IDataPacket data) throws ObjectDisposedException
	{
		for (IConnection client : _clients)
			client.send(data);
	}

	public void connectionActive(IConnection connection)
	{
		_events.connectionActive(this);
	}
	
	public int clients()
	{
		if (_socket == null || _socket.isClosed())
			return 0;
		
		return _clients.size();
	}

	public void nameChange(IConnection client)
	{
		_events.nameChange(this, client);
	}
}