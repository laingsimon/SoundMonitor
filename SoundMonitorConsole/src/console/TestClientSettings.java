package console;

import java.net.InetAddress;
import java.net.UnknownHostException;
import SoundMonitor.*;

public class TestClientSettings extends Settings implements IClientSettings
{
	private static int _count = 0;
	
	public InetAddress server()
	{
		try
		{
			return InetAddress.getLocalHost();
		}
		catch (UnknownHostException e)
		{
			return null;
		}
	}

	public String friendlyName()
	{
		return "client-name-" + ++_count;
	}
	
	public void dispose()
	{}
}
