CREATE DEFINER =`root`@`localhost` PROCEDURE `province_get_all`()
  BEGIN
    SELECT *
    FROM smn_province
    WHERE is_active > 0;
  END