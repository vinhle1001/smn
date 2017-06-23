CREATE DEFINER =`root`@`localhost` PROCEDURE `bill_returned_detail_extend_field_by_bill_id`(IN _bill_id INT)
  BEGIN
    SELECT
      bill_returned_detail.bill_returned_detail_id,
      bill_detail.bill_detail_id,
      bill_detail.bill_id,
      bill_returned_detail.product_returned_quantity,
      bill_returned_detail.refunded_cost,
      bill_returned_detail.description,
      bill_returned_detail.created_date,
      bill_returned_detail.created_by,
      bill_returned_detail.modified_date,
      product.product_name,
      bill_detail.product_quantity,
      bill_returned_detail.is_active
    FROM smn_bill_detail bill_detail
      JOIN smn_product product ON (bill_detail.product_id = product.product_id)
      LEFT JOIN smn_bill_returned_detail bill_returned_detail
        ON (bill_returned_detail.bill_detail_id = bill_detail.bill_detail_id)
    WHERE bill_detail.bill_id = _bill_id;
  END