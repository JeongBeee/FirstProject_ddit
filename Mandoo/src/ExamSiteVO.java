
public class ExamSiteVO {

	private String siteCode;
	private String examTime;
	private String examDate;
	private String siteName;

	public ExamSiteVO(String siteCode, String examTime, String examDate, String siteName) {
		super();
		this.siteCode = siteCode;
		this.examTime = examTime;
		this.examDate = examDate;
		this.siteName = siteName;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

}
