CREATE DEFINER =`root`@`localhost` PROCEDURE `bill_detail_by_bill_id_and_product_ids`(IN _bill_id     INT,
                                                                                      IN _product_ids TEXT)
  BEGIN
    SELECT *
    FROM smn_bill_detail
    WHERE bill_id = _bill_id AND FIND_IN_SET(product_id, _product_ids) > 0
    ORDER BY product_id;
  END