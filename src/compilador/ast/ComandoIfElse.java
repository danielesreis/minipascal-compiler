package compilador.ast;
public class ComandoIfElse extends Comando{
    public Expressao E;
    public Comando C1, C2;
    
    public ComandoIfElse(Expressao E, Comando C1, Comando C2) {
        this.E = E;
        this.C1 = C1;
        this.C2 = C2;
    }
    
    public void visit (Visitor v){
        v.visitComandoIfElse(this);
    }
}

