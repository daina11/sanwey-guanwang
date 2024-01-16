package sw.biz.bas.business;

import java.util.Date;

/**
 * 日期筛选-基类
 * @author wwh
 * @date 2022/6/22-18:44
 * @email 644129971@qq.com
 */
public class BaseDateCondition {
    private Date startDate;
    private Date endDate;

    private BaseDateCondition() {}

    public BaseDateCondition(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
