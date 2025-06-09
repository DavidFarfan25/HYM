package Main;

import Vista.login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Iniciando aplicación HYM");

        try {
            login app = new login();
            app.setVisible(true);
            app.setLocationRelativeTo(null);
            logger.info("Interfaz de login mostrada correctamente");
        } catch (Exception e) {
            logger.error("Error al iniciar la aplicación: {}", e.getMessage(), e);
        }
    }
}
