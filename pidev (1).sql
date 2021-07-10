-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 01 mars 2019 à 03:07
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `pidev`
--

-- --------------------------------------------------------

--
-- Structure de la table `action_benevole`
--

DROP TABLE IF EXISTS `action_benevole`;
CREATE TABLE IF NOT EXISTS `action_benevole` (
  `id_action` int(11) NOT NULL AUTO_INCREMENT,
  `type_action` varchar(255) NOT NULL,
  `date_d_action` date NOT NULL,
  `date_f_action` date NOT NULL,
  `Description` varchar(255) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_action`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `action_benevole`
--

INSERT INTO `action_benevole` (`id_action`, `type_action`, `date_d_action`, `date_f_action`, `Description`, `id_user`) VALUES
(2, 'santé', '2019-02-22', '2019-02-23', 'hhghhg', 10);

-- --------------------------------------------------------

--
-- Structure de la table `demande`
--

DROP TABLE IF EXISTS `demande`;
CREATE TABLE IF NOT EXISTS `demande` (
  `id_demande` int(11) NOT NULL AUTO_INCREMENT,
  `id_maison` int(11) DEFAULT NULL,
  `categorie_demande` varchar(255) NOT NULL,
  `quantite_demande` int(11) NOT NULL,
  `description_demande` varchar(255) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_demande`),
  KEY `id_demande` (`id_demande`),
  KEY `demande_ibfk_1` (`id_maison`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `demande`
--

INSERT INTO `demande` (`id_demande`, `id_maison`, `categorie_demande`, `quantite_demande`, `description_demande`, `id_user`) VALUES
(4, 50, 'medicaments', 10, 'm', 27);

-- --------------------------------------------------------

--
-- Structure de la table `don`
--

DROP TABLE IF EXISTS `don`;
CREATE TABLE IF NOT EXISTS `don` (
  `id_don` int(11) NOT NULL AUTO_INCREMENT,
  `categorie_don` varchar(255) NOT NULL,
  `quantite_don` int(255) NOT NULL,
  `description_don` varchar(255) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_demande` int(11) NOT NULL,
  PRIMARY KEY (`id_don`),
  KEY `id_demande` (`id_demande`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `don`
--

INSERT INTO `don` (`id_don`, `categorie_don`, `quantite_don`, `description_don`, `id_user`, `id_demande`) VALUES
(2, 'Vetement', 30, 'pull', 1, 4),
(3, 'nourriture', 40, 'lait', 1, 4);

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
CREATE TABLE IF NOT EXISTS `evenement` (
  `id_evenement` int(11) NOT NULL AUTO_INCREMENT,
  `date_d_evenement` date NOT NULL,
  `date_f_evenement` date NOT NULL,
  `heure_evenement` varchar(255) NOT NULL,
  `nom_evenement` varchar(255) NOT NULL,
  `adresse_evenement` varchar(255) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `description_evenement` varchar(255) DEFAULT NULL,
  `id_maison` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_evenement`),
  KEY `id_user` (`id_user`),
  KEY `id_maison` (`id_maison`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`id_evenement`, `date_d_evenement`, `date_f_evenement`, `heure_evenement`, `nom_evenement`, `adresse_evenement`, `id_user`, `description_evenement`, `id_maison`) VALUES
(12, '2019-02-19', '2019-02-19', '17h', 'visite des enfants', 'ariana', NULL, 'loisirs', NULL),
(30, '2019-02-20', '2019-02-20', '15h', 'yoga', 'Sfax', NULL, 'meditation', NULL),
(31, '2019-02-14', '2019-02-14', '18h', 'journée des jeux mentales', 'tunis', NULL, 'loisirs', NULL),
(32, '2019-02-14', '2019-02-16', '18h', 'projection film', 'elghazela', NULL, 'culturel', NULL),
(33, '2019-02-13', '2019-02-13', '14h', 'traditions tunisiennes', 'Bizerte', NULL, 'traditionnel', NULL),
(34, '2019-02-21', '2019-02-22', '16h', 'jeux', 'tunis', NULL, 'loisirs', NULL),
(39, '2019-02-14', '2019-03-08', '44', 'rrrrr', 'rrrrrrrr', NULL, 'tggggggg', NULL),
(40, '2019-02-06', '2019-02-21', '14', 'rrrr', 'rrrrrr', NULL, 'ggggggggggg', NULL),
(41, '2019-02-13', '2019-02-21', 'sssss', 'yyyyy', 'sss', NULL, 'dddddddddddd', NULL),
(42, '2019-02-01', '2019-02-08', '1', 'aaaaaaaaa', 'aaaaaaaaaaaaaaa', NULL, 'aaaaaaaaaaaa', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `fiche_medicale`
--

DROP TABLE IF EXISTS `fiche_medicale`;
CREATE TABLE IF NOT EXISTS `fiche_medicale` (
  `id_fiche` int(11) NOT NULL AUTO_INCREMENT,
  `date_creation_fiche` date NOT NULL,
  `date_dermodif_fiche` date NOT NULL,
  `remarques_fiche` varchar(20000) NOT NULL,
  `niveau_temp` double NOT NULL,
  `niveau_sec` double NOT NULL,
  `niveau_tension` double NOT NULL,
  `groupe_sanguin` varchar(2) NOT NULL,
  `medicaments` varchar(20000) NOT NULL,
  `taille_resident` double NOT NULL,
  `poids_resident` double NOT NULL,
  PRIMARY KEY (`id_fiche`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `fiche_medicale`
--

INSERT INTO `fiche_medicale` (`id_fiche`, `date_creation_fiche`, `date_dermodif_fiche`, `remarques_fiche`, `niveau_temp`, `niveau_sec`, `niveau_tension`, `groupe_sanguin`, `medicaments`, `taille_resident`, `poids_resident`) VALUES
(12, '2019-02-05', '2019-02-06', 'test remarque', 36.5, 0.5, 12.8, 'A', 'zefzfzf', 160, 70),
(14, '2019-02-03', '2019-02-27', 'aaaaaaaaaaaa', 36, 1, 10, 'C', 'aaaaaaaaaaaaaaaaaaaa', 177, 77),
(21, '3919-02-01', '3919-02-01', 'ijhoin', 38, 1, 15, 'B+', 'sdsc', 160, 70),
(23, '3919-02-01', '3919-02-01', 'dzdddddddddd', 36, 11, 15, 'B+', 'aaaaaaa', 155, 70),
(24, '3919-02-01', '3919-02-01', 'ccccccccccccccc', 37, 1.1, 16, 'A+', 'cccccccccccc', 150, 60),
(26, '3919-02-01', '3919-02-01', 'kkkkkkkkkkk', 38, 1, 15, 'A-', 'kkkkkkkkkk', 155, 80),
(27, '3919-02-01', '3919-02-01', 'ppppppppppp', 37, 0.8, 16, 'B+', 'ppppppppppp', 155, 70),
(34, '2019-02-27', '2019-02-28', 'aaaaaaaaaaa', 37, 0.9, 15, 'A-', 'cccccccc', 155, 70),
(35, '2019-02-27', '2019-02-27', 'Attension au tension arterielle !!', 36.5, 0.82, 13.8, 'A+', 'ADOL	', 169, 93.2),
(36, '2019-02-28', '2019-02-28', 'nothing', 36.7, 0.83, 11.5, 'B+', 'Doliprane,Adol', 157, 73),
(37, '2019-03-01', '2019-03-01', 'aaa', 35.7, 0.78, 11.4, 'B+', 'aaaa', 158, 61.4);

-- --------------------------------------------------------

--
-- Structure de la table `maison`
--

DROP TABLE IF EXISTS `maison`;
CREATE TABLE IF NOT EXISTS `maison` (
  `id_maison` int(11) NOT NULL AUTO_INCREMENT,
  `nom_maison` varchar(20) NOT NULL,
  `adresse_maison` varchar(255) NOT NULL,
  `telephone_maison` varchar(8) NOT NULL,
  `mail_maison` varchar(30) NOT NULL,
  `nbr_personne` int(11) NOT NULL DEFAULT '0',
  `nbr_homme` int(11) NOT NULL DEFAULT '0',
  `nbr_femme` int(11) NOT NULL DEFAULT '0',
  `nbr_alzheimer` int(11) NOT NULL DEFAULT '0',
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_maison`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `maison`
--

INSERT INTO `maison` (`id_maison`, `nom_maison`, `adresse_maison`, `telephone_maison`, `mail_maison`, `nbr_personne`, `nbr_homme`, `nbr_femme`, `nbr_alzheimer`, `id_user`) VALUES
(48, 'sdf', 'Amiret El Fhoul', '54879582', 'yafet.shil@esprit.tn', 0, 0, 0, 0, NULL),
(50, 'jasmine', 'Ksibet Thrayet', '54785987', 'yafet.shil@esprit.tn', 1, 0, 0, 0, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `prestation_sante`
--

DROP TABLE IF EXISTS `prestation_sante`;
CREATE TABLE IF NOT EXISTS `prestation_sante` (
  `id_prestation` int(11) NOT NULL AUTO_INCREMENT,
  `id_resident` int(11) NOT NULL,
  `id_fiche` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `nom_resident` varchar(255) NOT NULL,
  `prenom_resident` varchar(255) NOT NULL,
  `nom_user` varchar(255) NOT NULL,
  `prenom_user` varchar(255) NOT NULL,
  `medicaments` varchar(20000) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id_prestation`),
  KEY `id_fiche` (`id_fiche`),
  KEY `id_resident` (`id_resident`),
  KEY `id_user` (`id_user`),
  KEY `id_user_2` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `prestation_sante`
--

INSERT INTO `prestation_sante` (`id_prestation`, `id_resident`, `id_fiche`, `id_user`, `nom_resident`, `prenom_resident`, `nom_user`, `prenom_user`, `medicaments`, `date`) VALUES
(1, 118, 34, 22, 'mohamed', 'shil', 'abdessmad', 'ahmed', 'zezezev', '2019-02-20'),
(2, 119, 37, 28, 'fff', 'ggg', 'benevole', 'b', 'aaaa', '2019-03-01'),
(3, 122, 35, 1, 'wassim', 'ali', 'chahir', 'bahri', 'Adol', '2019-03-01');

-- --------------------------------------------------------

--
-- Structure de la table `resident`
--

DROP TABLE IF EXISTS `resident`;
CREATE TABLE IF NOT EXISTS `resident` (
  `id_resident` int(11) NOT NULL AUTO_INCREMENT,
  `id_maison` int(11) DEFAULT NULL,
  `nom_resident` varchar(20) NOT NULL,
  `prenom_resident` varchar(20) NOT NULL,
  `age_resident` int(11) NOT NULL,
  `sexe_resident` varchar(255) NOT NULL,
  `date_resident` date NOT NULL,
  `alzheimer_resident` varchar(10) NOT NULL,
  `maladie_resident` varchar(255) NOT NULL,
  `nom_maison` varchar(255) NOT NULL,
  PRIMARY KEY (`id_resident`),
  KEY `id_maison` (`id_maison`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `resident`
--

INSERT INTO `resident` (`id_resident`, `id_maison`, `nom_resident`, `prenom_resident`, `age_resident`, `sexe_resident`, `date_resident`, `alzheimer_resident`, `maladie_resident`, `nom_maison`) VALUES
(118, 50, 'mohamed', 'shil', 20, 'H', '2019-02-13', '1', 'asas', 'jasmine'),
(119, 50, 'fff', 'ggg', 50, 'Homme', '2019-02-28', 'Oui', 'hh', 'jasmine'),
(120, 50, 'fff', 'ggg', 50, 'Homme', '2019-02-28', 'Oui', 'hh', 'jasmine'),
(121, 50, 'ff', 'ee', 50, 'Homme', '2019-02-28', 'Oui', 'jj', 'jasmine'),
(122, 48, 'wassim', 'ali', 55, 'Homme', '2019-03-01', 'Oui', 'alzheimer', 'sdf');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `nom` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `datenaissance` date NOT NULL,
  `adresse` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `phone` int(11) NOT NULL,
  `image` varchar(20000) COLLATE utf8_unicode_ci NOT NULL,
  `profession` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_8D93D64992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_8D93D649A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_8D93D649C05FB297` (`confirmation_token`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `nom`, `prenom`, `datenaissance`, `adresse`, `phone`, `image`, `profession`, `status`) VALUES
(1, 'chahir.18', NULL, 'chahir.1819@gmail.com', NULL, NULL, NULL, '1717', NULL, NULL, NULL, 'Admin', 'chahir', 'bahri', '2019-02-07', 'nabeul', 27914170, '*******', 'admin', 'ACTIF'),
(14, 'scqsc', NULL, 'test1', NULL, NULL, NULL, 'test1', NULL, NULL, NULL, 'Responsable MR', 'qc', 'qc', '2019-02-12', 'qsc', 561648, 'qc', 'qscsqc', 'SUSPEND'),
(16, 'qscsq', NULL, 'qscsq', NULL, NULL, NULL, 'qscsq', NULL, NULL, NULL, 'Admin', 'qsc', 'sqc', '2019-02-27', 'qscq', 51915, 'C:\\wamp64\\www\\pidev\\profileimages\\TextField[id=email_txt, styleClass=text-input text-field].png', 'qscqs', 'SUSPEND'),
(17, 'qscsqcqs', NULL, 'test2', NULL, NULL, NULL, 'test2', NULL, NULL, NULL, 'Responsable MR', 'qscsqcljbqsc', 'jbsqkc', '2019-02-21', 'qscqs', 8456, 'C:\\wamp64\\www\\pidev\\profileimages\\TextField[id=email_txt, styleClass=text-input text-field].png', 'qscsqjcbsqc', 'SUSPEND'),
(18, 'chahir.18', NULL, 'chahir.bahri@esprit.tn', NULL, NULL, NULL, '629485137', NULL, NULL, NULL, 'Responsable MR', 'Bahri', 'Chahir', '1994-04-16', 'Nabeul', 27914140, 'C:\\wamp64\\www\\pidev\\profileimages\\TextField[id=email_txt, styleClass=text-input text-field].png', 'Patron', 'ACTIF'),
(22, 'aaaa', NULL, 'ahmedabdessamed02@gmail.com', NULL, NULL, NULL, '50135013', NULL, NULL, NULL, 'Responsable MR', 'ahmed', 'abdessamed', '2019-02-14', 'dsqsq', 27914140, 'C:\\wamp64\\www\\pidev\\profileimages\\TextField[id=email_txt, styleClass=text-input text-field].png', 'dsqds', 'SUSPEND'),
(24, 'ch1', NULL, 'chahir.@esprit.tn', NULL, NULL, NULL, '123456789', NULL, NULL, NULL, 'Benevole', 'chahir', 'Bahri', '2019-02-05', 'Nabeul', 27914140, 'C:\\Users\\poste\\Desktop\\img\\13920_322726684557552_4604971722916088030_n.jpg', 'qd', 'SUSPEND'),
(26, 'chahir18', NULL, 'chahir18esprittn', NULL, NULL, NULL, '1777777', NULL, NULL, NULL, 'admin', 'chahir', 'bahri', '3888-08-11', 'ariana', 27999888, '*********', 'medecin', 'SUSPEND'),
(27, 'sabrine', 'efze', 'chernisabrine32@gmail.com', 'chernisabrine32@gmail.com', NULL, NULL, '', NULL, NULL, NULL, 'sdvsdvsdv', 'sabrine', 'sabrine', '2019-02-19', 'sdvsev', 222222, 'sdvsvdsdv', 'sdvsdvdsv', 'sdvsdvdsv'),
(28, 'benevole', NULL, 'benevole@b.tn', NULL, NULL, NULL, '0000', NULL, NULL, NULL, 'Bénévole', 'benevole', 'b', '2019-03-13', 'b', 3, 'b', 'b', 'ACTIF'),
(29, 'rm', NULL, 'rm@rm.rm', NULL, NULL, NULL, '0000', NULL, NULL, NULL, 'Responsable MR', 'rm', 'rm', '2019-03-13', 'rm', 22, '22', 'rm', 'ACTIF'),
(30, 'rac', NULL, 'rac@rac.rac', NULL, NULL, NULL, '0000', NULL, NULL, NULL, 'Responsable Club/Association', 'aaaa', 'aaa', '2019-03-05', 'aa', 33, 'aa', 'aa', 'ACTIF'),
(31, 'mohamed', NULL, 'medecin@medecin.tn', NULL, NULL, NULL, '0000', NULL, NULL, NULL, 'bénévole', 'mohamed', 'mohamed', '1996-03-13', 'Nabeul', 20002000, '', 'médecin', 'ACTIF');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement` ADD FULLTEXT KEY `description_evenement` (`description_evenement`);
ALTER TABLE `evenement` ADD FULLTEXT KEY `heure_evenement` (`heure_evenement`);
ALTER TABLE `evenement` ADD FULLTEXT KEY `heure_evenement_2` (`heure_evenement`);
ALTER TABLE `evenement` ADD FULLTEXT KEY `heure_evenement_3` (`heure_evenement`);
ALTER TABLE `evenement` ADD FULLTEXT KEY `heure_evenement_4` (`heure_evenement`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `demande`
--
ALTER TABLE `demande`
  ADD CONSTRAINT `demande_ibfk_1` FOREIGN KEY (`id_maison`) REFERENCES `maison` (`id_maison`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `demande_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `don`
--
ALTER TABLE `don`
  ADD CONSTRAINT `don_ibfk_2` FOREIGN KEY (`id_demande`) REFERENCES `demande` (`id_demande`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `don_ibfk_3` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `evenement_ibfk_2` FOREIGN KEY (`id_maison`) REFERENCES `maison` (`id_maison`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `evenement_ibfk_3` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `maison`
--
ALTER TABLE `maison`
  ADD CONSTRAINT `maison_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `prestation_sante`
--
ALTER TABLE `prestation_sante`
  ADD CONSTRAINT `prestation_sante_ibfk_1` FOREIGN KEY (`id_fiche`) REFERENCES `fiche_medicale` (`id_fiche`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `prestation_sante_ibfk_2` FOREIGN KEY (`id_resident`) REFERENCES `resident` (`id_resident`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `prestation_sante_ibfk_3` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `resident`
--
ALTER TABLE `resident`
  ADD CONSTRAINT `resident_ibfk_1` FOREIGN KEY (`id_maison`) REFERENCES `maison` (`id_maison`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
