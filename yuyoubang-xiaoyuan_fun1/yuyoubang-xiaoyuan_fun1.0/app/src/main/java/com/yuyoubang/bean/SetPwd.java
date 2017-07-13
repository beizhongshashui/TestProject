package com.yuyoubang.bean;

import java.io.Serializable;

/**
 * Created by xiaoyuan on 16/11/9.
 */
public class SetPwd implements Serializable {
    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    private int error_code;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    private boolean result;
}
