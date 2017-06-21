-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 21-Jun-2017 às 19:08
-- Versão do servidor: 10.1.22-MariaDB
-- PHP Version: 7.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `imobiliaria`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cargo`
--

CREATE TABLE `cargo` (
  `pk_cargo` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `descricao` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cargo`
--

INSERT INTO `cargo` (`pk_cargo`, `nome`, `descricao`) VALUES
(1, 'Secretária', 'Secreatria as parada.');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `pk_cliente` int(11) NOT NULL,
  `nome` varchar(150) NOT NULL,
  `cpf` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`pk_cliente`, `nome`, `cpf`) VALUES
(1, 'Marcio', '0231231516'),
(2, 'dcfddd', '2232323');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente_endereco`
--

CREATE TABLE `cliente_endereco` (
  `pk_endereco` int(11) NOT NULL,
  `logradouro` varchar(150) NOT NULL,
  `bairro` varchar(45) DEFAULT 'nao informado',
  `cidade` varchar(45) DEFAULT 'nao informado',
  `estado` varchar(50) DEFAULT 'nao informado',
  `pais` varchar(45) DEFAULT 'nao informado',
  `cep` varchar(45) DEFAULT 'nao informado',
  `fk_cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente_endereco`
--

INSERT INTO `cliente_endereco` (`pk_endereco`, `logradouro`, `bairro`, `cidade`, `estado`, `pais`, `cep`, `fk_cliente`) VALUES
(1, 'wewewe', 'wewewewe', 'ewewwee23', 'AP', 'Estados Unidos', '21212', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `pk_funcionario` int(11) NOT NULL,
  `nome` varchar(150) NOT NULL,
  `cpf` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `fk_cargo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`pk_funcionario`, `nome`, `cpf`, `senha`, `fk_cargo`) VALUES
(1, 'Jhon Snow', '123456789', 'null', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario_endereco`
--

CREATE TABLE `funcionario_endereco` (
  `pk_endereco` int(11) NOT NULL,
  `logradouro` varchar(150) NOT NULL,
  `bairro` varchar(45) DEFAULT 'nao informado',
  `cidade` varchar(45) DEFAULT 'nao informado',
  `estado` varchar(50) DEFAULT 'nao informado',
  `pais` varchar(45) DEFAULT 'nao informado',
  `cep` varchar(45) DEFAULT 'nao informado',
  `fk_funcionario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `funcionario_endereco`
--

INSERT INTO `funcionario_endereco` (`pk_endereco`, `logradouro`, `bairro`, `cidade`, `estado`, `pais`, `cep`, `fk_funcionario`) VALUES
(1, '12312312qeqw', 'qweqw', 'qweeqw', 'AM', 'Estados Unidos', '213123123', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `imovel`
--

CREATE TABLE `imovel` (
  `pk_imovel` int(11) NOT NULL,
  `quartos` int(11) NOT NULL,
  `banheiros` int(11) NOT NULL,
  `tipo` varchar(45) DEFAULT 'aluguel',
  `garagens` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT 'vazio'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `imovel`
--

INSERT INTO `imovel` (`pk_imovel`, `quartos`, `banheiros`, `tipo`, `garagens`, `status`) VALUES
(5, 1, 2, 'Venda', 3, 'Alugado');

-- --------------------------------------------------------

--
-- Estrutura da tabela `imovel_endereco`
--

CREATE TABLE `imovel_endereco` (
  `pk_endereco` int(11) NOT NULL,
  `logradouro` varchar(150) NOT NULL,
  `bairro` varchar(45) DEFAULT 'nao informado',
  `cidade` varchar(45) DEFAULT 'nao informado',
  `estado` varchar(50) DEFAULT 'nao informado',
  `pais` varchar(45) DEFAULT 'nao informado',
  `cep` varchar(45) DEFAULT 'nao informado',
  `fk_imovel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `imovel_endereco`
--

INSERT INTO `imovel_endereco` (`pk_endereco`, `logradouro`, `bairro`, `cidade`, `estado`, `pais`, `cep`, `fk_imovel`) VALUES
(1, 'ewqdsd2', 'qweeqweqw', 'eqweqwasd', 'BA', 'Estados Unidos', '2', 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `pk_usuario` int(11) NOT NULL,
  `nome` varchar(150) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `nivel` varchar(45) NOT NULL DEFAULT 'comum'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cargo`
--
ALTER TABLE `cargo`
  ADD PRIMARY KEY (`pk_cargo`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`pk_cliente`);

--
-- Indexes for table `cliente_endereco`
--
ALTER TABLE `cliente_endereco`
  ADD PRIMARY KEY (`pk_endereco`,`fk_cliente`),
  ADD KEY `fk_cliente_endereco_cliente1_idx` (`fk_cliente`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`pk_funcionario`,`fk_cargo`),
  ADD KEY `fk_funcionario_cargo1_idx` (`fk_cargo`);

--
-- Indexes for table `funcionario_endereco`
--
ALTER TABLE `funcionario_endereco`
  ADD PRIMARY KEY (`pk_endereco`,`fk_funcionario`),
  ADD KEY `fk_funcionario_endereco_funcionario1_idx` (`fk_funcionario`);

--
-- Indexes for table `imovel`
--
ALTER TABLE `imovel`
  ADD PRIMARY KEY (`pk_imovel`);

--
-- Indexes for table `imovel_endereco`
--
ALTER TABLE `imovel_endereco`
  ADD PRIMARY KEY (`pk_endereco`,`fk_imovel`),
  ADD KEY `fk_imovel_endereco_imovel1_idx` (`fk_imovel`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`pk_usuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cargo`
--
ALTER TABLE `cargo`
  MODIFY `pk_cargo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `pk_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `cliente_endereco`
--
ALTER TABLE `cliente_endereco`
  MODIFY `pk_endereco` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `pk_funcionario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `funcionario_endereco`
--
ALTER TABLE `funcionario_endereco`
  MODIFY `pk_endereco` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `imovel`
--
ALTER TABLE `imovel`
  MODIFY `pk_imovel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `imovel_endereco`
--
ALTER TABLE `imovel_endereco`
  MODIFY `pk_endereco` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `pk_usuario` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `cliente_endereco`
--
ALTER TABLE `cliente_endereco`
  ADD CONSTRAINT `fk_cliente_endereco_cliente1` FOREIGN KEY (`fk_cliente`) REFERENCES `cliente` (`pk_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `fk_funcionario_cargo1` FOREIGN KEY (`fk_cargo`) REFERENCES `cargo` (`pk_cargo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `funcionario_endereco`
--
ALTER TABLE `funcionario_endereco`
  ADD CONSTRAINT `fk_funcionario_endereco_funcionario1` FOREIGN KEY (`fk_funcionario`) REFERENCES `funcionario` (`pk_funcionario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `imovel_endereco`
--
ALTER TABLE `imovel_endereco`
  ADD CONSTRAINT `fk_imovel_endereco_imovel1` FOREIGN KEY (`fk_imovel`) REFERENCES `imovel` (`pk_imovel`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
