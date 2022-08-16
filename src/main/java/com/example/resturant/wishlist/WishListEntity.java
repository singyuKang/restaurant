package com.example.resturant.wishlist;

import com.example.resturant.db.MemoryDbEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class WishListEntity extends MemoryDbEntity {
    private String title;     //음식명
    private String category;    //카테고리
    private String address;//주소
    private String roadAddress;//도로명
    private String homePageLink;//홈페이지 주소
    private String imageLink;//이미지 주소
    private boolean isVisit;//방문여부
    private int visitCount;//방문 카운트
    private LocalDateTime lastVisitDate; //방문일자

}
