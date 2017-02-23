package sort;
import com.fourinone.Contractor;
import com.fourinone.WareHouse;
import com.fourinone.WorkerLocal;
import java.util.Date;

public class SortCtor extends Contractor
{
	public WareHouse giveTask(WareHouse wh)
	{
		WorkerLocal[] wks = getWaitingWorkers("SortWorker");
		System.out.println("wks.length:"+wks.length+";"+wh);
		
		wh.setObj("step", 1);//1:group;
		doTaskBatch(wks, wh);
		
		wh.setObj("step", 2);//2:merge;
		doTaskBatch(wks, wh);
		
		wh.setObj("step", 3);//3:sort
		WareHouse[] hmarr = doTaskBatch(wks, wh);
		int total=0;
		for(int i=0;i<hmarr.length;i++){
			Object num = hmarr[i].getObj("total");
			if(num!=null)
				total+=(Integer)num;
		}
		wh.setObj("total",total);
		return wh;
	}
	
	public static void main(String[] args)
	{
		Contractor a = new SortCtor();
		WareHouse wh = new WareHouse();
		long begin = (new Date()).getTime();
		a.doProject(wh);
		long end = (new Date()).getTime();
		System.out.println("total:"+wh.getObj("total")+",time:"+(end-begin)/1000+"s");
	}
}