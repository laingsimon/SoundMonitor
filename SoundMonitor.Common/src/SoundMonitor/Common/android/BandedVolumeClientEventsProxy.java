package SoundMonitor.Common.android;
import java.io.IOException;
import java.util.ArrayList;

import SoundMonitor.Common.common.IClientEvents;
import SoundMonitor.Common.common.ISoundClient;

public final class BandedVolumeClientEventsProxy implements IClientEvents
{
	private ArrayList<VolumeBand> _bands;
	private final IBandedVolumeClientEvents _events;

	public BandedVolumeClientEventsProxy(ArrayList<VolumeBand> bands, IBandedVolumeClientEvents events)
	{
		_bands = bands;
		_events = events;
	}
	
	public void connectedToServer(ISoundClient client)
	{
		_events.connectedToServer(client);
	}

	public void disconnectedFromServer(ISoundClient client)
	{
		_events.disconnectedFromServer(client);
	}

	public void connectionError(ISoundClient client, IOException error)
	{
		_events.connectionError(client, error);
	}

	public void volumeUpdate(ISoundClient soundClient, float volume)
	{
		_events.volumeUpdate(soundClient, volume);
		
		BandedVolume band = _findBand(volume);
		_events.volumeUpdate(soundClient, band);
	}

	public void volumeChange(ISoundClient soundClient, float lastVolume, float volume)
	{
		_events.volumeChange(soundClient, lastVolume, volume);
		
		BandedVolume lastBand = _findBand(lastVolume);
		BandedVolume newBand = _findBand(volume);
		
		if (newBand.band() != lastBand.band())
			_events.volumeChange(soundClient, lastBand, newBand);
	}
	
	private BandedVolume _findBand(float volume)
	{
		for (VolumeBand band : _bands)
		{
			if (band.isVolumeWithinBand(volume))
				return new BandedVolume(volume, band);
		}
		
		return new BandedVolume(volume);
	}
	
	public void updateBands(ArrayList<VolumeBand> bands)
	{
		_bands = bands;
	}
}
