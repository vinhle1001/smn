CREATE DEFINER =`root`@`localhost` PROCEDURE `import_extend_field_by_import_id`(IN _import_id INT)
  BEGIN
    SELECT
      `import`.*,
      supplier.name supplier_name,
      agency.agency_name
    FROM smn_import `import`
      JOIN smn_supplier supplier ON (`import`.supplier_id = supplier.supplier_id)
      JOIN smn_agency agency ON (`import`.agency_id = agency.agency_id)
    WHERE `import`.import_id = _import_id;
  END