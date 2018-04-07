package compilador.ast;
public class ComandoSequencial extends Comando{
    public Comando C1, C2;
    
    public ComandoSequencial(Comando C1, Comando C2) {
        this.C1 = C1;
        this.C2 = C2;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitComandoSequencial(this, o);
    }
}
