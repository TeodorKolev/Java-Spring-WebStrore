package com.webstore.controller;

import com.constant.Constant;
import com.webstore.dto.ProductSearch;
import com.webstore.entity.Product;
import com.webstore.serviceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductsController {

    @Autowired
    ProductServiceImpl productService;

    private String errorMessage;

    @RequestMapping(value = Constant.PRODUCTS_URL, method = RequestMethod.GET)
    public String listProducts(Model model, @ModelAttribute("ProductSearch") ProductSearch productSearch){
        model.addAttribute(Constant.PRODUCTS, productService.getAllProducts(productSearch.getName(),
                productSearch.getPrice(), productSearch.getType(), productSearch.getQuantity()));
        model.addAttribute("productsUrl", Constant.PRODUCTS_URL);
        model.addAttribute("productAddUrl", Constant.PRODUCT_ADD_URL);
        model.addAttribute("productEditNoIdUrl", Constant.PRODUCT_EDIT_NO_ID_URL);
        model.addAttribute("productDeleteNoIdUrl", Constant.PRODUCT_DELETE_NO_ID_URL);
        return Constant.PRODUCTS;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = Constant.PRODUCT_URL, method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product, Model model){
        boolean successCreateProduct;
        boolean successEditProduct;
        if (product.getId() == null) {
            successCreateProduct = productService.createProduct(product);
            if (successCreateProduct) {
                return Constant.PRODUCTS_REDIRECT_URL;
            } else {
                errorMessage = productService.showErrorMessage();
                model.addAttribute(Constant.ERROR_MESSAGE, errorMessage);
                return addProduct(product, model);
            }
        } else {
            successEditProduct = productService.updateProduct(product);
            if (successEditProduct) {
                return Constant.PRODUCTS_REDIRECT_URL;
            } else {
                errorMessage = productService.showErrorMessage();
                model.addAttribute(Constant.ERROR_MESSAGE, errorMessage);
                return editProduct(product.getId(), model);
            }
        }
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(Constant.PRODUCT_ADD_URL)
    public String addProduct(@ModelAttribute("product") Product product, Model model){
        model.addAttribute("productEditNoIdUrl", Constant.PRODUCT_EDIT_NO_ID_URL);
        model.addAttribute("productUrl", Constant.PRODUCT_URL);
        return Constant.PRODUCT_FORM_URL;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(Constant.PRODUCT_EDIT_URL)
    public String editProduct(@PathVariable Integer id, Model model){
        model.addAttribute("productEditNoIdUrl", Constant.PRODUCT_EDIT_NO_ID_URL);
        model.addAttribute("productUrl", Constant.PRODUCT_URL);
        model.addAttribute("product", productService.getProductById(id));
        return Constant.PRODUCT_FORM_URL;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(Constant.PRODUCT_DELETE_URL)
    public String deleteProduct(@PathVariable Integer id){
        productService.deleteProductById(id);
        return Constant.PRODUCTS_REDIRECT_URL;
    }
}
