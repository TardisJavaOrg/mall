package hx.mall.mbg;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 从数据库中映射表
 */
public class Generator {
    public static void main(final String[] args) throws Exception{
        // MBG 执行过程中的警告信息
        final ArrayList<String> warnings = new ArrayList<>();
        final boolean overwrite = true;
        // 读取MBG配置文件
        InputStream input = Generator.class.getResourceAsStream("/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration  config = cp.parseConfiguration(input);
        input.close();

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        // 创建MBG
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        // 输出警告信息
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
}