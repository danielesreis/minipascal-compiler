package compilador.ast;
public class ComandoChamadaProcedimentoSemArgs extends Comando{
    public Identifier I;
    
    public ComandoChamadaProcedimentoSemArgs(Identifier I) {
        this.I = I;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitComandoChamadaProcedimentoSemArgs(this, o);
    }
}
