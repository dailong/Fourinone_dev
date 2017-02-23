package stream;

import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;

public class StreamWorkerA extends MigrantWorker{
	public static void main(String[] args) {
		StreamWorkerA wd = new StreamWorkerA();
		wd.waitWorking(args[0], Integer.parseInt(args[1]), "StreamWorkerA");
	}
	
	@Override
	protected WareHouse doTask(WareHouse inhouse) {
		System.out.println(inhouse);
		StreamCtorB sc = new StreamCtorB();
		WareHouse wh = new WareHouse();
		wh.put("msg", inhouse.getString("msg") + ",from StreamWorkerA!");
		WareHouse w = sc.giveTask(wh);
		sc.exit();
		return w;
	}
}
