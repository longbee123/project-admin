package com.example.demo.controller;




import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

@Controller
public class UserTemplateController {
    @Autowired
    private UserService userService;


    @GetMapping("/admin")
    public String list(Model model , @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,@ModelAttribute("name") String name,
                       @ModelAttribute("name") String email){

        model.addAttribute("listuser" ,userService.search(name ,email, page , size));
        model.addAttribute("name",name);

        return "list";
    }
//    @GetMapping("/search")
//        public String search(@ModelAttribute("name") String name ,@RequestParam(defaultValue = "1") int page, Model model){
//            if(StringUtils.isEmpty(name)){
//                return "redirect:/";
//            }
//            model.addAttribute("listuser" , userService.search(name,page));
//
//            return "list";
//    }
    @GetMapping("/listuser/add")
    public String add(){

        return "form";
    }
//    @PostMapping("/listuser/create")
//    public String create( @ModelAttribute("register") @Valid RegisterForm form, BindingResult result, RedirectAttributes redirect) {
//        if (result.hasErrors()) {
//           return "form";
//        }
//        userService.createUser(form);
//        redirect.addFlashAttribute("successMessage", "Created user successfully!");
//        return "redirect:/listuser";
//    }
    @GetMapping("/listuser/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("user",userService.getUserById(id));

        return "formupdate";
    }
//    @PostMapping("/listuser/save")
//    public String save(@Valid UserDto userdto,BindingResult result, RedirectAttributes redirect) {
//        if (result.hasErrors()) {
//            return "formupdate";
//        }
//        userService.updateUser(userdto);
//        redirect.addFlashAttribute("successMessage", "Update user successfully!");
//        return "redirect:/listuser";
//
//    }
//    @GetMapping("/listuser/{id}/delete")
//    public String delete(@PathVariable("id") int id , RedirectAttributes redirect){
//        userService.deleteUser(id);
//        redirect.addFlashAttribute("successMessage", "Delete user successfully!");
//        return "redirect:/listuser";
//    }
}
