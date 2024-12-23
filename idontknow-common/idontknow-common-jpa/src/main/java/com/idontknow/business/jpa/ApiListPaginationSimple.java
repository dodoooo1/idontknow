package com.idontknow.business.jpa;


import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public record ApiListPaginationSimple<T>(PaginationMeta meta, Collection<T> data, List<Error> errors) {

    public ApiListPaginationSimple(PaginationMeta meta, Collection<T> data, List<Error> errors) {
        this.meta = meta;
        this.data = data;
        this.errors = errors != null ? errors : Collections.emptyList();
    }

    public ApiListPaginationSimple(final Page<T> page, final List<Error> errors) {
        this(new PaginationMeta(page), page.getContent(), errors);
    }

    public static <T> ApiListPaginationSimple<T> of(final Page<T> page, final List<Error> errors) {
        return new ApiListPaginationSimple<>(new PaginationMeta(page), page.getContent(), errors);
    }

    public static <T> ApiListPaginationSimple<T> of(final Page<T> page) {
        return new ApiListPaginationSimple<>(new PaginationMeta(page), page.getContent(), null);
    }

    public record PaginationMeta(Integer currentPage, Integer pageSize, String sortedBy, Long totalElements,
                                 Integer totalPages) {

        public PaginationMeta(final Page<?> page) {
            this(
                    page.getNumber() + 1,
                    page.getSize(),
                    page.getSort().isSorted() ? page.getSort().toString() : StringUtils.EMPTY,
                    page.getTotalElements(),
                    page.getTotalPages()
            );
        }
    }

    public record Error(String code, String message) {
    }
}
