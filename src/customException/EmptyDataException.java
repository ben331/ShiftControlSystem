package customException;

@SuppressWarnings("serial")
public class EmptyDataException extends Exception{
	
	private String emptyFields;
	
	public EmptyDataException(String id, String idType, String name, String lastNames) {
		emptyFields="";
		determineEmptyFields(id, idType, name, lastNames);
	}
	
	public void determineEmptyFields(String id,String idType, String name, String lastNames) {
		if(id.equals(""))
			emptyFields+="Empty id, ";
		if(idType.equals(""))
			emptyFields+="Empty idType, ";
		if(name.equals(""))
			emptyFields+="Empty name, ";
		if(lastNames.equals(""))
			emptyFields+="Empty lastNames.";
	}
	
	@Override
	public String getMessage() {
		return "Attemp to create a user with "+emptyFields;
	}

}
