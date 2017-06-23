CREATE DEFINER =`root`@`localhost` PROCEDURE `supplier_extend_filed_get_by_id`(IN _supplier_id INT)
  BEGIN
    SELECT
      supplier.*,
      concat(province.type, ' ', province.name) province_name,
      concat(district.type, ' ', district.name) district_name,
      concat(ward.type, ' ', ward.name)         ward_name
    FROM smn_supplier supplier
      JOIN smn_province province ON (supplier.province_id = province.province_id)
      LEFT JOIN smn_district district ON (supplier.district_id = district.district_id)
      LEFT JOIN smn_ward ward ON (supplier.ward_id = ward.ward_id)
    WHERE supplier_id = _supplier_id;
  END