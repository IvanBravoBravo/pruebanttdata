package com.nttdata.prueba.service;

import com.nttdata.prueba.entity.Telefono;
import com.nttdata.prueba.entity.Usuario;
import com.nttdata.prueba.exception.InvalidEmailException;
import com.nttdata.prueba.exception.InvalidPasswordException;
import com.nttdata.prueba.exception.UsuarioExisteException;
import com.nttdata.prueba.exception.UsuarioNotFoundException;
import com.nttdata.prueba.model.TelefonoRequest;
import com.nttdata.prueba.model.User;
import com.nttdata.prueba.model.UsuarioRequest;
import com.nttdata.prueba.model.UsuarioResponse;
import com.nttdata.prueba.repository.TelefonoRepository;
import com.nttdata.prueba.repository.UsuarioRepository;
import com.nttdata.prueba.util.AppUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TelefonoRepository telefonoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AppUtils utils;

    public Optional<UsuarioResponse> getUsuario(String id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        UsuarioResponse usuarioResponse = modelMapper.map(usuario, UsuarioResponse.class);
        return Optional.ofNullable(usuarioResponse);
    }

    public UsuarioResponse postUsuario(UsuarioRequest usuarioRequest)
            throws UsuarioExisteException, InvalidEmailException, InvalidPasswordException {
        if(!utils.isValidEmail(usuarioRequest.getCorreo())){
            throw new InvalidEmailException();
        }
        if(!utils.isValidPassword(usuarioRequest.getContraseña())){
            throw new InvalidPasswordException();
        }
        Optional<Usuario> usuarioSaved = usuarioRepository.findByCorreo(usuarioRequest.getCorreo());
        if(usuarioSaved.isPresent()){
            throw new UsuarioExisteException();
        }

        Usuario usuario = modelMapper.map(usuarioRequest, Usuario.class);
        List<TelefonoRequest> telefonosRequest = usuarioRequest.getTelefonos();
        List<Telefono> telefonos = utils.mapList(telefonosRequest,Telefono.class);

        User user = new User();
        user.setEmail(usuarioRequest.getCorreo());
        user.setPassword(usuarioRequest.getContraseña());
        String jwtToken = jwtService.generateToken(user);
        usuario.setToken(jwtToken);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String passEncode = encoder.encode(usuarioRequest.getContraseña());
        usuario.setContraseña(passEncode);

        Usuario usuarioSave = usuarioRepository.save(usuario);

        for (Telefono telefono : telefonos){
            telefono.setUsuario(usuario);
            telefonoRepository.save(telefono);
        }

        UsuarioResponse response = modelMapper.map(usuarioSave, UsuarioResponse.class);
        return response;
    }

    public void putUsuario(UsuarioRequest usuarioRequest) throws UsuarioNotFoundException {
        Optional<Usuario> usuarioSaved = Optional.ofNullable(usuarioRepository.findByCorreo(usuarioRequest.getCorreo())
                .orElseThrow(() -> new UsuarioNotFoundException()));

        Usuario usuario = modelMapper.map(usuarioRequest, Usuario.class);
        usuario.setId(usuarioSaved.get().getId());
        usuario.setCreado(usuarioSaved.get().getCreado());
        usuario.setUltimoLogin(usuarioSaved.get().getUltimoLogin());
        usuario.setToken(usuarioSaved.get().getToken());
        usuarioRepository.save(usuario);
    }


    public List<UsuarioResponse> getUsuarios(){
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        List<UsuarioResponse> usuariosResponse = utils.mapList(usuarios,UsuarioResponse.class);
        return usuariosResponse;
    }

    public void deleteUsuario(String id) throws UsuarioNotFoundException {
        Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException()));
        usuarioRepository.delete(usuario.get());
    }
}
