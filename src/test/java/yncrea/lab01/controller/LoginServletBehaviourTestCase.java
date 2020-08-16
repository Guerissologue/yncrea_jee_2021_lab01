package yncrea.lab01.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yncrea.lab01.model.Pharmacist;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginServletBehaviourTestCase {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private ServletContext context;

    @Mock
    private HttpSession session;

    @Test
    public void shouldNotHavePharmacistsBeforeInit() throws NoSuchFieldException, IllegalAccessException, ServletException {
        //GIVEN
        Class<LoginServlet> clazz = LoginServlet.class;
        Field pharmacistsField = clazz.getDeclaredField("pharmacists");
        pharmacistsField.setAccessible(true);
        ParameterizedType listType = (ParameterizedType) pharmacistsField.getGenericType();
        LoginServlet servlet = new LoginServlet();
        //WHEN
        Object pharmacists = pharmacistsField.get(servlet);
        //THEN
        assertThat(pharmacists).isNull();
        assertThat(pharmacistsField.getType()).isEqualTo(List.class);
        assertThat(listType.getActualTypeArguments()[0]).isEqualTo(Pharmacist.class);
        
    }

    @Test
    public void shouldHavePharmacistsAfterInit() throws NoSuchFieldException, IllegalAccessException, ServletException {
        //GIVEN
        Class<LoginServlet> clazz = LoginServlet.class;
        Field pharmacistsField = clazz.getDeclaredField("pharmacists");
        pharmacistsField.setAccessible(true);
        LoginServlet servlet = new LoginServlet();
        ((HttpServlet)servlet).init();
        //WHEN
        Object pharmacists = pharmacistsField.get(servlet);
        //THEN
        assertThat(pharmacists).isInstanceOf(List.class);
        List<?> list = (List<?>) pharmacists;
        assertThat(list).hasSize(2);
        assertThat(list.get(0)).isInstanceOf(Pharmacist.class);
        assertThat(list.get(1)).isInstanceOf(Pharmacist.class);
        List<Pharmacist> pharmacistsList = (List<Pharmacist>) pharmacists;
        assertThat(pharmacistsList).extracting("login", "password").containsExactly(tuple("pharm1", "password1"), tuple("pharm2", "password2"));
    }

    @Test
    public void shouldLogoutIfUrlIsGood() throws ServletException, IOException {
        //GIVEN
        when(request.getSession()).thenReturn(session);
        when(request.getQueryString()).thenReturn("logout");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("contextPath");
        LoginServlet servlet = new LoginServlet();
        ((HttpServlet)servlet).init();
        //WHEN
        servlet.doGet(request, response);
        //THEN
        verify(session, times(1)).removeAttribute(eq("loggedPharmacist"));
        verify(response, times(1)).sendRedirect(eq(request.getServletContext().getContextPath()));
    }

    @Test
    public void shouldNotLogoutIfUrlIsBad() throws ServletException, IOException {
        //GIVEN
        when(request.getQueryString()).thenReturn("someUrl");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("contextPath");
        LoginServlet servlet = new LoginServlet();
        ((HttpServlet)servlet).init();
        //WHEN
        servlet.doGet(request,response);
        //THEN
        verify(session,never()).removeAttribute(eq("loggedPharmacist"));
        verify(response, never()).sendRedirect(eq(request.getServletContext().getContextPath()));
    }

    @Test
    public void shouldNotLogInIfCredentialsAreWrong() throws ServletException, IOException {
        //GIVEN
        when(request.getParameter(eq("login"))).thenReturn("loginTest");
        when(request.getParameter(eq("password"))).thenReturn("passwordTest");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        LoginServlet servlet = new LoginServlet();
        ((HttpServlet)servlet).init();
        //WHEN
        servlet.doPost(request, response);
        //THEN
        verify(request,times(1)).setAttribute(eq("loginError"), eq("Invalid credentials!"));
        verify(request,times(1)).getRequestDispatcher(eq("/index.jsp"));
        verify(dispatcher,times(1)).forward(eq(request), eq(response));
    }

    @Test
    public void shouldLogInIfCredentialsAreGood() throws ServletException, IOException {
        //GIVEN
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(eq("login"))).thenReturn("pharm1");
        when(request.getParameter(eq("password"))).thenReturn("password1");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("contextPath");
        LoginServlet servlet = new LoginServlet();
        ((HttpServlet)servlet).init();
        //WHEN
        servlet.doPost(request, response);
        //THEN
        verify(request,times(1)).removeAttribute(eq("loginError"));
        verify(session,times(1)).setAttribute(eq("loggedPharmacist"),eq (new Pharmacist("pharm1", "password1")));
        verify(response,times(1)).sendRedirect(eq(request.getServletContext().getContextPath()+"/drugs"));

    }

}
