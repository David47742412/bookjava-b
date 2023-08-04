package com.idat.learn.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table(name = "book")
public class BookEntity {

    @Id
    @Column("book_id")
    public String bookId;

    @Column("title")
    public String title;

    @Column("description")
    public String description;

    @Column("__workspace_create__")
    public String workSpaceCreate;

    @Column("__workspace_update__")
    public String workSpaceUpdate;

    @Column("__create_date__")
    public Date createDate;

    @Column("__update_date__")
    public Date updateDate;

    @Column("__ip_req__")
    public String ipReq;

    @Column("__deleted__")
    public boolean deleted;
}
