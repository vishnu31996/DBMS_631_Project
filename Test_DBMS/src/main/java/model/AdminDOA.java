package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdminDOA extends OracleCon{
	java.sql.Date d1;
	java.sql.Date d2;

	public AdminDOA(String d1, String d2) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
		java.util.Date myDate1 = formatter.parse(d1);
		java.sql.Date sqlDate1 = new java.sql.Date(myDate1.getTime());
		this.d1 = sqlDate1;
		java.util.Date myDate2 = formatter.parse(d2);
		java.sql.Date sqlDate2 = new java.sql.Date(myDate2.getTime());
		this.d2 = sqlDate2;
	}

	public List<String> getFeqprod() throws SQLException {
		Connection con = null;

		ArrayList<String> data = new ArrayList<>();
		try {
			con = createConnection();
			PreparedStatement st = con.prepareStatement("SELECT \n" + "  PRODUCT.PID,\n" + "  PRODUCT.PNAME,\n"
					+ "   SUM(Quantity) as total\n" + "FROM TRANSACTIONS\n" + "JOIN BASKET\n"
					+ "  ON TRANSACTIONS.BID = BASKET.BID\n" + "JOIN APPEARS_IN\n"
					+ "  ON APPEARS_IN.BID = BASKET.BID\n" + "JOIN PRODUCT\n" + "  ON PRODUCT.PID = APPEARS_IN.PID \n"
					+ "WHERE \n" + "(TRANSACTIONS.TDate >= ? AND TRANSACTIONS.TDate < ?) \n" + "GROUP BY PRODUCT.PID,\n"
					+ "  PRODUCT.PNAME\n" + "ORDER BY SUM(QUANTITY) DESC limit 10;");

			st.setDate(1, d1);
			st.setDate(2, d2);
			System.out.println(st);
			ResultSet result = st.executeQuery();

			while (result.next()) {

				data.add(result.getString(2));

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			con.close();
		}
		return data;
	}

	public List<String> getAdmin2() throws SQLException {
		Connection con = null;

		ArrayList<String> data = new ArrayList<>();
		try {
			con = createConnection();
			PreparedStatement st = con.prepareStatement("select b.pname,  count(distinct cid) as distinct_count\n"
					+ "from(\n"
					+ "	select pname,appears_in.pid,Appears_IN.bid\n"
					+ "	from appears_in\n"
					+ "	inner join product on appears_in.pid = product.pid) b\n"
					+ "inner join TRANSACTIONS on b.bid=transactions.bid\n"
					+ "where b.BID in (select BID \n"
					+ "	from transactions \n"
					+ "	where TDate BETWEEN ? AND ?)\n"
					+ "group by pname");
			st.setDate(1, d1);
			st.setDate(2, d2);
			ResultSet result = st.executeQuery();

			while (result.next()) {

				data.add(result.getString(1));

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			con.close();
		}
		return data;
	}

	public List<String> getAdmin3() throws SQLException {
		Connection con = null;

		ArrayList<String> data = new ArrayList<>();
		ArrayList<String> temp = new ArrayList<>();
		try {
			con = createConnection();
			PreparedStatement st = con.prepareStatement("SELECT\n"
					+ "  TRANSACTIONS.CID,\n"
					+ "  APPEARS_IN.Price_Sold\n"
					+ "FROM TRANSACTIONS\n"
					+ "JOIN BASKET\n"
					+ "  ON TRANSACTIONS.BID = BASKET.BID\n"
					+ "JOIN APPEARS_IN\n"
					+ "  ON APPEARS_IN.BID = BASKET.BID\n"
					+ "JOIN PRODUCT\n"
					+ "  ON PRODUCT.PID = APPEARS_IN.PID \n"
					+ "WHERE \n"
					+ "(TRANSACTIONS.TDate >= ? AND TRANSACTIONS.TDate <  ?) \n"
					+ "ORDER BY Price_Sold DESC limit 10;");
			st.setDate(1, d1);
			st.setDate(2, d2);
			ResultSet result = st.executeQuery();

			while (result.next()) {

				data.add(result.getString(1));

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			con.close();
		}
		return data;
	}

	public List<String> getAdmin4() throws SQLException {
		Connection con = null;

		ArrayList<String> data = new ArrayList<>();
		try {
			con = createConnection();
			PreparedStatement st = con.prepareStatement("select CardNumber, max(p)\n"
					+ "from (select bid, sum(price_sold) as p, CardNumber\n"
					+ "	from (select (b.price_sold ),c.bid,c.CardNumber\n"
					+ "		from appears_in b, ( \n"
					+ "			select a.bid,a.CardNumber\n"
					+ "			from transactions a\n"
					+ "			where TDate BETWEEN ? AND ?) c\n"
					+ "		WHERE c.bid = b.bid) d\n"
					+ "	group by bid, CardNumber) e\n"
					+ "group by CardNumber;");
			st.setDate(1, d1);
			st.setDate(2, d2);
			ResultSet result = st.executeQuery();

			while (result.next()) {

				data.add(result.getString(1));

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			con.close();
		}
		return data;
	}

	public List<String> getAdmin5() throws SQLException {
		Connection con = null;

		ArrayList<String> data = new ArrayList<>();
		try {
			con = createConnection();
			PreparedStatement st = con.prepareStatement("select b.ptype, avg(b.price_sold) as avg\n"
					+ "from(\n"
					+ "	select bid,appears_in.pid,price_sold,ptype\n"
					+ "	from appears_in\n"
					+ "	inner join product on appears_in.pid = product.pid) b\n"
					+ "where bid in (\n"
					+ "	 select BID \n"
					+ "	from transactions \n"
					+ "	where TDate BETWEEN ? AND ?)\n"
					+ "group by b.ptype");
			st.setDate(1, d1);
			st.setDate(2, d2);
			ResultSet result = st.executeQuery();

			while (result.next()) {
				data.add(result.getString(1));
				

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			con.close();
		}
		return data;
	}
	public List<String> getAdmin6() throws SQLException {
		Connection con = null;

		ArrayList<String> data = new ArrayList<>();
		try {
			con = createConnection();
			PreparedStatement st = con.prepareStatement("select b.ptype, avg(b.price_sold) as avg\n"
					+ "from(\n"
					+ "	select bid,appears_in.pid,price_sold,ptype\n"
					+ "	from appears_in\n"
					+ "	inner join product on appears_in.pid = product.pid) b\n"
					+ "where bid in (\n"
					+ "	 select BID \n"
					+ "	from transactions \n"
					+ "	where TDate BETWEEN ? AND ?)\n"
					+ "group by b.ptype");
			st.setDate(1, d1);
			st.setDate(2, d2);
			ResultSet result = st.executeQuery();

			while (result.next()) {
				data.add(result.getString(2));
				

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			con.close();
		}
		return data;
	}

}
