package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsuarioDAO {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioDAO.class);

    public boolean validarCredenciales(String username, String password) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(username), "El nombre de usuario no puede estar vacío");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(password), "La contraseña no puede estar vacía");

        String sql = "SELECT 1 FROM Usuario WHERE nombreUsuario = ? AND contrasena = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                boolean existe = rs.next();
                if (existe) {
                    logger.info("Credenciales válidas para usuario: {}", username);
                } else {
                    logger.warn("Intento de login fallido para usuario: {}", username);
                }
                return existe;
            }

        } catch (SQLException e) {
            logger.error("Error al validar credenciales del usuario {}: {}", username, e.getMessage(), e);
            return false;
        }
    }
}
