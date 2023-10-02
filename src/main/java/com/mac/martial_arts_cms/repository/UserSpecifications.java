package com.mac.martial_arts_cms.repository;

import com.mac.martial_arts_cms.model.entity.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class UserSpecifications.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
public class UserSpecifications {

    /**
     *
     * @param searchCondition
     * @param searchWord
     * @return Specification<User>
     */
    public static Specification<User> compareKeywords(String searchCondition, String searchWord) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(searchCondition) && !StringUtils.isEmpty(searchWord)) {
                predicates.add(builder.equal(root.get(searchCondition), searchWord));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
