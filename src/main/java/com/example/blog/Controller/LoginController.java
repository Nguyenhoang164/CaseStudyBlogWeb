package com.example.blog.Controller;

import com.example.blog.Model.Customer;
import com.example.blog.Model.CustomerForm;
import com.example.blog.Service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private ICustomerService customerService;
    @GetMapping("")
    public String homeLogin(Model model){
        model.addAttribute("customer",new Customer());
        return "/login/homeLogin";
    }
    @RequestMapping("/loginToPage")
    public String loginToPage(@ModelAttribute("customer") Customer customer , Model model , RedirectAttributes redirectAttributes){
        Iterable<Customer> listCustomer = customerService.findAll();
        String result = customer.getName();
        Customer customerCheck = customerService.findCustomersByName(result);
        if (customerCheck == null){
            customerCheck = customerService.findCustomersByEmail(result);
            if (customerCheck == null){
                model.addAttribute("messageError", "wrong email or username,password ");
                return "/login/homeLogin";
            }
        }
        for (Customer customerItem : listCustomer) {
            if ((customerCheck.getEmail().equals(customerItem.getEmail()) && customer.getPassword().equals(customerItem.getPassword())) || (customerCheck.getName().equals(customerItem.getName()) && customerCheck.getPassword().equals(customer.getPassword())))  {
                    if (customerCheck.getPermission().equals("admin")){
                        redirectAttributes.addFlashAttribute("admin",customerCheck);
                        return "redirect:/admin";
                    }else{
                        redirectAttributes.addFlashAttribute("customer",customerCheck);
                        return "redirect:/blog";
                    }
                }else {
                model.addAttribute("messageError", "wrong email or username,password ");
                return "redirect:/login";
             }
            }
        model.addAttribute("messageError", "wrong email or username,password ");
        return "redirect:/login";
        }
     @GetMapping("/createAccount")
     public String showFormCreateAccount(Model model){
        model.addAttribute("customer",new CustomerForm());
        return "/login/create";
     }
     @Value("${file-upload}")
     private String fileUpload;
     @RequestMapping("/save")
      public String createCustomer(@ModelAttribute("customer") CustomerForm customerForm , Model model){
         Iterable<Customer> customerIterableCheck = customerService.findAll();
         for (Customer customer : customerIterableCheck){
             if (customerForm.getName().equals(customer.getName())||customerForm.getEmail().equals(customer.getEmail())||customerForm.getPhone().equals(customer.getPhone())){
                 model.addAttribute("messageError","* your name or email,phone was exit , please get another email or username,phone");
                 return "/login/create";
             }
         }
         MultipartFile multipartFile = customerForm.getAvatar();
         String fileAvatar = multipartFile.getOriginalFilename();
         try {
             FileCopyUtils.copy(customerForm.getAvatar().getBytes(),new File(fileUpload + fileAvatar));
         }catch (Exception e){
             e.printStackTrace();
         }
         Customer customer = new Customer(customerForm.getName(),customerForm.getEmail(),customerForm.getPhone(),customerForm.getPassword(),customerForm.getPermission(),fileAvatar);
         customerService.save(customer);
         model.addAttribute("messageCreate","* create account complete , now you can login by your new account");
         return "/login/homeLogin";
     }
    }

