package compilador;
public class DeclaracaoVariavel extends Declaracao{
    IdentifierSequencial IS;
    Tipo T;
    
    public DeclaracaoVariavel(IdentifierSequencial IS, Tipo T) {
        this.IS = IS;
        this.T = T;
    }
}
