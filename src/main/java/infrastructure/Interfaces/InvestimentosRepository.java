package infrastructure.Interfaces;

import domain.Entities.RendaFixa;
import domain.Entities.RendaVariavel;
import java.util.List;

public interface InvestimentosRepository 
{
    void salvarRendaFixa(RendaFixa rendaFixa);
    void salvarRendaVariavel(RendaVariavel rendaVariavel);
    List<RendaFixa> listarRendaFixa();
    List<RendaVariavel> listarRendaVariavel();
}