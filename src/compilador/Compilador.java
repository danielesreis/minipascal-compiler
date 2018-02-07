package compilador;

public class Compilador {
    public static Scanner scanner;
    //public static BufferedReader code;
    public static String code;
    public static int currentIndex;
    public static CompilerFrame compilerFrame;

    public static void main(String[] args){
       CompilerFrame cf = new CompilerFrame();
       compilerFrame = cf;
       compilerFrame.setVisible(true);
    }
    
    public static void startCompilation() {
       currentIndex = 0;
       Parser parser = new Parser();
       scanner = new Scanner(code.charAt(currentIndex));
       parser.parse();
    }
}