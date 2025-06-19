-- Actualizar datos existentes en abogado
UPDATE abogado SET
    nombre = UPPER(nombre),
    curp = UPPER(curp),
    apellido_paterno = UPPER(apellido_paterno),
    apellido_materno = UPPER(apellido_materno);

-- Actualizar datos existentes en cliente
UPDATE cliente SET
    nombre = UPPER(nombre),
    curp = UPPER(curp),
    apellido_paterno = UPPER(apellido_paterno),
    apellido_materno = UPPER(apellido_materno);

-- Triggers Cliente
DROP TRIGGER IF EXISTS cliente_mayusculas_insert;
DROP TRIGGER IF EXISTS cliente_mayusculas_update;
DELIMITER //

-- Trigger para INSERT en cliente
CREATE TRIGGER cliente_mayusculas_insert
BEFORE INSERT ON cliente
FOR EACH ROW
BEGIN
    SET NEW.nombre = UPPER(NEW.nombre);
    SET NEW.curp = UPPER(NEW.curp);
    SET NEW.apellido_paterno = UPPER(NEW.apellido_paterno);
    SET NEW.apellido_materno = UPPER(NEW.apellido_materno);
END //

-- Trigger para UPDATE en cliente
CREATE TRIGGER cliente_mayusculas_update
BEFORE UPDATE ON cliente
FOR EACH ROW
BEGIN
    SET NEW.nombre = UPPER(NEW.nombre);
    SET NEW.apellido_paterno = UPPER(NEW.apellido_paterno);
    SET NEW.apellido_materno = UPPER(NEW.apellido_materno);
    SET NEW.curp = UPPER(NEW.curp);
END //

DELIMITER ;

-- Triggers abogados
DROP TRIGGER IF EXISTS abogado_mayusculas_insert;
DROP TRIGGER IF EXISTS abogado_mayusculas_update;
DELIMITER //

-- Trigger para INSERT en abogado
CREATE TRIGGER abogado_mayusculas_insert
BEFORE INSERT ON abogado
FOR EACH ROW
BEGIN
    SET NEW.nombre = UPPER(NEW.nombre);
    SET NEW.apellido_paterno = UPPER(NEW.apellido_paterno);
    SET NEW.apellido_materno = UPPER(NEW.apellido_materno);
    SET NEW.curp = UPPER(NEW.curp);
END //

-- Trigger para UPDATE en abogado
CREATE TRIGGER abogado_mayusculas_update
BEFORE UPDATE ON abogado
FOR EACH ROW
BEGIN
    SET NEW.nombre = UPPER(NEW.nombre);
    SET NEW.apellido_paterno = UPPER(NEW.apellido_paterno);
    SET NEW.apellido_materno = UPPER(NEW.apellido_materno);
    SET NEW.curp = UPPER(NEW.curp);
END //

DELIMITER ;
