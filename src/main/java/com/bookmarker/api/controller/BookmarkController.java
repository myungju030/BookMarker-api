package com.bookmarker.api.controller;

import com.bookmarker.api.domain.Bookmark;
import com.bookmarker.api.dto.BookmarkDTO;
import com.bookmarker.api.dto.BookmarksDTO;
import com.bookmarker.api.dto.CreateBookmarkRequest;
import com.bookmarker.api.service.BookmarkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

    //api/bookmarks?page=2&query=boot
    @GetMapping
    public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1")
                                     Integer page,
                                     @RequestParam(name = "query", defaultValue = "")
                                     String query) {
        /*
            java 11 String 추가된 메서드
            isBlank() => isEmpty() + trim()
         */
        //if(query == null || query.trim().isEmpty()) {
        if(query == null || query.isBlank()) {
            return bookmarkService.getBookmarks(page);
        }
        return bookmarkService.searchBookmarks(query, page);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //201
    //@Valid 는 DataBinding 을 체크하는 내부적으로 생성된 Validator 를 호출해주는 역할을 함
    public BookmarkDTO createBookmark(@RequestBody @Valid CreateBookmarkRequest request) {
        return bookmarkService.createBookmark(request);
    }
}