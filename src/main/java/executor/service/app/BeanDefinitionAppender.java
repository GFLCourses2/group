//package executor.service.app;
//
//import executor.service.annotation.Main;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Component
//@AllArgsConstructor
//public class BeanDefinitionAppender implements BeanPostProcessor {
//    private ConfigurableListableBeanFactory factory;
//
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) {
//        Arrays.stream(factory.getBeanDefinitionNames()).parallel().forEach(name -> {
//            if (hasMain(bean)) {
//                BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
//                if (beanDefinition.getBeanClassName() == null) {
//                    beanDefinition.setBeanClassName(bean.getClass().getName());
//                }
//            }
//        });
//        return bean;
//    }
//
//    private boolean hasMain(Object bean) {
//        return Arrays.stream(bean.getClass().getMethods())
//                .anyMatch(method -> method.isAnnotationPresent(Main.class));
//    }
//}
