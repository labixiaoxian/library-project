package com.wyu.controller;

import com.wyu.dao.CountryMapper;
import com.wyu.entity.Country;
import com.wyu.entity.User;
import com.wyu.entity.UserInfo;
import com.wyu.enums.Constant;
import com.wyu.enums.LoginStatusEnums;
import com.wyu.enums.UserStatusEnums;
import com.wyu.exception.UserException;
import com.wyu.service.user.UserInfoService;
import com.wyu.service.user.UserService;
import com.wyu.utils.DateFormateUtile;
import com.wyu.utils.HttpServletRequestUtil;
import com.wyu.utils.ImageUtil;
import com.wyu.utils.WriteBackUtil;
import com.wyu.vo.WriteBack;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XiaoXian on 2020/11/18.
 */
@Api(value = "用户模块", tags = { "用于用户的相关接口" })
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private CountryMapper countryMapper;

    /**
     * 用户登录
     *
     * @param requestMap
     * @return
     */
    @ApiOperation(notes = "用户登录", value = "用户登录")
    @PostMapping(value = "/login")
    public WriteBack login(@ApiParam(name = "json", value = "{" + "'username':'username',"
            + "'password':'password'}", required = true)@RequestBody Map<String, Object> requestMap) {
        WriteBack writeBack = new WriteBack();
        String username = (String) requestMap.get("username");
        String password = (String) requestMap.get("password");
        System.out.println(username);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> map = new HashMap<>();
        try {
            //登录成功
            subject.login(token);
            map.put("token", subject.getSession().getId());
            WriteBackUtil.setWriteBack(LoginStatusEnums.LOGIN_SUCCESS.getState(),
                    LoginStatusEnums.LOGIN_SUCCESS.getStateInfo(), map, writeBack);
        } catch (IncorrectCredentialsException e) {
            //密码错误
            WriteBackUtil.setWriteBack(LoginStatusEnums.PASSWORD_ERROR.getState(),
                    LoginStatusEnums.PASSWORD_ERROR.getStateInfo(), null, writeBack);
        } catch (UserException e) {
            //用户已注销
            WriteBackUtil.setWriteBack(LoginStatusEnums.USER_LOGOUT.getState(),
                    LoginStatusEnums.USER_LOGOUT.getStateInfo(), null, writeBack);
        } catch (AuthenticationException e) {
            //用户不存在
            WriteBackUtil.setWriteBack(LoginStatusEnums.NO_USER.getState(),
                    LoginStatusEnums.NO_USER.getStateInfo(), null, writeBack);
        } catch (Exception e) {
            //未知错误
            WriteBackUtil.setFail(writeBack);
        }
        return writeBack;
    }

    /**
     * 用户退出登录
     * @return
     */
    @ApiOperation(notes = "用户退出登录", value = "用户退出登录")
    @GetMapping("/logout")
    public WriteBack logout(){
        WriteBack writeBack = new WriteBack();
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            WriteBackUtil.setSuccess(writeBack);
        } catch (Exception e) {
            e.printStackTrace();
            WriteBackUtil.setFail(writeBack);
        }
        return writeBack;
    }

    /**
     * 用户没有权限
     *
     * @return
     */
    @ApiOperation(notes = "用户未认证授权", value = "用户未认证授权")
    @GetMapping(value = "/unauth")
    @ResponseBody
    public WriteBack unauth() {
        WriteBack writeBack = new WriteBack();
        WriteBackUtil.setWriteBack(LoginStatusEnums.NO_AUTH.getState(),
                LoginStatusEnums.NO_AUTH.getStateInfo(), null, writeBack);
        return writeBack;
    }


    /**
     * 密码修改
     *
     * @param requestMap
     * @return
     */
    @ApiOperation(notes = "修改密码", value = "修改密码")
    @PostMapping(value = "/modifypassword")
    public WriteBack modifyPassword(@ApiParam(name = "json", value = "{" + "'userId':1,"
            + "'oldPassword':'oldPassword' "+"'newPassword':'newPassword'}", required = true)@RequestBody Map<String, Object> requestMap) {
        WriteBack writeBack = new WriteBack();
        Integer userId = (Integer) requestMap.get("userId");
        String oldPassword = (String) requestMap.get("oldPassword");
        String newPassword = (String) requestMap.get("newPassword");

        try {
            int result = userService.modifyPassword(userId, oldPassword, newPassword);
            if (result == 0) {//数据为空
                WriteBackUtil.setWriteBack(UserStatusEnums.NULL_PARAMETER.getState(),
                        UserStatusEnums.NULL_PARAMETER.getStateInfo(), null, writeBack);
            } else if (result == -1) {//查询不到该用户
                WriteBackUtil.setWriteBack(UserStatusEnums.NO_USER.getState(),
                        UserStatusEnums.NO_USER.getStateInfo(), null, writeBack);
            } else if (result == -2) {//新密码与旧密码相同
                WriteBackUtil.setWriteBack(UserStatusEnums.OLD_EQUAL_NEW.getState(),
                        UserStatusEnums.OLD_EQUAL_NEW.getStateInfo(), null, writeBack);
            } else if (result == -3) {//旧密码不正确
                WriteBackUtil.setWriteBack(UserStatusEnums.ERROR_PASSWORD.getState(),
                        UserStatusEnums.ERROR_PASSWORD.getStateInfo(), null, writeBack);
            } else {//修改成功
                WriteBackUtil.setSuccess(writeBack);
            }
        } catch (Exception e) {
            e.printStackTrace();
            WriteBackUtil.setFail(writeBack);
        }
        return writeBack;
    }

    /**
     * 通过用户userId查询用户详情
     *
     * @param userId
     * @return
     */
    @ApiOperation(notes = "通过用户userId查询用户详情", value = "通过用户userId查询用户详情")
    @GetMapping(value = "/getuserbyuserid")
    public WriteBack getUserByUserId(@ApiParam(name = "userId", value = "用户ID", required = true)@RequestParam("userId") Integer userId) {
        WriteBack writeBack = new WriteBack();
        UserInfo userInfoByUserId = userInfoService.findUserInfoByUserId(userId);
        if (!ObjectUtils.isEmpty(userInfoByUserId)) {
            WriteBackUtil.setWriteBack(Constant.success.getCode(),
                    Constant.success.getMsg(), userInfoByUserId, writeBack);
        } else {
            WriteBackUtil.setFail(writeBack);
        }
        return writeBack;
    }

    /**
     * 通过用户userInfoId查询用户详情
     *
     * @param userInfoId
     * @return
     */
    @ApiOperation(notes = "通过用户userInfoId查询用户详情", value = "通过用户userInfoId查询用户详情")
    @GetMapping(value = "/getuserbyuserinfoid")
    public WriteBack getUserByuserInfoId(@ApiParam(name = "userInfoId", value = "用户详情ID", required = true)@RequestParam("userInfoId") Integer userInfoId) {
        WriteBack writeBack = new WriteBack();
        UserInfo userInfoById = userInfoService.findUserInfoById(userInfoId);
        if (!ObjectUtils.isEmpty(userInfoById)) {
            WriteBackUtil.setWriteBack(Constant.success.getCode(),
                    Constant.success.getMsg(), userInfoById, writeBack);
        } else {
            WriteBackUtil.setFail(writeBack);
        }
        return writeBack;

    }

    /**
     * 获取用户详情列表
     *
     * @param requestMap
     * @return
     */
    @ApiOperation(notes = "条件查询获取用户详情列表", value = "条件查询获取用户详情列表")
    @PostMapping(value = "/getuserinfolist")
    public WriteBack getUserInfoList(@ApiParam(name = "json", value = "{" + "'age':1," + "'sex':2" +"'birthday':'2020-11-12'"
            + "'nickname':'nickname' "+"'borrowCount':12" +"'credit':100"+"'currentPage':1"+"'status':1"+"'pageSize':5}", required = true)
                                         @RequestBody Map<String, Object> requestMap) {
        WriteBack writeBack = new WriteBack();
        Integer age = (Integer) requestMap.get("age");
        Integer sex = (Integer) requestMap.get("sex");
        String birthday = (String) requestMap.get("birthday");
        String nickname = (String) requestMap.get("nickname");
        Integer borrowCount = (Integer) requestMap.get("borrowCount");
        Integer credit = (Integer) requestMap.get("credit");
        Integer currentPage = (Integer) requestMap.get("currentPage");
        Integer pageSize = (Integer) requestMap.get("pageSize");
        Integer status = (Integer) requestMap.get("status");
        UserInfo userInfo = parseToUserInfo(age, sex, borrowCount, credit, birthday, nickname,status);

        List<UserInfo> userInfoList = null;
        try {
            userInfoList = userInfoService.findUserInfoList(userInfo, currentPage, pageSize);
            int count = userInfoService.queryUserInfoCount(userInfo);
            WriteBackUtil.setSuccess(writeBack);
            writeBack.setData(userInfoList);
            writeBack.setCount(count);
        } catch (Exception e) {
            e.printStackTrace();

            WriteBackUtil.setFail(writeBack);
        }
        return writeBack;
    }


    /**
     * 条件查询获取用户详情数量
     * @param requestMap
     * @return
     */
    @ApiOperation(notes = "条件查询获取用户详情数量", value = "条件查询获取用户详情数量")
    @GetMapping(value = "/getuserinfocount")
    public WriteBack getUserInfoCount(@ApiParam(name = "json", value = "{" + "'age':1," + "'sex':2" +"'birthday':'2020-11-12'"
            + "'nickname':'nickname' "+"'borrowCount':12"+"'status':1" +"'credit':100}", required = true)
                                          @RequestBody Map<String, Object> requestMap){
        WriteBack writeBack = new WriteBack();
        Integer age = (Integer) requestMap.get("age");
        Integer sex = (Integer) requestMap.get("sex");
        String birthday = (String) requestMap.get("birthday");
        String nickname = (String) requestMap.get("nickname");
        Integer borrowCount = (Integer) requestMap.get("borrowCount");
        Integer credit = (Integer) requestMap.get("credit");
        Integer status = (Integer) requestMap.get("status");
        UserInfo userInfo = parseToUserInfo(age, sex, borrowCount, credit, birthday, nickname,status);
        try {
            int count = userInfoService.queryUserInfoCount(userInfo);
            WriteBackUtil.setSuccess(writeBack);
            writeBack.setData(count);
        } catch (Exception e) {
            e.printStackTrace();
            WriteBackUtil.setFail(writeBack);
        }
        return writeBack;
    }

    /**
     * 用户注册
     * @param requestMap
     * @return
     */
    @ApiOperation(notes = "用户注册", value = "用户注册")
    @PostMapping(value = "/register")
    public WriteBack register(@ApiParam(name = "json", value = "{" + "'userName':'userName',"
            + "'nickname':'nickname' "+"'email':'email'" +"'password':'password'"+"'twicePassword':'twicePassword'}", required = true)
            @RequestBody Map<String, Object> requestMap) {
        //获取数据
        WriteBack writeBack = new WriteBack();
        String userName = (String) requestMap.get("userName");
        String nickname = (String) requestMap.get("nickname");
        String email = (String) requestMap.get("email");
        String password = (String) requestMap.get("password");
        String twicePassword = (String) requestMap.get("twicePassword");

        //注册
        try {
            int register = userService.register(userName, nickname, email, password, twicePassword);
            if (register==0){
                WriteBackUtil.setWriteBack(UserStatusEnums.NULL_PARAMETER.getState(),
                        UserStatusEnums.NULL_PARAMETER.getStateInfo(), null, writeBack);
            }
            else if(register == -1){
                WriteBackUtil.setWriteBack(UserStatusEnums.HASH_REGISTER.getState(),
                        UserStatusEnums.HASH_REGISTER.getStateInfo(), null, writeBack);
            }
            else if(register == -2){
                WriteBackUtil.setWriteBack(UserStatusEnums.DIFFERENT_PASSWORD.getState(),
                        UserStatusEnums.DIFFERENT_PASSWORD.getStateInfo(), null, writeBack);
            }else {
                WriteBackUtil.setSuccess(writeBack);
            }
        } catch (Exception e) {
            e.printStackTrace();
            WriteBackUtil.setFail(writeBack);
        }
        return writeBack;
    }


    /**
     * 用户激活
     * @param userId
     * @return
     */
    @ApiOperation(notes = "用户激活", value = "用户激活")
    @GetMapping(value = "/active/{userId}")
    public WriteBack active(@ApiParam(name = "userId", value = "用户ID", required = true)@PathVariable("userId")Integer userId){
        WriteBack writeBack = new WriteBack();
        int result = userService.activeUser(userId);
        if (result == 0){
            WriteBackUtil.setWriteBack(UserStatusEnums.ACTIVE_FAIL.getState(),
                    UserStatusEnums.ACTIVE_FAIL.getStateInfo(), null, writeBack);
        }else {
            WriteBackUtil.setSuccess(writeBack);
        }
        return writeBack;
    }


    /**
     * 用户注销
     * @param userId
     * @return
     */
    @ApiOperation(notes = "用户注销", value = "用户注销")
    @GetMapping(value = "/usercancellation")
    public WriteBack userCancellation(@ApiParam(name = "userId", value = "用户ID", required = true)@RequestParam("userId")Integer userId){
        WriteBack writeBack = new WriteBack();
        int result = userService.cancellation(userId);
        if (result == 0){
            WriteBackUtil.setWriteBack(UserStatusEnums.CANCEL_FAIL.getState(),
                    UserStatusEnums.CANCEL_FAIL.getStateInfo(), null, writeBack);
        }
        else if (result == -1){
            WriteBackUtil.setWriteBack(UserStatusEnums.CAN_NOT_CANCEL.getState(),
                    UserStatusEnums.CAN_NOT_CANCEL.getStateInfo(), null, writeBack);
        }else if (result == -2){
            WriteBackUtil.setWriteBack(UserStatusEnums.NO_USER.getState(),
                    UserStatusEnums.NO_USER.getStateInfo(), null, writeBack);
        }else {
            WriteBackUtil.setSuccess(writeBack);
        }
        return writeBack;
    }

    /**
     * 更新用户信息
     * @param file
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(notes = "更新用户信息", value = "更新用户信息")
    @PutMapping(value = "/updateuser")
    public WriteBack updateUser(@ApiParam(name = "file", value = "用户上传图片", required = true)@RequestParam MultipartFile file, HttpServletRequest request,
                                HttpServletResponse response){
        WriteBack writeBack = new WriteBack();

        int id = HttpServletRequestUtil.getInt(request,"id");
        int age = HttpServletRequestUtil.getInt(request, "age");
        int sex = HttpServletRequestUtil.getInt(request, "sex");
        String birthday = HttpServletRequestUtil.getString(request, "birthday");
        String nickname = HttpServletRequestUtil.getString(request, "nickname");
        String email = HttpServletRequestUtil.getString(request, "email");
        String telephone = HttpServletRequestUtil.getString(request, "telephone");
        String address = HttpServletRequestUtil.getString(request, "address");
        String personalDesc = HttpServletRequestUtil.getString(request, "personalDesc");
        UserInfo userInfo = parseToUserInfo2(id, age, sex, birthday, nickname, email, telephone, address, personalDesc);


        //上传图片
        if(file != null){
            try {
                String path = ImageUtil.uploadPath(file.getOriginalFilename());
                File storeImage = new File(path);
                if (!storeImage.exists()) {
                    storeImage.mkdirs();
                }
                file.transferTo(storeImage);
                userInfo.setPicture(path);
            } catch (IOException e) {
                e.printStackTrace();
                WriteBackUtil.setFail(writeBack);
                return writeBack;
            }
        }

        int result = userInfoService.updateUserInfo(userInfo);
        if (result==0){
            WriteBackUtil.setFail(writeBack);
        }else {
            WriteBackUtil.setSuccess(writeBack);
        }

        return writeBack;
    }



//    @GetMapping("/caluser")
//    public WriteBack calUser(){
//
//    }




    //@RequiresRoles(value = "admin")
    @GetMapping(value = "/test")
    public String test() {
        for (int i = 0;i<100;i++){
            Country country = new Country();
            country.setCountryName("图书");
            try {
                countryMapper.newCountry(country);
            } catch (Exception e) {
            }
            System.out.println(i);
        }
        return "error";
    }


    private UserInfo parseToUserInfo(Integer age, Integer sex, Integer borrowCount, Integer credit,
                                     String birthday, String nickname,Integer status) {
        UserInfo userInfo = new UserInfo();
        User user = new User();
        user.setCredit(credit);
        user.setStatus(status);
        user.setBorrowCount(borrowCount);
        userInfo.setAge(age);
        userInfo.setSex(sex);
        userInfo.setNickname(nickname);
        userInfo.setBirthday(DateFormateUtile.stampToString(birthday));
        userInfo.setUser(user);
        return userInfo;
    }

//    int id = HttpServletRequestUtil.getInt(request,"id");
//    int age = HttpServletRequestUtil.getInt(request, "age");
//    int sex = HttpServletRequestUtil.getInt(request, "sex");
//    String birthday = HttpServletRequestUtil.getString(request, "birthday");
//    String nickname = HttpServletRequestUtil.getString(request, "nickname");
//    String email = HttpServletRequestUtil.getString(request, "email");
//    String telephone = HttpServletRequestUtil.getString(request, "telephone");
//    String address = HttpServletRequestUtil.getString(request, "address");
//    String personalDesc = HttpServletRequestUtil.getString(request, "personalDesc");

    private UserInfo parseToUserInfo2(Integer id,Integer age, Integer sex,String birthday, String nickname,
                                      String email,String telephone,String address,String personalDesc) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setAge(age);
        userInfo.setSex(sex);
        userInfo.setNickname(nickname);
        userInfo.setBirthday(DateFormateUtile.stampToString(birthday));
        userInfo.setTelephone(telephone);
        userInfo.setEmail(email);
        userInfo.setAddress(address);
        userInfo.setPersonalDesc(personalDesc);
        return userInfo;
    }
}
