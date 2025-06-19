DELIMITER //

CREATE TRIGGER asignar_abogado_si_queda_vacio
AFTER DELETE ON expediente_abogado
FOR EACH ROW
BEGIN
    DECLARE v_count INT;
    DECLARE v_abogado_id INT;

    -- Verificar si el caso se quedó sin abogados
    SELECT COUNT(*) INTO v_count
    FROM expediente_abogado
    WHERE id_cso = OLD.id_cso;

    IF v_count = 0 THEN
        -- Obtener un abogado cualquiera disponible
        SELECT id_abo INTO v_abogado_id
        FROM abogado
        ORDER BY id_abo
        LIMIT 1;

        -- Asignarlo automáticamente
        INSERT INTO expediente_abogado (id_abo, id_cso, situacion)
        VALUES (v_abogado_id, OLD.id_cso, 'ASIGNADO AUTOMATICAMENTE');
    END IF;
END;
//

DELIMITER ;


DELIMITER //

CREATE TRIGGER evitar_abogado_activo_duplicado
BEFORE INSERT ON expediente_abogado
FOR EACH ROW
BEGIN
    DECLARE v_count INT;

    -- Verificar si ya hay un abogado activo en el caso
    SELECT COUNT(*) INTO v_count
    FROM expediente_abogado
    WHERE id_cso = NEW.id_cso AND situacion = 'VIGENTE';

    IF v_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Este caso ya tiene un abogado activo.';
    END IF;
END;
//

DELIMITER ;

DELIMITER //

CREATE TRIGGER asignar_procurador_si_queda_vacio
AFTER DELETE ON expediente_procurador
FOR EACH ROW
BEGIN
    DECLARE v_count INT;
    DECLARE v_procurador_id INT;

   
    SELECT COUNT(*) INTO v_count
    FROM expediente_procurador
    WHERE id_cso = OLD.id_cso;

    IF v_count = 0 THEN
    
        SELECT id_pcr INTO v_procurador_id
        FROM procurador
        ORDER BY id_pcr
        LIMIT 1;

   
        INSERT INTO expediente_procurador (id_pcr, id_cso, situacion)
        VALUES (v_procurador_id, OLD.id_cso, 'ASIGNADO AUTOMATICAMENTE');
    END IF;
END;
//

DELIMITER ;


DELIMITER //

CREATE TRIGGER evitar_procurador_activo_duplicado
BEFORE INSERT ON expediente_procurador
FOR EACH ROW
BEGIN
    DECLARE v_count INT;

    
    SELECT COUNT(*) INTO v_count
    FROM expediente_procurador
    WHERE id_cso = NEW.id_cso AND situacion = 'VIGENTE';

    IF v_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Este caso ya tiene un procurador activo.';
    END IF;
END;
//

DELIMITER ;
