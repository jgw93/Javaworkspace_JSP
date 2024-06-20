package Service;

import DAO.NoticeDAO;
import DTO.NoticeDTO;
import VO.NoticeVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import util.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum NoticeService {
    INSTANCE;
    private NoticeDAO dao;
    private ModelMapper modelMapper;

    NoticeService() {
        dao = new NoticeDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public List<NoticeDTO> listALL() throws Exception{
        List<NoticeVO> voList = dao.selectAll();
        log.info("voList..................");
        List<NoticeDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo,NoticeDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }


    public NoticeDTO get(long tno) throws Exception{
        log.info("tno:" + tno);
        NoticeVO noticeVO = dao.selectOne(tno);
        NoticeDTO noticeDTO = modelMapper.map(noticeVO, NoticeDTO.class);
        return noticeDTO;
    }

    public void remove(Long tno) throws Exception {
        log.info("tno:" + tno);
        dao.deleteOne(tno);
    }

    public void add(NoticeDTO noticeDTO) throws Exception {
        log.info("noticeDTO:" + noticeDTO);

        dao.insertOne(noticeDTO);
    }
}
