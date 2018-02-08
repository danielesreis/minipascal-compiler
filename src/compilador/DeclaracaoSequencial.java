package compilador;
public class DeclaracaoSequencial extends Declaracao{
    Declaracao D1, D2;
    
    public DeclaracaoSequencial(Declaracao D1, Declaracao D2) {
        this.D1 = D1;
        this.D2 = D2;
    }
}
