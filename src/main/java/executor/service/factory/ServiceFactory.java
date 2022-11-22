package executor.service.factory;

public interface ServiceFactory {
    <T> T create(Class<T> clazz);
}
