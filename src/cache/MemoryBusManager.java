package cache;

public class MemoryBusManager
{

	private static volatile MemoryBusManager instance;

	public static MemoryBusManager getInstance() {
		if (null == instance)
			synchronized (MemoryBusManager.class) {
				if (null == instance)
					instance = new MemoryBusManager();
			}

		return instance;
	}
	
	
	public int getDelayFromBCM(){
		
		return 0;
		
	}
	
	
}
