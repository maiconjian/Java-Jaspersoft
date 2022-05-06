package com.example.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class DemoService {

	public byte[] gerarRelatorio(int codigoRelatorio, String dataDe, String dataAte, Long idProjeto,
			Long idEmpreeiteiro) throws JRException, IOException {
		switch (codigoRelatorio) {
		case 1:
			return relatorioListaFornecedor();
		case 2:
			return relatorioListaEmpreeiteiro();
		case 3:
			return relatorioListaFaturados(dataDe, dataAte, idProjeto);
		case 4:
			return relatorioListaAguardandoFaturamento(dataDe, dataAte, idProjeto);
		case 5:
			return relatorioListaContasPagas(dataDe, dataAte, idProjeto);
		case 6:
			return relatorioListaContasApagar(dataDe, dataAte, idProjeto);
		case 7:
			return relatorioMedicaoObra(idProjeto, idEmpreeiteiro);
		case 8:
			return relatorioFechamentoObra(idProjeto);
		default:
			break;
		}
		return null;
	}

	private byte[] relatorioListaFornecedor() throws JRException, IOException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("PATH_LOGO", "C:\\Users\\maico\\Documents\\GitHub\\relatoriosRhfactor\\img\\logo.png");

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/lista-fornecedor.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conexao());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	private byte[] relatorioListaEmpreeiteiro() throws JRException, IOException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("PATH_LOGO", "C:\\Users\\maico\\Documents\\GitHub\\relatoriosRhfactor\\img\\logo.png");

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/lista-empreeiteiros.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conexao());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	private byte[] relatorioListaFaturados(String dataDe, String dataAte, Long idProjeto)
			throws JRException, IOException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("PATH_LOGO", "C:\\Users\\maico\\Documents\\GitHub\\relatoriosRhfactor\\img\\logo.png");
		parametros.put("DATA_DE", dataDe);
		parametros.put("DATA_ATE", dataAte);
		parametros.put("ID_PROJETO", idProjeto);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/lista-faturados.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conexao());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	private byte[] relatorioListaAguardandoFaturamento(String dataDe, String dataAte, Long idProjeto)
			throws JRException, IOException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("PATH_LOGO", "C:\\Users\\maico\\Documents\\GitHub\\relatoriosRhfactor\\img\\logo.png");
		parametros.put("DATA_DE", dataDe);
		parametros.put("DATA_ATE", dataAte);
		parametros.put("ID_PROJETO", idProjeto);

		InputStream inputStream = this.getClass()
				.getResourceAsStream("/relatorios/lista-aguardando-faturamento.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conexao());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	private byte[] relatorioListaContasPagas(String dataDe, String dataAte, Long idProjeto)
			throws JRException, IOException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("PATH_LOGO", "C:\\Users\\maico\\Documents\\GitHub\\relatoriosRhfactor\\img\\logo.png");
		parametros.put("DATA_DE", dataDe);
		parametros.put("DATA_ATE", dataAte);
		parametros.put("ID_PROJETO", idProjeto);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/lista-contas-pagas.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conexao());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	private byte[] relatorioListaContasApagar(String dataDe, String dataAte, Long idProjeto)
			throws JRException, IOException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("PATH_LOGO", "C:\\Users\\maico\\Documents\\GitHub\\relatoriosRhfactor\\img\\logo.png");
		parametros.put("DATA_DE", dataDe);
		parametros.put("DATA_ATE", dataAte);
		parametros.put("ID_PROJETO", idProjeto);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/lista-contas-a-pagar.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conexao());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	private byte[] relatorioMedicaoObra(Long idProjeto, Long idEmpreeiteiro) throws JRException, IOException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("PATH_LOGO", "C:\\Users\\maico\\Documents\\GitHub\\relatoriosRhfactor\\img\\logo.png");
		parametros.put("ID_PROJETO", idProjeto);
		parametros.put("ID_EMPREITEIRO", idEmpreeiteiro);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/medicao-obra.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conexao());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	private byte[] relatorioFechamentoObra(Long idProjeto) throws JRException, IOException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("PATH_LOGO", "C:\\Users\\maico\\Documents\\GitHub\\relatoriosRhfactor\\img\\logo.png");
		parametros.put("ID_PROJETO", idProjeto);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/fechamento-obra.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conexao());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	private Connection conexao() {
		Connection conexao = null;
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://mysql.rhfactor.com.br/rhfactor04?useSSL=false&createDatabaseIfNotExist=true";
		String user = "rhfactor04";
		String password = "AZs4buJS6F";
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			return null;
		}
		return conexao;
	}

}
