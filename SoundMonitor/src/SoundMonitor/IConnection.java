package SoundMonitor;

public interface IConnection extends Runnable, IDisposable
{
	void startThread();
	void send(IDataPacket data) throws ObjectDisposedException;
	String friendlyName();
	void identifyAs(String friendlyName);
}