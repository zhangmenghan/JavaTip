package remote.procedure.call.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
	
	//获取代表服务端接口的动态代理对象(HelloService)
	//serviceName:请求的接口名
	//addr:IP 端口
	@SuppressWarnings("unchecked")
	public static <T> T getRemoteProxyObj(Class serviceInterface,InetSocketAddress addr) {
		//类加载器：需要代理的哪个类
		//需要代理的对象,具备哪些方法  --接口
		//
		return (T)Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[] {serviceInterface}, new InvocationHandler() {
			//proxy  代理的对象
			//method  哪个方法
			//args  参数列表
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				ObjectOutputStream output = null;
				ObjectInputStream input = null;
				try {
					//客户端向服务端发送请求:请求一个具体的接口
					Socket socket = new Socket();
					socket.connect(addr);
					output = new ObjectOutputStream(socket.getOutputStream());//发送通过序列化流
					//接口名 方法名  writeUTF
					output.writeUTF(serviceInterface.getName());
					output.writeUTF(method.getName());
					//方法参数 方法参数的类型
					output.writeObject(method.getParameterTypes());
					output.writeObject(args);
					
					//等待服务端处理
					//接受服务端处理后的返回值
					input = new ObjectInputStream(socket.getInputStream());
					return input.readObject();//客户端-服务端->返回值
				}catch(Exception e) {
					e.printStackTrace();
					return null;
				}finally {
					try {
						if(output != null) {
							output.close();
						}
						if(output != null) {
							input.close();
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

}
