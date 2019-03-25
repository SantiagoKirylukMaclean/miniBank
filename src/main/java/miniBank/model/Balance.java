package miniBank.model;

import java.math.BigDecimal;
import java.util.Currency;

public class Balance {
	
	private long id;
	private String identityId;
	private BigDecimal amount;
	private String currency;
	
	public Balance() {
		super();
		this.id = id;
		this.identityId = identityId;
		this.amount = amount;
		this.currency = currency;
	}
	
	public Balance(long id, String identityId, BigDecimal amount, String currency) {
		super();
		this.id = id;
		this.identityId = identityId;
		this.amount = amount;
		this.currency = currency;
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String string) {
		this.currency = string;
	}

	
}
