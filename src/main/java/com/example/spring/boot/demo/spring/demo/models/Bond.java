package com.example.spring.boot.demo.spring.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Bond {

    @Id
    @GeneratedValue
    private Long id;
    
    private String bondName;
    private String isin;
    private Double depth;
    private Double price;
    
    public Bond() {}
    
    public Bond(String bondName, String isin, Double depth, Double price) {
	super();
	this.bondName = bondName;
	this.isin = isin;
	this.depth = depth;
	this.price = price;
    }
    
    
}
