package SoundMonitor.Common.android;

import SoundMonitor.Common.common.IClientEvents;
import SoundMonitor.Common.common.ISoundClient;

public interface IBandedVolumeClientEvents extends IClientEvents
{
	void volumeUpdate(ISoundClient soundClient, BandedVolume band);
	void volumeChange(ISoundClient soundClient, BandedVolume lastBand, BandedVolume newBand);
}
