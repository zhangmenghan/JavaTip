package remote.procedure.call.service;

public interface Server {

	public void start();
	public void stop();
	
	public void register(Class service,Class serviceImpl);
	
}
