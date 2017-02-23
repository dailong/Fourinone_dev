package worker_service;

import com.fourinone.Contractor;
import com.fourinone.WareHouse;
import com.fourinone.WorkerLocal;
public class CtorClient extends Contractor
{
	public String sayHello(String name)
	{
		//��װ�������InputString��Ȼ��ʵ�ʵ���ͨ�÷���ӿ�
		WareHouse result = giveTask(new WareHouse("InputString",name));
		//���ط�������Ľ��
		return result.getString("Result");
	}
	
	public WareHouse giveTask(WareHouse inhouse)
	{
		//��ȡ�ṩHello����ĵ�ַ���������ֻ��һ�����������Ƕ��
		WorkerLocal[] wks = getWaitingWorkers("HelloService");
		System.out.println("wks.length:"+wks.length);
		
		//������񲢴���InputString������Ȼ��ȴ������ɺ󷵻�
		WareHouse[] result = doTaskBatch(wks, inhouse);
		
		return result[0];
	}
	
	public static void main(String[] args)
	{
		CtorClient a = new CtorClient();
		String serviceResult = a.sayHello(args[0]);
		System.out.println(serviceResult);
		a.exit();
	}
}