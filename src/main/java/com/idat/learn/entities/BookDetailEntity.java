package com.idat.learn.entities;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("book_detail")
public class BookDetailEntity {

    @Column("book_id")
    String bookId;

    @Column("category_id")
    String categoryId;

    @Column("__deleted__")
    String deleted;

}
