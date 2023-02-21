package cn.laiyuejia.bilibili.domain;

import lombok.Data;

import java.util.List;
@Data
public class PageResult <T> {

    private Integer total;

    private List<T> list;

    public PageResult(Integer total,List<T> list){
        this.total = total;
        this.list = list;
    }

}
