package console;

import java.util.ArrayList;

import SoundMonitor.Common.android.*;
import SoundMonitor.Common.common.*;

public class Client
{
	private static IClientManager _client;
	private static BandedVolumeClientEventsProxy _events;
	private static ArrayList<VolumeBand> _bands;
	private static IClientSettings _settings;

	public static void main(String[] args)
	{
		_bands = new ArrayList<VolumeBand>();
		_bands.add(new TestVolumeBand(0, 10, "0-10"));
		_bands.add(new TestVolumeBand(10, 15, "10-15"));
		_bands.add(new TestVolumeBand(15, 20, "15-20"));
		_events = new BandedVolumeClientEventsProxy(_bands, new TestClientEvents());
		_settings = new TestClientSettings();
		_client = new ClientManager(_events, _settings);
		_client.connect();
	}
}
