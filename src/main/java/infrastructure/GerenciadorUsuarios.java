package infrastructure;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.Entities.Usuario.Usuario;
import domain.Enum.TipoUsuarioEnum;
import domain.Exceptions.UsuarioNaoEncontradoException;
import domain.Interfaces.UsuarioRepository;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorUsuarios implements UsuarioRepository 
{
    private static final String FILE_PATH = "usuarios.json";
    private final List<Usuario> usuarios;
    private final Gson gson;

    public GerenciadorUsuarios() 
    {
        this.gson = new Gson();
        this.usuarios = carregarUsuarios();
    }

    private List<Usuario> carregarUsuarios()
    {
        try (Reader reader = new FileReader(FILE_PATH)) 
        {
            Type listType = new TypeToken<ArrayList<Usuario>>() {}.getType();
            return gson.fromJson(reader, listType);
        } 
        catch (FileNotFoundException e) 
        {
            return new ArrayList<>();
        } 
        catch (IOException e) 
        {
            return new ArrayList<>();
        }
    }

    private void salvarUsuarios()
    {
        try (Writer writer = new FileWriter(FILE_PATH)) 
        {
            gson.toJson(usuarios, writer);
        } 
        catch (IOException e) 
        {
            System.out.println("Erro na hora de tentar salvar os usuários no arquivo JSON");
        }
    }

    @Override
    public void adicionar(Usuario usuario) 
    {
        usuarios.add(usuario);
        salvarUsuarios();
    }

    @Override
    public void remover(String cpf) 
    {
        usuarios.removeIf(u -> u.getCpf().equals(cpf));
        salvarUsuarios();
    }
    
    @Override
    public void editar(String cpf, String novaSenha, TipoUsuarioEnum tipo) 
    {
        boolean encontrado = false;
        for (Usuario u : usuarios) 
        {
            if (u.getCpf().equals(cpf)) 
            {
                u.setSenha(novaSenha);
                u.setTipoUsuario(tipo);
                salvarUsuarios();
                encontrado = true;
                break;
            }
        }
        if (!encontrado)
        {
            throw new UsuarioNaoEncontradoException(cpf);
        }
    }

    @Override
    public List<Usuario> listarUsuarios() 
    {
        return usuarios;
    }
}
