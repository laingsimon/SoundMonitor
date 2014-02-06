package SoundMonitor;

public interface IServerControllerSettings extends IServerSettings
{
	int normalModeSpeed();
	int burstModeSpeed();
	int burstModeDuration();
	boolean verbose();
}