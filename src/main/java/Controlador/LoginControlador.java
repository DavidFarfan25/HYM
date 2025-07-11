package Controlador;

import DAO.ConexionBD;
import DAO.TIDAO;
import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginControlador {

    private static final Logger logger = LoggerFactory.getLogger(LoginControlador.class);
    private final TIDAO usuarioDAO;

    public LoginControlador(TIDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public LoginControlador() throws SQLException {
        Connection conn = ConexionBD.obtenerConexion();
        this.usuarioDAO = new TIDAO(conn);
    }

    public boolean validarLogin(String usuario, String contrasena) {
        try {
            logger.info("Intento de inicio de sesión para el usuario: {}", usuario);
            boolean valido = usuarioDAO.validarCredenciales(usuario, contrasena);

            if (valido) {
                logger.info("Inicio de sesión exitoso para: {}", usuario);
            } else {
                logger.warn("Inicio de sesión fallido para: {}", usuario);
            }
            return valido;
        } catch (Exception e) {
            logger.error("Error durante la validación de login para usuario {}: {}", 
                        usuario, e.getMessage(), e);
            return false;
        }
    }

    public String obtenerRol(String usuario) {
        return usuarioDAO.obtenerRolUsuario(usuario);
    }
}
