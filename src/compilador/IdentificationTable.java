package compilador;
import compilador.ast.Declaracao;
import compilador.ast.DeclaracaoFuncao;
import compilador.ast.DeclaracaoFuncaoSemArgs;
import compilador.ast.DeclaracaoProcedure;
import compilador.ast.DeclaracaoProcedureSemArgs;
import compilador.ast.DeclaracaoVariavel;
import compilador.ast.Tipo;

public final class IdentificationTable {
    IdEntry latest;
    int level;
    
    public IdentificationTable() {
        level = 0;
        latest = null;
    }
    
    public void openScope(){
        level++;
    }
       
    public void closeScope() {
        IdEntry entry, local;
        entry = this.latest;
        
        while (entry.level == this.level) {
            local = entry;
            entry = local.previous;
        }
        this.level--;
        this.latest = entry;
    }
    
    public Declaracao retrieve(String id) {
        IdEntry entry;
        Declaracao declaracao = null;
        boolean present = false, searching = true;
        entry = this.latest;
        
        while (searching) {
        if (entry == null)
            searching = false;
        else if (entry.id.equals(id)) {
            present = true;
            searching = false;
            declaracao = entry.declaracao;
        } 
            else entry = entry.previous;
        }
        return declaracao;
    }
    
    public void enter(String id, Declaracao declaracao) {
        Tipo tipo = null;
        IdEntry entry = this.latest;
        boolean present = false, searching = true;

        while (searching) {
            if (entry == null || entry.level < this.level)
                searching = false;
            else if (entry.id.equals(id)) {
                present = true;
                searching = false;
            } 
            else entry = entry.previous;
        }

        declaracao.duplicated = present;
        
        if (declaracao instanceof DeclaracaoFuncao) tipo = ((DeclaracaoFuncao)declaracao).TS;
        else if (declaracao instanceof DeclaracaoFuncaoSemArgs) tipo = ((DeclaracaoFuncaoSemArgs)declaracao).TS;
        else if (declaracao instanceof DeclaracaoProcedure) tipo.kind = Tipo.UND;
        else if (declaracao instanceof DeclaracaoProcedureSemArgs) tipo.kind = Tipo.UND;
        else if (declaracao instanceof DeclaracaoVariavel) tipo = ((DeclaracaoVariavel)declaracao).T;
        
        entry = new IdEntry(id, declaracao, this.level, this.latest, tipo);
        this.latest = entry;
    }
}
