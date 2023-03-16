import java.io.*;
public class xml {
    public static void main(String[] args) {
        String result= "";
        String document="";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        FileWriter fichero = null;
        PrintWriter pw = null;
        boolean primer = true;
        boolean almacen = false;
        try {
            archivo = new File("C:\\Users\\Pablo\\Downloads\\FullOct2007\\FullOct2007.xml");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            fichero = new FileWriter("Fernandez_Valle.xml");
            pw = new PrintWriter(fichero);
            String st;
            while ((st = br.readLine()) != null) {
                if(st.contains("<vespaadd>")) {
                    document += st; 
                }else if(st.contains("</vespaadd")){
                    document += st;
                    if(almacen){
                        if(primer){
                            pw.println(result);
                            pw.println(document);
                            primer = false;
                        }else{
                            pw.println(document);
                        }
                    }
                    document = "";
                }else if(st.contains("<maincat>") || st.contains("<subcat>")){
                    document += st;
                    if(st.contains("Games") || st.contains("Geography")
                    || st.contains("Business") || st.contains("Cleaning")){
                        almacen = true;
                    }else{
                        almacen = false;
                    }
                }else{
                    if (st.contains("<?xml version") || st.contains("<ystfeed>")) {
                        result += st;
                    }else{
                        document += st;
                    }
                }
            }
            pw.println("</ystfeed>");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (null != fr) {
                    fr.close();
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}