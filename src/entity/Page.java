package entity;

import java.util.List;

/**
 * @description 定义通用的分页实体类
 * @user summerHouAnNing
 * @creatTime 2021/4/29--13:50
 */
public class Page<T> {
    private Integer pageNo;//当前页码
    private Integer pageTotal;//总页码
    private Integer pageSize;//当前页显示的数量
    private Integer getPageTotalCount;//数据总数
    private List<T> items;//当前页显示的数据

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getGetPageTotalCount() {
        return getPageTotalCount;
    }

    public void setGetPageTotalCount(Integer getPageTotalCount) {
        this.getPageTotalCount = getPageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", getPageTotalCount=" + getPageTotalCount +
                ", items=" + items +
                '}';
    }
}
