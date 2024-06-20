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
    public MemberDTO login(String memberId, String memberPw) throws Exception{
        MemberVO vo = dao.getWithPassword(memberId,memberPw);
        log.info(vo);
        MemberDTO dto = modelMapper.map(vo, MemberDTO.class);
        log.info(dto);
        return dto;
    }

}

