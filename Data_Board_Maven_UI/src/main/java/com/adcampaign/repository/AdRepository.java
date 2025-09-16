package com.adcampaign.repository;

import com.adcampaign.entity.AdAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 可加可不加，Spring会自动识别
// 继承JpaRepository<实体类类型, 主键类型>
public interface AdRepository extends JpaRepository<AdAdvertisement, Long> {
    // 不需要写任何方法！
    // 你已经免费获得了：save(), findAll(), findById(), deleteById(), count() 等所有常用方法。
}
