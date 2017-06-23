CREATE DEFINER =`root`@`localhost` PROCEDURE `agency_product_by_product_id_and_agency_id`(IN _agency_id  INT,
                                                                                          IN _product_id INT)
  BEGIN
    SELECT *
    FROM smn_agency_product;
  END