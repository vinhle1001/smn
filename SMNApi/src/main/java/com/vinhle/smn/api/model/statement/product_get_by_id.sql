CREATE DEFINER =`root`@`localhost` PROCEDURE `product_get_by_id`(IN _id INT)
  BEGIN
    SELECT
      product.*,
      product_type.name               product_type_name,
      product_type.icon               product_type_icon,
      product_type.notation           product_type_notation,
      agency_product.product_quantity product_quantity
    FROM smn_product product
      JOIN smn_product_type product_type ON (product.product_type_id = product_type.product_type_id)
      LEFT JOIN smn_agency_product agency_product ON (agency_product.product_id = product.product_id)
    WHERE product.product_id = _id;
  END