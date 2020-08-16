package yncrea.lab01.utils;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtils {

    private TestUtils(){
        super();
    }

    public static void shouldHaveMethod(Class<?> clazz,String methodName, Class<?>... parametersType) throws NoSuchMethodException {
        //GIVEN
        //WHEN
        Method initMethod = clazz.getDeclaredMethod(methodName, parametersType);
        //THEN
        assertThat(initMethod).isNotNull();
    }
}
