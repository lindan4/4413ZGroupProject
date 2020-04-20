package bean;

public class PaymentBean {
	private String nameOnCard;
	private int ccNumber;
	private int ccCVV;
	private String ccExpiryDate;
	
	public PaymentBean(String nameOnCard, int ccNumber, int ccCVV, String ccExpiryDate) {
		this.setNameOnCard(nameOnCard);
		this.setCcNumber(ccNumber);
		this.setCcCVV(ccCVV);
		this.setCcExpiryDate(ccExpiryDate);
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public int getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(int ccNumber) {
		this.ccNumber = ccNumber;
	}

	public int getCcCVV() {
		return ccCVV;
	}

	public void setCcCVV(int ccCVV) {
		this.ccCVV = ccCVV;
	}

	public String getCcExpiryDate() {
		return ccExpiryDate;
	}

	public void setCcExpiryDate(String ccExpiryDate) {
		this.ccExpiryDate = ccExpiryDate;
	}
	
	
	
	

}
