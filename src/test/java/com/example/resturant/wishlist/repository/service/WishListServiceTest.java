package com.example.resturant.wishlist.repository.service;


import com.example.resturant.wishlist.dto.WishListDto;
import com.example.resturant.wishlist.service.WishListService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WishListServiceTest {
    @Autowired
    private WishListService wishListService;


    @Test
    public void searchTest(){
        WishListDto result = wishListService.search("갈비집");

        System.out.println(result);
        Assertions.assertNotNull(result);
    }

}
