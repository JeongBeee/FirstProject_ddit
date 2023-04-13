package service;

import dao.ExamineeDAO;
import vo.ExamineeVO;

public class ExamineeService {
	ExamineeDAO dao = new ExamineeDAO();

	public int insertMyInfo(ExamineeVO vo) throws Exception {
		return dao.insertMyInfo(vo);
	}

	public ExamineeVO updateInfo(ExamineeVO vo) throws Exception {
		return dao.updateInfo(vo);
	}

	public int deleteMyInfo(ExamineeVO vo) throws Exception {
		return dao.deleteMyInfo(vo);
	}

}
