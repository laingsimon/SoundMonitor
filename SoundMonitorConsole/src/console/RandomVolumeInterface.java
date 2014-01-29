package console;

import java.util.Random;
import SoundMonitor.*;

public class RandomVolumeInterface implements IVolumeSensor
{
	private final Random _random;
	private final int _maxValue;
	private float _lastVolume = -1;

	public RandomVolumeInterface(int maxValue)
	{
		_maxValue = maxValue;
		_random = new Random();
	}
	
	public void dispose()
	{	}

	public float getVolume()
	{
		if (_lastVolume > -1)
		{
			boolean staySame = _random.nextBoolean();
			if (staySame)
				return _lastVolume;
		}
		
		return (_lastVolume = _random.nextInt(_maxValue));
	}

}
