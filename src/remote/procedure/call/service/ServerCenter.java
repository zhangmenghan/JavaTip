package remote.procedure.call.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerCenter implements Server {
	//map:服务端所有可供客户端访问的接口，都注册到该map中
	private static HashMap<String, Class> serviceRegister = new HashMap<>();
	private static int port;
	//连接池:连接池中存在多个连接对象,每个连接对象都可以处理一个用户请求
	private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	private boolean isRunning;
	
	public ServerCenter(int port) {
		this.port = port;
	}
	
	//开启服务端服务
	@Override
	public void start() {
		ServerSocket server = null;
		
		try {
			server = new ServerSocket();
			server.bind(new InetSocketAddress(port));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		isRunning = true;
		while(true) {
			System.out.println("start server...");
			Socket socket = null;
			try {
				socket = server.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			executor.execute(new ServiceTask(socket));
		}
	}

	@Override
	public void stop() {
		isRunning = false;
		executor.shutdown();
	}

	@Override
	public void register(Class service,Class serviceImpl) {
		serviceRegister.put(service.getName(), serviceImpl);
	}
	
	private static class ServiceTask implements Runnable{
		private Socket socket;
		public ServiceTask() {
		}
		public ServiceTask(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			ObjectInputStream input = null;
			ObjectOutputStream output = null;
			
			try {
				
				//接收到客户端连接及请求,处理该请求
				input = new ObjectInputStream(socket.getInputStream());
				String serviceName = input.readUTF();
				String methodName = input.readUTF();
				Class[] parameterTypes = (Class[])input.readObject();
				Class[] arguments = (Class[])input.readObject();
				
				//根据客户请求,到map中找到与其对应具体的接口
				Class serviceClass = serviceRegister.get(serviceName);
				Method method = serviceClass.getMethod(methodName, parameterTypes);
				//执行该方法
				Object result = method.invoke(serviceClass.newInstance(), arguments);
				
				//在向客户端将方法执行完毕的返回值传给客户端
				output = new ObjectOutputStream(socket.getOutputStream());
				output.writeObject(result);
			}catch(Exception e) {
				e.printStackTrace();
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
	}
}
