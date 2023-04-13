package service;

import java.util.List;

import dao.RegisterDAO;
import vo.RegisterVO;

public class RegisterService {
	RegisterDAO dao = new RegisterDAO();

	public List<RegisterVO> selectRegisterInfos() throws Exception {
		return dao.selectRegisterInfos();
	}

	public RegisterVO selectRegisterInfo(String searchId) throws Exception {
		return dao.selectRegisterInfo(searchId);
	}

	public int insertRegisterInfo(RegisterVO vo) throws Exception {
		return dao.insertRegisterInfo(vo);
	}

	public int updateInfo(RegisterVO vo) throws Exception {
		return dao.updateInfo(vo);
	}

	public int deleteOneOfRegisterInfo(RegisterVO vo) throws Exception {
		return dao.deleteOneOfRegisterInfo(vo);
	}

	public int deleteRegisterInfo(RegisterVO vo) throws Exception {
		return dao.deleteRegisterInfo(vo);
	}

}
