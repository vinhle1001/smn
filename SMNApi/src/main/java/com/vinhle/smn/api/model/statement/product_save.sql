CREATE DEFINER =`root`@`localhost` PROCEDURE `product_save`(IN _id              INT,
                                                            IN _product_code    VARCHAR(20),
                                                            IN _product_name    VARCHAR(20),
                                                            IN _product_price   BIGINT(20),
                                                            IN _cost_of_import  BIGINT(20),
                                                            IN _cost_of_order   BIGINT(20),
                                                            IN _product_size    VARCHAR(45),
                                                            IN _product_type_id INT,
                                                            IN _description     VARCHAR(500),
                                                            IN _is_active       TINYINT(1))
  BEGIN
    DECLARE v_product_id INT UNSIGNED DEFAULT 0;
    IF (_id IS NULL)
    THEN
      INSERT INTO smn_product (product_code, product_name, product_price, cost_of_import, cost_of_order, product_size, product_type_id, description, created_date, modified_date, is_active)
        VALUE
        (_product_code, _product_name, _product_price, _cost_of_import, _cost_of_order, _product_size, _product_type_id,
                        _description, NOW(), NOW(), _is_active);
      SET v_product_id = last_insert_id();
      SELECT *
      FROM smn_product
      WHERE product_id = v_product_id;
    ELSE
      UPDATE smn_product
      SET
        product_code    = _product_code,
        product_name    = _product_name,
        product_price   = _product_price,
        cost_of_import  = _cost_of_import,
        cost_of_order   = _cost_of_order,
        product_size    = _product_size,
        product_type_id = _product_type_id,
        description     = _description,
        modified_date   = NOW(),
        is_active       = _is_active
      WHERE product_id = _id;
      SELECT *
      FROM smn_product
      WHERE product_id = _id;
    END IF;
  END