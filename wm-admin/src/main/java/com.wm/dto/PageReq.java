package com.wm.dto;

import lombok.Data;

@Data
public class PageReq<T> {

    private Integer page;
    private Integer limit;

    private T form;


}
