package org.sopt.global.response;

import java.util.List;

import org.springframework.data.domain.Page;

public record PageResponse<T>(
	List<T> content,
	int currentPage,
	int totalPage,
	long totalElements,
	boolean isFirst,
	boolean isLast
) {
	public static <T> PageResponse<T> from(Page<T> page) {
		return new PageResponse<>(
			page.getContent(),
			page.getNumber(),
			page.getTotalPages(),
			page.getTotalElements(),
			page.isFirst(),
			page.isLast()
		);
	}
}
