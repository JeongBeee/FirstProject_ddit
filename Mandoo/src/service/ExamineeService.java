package service;

import dao.ExamineeDAO;
import vo.ExamineeVO;

public class ExamineeService {
	private ExamineeDAO dao = new ExamineeDAO();

	public int insertMyInfo(ExamineeVO vo) throws Exception {
		return dao.insertMyInfo(vo);
	}

	public int updatePassword(ExamineeVO vo) throws Exception {
		return dao.updatePassword(vo);
	}

	public int updateTelNO(ExamineeVO vo) throws Exception {
		return dao.updateTelNO(vo);
	}

	public int updateEmail(ExamineeVO vo) throws Exception {
		return dao.updateEmail(vo);
	}

	public int deleteMyInfo(ExamineeVO vo) throws Exception {
		return dao.deleteMyInfo(vo);
	}

	public ExamineeVO loginExaminee(ExamineeVO vo) throws Exception {
		return dao.loginExaminee(vo);
	}

}
