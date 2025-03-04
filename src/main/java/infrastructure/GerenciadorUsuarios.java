package infrastructure;

import application.Exceptions.DadoInseridoInvalidoException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.Entities.Usuarios.Usuario;
import domain.Enum.TipoUsuarioEnum;
import domain.Exceptions.UsuarioNaoEncontradoException;
import domain.Interfaces.UsuarioRepository;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public void salvarUsuarios()
    {
        try (Writer writer = new FileWriter(FILE_PATH)) 
        {
            Gson gsonPretty = new Gson().newBuilder().setPrettyPrinting().create();
            gsonPretty.toJson(usuarios, writer);
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
        try
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
        }catch(DadoInseridoInvalidoException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Usuario> listarUsuarios() 
    {
        return usuarios;
    }
    
    @Override
    public boolean existeUsuario(String cpf)
    {
        return usuarios.stream().anyMatch(u -> u.getCpf().equalsIgnoreCase(cpf));
    }   
    
    public boolean validarLogin(String cpf, String senha) 
    {
        for (Usuario usuario : usuarios)
        {   
            if (usuario.getCpf().equals(cpf) && usuario.getSenha().equals(senha)) 
            {
                return true;
            }
        }
        return false;
    }

    public Usuario buscarUsuarioPorCpf(String cpf) throws UsuarioNaoEncontradoException 
    {
        return usuarios.stream()
                .filter(u -> u.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new UsuarioNaoEncontradoException(cpf));
    }
}
