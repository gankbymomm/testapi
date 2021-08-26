package vn.vnpay.exception;

public class CustomException extends Exception{

    public CustomException(Exception ex) {
        super(ex);
    }

    public CustomException(String code, Exception ex) {
        super(ex);
        this.code = code;
    }

    public CustomException(String code, String message) {
        super(message);
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

