package com.example.net_file.mapper;

import com.example.net_file.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shyheng
 * @since 2022-05-26
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
