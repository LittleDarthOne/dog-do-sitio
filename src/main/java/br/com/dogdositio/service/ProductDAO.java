package br.com.dogdositio.service;

import br.com.dogdositio.service.repository.ProductRepository;
import br.com.dogdositio.model.Product;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAO implements BaseProductDAO {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product loadById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<Product> findAll() {
        Iterable<Product> products = productRepository.findAll();
        if(products != null) {
            return Lists.newArrayList(products);
        }

        return new ArrayList<>();
    }

    @Override
    public List<Product> findByName(String name) {
        if(name != null && !name.trim().isEmpty()) {
            if(!name.startsWith("%")) name = "%" + name;
            if(!name.endsWith("%")) name = name + "%";

            List<Product> products = productRepository.findByName(name);
            if(products != null) {
                return products;
            }
        }

        return new ArrayList<>();
    }
}
