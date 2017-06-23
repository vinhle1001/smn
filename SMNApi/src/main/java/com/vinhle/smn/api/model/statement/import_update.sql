CREATE DEFINER=`root`@`localhost` PROCEDURE `import_update`(IN _import_id   INT,
                                                            IN _supplier_id INT,
                                                            IN _agency_id   INT,
                                                            IN _import_cost BIGINT(20),
                                                            IN _import_debt BIGINT(20),
                                                            IN _description VARCHAR(5000),
                                                            IN _is_active   TINYINT(1))
  BEGIN
    UPDATE smn_import
    SET
      supplier_id   = _supplier_id,
      agency_id     = _agency_id,
      import_cost   = _import_cost,
      import_debt   = _import_debt,
      description   = _description,
      modified_date = NOW(),
      is_active     = _is_active
    WHERE import_id = _import_id;
    SELECT *
    FROM smn_import
    WHERE import_id = _import_id;
  END