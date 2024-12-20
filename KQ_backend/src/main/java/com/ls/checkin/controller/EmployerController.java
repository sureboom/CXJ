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

import java.util.HashMap;
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
/* 
    @ApiOperation("员工登录")
    @GetMapping("login/{account}/{password}")
    public Employer login(@PathVariable("account") String account,@PathVariable("password") String password) {
        return employerService.login(account , password);
    }
*/


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginRequest) {
        String account = loginRequest.get("account");
        String inputPassword = loginRequest.get("password");

        Map<String, Object> response = new HashMap<>();
        try {
            Employer employer = employerService.login(account, inputPassword);

            if (employer == null) {
                response.put("status", "error");
                response.put("message", "账号或密码错误");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

            // 返回成功信息和用户数据
            response.put("status", "success");
            response.put("message", "登录成功");
            response.put("data", Map.of(
                "empId", employer.getEmpId(),
                "account", employer.getAccount(),
                "name", employer.getName(),
                "role", employer.getRole()
            ));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "error");
            response.put("message", "服务器错误");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



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

    @ApiOperation("修改员工信息")
    @PostMapping("/updateEmpInfo")
    public ResponseEntity<Map<String, Object>> updateEmpInfo(@RequestBody Employer employer) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean success = employerService.updateInfo(employer);
            if (success) {
                response.put("status", "success");
                response.put("message", "信息更新成功");
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "error");
                response.put("message", "信息更新失败");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "error");
            response.put("message", "服务器错误");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
