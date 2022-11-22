package executor.service.factory.servicefactory;

import executor.service.config.ApplicationConfig;
import executor.service.config.ConfigHolder;
import executor.service.factory.webdriver.WebDriverInitializer;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultServiceFactory implements ServiceFactory {

    private final ConfigHolder configHolder = new ConfigHolder();
    private final ApplicationConfig config = new ApplicationConfig(
            configHolder,
            new WebDriverInitializer(
                    configHolder.getProxyConfigHolder(),
                    configHolder.getWebDriverConfig()));

    @Override
    public <T> T create(Class<T> clazz) {
        if (clazz.isInterface()) {
            if (config.getImplementation(clazz) != null)
                return config.getImplementation(clazz);

            Reflections reflections = new Reflections("executor.service");
            Set<Class<? extends T>> implementations = reflections.getSubTypesOf(clazz).stream()
                    .filter(aClass -> !aClass.isInterface())
                    .collect(Collectors.toSet());
            if (implementations.size() != 1) {
                System.out.println(implementations.size());
                throw new RuntimeException("Implementations count for " + clazz.getTypeName() + " should be 1");
            }
            return createImpl(implementations.stream().findFirst().get());

        }
        return createImpl(clazz);
    }

    private <T> T createImpl(Class<T> clazz) {
        Constructor<?> constructor = Arrays.stream(clazz.getConstructors())
                .max(Comparator.comparing(Constructor::getParameterCount))
                .orElseThrow(RuntimeException::new);


        try {
            Constructor<T> allArgsConstructor = clazz.getConstructor(constructor.getParameterTypes());
            if (allArgsConstructor.getParameterCount() == 0) {
                return allArgsConstructor.newInstance();
            }
            return allArgsConstructor.newInstance(Arrays.stream(allArgsConstructor.getParameterTypes())
                    .map(this::checkForExistenceOrCreate).toArray(Object[]::new));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T checkForExistenceOrCreate(Class<T> clazz) {
        T configImplementation = config.getImplementation(clazz);
        if (configImplementation != null)
            return configImplementation;
        return create(clazz);
    }
}
