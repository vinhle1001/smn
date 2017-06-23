CREATE DEFINER =`root`@`localhost` PROCEDURE `bill_returned_detail_get_by_bill_id`(IN _bill_id INT)
  BEGIN
    SELECT *
    FROM smn_bill_returned_detail
    WHERE bill_id = _bill_id;
  END