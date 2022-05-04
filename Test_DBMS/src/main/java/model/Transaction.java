package model;

public class Transaction {
	private int cid;
	private String bid;
	private String cardNumber;
	private String SAName;
	private String tDate;
	private String tTag;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getSAName() {
		return SAName;
	}
	public void setSAName(String sAName) {
		SAName = sAName;
	}
	public String gettDate() {
		return tDate;
	}
	public void settDate(String tDate) {
		this.tDate = tDate;
	}
	public String gettTag() {
		return tTag;
	}
	public void settTag(String tTag) {
		this.tTag = tTag;
	}
	
	
	
}

