package us.betahouse.haetae.serviceimpl.activity.model;

import us.betahouse.util.common.ToString;

import java.util.List;

/**
 *
 * 活动报名信息列表
 *
 * @author zjb
 * @version : ActivityEntryList.java 2019/7/8 1:17 zjb
 */
public class ActivityEntryList extends ToString {

    private static final long serialVersionUID = -1020687968080157475L;
    /**
     * 总数据
     */
    private Long totalElements;

    /**
     * 总页码
     */
    private Integer totalPages;

    /**
     * 每页大小
     */
    private Integer size;

    /**
     * 当前页
     */
    private Integer number;

    /**
     * 当前页条数
     */
    private Integer numberOfElements;

    /**
     * 是否是首页
     */
    private Boolean first;

    /**
     * 是否是尾页
     */
    private Boolean end;

    /**
     * 数据
     */
    private List content;

    public ActivityEntryList(){

    }

    public ActivityEntryList(Long totalElements, Integer totalPages, Integer size, Integer number, Integer numberOfElements, Boolean first, Boolean end, List content) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.size = size;
        this.number = number;
        this.numberOfElements = numberOfElements;
        this.first = first;
        this.end = end;
        this.content = content;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }

    public List getContent() {
        return content;
    }

    public void setContent(List content) {
        this.content = content;
    }
}
