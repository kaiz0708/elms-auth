package com.elms.auth.model.criteria;

import com.elms.auth.model.Account;
import com.elms.auth.model.Career;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CareerCriteria {

    private Long id;
    private Long accountId;
    private String careerName;
    private Integer status;

    public static Specification<Career> findCareerByCriteria(final CareerCriteria careerCriteria) {
        return new Specification<Career>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Career> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (careerCriteria.getAccountId() != null) {
                    Join<Career, Account> account = root.join("account", JoinType.INNER);
                    predicates.add(cb.equal(account.get("id"), careerCriteria.getAccountId()));
                }
                if (careerCriteria.getId() != null) {
                    predicates.add(cb.equal(root.get("id"), careerCriteria.getId()));
                }
                if (!StringUtils.isEmpty(careerCriteria.getCareerName())) {
                    predicates.add(cb.like(cb.lower(root.get("storeName")), "%" + careerCriteria.getCareerName().toLowerCase() + "%"));
                }
                if (careerCriteria.getStatus() != null) {
                    predicates.add(cb.equal(root.get("status"), careerCriteria.getStatus()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
