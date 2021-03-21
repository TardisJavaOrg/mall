package hx.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"hx.mall.mbg.mapper", "hx.mall.dao"})
public class MyBatisConfig {

}