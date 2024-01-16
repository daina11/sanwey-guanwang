package sw.core.spring.user;

import java.io.Serializable;

/**
 * 菜单样式
 * @author wwh
 * @date 2021/4/21
 * @email 644129971@qq.com
 */
public class AuthUserMenuMeta implements Serializable {

    /**菜单Id*/
    private String id;
    /**菜单名称*/
    private String title;
    /**图标*/
    private String icon;
    /** **/
    private Boolean noCache;

    private AuthUserMenuMeta(){}

    public AuthUserMenuMeta(String id, String title, String icon, Boolean noCache) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
    }

    public static AuthUserMenuMeta build(AuthUserMenu menu){
        return new AuthUserMenuMeta(menu.getId(), menu.getTitle(), menu.getIcon(), false);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getNoCache() {
        return noCache;
    }

    public void setNoCache(Boolean noCache) {
        this.noCache = noCache;
    }
}
