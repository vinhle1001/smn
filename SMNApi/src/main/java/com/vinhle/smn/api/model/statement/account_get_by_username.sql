CREATE PROCEDURE `account_get_by_username`(IN _username TEXT)
  BEGIN
    SELECT *
    FROM smn_account
    WHERE username LIKE _username
    LIMIT 1;
  END