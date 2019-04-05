package miniBank.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import miniBank.MiniBankApiApp;
import miniBank.dao.UserRepository;
import miniBank.model.Balance;
import miniBank.model.User;
import javax.servlet.Filter;
import miniBank.security.JwtProvider;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class TestAuthController {

	 @Autowired
	    private WebApplicationContext context;

	    @Autowired
	    private Filter springSecurityFilterChain;

	    private MockMvc mvc;

	    @Before
	    public void setup() {
	        mvc = MockMvcBuilders
	                .webAppContextSetup(context)
	                .addFilters(springSecurityFilterChain)
	                .build();
	    }

	    @Test
	    public void requiresAuthentication() throws Exception {
	        mvc
	            .perform(get("/"))
	            .andExpect(status().isMovedTemporarily());
	    }

	    @Test
	    public void httpBasicAuthenticationSuccess() throws Exception {
	        mvc
	            .perform(get("/secured/butnotfound").with(httpBasic("user","password")))
	            .andExpect(status().isNotFound())
	            .andExpect(authenticated().withUsername("user"));
	    }

	    @Test
	    public void authenticationSuccess() throws Exception {
	        mvc
	            .perform(formLogin())
	            .andExpect(status().isMovedTemporarily())
	            .andExpect(redirectedUrl("/"))
	            .andExpect(authenticated().withUsername("user"));
	    }

	    @Test
	    public void authenticationFailed() throws Exception {
	        mvc
	            .perform(formLogin().user("user").password("invalid"))
	            .andExpect(status().isMovedTemporarily())
	            .andExpect(redirectedUrl("/login?error"))
	            .andExpect(unauthenticated());
	    }

	    @Configuration
	    @EnableWebMvcSecurity
	    @EnableWebMvc
	    static class Config extends WebSecurityConfigurerAdapter {
	        @Autowired
	        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	            auth
	                .inMemoryAuthentication()
	                    .withUser("user").password("password").roles("USER");
	        }
	    }
	}