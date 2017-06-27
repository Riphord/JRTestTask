package com.riphord.controller;

import com.riphord.model.User;
import com.riphord.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;


@org.springframework.stereotype.Controller
@RequestMapping("/")
public class MyController {

    @Autowired
    UserService service;

    @Autowired
    MessageSource source;

    @RequestMapping(value = {"/", "/list"},method = RequestMethod.GET)
    public String listUsers(@RequestParam(value = "page", required = false)Long page, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "age", required = false)Integer age, ModelMap model)
    {
        List<User> users;
        if(name!=null) {
            users = service.getAllUsersByName(name);
            model.addAttribute("name", name);
        }
        else if(age!=null) {
            users = service.getAllUsersByAge(age);
            model.addAttribute("age", age);
        }
        else
            users = service.getAllUsers();
        if(page==null)
            page = 0L;
        int startpage = (int) (page - 5 > 0?page - 5:0);
        int maxPage = users.size()/20;
        int endpage = startpage + 10;
        if(endpage>maxPage)
            endpage = maxPage;
        int last = users.size();
        int sublistend = (int)(page*20) + 20;
        if(sublistend>last)
            sublistend = last;
        List<User> users1 = users.subList((int)((page)*20), sublistend);
        model.addAttribute("users", users1);
        model.addAttribute("startpage",startpage);
        model.addAttribute("endpage",endpage);
        return "allusers";
    }

    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newUser(ModelMap model)
    {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "registration";
    }

    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result, ModelMap model)
    {
        if(result.hasErrors())
        {
            return "registration";
        }
        if(!service.isIdUniqueForRegistration(user.getId()))
        {
            FieldError idError = new FieldError("user", "id", source.getMessage("non.unique.id", new Integer[]{user.getId()}, Locale.getDefault()));
            result.addError(idError);
            return "registration";
        }
        service.saveUser(user);
        model.addAttribute("success", "User " + user.getName() + " registered successfully");
        return "success";
    }

    @RequestMapping(value = {"/edit-{id}-user"}, method = RequestMethod.GET)
    public String editUser(@PathVariable Integer id, ModelMap model)
    {
        User user = service.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "update";
    }

    @RequestMapping(value = {"/edit-{id}-user"}, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable Integer id)
    {
        if(result.hasErrors())
        {
            return "update";
        }
        if(!service.isIdUnique(user.getId(), user))
        {
            FieldError error = new FieldError("user", "id", source.getMessage("non.unique.id", new Integer[]{user.getId()}, Locale.getDefault()));
            result.addError(error);
            return "update";
        }

        service.updateUser(user);

        model.addAttribute("success", "User " + user.getName() + " updated successfully");
        return "success";
    }

    @RequestMapping(value = {"/delete-{id}-user"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable Integer id,HttpServletRequest request)
    {
        service.deleteById(id);
        String ret = request.getHeader("Referer");
        return "redirect:"+ret;
    }
}
