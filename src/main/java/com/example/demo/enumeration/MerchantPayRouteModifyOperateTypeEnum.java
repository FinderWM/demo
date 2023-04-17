package com.example.demo.enumeration;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

/**
 * @date: 2023/3/30
 * @author: YL
 */
@Getter
public enum MerchantPayRouteModifyOperateTypeEnum {
    INSERT(1, "新增"),
    UPDATE(2, "修改"),
    DISABLE(3, "失效"),
    
    ;

    private int type;
    
    private String desc;

    MerchantPayRouteModifyOperateTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static final Map<Integer, MerchantPayRouteModifyOperateTypeEnum> MAP_CACHE = new HashMap<>();

    private volatile static boolean cached = false;

    public static Map<Integer, MerchantPayRouteModifyOperateTypeEnum> valuesMap() {
        if (!cached) {
            synchronized (MerchantPayRouteModifyOperateTypeEnum.class) {
                if (!cached || MAP_CACHE.isEmpty()) {
                    for (MerchantPayRouteModifyOperateTypeEnum value : values()) {
                        MAP_CACHE.put(value.getType(), value);
                    }
                    cached = true;
                }
            }
        }

        return MAP_CACHE;
    }
}
