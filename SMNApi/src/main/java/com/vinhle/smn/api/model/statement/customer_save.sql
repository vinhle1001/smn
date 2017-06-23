CREATE DEFINER =`root`@`localhost` PROCEDURE `customer_save`(IN _id               INT,
                                                             IN _full_name        VARCHAR(500),
                                                             IN _first_name       VARCHAR(100),
                                                             IN _last_name        VARCHAR(100),
                                                             IN _email            VARCHAR(100),
                                                             IN _facebook         VARCHAR(100),
                                                             IN _phone_number     VARCHAR(500),
                                                             IN _gender_id        TINYINT(1),
                                                             IN _birthday         DATE,
                                                             IN _address          VARCHAR(200),
                                                             IN _province_id      INT,
                                                             IN _district_id      INT,
                                                             IN _ward_id          INT,
                                                             IN _customer_type_id TINYINT(1),
                                                             IN _customer_note    VARCHAR(5000),
                                                             IN _is_active        TINYINT(1))
  BEGIN
    DECLARE v_customer_id INT UNSIGNED DEFAULT 0;

    IF (_id IS NULL)
    THEN
      INSERT INTO smn_customer (full_name, first_name, last_name, email, facebook, _gender_id, birthday, address, `phone_number`, province_id, district_id, ward_id, customer_type_id, customer_note, created_date, modified_date, is_active)
        VALUE (_full_name, _first_name, _last_name, _email, _facebook, _gender_id, _birthday, _address, _phone_number,
                           _province_id, _district_id, _ward_id, _customer_type_id, _customer_note, NOW(), NOW(),
               _is_active);
      SET v_customer_id = last_insert_id();
      SELECT *
      FROM smn_customer
      WHERE customer_id = v_customer_id;
    ELSE
      UPDATE smn_customer
      SET
        full_name        = _full_name,
        first_name       = _first_name,
        last_name        = _last_name,
        email            = _email,
        facebook         = _facebook,
        `phone_number`     = _phone_number,
        gender_id           = _gender_id,
        birthday         = _birthday,
        address          = _address,
        province_id      = _province_id,
        district_id      = _district_id,
        ward_id          = _ward_id,
        customer_type_id = _customer_type_id,
        customer_note    = _customer_note,
        modified_date    = NOW(),
        is_active        = _is_active
      WHERE customer_id = _id;
      SELECT *
      FROM smn_customer
      WHERE customer_id = _id;
    END IF;

  END