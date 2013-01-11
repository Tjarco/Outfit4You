-- phpMyAdmin SQL Dump
-- version 3.4.11.1
-- http://www.phpmyadmin.net
--
-- Machine: localhost
-- Genereertijd: 11 jan 2013 om 09:30
-- Serverversie: 5.1.63
-- PHP-Versie: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Databank: `vernondg_winkelApp2012`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `categorie_id` int(11) NOT NULL,
  `naam` varchar(50) NOT NULL,
  `prijs` double NOT NULL,
  `omschrijving` text NOT NULL,
  `datum_aangemaakt` varchar(50) NOT NULL DEFAULT '0',
  `datum_gewijzigd` varchar(50) NOT NULL DEFAULT '0',
  `sku` int(11) NOT NULL,
  `omschrijving_kort` varchar(400) NOT NULL,
  `voorraad` int(11) NOT NULL,
  `afbeelding` varchar(300) NOT NULL,
  `thumbnail` varchar(300) NOT NULL,
  `is_actief` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=41 ;

--
-- Gegevens worden uitgevoerd voor tabel `product`
--

INSERT INTO `product` (`product_id`, `categorie_id`, `naam`, `prijs`, `omschrijving`, `datum_aangemaakt`, `datum_gewijzigd`, `sku`, `omschrijving_kort`, `voorraad`, `afbeelding`, `thumbnail`, `is_actief`) VALUES
(1, 1, 'Een Puma shirt', 5, 'Jaa, een echte puma', '0', '0', 0, '', 4, '', '', 1),
(2, 1, 'Paradise', 30, 'Een mooi paradise t-shirt', '0', '0', 0, '', 1, '', '', 1),
(3, 2, 'Cars', 4.25, 'Een handige Cars tuinbroek voor in de tuin!', '0', '0', 0, '', 16, '', '', 1),
(4, 2, 'Rode driekwart zomerbroek', 25.65, 'super fancy driekwart broek', '0', '0', 0, '', 21, '', '', 1),
(5, 2, 'Gele driekwart zomerbroek', 13, 'Met deze gele broek ben jij het echt helemaal deze zomer!', '0', '0', 0, '', 12, '', '', 1),
(6, 2, 'Paarse zwembroek', 3, 'Een paarse zwembroek ', '0', '0', 0, '', 50, '', '', 0),
(10, 2, 'Heren Onderbroek', 2.5, 'Onderbroeken in setjes van 3, in de kleuren Geel, oranje en bruin.', '0', '0', 0, '', 5, '', '', 0),
(11, 2, 'Dames onderbroek', 3.5, 'Onderbroeken in setjes van 5, in de kleuren zwart, goud en zilver', '0', '0', 0, '', 2, '', '', 0),
(12, 2, 'Heren spijkerbroek ', 7.9, 'In de kleuren blauw, grijs en geel verkrijgbaar!', '0', '0', 0, '', 10, '', '', 0),
(13, 2, 'Dames spijkerbroek', 8.9, 'Afgewerkt met zilveren strips aan de achterkant', '0', '0', 0, '', 14, '', '', 0),
(14, -1, 'test', 15, 'test', '0', '0', 15, 'test', 50, '', '', 0),
(15, 1, 'Blauwe Polo', 12, 'Een mooie blauwe polo voor zomerse dagen. Nu voor een zacht prijsje!', '0', '0', 234, '-', 45, 'null', 'null', 1),
(16, 1, 'simpel groen shirt', 7, 'Goedkoop simpel shirt, maar van degelijke kwaliteit.', '0', '0', 456545, '.', 20, 'null', 'null', 1),
(17, 1, 'Piet Parra kers', 30, 'Kers van Piet Parra', '0', '0', 0, 'Parra', 2, 'null', 'null', 1),
(18, 1, 'Piet Parra Wallow', 30, 'Wallow in self pity van Piet Parra', '0', '0', 0, 'Parra', 3, 'null', 'null', 1),
(19, 1, 'Piet Parra Roquewell', 30, 'Le Roque Welle van Piet Parra', '0', '0', 0, 'Parra', 3, 'null', 'null', 1),
(20, 1, 'Piet Parra Think Self', 30, 'Tunk Self van Piet Parra', '0', '0', 0, 'Parra', 3, 'null', 'null', 1),
(21, 1, 'Piet Parra Wallow', 30, 'Wallow in self pity van Piet Parra', '0', '0', 0, 'Parra	', 3, 'null', 'null', 1),
(22, 1, 'Grijs met lange kol', 60, 'Grijs shirt met lange mouwen en lange kol. weijzih', '2013-01-11 12:03:57', '2013-01-11 12:03:57', 0, 'Lange kol', 1, '', '', 1),
(23, 1, 'Donker blauw lange kol', 60, 'Donker blauw shirt met lange kol en lange mouwen.', '0', '0', 0, 'Lange kol', 6, 'null', 'null', 1),
(24, 1, 'Roze met lange kol', 50, 'Roze shirt met korte mouwen en lange kol.', '0', '0', 0, 'Korte mouwen lange kol', 3, 'null', 'null', 1),
(25, 1, 'Wit blauw gestreept', 30, 'Donker blauw met wit gestreept t-shirt met lange mouwen.', '0', '0', 0, 'Marine', 2, 'null', 'null', 1),
(26, 2, 'The Hunger Games', 9.99, 'Scifi-drama waarin Katniss (Jennifer Lawrence) deelneemt aan de jaarlijkse Hunger Games op televisie, waarin tieners uit twaalf districten vechten tot de dood. Gebaseerd op het boek The Hunger Games van Suzanne Collins.', '621343396', '621343396', 0, 'null', 50, 'null', 'null', 0),
(27, 2, 'Korte Spijkerbroek', 14, 'Een korte spijkerbroek voor de mensen die in de zomer ook spijkerkleding willen dragen', '2013-01-11 12:10:42', '2013-01-11 12:10:42', 0, 'null', 10, 'srcpictures67598.png', 'srcpictures67598.png', 1),
(28, 1, 'Monster shirt', 15, 'Een shirt waarmee je iedereen de stuipen op het lijf jaagt!', '2013-01-10 18:42:52', '2013-01-10 18:42:52', 0, 'null', 10, 'srcpictures97957.png', 'srcpictures97957.png', 1),
(29, 2, 'Een paarse broek', 20, 'Een paarse broek voor mensen die graag opvallen!', '2013-01-11 13:04:22', '2013-01-11 13:04:22', 0, 'null', 10, 'srcpictures30446.png', 'srcpictures30446.png', 1),
(30, 4, 'Nikes', 80, 'Tijdsloze standaard gympen!', '2013-01-10 23:01:18', '2013-01-10 23:01:18', 0, 'null', 10, '', 'srcpictures84591.jpg', 1),
(31, 4, 'Oranje Nikes', 100, 'Opvallende schoenen voor mensen die willen opvallen!', '2013-01-10 23:04:52', '2013-01-10 23:04:52', 0, 'null', 5, 'srcpictures43835.jpg', 'srcpictures43835.jpg', 1),
(32, 5, 'Blauwe zomerjas', 40, 'Een voordelige zomerjas van een degelijke kwaliteit!', '2013-01-10 23:09:52', '2013-01-10 23:09:52', 0, 'null', 20, '', '', 1),
(33, 5, 'Leren jas', 200, 'Een echt lederen jas, voor de koude winterdagen!', '2013-01-10 23:11:36', '2013-01-10 23:11:36', 0, 'null', 5, 'srcpictures18843.jpg', 'srcpictures18843.jpg', 1),
(34, 3, 'geruit overhemd', 15, 'Een casual geruit overhemd. Past overal bij!', '2013-01-10 23:12:05', '2013-01-10 23:12:05', 0, 'null', 20, '', '', 1),
(35, 3, 'Houthakkers hemd', 20, 'Een mooi houthakkershemd, net even iets warmer dan een ander hemd!', '2013-01-10 23:13:22', '2013-01-10 23:13:22', 0, 'null', 10, 'srcpictures183.jpg', 'srcpictures183.jpg', 1),
(36, 4, 'Vans', 50, 'Casual vans.', '2013-01-10 23:15:18', '2013-01-10 23:15:18', 0, 'null', 10, 'srcpictures44804.jpg', 'srcpictures44804.jpg', 1),
(37, 3, 'Gestreept overhemd', 20, 'Een mooi gestreept overhemd.', '2013-01-10 23:23:12', '2013-01-10 23:23:12', 0, 'null', 20, 'srcpictures88191.jpg', 'srcpictures88191.jpg', 1),
(38, 5, 'Rode winterjas', 80, 'Een voordelige dikke winterjas. Erg warm en van een degelijke kwaliteit', '2013-01-10 23:23:53', '2013-01-10 23:23:53', 0, 'null', 20, '', '', 1),
(39, 4, 'budged schoenen', 15, 'Zeer goedkope schoenen, maar van degelijke kwaliteit', '2013-01-10 23:24:30', '2013-01-10 23:24:30', 0, 'null', 20, '', '', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
