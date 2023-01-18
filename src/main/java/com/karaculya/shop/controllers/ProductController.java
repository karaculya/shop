package com.karaculya.shop.controllers;

import com.karaculya.shop.models.Product;
import com.karaculya.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String productsMain(Model model){
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/add")
    public String addProduct(Model model){
        return "products-add";
    }

    @PostMapping("/products/add")
    public String addProductPost(@RequestParam String title, @RequestParam String description, @RequestParam int cost, Model model){
        Product product = new Product(title, cost, description);
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String productDetails(@PathVariable(value = "id") long id, Model model){
        if (!productRepository.existsById(id)) return "redirect:/products";

        Optional<Product> product = productRepository.findById(id);
        ArrayList<Product> result = new ArrayList<>();
        product.ifPresent(result::add);
        model.addAttribute("product", result);
        return "product-details";
    }

    @GetMapping("/products/{id}/edit")
    public String editProduct(@PathVariable(value = "id") long id, Model model){
        if (!productRepository.existsById(id)) return "redirect:/products";

        Optional<Product> product = productRepository.findById(id);
        ArrayList<Product> result = new ArrayList<>();
        product.ifPresent(result::add);
        model.addAttribute("product", result);
        return "product-edit";
    }

    @PostMapping("/products/{id}/edit")
    public String updateProductPost(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String description, @RequestParam int cost, Model model){
        Product product = productRepository.findById(id).orElseThrow();
        product.setTitle(title);
        product.setCost(cost);
        product.setDescription(description);
        productRepository.save(product);
        return "redirect:/products";
    }

    @PostMapping("/products/{id}/remove")
    public String updateProductPost(@PathVariable(value = "id") long id, Model model){
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
        return "redirect:/products";
    }
}
