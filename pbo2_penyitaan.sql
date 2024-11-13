-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Nov 13, 2024 at 05:38 PM
-- Server version: 8.0.35
-- PHP Version: 8.2.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pbo2_penyitaan`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang_bukti`
--

CREATE TABLE `barang_bukti` (
  `id_barang_bukti` bigint UNSIGNED NOT NULL,
  `asal_permohonan` varchar(100) DEFAULT NULL,
  `tersangka` varchar(100) DEFAULT NULL,
  `id_tindak_pidana` int DEFAULT NULL,
  `dokumen` text,
  `tahap` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `barang_bukti`
--

INSERT INTO `barang_bukti` (`id_barang_bukti`, `asal_permohonan`, `tersangka`, `id_tindak_pidana`, `dokumen`, `tahap`) VALUES
(1, 'Jakarta', 'Tersangka A', 1, 'Bukti A', 'Penyidikan'),
(2, 'Bandung', 'Tersangka B', 2, 'Bukti B', 'Penuntutan');

-- --------------------------------------------------------

--
-- Table structure for table `halaman_utama`
--

CREATE TABLE `halaman_utama` (
  `id_halaman` bigint UNSIGNED NOT NULL,
  `id_tindak_pidana` int DEFAULT NULL,
  `id_institusi` int DEFAULT NULL,
  `id_pengguna` int DEFAULT NULL,
  `id_permohonan` int DEFAULT NULL,
  `id_barang_bukti` int DEFAULT NULL,
  `tanda_tangan` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `halaman_utama`
--

INSERT INTO `halaman_utama` (`id_halaman`, `id_tindak_pidana`, `id_institusi`, `id_pengguna`, `id_permohonan`, `id_barang_bukti`, `tanda_tangan`) VALUES
(1, 1, 1, 1, 1, 1, 'TTD User1'),
(2, 2, 2, 2, 2, 2, 'TTD User2');

-- --------------------------------------------------------

--
-- Table structure for table `institusi`
--

CREATE TABLE `institusi` (
  `id_institusi` bigint UNSIGNED NOT NULL,
  `nama_institusi` varchar(100) NOT NULL,
  `alamat_institusi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `institusi`
--

INSERT INTO `institusi` (`id_institusi`, `nama_institusi`, `alamat_institusi`) VALUES
(1, 'Kejaksaan Negeri', 'Jl. Kejaksaan No. 1'),
(2, 'Kepolisian Resort', 'Jl. Polisi No. 2');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id_login` bigint UNSIGNED NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id_login`, `username`, `password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `id_pengguna` bigint UNSIGNED NOT NULL,
  `nama_pengguna` varchar(50) NOT NULL,
  `nama_lengkap` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`id_pengguna`, `nama_pengguna`, `nama_lengkap`) VALUES
(1, 'user1', 'Pengguna Satu'),
(2, 'user2', 'Pengguna Dua');

-- --------------------------------------------------------

--
-- Table structure for table `permohonan_penyitaan`
--

CREATE TABLE `permohonan_penyitaan` (
  `id_permohonan` bigint UNSIGNED NOT NULL,
  `asal_permohonan` varchar(100) DEFAULT NULL,
  `tersangka` varchar(100) DEFAULT NULL,
  `id_tindak_pidana` int DEFAULT NULL,
  `dokumen` text,
  `tahap` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `permohonan_penyitaan`
--

INSERT INTO `permohonan_penyitaan` (`id_permohonan`, `asal_permohonan`, `tersangka`, `id_tindak_pidana`, `dokumen`, `tahap`) VALUES
(1, 'Jakarta', 'Tersangka A', 1, 'Dokumen A', 'Penyidikan'),
(2, 'Bandung', 'Tersangka B', 2, 'Dokumen B', 'Penuntutan');

-- --------------------------------------------------------

--
-- Table structure for table `tindak_pidana`
--

CREATE TABLE `tindak_pidana` (
  `id_tindak_pidana` bigint UNSIGNED NOT NULL,
  `nama_tindak_pidana` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tindak_pidana`
--

INSERT INTO `tindak_pidana` (`id_tindak_pidana`, `nama_tindak_pidana`) VALUES
(1, 'Korupsi'),
(2, 'Pencurian'),
(3, 'Penipuan');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang_bukti`
--
ALTER TABLE `barang_bukti`
  ADD PRIMARY KEY (`id_barang_bukti`),
  ADD UNIQUE KEY `id_barang_bukti` (`id_barang_bukti`);

--
-- Indexes for table `halaman_utama`
--
ALTER TABLE `halaman_utama`
  ADD PRIMARY KEY (`id_halaman`),
  ADD UNIQUE KEY `id_halaman` (`id_halaman`);

--
-- Indexes for table `institusi`
--
ALTER TABLE `institusi`
  ADD PRIMARY KEY (`id_institusi`),
  ADD UNIQUE KEY `id_institusi` (`id_institusi`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id_login`),
  ADD UNIQUE KEY `id_login` (`id_login`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id_pengguna`),
  ADD UNIQUE KEY `id_pengguna` (`id_pengguna`);

--
-- Indexes for table `permohonan_penyitaan`
--
ALTER TABLE `permohonan_penyitaan`
  ADD PRIMARY KEY (`id_permohonan`),
  ADD UNIQUE KEY `id_permohonan` (`id_permohonan`);

--
-- Indexes for table `tindak_pidana`
--
ALTER TABLE `tindak_pidana`
  ADD PRIMARY KEY (`id_tindak_pidana`),
  ADD UNIQUE KEY `id_tindak_pidana` (`id_tindak_pidana`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang_bukti`
--
ALTER TABLE `barang_bukti`
  MODIFY `id_barang_bukti` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `halaman_utama`
--
ALTER TABLE `halaman_utama`
  MODIFY `id_halaman` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `institusi`
--
ALTER TABLE `institusi`
  MODIFY `id_institusi` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `id_login` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `id_pengguna` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `permohonan_penyitaan`
--
ALTER TABLE `permohonan_penyitaan`
  MODIFY `id_permohonan` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tindak_pidana`
--
ALTER TABLE `tindak_pidana`
  MODIFY `id_tindak_pidana` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
