CREATE DEFINER =`root`@`localhost` PROCEDURE `bill_detail_extend_field_by_bill_id`(IN _bill_id INT)
  BEGIN
    SELECT
      bill_detail.*,
      product.product_name                           product_name,
      product.product_size                           product_size,
      bill_returned_detail.product_returned_quantity product_quantity_returned,
      bill_returned_detail.refunded_cost             refunded_cost
    FROM smn_bill_detail bill_detail
      JOIN smn_product product ON (bill_detail.product_id = product.product_id)
      LEFT JOIN smn_bill_returned_detail bill_returned_detail
        ON (bill_returned_detail.bill_detail_id = bill_detail.bill_detail_id)
    WHERE bill_detail.bill_id = _bill_id;
  END