package SoundMonitor.Common.common;

import java.io.*;

public interface IConnector
{
	void dataReceived(IConnection connection, IDataPacket data);
	void disconnected(IConnection connection);
	void connectionError(IConnection connection, IOException exception);
	void connectionActive(IConnection connection);
	void nameChange(IConnection connection);
}