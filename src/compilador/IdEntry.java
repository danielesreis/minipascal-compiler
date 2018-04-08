package compilador;
import compilador.ast.Declaracao;
public class IdEntry {
    String id;
    Declaracao declaracao;
    int level;
    IdEntry previous;
    
    public IdEntry(String id, Declaracao declaracao, int level, IdEntry previous) {
        this.id = id;
        this.declaracao = declaracao;
        this.level = level;
        this.previous = previous;
    }
}