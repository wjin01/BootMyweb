package com.coding404.myweb.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductUploadVO {
    private int uploadNo; //pk
    private String filename; //실제파일명
    private String filepath; //폴더명
    private String uuid; //실제 파일명 앞에 붙은 랜덤값
    private LocalDateTime regdate; //등록일
    private int prodId; //fk
    private String prodWriter; //fk
}
