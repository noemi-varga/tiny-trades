package com.tinytrades.tinytradesbackend.repository.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompositeSpecification<T> implements SearchSpecification<T> {

    private List<SearchSpecification<T>> specifications;

    public CompositeSpecification() {
        this.specifications = new ArrayList<>();
    }

    public void add(SearchSpecification<T> specification) {
        specifications.add(specification);
    }

    @Override
    public Specification<T> and(Specification<T> other) {
        return SearchSpecification.super.and(other);
    }

    @Override
    public Specification<T> or(Specification<T> other) {
        return SearchSpecification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = specifications.stream()
                .map(spec -> spec.toPredicate(root, query, criteriaBuilder))
                .toList();
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

