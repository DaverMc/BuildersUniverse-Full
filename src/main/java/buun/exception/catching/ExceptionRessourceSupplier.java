package buun.exception.catching;

public interface ExceptionRessourceSupplier<A extends AutoCloseable, T, E extends Exception> {

    T get(A autoClosable) throws E;

}
