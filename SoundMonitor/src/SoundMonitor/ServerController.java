package SoundMonitor;

import java.io.IOException;

public class ServerController implements IServerController
{
	private final ISoundServer _server;
	private final IVolumeSensor _volume;
	private final IServerControllerEvents _events;
	private boolean _run = true;
	private IServerControllerSettings _settings;
	
	public ServerController(
		IVolumeSensor volume,				//volume interface
		IServerControllerEvents events,		//event consumers
		IServerControllerSettings settings	//settings
	) throws IOException
	{
		_events = events;
		_server = new SoundServer(new ServerControllerServerEvents(events, this), settings);  //communicate with clients
		_volume = volume;
		_settings = settings;
	}
	
	public void run()
	{
		if (_settings.verbose())
			System.out.println("Server controller running...");
		
		if (!_run || _server == null)
		{
			_events.serverControllerError(this, new ObjectDisposedException());
			return;
		}
	
		float lastVolume = -1;
		int burstMode = 0;
	
		new Thread(_server).start();
		while (_run)
		{
			try
			{
				float volume = _volume.getVolume();
				boolean volumeChangeOverThreshold = _events.isVolumeOverThreshold(volume, lastVolume);
				
				if (volumeChangeOverThreshold)
				{
					if (burstMode == 0)
						_events.enteredBurstMode(this, volume, lastVolume);
					else
						_events.extendedBurstMode(this, volume, lastVolume);
					
					burstMode = _settings.burstModeDuration();
				}
				
				_server.send(new DataPacket(volume));
				Thread.sleep(burstMode > 0 
						? _settings.burstModeSpeed() 
						: _settings.normalModeSpeed());
				
				if (burstMode > 0)
				{
					burstMode--;
					if (burstMode == 0)
						_events.exitedBurstMode(this);
				}
				lastVolume = volume;
			}
			catch (InterruptedException exc) { } 
			catch (ObjectDisposedException exc)
			{
				dispose();
				_events.serverControllerError(this, exc);
			}
		}
	}
	
	public void dispose()
	{
		_run = false;
		_server.dispose();
		_volume.dispose();
	}

	public void start()
	{
		new Thread(this).start();
	}
	
	public int clients()
	{
		return _server.clients();
	}
}