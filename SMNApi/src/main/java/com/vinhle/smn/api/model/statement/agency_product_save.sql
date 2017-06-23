CREATE DEFINER =`root`@`localhost` PROCEDURE `agency_product_save`(IN _id                         INT,
                                                                   IN _agency_id                  INT,
                                                                   IN _product_id                 INT,
                                                                   IN _product_quantity           INT,
                                                                   IN _product_beginning_quantity INT,
                                                                   IN _is_active                  TINYINT(1))
  BEGIN
    DECLARE v_agency_product_id INT UNSIGNED DEFAULT 0;
    IF (_id IS NULL)
    THEN
      INSERT INTO smn_agency_product (agency_id, product_id, product_quantity, product_beginning_quantity, created_date, modified_date, is_active)
        VALUE
        (_agency_id, _product_id, _product_beginning_quantity, _product_beginning_quantity, NOW(), NOW(), _is_active);

      SET v_agency_product_id = last_insert_id();
      SELECT *
      FROM smn_agency_product
      WHERE agency_product_id = _id;
    ELSE
      UPDATE smn_agency_product
      SET
        agency_id                  = _agency_id,
        product_id                 = _product_id,
        product_quantity           = _product_quantity,
        product_beginning_quantity = _product_beginning_quantity,
        modified_date              = NOW(),
        is_active                  = _is_active
      WHERE agency_product_id = _id;
      SELECT *
      FROM smn_agency_product
      WHERE agency_product_id = _id;
    END IF;
  END