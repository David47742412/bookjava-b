package com.idat.learn.entities;

import jakarta.annotation.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Table(name = "category")
public class CategoryEntity {

    @Id
    @Column("category_id")
    public String categoryId;

    @Column("description")
    public String description;

    @Column("__workspace_create__")
    public String workSpaceCreate;

    @Column("__workspace_update__")
    public String workSpaceUpdate;

    @Column("__create_date__")
    public LocalDateTime createDate;

    @Column("__update_date__")
    public LocalDateTime updateDate;

    @Column("__ip_req__")
    public String ipReq;

    @Column("__deleted__")
    public boolean deleted;

}
