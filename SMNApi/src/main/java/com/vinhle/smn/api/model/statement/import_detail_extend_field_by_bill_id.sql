CREATE DEFINER=`root`@`localhost` PROCEDURE `import_detail_extend_field_by_bill_id`(IN _import_id INT)
  BEGIN
    SELECT
      import_detail.*,
      product.product_name product_name,
      product.product_size product_size
    FROM smn_import_detail import_detail
      JOIN smn_product product ON (import_detail.product_id = product.product_id)
    WHERE import_detail.import_id = _import_id;
  END