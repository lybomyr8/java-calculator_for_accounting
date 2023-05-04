package com.joreijarr.studycontrol.controllers;

import com.joreijarr.studycontrol.models.Product;
import com.joreijarr.studycontrol.models.Purchase;
import com.joreijarr.studycontrol.models.Sell;
import com.joreijarr.studycontrol.repo.ProductRepository;
import com.joreijarr.studycontrol.repo.PurchaseRepository;
import com.joreijarr.studycontrol.repo.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class startController {


    @Autowired
    ProductRepository productRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    SellRepository sellRepository;




    @GetMapping("/")
    public String index(Model model)
    {
        return "index";
    }



    @GetMapping("/products")
    public String products(Model model)
    {
        List<Product> productList = new ArrayList<>();
        productRepository.findAll().forEach(productList::add);
        model.addAttribute("title", "Товари");
        model.addAttribute("products", productList);
        return "products";
    }

    @GetMapping("/products/add")
    public String products_add(Model model)
    {
        model.addAttribute("title", "Новий товар");
        return "products_add";
    }


    @PostMapping("/products/add")
    public String products_add_post(Model model,
                                    @RequestParam String name,
                                    @RequestParam String description,
                                    @RequestParam float purchase_price,
                                    @RequestParam float production_price,
                                    @RequestParam float sell_price)
    {
        Product product = new Product(name, description, purchase_price, production_price, sell_price);
        productRepository.save(product);
        return "redirect:/products";
    }


    @GetMapping("/purchases")
    public String purchases(Model model)
    {
        List<Purchase> purchaseList = new ArrayList<>();
        purchaseRepository.findAll().forEach(purchaseList::add);
        model.addAttribute("title", "Закупівлі");
        model.addAttribute("purchases", purchaseList);

        List<Product> productList = new ArrayList<>();
        productRepository.findAll().forEach(productList::add);
        model.addAttribute("products", productList);

        return "purchases";
    }


    @GetMapping("/purchases/add")
    public String purchases_add(Model model)
    {
        model.addAttribute("title", "Нова закупівля");
        List<Product> productList = new ArrayList<>();
        productRepository.findAll().forEach(productList::add);
        model.addAttribute("products", productList);

        return "purchases_add";
    }

    @PostMapping("/purchases/add")
    public String purchases_add_post(Model model,
                                     @RequestParam Long product,
                                     @RequestParam int count)
    {
        float price = productRepository.findById(product).orElse(null).purchase_price * count;
        Purchase purchase = new Purchase(product, count, price);
        purchaseRepository.save(purchase);
        return "redirect:/purchases";
    }


    @GetMapping("/sales")
    public String sales(Model model)
    {
        List<Sell> sellList = new ArrayList<>();
        sellRepository.findAll().forEach(sellList::add);
        model.addAttribute("title", "Продажі");
        model.addAttribute("sales", sellList);

        List<Product> productList = new ArrayList<>();
        productRepository.findAll().forEach(productList::add);
        model.addAttribute("products", productList);

        return "sales";
    }


    @GetMapping("/sales/add")
    public String sales_add(Model model)
    {
        model.addAttribute("title", "Новий продаж");
        List<Product> productList = new ArrayList<>();
        productRepository.findAll().forEach(productList::add);
        model.addAttribute("products", productList);

        return "sales_add";
    }

    @PostMapping("/sales/add")
    public String sales_add_post(Model model,
                                     @RequestParam Long product,
                                     @RequestParam int count)
    {
        float purchase_price = productRepository.findById(product).orElse(null).purchase_price * count;
        float production_price = productRepository.findById(product).orElse(null).production_price * count;
        float sell_price = productRepository.findById(product).orElse(null).sell_price * count;

        float profit = sell_price - purchase_price - production_price;
        Sell sell =  new Sell(product, count, sell_price, profit);
        sellRepository.save(sell);
        return "redirect:/sales";
    }


    @GetMapping("/report")
    public String report(Model model)
    {

        model.addAttribute("title", "Звіт прибутковості");
        float production = 0;

        List<Product> productList = new ArrayList<>();
        productRepository.findAll().forEach(productList::add);

        for (Product el: productList)
        {

            if (purchaseRepository.count()>0)
            {
                production += purchaseRepository.countPurchases(el.id)*el.production_price;
            }


        }



        float purchase = 0;
        if(purchaseRepository.count() > 0)
        {
            purchase = purchaseRepository.sumPurchases();
        }
        float sell_price = 0;
        if(sellRepository.count()>0)
        {
            sell_price = sellRepository.sumSellPrice();
        }

        float profit = 0;
        if (purchase >= 0 && sell_price >= 0)
        {
            profit = sell_price - purchase - production;
        }




        model.addAttribute("purchase", purchase);
        model.addAttribute("profit", profit);
        model.addAttribute("price", sell_price);
        model.addAttribute("production", production);

        return "report";
    }

}