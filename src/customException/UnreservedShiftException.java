package customException;

@SuppressWarnings("serial")
public class UnreservedShiftException extends Exception{
	
	private String stringShift;
	
	public UnreservedShiftException(String stringShift) {
		this.stringShift=stringShift;
	}
	
	@Override
	public String getMessage() {
		return"Shift "+stringShift+" has not been reserved";
	}

}
