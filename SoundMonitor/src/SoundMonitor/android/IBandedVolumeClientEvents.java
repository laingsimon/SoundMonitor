package SoundMonitor.android;

import SoundMonitor.IClientEvents;
import SoundMonitor.ISoundClient;

public interface IBandedVolumeClientEvents extends IClientEvents
{
	void volumeUpdate(ISoundClient soundClient, BandedVolume band);
	void volumeChange(ISoundClient soundClient, BandedVolume lastBand, BandedVolume newBand);
}
