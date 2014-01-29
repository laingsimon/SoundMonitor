package SoundMonitor;

public class DefaultServerControllerSettings implements IServerControllerSettings
{
	private final IServerSettings _serverSettings;

	public DefaultServerControllerSettings(IServerSettings serverSettings)
	{
		_serverSettings = serverSettings;
	}
	
	public int normalModeSpeed()
	{
		return 1000;
	}
	
	public int burstModeSpeed()
	{
		return 100;
	}

	public boolean verbose()
	{
		return true;
	}

	public int port()
	{
		return _serverSettings.port();
	}
	
	public void dispose()
	{
		_serverSettings.dispose();
	}
}
