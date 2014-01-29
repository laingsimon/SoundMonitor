package SoundMonitor;

public interface IServerController extends Runnable, IDisposable
{
	void start();
	int clients();
}