package cn.edu.bupt.todolist_2.controller;

import cn.edu.bupt.todolist_2.entity.Todo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ajax/todo")
public class AjaxTodoController {

    @GetMapping("/list")
    List<Todo> list(HttpSession session, @SessionAttribute(required = false) List<Todo> todos){
        if (todos == null) {
            todos = new ArrayList<Todo>();
            session.setAttribute("todos",todos);
        }
        return todos;
    }

    @PostMapping("/add")
    String add(@SessionAttribute List<Todo> todos, @RequestParam String content){
        Todo todo = new Todo();
        todo.setContent(content);
        todos.add(todo);
        return "success";
    }

    @PostMapping("/edit")
    String edit(@SessionAttribute List<Todo> todos, @RequestParam String content,
                @RequestParam int todoId){
        todos.get(todoId-1).setContent(content);
        return "success";
    }

    @DeleteMapping("/delete")
    String delete(@SessionAttribute List<Todo> todos, @RequestParam int todoId){
        todos.remove(todoId-1);
        return "success";
    }
}
