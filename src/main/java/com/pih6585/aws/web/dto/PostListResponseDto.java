package com.pih6585.aws.web.dto;

import java.time.LocalDateTime;

import com.pih6585.aws.domain.posts.Posts;

import lombok.Getter;

@Getter
public class PostListResponseDto {
	private Long id;
	private String title;
	private String author;
	private LocalDateTime modifiedDate;

	public PostListResponseDto(Posts entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.author = entity.getAuthor();
		this.modifiedDate = entity.getModifiedDate();
	}
}
