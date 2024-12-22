package com.ls.checkin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ls.checkin.entity.EmpState;
import com.ls.checkin.entity.Employer;
import com.ls.checkin.entity.LeftVacation;
import com.ls.checkin.mapper.EmpStateMapper;
import com.ls.checkin.mapper.EmployerMapper;
import com.ls.checkin.mapper.LeftVacationMapper;
import com.ls.checkin.service.EmployerService;
import com.ls.checkin.util.PasswordUtil;
import com.ls.checkin.vo.QueryEmpInfoResp;
import com.ls.checkin.vo.QueryEmpStateResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    private EmployerMapper employerMapper;

    @Autowired
    private LeftVacationMapper leftVacationMapper;

    @Autowired
    private EmpStateMapper empStateMapper;


    /**
     * 查询全部员工信息 , 即role = 0
     * @return
     */
    @Override
    public List<QueryEmpInfoResp> queryAllEmployeeInfo() {
        QueryWrapper<Employer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role" , 0);
        List<Employer> employerList = employerMapper.selectList(queryWrapper);
        List<QueryEmpInfoResp> respList = new ArrayList<>();
        for(Employer employer : employerList){
            QueryEmpInfoResp resp = new QueryEmpInfoResp();
            resp.setEmpId(employer.getEmpId());
            resp.setAccount(employer.getAccount());
            resp.setName(employer.getName());
            resp.setGender(employer.getGender());
            resp.setPhone(employer.getPhone());
            resp.setRole(employer.getRole());

            respList.add(resp);
        }
        return respList;
    }

    /**
     * 根据id查询员工信息
     * @param empId
     * @return
     */
    @Override
    public Employer queryEmpInfoById(long empId) {
        return employerMapper.selectById(empId);
    }

    /**
     * 根据account查询员工信息
     * @param account
     * @return
     */
    @Override
    public Employer findByAccount(String account){
        
        QueryWrapper<Employer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account" , account);
        return employerMapper.selectOne(queryWrapper);
    }

    /**
     * 查询全部经理信息
     * @return
     */
    @Override
    public List<QueryEmpInfoResp> queryAllEmployerInfo() {
        QueryWrapper<Employer> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role" , 1, 2 , 3, 4, 5);
        List<Employer> employerList = employerMapper.selectList(queryWrapper);
        List<QueryEmpInfoResp> respList = new ArrayList<>();
        for(Employer employer : employerList){
            QueryEmpInfoResp resp = new QueryEmpInfoResp();
            resp.setEmpId(employer.getEmpId());
            resp.setAccount(employer.getAccount());
            resp.setName(employer.getName());
            resp.setGender(employer.getGender());
            resp.setPhone(employer.getPhone());
            resp.setRole(employer.getRole());
            resp.setAvatar(employer.getAvatar());

            respList.add(resp);
        }
        return respList;
    }

    /**
     * 登录，登录后返回该员工信息
     * @param account
     * @param password
     * @return
     */
    @Override
    public Employer login(String account, String password) {
        QueryWrapper<Employer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account" , account);
        List<Employer> employerList = employerMapper.selectList(queryWrapper);
        if(employerList.size() == 0) {
            System.out.println("没有该用户信息！");
            return null;
        }
        try{
            Employer employer = employerList.get(0);
            String storedPassword = employer.getPassword();
            String salt = employer.getSalt();
        
            // 校验输入密码是否正确
            if (PasswordUtil.verifyPassword(password, storedPassword, salt)) {
                return employer;
            } else {
                System.out.println("密码不正确！");
                return null;
            }
                }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 新增员工信息
     * @param employer
     * @return
     */
    @Override
    public boolean insertEmpInfo(Employer employer) {
        int insert = employerMapper.insert(employer);
        if(insert == 1){
            //需要在剩余假期表中初始化该员工的剩余假期表
            LeftVacation leftVacation = new LeftVacation();
            leftVacation.setLeftYear(7);
            leftVacation.setLeftHunJia(3);
            leftVacation.setLeftChanJian(3);
            leftVacation.setLeftChanJia(20);
            leftVacation.setLeftBuRu(30);
            leftVacation.setLeftPeiChan(5);
            leftVacation.setTotal(68);
            leftVacation.setEmpId(employer.getEmpId());
            leftVacationMapper.insert(leftVacation);

            //需要在员工状态表汇中初始化该员工的状态
            EmpState empState = new EmpState();
            empState.setEmpId(employer.getEmpId());
            empState.setState(0);
            empStateMapper.insert(empState);

            return true;
        }else{
            return false;
        }
    }

    /**
     * 查询公司全部人员的状态
     * @return
     */
    @Override
    public List<QueryEmpStateResp> queryEmpState() {
        List<EmpState> empStateList = empStateMapper.selectList(null);
        List<QueryEmpStateResp> respList = new ArrayList<>();
        for(EmpState empState : empStateList){
            QueryEmpStateResp resp = new QueryEmpStateResp();
            resp.setStateId(empState.getStateId());
            resp.setState(empState.getState());
            Employer employer = employerMapper.selectById(empState.getEmpId());
            resp.setAccount(employer.getAccount());
            resp.setName(employer.getName());
            respList.add(resp);

        }
        return respList;

    }

    /**
     * 查询当前用户的状态
     * @param empId
     * @return
     */
    @Override
    public Integer queryEmpStateById(long empId) {
        return empStateMapper.selectById(empId).getState();
    }

     /**
     * 更新用户密码
     *
     * @param empId    员工 ID
     * @param password 新密码
     * @return 是否更新成功
     */
        @Override
        public Boolean updatePassword(long empId, String password) {
            Employer employer = new Employer();
            employer.setEmpId(empId);

            try {
                String salt = PasswordUtil.generateSalt();
                String HashPassword = PasswordUtil.hashPassword(password, salt);
                employer.setSalt(salt);
                employer.setPassword(HashPassword); // 这里可以对密码进行加密
                int update = employerMapper.updateById(employer);
                return update == 1;
            } catch (Exception e) {
                // 处理异常，例如记录日志
                e.printStackTrace();
                // 可以选择抛出一个更具体的异常，或者返回false表示更新失败
                return false;
            }
        }

         /**
         * 更新员工信息（手机号、邮箱、头像）
         *
         * @param employer 更新后的员工对象
         * @return 是否更新成功
         */
        @Override
        public boolean updateInfo(Employer employer) {
            int updatedRows = employerMapper.updateById(employer);
            return updatedRows > 0;
        }

}
