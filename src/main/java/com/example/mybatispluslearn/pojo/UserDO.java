package com.example.mybatispluslearn.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "user")
public class UserDO {
    //对应数据库的主键（uuid、自增、雪花算法、redis）
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    //乐观锁注解
    @Version
    private Integer version;
    //逻辑删除
    @TableLogic
    @TableField(value = "is_deleted")
    private Integer deleted;
    //字段属性
    //字段添加时填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //字段添加或修改时填充
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
