package com.smartdz.dzboss.controller;

import com.smartdz.dzboss.domain.BaseListMdl;
import com.smartdz.dzboss.domain.BaseResponse;
import com.smartdz.dzboss.domain.Code;
import com.smartdz.dzboss.domain.User;
import com.smartdz.dzboss.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    /**
     * 映射方法
     */
    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "user/index";
        // 访问 localhost/index 将直接跳转到 resources/templates/user/index.html 页面
    }

    @ResponseBody
    @RequestMapping("/getUserInfo.do")
    public BaseResponse<User> getUserInfo(@RequestParam("id") Long id) {
        BaseResponse<User> response = new BaseResponse<>();
        try {
            User user = userService.findById(id);
            response.setData(user);
        } catch (Exception e) {
            response.setResult(Code.ERROR);
            response.setMessage("系统内部错误");
        }
        return response;
    }

    @ResponseBody
    @RequestMapping("/loadUserListByPage.do")
    public BaseResponse<BaseListMdl<User>> loadUserListByPage(
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
        BaseResponse<BaseListMdl<User>> response = new BaseResponse<>();
        try {
            BaseListMdl<User> baseList = userService.findByPage(pageNum, pageSize);
            response.setResult(Code.SUCCESS);
            response.setMessage("分页加载成功");
            response.setData(baseList);
        } catch (Exception e) {
            response.setResult(Code.ERROR);
            response.setMessage("系统内部错误");
        }
        return response;
    }
}
