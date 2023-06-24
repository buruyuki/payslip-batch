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
import com.payslip.batch.dto.PayslipSummaryDto;
import com.payslip.batch.exception.PayslipApplicationRuntimeException;
import com.payslip.batch.util.JasperPDFUtil;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;

@Service
public class PdfCreateSharedService {

    private final static String DETAIL_LIST_LENGTH = "listlength";

    public void createPdf(List<PayslipDetailDto> details) {

        String resourcePath = "/META-INF/jasper/payslip.jrxml";
        final URL url = getClass().getResource(resourcePath);
        String fileName = "sample_payslip.pdf";

        String targetDir = "target/";

        String outputPath = targetDir + fileName;

        if (details.size() == 0) {
            return;
        }
        PayslipParameterDto parameterDto = new PayslipParameterDto();
        // 明細は最新の日付でソートしているためリストの先頭が新しい日付
        String endYearMonth = details.get(0).getIssueDate();
        String startYearMonth = details.get(details.size() - 1).getIssueDate();
        String aggregationPeriod = "";
        if (startYearMonth.equals(endYearMonth)) {
            aggregationPeriod = endYearMonth;
        } else {
            aggregationPeriod = startYearMonth + "〜" + endYearMonth;
        }
        parameterDto.setAggregationPeriod(aggregationPeriod);
        // parameterDto.setCreateYear("2023");
        // parameterDto.setCreateMonth("3");

        Map<String, Object> mapParameter = JasperPDFUtil.getParameterMap(parameterDto);
        mapParameter.put(DETAIL_LIST_LENGTH, details.size());

        try {
            // JasperPDFUtil.createPdf(url, outputPath, mapParameter, new
            // JREmptyDataSource());
            JasperPDFUtil.createPdf(url, outputPath, mapParameter,
                    JasperPDFUtil.getCollectionDatasource(details));
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
