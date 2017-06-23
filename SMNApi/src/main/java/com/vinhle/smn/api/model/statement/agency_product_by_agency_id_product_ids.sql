CREATE DEFINER =`root`@`localhost` PROCEDURE `agency_product_by_agency_id_product_ids`(IN _agency_id   INT,
                                                                                       IN _product_ids TEXT)
  BEGIN
    SELECT *
    FROM smn_agency_product
    WHERE agency_id = _agency_id AND FIND_IN_SET(product_id, _product_ids) > 0 AND is_active > 0
    ORDER BY product_id;
  END