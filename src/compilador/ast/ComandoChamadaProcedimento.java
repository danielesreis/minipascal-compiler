package compilador.ast;
public class ComandoChamadaProcedimento extends Comando{
    public Identifier I;
    public Expressao E;
    
    public ComandoChamadaProcedimento(Identifier I, Expressao E) {
        this.I = I;
        this.E = E;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitComandoChamadaProcedimento(this, o);
    }
}
