package com.product.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "product_list")
@Data
public class Product {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Column //(name = "First Name")
    private String productName;

    @Column //(name = "Last Name")
    private String category;

    @Column //(name = "Email")
    private double unitPrice;

    @Column
    private int quantityAvailable;

}
