package com.idat.learn.repositories;

import com.idat.learn.entities.BookDetailEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookDetailRepository extends ReactiveCrudRepository<BookDetailEntity, String> {

}
