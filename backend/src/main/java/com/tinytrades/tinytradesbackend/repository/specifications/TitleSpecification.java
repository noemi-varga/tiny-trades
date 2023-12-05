package com.tinytrades.tinytradesbackend.repository.specifications;

import com.tinytrades.tinytradesbackend.model.product.Clothing;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class TitleSpecification implements SearchSpecification<Clothing> {
    private String titleFragment;

    public TitleSpecification(String titleFragment) {
        this.titleFragment = titleFragment;
    }

    @Override
    public Specification<Clothing> and(Specification<Clothing> other) {
        return SearchSpecification.super.and(other);
    }

    @Override
    public Specification<Clothing> or(Specification<Clothing> other) {
        return SearchSpecification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<Clothing> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (titleFragment == null || titleFragment.isEmpty()) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true predicate
        }
        return criteriaBuilder.like(root.get("title"), "%" + titleFragment + "%");
    }
}
