package compilador;
import compilador.ast.*;

public class Compilador {
    public static String code;
    public static String ast;
    public static int currentIndex;
    public static int currentColumn;
    public static int currentLine = 1;
    public static CompilerFrame compilerFrame;

    public static void main(String[] args) {
       CompilerFrame cf = new CompilerFrame();
       compilerFrame = cf;
       compilerFrame.setVisible(true);
    }
    
    public static void startCompilation() {
       currentIndex = 0;
       Programa p;
       IdentificationTable idTable = new IdentificationTable();
       Parser parser = new Parser(code.charAt(currentIndex));
       Printer printer = new Printer();
       Checker checker = new Checker(idTable);
       p = parser.parse();
       checker.visitPrograma(p, null);
       printer.print(p, null);
    }
}