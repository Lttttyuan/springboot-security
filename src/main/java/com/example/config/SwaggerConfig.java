package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//告诉Spring容器，这个类是一个配置类
@Configuration
//启用Swagger2功能
@EnableSwagger2
//解决版本冲突
@EnableWebMvc
//设置Swagger在开发和测试环境下开启
//@Profile({"dev","test"})
public class SwaggerConfig implements WebMvcConfigurer {
    //    重写父类方法
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    //配置Swagger2相关的bean
    @Bean
    public Docket createRestApi1(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Lttttyuan1") //分组名称
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.controller"))  //controller包下所有API都交给Swagger2管理
                //.paths(PathSelectors.any()) //过滤什么路径
                .build();
    }

    @Bean
    public Docket createRestApi2(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Lttttyuan2"); //分组名称
    }

    //  此处主要是API文档页面显示信息
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("Lttttyuan", "https://gitee.com/Lttttyuan/springboot-RBAC-demo", "2466606867@qq.com");

        return new ApiInfoBuilder()
                .title("演示项目API") //标题
                .description("学习Swagger2的演示项目") //描述
                .version("1.0") //版本
                .termsOfServiceUrl("https://gitee.com/Lttttyuan") //组织地址
                .contact(contact) //作者信息
                .build();
    }
}
