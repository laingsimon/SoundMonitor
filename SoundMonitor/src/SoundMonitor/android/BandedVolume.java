package SoundMonitor.android;

public class BandedVolume
{
	private final float _volume;
	private final VolumeBand _band;

	public BandedVolume(float volume, VolumeBand band)
	{
		_volume = volume;
		_band = band;
	}

	public BandedVolume(float volume)
	{
		_volume = volume;
		_band = null;
	}
	
	public float volume()
	{
		return _volume;
	}
	
	public VolumeBand band()
	{
		return _band;
	}
}
