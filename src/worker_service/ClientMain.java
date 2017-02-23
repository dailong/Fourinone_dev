package worker_service;

import com.fourinone.BeanContext;
import com.fourinone.StartResult;

public class ClientMain {
	public static void main(String[] args) {
		StartResult<Integer> ctor1 = BeanContext.tryStart("java","-cp","fourinone-src.jar;","worker_service.CtorClient","client1");
		ctor1.print("log/ctor1.log");
		StartResult<Integer> ctor2 = BeanContext.tryStart("java","-cp","fourinone-src.jar;","worker_service.CtorClient","client2");
		ctor2.print("log/ctor2.log");
	}
}
