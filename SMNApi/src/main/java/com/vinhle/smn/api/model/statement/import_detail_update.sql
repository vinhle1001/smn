CREATE DEFINER =`root`@`localhost` PROCEDURE `import_detail_update`(IN _import_detail_id INT,
                                                                    IN _import_id        INT,
                                                                    IN _product_id       INT,
                                                                    IN _product_quantity INT,
                                                                    IN _product_cost     BIGINT(20),
                                                                    IN _description      VARCHAR(5000),
                                                                    IN _is_active        TINYINT(1))
  BEGIN
    UPDATE smn_import_detail
    SET
      import_id        = _import_id,
      product_id       = _product_id,
      product_quantity = _product_quantity,
      product_cost     = _product_cost,
      description      = _description,
      modified_date    = NOW(),
      is_active        = _is_active
    WHERE import_detail_id = _import_detail_id;
    SELECT *
    FROM smn_import_detail
    WHERE import_detail_id = _import_detail_id;
  END