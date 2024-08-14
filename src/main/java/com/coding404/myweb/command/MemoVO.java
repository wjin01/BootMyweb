package com.coding404.myweb.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemoVO {
    private int mno;
    private String writer;
    private String memo;

    //N:1 로 조인할 컬럼
    private String name;
    private String pw;

}
