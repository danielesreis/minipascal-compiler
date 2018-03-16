package compilador.ast;
public class TipoSimples extends Tipo{
    String spelling;
    
    public TipoSimples(String spelling) {
        this.spelling = spelling;
    }
    
    public void visit (Visitor v){
        v.visitTipoSimples(this);
    }
}
