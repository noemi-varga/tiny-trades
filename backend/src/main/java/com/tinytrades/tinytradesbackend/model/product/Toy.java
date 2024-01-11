package com.tinytrades.tinytradesbackend.model.product;
import com.tinytrades.tinytradesbackend.model.enums.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "toys")
public class Toy extends Product{

    @ElementCollection
    @CollectionTable(name = "toy_tags", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "tag")
    @Builder.Default
    private Set<String> tags = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column
    private ToyCategory toyCategory;

}
