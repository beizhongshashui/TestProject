package com.yuyoubang.listener;

public interface ComplaintClickListener {
    void sure(String type, String num, String because, String price);

    void notSure();
}
