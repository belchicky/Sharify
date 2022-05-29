package com.sharify.controllers;

import com.sharify.models.Product;
import com.sharify.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/category")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping()
    public String home() {
        return "redirect:/";
    }

    @GetMapping("/{category}")
    public String homeStuff(@PathVariable String category, Model model) {
        Iterable<Product> products;
        if (!category.equals("all"))
            products = productRepo.findByCategory(category);
        else
            products = productRepo.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/product/add")
    public String add(Product product) {
        return "add";
    }

    @PostMapping("/product/add")
    public String add(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add";
        }
        productRepo.save(product);
        return "redirect:/category/all";
    }

    @PostMapping("/product/{id}")
    public String delete(@PathVariable Long id) {
        productRepo.deleteById(id);
        return "redirect:/category/all";
    }

    @GetMapping("/product/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id, Product product) {
        model.addAttribute("product", productRepo.getOne(id));
        return "edit";
    }

    @PostMapping("/product/edit/{id}")
    public String edit(@ModelAttribute("product") @Valid  Product product, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        productRepo.save(product);
        return "redirect:/category/all";
    }

    @PostMapping("/{category}")
    public String search(@RequestParam String search, Model model, @PathVariable String category) {
        search = search.trim();
        if (!search.equals("")) {
            List<Product> products = new ArrayList<>();
            Pattern pattern = Pattern.compile(".*" + search + ".*");
            for (Product product : (category.equals("all")) ? productRepo.findAll() : productRepo.findByCategory(category)) {
                Matcher matcher = pattern.matcher(product.getTitle());
                boolean matchFound = matcher.find();
                if (matchFound)
                    products.add(product);
            }
            model.addAttribute("products", products);
        }
        return "products";
    }
}
