package SoundMonitor.Common.common;

public interface IServerControllerSettings extends IServerSettings
{
	int normalModeSpeed();
	int burstModeSpeed();
	boolean verbose();
}