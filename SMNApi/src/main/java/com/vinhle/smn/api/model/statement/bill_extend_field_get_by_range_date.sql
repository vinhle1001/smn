CREATE DEFINER =`root`@`localhost` PROCEDURE `bill_extend_field_get_by_range_date`(IN _timeStart DATETIME,
                                                                                   IN _timeEnd   DATETIME)
  BEGIN
    SELECT
      bill.*,
      customer.full_name                        customer_name,
      concat(province.type, ' ', province.name) province_name,
      concat(district.type, ' ', district.name) district_name,
      concat(ward.type, ' ', ward.name)         ward_name,
      agency.agency_name                        agency_name
    FROM smn_bill bill
      JOIN smn_customer customer ON (customer.customer_id = bill.customer_id)
      JOIN smn_agency agency ON (bill.agency_id = agency.agency_id)
      JOIN smn_province province ON (province.province_id = bill.province_id)
      JOIN smn_district district ON (district.district_id = bill.district_id)
      LEFT JOIN smn_ward ward ON (ward.ward_id = bill.ward_id)
    WHERE bill.created_date <= _timeEnd AND bill.created_date >= _timeStart
    /*ORDER BY bill.created_date DESC*/;
  END