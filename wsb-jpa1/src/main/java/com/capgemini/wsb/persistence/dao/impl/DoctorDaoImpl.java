package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.DoctorDao;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.enums.Specialization;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorDaoImpl extends AbstractDao<DoctorEntity, Long> implements DoctorDao {
    @Override
    public List<DoctorEntity> findBySpecialization(Specialization specialization) { // TODO(DONE) - napisac query

        return entityManager.createQuery(
                "SELECT doc FROM DoctorEntity doc " +
                        "WHERE doc.specialization = :specialization",
                        DoctorEntity.class
                ).setParameter("specialization", specialization).getResultList();
    }

    @Override
    public long countNumOfVisitsWithPatient(String docFirstName, String docLastName, String patientFirstName, String patientLastName) { // TODO(DONE) - napisac query
        return entityManager.createQuery(
                        "SELECT COUNT(vis) FROM VisitEntity vis " +
                                "JOIN vis.doctor doc " +
                                "JOIN vis.patient pat " +
                                "WHERE doc.firstName = :docFirstName " +
                                "AND doc.lastName = :docLastName " +
                                "AND pat.firstName = :patientFirstName " +
                                "AND pat.lastName = :patientLastName",
                        Long.class)
                .setParameter("docFirstName", docFirstName)
                .setParameter("docLastName", docLastName)
                .setParameter("patientFirstName", patientFirstName)
                .setParameter("patientLastName", patientLastName)
                .getSingleResult();
    }
}
