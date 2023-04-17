package com.example.demo.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @date: 2022/3/8
 */
@Data
public class MaterialDto {

    private Integer adOrgId;
    private Integer adClientId;
    private String isactive;
    private Date created;
    private Integer createdby;
    private Date updated;
    private Integer updatedby;

    private Integer lyyMaterialId;

    private String name;
    private String description;

    private BigDecimal price;
    private Integer lyyDistributorId;
    private String imageUrl;
    private String cId;
    private Integer lyyMaterialTypeId;
    private String materialTypeName;
    private String sku;


    /**
     * 默认商品标识
     */
    private String defaultMaterialMark;

    /**
     * 售货机商品分类：关联商品时是否选中
     */
    private Boolean selected = false;

    private BigDecimal goodPrice;

    private BigDecimal retailPrice;

    private Integer gameCoins;

    /**
     * 计价方式
     */
    private Integer pricingMode;

    /**
     * 计件时单位重量
     */
    private Integer perWeight;

    /**
     * 类型  0-商家  1-通用商品库
     */
    private Integer ownerType;

    /**
     * 商品规格
     */
    private String materialStandards;

    /**
     * 价格限制开关
     */
    private String priceLimitSwitch;

    /**
     * 零售价区间最小值
     */
    private BigDecimal retailPriceMinimum;

    /**
     * 零售价区间最大值
     */
    private BigDecimal retailPriceMaximum;

    /**
     * 投币单价区间最小值
     */
    private BigDecimal gameCoinsMinimum;

    /**
     * 投币单价区间最大值
     */
    private BigDecimal gameCoinsMaximum;

    /**
     * 广告媒体素材id
     */
    private Integer lyyMediaAdsMaterialId;

    /**
     * 适用设备，1-传统SHJ，2-重感SHJ，3-动态SHJ
     */
    private String applicableEquipment;

    /**
     * 复制的商品ID
     */
    private Integer copyMaterialId;

    /**
     * 审核状态，0审核中，1可售，2审核失败
     */
    private String auditStatus;

    private Object commodityModelDetail;

    /**
     * 商品渠道：xiaomai小麦 meizhi美智 hibianli嗨便利 haha哈哈零兽
     */
    private String channel;
    /**
     * 商品审核失败原因
     */
    private String failReason;

    /**
     * 商品详情id
     */
    private Integer lyyMaterialDetailId;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品介绍
     */
    private String introduction;

    /**
     * 商品图片
     */
    private List<String> picList;

    /**
     * 商品图片（数据库存储格式）
     */
    private String pics;

    /**
     * 单位重量、kg为单位
     */
    private String perWeightStr;

    /**
     * 商品是否有上架
     */
    private Boolean isOnShelf;

    /**
     * 广告媒体素材名称
     */
    private String lyyMediaAdsMaterialName;

    /**
     * 应该是零售的字段
     */
    private Integer commonId;

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true);

        System.out.println(mapper.writeValueAsString(new MaterialDto()));
    }
}
