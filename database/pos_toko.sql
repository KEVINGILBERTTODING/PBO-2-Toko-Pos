-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 26, 2022 at 06:17 AM
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
('B002', 'Sepatu Adidas Superstar', 200000, 220000, 12, 2),
('B003', 'Crewneck Adidas Black', 780000, 800000, 5, 2),
('B004', 'Adidas NMD R1 Green', 2300000, 2400000, 12, 6),
('B005', 'Thrsher Hoodie Camo Black', 750000, 800000, 3, 3),
('B006', 'Gucci Hat OG', 4500000, 5000000, 2, 1),
('B007', 'Converse Chuck Taylor BW', 800000, 820000, 15, 3),
('B008', 'Converse Back to 80s', 950000, 1000000, 24, 15),
('B009', 'Vetements Black Basic Tee', 2500000, 3000000, 2, 1),
('B010', 'Nike Big Logo Hoodie', 850000, 900000, 23, 21),
('B011', 'Off White Marbles Hoodie', 4200000, 4500000, 1, 1),
('B012', 'Adidas Ultraboost White', 1700000, 2000000, 2, 2),
('B013', 'Adidas Trifoild Hoodie Beige', 1200000, 1500000, 5, 3);

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

--
-- Dumping data for table `dpembelian`
--

INSERT INTO `dpembelian` (`KodePembelian`, `KodeBarang`, `HargaBeliBarang`, `JmlBeliBarang`) VALUES
('P001', 'B001', 7000, 12),
('PB001', 'B009', 3000000, 1),
('PB001', 'B002', 220000, 2),
('KP002', 'B011', 4500000, 1),
('KP002', 'B006', 5000000, 1),
('KP002', 'B004', 2400000, 1),
('KP003', 'B012', 2000000, 2);

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
('K001', 'B001', 7000, 6),
('121212', 'B001', 7000, 12),
('P002', 'B002', 220000, 2),
('P003', 'B010', 900000, 2),
('P005', 'B003', 800000, 2);

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

--
-- Dumping data for table `dreturpenjualan`
--

INSERT INTO `dreturpenjualan` (`KodeReturPenjualan`, `KodeBarang`, `HargaJualBarang`, `JmlReturJualBarang`) VALUES
('r001', 'B001', 7000, 1),
('R002', 'B002', 220000, 2);

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
('KD001', 'Kevin Gilbert', 'Jl. Pahlawan', 'Rantepao', '09090922', '989897'),
('KD002', 'Muhammad Azhar', 'Jl. Perintis, No 13', 'Semarang', '7873232', '0930232322'),
('KD003', 'Diana Thamara', 'Jl. Perintis, No 3', 'Semarang', '232301', '0816786923'),
('KD004', 'Muhammad Fakhruddin', 'Jl, Merpati, No 92', 'Pemalang', '2323232', '081221212121'),
('KD005', 'Nafisa Khally', 'Jl. Kudus, no. 12', 'Semarang', '2232323', '0899982144'),
('KD006', 'Alvina Rizqi', 'Jl. Mawar, no. 13', 'Semarang', '23232323', '08982323121'),
('KD007', 'Afrul Sandi', 'Jl. Merdeka, no. 45', 'Tembalang', '8239283', '08712121212');

-- --------------------------------------------------------

--
-- Table structure for table `pembelian`
--

CREATE TABLE `pembelian` (
  `KodePembelian` varchar(10) NOT NULL,
  `KodeSupplier` varchar(10) DEFAULT NULL,
  `TglPembelian` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pembelian`
--

INSERT INTO `pembelian` (`KodePembelian`, `KodeSupplier`, `TglPembelian`) VALUES
('KP002', 'S001', '2022-05-25'),
('KP003', 'S001', '2022-05-25'),
('P001', 'S001', '2022-05-25'),
('PB001', 'S001', '2022-05-25');

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
('121212', 'KD001', '2022-05-25'),
('K001', 'KD002', '2022-05-22'),
('P001', 'K002', '2022-04-13'),
('P002', 'KD002', '2022-05-25'),
('P003', 'KD006', '2022-05-25'),
('P005', 'KD003', '2022-05-25');

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

--
-- Dumping data for table `returpenjualan`
--

INSERT INTO `returpenjualan` (`KodeReturPenjualan`, `KodePenjualan`, `TglReturPenjualan`) VALUES
('qwqwq', '121212', '2022-05-25'),
('r001', '121212', '2022-05-25'),
('R002', '121212', '2022-05-25');

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
('S001', 'Kevin Gilbert Toding', 'Jl. Ahmad Yani', 'Rantepao', '08232', '0923232222'),
('S002', 'Muhammad Azhar', 'Jl. Pembangunan', 'Kebumen', '2232323', '087902033'),
('S003', 'Muhammad Fakhruddin', 'Jl. Perintis', 'Pemalang', '2354564', '0817890002');

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
