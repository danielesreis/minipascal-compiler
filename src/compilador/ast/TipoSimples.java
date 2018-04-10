package compilador.ast;
public class TipoSimples extends Tipo{
    public String spelling;
    
    public TipoSimples(String spelling, byte kind) {
        this.spelling = spelling;
        this.kind = kind;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitTipoSimples(this, o);
    }
}
