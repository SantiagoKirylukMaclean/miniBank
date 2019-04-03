package miniBank.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "balance", uniqueConstraints = {
        		@UniqueConstraint(columnNames = {
                "username"
            })
})
public class Balance {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Size(min=3, max = 50)
	private String username;
	
	
	@Digits(integer=5, fraction=2)
	private BigDecimal amount;

	@NotBlank
	@Size(min=3, max = 50)
	private String currency;
	
	public Balance() {}

	

	public Balance(long id, @NotBlank @Size(min = 3, max = 50) String username,
			@NotBlank @Digits(integer = 5, fraction = 2) BigDecimal amount,
			@NotBlank @Size(min = 3, max = 50) String currency) {
		super();
		this.id = id;
		this.username = username;
		this.amount = amount;
		this.currency = currency;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
	
}
