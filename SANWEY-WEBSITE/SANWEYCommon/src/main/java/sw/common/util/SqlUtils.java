package sw.common.util;

import sw.common.sys.SqlGenerator;

/**
 * sql语句构造
 * @author wwh
 * @date 2021/9/27
 * @email 644129971@qq.com
 */
public class SqlUtils {

    private SqlGenerator sqlGenerator;

    public static SqlUtils newBuilder(){
        SqlUtils sqlUtils = new SqlUtils();
        sqlUtils.sqlGenerator = new SqlGenerator();
        return sqlUtils;
    }

    public SqlUtils select(String select){
        this.sqlGenerator.select = select;
        return this;
    }

    public SqlUtils selectTotal(String selectTotal){
        this.sqlGenerator.selectTotal = selectTotal;
        return this;
    }

    public SqlUtils from(String from){
        this.sqlGenerator.from = from;
        return this;
    }

    public SqlUtils where(String where){
        this.sqlGenerator.where = where;
        return this;
    }

    /**
     * @param page 页
     * @param row 记录条数
     */
    public SqlUtils limit(int page, int row){
        StringBuffer limitSql = new StringBuffer();
        if (row > 0) {
            int startRow = (page - 1) * row;//计算
            limitSql.append(" LIMIT ");
            limitSql.append(startRow);
            limitSql.append(",");
            limitSql.append(row);
        }
        this.sqlGenerator.limit = limitSql.toString();
        return this;
    }

    public SqlUtils orderBy(String orderBy){
        this.sqlGenerator.orderBy = orderBy;
        return this;
    }

    public String build(){
        return this.sqlGenerator.start();
    }

    public String buildTotalSql(){
        return this.sqlGenerator.totalSql();
    }

    public String buildLimitSql(){
        return this.sqlGenerator.limit;
    }
}
