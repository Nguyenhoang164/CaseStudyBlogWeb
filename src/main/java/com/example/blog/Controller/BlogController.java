package com.example.blog.Controller;

import com.example.blog.Model.Blog;
import com.example.blog.Model.Customer;
import com.example.blog.Service.IBlogService;
import com.example.blog.Service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICustomerService customerService;
    @GetMapping("")
    public String index(Model model){
        Customer customer = (Customer) model.asMap().get("customer");
        Iterable<Blog> page = blogService.findAll();
        model.addAttribute("blog",page);
        model.addAttribute("customer",customer);
        return "/home/index";
    }
    @GetMapping("/create/{id}")
    public String showCreate(@PathVariable("id") int id, Model model){
        model.addAttribute("customer", customerService.findCustomerById(id));
        model.addAttribute("blog",new Blog());
        return "/home/create";
    }
    @PostMapping("/save")
    public String createBlog(@ModelAttribute("blog") Blog blog){
        blogService.save(blog);
        return "redirect:/blog";
    }
    @GetMapping("/{id}/edit")
    public String showUpdate(Model model, @PathVariable("id") int id){
        model.addAttribute("blog",blogService.findById(id));
        return "/home/update";
    }
    @PostMapping("/update")
    public String saveBlog(@ModelAttribute("blog") Blog blog){
        blogService.update(blog);
        return "redirect:/blog";
    }
    @GetMapping("/{id}/delete")
    public String showDelete(Model model, @PathVariable("id") int id){
        model.addAttribute("blog",blogService.findById(id));
        return "/home/delete";
    }
    @PostMapping("/delete")
    public String deleteBlog(@ModelAttribute("blog") Blog blog){
        blogService.remove(blog.getId());
        return "redirect:/blog";
    }
    @GetMapping("/{id}/view")
    public String view(Model model, @PathVariable("id") int id){
        model.addAttribute("blog",blogService.findById(id));
        return "/home/view";
    }
}
