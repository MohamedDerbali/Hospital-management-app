/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.ConnexionDB;
import Entities.Fiche_medicale;
import Entities.Prestation_sante;
import Entities.Resident;
import Entities.User;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmed
 */
public class GenererFichePDF {
    
    

   /* List<ProduitsPanier> ProduitsServicesList ;
    Panier panier = new Panier();
    Commande commande= new Commande();*/
    
    public PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 13, new BaseColor(68,141,186))));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }
    
    public void addrowsfrom(Document doc,Map<Integer,List<Fiche_medicale>> prestationMap , int id_resident) throws DocumentException{
        
        //resident data
        ServiceResident sr = new ServiceResident();
        Resident resident = sr.getResidentById(id_resident);
        
        //docter data (file writer)
         ServicePrestationSante sps = new ServicePrestationSante();
        Prestation_sante ps = sps.getPrestationRowByIdResident(resident.getId_resident());
        User user = sps.getUserData(ps.getId_user());
        
         Color mainColor =  Color.decode("#DBE4FF");
         BaseColor mycolor = new BaseColor(mainColor);
        
        prestationMap.get(id_resident).stream().forEach((f)->{
           
            try{
                
                
            
            Paragraph paragraphe = new Paragraph("Fiche Medicale n° : "+f.getId_fich(), FontFactory.getFont(FontFactory.TIMES_BOLD, 30, new BaseColor(68,141,186)));
            paragraphe.setAlignment(Paragraph.ALIGN_CENTER);
            doc.add(paragraphe);

             
             
              PdfPCell cell;
                
              PdfPTable table1 = new PdfPTable(5);
              {
                cell = new PdfPCell(new Phrase( "   ", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                cell.setPaddingBottom(20);
                table1.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Patient :", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                cell.setPaddingBottom(20);
                table1.addCell(cell);
                
                cell = new PdfPCell(new Phrase(resident.getNom_resident(), FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                cell.setPaddingBottom(20);
                table1.addCell(cell);
                
                cell = new PdfPCell(new Phrase(resident.getPrenom_resident(), FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                cell.setPaddingBottom(20);
                table1.addCell(cell);
                
                cell = new PdfPCell(new Phrase("    ", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                cell.setPaddingBottom(20);
                table1.addCell(cell);
                
                table1.setWidthPercentage(100);
                doc.add(table1);
              }
              
              PdfPTable table2 = new PdfPTable(5);
              {
                cell = new PdfPCell(new Phrase( user.getProfession()+" :", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
              //  cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table2.addCell(cell);
                
                cell = new PdfPCell(new Phrase(user.getNom(), FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
             //   cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table2.addCell(cell);
                
                cell = new PdfPCell(new Phrase(user.getPrenom(), FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
               // cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table2.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Date derniere modification :", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
              //  cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table2.addCell(cell);
                
                cell = new PdfPCell(new Phrase(""+f.getDermodif_fich(), FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
              //  cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table2.addCell(cell);
                
                
                table2.setWidthPercentage(100);
                doc.add(table2);
              }
              
              PdfPTable table3 = new PdfPTable(1);
              {
                cell = new PdfPCell(new Phrase( "Médicaments :        "+f.getMedicaments(), FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                cell.setPaddingBottom(20);
                table3.addCell(cell);
                
              /*  cell = new PdfPCell(new Phrase("liste medicaments", FontFactory.getFont("Comic Sans MS", 12, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                table3.addCell(cell);
*/
                table3.setWidthPercentage(100);
                doc.add(table3);
              }
              
              PdfPTable table4 = new PdfPTable(6);
              {
                cell = new PdfPCell(new Phrase( "Taille :", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255 ))));
                cell.setBorder(PdfPCell.NO_BORDER);
              //  cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table4.addCell(cell);
                
                cell = new PdfPCell(new Phrase(f.getTaille_resident()+" cm", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
              //  cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table4.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Poids :", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
              //  cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table4.addCell(cell);
                
                cell = new PdfPCell(new Phrase(f.getPoids_resident()+" kg", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
              //  cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table4.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Groupe sanguin  :", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
              //  cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table4.addCell(cell);
                
                cell = new PdfPCell(new Phrase(f.getGroupeSanguin(), FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
              //  cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table4.addCell(cell);
                
                table4.setWidthPercentage(100);
                doc.add(table4);
              }
              
               PdfPTable table5 = new PdfPTable(6);
              {
                cell = new PdfPCell(new Phrase( "Niveau temperature :", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                cell.setPaddingBottom(20);
                table5.addCell(cell);
                
                cell = new PdfPCell(new Phrase(f.getNiveauTemp()+" °", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                cell.setPaddingBottom(20);
                table5.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Taux Sucre :", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                cell.setPaddingBottom(20);
                table5.addCell(cell);
                
                cell = new PdfPCell(new Phrase(f.getNiveauSuc()+" g", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                cell.setPaddingBottom(20);
                table5.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Tension artérielle :", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                cell.setPaddingBottom(20);
                table5.addCell(cell);
                
                cell = new PdfPCell(new Phrase(f.getNiveauTension()+" cmHg", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                cell.setPaddingBottom(20);
                table5.addCell(cell);
                
                table5.setWidthPercentage(100);
                doc.add(table5);
              }
              
               PdfPTable table6 = new PdfPTable(1);
              {
                cell = new PdfPCell(new Phrase( "Remarques :        "+f.getRemarques_fiche(), FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
               // cell.setPaddingTop(20);
               cell.setPaddingBottom(13);
                cell.setBackgroundColor(mycolor);
                table6.addCell(cell);
                
              
                table6.setWidthPercentage(100);
                doc.add(table6);
              }
              
              PdfPTable table7 = new PdfPTable(6);
              {
                cell = new PdfPCell(new Phrase( " ", FontFactory.getFont("Comic Sans MS", 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table7.addCell(cell);
                
                cell = new PdfPCell(new Phrase(" ", FontFactory.getFont("Comic Sans MS", 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table7.addCell(cell);
                
                cell = new PdfPCell(new Phrase("     ", FontFactory.getFont("Comic Sans MS", 12, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table7.addCell(cell);
                
                cell = new PdfPCell(new Phrase("     ", FontFactory.getFont("Comic Sans MS", 12, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table5.addCell(cell);
                
                cell = new PdfPCell(new Phrase("     ", FontFactory.getFont("Comic Sans MS", 12, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table7.addCell(cell);
                
                cell = new PdfPCell(new Phrase(resident.getNom_maison()+" le :", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table7.addCell(cell);
                
                cell = new PdfPCell(new Phrase(f.getDate__creation_fich().toString(), FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table7.addCell(cell);
                table7.setWidthPercentage(100);
                
                
                doc.add(table7);
              }
              
            
          
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            }catch (DocumentException ex) {
                       System.out.println(ex.getMessage());
           }


        });
         
    }

    public void pdf(Map<Integer,List<Fiche_medicale>> prestationMap, int id_resident) throws IOException, SQLException {
    
        Document doc = new Document();

        try {
            ServiceResident sr = new ServiceResident();
            Resident resident = sr.getResidentById(id_resident);
            PdfWriter.getInstance(doc, new FileOutputStream("X:\\Esprit\\semestre 2\\PIDev\\pidevs\\HappyoldsVersion1.0\\src\\fiches medicales\\"+resident.getPrenom_resident()+" "+resident.getNom_resident()+".pdf"));

            doc.open();
            
            PdfPTable table1 = new PdfPTable(1);
            table1.setWidthPercentage(100);
           //afficher la date courante au dessus de la fiche
            table1.addCell(getCell(LocalDate.now().toString(), PdfPCell.ALIGN_RIGHT));
            doc.add(table1);
            
            Image img = Image.getInstance("X:\\Esprit\\semestre 2\\PIDev\\pidevs\\HappyoldsVersion1.0\\src\\GUI\\New Mockup 1 copy4.png");
            img.scaleAbsoluteWidth(80);
            img.scaleAbsoluteHeight(92);
            img.setAlignment(Image.ALIGN_CENTER);
            doc.add(img);
            
            addrowsfrom(doc,prestationMap,id_resident);
           
      
            doc.close();
            Desktop.getDesktop().open(new File("X:\\Esprit\\semestre 2\\PIDev\\pidevs\\HappyoldsVersion1.0\\src\\fiches medicales\\"+resident.getPrenom_resident()+" "+resident.getNom_resident()+".pdf"));

        } catch (DocumentException | FileNotFoundException e) {
            
            System.out.println(e.getMessage());
        }

    }


    
}
