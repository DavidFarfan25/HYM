// TIDAO.java
package DAO;

import Modelo.Usuario;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class TIDAO {

    private static final Logger logger = LoggerFactory.getLogger(TIDAO.class);
    private final Connection connection;

    public TIDAO(Connection connection) {
        this.connection = connection;
    }

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

    public String obtenerRolUsuario(String username) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(username), "El nombre de usuario no puede estar vacío");
        String sql = "SELECT rol FROM Usuario WHERE nombreUsuario = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String rol = rs.getString("rol");
                    logger.info("Rol del usuario {}: {}", username, rol);
                    return rol;
                } else {
                    logger.warn("No se encontró rol para el usuario: {}", username);
                    return null;
                }
            }

        } catch (SQLException e) {
            logger.error("Error al obtener rol del usuario {}: {}", username, e.getMessage(), e);
            return null;
        }
    }

    public Usuario buscarPorId(String id) {
        String sql = "SELECT * FROM Usuario WHERE usuario_id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("usuario_id"));
                usuario.setNombre(rs.getString("nombreUsuario"));
                usuario.setContraseña(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
                usuario.setActivo(rs.getBoolean("estado"));
                usuario.setCorreo(rs.getString("email"));
                usuario.setUltimoAcceso(rs.getTimestamp("ultimo_acceso"));
                return usuario;
            }
        } catch (SQLException e) {
            logger.error("Error al buscar usuario por ID: {}", e.getMessage(), e);
        }
        return null;
    }

    public boolean eliminarUsuarioPorId(String id) {
    String sql = "DELETE FROM Usuario WHERE usuario_id = ?";
    try (Connection conexion = ConexionBD.obtenerConexion();
         PreparedStatement stmt = conexion.prepareStatement(sql)) {

        stmt.setString(1, id);
        int filasAfectadas = stmt.executeUpdate();
        return filasAfectadas > 0;

    } catch (SQLException e) {
        logger.error("Error al eliminar usuario: {}", e.getMessage(), e);
        return false;
    }
}

   public int agregarUsuario(Usuario usuario) {
    String sql = "INSERT INTO Usuario (nombreUsuario, contrasena, rol, estado, email, ultimo_acceso) " +
                 "VALUES (?, ?, ?, ?, ?, ?)";

    try(Connection conexion = ConexionBD.obtenerConexion();
         PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {  // 🔁 corregido aquí

        stmt.setString(1, usuario.getNombre());
        stmt.setString(2, usuario.getContraseña());
        stmt.setString(3, usuario.getRol());
        stmt.setBoolean(4, usuario.isActivo());
        stmt.setString(5, usuario.getCorreo());
        stmt.setTimestamp(6, usuario.getUltimoAcceso());

        int filasAfectadas = stmt.executeUpdate();
        if (filasAfectadas > 0) {
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
    } catch (SQLException e) {
        logger.error("Error al agregar usuario: {}", e.getMessage(), e);
    }
    return -1;
}

    public boolean actualizarEstadoSistema(String nuevoEstado) {
        String sql = "UPDATE EstadoSistema SET nombre_estado = ? WHERE id = 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nuevoEstado);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error al actualizar el estado del sistema: {}", e.getMessage(), e);
            return false;
        }
    }
}