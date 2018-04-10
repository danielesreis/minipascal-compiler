package compilador.ast;

import compilador.Token;

public abstract class Tipo extends AST{
    public byte kind;
    public static final byte BOOL_LIT = 0, INT_LIT = 1, FLOAT_LIT = 2, UND = 3, ERROR = -1;
        
    public boolean equalTo(Object o) {
        Tipo tipo = (Tipo)o;
        String spellingT = "";
        byte byteT = Tipo.ERROR;
        
        if (tipo.kind == Token.TIPO_SIMPLES) byteT = Tipo.getTypeByte(((TipoSimples)tipo).spelling);
        //else if (tipo.kind == Token.)
        
        return(this.kind == byteT || this.kind == ERROR || byteT == ERROR);
    }
    
    public static byte getTypeByte(String spelling) {
        Byte byteT;
        
        if (spelling.toLowerCase().equals("integer")) byteT = Tipo.INT_LIT;
        else if (spelling.toLowerCase().equals("real")) byteT = Tipo.FLOAT_LIT;
        else if (spelling.toLowerCase().equals("boolean")) byteT = Tipo.BOOL_LIT;
        else byteT = Tipo.ERROR;
        
        return byteT;
    }
}
