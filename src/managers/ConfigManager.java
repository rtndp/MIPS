package managers;

public class ConfigManager {

	public static final ConfigManager instance = new ConfigManager();

	private ConfigManager() {

	}

	public int FPAdderLatency = 0;
	public int FPMultLatency = 0;
	public int FPDivideLatency = 0;
	public boolean FPAdderPipelined = false;
	public boolean FPMultPipelined = false;
	public boolean FPDividerPipelined = false;
	public int MemoryLatency = 0;
	public int ICacheLatency = 0;
	public int DCacheLatency = 0;

}
