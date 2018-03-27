package compilador.ast;
public class TipoSimples extends Tipo{
    public String spelling;
    
    public TipoSimples(String spelling) {
        this.spelling = spelling;
    }
    
    public void visit (Visitor v){
        v.visitTipoSimples(this);
    }
}
