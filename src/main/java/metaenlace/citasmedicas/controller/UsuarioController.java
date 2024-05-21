package metaenlace.citasmedicas.controller;

import metaenlace.citasmedicas.entity.Usuario;
import metaenlace.citasmedicas.entitydto.UsuarioDTO;
import metaenlace.citasmedicas.repository.UsuarioRepository;
import metaenlace.citasmedicas.security.JWTUtil;
import metaenlace.citasmedicas.service.MedicoService;
import metaenlace.citasmedicas.service.PacienteService;
import metaenlace.citasmedicas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin()
@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    // iniciar sesion
    @PostMapping("login")
    public Map<String, Object> login(@RequestParam("user") String usuario, @RequestParam("password") String password )
    {
        try
        {
            // crear input token con los parametros recibidos
            UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
                    usuario, password
            );

            // autenticar usuario/password
            authenticationManager.authenticate(authInputToken);

            // devolver token
            String token = jwtUtil.generateToken(usuario);
            Map<String, Object> loginResponse = new HashMap<>();

            // retornar token
            loginResponse.put("jwt-token", token);

            // retornar tipo de usuario
            if (pacienteService.findByUsuario(usuario).isPresent()) {
                loginResponse.put("user-type", "paciente");
            } else if (medicoService.findByUsuario(usuario).isPresent()) {
                loginResponse.put("user-type", "medico");
            }

            return loginResponse;
        }
        catch (AuthenticationException ex)
        {
            throw new RuntimeException("Invalid Login Credentials : " + ex.getMessage());
        }
    }

    // retorna el usuario de la sesion
    @GetMapping("session")
    public UsuarioDTO getSession()
    {
        Optional<UsuarioDTO> usuario =  usuarioService.findByUsuario(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
        );

        return usuario.get();
    }
}
