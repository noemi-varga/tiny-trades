package com.tinytrades.tinytradesbackend.repository.specifications;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClothingSearchCriteria {
    private String titleFragment;
    private String gender;
    private String ageGroup;
}
