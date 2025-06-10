package Controlador;

import DAO.UsuarioDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controlador para gestionar la autenticación de usuarios.
 */
public class LoginControlador {

    private static final Logger logger = LoggerFactory.getLogger(LoginControlador.class);
    private final UsuarioDAO usuarioDAO;

    // Constructor con inyección para testing o flexibilidad
    public LoginControlador(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    // Constructor por defecto
    public LoginControlador() {
        this(new UsuarioDAO());
    }

    /**
     * Valida las credenciales de inicio de sesión.
     *
     * @param usuario    Nombre de usuario
     * @param contrasena Contraseña ingresada
     * @return true si las credenciales son válidas, false en caso contrario
     */
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
