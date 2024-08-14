package com.coding404.myweb.product;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {
    //public int productInsert(ProductVO vo); //등록
    public int productInsert(ProductVO vo, List<MultipartFile> list); //등록

    public ArrayList<ProductVO> getList(String userId, Criteria cri); //목록
    public int getTotal(String userId, Criteria cri); //전체게시글 수

    public ProductVO getDetail(int prodId); //상세내역
    public int productUpdate(ProductVO vo); //수정
    public void productDelete(int prodId); //삭제

    //카테고리 1단계
    public ArrayList<CategoryVO> getCategory();
    //카테고리 2,3단계
    public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo);
}
