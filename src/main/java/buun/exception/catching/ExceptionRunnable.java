package buun.exception.catching;

public interface ExceptionRunnable <E extends Exception>{

    void run() throws E;

}
