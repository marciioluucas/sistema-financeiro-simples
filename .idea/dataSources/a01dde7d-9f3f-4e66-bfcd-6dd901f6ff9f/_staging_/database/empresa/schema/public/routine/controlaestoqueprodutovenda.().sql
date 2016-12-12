CREATE OR REPLACE FUNCTION controlaestoqueprodutovenda () RETURNS trigger
	LANGUAGE plpgsql
AS $$
BEGIN
  IF (TG_OP = 'DELETE')
  THEN
    UPDATE produtos
    SET qtd_estoque = produtos.qtd_estoque + OLD.qtd
    WHERE pk_produto = OLD.fk_produto;
    RETURN old;
  ELSEIF (TG_OP = 'INSERT')
    THEN
      UPDATE produtos
      SET qtd_estoque = produtos.qtd_estoque - NEW.qtd
      WHERE pk_produto = NEW.fk_produto;
      RETURN new;
  END IF;
END;
$$
