package console;

import SoundMonitor.android.*;

public class TestVolumeBand extends VolumeBand
{
	private final String _name;

	public TestVolumeBand(float lower, float upper, String name)
	{
		super(lower, upper);
		_name = name;
	}
	
	public String toString()
	{
		return _name;
	}
}
