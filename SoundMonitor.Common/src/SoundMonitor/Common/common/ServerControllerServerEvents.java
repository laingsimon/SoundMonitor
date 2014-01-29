package SoundMonitor.Common.common;

import java.io.IOException;

public class ServerControllerServerEvents implements IServerEvents
{
	private final IServerControllerEvents _serverControllerEvents;
	private final IServerController _serverController;
	
	public ServerControllerServerEvents(IServerControllerEvents serverControllerEvents, IServerController serverController)
	{
		_serverControllerEvents = serverControllerEvents;
		_serverController = serverController;
	}
	
	public void clientConnected(ISoundServer server, IConnection connection)
	{
		_serverControllerEvents.clientConnected(_serverController, connection);
	}

	public void clientDisconnected(ISoundServer server, IConnection connection)
	{
		_serverControllerEvents.clientDisconnected(_serverController, connection);
	}

	public void dataReceived(ISoundServer server, IConnection client, IDataPacket data)
	{
		_serverControllerEvents.dataReceived(_serverController, client, data);
	}

	public void connectionError(ISoundServer server, IConnection client, IOException error)
	{
		_serverControllerEvents.connectionError(_serverController, client, error);
	}

	public void serverError(ISoundServer server, Exception exception)
	{
		_serverControllerEvents.serverControllerError(_serverController, exception);
	}

	public void connectionActive(ISoundServer soundServer)
	{
		_serverControllerEvents.connectionActive(_serverController);
	}

	public void nameChange(ISoundServer soundServer, IConnection client)
	{
		_serverControllerEvents.nameChange(_serverController, client);		
	}
}
