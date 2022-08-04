package buun.lang;

public class MessageProcessor {

    private String message;

    public MessageProcessor(String rawMessage){
        this.message = rawMessage;
    }

    public MessageProcessor setArguments(Object...args){
        for(int i = 0; i < args.length; i++) message = message.replaceAll("<arg" + i + ">", args.toString());
        return this;
    }

    public MessageProcessor replacePaths(){return replacePaths('$');}

    public MessageProcessor replacePaths(char bracketChar){
        return this;
    }

    public MessageProcessor replaceColor(){return replaceColor('&', '§');}

    public MessageProcessor replaceColor(char colorSymbol, char replacerChar){
        message = message.replace(colorSymbol, replacerChar);
        return this;
    }

    public MessageProcessor replaceParagraphs(){return replaceParagraphs("/n");}

    public MessageProcessor replaceParagraphs(String paragraphSymbol){
        message = message.replaceAll(paragraphSymbol, "\n");
        return this;
    }

    public String getMessage(){
        return this.message;
    }

}
