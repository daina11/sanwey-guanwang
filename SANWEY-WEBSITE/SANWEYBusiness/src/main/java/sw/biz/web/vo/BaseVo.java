package sw.biz.web.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;
import sw.biz.bas.annotation.Equal;
import sw.biz.bas.annotation.Like;

import java.io.Serializable;

/**
 * 查询条件-基类
 * @author wwh
 * @date 2021/2/7
 * @email 644129971@qq.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseVo implements Serializable {

    @Equal(column = "id")
    private String id;

    @Like(column = "name")
    private String name;

    @Like(column = "number")
    private String number;

    @Equal(column = "ctrlUnitId")
    private String ctrlUnitId;

    @Equal(column = "hasEnabled")
    private Boolean hasEnabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (StringUtils.isBlank(id)) id = null;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCtrlUnitId() {
        return ctrlUnitId;
    }

    public void setCtrlUnitId(String ctrlUnitId) {
        this.ctrlUnitId = ctrlUnitId;
    }

    public Boolean getHasEnabled() {
        return hasEnabled;
    }

    public void setHasEnabled(Boolean hasEnabled) {
        this.hasEnabled = hasEnabled;
    }
}
