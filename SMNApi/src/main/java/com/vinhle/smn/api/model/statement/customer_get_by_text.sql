CREATE DEFINER =`root`@`localhost` PROCEDURE `customer_get_by_text`(IN _text TEXT)
BEGIN
DECLARE search TEXT DEFAULT '';
SET search = concat('%', _text, '%');

SELECT *
FROM smn_customer C
  JOIN smn_customer_has_phone CHP ON (C.customer_id = CHP.customer_id)
WHERE C.full_name LIKE search OR CHP.phone_number LIKE search;
END