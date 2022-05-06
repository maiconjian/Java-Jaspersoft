package com.example.demo.resource;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.DemoService;
import com.example.demo.service.relatorioV2;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/demo")
public class DemoResource {

//	@Autowired
//	private DemoService service;

	@Autowired
	private relatorioV2 service;

//	@GetMapping("/relatorio")
//	public ResponseEntity<byte[]> gerarRelatorio(@RequestParam("codigoRelatorio") int codigoRelatorio,
//			@RequestParam("dataDe") String dataDe, @RequestParam("dataAte") String dataAte,
//			@RequestParam("idProjeto") Long idProjeto, @RequestParam("idEmpreeiteiro") Long idEmpreeiteiro)
//			throws JRException, IOException {
//		byte[] relatorio = this.service.gerarRelatorio(codigoRelatorio, dataDe, dataAte, idProjeto, idEmpreeiteiro);
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatorio);
//	}
	
	@GetMapping("/relatorio")
	public ResponseEntity<byte[]> gerarRelatorio(
			@RequestParam("codigoRelatorio") int codigoRelatorio,
			@RequestParam("dataDe") String dataDe,
			@RequestParam("dataAte") String dataAte,
			@RequestParam("idProjeto") Long idProjeto, 
			@RequestParam("idEmpreeiteiro") Long idEmpreeiteiro,
			@RequestParam("exportType") String exportType,
			HttpServletResponse response)
			throws JRException, IOException {
		service.downloadReport(codigoRelatorio, dataDe, dataAte, idProjeto, idEmpreeiteiro,exportType,response);
		return ResponseEntity.ok().build();
	}



}
