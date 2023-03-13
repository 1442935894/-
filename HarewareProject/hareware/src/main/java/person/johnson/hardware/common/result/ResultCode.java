package person.johnson.hardware.common.result;

public enum ResultCode {
    // 公共请求信息
    SUCCESS(200, "请求成功"),
    FAIL(500, "请求失败，请联系管理员"),
    PARAMETER_MISSING(400,"参数缺失"),

    // 用户：50000100 - 50000199
    USER_LOGIN_UNAUTH_ERROR(50000100, "登录权限失败"),
    USER_LOGIN_ERROR(50000101, "登录失败，用户名或密码出错，请重新输入"),
    USER_UNREGISTER(50000110,"用户已注册");

    private Integer code;

    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getMsg(String name) {
        for (person.johnson.hardware.common.result.ResultCode item : person.johnson.hardware.common.result.ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.msg;
            }
        }
        return null;
    }

    public static Integer getCode(String name) {
        for (person.johnson.hardware.common.result.ResultCode item : person.johnson.hardware.common.result.ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ResultCode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
