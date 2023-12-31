package com.idat.learn.repositories;

import com.idat.learn.dto.FindBookDto;
import com.idat.learn.entities.BookDetailEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RepositoryRestResource(exported = false, path = "book", collectionResourceRel = "book") solo se puede utilizar para JPA y CRUDJPA
@Repository
public interface IBookRepository extends ReactiveCrudRepository<BookDetailEntity, String> {

    @Query("SELECT sp_crud_book(" +
            "InOption := :option, InBookId := :bookId, InTitle := :title, " +
            "InDescription := :description, InWorkspace := :wks, InIpReq := :ipReq, " +
            "InCategoryId := :categoryId)")
    Mono<String> crud(char option, String bookId,
                      String title, String description,
                      String wks, String ipReq, String categoryId);

    @Query("SELECT * FROM sp_find_person();")
    Flux<FindBookDto> find();

}
