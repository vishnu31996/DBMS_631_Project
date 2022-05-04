package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.RequestDispatcher;

public class RegistrationDAO extends OracleCon {
	static AtomicLong idCounter = new AtomicLong(200);
	@SuppressWarnings("finally")
	public int addRegistration(User user) throws SQLException, InputMismatchException {
		Connection con = null;
		int i = 0;
		try {
			System.out.println(user.getuPhone() + "\n" + generate_UID());
			con = createConnection();
			con.setAutoCommit(true);
			PreparedStatement st = con.prepareStatement(
					"insert into CUSTOMER (Status,LName,FName,Email,Address,Phone,CID)  values(?,?,?,?,?,?,?)");
			st.setString(1, user.getSILVER_AND_ABOVE());
			st.setString(2, user.getuLname());
			st.setString(3, user.getuFname());
			st.setString(4, user.getUid());
			st.setString(5, user.getuAddress());
			st.setString(6, user.getuPhone());
			user.setuCID(generate_UID());
			st.setString(7, user.getuCID());
			System.out.println(user.getuPhone() + "\n" + generate_UID());
			i = st.executeUpdate();
			if (i == 0) {
				System.out.println("Not added Registration page1");

			} else {
				System.out.println(" Reg1 Added Successfully");
				PreparedStatement st_1 = con
						.prepareStatement("insert into Login (USERID,PASS,USER_ROLE)values(?,MD5(?),?)");
				st_1.setString(1, user.getUid());
				st_1.setString(2, user.getUpass());
				st_1.setString(3, user.getUrole());
				int count = st_1.executeUpdate();
				if (count < 1)
					throw new Exception("Error in Login Table");

				PreparedStatement st_2 = con.prepareStatement(
						"insert into reset_pass (userid,security_question,security_answerCID)values(?,?,MD5(?))");
				st_2.setString(1, user.getUid());
				st_2.setString(2, user.getSecurityQ());
				st_2.setString(3, user.getSecurityA());
				st_2.executeUpdate();

				PreparedStatement st_3 = con
						.prepareStatement("insert into SILVER_AND_ABOVE (CID,Credit_Line) VALUES(?,?) ");
				st_3.setString(1, user.getuCID());
				st_3.setString(2, user.getSILVER_AND_ABOVE());
				st_3.executeUpdate();
				i = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			con.close();
			return i;
		}
	}

	private String generate_UID() {
		
		long timestamp = System.currentTimeMillis();
		long nextLong = idCounter.incrementAndGet();
		String random = String.valueOf(timestamp) + String.valueOf(nextLong);

		return random.substring(6, 15);
	}

}
