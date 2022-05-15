package com.slipper.service.modules.system.token.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slipper.service.modules.system.token.entity.TokenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TokenDao extends BaseMapper<TokenEntity> {
    
    TokenEntity queryByToken(@Param("token")
    String token);
    
    /**
     * 
     * <>
     * @param adminId
     * @return
     * @author  Old曾
     * @version  [版本号, 2022年5月15日]
     * @see [类、类#方法、类#成员]
     */
    TokenEntity queryByAdminId(@Param("adminId")
    Long adminId);
    
}
