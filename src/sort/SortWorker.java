package sort;
import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;
import com.fourinone.Workman;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Collections;

public class SortWorker extends MigrantWorker
{
	private final int rammax;//1000000 �������ݵ�ȡֵ��Χ���ֵ
	private int totalmax;//�������ݵ�������ÿ�����ˣ�
	private int total = 0;//��¼��ǰ����������ݵ�����
	private HashMap<Integer,List<Integer>> wharr = new HashMap<Integer,List<Integer>>();//������ŷ�����Ϣ
	private Random rad = new Random();
	private int wknum=-1;
	private int index=-1;
	private Workman[] wms = null;

	public SortWorker(int totalmax, int rammax)
	{
		this.totalmax = totalmax;
		this.rammax = rammax;
	}
	
	public Integer[] getNumber()
	{
		if(total++<totalmax){
			int thenum = rad.nextInt(rammax);
			int numi = (thenum*wknum)/rammax;//ͨ���Թ�������ȡģ��ȡ����
			return new Integer[]{numi,thenum};
		}
		else return new Integer[]{-1,-1};
	}
		
	public WareHouse doTask(WareHouse wh)
	{
		int step = (Integer)wh.getObj("step");
		if(wms==null){
			wms = getWorkerAll();
			wknum = wms.length;
		}
		index = getSelfIndex();
		System.out.println("wknum:"+wknum+";step:"+step);
		WareHouse resultWh = new WareHouse("ok",1);
		
		if(step==1){
			Integer[] num = null;
			while(true){
				num = getNumber();
				if(num[0]!=-1){
					List<Integer> arr = wharr.get(num[0]);//ȡ���÷����list
					if(arr==null)
						arr = new ArrayList<Integer>();
					arr.add(num[1]);
					wharr.put(num[0], arr);
				}
				else break;
			}
		}else if(step==2){
			for(int i=0;i<wms.length;i++){
				if(i!=index&&wharr.containsKey(i)){
					List<Integer> othernum = wharr.remove(i);
					Workman wm = wms[i];
					System.out.println(i+"-receive:"+wm.receive(new WareHouse(i, othernum)));//���������Լ��ķ��෢����������
				}
			}
		}else if(step==3){
			List<Integer> curlist = wharr.get(index);
			Collections.sort(curlist);//�������Լ���������ݽ����ڴ�������
			System.out.println(curlist);
			System.out.println(curlist.size());
			resultWh.setObj("total",curlist.size());
		}	
		return resultWh;
	}
	
	protected boolean receive(WareHouse inhouse)
	{
		List<Integer> thisnum = wharr.get(index);
		thisnum.addAll((List<Integer>)inhouse.get(index));
		return true;
	}
	
	public static void main(String[] args)
	{
		SortWorker mw = new SortWorker(Integer.parseInt(args[2]),Integer.parseInt(args[3]));
		mw.waitWorking(args[0],Integer.parseInt(args[1]),"SortWorker");
	}
}