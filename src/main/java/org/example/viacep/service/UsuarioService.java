package org.example.viacep.service;

import org.example.viacep.dto.EnderecoViaCepDTO;
import org.example.viacep.client.ViaCepClient;
import org.example.viacep.dto.UsuarioRequestDTO;
import org.example.viacep.entity.Usuario;
import org.example.viacep.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ViaCepClient viaCepClient;

    public Usuario cadastrarUsuario(UsuarioRequestDTO requestDTO) {
        EnderecoViaCepDTO endereco = viaCepClient.consultarCep(requestDTO.getCep());

        Usuario usuario = new Usuario();
        usuario.setNome(requestDTO.getNome());
        usuario.setCel(requestDTO.getCel());
        usuario.setCep(requestDTO.getCep());
        usuario.setLogradouro(endereco.getLogradouro());
        usuario.setBairro(endereco.getBairro());
        usuario.setLocalidade(endereco.getLocalidade());
        usuario.setUf(endereco.getUf());

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }
}