package SoundMonitor;

import java.net.*;
import java.io.*;

public class SoundClient implements ISoundClient
{
	private final Socket _socket;
	private final IClientEvents _events;
	private final IConnection _connection;
	private final IClientSettings _settings;
	private float _lastVolume = -1;
	
	public SoundClient(
		IClientEvents events,
		IClientSettings settings)
	{
		_events = events;
		_settings = settings;

		Socket socket = null;
		Connection connection = null;
		try
		{
			socket = new Socket(_settings.server(), _settings.port());
			connection = new Connection(socket, this);
			
			connection.startThread();
		} 
		catch (IOException exc)
		{
			events.connectionError(this, exc);
		}
		
		_connection = connection;
		_socket = socket;
	}
	
	public void send(IDataPacket data) throws ObjectDisposedException
	{
		if (_connection == null)
			throw new ObjectDisposedException();
	
		_connection.send(data);
	}
	
	public void dataReceived(IConnection server, IDataPacket data)
	{
		float volume = data.floatValue();
		_events.volumeUpdate(this, volume);
		
		if (volume != _lastVolume)
			_events.volumeChange(this, _lastVolume, volume);
		
		_lastVolume = volume;
	}
	
	public void disconnected(IConnection server)
	{
		_events.disconnectedFromServer(this);
	}

	public void connectionError(IConnection server, IOException error)
	{
		_events.connectionError(this, error);
	}
	
	public void dispose()
	{
		try
		{
			if (_socket != null)
				_socket.close();
		}
		catch (IOException e) 
		{
			
		}
		
		if (_connection != null)
			_connection.dispose();
	}

	public void connectionActive(IConnection connection)
	{
		updateIdentityFromSettings();
		_events.connectedToServer(this);
	}

	public void nameChange(IConnection connection)
	{ }
	
	public void updateIdentityFromSettings()
	{
		_connection.identifyAs(_settings.friendlyName());
	}
}