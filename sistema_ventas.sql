-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-07-2026 a las 01:51:08
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistema_ventas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `arqueo`
--

CREATE TABLE `arqueo` (
  `Cod_arqueo` int(11) NOT NULL,
  `Monto_cierre` int(11) DEFAULT NULL,
  `Monto_apertura` int(11) DEFAULT NULL,
  `Fecha_hora` varchar(45) DEFAULT NULL,
  `Empleado_Cod_empleado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `Cod_categ` int(11) NOT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  `Sector` varchar(45) DEFAULT NULL,
  `Nombre` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `CI` int(11) NOT NULL,
  `Nombres` varchar(45) DEFAULT NULL,
  `Apellidos` varchar(45) DEFAULT NULL,
  `Telefono` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contrato`
--

CREATE TABLE `contrato` (
  `Nro_contrato` int(11) NOT NULL,
  `Cuotas_totales` int(11) DEFAULT NULL,
  `Saldo_pendiente` int(11) DEFAULT NULL,
  `Interes` int(11) DEFAULT NULL,
  `Monto_total` int(11) DEFAULT NULL,
  `Fecha_emision` date DEFAULT NULL,
  `Cuotas_pagadas` int(11) DEFAULT NULL,
  `Venta_Nro_factura` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuota_pago`
--

CREATE TABLE `cuota_pago` (
  `Nro_cuota` int(11) NOT NULL,
  `Interes_mora` int(11) DEFAULT NULL,
  `Vencimiento` date DEFAULT NULL,
  `Monto` int(11) DEFAULT NULL,
  `Estado` varchar(45) DEFAULT NULL,
  `Contrato_Nro_contrato` int(11) NOT NULL,
  `Empleado_Cod_empleado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_venta`
--

CREATE TABLE `detalle_venta` (
  `Venta_Nro_factura` int(11) NOT NULL,
  `Producto_Cod_producto` int(11) NOT NULL,
  `Nro_item` int(11) NOT NULL,
  `Cantidad` int(11) DEFAULT NULL,
  `Precio_Unit` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `Cod_empleado` int(11) NOT NULL,
  `Cargo` varchar(45) DEFAULT NULL,
  `Nombres` varchar(45) DEFAULT NULL,
  `Apellidos` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `Cod_producto` int(11) NOT NULL,
  `Marca` varchar(45) DEFAULT NULL,
  `Ubicacion` varchar(45) DEFAULT NULL,
  `Modelo` varchar(45) DEFAULT NULL,
  `Estado` varchar(45) DEFAULT NULL,
  `Nro_serie` int(11) DEFAULT NULL,
  `Precio_unit` int(11) DEFAULT NULL,
  `Categoria_Cod_categ` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `Nro_factura` int(11) NOT NULL,
  `Total_pagar` int(11) NOT NULL,
  `Medio_pago` int(11) NOT NULL,
  `Fecha_emision` date DEFAULT NULL,
  `Tipo_venta` varchar(45) DEFAULT NULL,
  `Cliente_CI` int(11) NOT NULL,
  `Empleado_Cod_empleado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `arqueo`
--
ALTER TABLE `arqueo`
  ADD PRIMARY KEY (`Cod_arqueo`,`Empleado_Cod_empleado`),
  ADD KEY `fk_Arqueo_Empleado1_idx` (`Empleado_Cod_empleado`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`Cod_categ`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`CI`);

--
-- Indices de la tabla `contrato`
--
ALTER TABLE `contrato`
  ADD PRIMARY KEY (`Nro_contrato`,`Venta_Nro_factura`),
  ADD KEY `fk_Contrato_Venta1_idx` (`Venta_Nro_factura`);

--
-- Indices de la tabla `cuota_pago`
--
ALTER TABLE `cuota_pago`
  ADD PRIMARY KEY (`Nro_cuota`,`Contrato_Nro_contrato`,`Empleado_Cod_empleado`),
  ADD KEY `fk_Cuota_pago_Contrato1_idx` (`Contrato_Nro_contrato`),
  ADD KEY `fk_Cuota_pago_Empleado1_idx` (`Empleado_Cod_empleado`);

--
-- Indices de la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD PRIMARY KEY (`Venta_Nro_factura`,`Producto_Cod_producto`,`Nro_item`),
  ADD KEY `fk_Venta_has_Producto_Producto1_idx` (`Producto_Cod_producto`),
  ADD KEY `fk_Venta_has_Producto_Venta1_idx` (`Venta_Nro_factura`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`Cod_empleado`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`Cod_producto`,`Categoria_Cod_categ`),
  ADD KEY `fk_Producto_Categoria_idx` (`Categoria_Cod_categ`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`Nro_factura`,`Cliente_CI`,`Empleado_Cod_empleado`),
  ADD KEY `fk_Venta_Cliente1_idx` (`Cliente_CI`),
  ADD KEY `fk_Venta_Empleado1_idx` (`Empleado_Cod_empleado`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
