package stream;

import com.fourinone.Contractor;
import com.fourinone.WareHouse;
import com.fourinone.WorkerLocal;

public class StreamCtorA extends Contractor {
	public static void main(String[] args) {
		StreamCtorA sc = new StreamCtorA();
		for(int i=0;i<10;i++) {
			WareHouse wh = new WareHouse();
			wh.put("msg", "hello" + i);
			WareHouse w= sc.giveTask(wh);
			System.out.println(w);
		}
		sc.exit();
	}
	 
	@Override
	public WareHouse giveTask(WareHouse inhouse) {
		WorkerLocal[] wks = getWaitingWorkers("StreamWorkerA");
		System.out.println("wks.length :" + wks.length);
		
		WareHouse result = wks[0].doTask(inhouse);
		while(true) {
			if(result.getStatus() != WareHouse.NOTREADY) {
				break;
			}
		}
		return result;
	}
}
