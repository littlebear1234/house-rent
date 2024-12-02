package cn.xiong.houserent.mapper;

import cn.xiong.houserent.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author xiong
* @description 针对表【users(存储前台用户和后台管理员的信息)】的数据库操作Mapper
* @createDate 2024-12-03 00:44:35
* @Entity cn.xiong.houserent.entity.User
*/

public interface UserMapper extends BaseMapper<User> {



    @Select("select * from users where username = #{username}")
    User selectByUsername(String username);

}




