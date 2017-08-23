package endless.Exception;

public class DrawingException extends RuntimeException {

    public DrawingException(String msg){
        super(msg);
    }

    @Override
    public String getLocalizedMessage() {
        return "渲染出现错误: "+super.getLocalizedMessage();
    }
}
