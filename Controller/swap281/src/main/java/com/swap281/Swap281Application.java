package com.swap281;
import java.util.Map;

import com.swap281.repository.*;
import com.swap281.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Swap281Application {
	
	public static Database getDatabase(){
		Map<String, String> env = System.getenv();
		String db_url = env.get("DATABASE_URL");
		final Database database = Database.getDatabase(db_url);
		return database;
	}

	public static void main(String[] args) {
		SpringApplication.run(Swap281Application.class, args);
		// we need to call it before we do anything else with Spark.
        // Our server runs on port 4567. That's the Java Spark default

        // gson provides us with a way to turn JSON into objects, and objects
        // into JSON.
        //
        // NB: it must be final, so that it can be accessed from our lambdas
        //
        // NB: Gson is thread-safe. See
        // https://stackoverflow.com/questions/10380835/is-it-ok-to-use-gson-instance-as-a-static-field-in-a-model-bean-reuse
        final Gson gson = new Gson();

        String static_location_override = System.getenv("STATIC_LOCATION");

        // hardcode data entries

	}

}
