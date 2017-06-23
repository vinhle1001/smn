CREATE DEFINER =`root`@`localhost` PROCEDURE `import_get_by_id`(IN _import_id INT)
  BEGIN
    SELECT *
    FROM smn_import
    WHERE import_id = _import_id;
  END