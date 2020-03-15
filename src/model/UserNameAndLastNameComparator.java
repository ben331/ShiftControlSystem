package model;

import java.util.Comparator;

public class UserNameAndLastNameComparator implements Comparator<User>{

	@Override
	public int compare(User u1, User u2) {
		int diference = u1.getLastName().compareTo(u2.getLastName());
		if(diference==0)
			diference = u1.getName().compareTo(u2.getName());
		return diference;
	}
}
