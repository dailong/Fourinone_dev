package worker_service;

import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;

public class ServiceWorker extends MigrantWorker
{	
	public WareHouse doTask(WareHouse inhouse)
	{
		//ȡ��������ֵ
		String inputstring = inhouse.getString("InputString");
		//�յ���������󣬷���hello
		return new WareHouse("Result", inputstring+",hello");
	}
	
	public static void main(String[] args)
	{
		ServiceWorker mw = new ServiceWorker();
		//��������<SERVICE>true</SERVICE>
		mw.waitWorking("localhost",Integer.parseInt(args[0]),"HelloService");
	}
}