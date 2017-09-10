/**
 * Descrição: Script SQL de criação do banco de dados e seu usuário administrador.
 * Data: 24/06/2015
 * Autor: Thiago Alexandre Martins Monteiro
 */

create database siav default character set utf8 default collate utf8_general_ci;

grant all privileges on siav.* to siav@'%' identified by 'siav';

flush privileges;