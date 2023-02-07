package com.karaculya.shop;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    private ProductRepository productRepository;

    public void showProducts(Model model){
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
    }

    public void addProductPost(String title, int cost, String description){
        Product product = new Product(title, cost, description);
        productRepository.save(product);
    }

    public void showProductDetails(long id, Model model){
        if (productRepository.existsById(id)){
            Optional<Product> product = productRepository.findById(id);
            ArrayList<Product> result = new ArrayList<>();
            product.ifPresent(result::add);
            model.addAttribute("product", result);
        }
    }

    public void editProductPost(long id, Model model){
        if (productRepository.existsById(id)){
            Optional<Product> product = productRepository.findById(id);
            ArrayList<Product> result = new ArrayList<>();
            product.ifPresent(result::add);
            model.addAttribute("product", result);
        }
    }

    public void updateProductPost(long id, String title, String description, int cost){
        Product product = productRepository.findById(id).orElseThrow();
        product.setTitle(title);
        product.setCost(cost);
        product.setDescription(description);
        productRepository.save(product);
    }

    public void removeProductPost(long id){
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
    }
}
