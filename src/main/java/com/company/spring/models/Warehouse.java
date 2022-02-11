package com.company.spring.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY)
    private Set<Product> productSet;

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY)
    private Set<Order> orderSet;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    public Set<Order> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return Objects.equals(productSet, warehouse.productSet) && Objects.equals(orderSet, warehouse.orderSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productSet, orderSet);
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", productSet=" + productSet +
                ", orderSet=" + orderSet +
                '}';
    }
}
