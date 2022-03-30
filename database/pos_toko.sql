-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 30, 2022 at 02:02 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pos_toko`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `KodeBarang` varchar(10) NOT NULL,
  `NamaBarang` varchar(30) DEFAULT NULL,
  `HargaBeliBarang` int(11) DEFAULT NULL,
  `HargaJualBarang` int(11) DEFAULT NULL,
  `StokBarang` int(11) DEFAULT NULL,
  `StokMinBarang` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`KodeBarang`, `NamaBarang`, `HargaBeliBarang`, `HargaJualBarang`, `StokBarang`, `StokMinBarang`) VALUES
('3233', 'adasd', 2121, 1212, 3133, 434354),
('BOO1', 'Penghapuss', 12000, 50000, 121, 122),
('BOO3', 'penghapus', 5000, 6000, 121, 2);

-- --------------------------------------------------------

--
-- Table structure for table `dpembelian`
--

CREATE TABLE `dpembelian` (
  `KodePembelian` varchar(10) DEFAULT NULL,
  `KodeBarang` varchar(10) DEFAULT NULL,
  `HargaBeliBarang` int(11) DEFAULT NULL,
  `JmlBeliBarang` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `dpenjualan`
--

CREATE TABLE `dpenjualan` (
  `KodePenjualan` varchar(10) DEFAULT NULL,
  `KodeBarang` varchar(10) DEFAULT NULL,
  `HargaJualBarang` int(11) DEFAULT NULL,
  `JmlJualBarang` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `dreturpembelian`
--

CREATE TABLE `dreturpembelian` (
  `KodeReturpembelian` varchar(10) DEFAULT NULL,
  `KodeBarang` varchar(10) DEFAULT NULL,
  `HargaBeliBarang` int(11) DEFAULT NULL,
  `JmlReturBeliBarang` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `dreturpenjualan`
--

CREATE TABLE `dreturpenjualan` (
  `KodeReturPenjualan` varchar(10) DEFAULT NULL,
  `KodeBarang` varchar(10) DEFAULT NULL,
  `HargaJualBarang` int(11) DEFAULT NULL,
  `JmlReturJualBarang` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `konsumen`
--

CREATE TABLE `konsumen` (
  `KodeKonsumen` varchar(10) NOT NULL,
  `NamaKonsumen` varchar(30) DEFAULT NULL,
  `AlamatKonsumen` varchar(50) DEFAULT NULL,
  `KotaKonsumen` varchar(20) DEFAULT NULL,
  `TelpKonsumen` varchar(20) DEFAULT NULL,
  `HpKonsumen` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `konsumen`
--

INSERT INTO `konsumen` (`KodeKonsumen`, `NamaKonsumen`, `AlamatKonsumen`, `KotaKonsumen`, `TelpKonsumen`, `HpKonsumen`) VALUES
('1002', 'Kevin gilbert', 'Jl. Gajah', '1002', '090293', '090293023');

-- --------------------------------------------------------

--
-- Table structure for table `pembelian`
--

CREATE TABLE `pembelian` (
  `KodePembelian` varchar(10) NOT NULL,
  `KodeSupplier` varchar(10) DEFAULT NULL,
  `TglPembelian` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `KodePenjualan` varchar(10) NOT NULL,
  `KodeKonsumen` varchar(10) DEFAULT NULL,
  `TglPenjualan` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `returpembelian`
--

CREATE TABLE `returpembelian` (
  `KodeReturPembelian` varchar(10) NOT NULL,
  `KodePembelian` varchar(10) DEFAULT NULL,
  `TglReturPembelian` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `returpenjualan`
--

CREATE TABLE `returpenjualan` (
  `KodeReturPenjualan` varchar(10) NOT NULL,
  `KodePenjualan` varchar(10) DEFAULT NULL,
  `TglReturPenjualan` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `KodeSupplier` varchar(10) NOT NULL,
  `NamaSupplier` varchar(30) DEFAULT NULL,
  `AlamatSupplier` varchar(50) DEFAULT NULL,
  `KotaSupplier` varchar(20) DEFAULT NULL,
  `TelpSupplier` varchar(15) DEFAULT NULL,
  `HpSupplier` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`KodeBarang`);

--
-- Indexes for table `konsumen`
--
ALTER TABLE `konsumen`
  ADD PRIMARY KEY (`KodeKonsumen`);

--
-- Indexes for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD PRIMARY KEY (`KodePembelian`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`KodePenjualan`);

--
-- Indexes for table `returpembelian`
--
ALTER TABLE `returpembelian`
  ADD PRIMARY KEY (`KodeReturPembelian`);

--
-- Indexes for table `returpenjualan`
--
ALTER TABLE `returpenjualan`
  ADD PRIMARY KEY (`KodeReturPenjualan`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`KodeSupplier`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
