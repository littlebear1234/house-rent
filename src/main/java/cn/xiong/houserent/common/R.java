package cn.xiong.houserent.common;

import lombok.Data;

// 统一返回结果类
@Data
public class R {
    /**
     * 状态码
     */
    private String code;
    /**
     * 信息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;

    public R() {
    }

    public R(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }



    public static R ok() {
        return new R(Constants.CODE_200, "操作成功");
    }

    public static R ok(Object data) {
        return new R(Constants.CODE_200, "操作成功", data);
    }

    public static R error() {
        return new R(Constants.CODE_500, "操作失败");
    }

    public static R error(String msg) {
        return new R(Constants.CODE_500, msg);
    }

    public static R error(String code, String msg) {
        return new R(code, msg);
    }

    public static R error(String code, String msg, Object data) {
        return new R(Constants.CODE_500, msg, data);
    }

}
