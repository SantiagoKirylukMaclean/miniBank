package miniBank.model;

public class User {
	
	private long id;
	private String userId;
	private String password;
	private String identityId;
	private String name;
	private String lastName;
	
	public User() {
		super();
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.identityId = identityId;
		this.name = name;
		this.lastName = lastName;
	}
	
	
	public User(long id, String userId, String password, String identityId, String name, String lastName) {
		super();
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.identityId = identityId;
		this.name = name;
		this.lastName = lastName;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getIdentityId() {
		return identityId;
	}


	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
	
	
}
