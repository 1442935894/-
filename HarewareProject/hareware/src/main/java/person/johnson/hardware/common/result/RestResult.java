package person.johnson.hardware.common.result;

import com.github.pagehelper.PageInfo;

public class RestResult<T> {
    private int code;
    private String msg;
    private Integer count;
    private long totalPage;//总页数
    private long pageSize;//页面大小
    private long currentPage;//当前页
    private T data;// 数据

    public void setPage(Integer count, PageInfo pageInfo) {
        this.count = count;
        this.pageSize = pageInfo.getPageSize();
        this.currentPage = pageInfo.getPageNum();
        this.totalPage = (long) (count / pageSize) + 1;
    }

    public static <T> RestResult<T> failure() {
        RestResult<T> result = new RestResult<T>();
        result.setResultCode(ResultCode.FAIL);
        return result;
    }

    public static <T> RestResult<T> failure(T data) {
        RestResult<T> result = new RestResult<T>();
        result.setResultCode(ResultCode.FAIL);
        result.setData(data);
        return result;
    }

    public static <T> RestResult<T> failure(ResultCode resultCode) {
        RestResult<T> result = new RestResult<T>();
        result.setResultCode(resultCode);
        return result;
    }

    public static <T> RestResult<T> failure(ResultCode resultCode, T data) {
        RestResult<T> result = new RestResult<T>();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public static <T> RestResult<T> success() {
        RestResult<T> result = new RestResult<T>();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static <T> RestResult<T> success(T data) {
        RestResult<T> result = new RestResult<T>();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }
    public static <T> RestResult<T> success(T data, Integer count) {
        RestResult<T> result = new RestResult<T>();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        result.setCount(count);
        return result;
    }


    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
