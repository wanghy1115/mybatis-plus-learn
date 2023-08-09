package com.example.mybatispluslearn.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatispluslearn.pojo.UserDO;
import com.example.mybatispluslearn.mapper.UserMapper;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class SampleTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        //查询全部用户
        //参数是wrapper，条件构造器
        List<UserDO> userList = userMapper.selectList(null);
        Assert.isTrue(5 == userList.size(), "");
        userList.forEach(System.out::println);
    }
    @Test
    public void testInsert(){
        val userDO = new UserDO();
        userDO.setAge(13);
        userDO.setName("gy");
        userDO.setEmail("why1115@88.com");
        //会自动生成id，是一个long类型
        int insert = userMapper.insert(userDO);
        System.out.println(insert);

    }
    @Test
    public void testUpdate(){
        UserDO userDO = new UserDO();
        userDO.setId(5L);
        userDO.setName("Jim");
        userDO.setAge(100);
        userMapper.updateById(userDO);
    }
    //单线程成功
    @Test
    public void testOptimisticLocker1(){
        //查询用户
        UserDO userDO = userMapper.selectById(1L);
        //修改用户
        userDO.setName("dd");
        //执行更新操作
        userMapper.updateById(userDO);
    }
    //多线程失败
    @Test
    public void testOptimisticLocker2(){
        //线程1
        UserDO userDO1 = userMapper.selectById(1L);
        userDO1.setName("dd1");
        //模拟另一个线程插队操作
        UserDO userDO2 = userMapper.selectById(1L);
        userDO2.setName("dd2");
        userMapper.updateById(userDO2);
        //执行更新操作,此时下面的操作执行失败
        //使用自旋锁多次尝试
        userMapper.updateById(userDO1);
    }

    //测试批量查询
    @Test
    public void testSelectById(){
        UserDO userDO = userMapper.selectById(1);
        System.out.println(userDO);

        List<UserDO> userDOS = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(userDOS);
    }
    //测试添加查询map
    @Test
    public void testSelectByBatchIds(){
        HashMap<String, Object> map = new HashMap<>();
        //自定义查询
        map.put("name", "why");
        map.put("age", 13);
        List<UserDO> userDOS = userMapper.selectByMap(map);
        userDOS.forEach(System.out::println);
    }

    //测试分页查询
    @Test
    public void testPage(){
        //参数1:当前页
        //参数2:页面大小
        Page<UserDO> page = new Page<>(1,5);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
    }

    @Test
    public void testDelete(){
        userMapper.deleteById(2L);
    }
    @Test
    public void testDeleteBatchIds(){
        userMapper.deleteBatchIds(Arrays.asList(1688819046172160006L,1688819046172160005L,1688819046172160004L));
    }
    @Test
    public void testDeleteMap(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("name", "why");
        userMapper.deleteByMap(map);
    }

}
