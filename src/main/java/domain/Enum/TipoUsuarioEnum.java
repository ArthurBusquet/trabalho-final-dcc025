/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package domain.Enum;

public enum TipoUsuarioEnum 
{
    CLIENTE(1),
    CAIXA(2),
    GERENTE(3);

    private final int tipoUsuario;

    TipoUsuarioEnum(int tipoUsuario)
    {
        this.tipoUsuario = tipoUsuario;
    }

    public int getTipoUsuario() 
    {
        return tipoUsuario;
    } 
}
