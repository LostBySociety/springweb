package manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import manager.controller.payload.NewProductPayload;
import manager.entity.Product;
import manager.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping ("catalogue/products")
public class ProductListController {
    private final ProductService productService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getProductsList(Model model) {
        model.addAttribute("products", this.productService.findAllProducts());
        return "catalogue/products/list";
    }
    @GetMapping("create")
    public String getNewProductPage(Model model) {
        return "catalogue/products/new_product";
    }
    @PostMapping("create")
    public String createProduct(@Valid NewProductPayload payload, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "catalogue/products/new_product";
        } else {
            Product product = this.productService.createProduct(payload.title(), payload.details());
            return "redirect:/catalogue/products/" + product.getId();
        }
    }
}
