package sw.core.spring.user;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户授权信息
 * @author
 * @date 2020年12月18日
 */
public class AuthUser implements Serializable {
    /**用户id*/
    private String id;
    /**编号*/
    private String number;
    /**名称*/
    private String name;
    /**用户名称*/
    private String username;
    /**手机号*/
    private String phone;
    /**邮箱*/
    private String email;
    /**联系地址*/
    private String address;
    /**用户密码*/
    private String password;
    /**头像*/
    private String avatar;
    /**cuId*/
    private String cuId;
    /**公司名称*/
    private String cuName;
    /**用户类型*/
    private UserType userType;
    /**绑定的工程师*/
    private String engId;
    /**工程师所在组织-id*/
    private String engCuId;
    /**工程师所在组织-name*/
    private String engCuName;
    /**是否初始密码*/
    private Boolean hasInitPassword;
    /**用户权限*/
    private List<SpringGrantedAuthority> authorities;
    /**关联菜单*/
    private List<AuthUserMenu> menus;

    public AuthUser() {
    }

    public AuthUser(String id, String number, String name, String username, String phone, String password, String avatar, String cuId, String cuName, UserType userType, String engId, String engCuId, String engCuName, Boolean hasInitPassword, List<SpringGrantedAuthority> authorities, List<AuthUserMenu> menus, String email, String address) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.avatar = avatar;
        this.cuId = cuId;
        this.cuName = cuName;
        this.userType = userType;
        this.engId = engId;
        this.engCuId = engCuId;
        this.engCuName = engCuName;
        this.hasInitPassword = hasInitPassword;
        this.authorities = authorities;
        this.menus = menus;
        this.email = email;
        this.address = address;
    }

    public static AuthUser build(String json) {
        JSONObject data = JSONObject.parseObject(json);
        String id = data.getString("id");
        String number = data.getString("number");
        String name = data.getString("name");
        String username = data.getString("username");
        String phone = data.getString("phone");
        String email = data.getString("email");
        String address = data.getString("address");
        String password = data.getString("password");
        String avatar = data.getString("avatar");
        String cuId = data.getString("cuId");
        String cuName = data.getString("cuName");
        String engId = data.getString("engId");
        String engCuId = data.getString("engCuId");
        String engCuName = data.getString("engCuName");
        Boolean hasInitPassword = data.getBoolean("hasInitPassword");
        UserType userType = UserType.valueOf(data.getString("userType"));
        List<SpringGrantedAuthority> authorities = new ArrayList<>();
        if (data.getJSONArray("authorities") != null) {
            authorities.addAll(data.getJSONArray("authorities").toJavaList(SpringGrantedAuthority.class));
        }
        List<AuthUserMenu> menus = new ArrayList<>();
        if (data.getJSONArray("menus") != null){
            menus.addAll(data.getJSONArray("menus").toJavaList(AuthUserMenu.class));
        }
        return new AuthUser(id, number, name, username, phone, password, avatar, cuId, cuName, userType, engId, engCuId, engCuName, hasInitPassword, authorities, menus, email, address);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCuId() {
        return cuId;
    }

    public void setCuId(String cuId) {
        this.cuId = cuId;
    }

    public String getCuName() {
        return cuName;
    }

    public void setCuName(String cuName) {
        this.cuName = cuName;
    }

    public String getEngId() {
        return engId;
    }

    public void setEngId(String engId) {
        this.engId = engId;
    }

    public String getEngCuId() {
        return engCuId;
    }

    public void setEngCuId(String engCuId) {
        this.engCuId = engCuId;
    }

    public String getEngCuName() {
        return engCuName;
    }

    public void setEngCuName(String engCuName) {
        this.engCuName = engCuName;
    }

    public Boolean getHasInitPassword() {
        return hasInitPassword;
    }

    public void setHasInitPassword(Boolean hasInitPassword) {
        this.hasInitPassword = hasInitPassword;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<SpringGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<SpringGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public List<AuthUserMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<AuthUserMenu> menus) {
        this.menus = menus;
    }
}