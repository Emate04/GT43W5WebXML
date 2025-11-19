package GT43W5JSON;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONReadGT43W5 {

    public static void main(String[] args) {
        try {
            JSONParser parser = new JSONParser();

            
            Object obj = parser.parse(new FileReader("orarendGT43W5.json"));

            JSONObject root = (JSONObject) obj;
            JSONObject orarend = (JSONObject) root.get("GT43W5_orarend");

            JSONArray oraLista = (JSONArray) orarend.get("ora");

            System.out.println("=== GT43W5 órarend JSON feldolgozás ===");

            for (Object o : oraLista) {
                JSONObject ora = (JSONObject) o;

                System.out.println("-----------------------------------");
                System.out.println("Tárgy: " + ora.get("targy"));

                JSONObject idopont = (JSONObject) ora.get("idopont");
                System.out.println("Nap: " + idopont.get("nap"));
                System.out.println("Idõ: " + idopont.get("tol") + " - " + idopont.get("ig"));

                System.out.println("Helyszín: " + ora.get("helyszin"));
                System.out.println("Oktató: " + ora.get("oktato"));
                System.out.println("Szak: " + ora.get("szak"));
            }

            System.out.println("-----------------------------------");
            System.out.println("JSON feldolgozás kész.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
