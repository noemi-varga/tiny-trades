package com.tinytrades.tinytradesbackend.model.product;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "clothing_id", nullable = true)
    private Clothing clothing;

    @ManyToOne
    @JoinColumn(name = "toy_id", nullable = true)
    private Toy toy;
}
