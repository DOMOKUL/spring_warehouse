package com.company.spring.models;

public record Person(Long id, String name) {

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }
}
