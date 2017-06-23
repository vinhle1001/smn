CREATE DEFINER =`root`@`localhost` PROCEDURE `ward_get_all`()
  BEGIN
    SELECT *
    FROM smn_ward
    WHERE is_active > 0;
  END