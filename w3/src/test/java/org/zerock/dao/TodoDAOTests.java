package org.zerock.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.w2.dao.TodoDAO;
import com.example.w2.domain.TodoVO;

import java.time.LocalDate;
import java.util.List;

public class TodoDAOTests {
  private TodoDAO todoDAO;

  @BeforeEach
  public void ready(){
    todoDAO = new TodoDAO();
  }
  @Test
  public void testTime() throws Exception{
    System.out.println(todoDAO.getTime());
  }

  @Test
  public void testInsert() throws Exception{
    TodoVO todoVO = TodoVO.builder()
        .title("sample title...")
        .dueDate(LocalDate.of(2021,12,31))
        .build();
    todoDAO.insert(todoVO);
  }

  @Test
  public void testList() throws Exception{
    List<TodoVO> list = todoDAO.selectAll();
    list.forEach(vo->System.out.println(vo));
  }

  @Test
  public void testOne() throws Exception{
    Long tno = 7L;
    TodoVO vo = todoDAO.selectOne(tno);
    System.out.println(vo);
  }
  @Test
  public void testUpdateOne() throws Exception{
    TodoVO todoVO = TodoVO.builder()
        .tno(7L)
        .title("update title...")
        .dueDate(LocalDate.of(2024,4,10))
        .finished(true)
        .build();
    System.out.println(todoVO);
    todoDAO.updateOne(todoVO);
  }
  @Test
  public void testDeleteOne() throws Exception{
    Long tno = 7L;
    todoDAO.deleteOne(tno);
  }
}












