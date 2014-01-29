package SoundMonitor.Common.common;

public class DataPacket implements IDataPacket
{
	private final String _data;

	public DataPacket(String line)
	{
		_data = line.trim();
	}

	public DataPacket(float data)
	{
		_data = String.valueOf(data);
	}

	public String toString()
	{
		return _data + "\r\n";
	}

	public float floatValue()
	{
		return Float.parseFloat(_data);
	}
}
