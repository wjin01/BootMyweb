package com.coding404.myweb;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCode01 {
    /*
    @Autowired
    ProductMapper productMapper;

    @Test
    public void insertTest() {
        for(int i = 1; i <= 100; i++) {
            ProductVO vo = ProductVO.builder()
                    .prodEnddate("2024-10-15")
                    .prodWriter("admin")
                    .prodName("coding404" + i)
                    .prodPrice(i * 100)
                    .prodCount(i * 10)
                    .prodDiscount(i)
                    .prodPurchaseYn("N")
                    .prodContent("coding404" + i)
                    .prodComment("coding404" + i)
                    .build();
            productMapper.productInsert( vo );
        }
    }
    */





}
