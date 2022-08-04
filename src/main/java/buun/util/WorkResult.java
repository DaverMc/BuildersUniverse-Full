package buun.util;

import buun.lang.LangueKeys;
import buun.user.User;

public class WorkResult<T> {

    private final T value;
    private final LangueKeys message;
    private final boolean failed;
    private final Object[] arguments;

    public WorkResult(T value, boolean failed, LangueKeys message, Object...arguments){
        this.value = value;
        this.failed = failed;
        this.message = message;
        this.arguments = arguments;
    }

    public LangueKeys getMessage(){
        return this.message;
    }

    public boolean hasFailed(){
        return this.failed;
    }

    public T getValue(){
        return this.value;
    }

    public T getFeedBack(User user){
        if(this.failed) user.sendMessage(message, arguments);
        return (this.failed)? null : this.value;
    }

}
