USE tif;
-- /* -- Agregar filtro de estado.
-- =============================================
-- Autor: Máximo Canedo
-- Fecha de creación: 09/11/2023
-- Descripción: Buscar clientes.
--
-- Parámetros:
-- 		q: Texto a buscar. Se busca por nombre, apellido, DNI, CUIL y correo.
-- 		provinciaId: Si no es NULL, filtra por provincia.
-- 		localidadId: Si no es NULL, filtra por localidad.
-- 		nacionalidadId: Si no es NULL, filtra por nacionalidad.
-- 		estadoB: Si no es NULL, filtra por estado de usuarios.
--      page: Número de página, comienza desde 1.
-- 		size: Cantidad de registros por página. 
--
-- Historial de cambios:
-- 09/11/2023 Versión inicial.
-- 10/11/2023 Solución a problema con filtros
-- =============================================
DROP PROCEDURE IF EXISTS clientes__search;
DELIMITER $$
CREATE PROCEDURE clientes__search(
	IN q VARCHAR(255),
    IN provinciaId INT(11),
    IN localidadId INT(11),
    IN sexoValue VARCHAR(3),
    IN nacionalidadId VARCHAR(2),
    IN estadoB BIT,
    IN __page INT,
    IN __size INT,
    __offset INT
)
BEGIN
    IF __size IS NULL THEN
		SET __size = 10;
	END IF;
	IF __page IS NULL THEN
		SET __page = 0;
	END IF;
    SET __offset = (__page - 1) * __size;

	SELECT *, localidades.*, provincias.*, countries.* 
		FROM clientes
		INNER JOIN localidades 
			ON localidades.id_loc = clientes.localidad
		INNER JOIN provincias 
			ON provincias.id_provincia = clientes.provincia
		INNER JOIN countries 
			ON countries.code = clientes.nacionalidad
		WHERE 
			(q IS NOT NULL AND (
				clientes.nombre LIKE CONCAT('%', q, '%') OR
                clientes.apellido LIKE CONCAT('%', q, '%') OR
                clientes.dni LIKE CONCAT('%', q, '%') OR
                clientes.cuil LIKE CONCAT('%', q, '%') OR
                clientes.direccion LIKE CONCAT('%', q, '%') OR
                clientes.correo LIKE CONCAT('%', q, '%')
            )) AND
            ((provinciaId IS NULL) OR (clientes.provincia = provinciaId)) AND
			((localidadId IS NULL) OR (clientes.localidad = localidadId)) AND
			((nacionalidadId IS NULL) OR (clientes.nacionalidad = nacionalidadId)) AND
			((sexoValue IS NULL) OR (clientes.sexo = sexoValue)) AND
            ((estadoB IS NULL) OR (clientes.estado = estadoB))
		LIMIT __offset, __size
    ;
END
$$
DELIMITER ;
-- */
CALL clientes__search("", NULL, NULL, NULL, NULL, 1, 1, 10, NULL);
