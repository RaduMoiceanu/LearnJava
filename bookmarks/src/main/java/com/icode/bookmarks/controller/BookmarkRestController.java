package com.icode.bookmarks.controller;

import com.icode.bookmarks.exception.UserNotFoundException;
import com.icode.bookmarks.data.AccountRepository;
import com.icode.bookmarks.data.BookmarkRepository;
import com.icode.bookmarks.model.Bookmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

/**
 * Created by Radu on 1/8/2017.
 */
@RestController
@RequestMapping("/{userId}/bookmarks")
public class BookmarkRestController {

    private final BookmarkRepository bookmarkRepository;
    private final AccountRepository accountRepository;

    @Autowired
    BookmarkRestController(BookmarkRepository bookmarkRepository,
                           AccountRepository accountRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.accountRepository = accountRepository;
    }


    @RequestMapping(method = RequestMethod.GET)
    Collection<Bookmark> getBookmarks(@PathVariable String userId) {
        return this.bookmarkRepository.findByAccountUsername(userId);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{bookmarkId}")
    Bookmark getBookmark(@PathVariable String userId, @PathVariable Long bookmarkId) {
        return this.bookmarkRepository.findOne(bookmarkId);
    }


    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable String userId, @RequestBody Bookmark model) {
        return this.accountRepository.findByUsername(userId)
                   .map(account -> {
                       Bookmark result = bookmarkRepository.save(new Bookmark(account, model.uri, model.description));

                       URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                                 .path("/{id}").buildAndExpand(result.getId())
                                                                 .toUri();

                       return ResponseEntity.created(location).build();
                   })
                   .orElse(ResponseEntity.noContent().build());
    }
}
