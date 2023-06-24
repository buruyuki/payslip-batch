package com.payslip.batch.util;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRewindableDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JasperPDFUtil {

    private JasperPDFUtil() {
        throw new UnsupportedOperationException("Utilクラスのインスタンス生成は禁止されています");
    }

    /***
     * PDF出力
     * 
     * @param url            jrxmlテンプレートファイルパス
     * @param outputFilePath 出力先ファイルパス
     * @param mapParameter   テンプレートに埋め込むパラメータ
     * @param dataSource     テンプレートに埋め込むデータソース
     * @throws JRException Jasper関連のException
     * @throws IOException 入出力関連のException
     */
    public static void createPdf(final URL url, final String outputFilePath, final Map<String, Object> mapParameter,
            final JRRewindableDataSource dataSource) throws JRException, IOException {
        final JasperReport jasperReport = getJasperReport(url);
        final JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, mapParameter, dataSource);
        Path path = Paths.get(outputFilePath).getParent();
        if (path != null) {
            Files.createDirectories(path);
        }
        JasperExportManager.exportReportToPdfFile(jasperPrint, outputFilePath);
    }

    /***
     * jasperファイルコンパイル
     * 
     * @param url jrxmlテンプレートファイルパス
     * @return JasperReportクラス
     * @throws JRException Jasper関連のException
     * @throws IOException 入出力関連のException
     */
    private static JasperReport getJasperReport(final URL url) throws JRException, IOException {
        return JasperCompileManager.compileReport(url.openStream());
    }

    /***
     * ParameterMapの作成
     * 
     * @param parameter Parameterオブジェクト
     * @return HashMap化されたパラメータ
     */
    public static Map<String, Object> getParameterMap(final Object parameter) {
        if (parameter == null) {
            return Collections.emptyMap();
        }
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(parameter);
        Map<String, Object> mapParameter = new HashMap<>();
        for (PropertyDescriptor p : bw.getPropertyDescriptors()) {
            if (!"class".equals(p.getName())) {
                mapParameter.put(p.getName(), bw.getPropertyValue(p.getName()));
            }
        }
        return mapParameter;
    }

    /***
     * JasperDataSource取得
     * 
     * @param collection JRBeanCollectionDataSourceに埋め込むコレクションクラス
     * @return JRBeanCollectionDataSourceクラス
     */
    public static JRBeanCollectionDataSource getCollectionDatasource(final Collection<?> collection) {
        return new JRBeanCollectionDataSource(collection);
    }

}
