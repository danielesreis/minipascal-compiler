package compilador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
javac Compilador.java
java -jar "C:\Users\danie\Desktop\compilador\dist\Compilador.jar" C:\Users\danie\Desktop\teste.pas
*/
public class Compilador {
    public static Scanner scanner;
    //public static BufferedReader code;
    public static String code;
    public static int currentIndex;
    /**
     *
     * @param args
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException{
       
       //FileReader fileReader = new FileReader(args[0]);
       //BufferedReader br = new BufferedReader(fileReader);
       //code = br;
       //char firstChar = (char)br.read();
       
       code = "program teste;\n" +
        "var a : real;\n" +
        "begin\n" +
        "a := 4;\n" +
        "end\n" +
        ".";
       
       currentIndex = 0;
       Parser parser = new Parser();
       Scanner s = new Scanner(code.charAt(currentIndex));
       scanner = s;
       parser.parse();
    }
}