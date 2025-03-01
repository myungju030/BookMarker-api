package com.bookmarker.api.dto;

import com.bookmarker.api.domain.Bookmark;
import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {
    //Entity => DTO 변환
    public BookmarkDTO toDTO(Bookmark bookmark) {
        return new BookmarkDTO(
            bookmark.getId(),
            bookmark.getTitle(),
            bookmark.getUrl(),
            bookmark.getCreatedAt()
        );
    }
}