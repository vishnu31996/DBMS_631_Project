package model;

public class Cart {
	
	private int pID;
	private String pName;
	private String pQuantity;
	private String pPricesold;
	private String pTotal;
	
	

	public int getpID() {
		return pID;
	}

	public void setpID(int pID) {
		this.pID = pID;
	}

	public String getpTotal() {
		return pTotal;
	}

	public void setpTotal(String pTotal) {
		this.pTotal = pTotal;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpQuantity() {
		return pQuantity;
	}

	public void setpQuantity(String pQuantity) {
		this.pQuantity = pQuantity;
	}

	public String getpPricesold() {
		return pPricesold;
	}

	public void setpPricesold(String pPricesold) {
		this.pPricesold = pPricesold;
	}

}
