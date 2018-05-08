/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.itext;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;


import com.itextpdf.kernel.pdf.PdfWriter;



import com.itextpdf.layout.Document;

import com.itextpdf.layout.element.Image;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;


/**
 *
 * @author Ayrat
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
//        Document document = new Document(PageSize.A4, 20, 20, 20, 20);
//        PdfWriter.getInstance(document, new FileOutputStream("C:/test.pdf"));
//        document.open();
//        Image image = Image.getInstance(getClass().getResource("/logo.png"));
//        document.add(image);
//        document.close();

//Initialize PDF writer

        String dest = "hello.pdf";

        PdfWriter writer = new PdfWriter(dest);
 
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
 
        // Initialize document
        Document document = new Document(pdf);
 
        //Add paragraph to the document
        Image image = new Image(ImageDataFactory.create("darksouls.jpg"));
//                Image.getInstance(getClass().getResource("/logo.png"));
        document.add(image);
 
        //Close document
        document.close();
    }
}
