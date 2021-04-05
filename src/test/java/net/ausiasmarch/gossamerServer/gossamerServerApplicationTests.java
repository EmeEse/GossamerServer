
package net.ausiasmarch.gossamerServer;
import net.ausiasmarch.gossamerServer.api.AppController;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;


@Controller
public class gossamerServerApplicationTests {

	@Test
    public void testAppController() {
        AppController homeController = new AppController();
        String result = homeController.info().getBody();
        assertEquals(result, "Welcome to gossamer");
    }


}