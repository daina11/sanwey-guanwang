package sw.core.spring.user;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wwh
 * @date 2021/4/21
 * @email 644129971@qq.com
 */
public class AuthUserMenu implements RowMapper<AuthUserMenu>, Serializable {

    /**主键*/
    private String id;

    /**菜单名称*/
    private String name;

    /**菜单名称-meta.title*/
    private String title;

    /**菜单图标-meta.icon*/
    private String icon;

    /**地址是否隐藏*/
    private Boolean hidden = false;

    /**URL*/
    private String path;

    /**菜单视图||模块路径*/
    private String component;

    /**加载当前菜单重定向Url*/
    private String redirect;

    /**序号*/
    private Integer index;

    /**菜单类型*/
    private String menuType;

    /**菜单样式*/
    private AuthUserMenuMeta meta;

    /**上级*/
    private String parent;

    /**子集*/
    private List<AuthUserMenu> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public AuthUserMenuMeta getMeta() {
        return meta;
    }

    public void setMeta(AuthUserMenuMeta meta) {
        this.meta = meta;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<AuthUserMenu> getChildren() {
        return children;
    }

    public void setChildren(List<AuthUserMenu> children) {
        this.children = children;
    }

    @Override
    public AuthUserMenu mapRow(ResultSet resultSet, int i) throws SQLException {
        AuthUserMenu userMenu = new AuthUserMenu();
        userMenu.setId(resultSet.getString("FID"));
        userMenu.setName(resultSet.getString("FNAME"));
        userMenu.setTitle(resultSet.getString("FMETATITLE"));
        userMenu.setIcon(resultSet.getString("FMETAICON"));
        userMenu.setHidden(resultSet.getBoolean("FHIDDEN"));
        userMenu.setPath(resultSet.getString("FPATH"));
        userMenu.setComponent(resultSet.getString("FCOMPONENT"));
        userMenu.setRedirect(resultSet.getString("FREDIRECT"));
        userMenu.setIndex(resultSet.getInt("FINDEX"));
        userMenu.setMenuType(resultSet.getString("FMENUTYPE"));
        userMenu.setParent(resultSet.getString("FPARENT"));
        return userMenu;
    }

    /**重新构建菜单，组建为上下级结构*/
    public static List<AuthUserMenu> rebuild(List<AuthUserMenu> original){
        List<AuthUserMenu> result = new ArrayList<>();
        for (AuthUserMenu menu : original){
            menu.setMeta(AuthUserMenuMeta.build(menu));
            if (menu.getParent() != null){
                for (AuthUserMenu parent : original){
                    if (menu.getParent().equals(parent.getId())){
                        if (parent.getChildren() !=null){
                            parent.getChildren().add(menu);
                            continue;
                        }else {
                            List<AuthUserMenu> children = new ArrayList<>();
                            children.add(menu);
                            parent.setChildren(children);
                        }
                    }
                }
            }else {
                result.add(menu);
            }
        }
        return result;
    }
}
