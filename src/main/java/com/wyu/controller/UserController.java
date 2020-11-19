package com.wyu.controller;

import com.wyu.entity.User;
import com.wyu.entity.UserInfo;
import com.wyu.enums.Constant;
import com.wyu.enums.LoginStatusEnums;
import com.wyu.enums.UserStatusEnums;
import com.wyu.exception.UserException;
import com.wyu.service.user.UserInfoService;
import com.wyu.service.user.UserService;
import com.wyu.utils.DateFormateUtile;
import com.wyu.utils.WriteBackUtil;
import com.wyu.vo.WriteBack;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XiaoXian on 2020/11/18.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户登录
     * @param requestMap
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public WriteBack login(@RequestBody Map<String,Object> requestMap){
        WriteBack writeBack = new WriteBack();
        String username = (String) requestMap.get("username");
        String password = (String) requestMap.get("password");
        System.out.println(username);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> map = new HashMap<>();
        try {
            //登录成功
            subject.login(token);
            map.put("token", subject.getSession().getId());
            WriteBackUtil.setWriteBack(LoginStatusEnums.LOGIN_SUCCESS.getState(),
                    LoginStatusEnums.LOGIN_SUCCESS.getStateInfo(),map,writeBack);
        } catch (IncorrectCredentialsException e) {
            //密码错误
            WriteBackUtil.setWriteBack(LoginStatusEnums.PASSWORD_ERROR.getState(),
                    LoginStatusEnums.PASSWORD_ERROR.getStateInfo(),null,writeBack);
        } catch (UserException e) {
            //用户已注销
            WriteBackUtil.setWriteBack(LoginStatusEnums.USER_LOGOUT.getState(),
                    LoginStatusEnums.USER_LOGOUT.getStateInfo(),null,writeBack);
        } catch (AuthenticationException e) {
            //用户不存在
            WriteBackUtil.setWriteBack(LoginStatusEnums.NO_USER.getState(),
                    LoginStatusEnums.NO_USER.getStateInfo(),null,writeBack);
        } catch (Exception e) {
            //未知错误
            WriteBackUtil.setFail(writeBack);
        }
        return writeBack;
    }

    /**
     * 用户没有权限
     * @return
     */
    @RequestMapping(value = "/unauth")
    @ResponseBody
    public WriteBack unauth() {
        WriteBack writeBack = new WriteBack();
        WriteBackUtil.setWriteBack(LoginStatusEnums.NO_AUTH.getState(),
                LoginStatusEnums.NO_AUTH.getStateInfo(),null,writeBack);
        return writeBack;
    }


    /**
     * 密码修改
     * @param requestMap
     * @return
     */
    @RequestMapping(value = "/modifypassword",method = RequestMethod.POST)
    public WriteBack modifyPassword(@RequestBody Map<String,Object> requestMap){
        WriteBack writeBack = new WriteBack();
        Integer userId = (Integer) requestMap.get("userId");
        String oldPassword = (String) requestMap.get("oldPassword");
        String newPassword = (String) requestMap.get("newPassword");

        try {
            int result = userService.modifyPassword(userId, oldPassword, newPassword);
            if (result == 0){//数据为空
                WriteBackUtil.setWriteBack(UserStatusEnums.NULL_PARAMETER.getState(),
                        UserStatusEnums.NULL_PARAMETER.getStateInfo(),null,writeBack);
            }
            else if(result == -1){//查询不到该用户
                WriteBackUtil.setWriteBack(UserStatusEnums.NO_USER.getState(),
                        UserStatusEnums.NO_USER.getStateInfo(),null,writeBack);
            }
            else if (result == -2){//新密码与旧密码相同
                WriteBackUtil.setWriteBack(UserStatusEnums.OLD_EQUAL_NEW.getState(),
                        UserStatusEnums.OLD_EQUAL_NEW.getStateInfo(),null,writeBack);
            }
            else if (result == -3){//旧密码不正确
                WriteBackUtil.setWriteBack(UserStatusEnums.ERROR_PASSWORD.getState(),
                        UserStatusEnums.ERROR_PASSWORD.getStateInfo(),null,writeBack);
            }else {//修改成功
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
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getuserbyuserid",method = RequestMethod.GET)
    public WriteBack getUserByUserId(@RequestParam("userId")Integer userId){
        WriteBack writeBack = new WriteBack();
        UserInfo userInfoByUserId = userInfoService.findUserInfoByUserId(userId);
        if (!ObjectUtils.isEmpty(userInfoByUserId)){
            WriteBackUtil.setWriteBack(Constant.success.getCode(),
                    Constant.success.getMsg(),userInfoByUserId,writeBack);
        }else {
            WriteBackUtil.setFail(writeBack);
        }
       return writeBack;
    }

    /**
     * 通过用户userInfoId查询用户详情
     * @param userInfoId
     * @return
     */
    @RequestMapping(value = "/getuserbyuserinfoid",method = RequestMethod.GET)
    public WriteBack getUserByuserInfoId(@RequestParam("userInfoId")Integer userInfoId){
        WriteBack writeBack = new WriteBack();
        UserInfo userInfoById = userInfoService.findUserInfoById(userInfoId);
        if (!ObjectUtils.isEmpty(userInfoById)){
            WriteBackUtil.setWriteBack(Constant.success.getCode(),
                    Constant.success.getMsg(),userInfoById,writeBack);
        }else {
            WriteBackUtil.setFail(writeBack);
        }
        return writeBack;

    }

    /**
     * 获取用户详情列表
     * @param requestMap
     * @return
     */
    @RequestMapping(value = "/getuserinfolist",method = RequestMethod.GET)
    public WriteBack getUserInfoList(@RequestBody Map<String,Object> requestMap){
        WriteBack writeBack = new WriteBack();
        Integer age = (Integer) requestMap.get("age");
        Integer sex = (Integer) requestMap.get("sex");
        String birthday = (String) requestMap.get("birthday");
        String nickname = (String) requestMap.get("nickname");
        Integer borrowCount = (Integer) requestMap.get("borrowCount");
        Integer credit = (Integer) requestMap.get("credit");
        Integer currentPage = (Integer) requestMap.get("currentPage");
        Integer pageSize = (Integer) requestMap.get("pageSize");
        UserInfo userInfo = parseToUserInfo(age, sex, borrowCount, credit, birthday, nickname);

        List<UserInfo> userInfoList = null;
        try {
            userInfoList = userInfoService.findUserInfoList(userInfo, currentPage, pageSize);
            WriteBackUtil.setSuccess(writeBack);
            writeBack.setData(userInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            WriteBackUtil.setFail(writeBack);
            WriteBackUtil.setFail(writeBack);
        }
        return writeBack;
    }


    @RequestMapping(value = "/test")
    public String test(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")){
            return "test";
        }
        return "error";
    }


    private UserInfo parseToUserInfo(Integer age,Integer sex,Integer borrowCount,Integer credit,
                                     String birthday,String nickname){
        UserInfo userInfo = new UserInfo();
        User user = new User();
        user.setCredit(credit);
        user.setBorrowCount(borrowCount);
        userInfo.setAge(age);
        userInfo.setSex(sex);
        userInfo.setNickname(nickname);
        userInfo.setBirthday(DateFormateUtile.stampToString(birthday));
        userInfo.setUser(user);
        return userInfo;
    }
}
