package yncrea.lab01.filter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yncrea.lab01.model.Pharmacist;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SessionFilterBehaviourTestCase {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain chain;


    @Mock
    private ServletContext context;

    @Mock
    private HttpSession session;



    @Test
    public void shouldByPassIfUrlIsLogin() throws IOException, ServletException {
        //GIVEN
        when(request.getServletPath()).thenReturn("/login");
        Filter filter = new SessionFilter();
        //WHEN
        filter.doFilter(request, response, chain);
        //THEN
        verify(response, never()).sendRedirect(anyString());
        verify(chain, times(1)).doFilter(eq(request), eq(response));
    }

    @Test
    public void shouldByPassIfUrlIsIndexJsp() throws IOException, ServletException {
        //GIVEN
        when(request.getServletPath()).thenReturn("/index.jsp");
        Filter filter = new SessionFilter();
        //WHEN
        filter.doFilter(request, response, chain);
        //THEN
        verify(response, never()).sendRedirect(anyString());
        verify(chain, times(1)).doFilter(eq(request), eq(response));
    }

    @Test
    public void shouldByPassIfAlreadyLoggedIn() throws IOException, ServletException {
        //GIVEN
        when(session.getAttribute(eq("loggedPharmacist"))).thenReturn(new Pharmacist("pharm","pwd"));
        when(request.getSession()).thenReturn(session);
        when(request.getServletPath()).thenReturn("/someUrl");
        Filter filter = new SessionFilter();
        //WHEN
        filter.doFilter(request, response, chain);
        //THEN
        verify(response, never()).sendRedirect(anyString());
        verify(chain, times(1)).doFilter(eq(request), eq(response));
    }

    @Test
    public void shouldRedirectToLogIn() throws IOException, ServletException {
        //GIVEN
        when(session.getAttribute(eq("loggedPharmacist"))).thenReturn(null);
        when(request.getSession()).thenReturn(session);
        when(request.getServletPath()).thenReturn("/someUrl");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("contextPath");
        Filter filter = new SessionFilter();
        //WHEN
        filter.doFilter(request, response, chain);
        //THEN
        verify(response, times(1)).sendRedirect("contextPath/");
        verify(chain, never()).doFilter(eq(request), eq(response));
    }


}
