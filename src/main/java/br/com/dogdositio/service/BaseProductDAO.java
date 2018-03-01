package br.com.dogdositio.service;

import br.com.dogdositio.model.Product;

import java.util.List;

public interface BaseProductDAO {

    Product save(Product product);

    void delete(Product product);

    List<Product> findAll();

    List<Product> findByName(String name);

    Product loadById(Long id);

}
