package br.com.verx.challenge.renato.test;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.com.verx.challenge.renato.Bootstrap;

@SpringBootTest(classes=Bootstrap.class, webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AbstractChallengeTest {

}
