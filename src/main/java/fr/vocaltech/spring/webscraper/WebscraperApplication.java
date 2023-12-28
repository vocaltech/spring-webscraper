package fr.vocaltech.spring.webscraper;

import fr.vocaltech.spring.webscraper.beans.SavePlaylistDbRunner;
import fr.vocaltech.spring.webscraper.models.Music;
import fr.vocaltech.spring.webscraper.repositories.MusicRepository;
import fr.vocaltech.spring.webscraper.services.SmoothJazzScraperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableScheduling
public class WebscraperApplication {
	private static final Logger logger = LoggerFactory.getLogger(WebscraperApplication.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    MusicRepository musicRepository;

    @Autowired
    SmoothJazzScraperService smoothJazzScraperService;

    @Autowired
    ResourceLoader resourceLoader;

	public static void main(String[] args) {
		SpringApplication.run(WebscraperApplication.class, args);
	}

    @Bean
    public SavePlaylistDbRunner processSavePlaylistDb() {
        return new SavePlaylistDbRunner();
    }

	/*
	@Bean
	public CommandLineRunner loadedBeans(ApplicationContext ctx) {
		return args -> {
			String[] beanNames = ctx.getBeanDefinitionNames();

			Arrays.stream(beanNames)
					.sorted()
					.forEach(System.out::println);
		};
	}
	*/

	/*
	@Bean
	public CommandLineRunner populateDb(CustomerRepository repository) {
		return args -> {
			// save a few customer
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			Iterable<Customer> customers = repository.findAll();
			customers.forEach(customer -> {
				logger.info(customer.toString());
			});
		};
	}
	@Bean
	public BeanOneRunner processOne() {
		return new BeanOneRunner();
	}

	@Bean
	public BeanTwoRunner processTwo() {
		return new BeanTwoRunner();
	}

	@Bean
	public BeanThreeRunner processThree() {
		return new BeanThreeRunner();
	}

	 */
}
