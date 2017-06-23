CREATE DEFINER =`root`@`localhost` PROCEDURE `supplier_save`(IN _supplier_id  INT,
                                                             IN _name         VARCHAR(500),
                                                             IN _description  VARCHAR(5000),
                                                             IN _phone_number VARCHAR(500),
                                                             IN _email        VARCHAR(100),
                                                             IN _address      VARCHAR(200),
                                                             IN _province_id  INT(11),
                                                             IN _district_id  INT(11),
                                                             IN _ward_id      INT(11),
                                                             IN _is_active    TINYINT(1))
  BEGIN
    UPDATE smn_supplier
    SET
      `name`        = _name,
      description   = _description,
      phone_number  = _phone_number,
      email         = _email,
      address       = _address,
      province_id   = _province_id,
      district_id   = _district_id,
      ward_id       = _ward_id,
      modified_date = NOW(),
      is_active     = _is_active
    WHERE supplier_id = _supplier_id;
  END