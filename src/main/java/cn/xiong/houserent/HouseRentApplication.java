package cn.xiong.houserent;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.xiong.houserent.mapper")
public class HouseRentApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouseRentApplication.class, args);
        System.out.println("----------------后台管理系统启动成功！----------------");
    }

}
