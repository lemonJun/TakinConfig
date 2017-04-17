package com.xxl.conf.mysql;

import java.util.Date;

import com.bj58.sfft.utility.dao.annotation.Id;
import com.bj58.sfft.utility.dao.annotation.Table;
import com.bj58.spat.scf.serializer.component.annotation.SCFSerializable;

@Table(name = "t_mvtochr_job")
@SCFSerializable
public class JobRelBean {

    @Id
    private int id;
    private long buid;
    private String mvcomid;
    private String mvjobid;

    private String chrcomid;
    private String chrjobid;

    private String comname;
    private String jobname;
    private int published;

    private Date publishtime;

    private int prov;

    private int city;

    private String params;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getBuid() {
        return buid;
    }

    public void setBuid(long buid) {
        this.buid = buid;
    }

    public String getMvcomid() {
        return mvcomid;
    }

    public void setMvcomid(String mvcomid) {
        this.mvcomid = mvcomid;
    }

    public String getMvjobid() {
        return mvjobid;
    }

    public void setMvjobid(String mvjobid) {
        this.mvjobid = mvjobid;
    }

    public String getChrcomid() {
        return chrcomid;
    }

    public void setChrcomid(String chrcomid) {
        this.chrcomid = chrcomid;
    }

    public String getChrjobid() {
        return chrjobid;
    }

    public void setChrjobid(String chrjobid) {
        this.chrjobid = chrjobid;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public int getProv() {
        return prov;
    }

    public void setProv(int prov) {
        this.prov = prov;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

}
