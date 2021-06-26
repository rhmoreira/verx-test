@echo off

call mvn_config.cmd

call mvn.cmd clean package spring-boot:repackage
call java -jar target/currency-exchange-0.0.1-SNAPSHOT.jar