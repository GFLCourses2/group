package executor.service.app;

import executor.service.annotation.Main;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class MainRunnerListener {

    private final ConfigurableListableBeanFactory factory;

    @EventListener
    public void handleContextRefreshedEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext context = contextRefreshedEvent.getApplicationContext();
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            BeanDefinition beanDefinition = factory.getBeanDefinition(name);
            String beanClassName = beanDefinition.getBeanClassName();
            if (beanClassName == null)
                continue;

            Class<?> beanClass = ClassUtils.resolveClassName(beanClassName, ClassLoader.getSystemClassLoader());

            Method[] methods = beanClass.getMethods();
            Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(Main.class))
                    .forEach(method -> {
                        Object bean = context.getBean(name);
                        try {
                            method.invoke(bean);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }
}