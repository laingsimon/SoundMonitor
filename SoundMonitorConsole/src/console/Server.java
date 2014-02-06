package console;

import java.io.IOException;

import SoundMonitor.*;

public class Server
{
	private static TestServerSettings _settings;

	public static void main(String[] args) throws IOException
	{
		_settings = new TestServerSettings();
		System.out.println("Starting server on port " + _settings.port() + "...");
		
		ServerController server = new ServerController(
				new RandomVolumeInterface(50), 
				new TestServerControllerEvents(20),
				new DefaultServerControllerSettings(_settings));
		
		server.start();
	}
}
