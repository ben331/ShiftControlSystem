package customException;

@SuppressWarnings("serial")
public class DoubleRegistrationException extends Exception{
	
	private String repeatedId;
	
	public DoubleRegistrationException(String id) {
		repeatedId=id;
	}
	
	@Override
	public String getMessage() {
		return "User with the id: "+repeatedId+" is already exists";
	}

}
