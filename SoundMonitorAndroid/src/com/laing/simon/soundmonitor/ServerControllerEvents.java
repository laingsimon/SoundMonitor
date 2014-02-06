package com.laing.simon.soundmonitor;

import java.io.IOException;

import SoundMonitor.*;

public class ServerControllerEvents implements IServerControllerEvents
{
	@Override
	public void enteredBurstMode(IServerController server, float newVolume,
			float oldVolume)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void extendedBurstMode(IServerController server, float newVolume,
			float oldVolume)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitedBurstMode(IServerController server)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serverControllerError(IServerController server, Exception error)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientConnected(IServerController server, IConnection connection)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientDisconnected(IServerController server,
			IConnection connection)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dataReceived(IServerController server, IConnection client,
			IDataPacket data)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectionError(IServerController server, IConnection client,
			IOException error)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isVolumeOverThreshold(float volume, float lastVolume)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void connectionActive(IServerController _serverController)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nameChange(IServerController _serverController,
			IConnection client)
	{
		// TODO Auto-generated method stub
		
	}

}
