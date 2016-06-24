package com.huayu.doc;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
//使用Base64，Jdk8自带
import java.util.Base64;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocumentCore;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.converter.WordToHtmlUtils;
import org.apache.poi.hwpf.usermodel.Picture;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
public class InlineImageWordToHtmlConverter extends WordToHtmlConverter {
 
    public InlineImageWordToHtmlConverter(Document document) {
        super(document);
    }
 
    /**
     * 重写方法
     * @param currentBlock
     * @param inlined
     * @param picture
     */
    @Override
    protected void processImageWithoutPicturesManager(Element currentBlock, boolean inlined, Picture picture) {
        Element imgNode = currentBlock.getOwnerDocument().createElement("img");// 创建img标签
        StringBuilder sb = new StringBuilder();
        // Base64处理
        sb.append(Base64.getMimeEncoder().encodeToString(picture.getRawContent()));
        sb.insert(0, "data:" + picture.getMimeType() + ";base64,");
        imgNode.setAttribute("src", sb.toString());
        currentBlock.appendChild(imgNode);
    }
 
    public static void main(String[] args) {
        File file = new File("D://111.doc");
        convert(file);
    }
 
    /**
     * Doc转换为Html
     * @param file
     *            Doc文件
     */
    public static void convert(File file) {
        ByteArrayOutputStream out = null;
        try {
            HWPFDocumentCore wordDocument = null;
            wordDocument = WordToHtmlUtils.loadDoc(new FileInputStream(file));
            Document newDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            WordToHtmlConverter wordToHtmlConverter = new InlineImageWordToHtmlConverter(newDocument);
            wordToHtmlConverter.processDocument(wordDocument);
            Document htmlDocument = wordToHtmlConverter.getDocument();
            out = new ByteArrayOutputStream();
            DOMSource domSource = new DOMSource(htmlDocument);
            StreamResult streamResult = new StreamResult(out);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer serializer = tf.newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(domSource, streamResult);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = new String(out.toByteArray());
        // 输出到控制台
        System.out.println(result);
    }
}