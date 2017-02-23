package stream;

import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;

public class StreamWorkerB extends MigrantWorker{
	public static void main(String[] args) {
		StreamWorkerB wd = new StreamWorkerB();
		wd.waitWorking(args[0], Integer.parseInt(args[1]), "StreamWorkerB");
	}
	
	@Override
	protected WareHouse doTask(WareHouse inhouse) {
		System.out.println(inhouse);
		inhouse.put("msg", inhouse.getString("msg") + ",from StreamWorkerB!");
		return inhouse;
	}
}
