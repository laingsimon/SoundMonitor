package SoundMonitor.Common.common;

import java.io.IOException;

public interface ISoundServer extends IConnector, IDisposable, Runnable
{
	void close() throws IOException;
	void send(IDataPacket data) throws ObjectDisposedException;
	int clients();
}
