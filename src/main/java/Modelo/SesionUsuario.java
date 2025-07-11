package Modelo;

public class SesionUsuario {
    private static String nombreUsuario;
    private static String rol;

    public static void iniciarSesion(String usuario, String rolUsuario) {
        nombreUsuario = usuario;
        rol = rolUsuario;
    }

    public static String getUsuario() {
        return nombreUsuario;
    }

    public static String getRol() {
        return rol;
    }

    public static boolean esAdmin() {
        return "ADMIN".equalsIgnoreCase(rol);
    }

    public static boolean esTrabajador() {
        return "TRABAJADOR".equalsIgnoreCase(rol);
    }

    public static boolean esTI() {
        return "TI".equalsIgnoreCase(rol);
    }

    public static boolean esSupervisor() {
        return "SUPERVISOR".equalsIgnoreCase(rol);
    }
    
    public static boolean esGerente() {
        return "GERENTE".equalsIgnoreCase(rol);
    }
}
