package br.com.dogdositio.controller;

import br.com.dogdositio.model.Product;
import br.com.dogdositio.service.BaseProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private BaseProductDAO productDAO;

    @PostMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<Product> save(@RequestBody Product product) {
        if(product != null) {
            product.setCreationDate(new Date());
            Product persistedProduct = productDAO.save(product);
            if (persistedProduct != null) {
                return new ResponseEntity(persistedProduct, new HttpHeaders(), HttpStatus.CREATED);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<List<Product>> findAll() {
        List<Product> products = productDAO.findAll();
        return new ResponseEntity(products, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = {"/{id}/", "/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<Product> loadById(@PathVariable Long id) {
        Product product = productDAO.loadById(id);
        if(product != null) {
            return new ResponseEntity(product, new HttpHeaders(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = {"/{id}/", "/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<Product> delete(@PathVariable Long id) {
        Product product = productDAO.loadById(id);
        if(product != null) {
            productDAO.delete(product);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
