package com.coding404.myweb.controller;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.ProductService;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/product")
public class ProductController {


    @Autowired
    @Qualifier("productService") //이름으로연결
    private ProductService productService;


    //목록
//    @GetMapping("/productList")
//    public String productList(Model model, Criteria cri) {
//
//        //현재 로그인되어 있는 사람 아이디가 admin이라고 가정하고
//        String userId = "admin";
//
//        ArrayList<ProductVO> list = productService.getList(userId, cri);
//        model.addAttribute("list", list);
//
//        //페이지VO
//        int total = productService.getTotal(userId); //전체게시글 수
//        PageVO pageVO = new PageVO(cri, total ); //페이지네이션
//        model.addAttribute("pageVO", pageVO);
//
//        return "product/productList";
//    }

    //step1. criteria같은 객체에 검색 키워드를 추가
    //step2. 목록sql, 전체게시글sql 동적쿼리로 변경
    //step3. 화면에서 사용자가 검색버튼을 누를때, 다시 page번호를 1번으로, amount를 유지
    //step4. 검색값의 유지 (criteria안에 있음)
    //step5. 페이지네이션을 누를때, 검색 키워드를 같이 넘겨주어야 함
    //step6. 100씩 보기버튼 처리
    @GetMapping("/productList")
    public String productList(Model model, Criteria cri) {

        //현재 로그인되어 있는 사람 아이디가 admin이라고 가정하고
        String userId = "admin";
        ArrayList<ProductVO> list = productService.getList(userId, cri);
        model.addAttribute("list", list);
        //페이지VO
        int total = productService.getTotal(userId, cri); //전체게시글 수
        PageVO pageVO = new PageVO(cri, total ); //페이지네이션
        model.addAttribute("pageVO", pageVO);

        return "product/productList";
    }


    //등록
    @GetMapping("/productReg")
    public String productReg() {
        return "product/productReg";
    }

    //상세
    @GetMapping("/productDetail")
    public String productDetail(@RequestParam("prodId") int prodId,
                                Model model) {

        ProductVO vo = productService.getDetail(prodId);
        model.addAttribute("vo", vo);

        return "product/productDetail";
    }

    //등록요청
    @PostMapping("/registForm")
    public String registForm(ProductVO vo,
                             @RequestParam("file") List<MultipartFile> files, //파일 업로드
                             RedirectAttributes ra ) {

        //파일이 빈 데이터로 넘어오는 것을 제거
        files = files.stream().filter(file -> file.isEmpty() == false).collect(Collectors.toList());
        //확장자 검사
        for(MultipartFile f : files) {
            String contentType = f.getContentType(); //파일의 컨텐츠 타입을 얻음
            if(contentType.contains("image") == false) {
                ra.addFlashAttribute("msg", "png, jpg, jpeg 형식만 등록 가능합니다");
                return "redirect:/product/productList";
            }
        }

        //서버측에서 유효성 검사 진행가능
        int result = productService.productInsert(vo, files);
        if(result == 1) {
            ra.addFlashAttribute("msg", "정상 등록되었습니다");
        } else {
            ra.addFlashAttribute("msg", "등록에 실패했습니다. 1577-1577 문의해 주세요.");
        }


        return "redirect:/product/productList"; //다시 목록을 태워서 나감(데이터를 들고)
    }

    //상품 수정기능 - 반드시 필요한값은 PK
    @PostMapping("/productUpdate")
    public String productUpdate(ProductVO vo,
                                RedirectAttributes ra) {

        //업데이트
        int result = productService.productUpdate(vo);
        if(result == 1) {
            ra.addFlashAttribute("msg", "수정 되었습니다");
        } else {
            ra.addFlashAttribute("msg", "수정에 실패했습니다");
        }


        return "redirect:/product/productDetail?prodId=" + vo.getProdId(); //상세화면은 id값을 필요로 함
    }

    //상품 삭제기능 -
    @PostMapping("/productDelete")
    public String productDelete(@RequestParam("prodId") int prodId) {

        productService.productDelete(prodId);

        return "redirect:/product/productList";
    }


}
