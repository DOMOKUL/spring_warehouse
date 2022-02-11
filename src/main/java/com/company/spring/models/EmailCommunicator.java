package com.company.spring.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

@Entity
public class EmailCommunicator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Email text is not be empty")
    private String emailText;

    @OneToMany(mappedBy = "emailCommunicator", fetch = FetchType.LAZY)
    private List<Order> orderList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailText() {
        return emailText;
    }

    public void setEmailText(String emailText) {
        this.emailText = emailText;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailCommunicator that = (EmailCommunicator) o;
        return Objects.equals(emailText, that.emailText) && Objects.equals(orderList, that.orderList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailText, orderList);
    }

    @Override
    public String toString() {
        return "EmailCommunicator{" +
                "id=" + id +
                ", emailText='" + emailText + '\'' +
                ", orderList=" + orderList +
                '}';
    }
}
