package com.icode.bookmarks.data;

import com.icode.bookmarks.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * Created by Radu on 1/8/2017.
 */
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Collection<Bookmark> findByAccountUsername(String username);

}
