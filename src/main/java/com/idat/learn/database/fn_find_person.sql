create or replace function sp_find_person()
    returns TABLE
            (
                bookid       character varying,
                title        character varying,
                description  character varying,
                categoryid   character varying,
                categoryname character varying
            )
    language plpgsql
as
$$
BEGIN
RETURN QUERY SELECT b.book_id, b.title, b.description, c.category_id, c.description
                 FROM book_detail
                          JOIN public.book b on b.book_id = book_detail.book_id
                          JOIN public.category c on c.category_id = book_detail.category_id
                 where b.__deleted__ = false
                 ORDER BY b.__update_date__;
END
$$;

alter function sp_find_person() owner to postgres;

