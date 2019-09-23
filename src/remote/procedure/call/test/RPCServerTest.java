package remote.procedure.call.test;

import remote.procedure.call.service.HelloService;
import remote.procedure.call.service.HelloServiceImpl;
import remote.procedure.call.service.Server;
import remote.procedure.call.service.ServerCenter;

public class RPCServerTest {
	public static void main(String[] args) {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				//服务中心
				Server server = new ServerCenter(9999);
				server.register(HelloService.class, HelloServiceImpl.class);
				server.start();
			}
		}).start();;
	}
}
