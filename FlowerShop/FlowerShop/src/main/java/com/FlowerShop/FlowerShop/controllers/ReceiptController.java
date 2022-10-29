package com.FlowerShop.FlowerShop.controllers;

import com.FlowerShop.FlowerShop.models.Receipt;
import com.FlowerShop.FlowerShop.models.Receiptdetail;
import com.FlowerShop.FlowerShop.repositories.ReceiptRepository;
import com.FlowerShop.FlowerShop.repositories.ReceiptdetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/receipt")
public class ReceiptController {
    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private ReceiptdetailRepository receiptdetailRepository;

    @GetMapping(value = "")
    public String index(Model model){
        List<Receipt> receiptList = receiptRepository.findAll();
        model.addAttribute("receiptList", receiptList);
        return "admin/Receipt/index";
    }
    @GetMapping(value = "/ReceiptDetail")
    public String ReceiptDetail (Model model, @RequestParam("id") int id){
        List<Receiptdetail> receiptDetailList = receiptdetailRepository.getReceiptdetailsByReceiptID(id);
        model.addAttribute("receiptDetailList", receiptDetailList);
        return "admin/Receipt/ReceiptDetail";
    }
}
