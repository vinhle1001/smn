CREATE DEFINER =`root`@`localhost` PROCEDURE `customer_get_by_id`(IN _id INT)
  BEGIN
    SELECT
      customer.*,
      genderId.name                               gender_name,
      customer_type.name                        customer_type_name,
      concat(provinceId.type, ' ', provinceId.name) province_name,
      concat(districtId.type, ' ', districtId.name) district_name,
      concat(wardId.type, ' ', wardId.name)         ward_name
    FROM smn_customer customer
      JOIN smn_gender genderId ON (customer.gender_id = genderId.id)
      JOIN smn_customer_type customer_type ON (customer.customer_type_id = customer_type.id)
      JOIN smn_province provinceId ON (customer.province_id = provinceId.id)
      LEFT JOIN smn_district districtId ON (customer.district_id = districtId.id)
      LEFT JOIN smn_ward wardId ON (customer.ward_id = wardId.id)
    WHERE customer_id = _id AND is_active > 0;
  END