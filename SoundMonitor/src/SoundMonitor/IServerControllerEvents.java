package SoundMonitor;

import java.io.IOException;

public interface IServerControllerEvents
{
	void enteredBurstMode(IServerController server, float newVolume, float oldVolume);
	void extendedBurstMode(IServerController server, float newVolume, float oldVolume);
	void exitedBurstMode(IServerController server);
	void serverControllerError(IServerController server, Exception error);
	void clientConnected(IServerController server, IConnection connection);
	void clientDisconnected(IServerController server, IConnection connection);
	void dataReceived(IServerController server, IConnection client, IDataPacket data);
	void connectionError(IServerController server, IConnection client, IOException error);
	boolean isVolumeOverThreshold(float volume, float lastVolume);
	void connectionActive(IServerController _serverController);
	void nameChange(IServerController _serverController, IConnection client);
}
