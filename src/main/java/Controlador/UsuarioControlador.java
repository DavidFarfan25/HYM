package Controlador;

import DAO.TIDAO;
import Modelo.Usuario;
import java.sql.Connection;

public class UsuarioControlador {
    private final TIDAO usuarioDAO;

    public UsuarioControlador(Connection connection){
        this.usuarioDAO = new TIDAO(connection);
    }


    public Usuario obtenerUsuarioPorId(String id) {
        return usuarioDAO.buscarPorId(id);
    }

    public boolean eliminarUsuario(String id) {
        return usuarioDAO.eliminarUsuarioPorId(id);
    }

    public int agregarUsuario(Usuario usuario) {
        return usuarioDAO.agregarUsuario(usuario);
    }
}