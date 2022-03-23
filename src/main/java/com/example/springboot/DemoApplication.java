package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;

import java.io.File;
import java.util.List;
import java.io.FileOutputStream;

@SpringBootApplication
@RestController
public class DemoApplication  extends SpringBootServletInitializer {
   @Override
   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
      return application.sources(DemoApplication.class);
   }
   public static void main(String[] args) {
      SpringApplication.run(DemoApplication.class, args);
   }
   
   @RequestMapping(value = "/")
   public String hello() {
      SXSSFWorkbook wb2 = new SXSSFWorkbook(100);
		SXSSFSheet sheet2 = wb2.createSheet("new sheet");
      String output="Output not generated";
		int rowCount = 20;
		for (int i = 0; i < rowCount; i++) {
			sheet2.createRow(i);
		}
		sheet2.groupRow(4, 9);
		sheet2.groupRow(11, 19);
		sheet2.setRowGroupCollapsed(4, true);
		try {
         String fileName="outlining_collapsed"+System.currentTimeMillis()+".xlsx";
			FileOutputStream fileOut = new FileOutputStream(fileName);
         output="File generated - " +fileName;
			try {
				wb2.write(fileOut);
			} finally {
				fileOut.close();
				wb2.dispose();
				wb2.close();
			}
		} catch (Exception e) {
		}
		return output;
   }
}