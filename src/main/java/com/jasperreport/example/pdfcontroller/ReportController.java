package com.jasperreport.example.pdfcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jasperreport.example.pdf.service.JasperReportService;

@RestController
public class ReportController {

	@Autowired
	private JasperReportService jasperReportService;

	@GetMapping("/report")
	public ResponseEntity<Resource> getItemReport() throws Exception {
		byte[] content = jasperReportService.getReport();

		ByteArrayResource resource = new ByteArrayResource(content);
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.contentLength(resource.contentLength())
				.header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename("report.pdf").build().toString())
				.body(resource);
	}
}