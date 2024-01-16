package sw.biz.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据分页工具
 * @author wwh
 * @date 2021/1/22
 * @email 644129971@qq.com
 */
public class Pageable<T> implements Serializable {

    /**当前页*/
    private int page = 1;

    /**每页条数*/
    private int pageSize = 10;

    /**总条数*/
    private long total;

    /**排序*/
    private OrderBy orderBy = new OrderBy();

    /**数据结果*/
    private List<T> content = new ArrayList();

    /***--------构造器--------**/
    public Pageable() {
    }

    public Pageable(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public Pageable(List<T> content, Pageable pageable) {
        this.content = content;
        this.page = pageable.getPage();
        this.pageSize = pageable.getPageSize();
        this.total = pageable.getTotal();
        this.orderBy = pageable.getOrderBy();
    }

    /***--------构建查询条件--------**/
    public int selectPage(){
        return getPage() > 0 ? getPage() - 1 : 0;
    }

    /***--------构建返回数据--------**/
    public Pageable page(int page){
        this.page = page + 1;
        return this;
    }

    public Pageable pageSize(int pageSize){
        this.pageSize = pageSize;
        return this;
    }

    public Pageable total(long total){
        this.total = total;
        return this;
    }

    public Pageable orderBy(OrderBy orderBy){
        this.orderBy = orderBy;
        return this;
    }

    public Pageable content(List<T> content){
        this.content.addAll(content);
        return this;
    }

    /***--------get()/set()--------**/
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }

    public List<T> getContent() {
        return content;
    }
}