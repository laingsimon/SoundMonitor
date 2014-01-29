package SoundMonitor.Common.common;

import java.net.InetAddress;

public interface IClientSettings extends ISettings
{
	InetAddress server();
	String friendlyName();
}