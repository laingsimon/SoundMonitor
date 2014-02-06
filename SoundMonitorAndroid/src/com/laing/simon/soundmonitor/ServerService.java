package com.laing.simon.soundmonitor;

import java.io.IOException;

import SoundMonitor.*;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

//http://www.vogella.com/tutorials/AndroidServices/article.html
public class ServerService extends Service
{
	private static IServerController _server = null;
	private static IVolumeSensor _volume = null;
	private IServerControllerEvents _events = null;
	private IServerControllerSettings _settings = null;
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		IServerController server = _createServerController();
		
		if (server == null)
			return -1;
		
		server.start();
		
		return Service.START_STICKY;  //auto restart
	}
	
	private IServerController _createServerController()
	{
		if (_server != null)
			return _server;
		
		IVolumeSensor volume = _createVolumeSensor();
		_events = new ServerControllerEvents();
		_settings = new ServerControllerSettings();
		try
		{
			_server = new ServerController(volume, _events, _settings);
		}
		catch (IOException e)
		{
			return null;
		}
		return _server;
	}

	private static IVolumeSensor _createVolumeSensor()
	{
		if (_volume == null)
			_volume = new VolumeSensor();
		return _volume;
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
