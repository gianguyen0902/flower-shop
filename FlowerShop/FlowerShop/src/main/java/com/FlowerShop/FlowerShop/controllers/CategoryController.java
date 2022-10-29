package com.FlowerShop.FlowerShop.controllers;

import com.FlowerShop.FlowerShop.models.Category;
import com.FlowerShop.FlowerShop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @GetMapping(value = "")
    public String index (Model model){
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categoryList", categoryList);
        return "admin/Category/index";
    }
    @GetMapping(value = "/create")
    public String create(){
        return "admin/Category/create";
    }
    @PostMapping(value = "/create")
    public String create(@ModelAttribute Category category){
        categoryRepository.save(category);
        return "redirect:/category";
    }

    @GetMapping("/edit")
    public String edit(Model model,@RequestParam("id") int id){
        Category category = categoryRepository.getById(id);
        model.addAttribute("category", category);
        return "admin/Category/edit";
    }
    @PostMapping("/edit")
    public String edit(@ModelAttribute("category") Category category){
        categoryRepository.save(category);
        return "redirect:/category";
    }
    @GetMapping("/delete")
    public String delete(Model model,@RequestParam("id") int id){
        Category category = categoryRepository.getById(id);
        model.addAttribute("category", category);
        return "admin/Category/delete";
    }
    @PostMapping("/delete")
    public String delete(@ModelAttribute("category") Category category){
        categoryRepository.deleteById(category.getId());
        return "redirect:/category";
    }

}
