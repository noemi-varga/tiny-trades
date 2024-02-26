package com.tinytrades.tinytradesbackend.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinytrades.tinytradesbackend.model.user.User;
import com.tinytrades.tinytradesbackend.model.enums.AgeGroup;
import com.tinytrades.tinytradesbackend.model.enums.ConditionType;
import com.tinytrades.tinytradesbackend.model.enums.Gender;
import com.tinytrades.tinytradesbackend.model.enums.Status;
import com.tinytrades.tinytradesbackend.model.image.ProductImage;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trader_id", referencedColumnName = "id")
    private User trader;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition")
    private ConditionType condition;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_group")
    private AgeGroup ageGroup;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="product_id")
    private final Set<ProductImage> productImages = new HashSet<>();

    public String getFirstImageLink() {
        return productImages.stream().findFirst().map(ProductImage::getUrl).orElse(null);
    }

    public void addImage(ProductImage productImage){
        productImages.add(productImage);
    }

}
