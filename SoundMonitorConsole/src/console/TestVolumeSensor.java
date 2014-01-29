package console;

import java.io.*;
import SoundMonitor.*;

public class TestVolumeSensor implements IVolumeSensor, Runnable
{
	private float _volume = 0;
	private final BufferedReader _reader;

	public TestVolumeSensor() throws IOException
	{
		_reader = new BufferedReader(new InputStreamReader(System.in));
		
		new Thread(this).start();
	}
	
	public void dispose()
	{ 
	}

	public float getVolume()
	{
		return _volume;
	}

	public void run()
	{
		String line;
		try
		{
			while ((line = _reader.readLine()) != null)
			{
				_volume = Float.parseFloat(line);
				System.out.println("Volume changed to " + _volume);
			}
		}
		catch (IOException exc)
		{
		}
	}
}
