-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 05-04-2021 a las 10:50:44
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gossamer`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cita`
--

CREATE TABLE `cita` (
  `id` bigint(20) NOT NULL,
  `fecha` datetime NOT NULL,
  `id_cliente` bigint(20) NOT NULL,
  `id_empleado` bigint(20) DEFAULT NULL,
  `id_local` bigint(20) NOT NULL,
  `id_hacer` bigint(20) NOT NULL,
  `terminado` tinyint(1) NOT NULL,
  `id_factura` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cita`
--

INSERT INTO `cita` (`id`, `fecha`, `id_cliente`, `id_empleado`, `id_local`, `id_hacer`, `terminado`, `id_factura`) VALUES
(1, '2021-01-13 12:35:32', 1, 1, 2, 1, 0, 1),
(2, '2021-01-13 12:35:32', 1, 1, 2, 1, 0, 1),
(3, '2021-01-29 16:29:57', 2, 1, 1, 4, 1, 2),
(4, '2021-02-03 09:07:00', 1, 2, 3, 1, 1, 2),
(5, '2021-02-03 09:07:00', 1, 1, 3, 5, 1, 2),
(6, '2021-03-07 14:30:00', 3, 6, 2, 8, 1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL,
  `dni` varchar(10) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido1` varchar(255) NOT NULL,
  `apellido2` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(512) NOT NULL,
  `email` varchar(255) NOT NULL,
  `descuento` int(11) NOT NULL,
  `token` varchar(512) NOT NULL,
  `validado` tinyint(1) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `telefono` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `dni`, `nombre`, `apellido1`, `apellido2`, `login`, `password`, `email`, `descuento`, `token`, `validado`, `activo`, `telefono`) VALUES
(1, '1234', 'Pepe', 'bot', 'bot', 'admin', 'bitnami', 'pepe@bitnami.com', 1, '1', 1, 1, 1234567),
(2, '12504755', 'Motica', 'Cassanelli', '', 'user', 'bitnami', 'motica@gmail.com', 1, '1', 1, 1, 12509855),
(3, '4056774400', 'Dionsio', 'Paredes', '', 'Dionisio', 'bitnami', 'Dionisio@gmail.com', 2, '2', 1, 1, 109755405);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id` bigint(20) NOT NULL,
  `dni` varchar(10) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido1` varchar(255) NOT NULL,
  `apellido2` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(512) NOT NULL,
  `email` varchar(255) NOT NULL,
  `token` varchar(512) NOT NULL,
  `validado` tinyint(1) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `telefono` int(20) NOT NULL,
  `id_local` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id`, `dni`, `nombre`, `apellido1`, `apellido2`, `login`, `password`, `email`, `token`, `validado`, `activo`, `telefono`, `id_local`) VALUES
(1, '1234', 'pepeEmpleado', 'Sindical', 'MasSindical', 'admin', 'bitnami', 'empleado@bitnami.com', '1', 1, 1, 1234567, 1),
(2, '4056774400', 'Dionsio', 'Paredes', 'Gomez', 'Dionisio', 'bitnami', 'Dionisio@gmail.com', '2', 1, 1, 109755405, 3),
(3, '774400', 'Paulo', 'Empanadas', 'Gomez', 'Paulo', 'bitnami', 'Dionisio@gmail.com', '2', 1, 1, 109755405, 3),
(4, '7890435', 'Carlos', 'Giner', 'Llopis', 'Carlos', 'admin', 'Carlos@gmai.com', '1', 1, 1, 664778623, 2),
(6, '10209405', 'Manuel ', 'Belgrano', 'Perez', 'ManuelPerez', 'bitnami', 'Manuel@gmail.com', '123', 1, 1, 123456, NULL),
(7, 'Y7107512C', 'Mauricio', 'Speroni', 'Gomez', 'Mauricio', 'Mauricio', 'mauricio@gmail.com', '12', 1, 1, 4520837, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id` bigint(20) NOT NULL,
  `fecha` datetime NOT NULL,
  `iva` int(11) NOT NULL,
  `id_cliente` bigint(20) NOT NULL,
  `pagado` tinyint(1) NOT NULL,
  `id_cita` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id`, `fecha`, `iva`, `id_cliente`, `pagado`, `id_cita`) VALUES
(1, '2021-01-19 12:59:31', 21, 1, 1, 1),
(2, '2021-01-19 12:59:31', 21, 1, 1, 1),
(3, '2021-03-07 14:30:00', 21, 3, 1, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `file`
--

CREATE TABLE `file` (
  `id` bigint(20) NOT NULL,
  `name` varchar(250) NOT NULL,
  `type` varchar(250) NOT NULL,
  `file` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hacer`
--

CREATE TABLE `hacer` (
  `id` bigint(20) NOT NULL,
  `id_trabajo` bigint(20) NOT NULL,
  `id_cita` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `hacer`
--

INSERT INTO `hacer` (`id`, `id_trabajo`, `id_cita`) VALUES
(1, 1, 1),
(3, 2, 1),
(5, 1, 5),
(6, 3, 5),
(7, 3, 3),
(8, 5, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `local`
--

CREATE TABLE `local` (
  `id` bigint(20) NOT NULL,
  `direccion` varchar(512) NOT NULL,
  `ciudad` varchar(255) NOT NULL,
  `provincia` varchar(255) NOT NULL,
  `codigo_postal` varchar(30) NOT NULL,
  `telefono` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `local`
--

INSERT INTO `local` (`id`, `direccion`, `ciudad`, `provincia`, `codigo_postal`, `telefono`) VALUES
(1, 'siempreviva', 'Valencia', 'Valencia', '46900', 4520837),
(2, 'J. J. Paso', 'Murcia', 'Murcia', '56000', 46517885),
(3, 'Huida del rey', 'Madrid', 'Madrid', '500', 77885);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajo`
--

CREATE TABLE `trabajo` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(512) NOT NULL,
  `precio` double(10,2) NOT NULL,
  `imagen` bigint(20) DEFAULT NULL,
  `descuento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `trabajo`
--

INSERT INTO `trabajo` (`id`, `nombre`, `descripcion`, `precio`, `imagen`, `descuento`) VALUES
(1, 'corte de pelo', 'corte de pelo', 10.00, NULL, 0),
(2, 'Afeitado', 'Estilos varios a elegir. Opcional nutrición con aceite de coco', 8.00, NULL, 0),
(3, 'lavado de cabello', 'usamos los mejores Shampoo de primera marca', 5.00, 1, 20),
(5, 'Depilacion de cejas', 'Depilacion de cejas, metodo asiatico', 5.00, NULL, 10);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cita`
--
ALTER TABLE `cita`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `hacer`
--
ALTER TABLE `hacer`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `local`
--
ALTER TABLE `local`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `trabajo`
--
ALTER TABLE `trabajo`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cita`
--
ALTER TABLE `cita`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `hacer`
--
ALTER TABLE `hacer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `local`
--
ALTER TABLE `local`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `trabajo`
--
ALTER TABLE `trabajo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
