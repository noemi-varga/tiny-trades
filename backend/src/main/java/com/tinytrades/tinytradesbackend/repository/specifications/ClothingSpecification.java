package com.tinytrades.tinytradesbackend.repository.specifications;

import com.tinytrades.tinytradesbackend.model.enums.AgeGroup;
import com.tinytrades.tinytradesbackend.model.enums.Gender;
import com.tinytrades.tinytradesbackend.model.product.Clothing;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ClothingSpecification implements SearchSpecification<Clothing> {
    private ClothingSearchCriteria criteria;

    public ClothingSpecification(ClothingSearchCriteria criteria) {
        this.criteria = criteria;
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
        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getTitleFragment() != null && !criteria.getTitleFragment().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + criteria.getTitleFragment() + "%"));
        }
        if (criteria.getGender() != null && !criteria.getGender().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("gender"), Gender.valueOf(criteria.getGender())));
        }
        if (criteria.getAgeGroup() != null && !criteria.getAgeGroup().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("ageGroup"), AgeGroup.valueOf(criteria.getAgeGroup())));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
