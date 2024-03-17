-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-03-2024 a las 04:57:22
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `vayven`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camiones`
--

CREATE TABLE `camiones` (
  `num_pasajeros` int(11) NOT NULL,
  `velocidad` float NOT NULL,
  `temperatura` float NOT NULL,
  `l_gasolina` float NOT NULL,
  `alarma` tinytext NOT NULL,
  `latitud` double NOT NULL,
  `longitud` double NOT NULL,
  `nom_conductor` tinytext NOT NULL,
  `ruta` int(11) NOT NULL,
  `id` bigint(20) NOT NULL,
  `id_autobus` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `camiones`
--

INSERT INTO `camiones` (`num_pasajeros`, `velocidad`, `temperatura`, `l_gasolina`, `alarma`, `latitud`, `longitud`, `nom_conductor`, `ruta`, `id`, `id_autobus`) VALUES
(10, 40, 25.2, 5.7, 'OKAY', 20.9688, -89.6234, 'Juan Efren Perez Gomez', 23, 1, 1),
(12, 12.23, 12.23, 12.23, 'STILL_OK', 20.9685, -89.62756, 'Juan Efren Perez Gomez', 23, 2, 1),
(12, 12.23, 12.23, 12.23, 'STILL_OK', 20.9685, -89.62756, 'Juan Efren Perez Gomez', 23, 3, 1),
(12, 12.23, 12.23, 12.23, 'STILL_OK', 20.9685, -89.62756, 'Juan Efren Perez Gomez', 23, 4, 1),
(12, 12.23, 12.23, 12.23, 'STILL_OK', 20.9685, -89.62756, 'Juan Efren Perez Gomez', 23, 5, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `camiones`
--
ALTER TABLE `camiones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_autobus` (`id_autobus`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `camiones`
--
ALTER TABLE `camiones`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
