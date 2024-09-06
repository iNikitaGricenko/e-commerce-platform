package com.wolfhack.common.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class DomainPage<T> {

	List<T> content;

	int currentPage;

	int totalPages;

	int numberOfElements;

	long totalElements;

	public DomainPage(Page<T> page) {
		this.content = page.getContent();
		this.currentPage = page.getNumber();
		this.totalPages = page.getTotalPages();
		this.numberOfElements = page.getSize();
		this.totalElements = page.getTotalElements();
	}

	public DomainPage(DomainPage<?> page, List<T> content) {
		this.content = content;
		this.currentPage = page.currentPage;
		this.totalPages = page.getTotalPages();
		this.numberOfElements = page.numberOfElements;
		this.totalElements = page.getTotalElements();
	}

	public DomainPage(List<T> content, int currentPage, int totalPages, int numberOfElements, long totalElements) {
		this.content = content;
		this.currentPage = currentPage;
		this.totalPages = totalPages;
		this.numberOfElements = numberOfElements;
		this.totalElements = totalElements;
	}

	public <U> DomainPage<U> map(Function<? super T, ? extends U> converter) {
		return new DomainPage<>(
			this.getConvertedContent(converter),
			this.currentPage,
			this.totalPages,
			this.numberOfElements,
			this.totalElements
		);
	}

	protected <U> List<U> getConvertedContent(Function<? super T, ? extends U> converter) {
		Assert.notNull(converter, "Function must not be null");

		return this.content.stream()
			.map(converter)
			.collect(Collectors.toList());
	}

}
