package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ProductDOA extends OracleCon {
	String bId;
	String cId;
	String pID;

	public ProductDOA(String cid) {
		// TODO Auto-generated constructor stub
		this.cId = cid;
	}

	public List<Product> getProductDetails() throws SQLException {
		Connection con = null;

		ArrayList<Product> prod = new ArrayList<>();
		try {
			con = createConnection();
			PreparedStatement st = con.prepareStatement("select * from PRODUCT");

			ResultSet result = st.executeQuery();

			while (result.next()) {
				Product temp = new Product();
				temp.setPname(result.getString(1));
				temp.setPtype(result.getString(2));
				temp.setPquan(result.getString(3));
				temp.setPdesc(result.getString(4));
				temp.setPrice(result.getString(5));
				temp.setPid(result.getString(6));

				prod.add(temp);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			con.close();
		}
		return prod;
	}

	public void updateCart(String Uid, String Pid) throws SQLException {
		Connection con = null;
		this.pID = Pid;
		try {
			con = createConnection();
			PreparedStatement st = con.prepareStatement("select CID from CUSTOMER where EMAIL=?");
			st.setString(1, Uid);
			ResultSet result = st.executeQuery();

			if (result.next()) {

				String cid = result.getString(1);
				this.cId = cid;
				String bID = getOrCreateBasketID(cid);
				if (!checkForPreQuan()) {
					PreparedStatement st_2 = con
							.prepareStatement("insert into APPEARS_IN (BID,PID,PRICE_SOLD,QUANTITY)values(?,?,?,?)");
					st_2.setString(1, bID);
					st_2.setString(2, Pid);
					int pCost = getPrice();
					st_2.setInt(3, Integer.valueOf(pCost));
					st_2.setInt(4, 1);
					int count = st_2.executeUpdate();
					if (count < 1)
						throw new Exception("Error in APPEARS_IN Table");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	private int getPrice() {

		 double bestPrice = 0;
		 double discount = 0;
		Connection con = null;
		try {
			con = createConnection();
			PreparedStatement st = con.prepareStatement("select PPRICE from PRODUCT where PID=?");
			st.setString(1, pID);
			ResultSet result = st.executeQuery();
			if (result.next()) {
				bestPrice = result.getInt(1);
				PreparedStatement st_1 = con.prepareStatement("select OFFERPRICE from OFFER_PRODUCT where PID=?");
				st_1.setString(1, pID);
				ResultSet result1 = st_1.executeQuery();
				if (result1.next()) {
					bestPrice = result1.getInt(1);
				} else {
					System.out.println("No offer on product");
				}

			} else {
				throw new SQLException();
			}

			// discount for customers silver and above

			PreparedStatement st_d = con.prepareStatement("select status from CUSTOMER where CID=?");
			st_d.setString(1, cId);
			ResultSet result_d = st_d.executeQuery();
			if (result_d.next()) {
				String cStatus = result_d.getString(1).toUpperCase();
				
				switch (cStatus) {
				case "SILVER":
					discount = bestPrice * (5.0 / 100.0);
					break;
				case "GOLD":
					discount = bestPrice * (10.0 / 100.0);
					break;
				case "PLATINUM":
					discount = bestPrice * (15.0 / 100.0);
					break;
				}
				
				bestPrice = bestPrice - discount;
				System.out.println(bestPrice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (int)bestPrice;

	}

	public String getSilverAndAbove() {

		Connection con = null;

		try {
			con = createConnection();
			PreparedStatement st_d = con.prepareStatement("select status from CUSTOMER where CID=?");
			st_d.setString(1, cId);
			ResultSet result_d = st_d.executeQuery();

			if (result_d.next()) {
				return result_d.getString(1).toLowerCase();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

	}

	private boolean checkForPreQuan() throws SQLException {

		boolean isPresent = false;
		Connection con = null;

		try {
			con = createConnection();

			PreparedStatement st = con.prepareStatement("select QUANTITY from APPEARS_IN where BID=? And PID=?");
			st.setString(1, bId);
			st.setString(2, this.pID);
			ResultSet result = st.executeQuery();

			if (result.next()) {
				int Quan = Integer.parseInt(result.getString(1));

				PreparedStatement st_2 = con
						.prepareStatement("UPDATE APPEARS_IN set PRICE_SOLD=?, QUANTITY=? where BID=? and PID=?");

				int pCost = getPrice();
				st_2.setInt(1, (Quan + 1) * pCost);
				st_2.setInt(2, Quan + 1);
				st_2.setString(3, bId);
				st_2.setString(4, pID);
				int count = st_2.executeUpdate();
				if (count < 1)
					throw new Exception("Error in APPEARS_IN Table");
				isPresent = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return isPresent;

	}

	private String getOrCreateBasketID(String cID) {

		Connection con = null;
		String bId = null;
		try {
			con = createConnection();

			PreparedStatement st = con.prepareStatement("select BID from BASKET where CID=? and STATUS='ACTIVE'");
			st.setString(1, cID);
			ResultSet result = st.executeQuery();

			if (result.next()) {

				bId = result.getString(1);
			} else {
				PreparedStatement st_1 = con.prepareStatement("insert into BASKET (BID,CID,STATUS)values(?,?,?)");
				bId = generate_UID();
				st_1.setString(1, bId);
				st_1.setString(2, cID);
				st_1.setString(3, "ACTIVE");
				int count = st_1.executeUpdate();
				if (count < 1)
					throw new Exception("Error in BASKET Table");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.bId = bId;
		return bId;
	}

	public List<Cart> getCartDetails(String cId) throws SQLException {
		Connection con = null;
		ArrayList<Cart> cartDetails = new ArrayList<>();
		try {
			con = createConnection();
			System.out.println(cId);
			PreparedStatement st = con.prepareStatement("select BID from BASKET where CID=? and STATUS='ACTIVE'");
			st.setString(1, cId);
			ResultSet result = st.executeQuery();

			if (result.next()) {

				String bId = result.getString(1);
				System.out.println(bId);
				PreparedStatement st_1 = con
						.prepareStatement("select PID,QUANTITY,PRICE_SOLD from APPEARS_IN where BID=?");
				st_1.setInt(1, Integer.parseInt(bId));
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

	private String generate_UID() {
		AtomicLong idCounter = new AtomicLong(100);
		long timestamp = System.currentTimeMillis();
		long nextLong = idCounter.incrementAndGet();
		String random = String.valueOf(timestamp) + String.valueOf(nextLong);
		return random.substring(7, 15);
	}
}