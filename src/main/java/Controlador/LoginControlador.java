package Controlador;

import DAO.UsuarioDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginControlador {

    private static final Logger logger = LoggerFactory.getLogger(LoginControlador.class);
    private final UsuarioDAO usuarioDAO;

    public LoginControlador(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public LoginControlador() {
        this(new UsuarioDAO());
    }
    
    public boolean validarLogin(String usuario, String contrasena) {
        logger.info("Intento de inicio de sesión para el usuario: {}", usuario);

        boolean valido = usuarioDAO.validarCredenciales(usuario, contrasena);

        if (valido) {
            logger.info("Inicio de sesión exitoso para: {}", usuario);
        } else {
            logger.warn("Inicio de sesión fallido para: {}", usuario);
        }

        return valido;
    }
}
