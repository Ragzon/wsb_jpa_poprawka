package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.persistence.enums.TreatmentType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {


    @Override
    public List<PatientEntity> findByDoctor(String firstName, String lastName) { // TODO(DONE) - napisac query

        return entityManager.createQuery(
                                "SELECT pat FROM PatientEntity pat " +
                                "JOIN pat.visits vis " +
                                "WHERE vis.doctor.firstName = :firstName " +
                                "AND vis.doctor.lastName = :lastName",
                        PatientEntity.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsHavingTreatmentType(TreatmentType treatmentType) { // TODO(DONE) - napisac query
        return entityManager.createQuery(
                        "SELECT DISTINCT pat FROM PatientEntity pat " +
                                "JOIN pat.visits vis " +
                                "JOIN vis.medicalTreatments medTr " +
                                "WHERE medTr.type = :treatmentType",
                        PatientEntity.class)
                .setParameter("treatmentType", treatmentType)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsSharingSameLocationWithDoc(String firstName, String lastName) { // TODO(DONE) - napisac query

        return entityManager.createQuery(
                        "SELECT DISTINCT pat FROM PatientEntity pat " +
                                "JOIN pat.addresses adr " +
                                "JOIN adr.doctors doc " +
                                "WHERE doc.firstName = :firstName AND doc.lastName = :lastName",
                        PatientEntity.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList();

    }

    @Override
    public List<PatientEntity> findPatientsWithoutLocation() { // TODO(DONE) - napisac query

        return entityManager.createQuery(
                        "SELECT pat FROM PatientEntity pat " +
                                "WHERE pat.addresses IS EMPTY",
                        PatientEntity.class).getResultList();
    }
}
