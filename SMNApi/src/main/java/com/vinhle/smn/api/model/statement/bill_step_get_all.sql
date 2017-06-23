CREATE DEFINER =`root`@`localhost` PROCEDURE `bill_step_get_all`()
  BEGIN
    SELECT *
    FROM smn_bill_step
    WHERE is_active > 0;
  END