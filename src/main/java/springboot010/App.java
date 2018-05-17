package springboot010;

import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import redis.clients.jedis.Jedis;

@EnableRedis
@SpringBootApplication
public class App {
	
	public static void main(String[] args) throws SQLException {
		
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		Jedis client = context.getBean(Jedis.class);
		System.out.println(context.getBean(Jedis.class));
		client.set("name", "rootroot");
		System.out.println(client.get("name"));
		context.close();
		
	}

}
