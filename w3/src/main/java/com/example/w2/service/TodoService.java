package com.example.w2.service;

import com.example.w2.domain.TodoVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import com.example.w2.dao.TodoDAO;
import com.example.w2.dto.TodoDTO;
import com.example.w2.util.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum TodoService {
  INSTANCE;
  private TodoDAO dao;
  private ModelMapper modelMapper;
  TodoService(){
    dao = new TodoDAO();
    modelMapper = MapperUtil.INSTANCE.get();
  }
  public void register(TodoDTO todoDTO) throws Exception{
    TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
    log.info(todoVO);
    dao.insert(todoVO);
  }
  public List<TodoDTO> listALL() throws Exception{
    List<TodoVO> voList = dao.selectAll();
    log.info("voList..................");
    List<TodoDTO> dtoList = voList.stream()
        .map(vo -> modelMapper.map(vo,TodoDTO.class))
        .collect(Collectors.toList());
    return dtoList;
  }

  public TodoDTO get(long tno) throws Exception{
    log.info("tno:" + tno);
    TodoVO todoVO = dao.selectOne(tno);
    TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
    return todoDTO;
  }

  public void remove(Long tno) throws Exception{
    log.info("tno:" + tno);
    dao.deleteOne(tno);
  }
  public void modify(TodoDTO todoDTO) throws Exception{
    log.info("todoDTO:" + todoDTO);
    TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
    dao.updateOne(todoVO);
  }
}
