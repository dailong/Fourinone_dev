package worker_service;

import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;

public class ServiceWorker extends MigrantWorker
{	
	public WareHouse doTask(WareHouse inhouse)
	{
		//取出参数的值
		String inputstring = inhouse.getString("InputString");
		//收到服务请求后，返回hello
		return new WareHouse("Result", inputstring+",hello");
	}
	
	public static void main(String[] args)
	{
		ServiceWorker mw = new ServiceWorker();
		//启动服务，<SERVICE>true</SERVICE>
		mw.waitWorking("localhost",Integer.parseInt(args[0]),"HelloService");
	}
}