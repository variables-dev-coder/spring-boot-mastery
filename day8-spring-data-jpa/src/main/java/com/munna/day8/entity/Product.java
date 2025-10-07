package com.munna.day8.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity                   // Marks class as a database table
@Table(name = "products")  // Defines table name
public class Product {

    @Id   // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment ID
    private Long id;

    private String name;
    private double price;

    // Getters & Setters
    public Long getId() {
    	return id;
    	}
    public void setId(Long id) {
    	this.id = id; 
    	}

    public String getName() {
    	return name; 
    	}
    public void setName(String name) {
    	this.name = name; 
    	}

    public double getPrice() {
    	return price; 
    	}
    public void setPrice(double price) {
    	this.price = price;
    	}
}


/*
 * Explanation:
 
| Annotation                | Meaning                                                    |
| ------------------------- | ---------------------------------------------------------- |
| `@Entity`                 | Makes this class managed by JPA and maps it to a DB table. |
| `@Table(name="products")` | Specifies the table name in DB.                            |
| `@Id`                     | Marks this field as the Primary Key.                       |
| `@GeneratedValue`         | Automatically generates the ID value (auto increment).     |



 */
