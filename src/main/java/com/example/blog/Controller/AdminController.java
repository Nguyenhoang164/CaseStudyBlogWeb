package com.example.blog.Controller;

import com.example.blog.Model.Customer;
import com.example.blog.Service.Impl.BlogService;
import com.example.blog.Service.Impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CustomerService customerService;
    @GetMapping("")
    public String homeAdmin( @PageableDefault(8) Pageable pageable ,Model model){
        Customer customer = (Customer) model.asMap().get("customer");
        Page<Customer> customerPage = customerService.findAllBy(pageable);
        model.addAttribute("customer",customer);
        model.addAttribute("customerPage",customerPage);
        return "/admin/homePage";
    }
    @GetMapping("/delete/{id}/{idUser}")
    public String deleteUserById(@PathVariable("id") int id ,@PathVariable("idUser") int idUser, RedirectAttributes redirectAttributes){
        blogService.deleteBlogByCustomerId(id);
        customerService.remove(id);
        Customer customer = customerService.findCustomerById(idUser);
        redirectAttributes.addFlashAttribute("customer",customer);
        return "redirect:/admin";
    }
}
