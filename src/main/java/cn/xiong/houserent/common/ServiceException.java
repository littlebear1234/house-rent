package cn.xiong.houserent.common;

import lombok.Data;

/**
 * 自定义异常
 */
@Data
public class ServiceException extends RuntimeException {
    private final String code;

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }


}
