
public class ExamVO {
	private String examCode;
	private String examName;
	private String fee;

	public ExamVO(String examCode, String examName, String fee) {
		super();
		this.examCode = examCode;
		this.examName = examName;
		this.fee = fee;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

}
