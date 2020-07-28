package com.qf.dao;

import com.qf.pojo.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface UserDao {

    //校验手机号
    User selectUserByPhone(String phone);
    //注册
    Integer Register(User user);
    //根据用户id修改密码
    Integer UpdateUserPass(@Param("userId") Integer userId, @Param("userPass") String pass);

    //找回密码
    Integer findPass(@Param("userPhone") String phone, @Param("userPass") String pass);
//    @Select("select user_id, user_name,user_phone,user_pass")
//    Integer login(UserDto userDto);

    //根据id查询单个用户
    User findUserById(Integer userId);

}
