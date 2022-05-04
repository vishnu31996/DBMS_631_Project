package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProfileDOA extends OracleCon {
	String uid;
	String cid;

	public ProfileDOA(String uid, String cid) {
		this.uid = uid;
		this.cid = cid;
	}

	public void updateShipping_Address(Shipping ship) throws SQLException {
		Connection con = null;

		try {
			con = createConnection();
			PreparedStatement st_1 = con.prepareStatement("select SAName from SHIPPINGADDRESS where CID=?");
			st_1.setString(1, cid);
			ResultSet result = st_1.executeQuery();

			if (result.next()) {
				PreparedStatement st = con.prepareStatement(
						"update SHIPPINGADDRESS set SAName=?,RecepientName=?,Street=?,SNumber=?,City=?,Zip=?,State=?,Country=? WHERE CID=?");
				st.setString(1, ship.getSAName());
				st.setString(2, ship.getRecepientName());
				st.setString(3, ship.getStreet());
				st.setString(4, ship.getSNumber());
				st.setString(5, ship.getCity());
				st.setString(6, ship.getZip());
				st.setString(7, ship.getState());
				st.setString(8, ship.getCountry());
				st.setString(9, cid);

				st.executeUpdate();
			} else {
				PreparedStatement st = con.prepareStatement(
						"insert into SHIPPINGADDRESS (SAName,RecepientName,Street,SNumber,City,Zip,State,Country,CID) VALUES(?,?,?,?,?,?,?,?,?) ");
				st.setString(1, ship.getSAName());
				st.setString(2, ship.getRecepientName());
				st.setString(3, ship.getStreet());
				st.setInt(4, Integer.parseInt(ship.getSNumber()));
				st.setString(5, ship.getCity());
				st.setString(6, ship.getZip());
				st.setString(7, ship.getState());
				st.setString(8, ship.getCountry());
				st.setString(9, cid);

				st.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			con.close();
		}

	}

	public List<Shipping> getShipping_Address() throws SQLException {
		Connection con = null;

		ArrayList<Shipping> shipDet = new ArrayList<>();
		try {
			con = createConnection();
			PreparedStatement st = con.prepareStatement("select * from SHIPPINGADDRESS where CID=?");
			st.setString(1, cid);
			ResultSet result = st.executeQuery();

			while (result.next()) {
				Shipping temp = new Shipping();
				temp.setSAName(result.getString("SAName"));
				temp.setRecepientName(result.getString("RecepientName"));
				temp.setStreet(result.getString("Street"));
				temp.setSNumber(result.getString("SNumber"));
				temp.setCity(result.getString("City"));
				temp.setZip(result.getString("Zip"));
				temp.setState(result.getString("State"));
				temp.setCountry(result.getString("Country"));

				shipDet.add(temp);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			con.close();
		}
		return shipDet;
	}

	
	
	
	public boolean verifyShipingAddress() throws SQLException {
		Connection con = null;

		try {
			con = createConnection();
			PreparedStatement st = con.prepareStatement("select * from SHIPPINGADDRESS where CID=?");
			st.setString(1, cid);
			ResultSet result = st.executeQuery();

			if (result.next()) {

				return true;

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			con.close();
		}
		return false;
	}

	public boolean verifycardDetails() throws SQLException {
		Connection con = null;

		try {
			con = createConnection();
			PreparedStatement st = con
					.prepareStatement("select * from CREDIT_CARD where StoredCardCID=? and STATUS='ACTIVE'");
			st.setString(1, cid);

			ResultSet result = st.executeQuery();

			if (result.next()) {

				return true;

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			con.close();
		}
		return false;
	}

	public List<card> getCREDIT_CARD() throws SQLException {
		Connection con = null;

		ArrayList<card> shipDet = new ArrayList<>();
		try {
			con = createConnection();
			PreparedStatement st = con
					.prepareStatement("select * from CREDIT_CARD where StoredCardCID=? and STATUS='ACTIVE'");
			st.setString(1, cid);

			ResultSet result = st.executeQuery();

			while (result.next()) {
				card temp = new card();
				temp.setCardNumber(result.getString("CARDNUMBER"));
				temp.setCvv(result.getString("SecNumber"));
				temp.setName(result.getString("OwnerName"));
				temp.setCardType(result.getString("CCType"));
				temp.setBilAddress(result.getString("Bil_Address"));
				temp.setExpDate(result.getString("ExpDate"));

				shipDet.add(temp);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			con.close();
		}
		return shipDet;
	}

	public List<User> getCUSTOMER() throws SQLException {
		Connection con = null;

		ArrayList<User> shipDet = new ArrayList<>();
		try {
			con = createConnection();
			PreparedStatement st = con.prepareStatement("select * from CUSTOMER where CID=?");
			st.setString(1, cid);
			ResultSet result = st.executeQuery();

			while (result.next()) {
				User temp = new User();
				temp.setuFname(result.getString("FName"));
				temp.setuLname(result.getString("LName"));
				temp.setUid(result.getString("Email"));
				temp.setuAddress(result.getString("Address"));
				temp.setuPhone(result.getString("Phone"));
				temp.setSILVER_AND_ABOVE(result.getString("Status"));

				shipDet.add(temp);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			con.close();
		}
		return shipDet;
	}

	public boolean updateCREDIT_CARD(card uCard) throws SQLException {
		Connection con = null;

		boolean successfull = true;

		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM");

			java.util.Date myDate = formatter.parse(uCard.getExpDate());
			java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
			con = createConnection();
			PreparedStatement st = con.prepareStatement("select CARDNUMBER from CREDIT_CARD where StoredCardCID=? and Status='ACTIVE'");
			st.setString(1, cid);

			ResultSet result = st.executeQuery();

			while (result.next()) {
				long oldCardId = result.getInt(1);

				PreparedStatement st_1 = con.prepareStatement("UPDATE CREDIT_CARD SET STATUS=? where CARDNUMBER=?");
				st_1.setString(1, "INACTIVE");
				st_1.setLong(2, oldCardId);

				st_1.executeUpdate();
				System.out.println("Old c - " + oldCardId);
				System.out.println(uCard.getCardNumber());
				/*
				 * PreparedStatement st_2 = con.prepareStatement(
				 * "UPDATE CREDIT_CARD SET CARDNUMBER=?,SecNumber=?,OwnerName=?,CCType=?,Bil_Address=?,ExpDate=?,STATUS=? where StoredCardCID=?"
				 * );
				 * 
				 * st_2.setString(1, uCard.getCardNumber()); st_2.setString(2, uCard.getCvv());
				 * st_2.setString(3, uCard.getName()); st_2.setString(4, uCard.getCardType());
				 * st_2.setString(5, uCard.getBilAddress()); st_2.setDate(6, sqlDate);
				 * st_2.setString(7, "ACTIVE"); st_2.setInt(8, Integer.parseInt(this.cid));
				 */

			}

			PreparedStatement st_new = con.prepareStatement(
					"insert into  CREDIT_CARD  (CARDNumber,SecNumber,OwnerName,CCType,Bil_Address,ExpDate,STATUS,StoredCardCID) VALUES(?,?,?,?,?,?,?,?) ");

			st_new.setString(1, uCard.getCardNumber());
			st_new.setString(2, uCard.getCvv());
			st_new.setString(3, uCard.getName());
			st_new.setString(4, uCard.getCardType());
			st_new.setString(5, uCard.getBilAddress());
			st_new.setDate(6, sqlDate);
			st_new.setString(7, "ACTIVE");
			st_new.setInt(8, Integer.parseInt(this.cid));

			st_new.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			successfull = false;
		} finally {
			con.close();
		}
		return successfull;
	}

	public boolean updateReg(User user) throws SQLException {
		Connection con = null;

		boolean successfull = true;
		try {

			con = createConnection();
			PreparedStatement st = con
					.prepareStatement("update CUSTOMER set LName=?,FName=?,Address=?,Phone=?,STATUS=? WHERE CID=? ");
			st.setString(1, user.getuLname());
			st.setString(2, user.getuFname());
			st.setString(3, user.getuAddress());
			st.setString(4, user.getuPhone());
			st.setString(5, user.getuCID());
			st.setString(6, user.getSILVER_AND_ABOVE());
			
			st.executeUpdate();

			PreparedStatement st_2 = con.prepareStatement("select Credit_Line from SILVER_AND_ABOVE where CID=?  ");
			st_2.setString(1, cid);
			ResultSet rt = st_2.executeQuery();

			if (rt.next()) {
				PreparedStatement st_3 = con.prepareStatement("update SILVER_AND_ABOVE (CID,Credit_Line) VALUES(?,?) ");
				st_3.setString(1, user.getuCID());
				st_3.setString(2, user.getSILVER_AND_ABOVE());
				st_3.executeUpdate();
			} else {

				PreparedStatement st_3 = con
						.prepareStatement("insert into SILVER_AND_ABOVE (CID,Credit_Line) VALUES(?,?) ");
				st_3.setString(1, user.getuCID());
				st_3.setString(2, user.getSILVER_AND_ABOVE());
				st_3.executeUpdate();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			successfull = false;
		} finally {
			con.close();
		}
		return successfull;
	}

	public List<TransactionModel> getTransactionHistory() {
		Connection con = null;

		List<TransactionModel> tempTmodel = new LinkedList<TransactionModel>();
		try {

			con = createConnection();
			PreparedStatement st = con.prepareStatement("select * from TRANSACTIONS where CID=? ");
			st.setString(1, cid);
			ResultSet rt = st.executeQuery();
			while (rt.next()) {
				TransactionModel temp = new TransactionModel();
				temp.setBid(rt.getInt("BID"));
				temp.setSAName(rt.getString("SAName"));
				temp.setTDate(rt.getString("TDate"));
				temp.setTstatus(rt.getString("TTag"));
				temp.setProducts(getCartDetails(temp.getBid()));
				Double total = 0d;
				for (Cart c : temp.getProducts()) {
					total += Double.valueOf(c.getpPricesold());
				}

				temp.setTCost(total);
				tempTmodel.add(temp);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tempTmodel;

	}

	public void cancelOrder(String bid) throws SQLException {
		Connection con = null;

		List<TransactionModel> tempTmodel = new LinkedList<TransactionModel>();
		try {

			con = createConnection();
			PreparedStatement st = con.prepareStatement("UPDATE TRANSACTIONS SET TTAG='Cancelled' where BID=?");
			System.out.println(bid);
			st.setString(1, bid);
			st.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public TransactionModel checkOut(User user) throws SQLException {
		Connection con = null;

		TransactionModel tempTmodel = new TransactionModel();
		try {

			con = createConnection();
			PreparedStatement st = con.prepareStatement("select BID from BASKET where CID=? and status=?");
			st.setString(1, this.cid);
			st.setString(2, "ACTIVE");
			ResultSet rt = st.executeQuery();
			if (rt.next()) {
				int bid = rt.getInt(1);

				PreparedStatement st_2 = con.prepareStatement("select SAName from SHIPPINGADDRESS where CID=?  ");
				st_2.setString(1, cid);
				ResultSet rt1 = st_2.executeQuery();

				if (rt1.next()) {
					String SAName = rt1.getString(1);
					PreparedStatement st_3 = con
							.prepareStatement("select CARDNUMBER from CREDIT_CARD where STOREDCARDCID=?  ");
					st_3.setString(1, cid);
					ResultSet rt2 = st_3.executeQuery();
					if (rt2.next()) {
						int CCnum = rt2.getInt(1);
						DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
						LocalDateTime now = LocalDateTime.now();
						java.util.Date myDate = formatter.parse(String.valueOf(now));
						java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
						PreparedStatement st_4 = con.prepareStatement(
								"insert into TRANSACTIONS (BID,CARDNUMBER,SAName,CID,TDATE,TTAG) VALUES(?,?,?,?,?,?) ");

						st_4.setInt(1, bid);
						st_4.setInt(2, CCnum);
						st_4.setString(3, SAName);
						st_4.setString(4, cid);
						st_4.setDate(5, sqlDate);
						st_4.setString(6, "Processing");

						int i = st_4.executeUpdate();

						if (i > 0) {
							PreparedStatement st_5 = con.prepareStatement("UPDATE BASKET SET STATUS=? WHERE BID=? ");
							st_5.setString(1, "COMPLETED");
							st_5.setInt(2, bid);

							st_5.executeUpdate();

							tempTmodel.setBid(bid);
							tempTmodel.setSAName(SAName);
							tempTmodel.setTDate(String.valueOf(sqlDate));
							tempTmodel.setTstatus("Processing");
							/*
							 * PreparedStatement st_6 = con
							 * .prepareStatement("select PID,Quantity,PriceSold from APPEARS_IN where BID=?  "
							 * ); st_6.setInt(1, bid); ResultSet rt6 = st_6.executeQuery();
							 * 
							 * while(rt6.next()) {
							 * 
							 * int pid = rt6.getInt(1);
							 * 
							 * 
							 * }
							 */

						}

					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			con.close();
		}
		return tempTmodel;
	}

	public List<Cart> getCartDetails(int bid) throws SQLException {
		Connection con = null;
		ArrayList<Cart> cartDetails = new ArrayList<>();
		try {
			con = createConnection();
			System.out.println(bid);

			PreparedStatement st_1 = con.prepareStatement("select PID,QUANTITY,PRICE_SOLD from APPEARS_IN where BID=?");
			st_1.setInt(1, bid);
			ResultSet result1 = st_1.executeQuery();

			while (result1.next()) {
				Cart temp = new Cart();
				temp.setpID(result1.getInt(1));
				temp.setpQuantity(result1.getString(2));
				temp.setpPricesold(result1.getString(3));

				PreparedStatement st_2 = con.prepareStatement("select PName from PRODUCT where PID=?");

				st_2.setInt(1, temp.getpID());
				ResultSet result2 = st_2.executeQuery();
				if (result2.next()) {
					temp.setpName(result2.getString(1));
				}
				cartDetails.add(temp);
				System.out.println(temp.getpName());
			}

		} catch (

		Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			con.close();
		}
		return cartDetails;
	}

}
