CREATE DEFINER =`root`@`localhost` PROCEDURE `account_save`(IN _id              INT,
                                                            IN _username        VARCHAR(100),
                                                            IN _password        VARCHAR(512),
                                                            IN _full_name       VARCHAR(500),
                                                            IN _first_name      VARCHAR(100),
                                                            IN _last_name       VARCHAR(100),
                                                            IN _phone_number    VARCHAR(45),
                                                            IN _email           VARCHAR(60),
                                                            IN _gender          TINYINT(1),
                                                            IN _account_type_id TINYINT(1),
                                                            IN _last_login_date DATETIME,
                                                            IN _is_active       TINYINT(1))
  BEGIN
    DECLARE v_account_id INT UNSIGNED DEFAULT 0;
    IF (_id IS NULL)
    THEN
      INSERT INTO smn_account (username, `password`, full_name, first_name, last_name, phone_number, email, genderId, account_type_id, last_login_date, created_date, modified_date, is_active)
        VALUE
        (_username, _password, _full_name, _first_name, _last_name, _phone_number, _email, _gender, _account_type_id,
                    _last_login_date, NOW(), NOW(), _is_active);
      SET v_account_id = last_insert_id();
      SELECT *
      FROM smn_account
      WHERE account_id = v_account_id;
    ELSE
      UPDATE smn_account
      SET
        username        = _username,
        `password`      = _password,
        full_name       = _full_name,
        first_name      = _first_name,
        last_name       = _last_name,
        phone_number    = _phone_number,
        email           = _email,
        genderId          = _gender,
        account_type_id = _account_type_id,
        last_login_date = _last_login_date,
        modified_date   = NOW(),
        is_active       = _is_active
      WHERE account_id = _id;
      SELECT *
      FROM smn_account
      WHERE account_id = _id;
    END IF;
  END