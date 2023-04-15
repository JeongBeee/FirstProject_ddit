package service;

import dao.ExamineeDAO;
import vo.ExamineeVO;

public class ExamineeService {
	private ExamineeDAO dao = new ExamineeDAO();
	
	public int insertMyInfo(ExamineeVO vo) throws Exception {
		return dao.insertMyInfo(vo);
	}

	public int updateMyInfo(ExamineeVO vo) throws Exception {
		return dao.updateMyInfo(vo);
	}

	public int deleteMyInfo(ExamineeVO vo) throws Exception {
		return dao.deleteMyInfo(vo);
	}
	
	public ExamineeVO loginExaminee(ExamineeVO vo) throws Exception {
		return dao.loginExaminee(vo);
	}

}
