package com.fitem.games.ui.live.bean;

/**
 * Created by Fitem on 2018/3/21.
 */

public class LiveBase<T> {
    public String msg;
    public T result;
    public String status;

    @Override
    public String toString() {
        return "LiveBase{" +
                "msg='" + msg + '\'' +
                ", result=" + result +
                ", status='" + status + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
