CREATE DEFINER =`root`@`localhost` PROCEDURE `import_extend_field_by_supplier_id`(IN _supplier_id INT)
  BEGIN
    SELECT
      `import`.*,
      supplier.name supplier_name,
      agency.agency_name
    FROM smn_import `import`
      JOIN smn_supplier supplier ON (`import`.supplier_id = supplier.supplier_id)
      JOIN smn_agency agency ON (`import`.agency_id = agency.agency_id)
    WHERE `import`.supplier_id = _supplier_id;
  END