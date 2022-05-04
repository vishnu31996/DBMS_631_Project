package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class UserDOA {

	Connection con = null;

	private void createCon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Newark_IT_DBMS", "root", "myserver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void closeCon() {

		try {
			this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String loginVerify(User user) throws SQLException, InputMismatchException {

		try {
			createCon();
			PreparedStatement st = con.prepareStatement("select * from Login where userId=?");
			st.setString(1, user.getUid());
			// st.setString(2,user.getUpass());
			ResultSet result = st.executeQuery();
			if (result.next()) {
				PreparedStatement st_1 = con.prepareStatement("SELECT IF (? = MD5(?), 'true', 'false') AS valid;");
				st_1.setString(1, result.getString(2));
				st_1.setString(2, user.getUpass());
				ResultSet result_1 = st_1.executeQuery();
				if (result_1.next()) {
					System.out.println(result_1.getString(1));
					if (result_1.getString(1).equalsIgnoreCase("true"))
						user.setUrole(result.getString(3));
					PreparedStatement st_2 = con.prepareStatement("select CID from CUSTOMER where EMAIL=?");
					st_2.setString(1, user.getUid());
					ResultSet result2 = st_2.executeQuery();

					if (result2.next()) {

						user.setuCID(result2.getString(1));
					} else {

						throw new InputMismatchException();
					}
				}
				// user.setUrole(result.getString(3));

				// user.setUname(result.getString(4));
				return user.getuCID();
			} else {

				throw new InputMismatchException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			closeCon();
		}
		return null;
	}

	public int forgotPswdCheck(User user) throws SQLException {
		Connection con = null;
		try {
			createCon();
			PreparedStatement st = con.prepareStatement("select security_question from reset_pass where userid=?");
			st.setString(1, user.getUid());

			ResultSet result = st.executeQuery();

			if (result.next()) {
				user.setUq(result.getString(1));

				return 1;
			}

			else {
				throw new SQLException("User Id Not available");
			}
		} finally {
			closeCon();
		}
	}

	public int forgotPswdCheck1(User user) throws SQLException {
		Connection con = null;
		try {
			createCon();
			PreparedStatement st = con.prepareStatement("select * from atosdb2 where userid=? and security_answer=?");
			st.setString(1, user.getUid());
			st.setString(2, user.getUans());
			ResultSet result = st.executeQuery();

			if (result.next()) {

				return 1;
			}

			else {

				return 0;
			}
		} finally {
			closeCon();
		}
	}

	public void resetPwd(User user) throws SQLException {
		Connection con = null;
		// PrintWriter out= null;
		try {
			createCon();
			PreparedStatement st2 = con.prepareStatement("update Login set pass=? where userid=?");
			st2.setString(1, user.getUnewPassword());
			st2.setString(2, user.getUid());

			int update = st2.executeUpdate();

			if (update > 0) {
				System.out.println("Password has been successfully updated");
			} else {
				System.out.println("Error in data");
			}
		} finally {
			con.commit();
			closeCon();
		}
	}

}
