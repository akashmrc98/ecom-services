package com.example.orders.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Address {
    @Id
    private Long id;
    private String addressType;
    private String fullName;
    private String firstLine;
    private String secondLine;
    private String zip;
    private String city;
    private String state;
    private String phone;
}
