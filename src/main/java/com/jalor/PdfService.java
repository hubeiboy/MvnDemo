package com.jalor;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfService {

    public static void main(String[] args) throws Exception {
        //准备的参数
        String specName = "基本VLAN基本VLAN基本VLAN基本VLAN基本VLAN基本VLAN基本VLAN基本VLAN基本VLAN基本VLAN基本VLAN基本VLAN基本VLAN";
        String matureTitle = "成熟度：";
        String matureWord = "成熟特性 受控特性 不可商用";
        String descriptionTitle = "描述：";
        String descriptionWord = "从便利性、渲染效果综合来看，还是用XmlWorker好些。虽然渲染效果没有达到极致，但十分有利于用户自定义模板";
        String remarkTitle = "备注：";
        String remarkWord = "对于美制裁中兴一事，商务部新闻发言人高峰19日在回答记者提问时再次强调";
        String boardName = "CloudEngine 12800 V200R001C00";
        String pathTitle = "路径";
        String pathWord = "CloudEngine 12800 V200R001C00>CE12800";
        String xiangqTitle = "详情";
        String xiangqWord = "美方行径引起了市场对美国贸易和投资环境的普遍担忧，美方的行为表面针对中国，但最终伤害的是美国自身，不仅会使其丧失数以万计的就业机会，还会影响成百上千的美国关联企业";
        String beizTitle = "备注";
        String beizWord = "将会动摇国际社会对美国投资和营商环境稳定的信心。希望美方不要自作聪明，否则只会自食其果。也希望美方不要低估中方的决心，如果美方坚持通过单边主义的保护政策，不惜伤害中美两国企业利益";


        String outPath = "D:\\123.pdf";
        Rectangle rect = new Rectangle(PageSize.A4);
        Document doc=new Document(rect);
        //中文字体,解决中文不能显示问题
        BaseFont bfChinese=BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

        //设置字体样式
        Font textFont = new Font(bfChinese,11,Font.NORMAL); //正常
        Font boldFont = new Font(bfChinese,11,Font.BOLD); //加粗
        Font titleFont = new Font(bfChinese,15,Font.BOLD); //二级标题
        Color grayColor = new Color(204,204,204);

        PdfWriter.getInstance(doc, new FileOutputStream(new File(outPath)));
        doc.open();
        doc.newPage();

        //规格名称
//         Paragraph p= new Paragraph(specName, titleFont);
//         p.setAlignment(Element.ALIGN_CENTER);
//         doc.add(p);
        PdfPTable head = new PdfPTable(1);
        head.setTotalWidth(new float[]{520}); //设置列宽
        head.setLockedWidth(true); //锁定列宽
        head.setSpacingBefore(10f); // 前间距
        head.setSpacingAfter(10f); // 后间距
        PdfPCell cell1 = new PdfPCell(new Phrase(specName, titleFont));
        cell1.setBorderWidth(0);
        cell1.setBackgroundColor(grayColor);
        cell1.setMinimumHeight(20); //设置单元格高度
        cell1.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
        cell1.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        head.addCell(cell1);
        doc.add(head);


        //成熟度、描述、备注
        Paragraph p = new Paragraph();
        Phrase ph = new Phrase();
        Chunk c1 = new Chunk(matureTitle, boldFont) ;
        Chunk c2 = new Chunk(matureWord, textFont) ;
        ph.add(c1);
        ph.add(c2);
        p.add(ph);
        p.setLeading(20);
        doc.add(p);

        p = new Paragraph();
        ph = new Phrase();
        c1 = new Chunk(descriptionTitle, boldFont) ;
        c2 = new Chunk(descriptionWord, textFont) ;
        ph.add(c1);
        ph.add(c2);
        p.add(ph);
        p.setLeading(20);
        doc.add(p);

        p = new Paragraph();
        ph = new Phrase();
        c1 = new Chunk(remarkTitle, boldFont) ;
        c2 = new Chunk(remarkWord, textFont) ;
        ph.add(c1);
        ph.add(c2);
        p.add(ph);
        p.setLeading(20);
        doc.add(p);

        //创建2列的详情表格
        PdfPTable table = new PdfPTable(2);
        table.setSpacingBefore(20f);                    //table前间距
        table.setTotalWidth(new float[]{ 100, 420});     //设置列宽
        table.setLockedWidth(true); //锁定列宽
        PdfPCell cell;
        //第一行 实例名
        cell = new PdfPCell(new Phrase(" ", textFont));
        cell.setMinimumHeight(30); //设置单元格高度
        cell.setBackgroundColor(grayColor);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(boardName, textFont));
        cell.setBackgroundColor(grayColor);
        cell.setUseAscender(true); //设置可以居中
        cell.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        //第二行 路径
        cell = new PdfPCell(new Phrase(pathTitle, textFont));
        cell.setMinimumHeight(40);
        cell.setUseAscender(true);
        cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(pathWord, textFont));
        cell.setUseAscender(true);
        cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
        table.addCell(cell);
        //第三行 详情
        cell = new PdfPCell(new Phrase(xiangqTitle, textFont));
        cell.setMinimumHeight(80);
        cell.setUseAscender(true);
        cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(xiangqWord, textFont));
        cell.setUseAscender(true);
        cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
        table.addCell(cell);
        //第四行 备注
        cell = new PdfPCell(new Phrase(beizTitle, textFont));
        cell.setMinimumHeight(120);
        cell.setUseAscender(true);
        cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(beizWord, textFont));
        cell.setUseAscender(true);
        cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
        table.addCell(cell);

        doc.add(table);
        doc.close();

        System.out.println("------------->>写出完毕");
    }



}