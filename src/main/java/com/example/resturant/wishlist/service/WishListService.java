package com.example.resturant.wishlist.service;


import com.example.resturant.naver.NaverClient;
import com.example.resturant.naver.dto.SearchImageReq;
import com.example.resturant.naver.dto.SearchImageRes;
import com.example.resturant.naver.dto.SearchLocalReq;
import com.example.resturant.naver.dto.SearchLocalRes;
import com.example.resturant.wishlist.WishListEntity;
import com.example.resturant.wishlist.dto.WishListDto;
import com.example.resturant.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;

    public WishListDto search(String query){

        //지역검색
        SearchLocalReq searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);

        SearchLocalRes searchLocalRes = naverClient.searchLocal(searchLocalReq);

        if(searchLocalRes.getTotal() >0){
            SearchLocalRes.SearchLocalItem localItem = searchLocalRes.getItems().stream().findFirst().get();

            String imageQuery = localItem.getTitle().replaceAll("<[^>]*>", "");
            SearchImageReq searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            //이미지 검색
            SearchImageRes searchImageRes = naverClient.searchImage(searchImageReq);

            if(searchImageRes.getTotal()>0){
                SearchImageRes.SearchImageItem imageItem = searchImageRes.getItems().stream().findFirst().get();
                //결과를 리턴
                WishListDto result = new WishListDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCaregory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());

                return result;

            }



        }

        return new WishListDto();

    }


    public WishListDto add(WishListDto wishListDto) {
        WishListEntity entity = dtoToEntity(wishListDto);
        WishListEntity saveEntity = wishListRepository.save(entity);
        return entityToDto(saveEntity);

    }

    public WishListEntity dtoToEntity(WishListDto wishListDto){
        WishListEntity entity = new WishListEntity();
        entity.setIndex(wishListDto.getIndex());
        entity.setTitle(wishListDto.getTitle());
        entity.setCategory(wishListDto.getCategory());
        entity.setAddress(wishListDto.getAddress());
        entity.setRoadAddress(wishListDto.getRoadAddress());
        entity.setHomePageLink(wishListDto.getHomePageLink());
        entity.setImageLink(wishListDto.getImageLink());
        entity.setVisit(wishListDto.isVisit());
        entity.setVisitCount(wishListDto.getVisitCount());
        entity.setLastVisitDate(wishListDto.getLastVisitDate());
        return entity;
    }

    public WishListDto entityToDto(WishListEntity wishListEntity){
        WishListDto dto = new WishListDto();
        dto.setIndex(wishListEntity.getIndex());
        dto.setTitle(wishListEntity.getTitle());
        dto.setCategory(wishListEntity.getCategory());
        dto.setAddress(wishListEntity.getAddress());
        dto.setRoadAddress(wishListEntity.getRoadAddress());
        dto.setHomePageLink(wishListEntity.getHomePageLink());
        dto.setImageLink(wishListEntity.getImageLink());
        dto.setVisit(wishListEntity.isVisit());
        dto.setVisitCount(wishListEntity.getVisitCount());
        dto.setLastVisitDate(wishListEntity.getLastVisitDate());
        return dto;
    }

    public List<WishListDto> findAll(){
        return wishListRepository.listAll()
                .stream()
                .map(it ->entityToDto(it))
                .collect(Collectors.toList());
    }

    public void delete(int index) {
        wishListRepository.deleteById(index);
    }

    public void  addVisit(int index){
        Optional<WishListEntity> wishItem = wishListRepository.findById(index);
        if(wishItem.isPresent()){
            WishListEntity item = wishItem.get();

            item.setVisit(true);
            item.setVisitCount(item.getVisitCount()+1);

        }
    }
}
