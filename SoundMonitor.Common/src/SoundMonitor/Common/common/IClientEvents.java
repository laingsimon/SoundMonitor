package SoundMonitor.Common.common;

import java.io.IOException;

public interface IClientEvents
{
	void connectedToServer(ISoundClient client);
	void disconnectedFromServer(ISoundClient client);
	void connectionError(ISoundClient client, IOException error);
	void volumeUpdate(ISoundClient soundClient, float volume);
	void volumeChange(ISoundClient soundClient, float lastVolume, float volume);
}