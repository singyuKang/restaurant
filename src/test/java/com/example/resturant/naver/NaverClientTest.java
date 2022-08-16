package com.example.resturant.naver;

import com.example.resturant.naver.dto.SearchImageReq;
import com.example.resturant.naver.dto.SearchImageRes;
import com.example.resturant.naver.dto.SearchLocalReq;
import com.example.resturant.naver.dto.SearchLocalRes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void localSearchTest(){
        SearchLocalReq search = new SearchLocalReq();
        search.setQuery("abc");

        SearchLocalRes result =  naverClient.searchLocal(search);
        System.out.println("result" + result);

    }
    @Test
    public void searchLocalTest(){
        SearchImageReq  search = new SearchImageReq();
        search.setQuery("갈비집");


        SearchImageRes result = naverClient.searchImage(search);
        System.out.println("result: " + result);


    }
}
