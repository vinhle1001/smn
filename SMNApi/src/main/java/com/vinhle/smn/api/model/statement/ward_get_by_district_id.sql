CREATE PROCEDURE `ward_get_by_district_id`(IN _districtId INT)
  BEGIN
    SELECT *
    FROM smn_ward
    WHERE district_id = _districtId AND is_active > 0;
  END