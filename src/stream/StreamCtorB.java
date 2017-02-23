package stream;

import com.fourinone.Contractor;
import com.fourinone.WareHouse;
import com.fourinone.WorkerLocal;

public class StreamCtorB extends Contractor {
	 
	@Override
	public WareHouse giveTask(WareHouse inhouse) {
		WorkerLocal[] wks = getWaitingWorkers("StreamWorkerB");
		System.out.println("wks.length :" + wks.length);
		
		WareHouse[] hmarr = doTaskBatch(wks, inhouse);
		WareHouse result = new WareHouse();
		result.put("B1", hmarr[0]);
		result.put("B2", hmarr[1]);
		return result;
	}
}
