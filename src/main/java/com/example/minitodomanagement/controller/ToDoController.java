package com.example.minitodomanagement.controller;

import com.example.minitodomanagement.model.Todo;
import com.example.minitodomanagement.service.IToDoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Binding;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;

@Controller
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoController {
    private IToDoService todoService;


    @InitBinder
    public void initBinder(WebDataBinder binder){
        //Date -dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
    }



    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public  String showTodos(ModelMap modelMap){
        String name = getLoggedInUserName(modelMap);
        modelMap.put("todos", todoService.getTodosByUser(name));
        return "list-todos";
    }

    private String getLoggedInUserName(ModelMap modelMap){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails){
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showAddTodoPage(ModelMap model){
        model.addAttribute("todo", new Todo());
        return "todo";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam long id){
        todoService.deleteTodo(id);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result){
        if (result.hasErrors()){
            return "todo";
        }
        
        todo.setUsername(getLoggedInUserName(modelMap));
        todoService.updateTodo(todo);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap modelMap,  @Valid Todo todo, BindingResult result){
        if (result.hasErrors()) {
        return "todo";
        }

        todo.setUsername(getLoggedInUserName(modelMap));
        todoService.saveTodo(todo);
        return "redirect:/list-todos";
    }









}
