USE tif;
-- =============================================
-- Autor: Máximo Canedo
-- Fecha de creación: 09/11/2023
-- Descripción: Obtener todos los movimientos de todos los clientes.
--
-- Parámetros:
--      page: Número de página, comienza desde 1.
-- 		size: Cantidad de registros por página. 
--
-- Historial de cambios:
-- 10/11/2023 Versión inicial.
-- =============================================
DROP PROCEDURE IF EXISTS movimientos__getAll;
DELIMITER $$
CREATE PROCEDURE movimientos__getAll(IN __page INT, IN __size INT, IN __offset INT)
BEGIN
	IF __size IS NULL THEN
		SET __size = 10;
	END IF;
	IF __page IS NULL THEN
		SET __page = 0;
	END IF;
    SET __offset = (__page - 1) * __size;
	SELECT movimientos.*, cuentas.*, conceptos.*
		FROM movimientos
		INNER JOIN cuentas ON cuentas.Num_Cuenta_CxC = movimientos.num_cuenta_CxC_Mv
        INNER JOIN conceptos ON conceptos.cod_Con = movimientos.cod_Con_Mv
        INNER JOIN Clientes__SafeSelect ON cuentas.Dni_Cl_CxC = Clientes__SafeSelect.dni
        LIMIT __offset, __size;
END
$$
DELIMITER ;
-- CALL movimientos__getAll(1, 10, NULL);
-- =============================================
-- Autor: Máximo Canedo
-- Fecha de creación: 09/11/2023
-- Descripción: Obtener todos los movimientos de un cliente en específico.
--
-- Parámetros:
-- 		dni: DNI del cliente.
--      page: Número de página, comienza desde 1.
-- 		size: Cantidad de registros por página. 
--
-- Historial de cambios:
-- 10/11/2023 Versión inicial.
-- =============================================
DROP PROCEDURE IF EXISTS movimientos__getAllFromClient;
DELIMITER $$
CREATE PROCEDURE movimientos__getAllFromClient(IN _dni VARCHAR(12), IN __page INT, IN __size INT, IN __offset INT)
BEGIN
	IF __size IS NULL THEN
		SET __size = 10;
	END IF;
	IF __page IS NULL THEN
		SET __page = 0;
	END IF;
    SET __offset = (__page - 1) * __size;
	SELECT movimientos.*, cuentas.*, conceptos.*
		FROM movimientos
		INNER JOIN cuentas ON cuentas.Num_Cuenta_CxC = movimientos.num_cuenta_CxC_Mv
        INNER JOIN conceptos ON conceptos.cod_Con = movimientos.cod_Con_Mv
        INNER JOIN Clientes__SafeSelect ON cuentas.Dni_Cl_CxC = Clientes__SafeSelect.dni
        WHERE cuentas.Dni_Cl_CxC = _dni
        LIMIT __offset, __size;
END
$$
DELIMITER ;
-- CALL movimientos__getAllFromClient(NULL, 1, 10, NULL);
-- =============================================
-- Autor: Máximo Canedo
-- Fecha de creación: 09/11/2023
-- Descripción: Obtener todos los movimientos de una cuenta en específico
--
-- Parámetros:
-- 		nc: Número de cuenta.
--      page: Número de página, comienza desde 1.
-- 		size: Cantidad de registros por página. 
--
-- Historial de cambios:
-- 10/11/2023 Versión inicial.
-- =============================================
DROP PROCEDURE IF EXISTS movimientos__getAllFromAccount;
DELIMITER $$
CREATE PROCEDURE movimientos__getAllFromAccount(IN _nc VARCHAR(10), IN __page INT, IN __size INT, IN __offset INT)
BEGIN
	IF __size IS NULL THEN
		SET __size = 10;
	END IF;
	IF __page IS NULL THEN
		SET __page = 0;
	END IF;
    SET __offset = (__page - 1) * __size;
	SELECT movimientos.*, cuentas__select.*, conceptos.*, tipo_movimiento.*
		FROM movimientos
		INNER JOIN cuentas__select ON cuentas__select.Num_Cuenta_CxC = movimientos.num_cuenta_CxC_Mv
        INNER JOIN conceptos ON conceptos.cod_Con = movimientos.cod_Con_Mv
        INNER JOIN tipo_movimiento ON tipo_movimiento.cod_TPMV = movimientos.cod_TPMV_Mv
        WHERE cuentas__select.Num_Cuenta_CxC = _nc
        LIMIT __offset, __size;
END
$$
DELIMITER ;
-- CALL movimientos__getAllFromAccount(NULL, 1, 10, NULL);