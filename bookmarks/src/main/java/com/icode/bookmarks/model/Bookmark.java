package com.icode.bookmarks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Radu on 1/8/2017.
 */
@Entity
public class Bookmark {

    @Id
    @GeneratedValue
    private Long id;

    public String uri;
    public String description;

    @JsonIgnore
    @ManyToOne
    private Account account;


    //region Properties
    public Account getAccount() {
        return account;
    }

    public Long getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public String getDescription() {
        return description;
    }
    //endregion


    //region Constructors
    Bookmark() { // jpa only
    }

    public Bookmark(Account account, String uri, String description) {
        this.uri = uri;
        this.description = description;
        this.account = account;
    }
    //endregion


    @Override
    public String toString() {
        return this.description;
    }
}
