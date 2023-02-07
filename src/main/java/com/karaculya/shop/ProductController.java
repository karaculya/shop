package com.karaculya.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    private final ShopService shopService;

    public ProductController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/")
    public String openIndexPage(Model model) {
        shopService.showProducts(model);
        return "index";
    }

    @GetMapping("/products/add")
    public String addProduct(Model model){
        return "products-add";
    }

    @PostMapping("/products/add")
    public String addProduct(@RequestParam String title, @RequestParam String description, @RequestParam int cost){
        shopService.addProductPost(title, cost, description);
        return "redirect:/";
    }

    @GetMapping("/products/{id}")
    public String productDetails(@PathVariable(value = "id") long id, Model model){
        try{
            shopService.showProductDetails(id, model);
        } catch (Exception e) {
            return "redirect:/";
        }
        return "product-details";
    }

    @GetMapping("/products/{id}/edit")
    public String edit(@PathVariable(value = "id") long id, Model model){
        try {
            shopService.editProductPost(id, model);
        } catch (Exception e) {
            return "redirect:/";
        }
        return "product-edit";
    }

    @PostMapping("/products/{id}/edit")
    public String update(@PathVariable(value = "id") long id, @RequestParam String title,
                                    @RequestParam String description, @RequestParam int cost){
        shopService.updateProductPost(id, title, description, cost);
        return "redirect:/";
    }

    @PostMapping("/products/{id}/remove")
    public String updateProductPost(@PathVariable(value = "id") long id){
        shopService.removeProductPost(id);
        return "redirect:/";
    }
}
