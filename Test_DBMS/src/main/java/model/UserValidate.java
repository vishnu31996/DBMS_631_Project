package model;

import java.util.List;

public class UserValidate {
	public static void loginVerify(User user,List<String> errors) {
		if(user.getUid()==null || user.getUid().isEmpty()) {
			errors.add("User id should not be empty");
		}
		if(user.getUpass()==null || user.getUpass().isEmpty()) {
			errors.add("User Password should not be empty");
		}
	}

	public static void checkforgotpassword(User user, List<String> errors) {
		
	}

	public static void updatePswdCheckValidate(User user, List<String> errors) {
			
	}

}
