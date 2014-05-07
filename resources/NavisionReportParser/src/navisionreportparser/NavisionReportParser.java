/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navisionreportparser;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author pepa
 */
public class NavisionReportParser {

    private static String[] headerRow = new String[]{"ID", "NAME", "ACC", "Účto skupina zákazníka", "Zúčtovací datum", "Číslo dokladu", "Popis", "Datum splatnosti", "Měsíc splatnosti", "Kód měny", "Zůstatek", "Zůstatek (LM)"};
    private static String[] currentCustomer = new String[3];
    private static ArrayList<String[]> rows = new ArrayList<>();

    public static void main(String[] argv) throws Exception {
        DOMParser parser = new DOMParser();
        if (argv.length == 0) {

            argv = new String[]{"/home/pepa/elvis_detail_open.htm"};
        }
        
        for (String argv1 : argv) {
            InputSource input = new InputSource(new InputStreamReader(new FileInputStream(argv1), "windows-1250"));
            parser.parse(input);

            NodeList body = parser.getDocument().getElementsByTagName("BODY");
             rows.add(headerRow);
            readDocument(body.item(0));
            try (
                    BufferedWriter bf = new BufferedWriter(new FileWriter("/home/pepa/elvis_detail_open.csv"))) {
                StringBuilder sb = new StringBuilder();

                for (String[] r : rows) {
                    sb.append(System.getProperty("line.separator"));
                    for (String r1 : r) {

                        sb.append("\"" + r1 + "\"");
                        sb.append(",");
                    }
                }
                bf.write(sb.toString());
                bf.close();
            }

        }

    }

    private static void readDocument(Node nodes) {
        for (int i = 0; i < nodes.getChildNodes().getLength(); i++) {
            if ("TABLE".equalsIgnoreCase(nodes.getChildNodes().item(i).getNodeName())) {
                readPage(nodes.getChildNodes().item(i));
            }
        }
    }

    private static void readPage(Node page) {

        if ("TABLE".equalsIgnoreCase(page.getNodeName())) {
            NodeList childNodes = page.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                if ("TBODY".equalsIgnoreCase(childNodes.item(i).getNodeName())) {

                    Node dataTables = innerTables(childNodes.item(i));
                    if (dataTables == null) {
                    } else {
                        for (int b = 0; b < dataTables.getChildNodes().getLength(); b++) {
                            if ("TABLE".equalsIgnoreCase(dataTables.getChildNodes().item(b).getNodeName())) {
                                readTable(dataTables.getChildNodes().item(b));
                            }
                        }

                    }

                }

            }

        }

    }

    private static Node innerTables(Node node) {

        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if ("TD".equalsIgnoreCase(node.getChildNodes().item(i).getNodeName())) {
                return node.getChildNodes().item(i);
            } else if (node.getChildNodes().item(i).hasChildNodes()) {
                return innerTables(node.getChildNodes().item(i));

            }

        }

        return null;
    }

    private static void readTable(Node table) {

        NodeList rows = table.getChildNodes();
        if (rows.getLength() > 0) {

            for (int i = 0; i < rows.getLength(); i++) {

                if ("TBODY".equalsIgnoreCase(rows.item(i).getNodeName())) {
                    getRows(rows.item(i));
                }

            }
        }
    }

    private static void getRows(Node rows) {
        for (int i = 0; i < rows.getChildNodes().getLength(); i++) {

            if (rows.getChildNodes().item(i).getNodeName().equalsIgnoreCase("tr")) {
                readCells(rows.getChildNodes().item(i));

            }
        }

    }

    private static void readCells(Node row) {
        int offset = 0;
        List<Node> cells = new ArrayList();
        for (int i = 0; i < row.getChildNodes().getLength(); i++) {

            if (row.getChildNodes().item(i).getNodeName().equalsIgnoreCase("TD")) {
                cells.add(row.getChildNodes().item(i));

            }

        }
        printRow(cells);

        if (cells.size() == 6 || cells.size() == 12) {

            changeCustomer(cells);

        } else if (cells.size() == 5) {
            addAccount(cells);
        } else if (cells.size() == 18) {
            if (!cells.get(0).getTextContent().startsWith("Z")) {

                addData(cells);
            }

        }

    }

    private static void printRow(List<Node> cells) {
        System.out.println("");
        System.out.println("ROW LENGTH: " + cells.size());
        for (Node n : cells) {
            System.out.print(n.getTextContent() + " , ");
        }
    }

    private static void addData(List<Node> cells) {
        String[] dataArray = new String[9];

        dataArray[0] = cells.get(0).getTextContent().trim();
        dataArray[1] = cells.get(2).getTextContent().trim();
        dataArray[2] = cells.get(4).getTextContent().trim();
        dataArray[3] = cells.get(6).getTextContent().trim();
        dataArray[4] = cells.get(8).getTextContent().trim();
        dataArray[5] = cells.get(10).getTextContent().trim();
        dataArray[6] = cells.get(12).getTextContent().trim();
        dataArray[7] = cells.get(14).getTextContent().trim();
        dataArray[8] = cells.get(16).getTextContent().trim();
        rows.add(concat(currentCustomer, dataArray));
    }

    private static void changeCustomer(List<Node> cells) {
        currentCustomer = new String[4];
        currentCustomer[0] = cells.get(0).getTextContent().trim();
        currentCustomer[1] = cells.get(2).getTextContent().trim();
        currentCustomer[2] = cells.get(4).getTextContent().trim();

    }

    private static void addAccount(List<Node> cells) {
        currentCustomer[3] = cells.get(3).getTextContent().trim();

    }
    
   private static String[] concat(String[] A, String[] B) {
   int aLen = A.length;
   int bLen = B.length;
   String[] C= new String[aLen+bLen];
   System.arraycopy(A, 0, C, 0, aLen);
   System.arraycopy(B, 0, C, aLen, bLen);
   return C;
}

}
