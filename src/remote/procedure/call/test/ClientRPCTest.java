package remote.procedure.call.test;

import java.net.InetSocketAddress;

import remote.procedure.call.client.Client;
import remote.procedure.call.service.HelloService;

public class ClientRPCTest {
	public static void main(String[] args) throws ClassNotFoundException {
		HelloService service = Client.getRemoteProxyObj(Class.forName("remote.procedure.call.service.HelloService"), new InetSocketAddress("127.0.0.1", 9999));
		System.out.println(service.sayHi("zmh"));
	}
}
