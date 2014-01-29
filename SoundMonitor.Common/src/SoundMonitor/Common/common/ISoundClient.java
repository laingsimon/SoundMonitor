package SoundMonitor.Common.common;

public interface ISoundClient extends IDisposable, IConnector
{
	void send(IDataPacket data) throws ObjectDisposedException;
	void updateIdentityFromSettings();
}