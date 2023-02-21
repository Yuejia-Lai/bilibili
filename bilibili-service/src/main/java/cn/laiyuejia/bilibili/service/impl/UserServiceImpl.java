package cn.laiyuejia.bilibili.service.impl;

import cn.laiyuejia.bilibili.domain.*;
import cn.laiyuejia.bilibili.domain.constant.UserConstant;
import cn.laiyuejia.bilibili.domain.exception.ConditionException;
import cn.laiyuejia.bilibili.mapper.UserMapper;
import cn.laiyuejia.bilibili.service.*;
import cn.laiyuejia.bilibili.service.util.MD5Util;
import cn.laiyuejia.bilibili.service.util.RSAUtil;
import cn.laiyuejia.bilibili.service.util.TokenUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* @author laiyuejia
* @description 针对表【t_user(用户表)】的数据库操作Service实现
* @createDate 2023-01-22 21:44:21
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserCoinService userCoinService;


    @Override
    public void addUser(User user) {
        String phone = user.getPhone();
        if(StringUtils.isBlank(phone)){
            throw new ConditionException("手机号不能为空！");
        }
        User dbUser = this.getUserByPhone(phone);
        if(dbUser!=null){
            throw new ConditionException("该手机号已经注册！");
        }
        Date date = new Date();
        String salt = String.valueOf(date.getTime());
        String userPassword = user.getUserPassword();
        String rawPassword;
        try {
            rawPassword = RSAUtil.decrypt(userPassword);
        } catch (Exception e) {
            throw new ConditionException("数据解析异常！");
        }
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");
        user.setSalt(salt);
        user.setUserPassword(md5Password);
        boolean result = this.save(user);
        if(!result){
            throw new ConditionException("用户注册失败！");
        }
        //添加用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setNick(UserConstant.DEFAULT_NICK);
        userInfo.setBirth(UserConstant.DEFAULT_BIRTH);
        userInfo.setGender(UserConstant.GENDER_UNKNOW);
        userInfoService.save(userInfo);
        //添加用户默认权限角色
        userAuthService.addUserDefaultRole(user.getId());
        //添加用户硬币数量
        UserCoin userCoin = new UserCoin();
        userCoin.setUserId(user.getId());
        userCoin.setAmount(0L);
        userCoinService.save(userCoin);
    }

    public User getUserByPhone(String phone){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        return this.getOne(queryWrapper);
    }

    @Override
    public String login(User user) throws Exception{
        String phone = user.getPhone();
        if(StringUtils.isBlank(phone)){
            throw new ConditionException("手机号不能为空！");
        }
        User dbUser = this.getUserByPhone(phone);
        if(dbUser==null){
            throw  new ConditionException("当前用户不存在");
        }
        String userPassword = user.getUserPassword();
        String rawPassword;
        try {
            rawPassword = RSAUtil.decrypt(userPassword);
        } catch (Exception e) {
            throw new ConditionException("数据解析异常!");
        }
        String salt = dbUser.getSalt();
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");
        if(!dbUser.getUserPassword().equals(md5Password)){
            throw new ConditionException("密码错误！");
        }
        //生成用户令牌，返回
        TokenUtil tokenUtil = new TokenUtil();
        return TokenUtil.generateToken(dbUser.getId());
    }

    @Override
    public User getUserInfo(Long userId) {
        User user = this.getById(userId);
        QueryWrapper<UserInfo> queryWrapper  =new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        UserInfo userInfo = userInfoService.getOne(queryWrapper);
        user.setUserInfo(userInfo);
        return user;
    }

    @Override
    public void updateUserInfos(UserInfo userInfo) {
        UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id",userInfo.getUserId());
        userInfoService.update(userInfo,updateWrapper);
    }

    @Override
    public PageResult<UserInfo> pageListUserInfos(JSONObject params) {
        Integer page = params.getInteger("page");
        Integer size = params.getInteger("size");
        String nick = params.getString("nick");
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(nick!=null&&nick!="","nick",nick);
        queryWrapper.orderByDesc("id");
        int count = userInfoService.count(queryWrapper);
        List<UserInfo> list = new ArrayList<>();
        if(count>0){
            IPage<UserInfo> iPage = new Page<>((page-1)*size,size);
            iPage = userInfoService.page(iPage,queryWrapper);
            list = iPage.getRecords();
        }
        return new PageResult<>(count,list);
    }

    @Override
    public Map<String, Object> loginForDts(User user) throws Exception {
        String phone = user.getPhone();
        if(StringUtils.isBlank(phone)){
            throw new ConditionException("手机号不能为空！");
        }
        User dbUser = this.getUserByPhone(phone);
        if(dbUser==null){
            throw  new ConditionException("当前用户不存在");
        }
        String userPassword = user.getUserPassword();
        String rawPassword;
        try {
            rawPassword = RSAUtil.decrypt(userPassword);
        } catch (Exception e) {
            throw new ConditionException("数据解析异常!");
        }
        String salt = dbUser.getSalt();
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");
        if(!dbUser.getUserPassword().equals(md5Password)){
            throw new ConditionException("密码错误！");
        }
        Long userId = dbUser.getId();
        String accessToken = TokenUtil.generateToken(userId);
        //生成刷新token
        String refreshToken = TokenUtil.generateRefreshToken(userId);
        //保存refreshToken到数据库
        QueryWrapper<RefreshToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("refresh_token",refreshToken);
        refreshTokenService.remove(queryWrapper);
        RefreshToken refreshToken1 = new RefreshToken();
        refreshToken1.setUserId(userId);
        refreshToken1.setRefreshToken(refreshToken);
        refreshTokenService.save(refreshToken1);

        Map<String,Object> result = new HashMap<>();
        result.put("accessToken",accessToken);
        result.put("refreshToken",refreshToken);
        return result;
    }

    @Override
    public void logout(String refreshToken, Long userId) {
        QueryWrapper<RefreshToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("refresh_token",refreshToken);
        refreshTokenService.remove(queryWrapper);
    }

    @Override
    public String refreshAccessToken(String refreshToken) throws Exception {
        QueryWrapper<RefreshToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("refresh_token",refreshToken);
        RefreshToken refreshToken1 = refreshTokenService.getOne(queryWrapper);
        if(refreshToken1==null){
            throw new ConditionException("555","token过期！");
        }
        Long userId = refreshToken1.getUserId();
        String accessToken = TokenUtil.generateToken(userId);
        return accessToken;
    }
}




