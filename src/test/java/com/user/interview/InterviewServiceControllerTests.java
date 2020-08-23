package com.user.interview;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InterviewServiceControllerTests {
    private InterviewServiceController classUnderTest = new InterviewServiceController();


    @Test
    void testThatValidUserReturnsSuccess() {
        User user = new User();
        user.setFullName("man hunt");
        user.setIdNumber("1234567891234");
        user.setId("1234567891234");
        assertThat(classUnderTest.createPerson(user)).isNotNull();
        assertThat(classUnderTest.createPerson(user)).isEqualTo(new ResponseEntity<>(HttpStatus.CREATED));
    }

    @Test
    void testThatLettersInIDNumberReturnError() {
        User user = new User();
        user.setFullName("man hunt");
        user.setIdNumber("12345e67891234");
        user.setId("1234567891234");
        assertThat(classUnderTest.createPerson(user)).isNotNull();
        assertThat(classUnderTest.createPerson(user).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testThatNumbersInNameReturnError() {
        User user = new User();
        user.setFullName("man hunt321");
        user.setIdNumber("1234567891234");
        user.setId("1234567891234");
        assertThat(classUnderTest.createPerson(user)).isNotNull();
        assertThat(classUnderTest.createPerson(user).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
