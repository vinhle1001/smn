CREATE DEFINER =`root`@`localhost` PROCEDURE `product_get_by_ids`(IN _product_ids TEXT)
  BEGIN
    SELECT *
    FROM smn_product
    WHERE FIND_IN_SET(product_id, _product_ids) > 0;
  END