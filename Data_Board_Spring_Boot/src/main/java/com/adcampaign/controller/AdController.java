package com.adcampaign.controller;

import com.adcampaign.entity.AdAdvertisement; // 导入刚刚创建的实体类
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/ads") // 所有这个控制器下的接口路径都以 /api/ads 开头
public class AdController {

    // 模拟一些静态数据
    private List<AdAdvertisement> adList = Arrays.asList(
            new AdAdvertisement("2025-9-28",5000,10,50),
            new AdAdvertisement("2025-9-29",6000,15,60)
    );

    @GetMapping // 等价于 @GetMapping("")，访问路径是 /api/ads
    public List<AdAdvertisement> getAllAds() {
        return adList; // Spring Boot会自动将这个List序列化为JSON返回
    }

    // 新增POST接口
    @PostMapping
    public String createAd(@RequestBody AdAdvertisement newAd) {
        // @RequestBody 注解告诉Spring：“请把请求体里的JSON数据，转换成一个AdAdvertisement对象”

        // 模拟处理：打印接收到数据
        System.out.println("接收到新的广告数据!!!");
        System.out.println("日期: " + newAd.getDate());
        System.out.println("广告花费: " + newAd.getCost());
        System.out.println("线索提交个数: " + newAd.getLeadCount());
        System.out.println("私信消息数: " + newAd.getMessageCount());

        // 这里暂时不做实际存储，只是返回成功消息
        return "广告数据创建成功! " + newAd.getDate();
    }
}