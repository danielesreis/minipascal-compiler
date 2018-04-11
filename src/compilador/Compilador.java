package compilador;
import compilador.ast.*;

public class Compilador {
    public static String code;
    public static String ast;
    public static int currentIndex;
    public static int currentColumn;
    public static int currentLine;
    public static CompilerFrame compilerFrame;
    public static byte step = 3;

    public static void main(String[] args) {
       CompilerFrame cf = new CompilerFrame();
       compilerFrame = cf;
       compilerFrame.setVisible(true);
    }
    
    public static void startCompilation() {
        
        currentIndex = 0;
        currentLine = 1;
        currentColumn = 1;
        Programa p;
        IdentificationTable idTable = new IdentificationTable();
        Parser parser = new Parser(code.charAt(currentIndex));
        Printer printer = new Printer();
        Checker checker = new Checker(idTable);
       
        switch(step) {
           case 0: 
                    p = parser.parse();
                    break;
           case 1: 
                    p = parser.parse();
                    break;
           case 2: 
                    p = parser.parse();
                    printer.print(p, null);
                    break;
           case 3:
                    p = parser.parse();
                    printer.print(p, null);
                    checker.visitPrograma(p, null);
        }
    }
}