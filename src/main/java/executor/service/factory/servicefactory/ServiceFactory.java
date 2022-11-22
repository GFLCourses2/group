package executor.service.factory.servicefactory;

public interface ServiceFactory {
    <T> T create(Class<T> clazz);
}
