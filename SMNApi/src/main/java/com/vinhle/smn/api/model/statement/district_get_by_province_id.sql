CREATE PROCEDURE `district_get_by_province_id`(IN _provinceId INT)
  BEGIN
    SELECT *
    FROM smn_district
    WHERE province_id = _provinceId AND is_active > 0;
  END