package com.tinytrades.tinytradesbackend.model.product;

import com.tinytrades.tinytradesbackend.model.enums.ClothingCategory;
import com.tinytrades.tinytradesbackend.model.enums.ClothingColor;
import com.tinytrades.tinytradesbackend.model.enums.ClothingSize;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "clothing")
public class Clothing extends Product{

    @Enumerated(EnumType.STRING)
    @Column
    private ClothingSize size;

    @Enumerated(EnumType.STRING)
    @Column
    private ClothingColor color;

    @Enumerated(EnumType.STRING)
    @Column
    private ClothingCategory clothCategory;

}
