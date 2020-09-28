package com.caili.todolist.service;

import com.caili.todolist.model.dao.TodoDao;
import com.caili.todolist.model.entity.Todo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
public class TestTodoControllerTodoService {
    @Autowired
    TodoService todoService;

    @MockBean
    TodoDao todoDao;

    @Test
    public void testGetTodos () {
        // [Arrange] 預期資料
        List<Todo> expectedTodosList = new ArrayList();
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTask("洗衣服");
        todo.setStatus(1);
        expectedTodosList.add(todo);

        // 定義模擬呼叫todoDao.findAll() 要回傳的預設結果
        Mockito.when(todoDao.findAll()).thenReturn(expectedTodosList);

        // [Act]操作todoService.getTodos();
        Iterable<Todo> actualTodoList = todoService.getTodos();

        // [Assert] 預期與實際的資料
        assertEquals(expectedTodosList, actualTodoList);
    }

    @Test
    public void testCreateTodo () {
        // [Arrange] 準備資料
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTask("寫鐵人賽文章");
        todo.setStatus(1);

        // 模擬呼叫todoDao.save(todo) 的回傳結果
         Mockito.when(todoDao.save(todo)).thenReturn(todo);

         // [Act] 實際呼叫操作todoService.createTodo
        Integer actualId = todoService.createTodo(todo);

        //  [Assert] 預期與實際的資料
        assertEquals(1, actualId);
    }

    @Test
    public void testUpdateTodoSuccess () {
        // 準備資料
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTask("寫鐵人賽文章");
        todo.setStatus(1);
        Optional<Todo> resTodo = Optional.of(todo);

        // 模擬呼叫todoDao.findById(id) 回傳的資料
        Mockito.when(todoDao.findById(1)).thenReturn(resTodo);

        // [Arrange] 更改的資料
        todo.setStatus(2);

        // [Act] 實際呼叫操作todoService.createTodo
        Boolean actualUpdateRlt = todoService.updateTodo(1, todo);

        //  [Assert] 預期與實際的資料
        assertEquals(true, actualUpdateRlt);
    }

    @Test
    public void testUpdateTodoNotExistId () {
        //準備更改的資料
        Todo todo = new Todo();
        todo.setStatus(2);

        Optional<Todo> resTodo = Optional.of(todo);
        // 模擬呼叫todoDao.findById(id)，資料庫沒有id=100的資料 回傳empty物件
        Mockito.when(todoDao.findById(100)).thenReturn(Optional.empty());

        // [Act] 實際呼叫操作todoService.updateTodo()
        Boolean actualUpdateRlt = todoService.updateTodo(100, todo);

        //  [Assert] 預期與實際的資料
        assertEquals(false, actualUpdateRlt);
    }

    @Test
    public void testUpdateTodoOccurException () {
        //準備更改的資料
        Todo todo = new Todo();
        todo.setId(1);
        todo.setStatus(1);

        Optional<Todo> resTodo = Optional.of(todo);
        // 模擬呼叫todoDao.findById(id)，資料庫有id=1的資料
        Mockito.when(todoDao.findById(1)).thenReturn(resTodo);
        todo.setStatus(2);

        // 模擬呼叫todoDao.save(todo)時發生NullPointerException例外
        doThrow(NullPointerException.class).when(todoDao).save(todo);
        // [Act] 實際呼叫操作todoService.updateTodo()
        Boolean actualUpdateRlt = todoService.updateTodo(100, todo);

        //  [Assert] 預期與實際的資料
        assertEquals(false, actualUpdateRlt);
    }

    @Test
    public void testDeleteTodoSuccess () {
        //準備更改的資料
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTask("鐵人賽文章");
        todo.setStatus(2);

        Optional<Todo> resTodo = Optional.of(todo);
        // 模擬呼叫todoDao.findById(id)，模擬資料庫有id=1的資料
        Mockito.when(todoDao.findById(1)).thenReturn(resTodo);

        // [Act] 實際呼叫操作todoService.deleteTodo()
        Boolean actualDeleteRlt = todoService.deleteTodo(1);

        //  [Assert] 預期與實際的資料
        assertEquals(true, actualDeleteRlt);
    }

    @Test
    public void testDeleteTodoIdNotExist () {
        //準備更改的資料
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTask("鐵人賽文章");
        todo.setStatus(2);

        Optional<Todo> resTodo = Optional.of(todo);
        // 模擬呼叫todoDao.findById(id)，並模擬資料庫沒有id=100的資料
        Mockito.when(todoDao.findById(100)).thenReturn(Optional.empty());

        // [Act] 實際呼叫操作todoService.deleteTodo()
        Boolean actualDeleteRlt = todoService.deleteTodo(100);

        //  [Assert] 預期與實際的資料
        assertEquals(false, actualDeleteRlt);
    }

    @Test
    public void testDeleteTodoOccurException () {
        //準備更改的資料
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTask("鐵人賽文章");
        todo.setStatus(2);

        Optional<Todo> resTodo = Optional.of(todo);
        // 模擬呼叫todoDao.findById(id)，並模擬資料庫有id=1的資料
        Mockito.when(todoDao.findById(1)).thenReturn(resTodo);

        // 模擬呼叫todoDao.deleteById(id)，會發生NullPointerException
        doThrow(NullPointerException.class).when(todoDao).deleteById(1);

        // [Act] 實際呼叫操作todoService.deleteTodo()
        Boolean actualDeleteRlt = todoService.deleteTodo(1);

        //  [Assert] 預期與實際的資料
        assertEquals(false, actualDeleteRlt);
    }
}
