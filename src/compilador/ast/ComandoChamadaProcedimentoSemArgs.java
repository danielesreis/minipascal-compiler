package compilador.ast;
public class ComandoChamadaProcedimentoSemArgs extends Comando{
    public Identifier I;
    
    public ComandoChamadaProcedimentoSemArgs(Identifier I) {
        this.I = I;
    }
    
    public void visit (Visitor v){
        v.visitComandoChamadaProcedimentoSemArgs(this);
    }
}
