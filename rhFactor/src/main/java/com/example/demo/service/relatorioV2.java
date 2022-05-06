package com.example.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class relatorioV2 {
	
	public void downloadReport(int codigoRelatorio, String dataDe, String dataAte, Long idProjeto,
			Long idEmpreeiteiro,String exportType,HttpServletResponse response) throws JRException, IOException {
		switch (codigoRelatorio) {
		case 1:
			relatorioListaFornecedor(exportType,response);
			break;
		case 2:
			relatorioListaEmpreeiteiro(exportType,response);
			break;
		case 3:
			relatorioListaFaturados(dataDe, dataAte, idProjeto,exportType,response);
			break;
		case 4:
			relatorioListaAguardandoFaturamento(dataDe, dataAte, idProjeto,exportType,response);
			break;
		case 5:
			relatorioListaContasPagas(dataDe, dataAte, idProjeto,exportType,response);
			break;
		case 6:
			relatorioListaContasApagar(dataDe, dataAte, idProjeto,exportType,response);
			break;
		case 7:
			relatorioMedicaoObra(idProjeto, idEmpreeiteiro,exportType,response);
			break;
		case 8:
			relatorioFechamentoObra(idProjeto,exportType,response);
			break;
		default:
			break;
		}
	  }
	
	
	private void relatorioListaFornecedor(String exportType, HttpServletResponse response) throws JRException, IOException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("PATH_LOGO", "C:\\Users\\maico\\Documents\\GitHub\\relatoriosRhfactor\\img\\logo.png");
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/lista-fornecedor.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conexao());
		String titleTransactionBy = "Transactions Report";
		if(exportType.equalsIgnoreCase("PDF")) {
			 JRPdfExporter exporter = new JRPdfExporter();
		      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		      response.setContentType("application/pdf");
		      response.setHeader(
		          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=lista-fornecedor.pdf;");
		      exporter.exportReport();
		}else if(exportType.equalsIgnoreCase("XLS")) {
			 JRXlsxExporter exporter = new JRXlsxExporter();
		      SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
		      reportConfigXLS.setSheetNames(new String[]{titleTransactionBy});
		      reportConfigXLS.setDetectCellType(true);
		      reportConfigXLS.setCollapseRowSpan(false);
		      exporter.setConfiguration(reportConfigXLS);
		      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		      response.setHeader(
		          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=lista-fornecedor.xlsx;");
		      response.setContentType("application/octet-stream");
		      exporter.exportReport();
		}
	}

	private void relatorioListaEmpreeiteiro(String exportType, HttpServletResponse response) throws JRException, IOException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("PATH_LOGO", "C:\\Users\\maico\\Documents\\GitHub\\relatoriosRhfactor\\img\\logo.png");

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/lista-empreeiteiros.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conexao());
		String titleTransactionBy = "Transactions Report";
		if(exportType.equalsIgnoreCase("PDF")) {
			 JRPdfExporter exporter = new JRPdfExporter();
		      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		      response.setContentType("application/pdf");
		      response.setHeader(
		          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=lista-empreeiteiros.pdf;");
		      exporter.exportReport();
		}else if(exportType.equalsIgnoreCase("XLS")) {
			 JRXlsxExporter exporter = new JRXlsxExporter();
		      SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
		      reportConfigXLS.setSheetNames(new String[]{titleTransactionBy});
		      reportConfigXLS.setDetectCellType(true);
		      reportConfigXLS.setCollapseRowSpan(false);
		      exporter.setConfiguration(reportConfigXLS);
		      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		      response.setHeader(
		          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=lista-empreeiteiros.xlsx;");
		      response.setContentType("application/octet-stream");
		      exporter.exportReport();
		}
	}

	private void relatorioListaFaturados(String dataDe, String dataAte, Long idProjeto,String exportType, HttpServletResponse response)
			throws JRException, IOException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("PATH_LOGO", "C:\\Users\\maico\\Documents\\GitHub\\relatoriosRhfactor\\img\\logo.png");
		parametros.put("DATA_DE", dataDe);
		parametros.put("DATA_ATE", dataAte);
		parametros.put("ID_PROJETO", idProjeto);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/lista-faturados.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conexao());
		String titleTransactionBy = "Transactions Report";
		if(exportType.equalsIgnoreCase("PDF")) {
			 JRPdfExporter exporter = new JRPdfExporter();
		      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		      response.setContentType("application/pdf");
		      response.setHeader(
		          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=lista-faturados.pdf;");
		      exporter.exportReport();
		}else if(exportType.equalsIgnoreCase("XLS")) {
			 JRXlsxExporter exporter = new JRXlsxExporter();
		      SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
		      reportConfigXLS.setSheetNames(new String[]{titleTransactionBy});
		      reportConfigXLS.setDetectCellType(true);
		      reportConfigXLS.setCollapseRowSpan(false);
		      exporter.setConfiguration(reportConfigXLS);
		      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		      response.setHeader(
		          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=lista-faturados.xlsx;");
		      response.setContentType("application/octet-stream");
		      exporter.exportReport();
		}
	}

	private void relatorioListaAguardandoFaturamento(String dataDe, String dataAte, Long idProjeto,String exportType, HttpServletResponse response)
			throws JRException, IOException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("PATH_LOGO", "C:\\Users\\maico\\Documents\\GitHub\\relatoriosRhfactor\\img\\logo.png");
		parametros.put("DATA_DE", dataDe);
		parametros.put("DATA_ATE", dataAte);
		parametros.put("ID_PROJETO", idProjeto);

		InputStream inputStream = this.getClass()
				.getResourceAsStream("/relatorios/lista-aguardando-faturamento.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conexao());
		String titleTransactionBy = "Transactions Report";
		if(exportType.equalsIgnoreCase("PDF")) {
			 JRPdfExporter exporter = new JRPdfExporter();
		      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		      response.setContentType("application/pdf");
		      response.setHeader(
		          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=lista-aguardando-faturamento.pdf;");
		      exporter.exportReport();
		}else if(exportType.equalsIgnoreCase("XLS")) {
			 JRXlsxExporter exporter = new JRXlsxExporter();
		      SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
		      reportConfigXLS.setSheetNames(new String[]{titleTransactionBy});
		      reportConfigXLS.setDetectCellType(true);
		      reportConfigXLS.setCollapseRowSpan(false);
		      exporter.setConfiguration(reportConfigXLS);
		      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		      response.setHeader(
		          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=lista-aguardando-faturamento.xlsx;");
		      response.setContentType("application/octet-stream");
		      exporter.exportReport();
		}
	}

	private void relatorioListaContasPagas(String dataDe, String dataAte, Long idProjeto,String exportType, HttpServletResponse response)
			throws JRException, IOException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("PATH_LOGO", "C:\\Users\\maico\\Documents\\GitHub\\relatoriosRhfactor\\img\\logo.png");
		parametros.put("DATA_DE", dataDe);
		parametros.put("DATA_ATE", dataAte);
		parametros.put("ID_PROJETO", idProjeto);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/lista-contas-pagas.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conexao());
		String titleTransactionBy = "Transactions Report";
		if(exportType.equalsIgnoreCase("PDF")) {
			 JRPdfExporter exporter = new JRPdfExporter();
		      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		      response.setContentType("application/pdf");
		      response.setHeader(
		          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=lista-contas-pagas.pdf;");
		      exporter.exportReport();
		}else if(exportType.equalsIgnoreCase("XLS")) {
			 JRXlsxExporter exporter = new JRXlsxExporter();
		      SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
		      reportConfigXLS.setSheetNames(new String[]{titleTransactionBy});
		      reportConfigXLS.setDetectCellType(true);
		      reportConfigXLS.setCollapseRowSpan(false);
		      exporter.setConfiguration(reportConfigXLS);
		      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		      response.setHeader(
		          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=lista-contas-pagas.xlsx;");
		      response.setContentType("application/octet-stream");
		      exporter.exportReport();
		}
	}

	private void relatorioListaContasApagar(String dataDe, String dataAte, Long idProjeto,String exportType, HttpServletResponse response)
			throws JRException, IOException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("PATH_LOGO", "C:\\Users\\maico\\Documents\\GitHub\\relatoriosRhfactor\\img\\logo.png");
		parametros.put("DATA_DE", dataDe);
		parametros.put("DATA_ATE", dataAte);
		parametros.put("ID_PROJETO", idProjeto);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/lista-contas-a-pagar.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conexao());
		String titleTransactionBy = "Transactions Report";
		if(exportType.equalsIgnoreCase("PDF")) {
			 JRPdfExporter exporter = new JRPdfExporter();
		      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		      response.setContentType("application/pdf");
		      response.setHeader(
		          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=lista-contas-a-pagar.pdf;");
		      exporter.exportReport();
		}else if(exportType.equalsIgnoreCase("XLS")) {
			 JRXlsxExporter exporter = new JRXlsxExporter();
		      SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
		      reportConfigXLS.setSheetNames(new String[]{titleTransactionBy});
		      reportConfigXLS.setDetectCellType(true);
		      reportConfigXLS.setCollapseRowSpan(false);
		      exporter.setConfiguration(reportConfigXLS);
		      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		      response.setHeader(
		          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=lista-contas-a-pagar.xlsx;");
		      response.setContentType("application/octet-stream");
		      exporter.exportReport();
		}
	}

	private void relatorioMedicaoObra(Long idProjeto, Long idEmpreeiteiro,String exportType, HttpServletResponse response) throws JRException, IOException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("PATH_LOGO", "C:\\Users\\maico\\Documents\\GitHub\\relatoriosRhfactor\\img\\logo.png");
		parametros.put("ID_PROJETO", idProjeto);
		parametros.put("ID_EMPREITEIRO", idEmpreeiteiro);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/medicao-obra.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conexao());
		String titleTransactionBy = "Transactions Report";
		if(exportType.equalsIgnoreCase("PDF")) {
			 JRPdfExporter exporter = new JRPdfExporter();
		      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		      response.setContentType("application/pdf");
		      response.setHeader(
		          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=medicao-obra.pdf;");
		      exporter.exportReport();
		}else if(exportType.equalsIgnoreCase("XLS")) {
			 JRXlsxExporter exporter = new JRXlsxExporter();
		      SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
		      reportConfigXLS.setSheetNames(new String[]{titleTransactionBy});
		      reportConfigXLS.setDetectCellType(true);
		      reportConfigXLS.setCollapseRowSpan(false);
		      exporter.setConfiguration(reportConfigXLS);
		      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		      response.setHeader(
		          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=medicao-obra.xlsx;");
		      response.setContentType("application/octet-stream");
		      exporter.exportReport();
		}
	}

	private void relatorioFechamentoObra(Long idProjeto,String exportType, HttpServletResponse response) throws JRException, IOException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("PATH_LOGO", "C:\\Users\\maico\\Documents\\GitHub\\relatoriosRhfactor\\img\\logo.png");
		parametros.put("ID_PROJETO", idProjeto);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/fechamento-obra.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conexao());
		String titleTransactionBy = "Transactions Report";
		if(exportType.equalsIgnoreCase("PDF")) {
			 JRPdfExporter exporter = new JRPdfExporter();
		      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		      response.setContentType("application/pdf");
		      response.setHeader(
		          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=fechamento-obra.pdf;");
		      exporter.exportReport();
		}else if(exportType.equalsIgnoreCase("XLS")) {
			 JRXlsxExporter exporter = new JRXlsxExporter();
		      SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
		      reportConfigXLS.setSheetNames(new String[]{titleTransactionBy});
		      reportConfigXLS.setDetectCellType(true);
		      reportConfigXLS.setCollapseRowSpan(false);
		      exporter.setConfiguration(reportConfigXLS);
		      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		      response.setHeader(
		          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=fechamento-obra.xlsx;");
		      response.setContentType("application/octet-stream");
		      exporter.exportReport();
		}
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
