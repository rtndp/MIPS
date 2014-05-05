package iCache;

import config.ConfigManager;
import program.ProgramManager;
import stages.CPU;
import instructions.Instruction;

/**
 * Called by main while fetching instructions if instructions not present in
 * cache keep track of when to populate the instruction in the cache
 * 
 * 
 * 
 * Instruction isInstructionInCache(int pc){ if instruction is not found in the
 * cache track of when to populate the instruction in the cache by marking it in
 * the tracker return null; else return instruction from cache
 * 
 * }
 */

public class ICacheManager {

	int lastRequestInstruction;
	int lastRequestCycle;
	int clockCyclesToBlock;
	int delayToBus;
	boolean cacheHit;

	private static volatile ICacheManager instance;

	public static ICacheManager getInstance() {
		if (null == instance)
			synchronized (ICacheManager.class) {
				if (null == instance)
					instance = new ICacheManager();
			}

		return instance;
	}

	private ICacheManager() {

		resetValues();

	}

	private void resetValues() {

		lastRequestInstruction = -1;
		lastRequestCycle = -1;
		clockCyclesToBlock = -1;
		delayToBus = -1;
		cacheHit = false;

	}

	public Instruction getInstructionFromCache(int pc) throws Exception {

		if (lastRequestInstruction == -1) {

			if (ICache.getInstance().isInstructionInCache(pc)) {

				lastRequestInstruction = pc;
				lastRequestCycle = CPU.CLOCK;
				clockCyclesToBlock = ConfigManager.instance.ICacheLatency - 1;
				cacheHit = true;

				if (clockCyclesToBlock == 0) {

					resetValues();
					return ProgramManager.instance.getInstructionAtAddress(pc);

				} else
					return null;

			} else {

				lastRequestInstruction = pc;
				lastRequestCycle = CPU.CLOCK;
				delayToBus = MemoryBusManager.getInstance().getDelayFromBCM();
				clockCyclesToBlock = 2
						* (ConfigManager.instance.ICacheLatency + ConfigManager.instance.MemoryLatency)
						+ delayToBus - 1;

				return null;

			}

		} else {

			if (CPU.CLOCK - lastRequestCycle == clockCyclesToBlock) {
				if (!cacheHit)
					ICache.getInstance().populateICache(pc);
				resetValues();
				return ProgramManager.instance.getInstructionAtAddress(pc);

			} else
				return null;

		}

	}
}