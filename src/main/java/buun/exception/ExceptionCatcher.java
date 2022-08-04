package buun.exception;

import buun.exception.catching.ExceptionRessourceSupplier;
import buun.exception.catching.ExceptionResultSupplier;
import buun.exception.catching.ExceptionRunnable;
import buun.exception.catching.ExceptionSupplier;

public class ExceptionCatcher<T, E extends Exception, A extends AutoCloseable> {

    private final ExceptionRunnable<E> runnable;
    private final ExceptionSupplier<T, E> supplier;
    private final ExceptionRessourceSupplier<A, T, E> ressourceSupplier;

    private ExceptionCatcher(ExceptionRunnable<E> runnable, ExceptionSupplier<T, E> supplier, ExceptionRessourceSupplier<A, T, E> ressourceSupplier){
        this.runnable = runnable;
        this.supplier = supplier;
        this.ressourceSupplier = ressourceSupplier;
    }

    public ExceptionCatcher(ExceptionRunnable<E> runnable){
        this(runnable, null, null);
    }

    public ExceptionCatcher(ExceptionSupplier<T,E> supplier){
        this(null, supplier, null);
    }

    public ExceptionCatcher(ExceptionRessourceSupplier<A, T, E> ressourceSupplier){
        this(null,null, ressourceSupplier);
    }

    public T tryCatch(){
        return tryCatch(false);
    }

    public T tryCatch(boolean print){
        return tryCatch(exception -> {
            if(print) exception.printStackTrace();
            return null;
        });
    }

    public T tryCatch(ExceptionResultSupplier<T> resultSupplier){
        try{
            if(runnable != null) runnable.run();
            if(supplier != null) return supplier.get();
        }catch (Exception exception){
            return resultSupplier.get(exception);
        }
        return null;
    }

    public T tryCatchRessource(ExceptionSupplier<A, Exception> ressource, ExceptionResultSupplier<T> resultSupplier){
        try(A value = ressource.get()){
            if(ressourceSupplier != null) ressourceSupplier.get(value);
        }catch (Exception exception){
            return resultSupplier.get(exception);
        }
        return null;
    }

    public T tryCatchRessource(ExceptionSupplier<A, Exception> ressource, boolean print){
        return tryCatchRessource(ressource, exception -> {
            if(print) exception.printStackTrace();
            return null;
        });
    }

    public T tryCatchRessource(ExceptionSupplier<A, Exception> ressource){
        return tryCatchRessource(ressource, false);
    }

}
