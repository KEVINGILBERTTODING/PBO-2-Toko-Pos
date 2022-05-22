-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 22, 2022 at 10:45 AM
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
('B001', 'Pensil', 5000, 7000, 12, 5),
('B002', 'Sepatu Adidas Superstar', 200000, 220000, 12, 2);

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

--
-- Dumping data for table `dpenjualan`
--

INSERT INTO `dpenjualan` (`KodePenjualan`, `KodeBarang`, `HargaJualBarang`, `JmlJualBarang`) VALUES
('', 'B004', 140000, 3),
('2333', '3233', 1212, 20),
('B00009', 'BOO3', 6000, 2),
('B00009', 'B004', 140000, 2),
('1022', 'BOO1', 50000, 12),
('B0003', 'BOO1', 50000, 15),
('P0002', 'BOO1', 50000, 23),
('P0002', 'B004', 140000, 56),
('P003', 'B003', 50000, 23),
('K001', 'B001', 7000, 6);

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
('KD001', 'Kevin Gilbert', 'Jl. Pahlawan', 'Rantepao', '09090922', '989897');

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

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`KodePenjualan`, `KodeKonsumen`, `TglPenjualan`) VALUES
('K001', 'KD002', '2022-05-22'),
('P001', 'K002', '2022-04-13');

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
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`KodeSupplier`, `NamaSupplier`, `AlamatSupplier`, `KotaSupplier`, `TelpSupplier`, `HpSupplier`) VALUES
('S001', 'Kevin Gilbert Toding', 'Jl. Ahmad Yani', 'Rantepao', '08232', '0923232222');

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
