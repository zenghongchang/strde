package com.strde.common.exception;

import java.io.Serializable;

import com.strde.common.utils.Constant;

/**
 * 
 * <自定义异常.>
 * 
 * @author Old曾
 * @version [版本号, 2022年5月16日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SuppressWarnings("serial")
public class RunException extends RuntimeException implements Serializable {
    private int code = Constant.ERROR_CODE;    
    private String message = Constant.ERROR_MESSAGE;
    
    public RunException(int code) {
        super();
        this.code = code;
    }
    
    public RunException(int code, Throwable e) {
        super(e);
        this.code = code;
    }
    
    public RunException(String message) {
        super();
        this.message = message;
    }
    
    public RunException(String message, Throwable e) {
        super(e);
        this.message = message;
    }
    
    public RunException(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
    
    public RunException(int code, String message, Throwable e) {
        super(e);
        this.code = code;
        this.message = message;
    }
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
