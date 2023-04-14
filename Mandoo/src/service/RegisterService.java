package service;

import java.util.List;

import dao.RegisterDAO;
import vo.RegisterVO;

public class RegisterService {
	private RegisterDAO dao = new RegisterDAO();

	public List<RegisterVO> selectRegisterInfos() throws Exception {
		return dao.selectRegisterInfos();
	}

	public RegisterVO selectRegisterInfo(String searchId) throws Exception {
		return dao.selectRegisterInfo(searchId);
	}
	
	public int insertRegisterInfo(RegisterVO vo) throws Exception {
		return dao.insertRegisterInfo(vo);
	}
	
	public int updateExamCode(RegisterVO vo) throws Exception {
		return dao.updateExamCode(vo);
	}
	
	public int updateSiteCode(RegisterVO vo) throws Exception {
		return dao.updateSiteCode(vo);
	}
	
	public int updateExamDate(RegisterVO vo) throws Exception {
		return dao.updateExamDate(vo);
	}

	public int deleteOneOfRegisterInfo(RegisterVO vo) throws Exception {
		return dao.deleteOneOfRegisterInfo(vo);
	}

	public int deleteRegisterInfo(RegisterVO vo) throws Exception {
		return dao.deleteRegisterInfo(vo);
	}

}
