package com.example.blog.Controller;

import com.example.blog.Model.Blog;
import com.example.blog.Model.BlogForm;
import com.example.blog.Model.Customer;
import com.example.blog.Service.IBlogService;
import com.example.blog.Service.ICustomerService;
import com.sun.tools.sjavac.CopyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Value("${file-upload}")
    private String fileUpload;
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
    @GetMapping("/home/{id}")
    public String showHomePage(@PathVariable("id") int id ,Model model){
        Customer customer = customerService.findCustomerById(id);
        Iterable<Blog> page = blogService.findAll();
        model.addAttribute("blog",page);
        model.addAttribute("customer",customer);
        return "/home/index";
    }
    @GetMapping("/create/{id}")
    public String showCreate(@PathVariable("id") int id, Model model){
        Customer customer = customerService.findCustomerById(id);
        Iterable<Blog> blogOptional = blogService.findBlogByCustomerId(customer);
        List<Blog> list = new ArrayList<>();
        for (Blog itemBlog : blogOptional){
            list.add(itemBlog);
        }
        model.addAttribute("customer", customer);
        model.addAttribute("blog",list);
        model.addAttribute("blogForm",new Blog());
        return "/home/create";
    }
    @PostMapping("/save/{id}")
    public String createBlog(@ModelAttribute("blog")BlogForm blogForm , @PathVariable("id") int id , RedirectAttributes redirectAttributes){
        Customer customer = customerService.findCustomerById(id);
        MultipartFile multipartFile = blogForm.getImg();
        String fileImg = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(blogForm.getImg().getBytes(),new File(fileUpload + fileImg ));
        }catch (Exception e){
            e.printStackTrace();
        }
        LocalDate timeCreate = LocalDate.now();
        Blog blog = new Blog(blogForm.getId(), blogForm.getName(), blogForm.getText(), blogForm.getThumbnail(), fileImg, blogForm.getTypeBlog(),timeCreate, customer);
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("customer",customer);
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
