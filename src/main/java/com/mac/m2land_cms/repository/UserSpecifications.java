package com.mac.m2land_cms.repository;

import com.mac.m2land_cms.model.entity.User;
import com.mac.m2land_cms.model.entity.UserCharacter;
import com.mac.m2land_cms.model.enum_class.AuthProvider;
import com.mac.m2land_cms.model.response.UserResponse;
import com.mac.m2land_cms.utils.PageUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The Class UserSpecifications.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Component
public class UserSpecifications {

    @PersistenceContext
    private EntityManager entityManager;

    private final PageUtils pageUtils;
    private final ModelMapper modelMapper;

    private static final Map<String, String> paramMapping = new HashMap<>();


    static {
        paramMapping.put("uuid", "uuid");
        paramMapping.put("kakao_id", "provider");
        paramMapping.put("google_id", "provider");
        paramMapping.put("apple_id", "provider");
        paramMapping.put("naver_id", "provider");
        paramMapping.put("character_name", "character_name");
    }

    public UserSpecifications(PageUtils pageUtils, ModelMapper modelMapper) {
        this.pageUtils = pageUtils;
        this.modelMapper = modelMapper;
    }


    /**
     * @param searchCondition
     * @param searchWord
     * @return List<User>
     */

    public Page<User> searchUserAccount(String searchCondition, String searchWord, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        List<Predicate> predicates = new ArrayList<>();

        if (searchCondition != null && searchWord != null) {
            if (searchCondition.trim().isEmpty() || searchCondition.equals("전체")) {
                predicates.add(builder.isNotNull(root.get("uuid")));
            } else {
                String search = convertParam(searchCondition);
                String[] parts = search.toLowerCase().split("_");
                StringBuilder attributeNameBuilder = new StringBuilder(parts[0]);
                for (int i = 1; i < parts.length; i++) {
                    String part = parts[i];
                    attributeNameBuilder.append(part.substring(0, 1).toUpperCase()).append(part.substring(1));
                }
                if (search.equals("provider")) {
                    List<Predicate> orPredicates = new ArrayList<>();
                    if ("google_id".equals(searchCondition)) {
                        orPredicates.add(builder.and(
                                builder.equal(root.get("provider"), AuthProvider.google),
                                builder.equal(root.get("email"), searchWord)
                        ));
                    } else if ("kakao_id".equals(searchCondition)) {
                        orPredicates.add(builder.and(
                                builder.equal(root.get("provider"), AuthProvider.kakao),
                                builder.equal(root.get("email"), searchWord)
                        ));
                    } else if ("naver_id".equals(searchCondition)) {
                        orPredicates.add(builder.and(
                                builder.equal(root.get("provider"), AuthProvider.naver),
                                builder.equal(root.get("email"), searchWord)
                        ));
                    } else if ("apple_id".equals(searchCondition)) {
                        orPredicates.add(builder.and(
                                builder.equal(root.get("provider"), AuthProvider.apple),
                                builder.equal(root.get("email"), searchWord)
                        ));
                    } else {
                        orPredicates.add(builder.equal(root.get("provider"), AuthProvider.google));
                    }
                    predicates.add(builder.or(orPredicates.toArray(new Predicate[0])));
                } else if (search.equals("character_name")) {
                    Join<User, UserCharacter> characterJoin = root.join("character");
                    predicates.add(builder.equal(characterJoin.get("characterName"), searchWord));
                } else {
                    String attributeName = attributeNameBuilder.toString();

                    predicates.add(builder.equal(root.get(search), attributeName));
                }
            }
        }

        query.select(root).where(builder.or(predicates.toArray(new Predicate[0])));

        TypedQuery<User> typedQuery = entityManager.createQuery(query);

        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());


        List<User> users = typedQuery.getResultList();
        List<UserResponse> responses = users.stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
        List<User> userList = responses.stream()
                .map(userResponse -> modelMapper.map(userResponse, User.class))
                .collect(Collectors.toList());
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<User> countRoot = countQuery.from(User.class);
        countQuery.select(builder.count(countRoot)).where(builder.or(predicates.toArray(new Predicate[0])));
        int totalItems = typedQuery.getResultList().size();

        return new PageImpl<>(userList, pageable, totalItems);
    }


    /**
     * @param searchCondition
     * @return
     */
    public String convertParam(String searchCondition) {
        return paramMapping.getOrDefault(searchCondition, "");
    }
}
