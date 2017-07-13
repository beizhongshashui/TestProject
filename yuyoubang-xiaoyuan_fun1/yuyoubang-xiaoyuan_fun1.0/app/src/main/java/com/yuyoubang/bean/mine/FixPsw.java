package com.yuyoubang.bean.mine;

import java.io.Serializable;

/**
 * Created by hongchen on 16/12/1.
 */

public class FixPsw implements Serializable {

    /**
     * result : true
     * error_code : 0
     */

    private boolean result;
    private int error_code;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
