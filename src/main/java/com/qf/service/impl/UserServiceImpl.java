package com.qf.service.impl;

import com.alibaba.fastjson.JSON;
import com.qf.config.RedisKeyConfig;
import com.qf.dao.UserDao;
import com.qf.dto.UserDto;
import com.qf.dto.UserLoginDto;
import com.qf.pojo.User;
import com.qf.service.UserService;
import com.qf.util.BeanCopyUtil;
import com.qf.util.EncryptUtil;
import com.qf.util.JedisCore;
import com.qf.util.TokenUtil;
import com.qf.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Value("${brandshop.aes.passkey}")
    private String key;
    @Autowired
    private JedisCore jedisCore;
    //校验手机号
    @Override
    public R checkPhone(String phone) {
        User user = userDao.selectUserByPhone(phone);
        if (user != null){
            return R.error("亲，此手机号存在");
        }
        return R.ok();
    }

    //注册
    @Transactional//开启事务 保证操作的一致性
    @Override
    public R register(UserDto userDto) {
        //判断用户输入的手机号是否已经被注册
        if (checkPhone(userDto.getUserPhone()).getCode()==200){
            String pass = userDto.getUserPass();
            //密文
            userDto.setUserPass(EncryptUtil.aesenc(key,pass));
//            System.out.println(userDto.getUserPass());
            User user = BeanCopyUtil.copyDto(User.class,userDto,userDto.getClass().getDeclaredFields());
            Integer register = userDao.Register(user);
            if (register>0){
                return R.ok();
            }
        }
        return R.error("亲，该此手机号已被注册");
    }

    /**
     * 唯一登录 一个账号只能同时在线一个
     * @param userLoginDto
     * @return
     */
    @Override
    public R login(UserLoginDto userLoginDto) {
        String userPhone = userLoginDto.getUserPhone();
        if (jedisCore.checkKey(RedisKeyConfig.PHONE_FREEZE+userPhone)){
            return R.error("亲，账号已被冻结，请"+jedisCore.ttl(RedisKeyConfig.PHONE_FREEZE+userPhone)+"" +
                    "之后重新登录");
        }else if (jedisCore.checkKey(RedisKeyConfig.PHONE_TOKEN+userPhone)){
            return R.error("亲，您已经登录");
        }else{
            //根据手机号先判断用户是否存在
            User user = userDao.selectUserByPhone(userPhone);
            System.out.println(user.toString());
            System.out.println(user.getUserPass());
//            boolean iserror = true;
            if (user!=null){
                //用户存在 校验密码
                System.out.println(EncryptUtil.aesdec(key,user.getUserPass()));
                //userLoginDto.getUserPass().equals(EncryptUtil.aesdec(key,user.getUserPass()))
                if (EncryptUtil.aesdec(key,user.getUserPass()).equals(userLoginDto.getUserPass())){
                    //成功 生成令牌
                    String token = TokenUtil.createToken(user.getUserId());
                    //存储到redis
                    jedisCore.set(RedisKeyConfig.PHONE_TOKEN+userPhone,token,RedisKeyConfig.TOKEN_TIME);
                    //存储用户信息到redis
                    jedisCore.set(RedisKeyConfig.TOKEN_USER+token, JSON.toJSONString(user),RedisKeyConfig.TOKEN_TIME);
//                    iserror = false;
                    return R.ok(token);
                }else {
                    //本次登录失败
                    //校验十分钟内登录失败的次数
                    if (jedisCore.keys(RedisKeyConfig.PHONE_ERROR+userPhone)==2){
                        //冻结账号
                        jedisCore.set(RedisKeyConfig.PHONE_FREEZE+userPhone,System.currentTimeMillis()+"",RedisKeyConfig.TOKEN_TIME);
                    }
                    //未达到错误上线 记录本次错误 ""代表这个key存的值为空字符串
                    jedisCore.set(RedisKeyConfig.PHONE_ERROR+userPhone+":"+System.currentTimeMillis(),"",RedisKeyConfig.PHONE_ERROR_TIME);
                }
            }
            return R.error("账号或密码错误");
        }

    }

    //修改密码
    @Override
    public R updatePass(String pass, String token) {
        if (jedisCore.checkKey(RedisKeyConfig.TOKEN_USER+token)){
            User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER+token),User.class);
            Integer integer = userDao.UpdateUserPass(user.getUserId(), EncryptUtil.aesenc(key,pass));
            if (integer>0){
                //删除令牌
                jedisCore.del(RedisKeyConfig.TOKEN_USER+token);
                jedisCore.del(RedisKeyConfig.PHONE_TOKEN+user.getUserPhone());
                return R.ok("修改成功，请重新登录");
            }
        }
        return R.error("密码修改失败");
    }

    //找回密码
    @Override
    public R findPass(UserLoginDto userLoginDto) {
       String phone = userLoginDto.getUserPhone();
        User user = userDao.selectUserByPhone(phone);
        if (user != null && !"".equals(user)){
            if (userDao.findPass(phone,EncryptUtil.aesenc(key,userLoginDto.getUserPass()))>0){
                return R.ok();
            }else {
                R.error("手机号不存在");
            }
        }
        return null;
    }



    @Override
    public R loginOut(String token) {
        if (jedisCore.checkKey(RedisKeyConfig.TOKEN_USER+token)){
            User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER+token),User.class);

            jedisCore.del(RedisKeyConfig.TOKEN_USER+token);
            jedisCore.del(RedisKeyConfig.PHONE_TOKEN+user.getUserPhone());
            return R.ok();
        }
        return R.error("请传递令牌");
    }


}
