package com.example.mybatispluslearn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatispluslearn.pojo.UserDO;
import org.springframework.stereotype.Repository;

//在对应的mapper上边继承BaseMapper，然后所有的CRUD就已经操作完成了
@Repository //@Mapper也可以
public interface UserMapper extends BaseMapper<UserDO> {
}
