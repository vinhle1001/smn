CREATE DEFINER =`root`@`localhost` PROCEDURE `bill_save`(IN _bill_id      INT,
                                                         IN _bill_code    VARCHAR(25),
                                                         IN _customer_id  INT,
                                                         IN _agency_id    INT,
                                                         IN _bill_price   BIGINT(20),
                                                         IN _address      VARCHAR(255),
                                                         IN _bill_step_id INT,
                                                         IN _bill_type_id INT,
                                                         IN _description  VARCHAR(500),
                                                         IN _province_id  INT,
                                                         IN _district_id  INT,
                                                         IN _ward_id      INT,
                                                         IN _is_active    TINYINT(1))
  BEGIN
    UPDATE smn_bill
    SET
      bill_code     = _bill_code,
      customer_id   = _customer_id,
      agency_id     = _agency_id,
      bill_price    = _bill_price,
      address       = _address,
      bill_step_id  = _bill_step_id,
      bill_type_id  = _bill_type_id,
      description   = _description,
      province_id   = _province_id,
      district_id   = _district_id,
      ward_id       = _ward_id,
      modified_date = NOW(),
      is_active     = _is_active
    WHERE bill_id = _bill_id;
    SELECT *
    FROM smn_bill_detail
    WHERE bill_id = _bill_id;
  END