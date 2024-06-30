package com.wolfhack.common.wrapper;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
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

}
