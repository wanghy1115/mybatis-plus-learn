package com.example.mybatispluslearn.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatispluslearn.mapper.UserMapper;
import com.example.mybatispluslearn.pojo.UserDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test1(){
        //查询姓名、邮箱不为null，而且年龄等于100的
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name");
        wrapper.ge("age",100);
        wrapper.isNotNull("email");
        userMapper.selectList(wrapper);//和map对比一下
    }
    @Test
    public void test2(){
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Tom");
        //查询一个结果，如果查询多个可能出现问题
        userMapper.selectOne(queryWrapper);
    }
    @Test
    public void test3(){
        //查询20-30的用户
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("age", 20, 30);
        //SQL中的between语句包括开始和结束都是闭区间
        Long num = userMapper.selectCount(queryWrapper);
        System.out.println(num);
    }
    @Test
    public void test4(){
        //模糊查询，名字中不包含m的,并且邮箱以t开头
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.notLike("name", "m");
        queryWrapper.likeRight("email", "t");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }
    @Test
    public void test5(){
        //先自查询，然后查询age==28的
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        //id在自查询中查出来
        queryWrapper.inSql("id", "select id from user where id < 4");
        queryWrapper.ge("age", 28);
        List<Object> objects = userMapper.selectObjs(queryWrapper);
        objects.forEach(System.out::println);
    }
    @Test
    public void test6(){
        //通过id降序排序
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<UserDO> objects = userMapper.selectList(queryWrapper);
        objects.forEach(System.out::println);
    }

}
