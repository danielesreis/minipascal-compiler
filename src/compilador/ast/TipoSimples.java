package compilador.ast;
public class TipoSimples extends Tipo{
    public String spelling;
    
    public TipoSimples(String spelling) {
        this.spelling = spelling;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitTipoSimples(this, o);
    }
}
