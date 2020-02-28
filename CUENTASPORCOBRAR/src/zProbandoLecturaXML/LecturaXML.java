/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zProbandoLecturaXML;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;



/**
 *
 * @author ARCRODINPC-05
 */
public class LecturaXML {
    
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        
         try {
            File archivo = new File("D:\\USER\\Escritorio\\20515074521-01-FE02-00000279.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);

            document.getDocumentElement().normalize();

            System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());

            NodeList listaUsuarios = document.getElementsByTagName("Invoice");

            for(int i = 0 ; i < listaUsuarios.getLength() ; i++) {
                Node nodo = listaUsuarios.item(i);
                System.out.println("Elemento: " + nodo.getNodeName());

                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    System.out.println("ID: " + element.getElementsByTagName("cbc:ID").item(0).getTextContent());
                    System.out.println("Fecha Emisión: " + element.getElementsByTagName("cbc:IssueDate").item(0).getTextContent());
                    System.out.println("Fecha Vencimiento: " + element.getElementsByTagName("cbc:DueDate").item(0).getTextContent());
                    System.out.println("Total: " + element.getElementsByTagName("cbc:TaxInclusiveAmount").item(0).getTextContent());
                                        
                    System.out.println("");
                }
            }

        } catch(IOException | ParserConfigurationException | DOMException | SAXException e) {
             System.out.println("e:"+e);
        }
        
        
        
        
        
        
//        try {
//            new LecturaXML().process(new File("D:\\USER\\Escritorio\\20515074521-01-FE02-00000279.xml"));
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        }
    }
    
//     private void process(File file) throws ParserConfigurationException, IOException, SAXException {
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//        Document doc = dBuilder.parse(file);
//
//        doc.getDocumentElement().normalize();
//
//        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
//        NodeList nList = doc.getElementsByTagName(doc.getDocumentElement().getNodeName());
//
//        System.out.println("Root element length: " + nList.getLength());
//
//        Node root = nList.item(0);
//        System.out.println("First node of root: " + root);
//        Node nNode = nList.item(0);
//
//        // aqui leo los atributos del nodo principal
//        System.out.println("Number of attributes of root: " + nNode.getAttributes().getLength());
//        System.out.println("Node name: " + nNode.getAttributes().item(0).getNodeName());
//        System.out.println("Node value: " + nNode.getAttributes().item(0).getNodeValue());
//
//        processNode(root);
//
//    }
//     
//    private void processNode(Node inputNode) {
// 
//        
//
//        for (int i = 0; i < inputNode.getChildNodes().getLength(); ++i) {
//
//            Node node = inputNode.getChildNodes().item(i);
//
//            if (node.getNodeType() == Node.ELEMENT_NODE) {
//
//                System.out.print("Node: " + node.getNodeName() + " ==> ");
//
//                if (node.getChildNodes().getLength() == 1) {
//                    String nodeText = node.getTextContent().trim();
//                    System.out.println(nodeText);
//                } else {
//                    System.out.println();
//                }
//
//                processNode(node);
//            }
//        }
//    }

}
    

