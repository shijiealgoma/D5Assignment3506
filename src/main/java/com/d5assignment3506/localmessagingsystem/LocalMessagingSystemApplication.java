package com.d5assignment3506.localmessagingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class LocalMessagingSystemApplication implements CommandLineRunner {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(LocalMessagingSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String sql = "INSERT INTO public.auth(\n" +
                "\tuser_id, username, password, fristname, lastname, email, created_on, last_login)\n" +
                "\tVALUES (1, 'admin', 'admin', 'admin', 'admin', 'admin@localhost.com', null, null);";

        int row = jdbcTemplate.update(sql);
		if (row > 0) {
			System.out.println("A new row has been inserted.");
		}

    }
}
