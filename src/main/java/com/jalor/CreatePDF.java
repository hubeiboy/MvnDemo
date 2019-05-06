package com.jalor;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

public class CreatePDF {
    public static void main(String[] args) throws Exception {
        test();
        System.out.println("success");
    }

    public static void test() throws IOException, DocumentException {
        // pdf模板
        String fileName = "D:/百度营销认证书模板.pdf";
        PdfReader reader = new PdfReader(fileName);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        /* 将要生成的目标PDF文件名称 */
        PdfStamper ps = new PdfStamper(reader, bos);
        PdfContentByte under = ps.getUnderContent(1);

        /* 使用中文字体 */
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
        fontList.add(bf);

        /* 取出报表模板中的所有字段 */
        AcroFields fields = ps.getAcroFields();
        fields.setSubstitutionFonts(fontList);
        fillData(fields, data());

        /* 必须要调用这个，否则文档不会生成的 */
        ps.setFormFlattening(true);
        ps.close();

        //生成pdf路径
        OutputStream fos = new FileOutputStream("D:/百度营销认证书.pdf");
        fos.write(bos.toByteArray());
        fos.flush();
        fos.close();
        bos.close();
    }

    /**
     * * 填充模板
     * *
     */
    public static void fillData(AcroFields fields, Map<String, String> data)
            throws IOException, DocumentException {
        for (String key : data.keySet()) {
            String value = data.get(key);
            // 为字段赋值,注意字段名称是区分大小写的
            fields.setField(key, value);
        }
    }

    /**
     * * 填充数据源
     * * 其中data存放的key值与pdf模板中的文本域值相对应
     * *
     */
    public static Map<String, String> data() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("username", "Liu Bo");
        data.put("number", "BCMM01201904191679");
        data.put("idcard", "1234567890");
        data.put("validity", "2022年4月24日");
        data.put("lyh", "李彦宏");
        return data;
    }
}

