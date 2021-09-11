-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-09-2021 a las 23:35:08
-- Versión del servidor: 10.4.20-MariaDB
-- Versión de PHP: 7.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `challenge_disney`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

CREATE TABLE `genero` (
  `id_genero` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `imagen` varchar(500) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`id_genero`, `nombre`, `imagen`) VALUES
(1, 'Animacion', 'https://i.postimg.cc/5yRqCWjv/inside-out.jpg'),
(2, 'Realismo Magico', 'https://i.postimg.cc/4NwRj8qc/realismo-magico-2-e1560393953305.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `participacion`
--

CREATE TABLE `participacion` (
  `id_participacion` int(11) NOT NULL,
  `id_personaje` int(11) NOT NULL,
  `id_pelicula` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `participacion`
--

INSERT INTO `participacion` (`id_participacion`, `id_personaje`, `id_pelicula`) VALUES
(2, 2, 1),
(3, 3, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pelicula`
--

CREATE TABLE `pelicula` (
  `id_pelicula` int(11) NOT NULL,
  `imagen` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `titulo` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fecha_creacion` date NOT NULL,
  `calificacion` int(11) NOT NULL,
  `id_genero` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `pelicula`
--

INSERT INTO `pelicula` (`id_pelicula`, `imagen`, `titulo`, `fecha_creacion`, `calificacion`, `id_genero`) VALUES
(1, 'https://i.postimg.cc/Sxpg48Yp/maxresdefault.jpg', 'El cumpleaños de mickey', '2012-04-10', 5, 1),
(2, 'https://i.postimg.cc/qBPLcnLC/images.jpg', 'Goofy e Hijo', '1995-04-07', 8, 1),
(4, 'https://i.postimg.cc/hP899YvH/scale.jpg', 'Mickey,Donald,Goofy: Los Tres Mosqueteros', '2004-08-17', 4, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personaje`
--

CREATE TABLE `personaje` (
  `id_personaje` int(11) NOT NULL,
  `imagen` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `edad` int(11) NOT NULL,
  `peso` double(10,0) NOT NULL,
  `historia` varchar(100) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `personaje`
--

INSERT INTO `personaje` (`id_personaje`, `imagen`, `nombre`, `edad`, `peso`, `historia`) VALUES
(2, 'https://i.postimg.cc/5NSwwzGp/goofy-1568726558.jpg', 'Goofy', 18, 20, 'Amigo de mickey mouse'),
(3, 'https://i.postimg.cc/HLDcRsfj/42fd93bd743b42853d2806457f85d364.jpg', 'Minney Mouse', 12, 30, 'Es la eterna novia de Mickey Mouse');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`id_genero`);

--
-- Indices de la tabla `participacion`
--
ALTER TABLE `participacion`
  ADD PRIMARY KEY (`id_participacion`),
  ADD KEY `FK_PERSONAJE` (`id_personaje`),
  ADD KEY `FK_PELICULA` (`id_pelicula`);

--
-- Indices de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  ADD PRIMARY KEY (`id_pelicula`),
  ADD KEY `FK_GENERO` (`id_genero`);

--
-- Indices de la tabla `personaje`
--
ALTER TABLE `personaje`
  ADD PRIMARY KEY (`id_personaje`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `genero`
--
ALTER TABLE `genero`
  MODIFY `id_genero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `participacion`
--
ALTER TABLE `participacion`
  MODIFY `id_participacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  MODIFY `id_pelicula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `personaje`
--
ALTER TABLE `personaje`
  MODIFY `id_personaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `participacion`
--
ALTER TABLE `participacion`
  ADD CONSTRAINT `FK_PELICULA` FOREIGN KEY (`id_pelicula`) REFERENCES `pelicula` (`id_pelicula`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_PERSONAJE` FOREIGN KEY (`id_personaje`) REFERENCES `personaje` (`id_personaje`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `pelicula`
--
ALTER TABLE `pelicula`
  ADD CONSTRAINT `FK_GENERO` FOREIGN KEY (`id_genero`) REFERENCES `genero` (`id_genero`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
