
public class RegisterVO {
	private String id;
	private String examCode;
	private String siteCode;

	public RegisterVO(String id, String examCode, String siteCode) {
		super();
		this.id = id;
		this.examCode = examCode;
		this.siteCode = siteCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

}
