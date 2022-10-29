package com.FlowerShop.FlowerShop.controllers;

import com.FlowerShop.FlowerShop.appuser.AppUser;
import com.FlowerShop.FlowerShop.dto.CartReq;
import com.FlowerShop.FlowerShop.dto.ReceiptReq;
import com.FlowerShop.FlowerShop.models.Cart;
import com.FlowerShop.FlowerShop.models.Flower;
import com.FlowerShop.FlowerShop.models.Receipt;
import com.FlowerShop.FlowerShop.models.Receiptdetail;
import com.FlowerShop.FlowerShop.repositories.CartRepository;
import com.FlowerShop.FlowerShop.repositories.FlowerRepository;
import com.FlowerShop.FlowerShop.repositories.ReceiptRepository;
import com.FlowerShop.FlowerShop.repositories.ReceiptdetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(value = "")
public class UserController {
    @Autowired
    private FlowerRepository flowerRepository;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private ReceiptdetailRepository receiptdetailRepository;

    @GetMapping(value = "")
    public String index(Model model) {
        List<Flower> flowerNewList = flowerRepository.getTopThreeNew();
        List<Flower> flowerExpensiveList = flowerRepository.getTopThreeExpensive();
        model.addAttribute("flowerNewList", flowerNewList);
        model.addAttribute("flowerExpensiveList", flowerExpensiveList);
        return "user/index";
    }

    @GetMapping(value = "/Flower")
    public String lstFlower(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "category", required = false) Integer category) {
        Pageable pageable = PageRequest.of(page.orElse(0), 3);
        Page<Flower> flowerPage;
        if (category != null) {
            flowerPage = flowerRepository.getFlowersByCategoryID(pageable, category);
            long count = flowerPage.getTotalElements();
            model.addAttribute("count", count);
            model.addAttribute("flowerPage", flowerPage);
            model.addAttribute("category", category);
            return "user/flower";
        } else if (name == null || name.equals("")) {
            flowerPage = flowerRepository.findAll(pageable);
            long count = flowerPage.getTotalElements();
            model.addAttribute("count", count);
            model.addAttribute("flowerPage", flowerPage);
            return "user/flower";
        } else {
            flowerPage = flowerRepository.getFlowersByName(pageable, name);
            long count = flowerPage.getTotalElements();
            model.addAttribute("count", count);
            model.addAttribute("flowerPage", flowerPage);
            model.addAttribute("name", name);
            return "user/flower";
        }

    }

    @GetMapping(value = "/FlowerDetail")
    public String flowerDetail(Model model, @RequestParam("id") int id) {
        Flower flower = flowerRepository.getById(id);
        model.addAttribute("flower", flower);
        return "user/flower_detail";
    }
    //giỏ hàng
    @GetMapping(value = "/user/deleteCartItem")
    public String deletedCartIem(Model model, @RequestParam("id") int id){
        Cart cart = cartRepository.findOneById(id);
        cart.setDeleted(true);
        cartRepository.save(cart);
        return "redirect:/shoppingcart";
    }
    @PostMapping(value = "/user/createCart")
    public String userCart(Model model, @ModelAttribute CartReq req){
        AppUser userDetails = (AppUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Flower flower = flowerRepository.findOneById(req.getId());
        Cart cart = new Cart();
        cart.setUser(userDetails);

        cart.setFlower(flower);
        cart.setQuantity(req.getQuantity());
        double totalPayment = flower.getPrice() * cart.getQuantity();
        cart.setTotalPayMent(totalPayment);
        cartRepository.save(cart);
        model.addAttribute("flower",flower);
        return "redirect:/shoppingcart";
    }
    @GetMapping(value = "/shoppingcart")
    public String Cart(Model model) {

        AppUser userDetails = (AppUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<Cart> carts = cartRepository.findAllByUserIdAndDeletedFalse(userDetails.getId());
        model.addAttribute("carts", carts);

        List<Cart> dsCart = cartRepository.findAllByUserIdAndDeletedFalse(userDetails.getId());
        List<Integer> dsId = new ArrayList<>();

        double totalCart =0;
        for (Cart i : dsCart){
            dsId.add(i.getId());
            totalCart += i.getTotalPayMent();
        }
        //----
        model.addAttribute("totalCart",totalCart);
        model.addAttribute("user", userDetails);
        return "user/cart";
    }

    @GetMapping(value = "/checkout")
    public String checkOut(Model model) {

        AppUser userDetails = (AppUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<Cart> carts = cartRepository.findAllByUserIdAndDeletedFalse(userDetails.getId());
        model.addAttribute("carts", carts);

        List<Cart> dsCart = cartRepository.findAllByUserIdAndDeletedFalse(userDetails.getId());
        List<Integer> dsId = new ArrayList<>();

        double totalCart =0;
        for (Cart i : dsCart){
            dsId.add(i.getId());
            totalCart += i.getTotalPayMent();
        }
        //----
        model.addAttribute("totalCart",totalCart);
        model.addAttribute("user", userDetails);
        return "user/checkout";
    }
    @PostMapping(value = "/user/createReceipt")
    public String createRecepit( Model model,@ModelAttribute ReceiptReq req){
        AppUser userDetails = (AppUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Receipt receipt = new Receipt();
        receipt.setExportDate(LocalDateTime.now());
        List<Cart> dsCart = cartRepository.findAllByUserIdAndDeletedFalse(userDetails.getId());
        List<Integer> dsId = new ArrayList<>();
        double totalCart =0;
        for (Cart i : dsCart){
            dsId.add(i.getId());
            totalCart += i.getTotalPayMent();
        }
        receipt.setTotalPayMent(totalCart);
        receipt.setUser(userDetails);
        receipt.setDeliveryDate(req.getDeliveryDate().toInstant(ZoneOffset.UTC));
        receipt.setReciverName(req.getReciverName());
        receipt.setReciverAddress(req.getAddress());
        receipt.setReciverPhoneNum(req.getPhoneNum());
        receipt.setMessage(req.getMessage());
        Receipt receiptSaved = receiptRepository.save(receipt);
        //luu chi tiet hoa don
        for (Cart i : dsCart){
            Receiptdetail receiptdetail = new Receiptdetail();
            receiptdetail.setPrice(i.getTotalPayMent());
            receiptdetail.setQuantity(i.getQuantity());
            receiptdetail.setFlower(i.getFlower());
            receiptdetail.setReceiptID(receiptSaved);
            receiptdetailRepository.save(receiptdetail);
        }
        return "redirect:/YourReceipt";
    }
    @GetMapping(value = "/YourReceipt")
    public String receipt(Model model) {

        AppUser userDetails = (AppUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<Receipt> receipts = receiptRepository.findAllByUser(userDetails.getId());
        model.addAttribute("receipt", receipts);
        model.addAttribute("user", userDetails);
        return "user/receipt";
    }
    @GetMapping(value = "/receiptDetail")
    public String receiptDetail(Model model,@RequestParam("id") int id) {

        AppUser userDetails = (AppUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<Receiptdetail> receiptdetails = receiptdetailRepository.getReceiptdetailsByReceiptID(id);
        Receipt receipt = receiptRepository.getById(id);
        model.addAttribute("receipt", receipt);
        model.addAttribute("receiptDetail", receiptdetails);
        model.addAttribute("user", userDetails);
        return "user/receipt_details";
    }
    @GetMapping(value = "/Contact")
    public String contact(Model model) {
        return "user/contact";
    }

    @GetMapping(value = "/ShowWay")
    public ModelAndView ggmap(@RequestParam("reciverAddress") String reciverAddress) {
        ModelAndView mv = new ModelAndView("user/ggmap");
        mv.addObject("destination", reciverAddress);
        return mv;
    }

    @GetMapping(value = "/registers")
    public String register() {
        return "pages-register";
    }

    @GetMapping(value = "/loginpage")
    public String login() {
        return "pages-login";
    }
}
