package com.coding404.myweb.product;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface ProductMapper {
    public int productInsert(ProductVO vo);//등록

    public ArrayList<ProductVO> getList(@Param("userId") String userId, @Param("cri") Criteria cri); //목록 (매개변수가 2개 이상이면, 이름을 붙여줘야함)

    public int getTotal(@Param("userId") String userId, @Param("cri") Criteria cri); //전체게시글 수

    public ProductVO getDetail(int prodId); //상세내역

    public int productUpdate(ProductVO vo); //수정

    public void productDelete(int prodId); //삭제

    //카테고리 1단계
    public ArrayList<CategoryVO> getCategory();

    //카테고리 2,3단계
    public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo);

    //파일 데이터 인서트
    public void uploadFile(ProductUploadVO vo);
}
