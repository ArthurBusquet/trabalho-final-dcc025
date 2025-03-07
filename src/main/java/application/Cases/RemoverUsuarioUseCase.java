/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package application.Cases;

import domain.Interfaces.UsuarioRepository;

public class RemoverUsuarioUseCase 
{
    private final UsuarioRepository usuarioRepository;

    public RemoverUsuarioUseCase(UsuarioRepository usuarioRepository) 
    {
        this.usuarioRepository = usuarioRepository;
    }

    public void executar(String cpf) 
    {
        usuarioRepository.remover(cpf);
    }
}
