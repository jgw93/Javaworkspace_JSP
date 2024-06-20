package Service;

import DAO.MemberDAO;
import DTO.MemberDTO;
import VO.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import util.MapperUtil;

@Log4j2
public enum MemberService {
    INSTANCE;
    private MemberDAO dao;
    private ModelMapper modelMapper;
    MemberService(){
        dao = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }
    public void register(MemberDTO memberDTO) throws Exception{
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        log.info(memberVO);
        dao.insert(memberVO);
    }
}

