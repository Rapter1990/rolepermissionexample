package com.security.rolepermissionexample.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.rolepermissionexample.auth.config.TokenConfigurationParameter;
import com.security.rolepermissionexample.auth.model.Token;
import com.security.rolepermissionexample.auth.model.entity.PermissionEntity;
import com.security.rolepermissionexample.auth.model.entity.RoleEntity;
import com.security.rolepermissionexample.auth.model.entity.UserEntity;
import com.security.rolepermissionexample.auth.model.enums.TokenClaims;
import com.security.rolepermissionexample.builder.PermissionEntityBuilder;
import com.security.rolepermissionexample.builder.RoleEntityBuilder;
import com.security.rolepermissionexample.builder.UserEntityBuilder;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class AbstractRestControllerTest extends AbstractTestContainerConfiguration {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected Token mockAdminCreateToken;
    protected Token mockAdminGetToken;
    protected Token mockAdminUpdateToken;
    protected Token mockAdminDeleteToken;
    protected Token mockUserGetToken;

    @Mock
    private TokenConfigurationParameter tokenConfiguration;


    @BeforeEach
    public void initializeAuth() {

        this.tokenConfiguration = new TokenConfigurationParameter();

        // Permissions for Admin
        PermissionEntity createPermission = new PermissionEntityBuilder().withValidFields()
                .withName("admin:create").build();

        PermissionEntity getPermission = new PermissionEntityBuilder().withValidFields()
                .withName("admin:get").build();

        PermissionEntity updatePermission = new PermissionEntityBuilder().withValidFields()
                .withName("admin:update").build();

        PermissionEntity deletePermission = new PermissionEntityBuilder().withValidFields()
                .withName("admin:delete").build();

        // Role for Admin with permissions
        RoleEntity adminRole = new RoleEntityBuilder().withValidFields().withName("Admin")
                .withPermissions(List.of(createPermission, getPermission, updatePermission, deletePermission))
                .build();

        // Permissions for User
        PermissionEntity userGetPermission = new PermissionEntityBuilder().withValidFields()
                .withName("user:get")
                .build();

        // Role for User with permissions
        RoleEntity userRole = new RoleEntityBuilder().withValidFields()
                .withName("User")
                .withPermissions(List.of(userGetPermission))
                .build();

        // Admin entity with role
        UserEntity adminEntity = new UserEntityBuilder().withValidAdminFields().withRoles(List.of(adminRole)).build();

        // User entity with role
        UserEntity userEntity = new UserEntityBuilder().withValidUserFields().withRoles(List.of(userRole)).build();

        // Generate tokens for Admin
        this.mockAdminCreateToken = this.generate(adminEntity.getClaims());
        this.mockAdminGetToken = this.generate(adminEntity.getClaims());
        this.mockAdminUpdateToken = this.generate(adminEntity.getClaims());
        this.mockAdminDeleteToken = this.generate(adminEntity.getClaims());

        // Generate token for User
        this.mockUserGetToken = this.generate(userEntity.getClaims());
    }

    private Token generate(Map<String, Object> claims) {

        final long currentTimeMillis = System.currentTimeMillis();

        final Date tokenIssuedAt = new Date(currentTimeMillis);

        final Date accessTokenExpiresAt = DateUtils.addMinutes(new Date(currentTimeMillis), tokenConfiguration.getAccessTokenExpireMinute());

        final String accessToken = Jwts.builder()
                .header()
                .add(TokenClaims.TYP.getValue(), OAuth2AccessToken.TokenType.BEARER.getValue())
                .and()
                .id(UUID.randomUUID().toString())
                .issuer(tokenConfiguration.getIssuer())
                .issuedAt(tokenIssuedAt)
                .expiration(accessTokenExpiresAt)
                .signWith(tokenConfiguration.getPrivateKey())
                .claims(claims)
                .compact();

        final Date refreshTokenExpiresAt = DateUtils.addDays(new Date(currentTimeMillis), tokenConfiguration.getRefreshTokenExpireDay());

        final JwtBuilder refreshTokenBuilder = Jwts.builder();

        final String refreshToken = refreshTokenBuilder
                .header()
                .add(TokenClaims.TYP.getValue(), OAuth2AccessToken.TokenType.BEARER.getValue())
                .and()
                .id(UUID.randomUUID().toString())
                .issuer(tokenConfiguration.getIssuer())
                .issuedAt(tokenIssuedAt)
                .expiration(refreshTokenExpiresAt)
                .signWith(tokenConfiguration.getPrivateKey())
                .claim(TokenClaims.USER_ID.getValue(), claims.get(TokenClaims.USER_ID.getValue()))
                .compact();

        return Token.builder()
                .accessToken(accessToken)
                .accessTokenExpiresAt(accessTokenExpiresAt.toInstant().getEpochSecond())
                .refreshToken(refreshToken)
                .build();

    }


}
