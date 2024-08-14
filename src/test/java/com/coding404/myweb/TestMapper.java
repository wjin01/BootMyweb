package com.coding404.myweb;

import com.coding404.myweb.command.MemoVO;
import com.coding404.myweb.command.UsersVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface TestMapper {

    //EX_MEMO 테이블 N
    //EX_USERS 테이블 1
    //N:1 조인 형식
    public ArrayList<MemoVO> joinOne();

    //1:N 조인 형식
    public UsersVO joinTwo();

}
