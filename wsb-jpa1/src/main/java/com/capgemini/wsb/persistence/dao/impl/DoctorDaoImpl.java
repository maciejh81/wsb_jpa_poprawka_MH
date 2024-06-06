package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.DoctorDao;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.enums.Specialization;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorDaoImpl extends AbstractDao<DoctorEntity, Long> implements DoctorDao {
    @Override
    public List<DoctorEntity> findBySpecialization(Specialization specialization) { // TODO - napisac query
        Query query = entityManager.createQuery(
          "select t1 from DoctorEntity t1 where t1.specialization = :specialization", DoctorEntity.class);
        query.setParameter("specialization",specialization);
        return query.getResultList();
        //return new ArrayList<>();
    }

    @Override
    public long countNumOfVisitsWithPatient(String docFirstName, String docLastName, String patientFirstName, String patientLastName) { // TODO - napisac query
        Query query = entityManager.createQuery(
                "select t1 from VisitEntity t1 where t1.doctor.firstName=:docFirstName and t1.doctor.lastName=:docLastName and t1.patient.firstName=:patientFirstName and t1.patient.lastName=:patientLastName"
        );

        query.setParameter("docFirstName",docFirstName);
        query.setParameter("docLastName",docLastName);
        query.setParameter("patientFirstName",patientFirstName);
        query.setParameter("patientLastName",patientLastName);

        return query.getResultList().size();
        //return 1000;
    }
}
