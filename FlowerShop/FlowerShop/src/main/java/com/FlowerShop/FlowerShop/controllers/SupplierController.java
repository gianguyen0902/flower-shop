package com.FlowerShop.FlowerShop.controllers;

import com.FlowerShop.FlowerShop.models.Supplier;
import com.FlowerShop.FlowerShop.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/supplier")
public class SupplierController {
    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping(value = "")
    public String index(Model model){
        List<Supplier> supplierList = supplierRepository.findAll();
        model.addAttribute("supplierList", supplierList);
        return "admin/Supplier/index";
    }
    @GetMapping(value = "/create")
    public String create(){
        return "admin/Supplier/create";
    }
    @PostMapping(value = "/create")
    public String create(@ModelAttribute Supplier supplier){
        supplierRepository.save(supplier);
        return "redirect:/supplier";
    }
    @GetMapping("/edit")
    public String edit(Model model,@RequestParam("id") int id){
        Supplier supplier = supplierRepository.getById(id);
        model.addAttribute("supplier", supplier);
        return "admin/Supplier/edit";
    }
    @PostMapping("/edit")
    public String edit(@ModelAttribute("supplier") Supplier supplier){
        supplierRepository.save(supplier);
        return "redirect:/supplier";
    }
    @GetMapping("/delete")
    public String delete(Model model,@RequestParam("id") int id){
        Supplier supplier = supplierRepository.getById(id);
        model.addAttribute("supplier", supplier);
        return "admin/Supplier/delete";
    }
    @PostMapping("/delete")
    public String delete(@ModelAttribute("supplier") Supplier supplier){
        supplierRepository.deleteById(supplier.getId());
        return "redirect:/supplier";
    }
}
