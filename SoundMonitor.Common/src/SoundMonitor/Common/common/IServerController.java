package SoundMonitor.Common.common;

public interface IServerController extends Runnable, IDisposable
{
	void start();
	int clients();
}