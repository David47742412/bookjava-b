create or replace function sp_crud_book(inoption character, inbookid character varying, intitle character varying,
                                        indescription character varying, inworkspace character varying,
                                        inipreq character varying,
                                        incategoryid character varying) returns character varying
    language plpgsql
as
$$
DECLARE
LMessage VARCHAR(300) default '';
    LBookId  VARCHAR(36) default '';
BEGIN

    InTitle := TRIM(InTitle);
    InDescription := TRIM(InDescription);

    IF InOption <> 'D' AND (SELECT COUNT(*) FROM category where category_id = InCategoryId) = 0 THEN
        LMessage := 'Esta categoria no existe o ha sido eliminado, por favor ingresar una valida';
        RAISE EXCEPTION '';
END IF;

    IF InOption = 'N' THEN
        LBookId := uuid_generate_v4();
INSERT INTO book(book_id, title, description, __workspace_create__, __workspace_update__, __create_date__,
                 __update_date__, __ip_req__, __deleted__)
VALUES (LBookId, InTitle, InDescription, InWorkspace, InWorkspace, now(), now(), InIpReq, false);

INSERT INTO book_detail(book_id, category_id) VALUES (LBookId, InCategoryId);

ELSIF InOption = 'M' THEN
UPDATE book
SET title                = InTitle,
    description          = InDescription,
    __workspace_update__ = InWorkspace,
    __update_date__      = now(),
    __ip_req__           = InIpReq
WHERE book_id = InBookId
  AND __deleted__ = false;

UPDATE book_detail
SET category_id = InCategoryId
WHERE book_id = InBookId
  AND __deleted__ = false;

ELSIF InOption = 'D' THEN
UPDATE book
SET __deleted__          = true,
    __workspace_update__ = InWorkspace,
    __ip_req__           = InIpReq
WHERE book_id = InBookId;

UPDATE book_detail
SET __deleted__ = true
WHERE book_id = InBookId;

END IF;

RETURN LMessage;

EXCEPTION
    WHEN OTHERS THEN
        return LMessage;

END;
$$;
