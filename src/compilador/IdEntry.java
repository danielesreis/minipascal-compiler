package compilador;
import compilador.ast.Declaracao;
import compilador.ast.Tipo;

public class IdEntry {
    String id;
    Declaracao declaracao;
    int level;
    IdEntry previous;
    Tipo tipo;
    
    public IdEntry(String id, Declaracao declaracao, int level, IdEntry previous, Tipo tipo) {
        this.id = id;
        this.declaracao = declaracao;
        this.level = level;
        this.previous = previous;
        this.tipo = tipo;
    }
}