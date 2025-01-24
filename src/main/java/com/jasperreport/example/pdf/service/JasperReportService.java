package com.jasperreport.example.pdf.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class JasperReportService {

	private static String PATH_JRXML = "report/report.jrxml";
	private static final Logger log = LoggerFactory.getLogger(JasperReportService.class);
	
	public byte[] getReport() throws Exception {

		try {
			InputStream input = getClass().getClassLoader().getResourceAsStream(PATH_JRXML);

			JasperReport jasperReport = JasperCompileManager.compileReport(input);

			Map<String, Object> parameters = fillParameters();

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			log.warn("Erro ao gerar report", e.getMessage());
			throw e;
		}
	}

	public HashMap<String, Object> fillParameters() {
		HashMap<String, Object> parameters = new HashMap<>();

		parameters.put("title", "Report");

		return parameters;
	}

}