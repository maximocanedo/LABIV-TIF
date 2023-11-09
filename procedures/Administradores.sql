-- Procedimientos almacenados de ADMINISTRADORES

DROP PROCEDURE IF EXISTS administradores__getAll_paginated;
DROP PROCEDURE IF EXISTS administradores__getAll;
DROP PROCEDURE IF EXISTS administradores__getByUsername;


-- =============================================
-- Autor: Máximo Canedo
-- Fecha de creación: 08/11/2023
-- Descripción: Obtener todas las cuentas de administradores (Paginado)
-- Parámetros:
--   @__offset: Offset. En general es Número de página * tamaño de página.
--   @__size: Tamaño de página.
--
-- Historial de cambios:
-- 08/11/2023 Versión inicial.
-- =============================================
DELIMITER //
CREATE PROCEDURE administradores__getAll_paginated(IN __offset INT, IN __size INT)
BEGIN
    SELECT *, localidades.*, provincias.*, countries.* 
	FROM administradores 
		CROSS JOIN (SELECT CEIL(COUNT(*) / __size) AS totalPages FROM tif.administradores) AS totalPages
		INNER JOIN localidades 
			ON administradores.localidad_admin = localidades.id_loc 
		INNER JOIN provincias 
			ON administradores.provincia_admin = provincias.id_provincia 
		INNER JOIN countries 
			ON countries.code = administradores.nacionalidad_admin
		LIMIT __offset, __size;
END//
DELIMITER ;


-- =============================================
-- Autor: Máximo Canedo
-- Fecha de creación: 08/11/2023
-- Descripción: Obtener todas las cuentas de administradores.
--
-- Historial de cambios:
-- 08/11/2023 Versión inicial.
-- =============================================
DELIMITER //
CREATE PROCEDURE administradores__getAll()
BEGIN
    SELECT *, localidades.*, provincias.*, countries.* 
	FROM administradores 
		INNER JOIN localidades 
			ON administradores.localidad_admin = localidades.id_loc 
		INNER JOIN provincias 
			ON administradores.provincia_admin = provincias.id_provincia 
		INNER JOIN countries 
			ON countries.code = administradores.nacionalidad_admin;
END//
DELIMITER ;

-- =============================================
-- Autor: Máximo Canedo
-- Fecha de creación: 08/11/2023
-- Descripción: Obtener cuenta de administrador por nombre de usuario.
-- Parámetros:
--   @username: Nombre de usuario.
--
-- Historial de cambios:
-- 09/11/2023 Versión inicial.
-- =============================================
DELIMITER //
CREATE PROCEDURE administradores__getByUsername(IN username VARCHAR(20))
BEGIN
    SELECT *, localidades.*, provincias.*, countries.* 
	FROM administradores 
		INNER JOIN localidades 
			ON administradores.localidad_admin = localidades.id_loc 
		INNER JOIN provincias 
			ON administradores.provincia_admin = provincias.id_provincia 
		INNER JOIN countries 
			ON countries.code = administradores.nacionalidad_admin
		WHERE administradores.usuario_admin = username;
END//
DELIMITER ;