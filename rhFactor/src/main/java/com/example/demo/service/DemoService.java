package com.example.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class DemoService {

	
	public byte[] gerarRelatorioListFornecedor() throws JRException, IOException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("PATH_LOGO", "C:\\Users\\maico\\Documents\\GitHub\\relatoriosRhfactor\\img\\logo.png");
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/lista-fornecedor.jasper"); 
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,conexao());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	
	
	private Connection conexao() {
		 Connection conexao = null;
		 String driver = "com.mysql.cj.jdbc.Driver";
		 String url = "jdbc:mysql://mysql.rhfactor.com.br/rhfactor04?useSSL=false&createDatabaseIfNotExist=true";
	     String user="rhfactor04";
	     String password="AZs4buJS6F";
		 try {
			 Class.forName(driver);
			 conexao = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			return null;
		}
		return conexao;
	}
}
