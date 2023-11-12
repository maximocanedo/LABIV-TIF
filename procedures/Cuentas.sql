USE tif;
/*
CREATE VIEW Clientes__SafeSelect AS 
SELECT 
	usuario, nombre, apellido, correo, 
    estado, fechaNacimiento, direccion, provincia, 
    nacionalidad, localidad, cuil, sexo, 
    dni, localidades.*, provincias.*, countries.*
	FROM Clientes
    INNER JOIN localidades
		ON localidades.id_loc = localidad
	INNER JOIN provincias
		ON provincias.id_provincia = provincia
	INNER JOIN countries
		ON countries.code = nacionalidad;
-- */
 /*
DROP PROCEDURE IF EXISTS cuentas__getAll;
-- =============================================
-- Autor: Máximo Canedo
-- Fecha de creación: 09/11/2023
-- Descripción: Obtener todas las cuentas bancarias de todos los clientes.
--
-- Parámetros:
--      page: Número de página, comienza desde 1.
-- 		size: Cantidad de registros por página. 
--
-- Historial de cambios:
-- 10/11/2023 Versión inicial.
-- =============================================
DELIMITER $$
CREATE PROCEDURE cuentas__getAll(IN __page INT, IN __size INT, IN __offset INT)
BEGIN
	IF __size IS NULL THEN
		SET __size = 10;
	END IF;
	IF __page IS NULL THEN
		SET __page = 0;
	END IF;
    SET __offset = (__page - 1) * __size;
	SELECT *, Clientes__SafeSelect.* 
		FROM tif.cuentas
        INNER JOIN tif.tipocuenta
			ON tipocuenta.Cod_TPCT = tif.cuentas.Cod_TPCT_CxC
        INNER JOIN Clientes__SafeSelect
			ON Clientes__SafeSelect.dni = cuentas.Dni_Cl_CxC
		LIMIT __offset, __size;
END
$$
DELIMITER ;
-- */
CALL cuentas__getAll(1,10, NULL);