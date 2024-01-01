package com.tinytrades.tinytradesbackend.model.product;
import com.tinytrades.tinytradesbackend.model.User;
import com.tinytrades.tinytradesbackend.model.enums.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "clothing")
public class Clothing extends Product{

    @ElementCollection
    @CollectionTable(name = "clothing_tags", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "tag")
    @Builder.Default
    private Set<String> tags = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column
    private ClothingSize size;

    @Enumerated(EnumType.STRING)
    @Column
    private ClothingColor color;

    @Enumerated(EnumType.STRING)
    @Column
    private ClothingCategory clothingCategory;
}
