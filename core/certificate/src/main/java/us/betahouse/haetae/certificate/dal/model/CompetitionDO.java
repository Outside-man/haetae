/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 竞赛证书实体类
 *
 * @author guofan.cp
 * @version : CompetitionDO.java 2019/04/03 14:22 guofan.cp
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "competition_certificate_record",
        indexes = {
                @Index(name = "uk_certificate_id", columnList = "certificate_id", unique = true)
        })
public class CompetitionDO extends BaseCommonDO {

    private static final long serialVersionUID = -8785538790073759064L;

    /**
     * 比赛名字
     */
    @Column(name = "competition_name", nullable = false, length = 200)
    private String competitionName;
    /**
     * 团队成员学号集合
     */
    @Column(name = "workers_user_id")
    private String workersUserId;
    /**
     * 比赛等级
     */
    @Column(name = "competition_rank", nullable = false)
    private String rank;
    /**
     * 队名
     */
    @Column(name = "team_name")
    private String teamName;
    /**
     * 指导教师名字集合
     */
    @Column(name = "teacher")
    private String teacher;

    /**
     * 团队id
     */
    @Column(name="team_id",nullable = false,length = 32)
    private String teamId;

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getWorkersUserId() {
        return workersUserId;
    }

    public void setWorkersUserId(String workersUserId) {
        this.workersUserId = workersUserId;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
