package console;

import java.io.IOException;

import SoundMonitor.Common.android.BandedVolume;
import SoundMonitor.Common.android.IBandedVolumeClientEvents;
import SoundMonitor.Common.common.IDataPacket;
import SoundMonitor.Common.common.ISoundClient;

public class TestClientEvents implements IBandedVolumeClientEvents
{
	public void dataReceived(ISoundClient client, IDataPacket data)
	{
		System.out.print("CLIENT: data received - " + data.toString());
	}

	public void disconnectedFromServer(ISoundClient client)
	{
		System.out.println("CLIENT: disconnected");
	}

	public void connectionError(ISoundClient client, IOException error)
	{
		System.out.println("CLIENT: connection error - " + error.toString());
	}

	public void connectedToServer(ISoundClient client)
	{
		System.out.println("CLIENT: connection made");
	}

	public void volumeUpdate(ISoundClient soundClient, float volume)
	{
		System.out.println("CLIENT: volume update " + volume);
	}

	public void volumeChange(ISoundClient soundClient, float lastVolume, float volume)
	{
		System.out.println("CLIENT: volume change " + lastVolume + " -> " + volume);
	}

	public void volumeUpdate(ISoundClient soundClient, BandedVolume band)
	{
		System.out.println("CLIENT: volume update " + band.volume() + " (" + band.toString() + ")");
	}

	public void volumeChange(ISoundClient soundClient, BandedVolume lastBand, BandedVolume newBand)
	{
		System.out.println("CLIENT: volume change " + lastBand.volume() + " (" + lastBand.toString() + "( -> " + newBand.volume() + " (" + newBand.toString() + ")");
	}
}
