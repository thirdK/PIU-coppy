package com.example.pickitup.domain.vo.dto;

import com.example.pickitup.domain.vo.Criteria;
import com.example.pickitup.domain.vo.ProjectCriteria;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ProjectPageDTO {

    private ProjectCriteria projectCriteria;
    private int startPage;
    private int endPage;
    private int realEnd;
    private int pageCount;
    private boolean prev, next;
    private Long num;
    private String nickname;
    private String email;
    private String registDate;
    private String phone;
    private String address;

    private int total;

    public ProjectPageDTO(ProjectCriteria projectCriteria, int total) {
        this(projectCriteria, 10, total);
    }

    public ProjectPageDTO(ProjectCriteria projectCriteria, int pageCount, int total) {
        this.projectCriteria = projectCriteria;
        this.total = total;
//        현재 페이지를 기준으로 소수점까지 모두 계산하여 보여질 마지막 페이지 번호를 구한다.
        this.endPage = (int)Math.ceil(projectCriteria.getPageNum() / (double)pageCount) * pageCount;
        this.startPage = this.endPage - pageCount + 1;
        this.realEnd = (int)Math.ceil((double)total / projectCriteria.getAmount());

        if(realEnd < this.endPage){
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }

}
