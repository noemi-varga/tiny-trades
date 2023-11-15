package com.tinytrades.tinytradesbackend.model.product;

import com.tinytrades.tinytradesbackend.model.enums.ToyCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "toys")
public class Toy extends Product{

    @Enumerated(EnumType.STRING)
    @Column
    private ToyCategory toyCategory;

}
