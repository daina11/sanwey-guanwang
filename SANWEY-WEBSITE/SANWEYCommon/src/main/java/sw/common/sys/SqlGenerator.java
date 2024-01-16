package sw.common.sys;

import org.apache.commons.lang3.StringUtils;

/**
 * @author wwh
 * @date 2021/9/27
 * @email 644129971@qq.com
 */
public class SqlGenerator {
    /**返回字段*/
    public String select;
    /**数据总条数*/
    public String selectTotal;
    /**表*/
    public String from;
    /**条件*/
    public String where;
    /**分页*/
    public String limit;
    /**排序*/
    public String orderBy;

    public String start(){
        StringBuffer sql = new StringBuffer();
        if (StringUtils.isNotBlank(this.select)) sql.append(this.select);
        if (StringUtils.isNotBlank(this.from)) sql.append(this.from);
        if (StringUtils.isNotBlank(this.where)) sql.append(this.where);
        if (StringUtils.isNotBlank(this.orderBy)) sql.append(this.orderBy);
        if (StringUtils.isNotBlank(this.limit)) sql.append(this.limit);
        return sql.toString();
    }

    public String totalSql(){
        return new StringBuffer().append(this.selectTotal).append(this.from).append(this.where).toString();
    }
}
