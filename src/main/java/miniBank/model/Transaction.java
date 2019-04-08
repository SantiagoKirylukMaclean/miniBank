package miniBank.model;

import java.math.BigDecimal;

public class Transaction {

	private long id;
	private BigDecimal amount;
	
	public Transaction() {
		super();
		this.id = id;
		this.amount = amount;
	}

	
	public Transaction(long id, BigDecimal amount) {
		super();
		this.id = id;
		this.amount = amount;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	
}
