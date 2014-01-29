package console;

import java.io.IOException;

import SoundMonitor.*;

public class TestServerControllerEvents implements IServerControllerEvents
{
	private float _volumeChangeThreshold;

	public TestServerControllerEvents(float threshold)
	{
		_volumeChangeThreshold = threshold;
	}
	
	public void enteredBurstMode(IServerController server, float newVolume, float oldVolume)
	{
		System.out.println("entered burst mode (" + oldVolume + " -> " + newVolume + ")");
	}

	public void extendedBurstMode(IServerController server, float newVolume, float oldVolume)
	{
		System.out.println("extended burst mode (" + oldVolume + " -> " + newVolume + ")");
	}

	public void exitedBurstMode(IServerController server)
	{
		System.out.println("exited burst mode");
	}

	public void serverControllerError(IServerController server, Exception error)
	{
		System.out.println("error: " + error.toString());
	}

	public void clientConnected(IServerController server, IConnection connection)
	{
		System.out.println("client connected - " + connection.friendlyName());
	}

	public void clientDisconnected(IServerController server, IConnection connection)
	{
		System.out.println("client disconnected - " + connection.friendlyName());
	}

	public void dataReceived(IServerController server, IConnection client, IDataPacket data)
	{
		System.out.println("data received - " + client.friendlyName() + ": " + data.toString());
	}

	public void connectionError(IServerController server, IConnection client, IOException error)
	{
		System.out.println("connection error - " + client.friendlyName() + ": " + error.toString());
	}

	public boolean isVolumeOverThreshold(float volume, float lastVolume)
	{
		float volumeChange = Math.abs(lastVolume - volume);
		return volumeChange >= _volumeChangeThreshold;
	}

	public void connectionActive(IServerController _serverController)
	{
		System.out.println("connection active");
	}

	public void nameChange(IServerController _serverController, IConnection client)
	{
		System.out.println("name change - " + client.friendlyName());
	}
}
