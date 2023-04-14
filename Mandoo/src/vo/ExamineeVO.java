package vo;

public class ExamineeVO {
	private String id;
	private String name;
	private String password;
	private String telNo;
	private String email;
	
	public ExamineeVO() {
		
	}
	
	public ExamineeVO(String id) {
		this.id = id;
	}
	
	public ExamineeVO(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public ExamineeVO(String password, String telNo, String email) {
		this.password = password;
		this.telNo = telNo;
		this.email = email;
	}
	
	public ExamineeVO(String id, String name, String password, String telNo, String email) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.telNo = telNo;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
    @Override
    public String toString() {
        return String.format("ExamineeVO [id=%s, password=%s]", id, password);
    }
	
}
