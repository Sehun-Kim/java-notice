package com.assignment.rsupport.support.test;

import com.assignment.rsupport.noticejava.domain.user.User;
import com.assignment.rsupport.noticejava.domain.user.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest extends BaseTest {
    private static final String TESTER = "tester";

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private UserRepository userRepository;

    public TestRestTemplate template() {
        return this.template;
    }

    protected User defaultUser() {
        return userRepository.findByUserId(TESTER).get();
    }

    public TestRestTemplate basicAuthTemplate() {
        return basicAuthTemplate(defaultUser());
    }

    private TestRestTemplate basicAuthTemplate(User defaultUser) {
        return this.template.withBasicAuth(defaultUser.getUserId(), defaultUser.getPassword());
    }

}
