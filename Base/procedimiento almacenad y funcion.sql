DELIMITER //
CREATE PROCEDURE validar_curp_unico(
    IN p_curp VARCHAR(18),
    OUT es_valido BOOLEAN
)
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM cliente WHERE curp = p_curp;
    SET es_valido = total = 0;
END //
DELIMITER ;

DROP FUNCTION IF EXISTS contar_casos_abogado;

DELIMITER $$

CREATE FUNCTION contar_casos_abogado(id INT)
RETURNS INT
DETERMINISTIC
BEGIN
    RETURN (
        SELECT COUNT(*)
        FROM expediente_abogado
        WHERE id_abo = id
    );
END$$

DELIMITER ;

SELECT contar_casos_abogado(2);


