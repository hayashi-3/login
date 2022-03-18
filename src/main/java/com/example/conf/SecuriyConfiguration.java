package com.example.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.auth.DetabaseUserDetailsService;

@EnableWebSecurity
public class SecuriyConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DetabaseUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	// ざっくり：webアプリケーションが管理しているリソースへのアクセス制御をするためのメソッドらしい
	// anyRequest()はログインしないとアプリを使えない。httpBasic()は認証方法。
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/**").permitAll().antMatchers("/registration").permitAll()
				.anyRequest().authenticated().and().formLogin().loginPage("/login").usernameParameter("email")
				.permitAll().and().logout().logoutUrl("/logout").invalidateHttpSession(true)
				.deleteCookies("JSESSIONID");
	}

	// Beanで依存性注入の管理対象
	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
