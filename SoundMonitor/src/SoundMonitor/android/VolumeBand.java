package SoundMonitor.android;

public abstract class VolumeBand
{
	private float _lower;
	private float _upper;

	public VolumeBand(float lower, float upper)
	{
		_lower = lower;
		_upper = upper;
	}
	
	public boolean isVolumeWithinBand(float volume)
	{
		return volume >= _lower && volume < _upper;
	}
}
