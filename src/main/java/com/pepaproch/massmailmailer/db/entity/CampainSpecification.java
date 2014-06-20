/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package com.pepaproch.massmailmailer.db.entity;

import static com.pepaproch.massmailmailer.db.entity.AbstracSpec.getLikeString;
import java.math.BigDecimal;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author pepa
 */
public class CampainSpecification extends AbstracSpec {

    public static Specification<Campain> findInStringFields(final String ctype, final String value, final String compareType) {
        return new Specification<Campain>() {

            @Override
            public Predicate toPredicate(Root<Campain> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder) {
                Predicate resultPredicate;

                switch (compareType) {
                    case AbstracSpec.LIKE_COMPARE:
                        resultPredicate = builder.or(
                                builder.like(root.get(Campain_.campainName), getLikeString(value)),
                                builder.like(root.get(Campain_.subject), getLikeString(value)));
                        break;
                    default:
                        resultPredicate = builder.or(
                                builder.equal(root.get(Campain_.campainName), value),
                                builder.equal(root.get(Campain_.subject), value));

                }

                return resultPredicate;
            }
        };
    }
    
    
    
    

    public static Specification<Campain> findByCampainName(final String value, final String compareType) {
        return new Specification<Campain>() {
            @Override
            public Predicate toPredicate(Root<Campain> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder) {

                Predicate resultPredicate;

                switch (compareType) {
                    case AbstracSpec.LIKE_COMPARE:
                        resultPredicate = builder.like(builder.lower(root.get(Campain_.campainName)), getLikeString(value));
                        break;
                    default:
                        resultPredicate = builder.equal(root.get(Campain_.campainName), value);

                }

                return resultPredicate;
            }
        };
    }
    
    
        public static Specification<Campain> findByCampainType(final String value) {
        return new Specification<Campain>() {
            @Override
            public Predicate toPredicate(Root<Campain> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder) {

                Predicate resultPredicate;

                switch (value) {
                    case AbstracSpec.ALL:
                        resultPredicate = builder.isNotNull(root.get(Campain_.campainName));
                        break;
                    default:
                        resultPredicate = builder.equal(root.get(Campain_.status), value);

                }

                return resultPredicate;
            }
        };
    }

    /**
     *
     * @param value
     * @param compare
     * @return
     */
    public static Specification<Campain> findByRecordCount(final Long value, final String compare) {
        return new Specification<Campain>() {
            @Override
            public Predicate toPredicate(Root<Campain> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder) {
                final Predicate resultPredicate;
                switch (compare) {
                    case AbstracSpec.EQUAL_COMPARE:

                        resultPredicate = builder.lessThan(root.get(Campain_.recordsCount), value);
                        break;
                    default:
                        resultPredicate = builder.equal(root.get(Campain_.recordsCount), value);
                }
                return resultPredicate;
            }
        };
    }

}
