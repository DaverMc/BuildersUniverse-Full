package buun.exception.catching;

public interface ExceptionSupplier <T, E extends Exception> {

    T get() throws E;

}
