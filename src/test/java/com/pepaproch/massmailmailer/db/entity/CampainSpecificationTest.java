/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.db.entity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author pepa
 */
public class CampainSpecificationTest {

    private CriteriaBuilder criteriaBuilderMock;

    private CriteriaQuery criteriaQueryMock;

    private Root<Campain> campainRootMock;

    @Before
    public void setup() {
        criteriaBuilderMock = mock(CriteriaBuilder.class);
        criteriaQueryMock = mock(CriteriaQuery.class);
        campainRootMock = mock(Root.class);
    }

    public CampainSpecificationTest() {
    }

    /**
     * Test of findInStringFields method, of class CampainSpecification.
     */
    @Test
    public void testFindInStringFields() {
    }

    /**
     * Test of findByCampainName method, of class CampainSpecification.
     */
    @Test
    public void testFindByCampainName() {
        Path campainNameMck = mock(Path.class);
        when(campainRootMock.get(Campain_.campainName)).thenReturn(campainNameMck);

        Predicate campainNameEqMck = mock(Predicate.class);
        when(criteriaBuilderMock.equal(campainNameMck, "name")).thenReturn(campainNameEqMck);
        
        Specification mySpec = CampainSpecification.findByCampainName("name", AbstracSpec.EQUAL_COMPARE);
        Predicate myPred = mySpec.toPredicate(campainRootMock, criteriaQueryMock, criteriaBuilderMock);
        assertEquals(myPred, campainNameEqMck);
    

    }

    /**
     * Test of findByRecordCount method, of class CampainSpecification.
     */
    @Test
    public void testFindByRecordCount() {
    }

}
