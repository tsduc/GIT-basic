package com.laptrinhjavaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity{

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<TransactionEntity> transaction = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<AssignmentCustomerEntity> assignmentCustomer = new ArrayList<>();

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TransactionEntity> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<TransactionEntity> transaction) {
        this.transaction = transaction;
    }

    public List<AssignmentCustomerEntity> getAssignmentCustomer() {
        return assignmentCustomer;
    }

    public void setAssignmentCustomer(List<AssignmentCustomerEntity> assignmentCustomer) {
        this.assignmentCustomer = assignmentCustomer;
    }
}
