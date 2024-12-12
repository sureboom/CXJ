package com.ls.checkin.controller;

import com.ls.checkin.vo.QueryEmpInfoResp;
import com.ls.checkin.vo.QueryEmpStateResp;
import com.ls.checkin.entity.Employer;
import com.ls.checkin.service.EmployerService;
import com.ls.checkin.util.PasswordUtil;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @ApiOperation("查询全部员工信息")
    @GetMapping("/queryAllEmployeeInfo")
    public List<QueryEmpInfoResp> queryAllEmployeeInfo(){
        return employerService.queryAllEmployeeInfo();
    }

    @ApiOperation("根据empId修改员工密码")
    @PostMapping("/updatePassword/{empId}/{password}")
    public boolean updatePassword(@PathVariable("empId") long empId, @PathVariable("password") String password) {
        System.out.println("empId: " + empId + " password: " + password);
      return  employerService.updatePassword(empId, password);
    }

    @ApiOperation("根据id查询员工信息")
    @GetMapping("/queryEmpInfoById/{empId}")
    public Employer queryEmpInfoById(@PathVariable("empId") long empId) {
        return employerService.queryEmpInfoById(empId);
    }

    @ApiOperation("查询全部经理信息")
    @GetMapping("/queryAllEmployerInfo")
    public List<QueryEmpInfoResp> queryAllEmployerInfo() {
        return employerService.queryAllEmployerInfo();
    }

    @ApiOperation("员工登录")
    @GetMapping("login/{account}/{password}")
    public Employer login(@PathVariable("account") String account,@PathVariable("password") String password) {
        return employerService.login(account , password);
    }


/* 
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginRequest) {
        String account = loginRequest.get("account");
        String inputPassword = loginRequest.get("password");

        Employer employer = employerService.findByAccount(account);
        if (employer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("账号不存在");
        }

        try {
            // 从数据库中获取盐
            String salt = employer.getSalt();
            // 使用输入密码和盐加密
            String hashedPassword = PasswordUtil.hashPassword(inputPassword, salt);

            // 校验密码
            if (hashedPassword.equals(employer.getPassword())) {
                return ResponseEntity.ok("登录成功");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务器错误");
        }
    }
*/


/* 
    @ApiOperation("新增员工信息")
    @PostMapping("/insertEmpInfo")
    public boolean insertEmpInfo(Employer employer) {
        return employerService.insertEmpInfo(employer);
    }
*/


    @ApiOperation("新增员工信息")
    @PostMapping("/insertEmpInfo")
    public ResponseEntity<String> insertEmpInfo(@RequestBody Employer employer) {
        try {
            // 生成盐
            String salt = PasswordUtil.generateSalt();
            employer.setSalt(salt);

            // 加密密码
            String hashedPassword = PasswordUtil.hashPassword(employer.getPassword(), salt);
            employer.setPassword(hashedPassword);

            boolean success = employerService.insertEmpInfo(employer);
            if (success) {
                return ResponseEntity.ok("新增员工成功");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("新增员工失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务器错误");
        }
    }



    @ApiOperation("查询公司全部人员的状态")
    @GetMapping("/queryEmpState")
    public List<QueryEmpStateResp> queryEmpState(){
        return employerService.queryEmpState();
    }

    @ApiOperation("查询当前用户的状态")
    @GetMapping("/queryEmpStateById/{empId}")
    public Integer queryEmpStateById(@PathVariable("empId") long empId) {
        return employerService.queryEmpStateById(empId);
    }

    

}
