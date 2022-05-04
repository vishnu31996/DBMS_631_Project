package model;

import java.util.List;

public class TransactionModel {
private String TDate;
private String Tstatus;
private String SAName;
private int bid;
private Double TCost;
private List<Cart> products;


public List<Cart> getProducts() {
	return products;
}
public void setProducts(List<Cart> products) {
	this.products = products;
}
public String getTDate() {
	return TDate;
}
public void setTDate(String tDate) {
	TDate = tDate;
}
public String getTstatus() {
	return Tstatus;
}
public void setTstatus(String tstatus) {
	Tstatus = tstatus;
}
public String getSAName() {
	return SAName;
}
public void setSAName(String sAName) {
	SAName = sAName;
}
public int getBid() {
	return bid;
}
public void setBid(int bid) {
	this.bid = bid;
}
public Double getTCost() {
	return TCost;
}
public void setTCost(Double tCost) {
	TCost = tCost;
}




}
