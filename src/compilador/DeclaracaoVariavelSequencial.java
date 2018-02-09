package compilador;
public class DeclaracaoVariavelSequencial extends Declaracao{
    IdentifierSequencial IS;
    Tipo T;
    
    public DeclaracaoVariavelSequencial(IdentifierSequencial IS, Tipo T) {
        this.IS = IS;
        this.T = T;
    }
}
