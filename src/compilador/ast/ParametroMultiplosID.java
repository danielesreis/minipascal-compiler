package compilador.ast;
public class ParametroMultiplosID extends Parametro{
    IdentifierSequencial IS;
    TipoSimples TS;
    
    public ParametroMultiplosID(IdentifierSequencial IS, TipoSimples TS) {
        this.IS = IS;
        this.TS = TS;
    }
}
