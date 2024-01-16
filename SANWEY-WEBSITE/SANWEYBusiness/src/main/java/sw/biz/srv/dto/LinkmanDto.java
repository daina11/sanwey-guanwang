package sw.biz.srv.dto;

import sw.biz.bas.annotation.Equal;
import sw.biz.bas.business.BasePageCondition;
import sw.biz.enums.LinkmanEnum;

/**
 * @author dwg
 * Date: 2023/12/22
 */
public class LinkmanDto  extends BasePageCondition {
    @Equal(column = "status")
    private LinkmanEnum linkmanEnum;

    public LinkmanEnum getLinkmanEnum() {
        return linkmanEnum;
    }

    public void setLinkmanEnum(LinkmanEnum linkmanEnum) {
        this.linkmanEnum = linkmanEnum;
    }
}
