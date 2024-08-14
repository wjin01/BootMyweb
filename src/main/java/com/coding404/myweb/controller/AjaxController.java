package com.coding404.myweb.controller;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AjaxController {

    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    //1단 카테고리
    @GetMapping("/getCategory")
    public ResponseEntity<List<CategoryVO>> getCategory() {
        ArrayList<CategoryVO> list = productService.getCategory();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    //2단, 3단 카테고리
    @GetMapping("/getCategoryChild/{groupId}/{categoryLv}/{categoryDetailLv}")
    public ResponseEntity<List<CategoryVO>> getCategoryChild(@PathVariable("groupId") String groupId,
                                                             @PathVariable("categoryLv") int categoryLv,
                                                             @PathVariable("categoryDetailLv") int categoryDetailLv) {
        CategoryVO vo = CategoryVO.builder()
                .groupId(groupId)
                .categoryLv(categoryLv)
                .categoryDetailLv(categoryDetailLv)
                .build();
        ArrayList<CategoryVO> list = productService.getCategoryChild(vo);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }



}
