package SoundMonitor.Common.common;

import java.io.*;

public interface IServerEvents
{
	void clientConnected(ISoundServer server, IConnection connection);
	void clientDisconnected(ISoundServer server, IConnection connection);
	void dataReceived(ISoundServer server, IConnection client, IDataPacket data);
	void connectionError(ISoundServer server, IConnection client, IOException error);
	void serverError(ISoundServer server, Exception exception);
	void connectionActive(ISoundServer soundServer);
	void nameChange(ISoundServer soundServer, IConnection client);
}