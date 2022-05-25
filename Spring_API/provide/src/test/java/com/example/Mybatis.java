package com.example;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class Mybatis {
    public static void main(String[] args) {

        FastAutoGenerator.create("jdbc:mysql://124.221.224.125:3306/xs?serverTimezone=GMT%2B8&useSSL=true",
                "root",
                "123456")
                .globalConfig(builder -> {
                    builder.author("shyheng") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E:\\NetDisk\\Spring_API\\provide\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example") // 设置父包名
                            .moduleName("user");
                })
                .strategyConfig(builder -> {
                    builder.addInclude("xs");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
