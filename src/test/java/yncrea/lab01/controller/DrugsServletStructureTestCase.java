package yncrea.lab01.controller;

import org.junit.jupiter.api.Test;
import yncrea.lab01.utils.TestUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;


public class DrugsServletStructureTestCase {

    @Test
    public void shouldExtendHttpServlet(){
        //GIVEN
        DrugsServlet servlet = new DrugsServlet();
        //WHEN
        //THEN
        assertThat(servlet).isInstanceOf(HttpServlet.class);
    }

    @Test
    public void shouldHaveWebServletAnnotation() {
        //GIVEN
        Class<DrugsServlet> clazz = DrugsServlet.class;
        WebServlet[] annotations = clazz.getAnnotationsByType(WebServlet.class);
        //WHEN
        //THEN
        assertThat(annotations).hasSize(1);
        assertThat(annotations[0].urlPatterns()).containsOnly("/drugs");
    }



    @Test
    public void shouldHaveInitMethod() throws NoSuchMethodException {
        TestUtils.shouldHaveMethod(DrugsServlet.class,"init");
    }

    @Test
    public void shouldHaveDoGetMethod() throws NoSuchMethodException {
        TestUtils.shouldHaveMethod(DrugsServlet.class,"doGet", HttpServletRequest.class, HttpServletResponse.class);
    }

    @Test
    public void shouldHaveDoPostMethod() throws NoSuchMethodException {
        TestUtils.shouldHaveMethod(DrugsServlet.class,"doPost", HttpServletRequest.class, HttpServletResponse.class);
    }





}
