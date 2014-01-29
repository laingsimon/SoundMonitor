package SoundMonitor;

public interface IClientManager extends IDisposable
{
	ISoundClient connect();
	void disconnect();
	void reconnect();
}