CREATE DEFINER =`root`@`localhost` PROCEDURE `agency_get_all`()
  BEGIN
    SELECT *
    FROM smn_agency
    ORDER BY agency_name;
  END