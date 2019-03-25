package miniBank.model;

import java.math.BigDecimal;

public class Transaction {

	private long id;
	private String identityId;
	private BigDecimal amount;
	
	public Transaction() {
		super();
		this.id = id;
		this.identityId = identityId;
		this.amount = amount;
	}

	
	public Transaction(long id, String identityId, BigDecimal amount) {
		super();
		this.id = id;
		this.identityId = identityId;
		this.amount = amount;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getIdentityId() {
		return identityId;
	}


	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	
}
