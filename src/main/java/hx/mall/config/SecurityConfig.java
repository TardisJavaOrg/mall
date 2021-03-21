package hx.mall.config;

import hx.mall.component.JwtAuthenticationTokenFilter;
import hx.mall.component.RestfulAccessDeniedHandler;
import hx.mall.component.RestAuthenticationEntryPoint;
import hx.mall.dto.AdminUserDetails;
import hx.mall.mbg.model.UmsAdmin;
import hx.mall.mbg.model.UmsPermission;
import hx.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import java.util.List;


/**
 * SpringSecurity 配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UmsAdminService adminService;

    // 当前用户没有访问权限的处理器，用于返回JSON格式的处理结果
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    // 档为登录或token失效时，返回JSON格式的结果
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;



    /**
     *  用于配置需要拦截的url路径、jwt过滤器及出现异常后的处理器
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf() // 使用的是 JWT 不需要csrf
                .disable()
                .sessionManagement() // 基于token,所以不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests() //
                .antMatchers(HttpMethod.GET, // 允许对于网站静态资源的无授权访问
                        "/",
                        "/*.html",
                        "/**/*.html",
                        "**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs"
                )
                .permitAll()
                .antMatchers("/admin/login", "/admin/register") // 登录注册允许匿名访问
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS) // 跨域请求会先进行一次options请求
                .permitAll()
                .antMatchers("/**") // 测试时 全部允许访问
                .permitAll()
                .anyRequest() // 除上面的所有请求全部需要健全认证
                .authenticated();

        // 禁用缓存
        http.headers().cacheControl();
        // 添加 JWT filter
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // 添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    /**
     * 用于配置UserDetailsService及PasswordEncoder
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(paaswordEncoder());
    }

    /**
     * SpringSecurity 定义的用于对密码进行编码及比对的接口，目前使用的是BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder paaswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 定义用于封装用户信息的类（主要是用户信息和权限）
      * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        // 获取登录用户信息
        return  username -> {
            UmsAdmin admin = adminService.getAdminByUsername(username);
            if (admin != null){
                List<UmsPermission> permissionList = adminService.getPermissionList(admin.getId());
                return new AdminUserDetails(admin,permissionList);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    /**
     * 返回一个JWT的过滤器
     * 在用户名和密码校验前添加过滤器，如果有jwt的token，会自行根据token信息进行登录
     * @return
     */
    @Bean
    public Filter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    /**
     * 返回一个鉴权管理对象
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }
}
