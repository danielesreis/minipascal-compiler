package compilador.ast;
public class DeclaracaoSequencial extends Declaracao{
    public Declaracao D1, D2;
    
    public DeclaracaoSequencial(Declaracao D1, Declaracao D2) {
        this.D1 = D1;
        this.D2 = D2;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitDeclaracaoSequencial(this, o);
    }
}
