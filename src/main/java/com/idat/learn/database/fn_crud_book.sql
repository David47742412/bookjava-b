CREATE OR REPLACE FUNCTION fn_crud_book(
    IN InOption CHAR,
    InBookId VARCHAR(36),
    InTitle VARCHAR(200),
    InDescription VARCHAR(500),
    InWorkspace VARCHAR(50),
    InIpReq VARCHAR(50),
    InCategoryId VARCHAR(36)
)
    RETURNS TEXT
    LANGUAGE plpgsql
AS
$$
DECLARE
LMessage VARCHAR(300) default '';
    LBookId  VARCHAR(36) default '';
BEGIN
    InTitle := TRIM(InTitle);
    InDescription := TRIM(InDescription);

    IF (SELECT COUNT(*) FROM category where category_id = InCategoryId) = 0 THEN
        LMessage := 'Esta categoria no existe o ha sido eliminado, por favor ingresar una valida';
RAISE EXCEPTION '';
END IF;

    IF InOption = 'N' THEN
        LBookId := uuid_generate_v4();
INSERT INTO book(book_id, title, description, __workspace_create__, __workspace_update__, __create_date__,
                 __update_date__, __ip_req__, __deleted__)
VALUES (LBookId, InTitle, InDescription, InWorkspace, InWorkspace, now(), now(), InIpReq, false);

IF FOUND <> true THEN
            LMessage := 'Ha Ocurrido un error al momento de insertar en la tabla book';
            RAISE EXCEPTION '';
end if;

INSERT INTO book_detail(book_id, category_id) VALUES (LBookId, InCategoryId);

IF FOUND <> true THEN
            LMessage := 'Ha Ocurrido un error al momento de insertar en la tabla book_detail';
            RAISE EXCEPTION '';
end if;

    ELSIF InOption = 'M' THEN
UPDATE book
SET title                = InTitle,
    description          = InDescription,
    __workspace_update__ = InWorkspace,
    __update_date__      = now(),
    __ip_req__           = InIpReq
WHERE book_id = InBookId
