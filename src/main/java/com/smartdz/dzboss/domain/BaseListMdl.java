package com.smartdz.dzboss.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 列表模型
 *
 * @param <T> 列表中对象
 * @author Guangkuo
 */
public class BaseListMdl<T> implements Serializable {
    private int num;// 当前页号
    private int size;// 当前页面数据个数
    private long total;// 数据总数
    private List<T> list;

    public BaseListMdl() {
    }

    public BaseListMdl(int num, int size, long total, List<T> list) {
        this.num = num;
        this.size = size;
        this.total = total;
        this.list = null == list ? new ArrayList<>() : list;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSize() {
        return 0 != size ? size : list.size();
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
