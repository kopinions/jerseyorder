package com.thoughtworks.com;

import java.util.ArrayList;
import java.util.List;

public interface ProductCatalog {

    private List<Product> products = new ArrayList<>();

    public ProductCatalog() {
    }


    public Product create(String name) {
        Long max = 0l;
        if(products.size()!=0)
        {
            System.out.println(products.get(0).getId());
            max = products.stream().map(p -> p.getId()).max((p1, p2) -> p1.compareTo(p2)).get();
        }
        Product e = new Product(name, (max + 1));
        products.add(e);
        return e;
    }

    public List<Product> getProductList() {
        return products;
    }

    public Product find(long id) throws ResourceNotFoundException {
        if(products.stream().noneMatch(p->p.getId() == id)) {
            throw new ResourceNotFoundException();
        }
        return products.stream().filter(p -> p.getId() == id).findFirst().get();
    }


}
