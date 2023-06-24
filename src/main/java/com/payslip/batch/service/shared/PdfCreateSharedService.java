package com.payslip.batch.service.shared;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.stereotype.Service;

import com.payslip.batch.dto.PayslipDetailDto;
import com.payslip.batch.dto.PayslipParameterDto;
import com.payslip.batch.exception.PayslipApplicationRuntimeException;
import com.payslip.batch.util.JasperPDFUtil;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;

@Service
public class PdfCreateSharedService {

    public void createPdf(List<PayslipDetailDto> details) {

        String resourcePath = "/META-INF/jasper/payslip.jrxml";
        final URL url = getClass().getResource(resourcePath);
        String fileName = "sample_payslip.pdf";

        String targetDir = "target/";

        String outputPath = targetDir + fileName;

        PayslipParameterDto parameterDto = new PayslipParameterDto();
        parameterDto.setCreateYear("2023");
        parameterDto.setCreateMonth("3");

        Map<String, Object> mapParameter = JasperPDFUtil.getParameterMap(parameterDto);

        try {
            JasperPDFUtil.createPdf(url, outputPath, mapParameter, new JREmptyDataSource());
            // JasperPDFUtil.createPdf(url, outputPath, mapParameter,
            // JasperPDFUtil.getCollectionDatasource(details));
        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // PDFBoxによるPDF生成
    public void create_PD() {
        // 空のドキュメントオブジェクトを作成
        PDDocument doc = new PDDocument();

        // 新しいページのオブジェクトを作成
        PDPage page = new PDPage();
        doc.addPage(page);

        String fileName = "sample.pdf";

        String targetDir = "target/";

        String outputPath = targetDir + fileName;

        // ドキュメントを保存
        try {
            doc.save(outputPath);
            doc.close();
        } catch (IOException e) {
            throw new PayslipApplicationRuntimeException("PDF作成エラー、ファイル：" + outputPath, e);
        }

    }

}
