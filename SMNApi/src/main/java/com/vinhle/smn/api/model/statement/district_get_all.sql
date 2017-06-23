CREATE DEFINER =`root`@`localhost` PROCEDURE `district_get_all`()
  BEGIN
    SELECT *
    FROM smn_district
    WHERE is_active > 0;
  END