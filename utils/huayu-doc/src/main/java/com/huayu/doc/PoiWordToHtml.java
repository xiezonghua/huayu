package com.huayu.doc;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;
 
/**
 * Poi实现Doc转Html
 * <p>ClassName: PoiWordToHtml</p>
 * @author Liao
 * @version 2015年6月2日
 */
public class PoiWordToHtml {
 
    /**
     * doc转Html
     * @param path
     *            Doc文件目录
     * @param file
     *            文件名
     * @throws Throwable
     */
    public static void convert(String path, String file) throws Throwable {
        InputStream input = new FileInputStream(path + file);// 文件输入流
        HWPFDocument wordDocument = new HWPFDocument(input);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
                return suggestedName;
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        // 获取并输出所有图片
        List<?> pics = wordDocument.getPicturesTable().getAllPictures();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                Picture pic = (Picture) pics.get(i);
                try {
                    pic.writeImageContent(new FileOutputStream(path + pic.suggestFullFileName())); // 保存图片
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);
 
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        outStream.close();
 
        String content = new String(outStream.toByteArray());
        // 生成Html文件
        writeFile(content, path + "1111111111.html", "UTF-8");
    }
 
    /**
     * 生成Html文件
     * @param content
     *            文件内容
     * @param path
     *            生成目录
     * @param encode
     *            文件编码
     */
    public static void writeFile(String content, String path, String encode) {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            File file = new File(path);
            fos = new FileOutputStream(file);
            bw = new BufferedWriter(new OutputStreamWriter(fos, encode));
            bw.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
    }
 
    /**
     * 测试生成
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        final String path = "/home/xzh/Desktop/convert/";
        final String file = "智联石油—谢宗华.doc";
        // final String file = "Using.Ppt transform";
//        final String file = "Professional and college correspondence between.Xls";
        PoiWordToHtml.convert(path, file);
    }
}