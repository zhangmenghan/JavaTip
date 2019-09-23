package remote.procedure.call.service;

public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHi(String name) {
		return "hi," + name;
	}

}
