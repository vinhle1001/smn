CREATE DEFINER =`root`@`localhost` PROCEDURE `agency_product_by_product_id`(IN _product_id INT)
  BEGIN
    SELECT *
    FROM smn_agency_product
    WHERE is_active > 0;
  END