package SoundMonitor.Common.common;

public interface IClientManager extends IDisposable
{
	ISoundClient connect();
	void disconnect();
	void reconnect();
}