package compilador;

import compilador.ast.*;

public class Compilador {
    public static String code;
    public static String ast;
    public static int currentIndex;
    public static CompilerFrame compilerFrame;

    public static void main(String[] args) {
       CompilerFrame cf = new CompilerFrame();
       compilerFrame = cf;
       compilerFrame.setVisible(true);
    }
    
    public static void startCompilation() {
       currentIndex = 0;
       Object o = null;
       Programa p;
       Parser parser = new Parser(code.charAt(currentIndex));
       Printer printer = new Printer();
       p = parser.parse();
       o = printer.print(p, o);
    }
}