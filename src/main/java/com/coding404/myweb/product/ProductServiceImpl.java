package com.coding404.myweb.product;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("productService") //이름
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Value("${project.upload.path}") //application.properties에 있는 키값 받아옴
    private String uploadPath; //업로드 경로

    //폴더 생성 함수
    public String makeFolder() {
        String filepath = LocalDate.now().format( DateTimeFormatter.ofPattern("yyyyMM") );
        File file = new File( uploadPath + "/" + filepath);
        if(file.exists() == false) {
            file.mkdirs(); //폴더 생성
        }
        return filepath;
    }

    @Override
    @Transactional(rollbackFor = Exception.class) //이 메서드 안에서 예외가 나면 ROLLBACK 시킴
    //단 try catch 문장으로 예외 처리가 해걀 되면 트랜잭션 처리가 되지 않음
    public int productInsert(ProductVO vo, List<MultipartFile> files) {

        //1st - 상품 인서트
        int result = productMapper.productInsert(vo); //상품 insert
        //2nd - 파일업로드
        for(MultipartFile file : files) {
            String originName = file.getOriginalFilename(); //파일명
            String filename = originName.substring(  originName.lastIndexOf("\\") + 1);
            String filepath = makeFolder(); //폴더명
            String uuid = UUID.randomUUID().toString();
            String savePath = uploadPath + "/" + filepath + "/" + uuid + "_" + filename; //업로드경로

            try {
                File path = new File(savePath); //파일명을 포함한 경로
                file.transferTo( path ); //파일 업로드

                //fileName, filePath, uuid 이 값은 디비에 저장

            } catch (Exception e) {
                e.printStackTrace();
            }

            //3nd - 파일 인서트
            productMapper.uploadFile(
                    ProductUploadVO.builder()
                            .filename(filename)
                            .filepath(filepath)
                            .uuid(uuid)
                            .prodWriter(vo.getProdWriter())
                            .build()
            );
        }

        return result;
    }


    @Override
    public ArrayList<ProductVO> getList(String userId, Criteria cri) {
        return productMapper.getList(userId, cri);
    }

    @Override
    public int getTotal(String userId, Criteria cri) {
        return productMapper.getTotal(userId, cri);
    }

    @Override
    public ProductVO getDetail(int prodId) {
        return productMapper.getDetail(prodId);
    }

    @Override
    public int productUpdate(ProductVO vo) {
        return productMapper.productUpdate(vo);
    }

    @Override
    public void productDelete(int prodId) {
        productMapper.productDelete(prodId);
    }

    @Override
    public ArrayList<CategoryVO> getCategory() {
        return productMapper.getCategory();
    }

    @Override
    public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo) {
        return productMapper.getCategoryChild(vo);
    }
}
