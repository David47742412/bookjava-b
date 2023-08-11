CREATE
OR REPLACE FUNCTION sp_find_person(
)
    RETURNS TABLE
            (
                bookId       VARCHAR(36),
                title        VARCHAR(200),
                description  VARCHAR(500),
                categoryId   VARCHAR(36),
                categoryName VARCHAR(500)
            )
    LANGUAGE plpgsql
AS
$$
BEGIN
RETURN QUERY SELECT b.book_id, b.title, b.description, c.category_id, c.description
                 FROM book_detail
                          JOIN public.book b on b.book_id = book_detail.book_id
                          JOIN public.category c on c.category_id = book_detail.category_id;
END
$$;

