import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: huangxiang
 * @create: 2018/5/23 14:43
 * @description:
 */
@SpringBootApplication
@ComponentScan("com.course.server")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
