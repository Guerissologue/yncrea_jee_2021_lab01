package yncrea.lab01.controller;

import org.junit.jupiter.api.Test;
import yncrea.lab01.utils.TestUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginServletStructureTestCase {

    @Test
    public void shouldExtendHttpServlet(){
        //GIVEN
        LoginServlet servlet = new LoginServlet();
        //WHEN
        //THEN
        assertThat(servlet).isInstanceOf(HttpServlet.class);
    }

    @Test
    public void shouldHaveWebServletAnnotation() {
        //GIVEN
        Class<LoginServlet> clazz = LoginServlet.class;
        WebServlet[] annotations = clazz.getAnnotationsByType(WebServlet.class);
        //WHEN
        //THEN
        assertThat(annotations).hasSize(1);
        assertThat(annotations[0].urlPatterns()).containsOnly("/login");
    }



    @Test
    public void shouldHaveInitMethod() throws NoSuchMethodException {
        TestUtils.shouldHaveMethod(LoginServlet.class,"init");
    }

    @Test
    public void shouldHaveDoGetMethod() throws NoSuchMethodException {
       TestUtils.shouldHaveMethod(LoginServlet.class,"doGet", HttpServletRequest.class, HttpServletResponse.class);
    }

    @Test
    public void shouldHaveDoPostMethod() throws NoSuchMethodException {
        TestUtils.shouldHaveMethod(LoginServlet.class,"doPost", HttpServletRequest.class, HttpServletResponse.class);
    }





}
