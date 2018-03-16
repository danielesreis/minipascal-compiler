package compilador.ast;
public class ComandoChamadaProcedimento extends Comando{
    Identifier I;
    Expressao E;
    
    public ComandoChamadaProcedimento(Identifier I, Expressao E) {
        this.I = I;
        this.E = E;
    }
    
    public void visit (Visitor v){
        v.visitComandoChamadaProcedimento(this);
    }
}
