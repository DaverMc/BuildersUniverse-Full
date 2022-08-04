package buun.exception.catching;

public interface ExceptionResultSupplier <T>{

    T get(Exception exception);

}
