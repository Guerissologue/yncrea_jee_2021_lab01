package yncrea.lab01.filter;

import org.junit.jupiter.api.Test;
import yncrea.lab01.utils.TestUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;


public class SessionFilterStructureTestCase {

    @Test
    public void shouldImplementFilter(){
        //GIVEN
        SessionFilter filter = new SessionFilter();
        //WHEN
        //THEN
        assertThat(filter).isInstanceOf(HttpFilter.class);
    }

    @Test
    public void shouldHaveWebFilterAnnotation() {
        //GIVEN
        Class<SessionFilter> clazz = SessionFilter.class;
        WebFilter[] annotations = clazz.getAnnotationsByType(WebFilter.class);
        //WHEN
        //THEN
        assertThat(annotations).hasSize(1);
        assertThat(annotations[0].urlPatterns()).containsOnly("/*");
    }





    @Test
    public void shouldHaveDoFilterMethod() throws NoSuchMethodException {
        TestUtils.shouldHaveMethod(SessionFilter.class,"doFilter", HttpServletRequest.class, HttpServletResponse.class,FilterChain.class);
    }



}
