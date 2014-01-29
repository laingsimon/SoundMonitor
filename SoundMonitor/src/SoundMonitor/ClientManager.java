package SoundMonitor;

public class ClientManager implements IClientManager
{
	private final IClientEvents _events;
	private final IClientSettings _settings;
	private ISoundClient _instance = null;

	public ClientManager(
		IClientEvents events,
		IClientSettings settings)
	{
		_events = events;
		_settings = settings;
	}
	
	public ISoundClient connect()
	{
		if (_instance != null)
			return _instance;
		
		_instance = new SoundClient(_events, _settings);
		return _instance;
	}
	
	public void disconnect()
	{
		if (_instance == null)
			return;
		
		_instance.dispose();
		_instance = null;
	}
	
	public void reconnect()
	{
		disconnect();
		connect();
	}
	
	public void dispose()
	{
		disconnect();
		_settings.dispose();
	}
}
