package com.adcampaign.controller;

import com.adcampaign.entity.AdAdvertisement;
import com.adcampaign.repository.AdRepository; // 导入Repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class AdController {

    // 注入Repository（Spring会自动帮你实例化）
    @Autowired
    private AdRepository adRepository;

    @GetMapping
    public List<AdAdvertisement> getAllAds() {
        // 查询所有数据
        return adRepository.findAll(); // 免费的方法！
    }

    @PostMapping
    public AdAdvertisement createAd(@RequestBody AdAdvertisement newAd) {
        // 保存数据到数据库
        AdAdvertisement savedAd = adRepository.save(newAd); // 免费的方法！
        System.out.println("数据已保存，日期为: " + savedAd.getId());
        return savedAd; // 通常返回保存后的对象，它包含了自动生成的主键ID
    }
}