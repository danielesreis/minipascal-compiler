package compilador;
import compilador.ast.*;

public class Type {
    public byte kind;
    public static final byte BOOL = 0, INTG = 1, REAL = 1, ERROR = -1;
    
    public Type(byte kind) {
        this.kind = kind;
    }
    
    public boolean equalTo(Object o) {
        Type tipo = (Type)o;
        return(this.kind == tipo.kind || this.kind == ERROR || tipo.kind == ERROR);
    }
    
    /*public static Type bool = new Type(BOOL);
    public static Type intg = new Type(INTG);
    public static Type real = new Type(REAL);
    public static Type error = new Type(ERROR);*/
}
