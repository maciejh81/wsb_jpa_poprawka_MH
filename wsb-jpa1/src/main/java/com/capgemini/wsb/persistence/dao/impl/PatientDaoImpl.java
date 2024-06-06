package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.persistence.enums.TreatmentType;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {


    @Override
    public List<PatientEntity> findByDoctor(String firstName, String lastName) { // TODO - napisac query
        Query query = entityManager.createQuery(
                "select t1 from PatientEntity t1 join t1.visits t2 where t2.doctor.firstName=:firstName and t2.doctor.lastName=:lastName"
        );

        query.setParameter("firstName",firstName);
        query.setParameter("lastName",lastName);

        return query.getResultList();
        //return new ArrayList<>();

    }

    @Override
    public List<PatientEntity> findPatientsHavingTreatmentType(TreatmentType treatmentType) { // TODO - napisac query
        Query query = entityManager.createQuery(
                "select distinct t1 from PatientEntity t1 join t1.visits t2 join t2.medicalTreatments t3 where t3.type=:treatmentType"
        );

        query.setParameter("treatmentType",treatmentType);

        return query.getResultList();
        //return new ArrayList<>();
    }

    @Override
    public List<PatientEntity> findPatientsSharingSameLocationWithDoc(String firstName, String lastName) { // TODO - napisac query
        Query query = entityManager.createQuery(
                "select t1 from PatientEntity t1 join t1.addresses t2 join t2.doctors t3 where t3.firstName=:firstName and t3.lastName=:lastName"
        );

        query.setParameter("firstName",firstName);
        query.setParameter("lastName",lastName);

        return query.getResultList();
        //return new ArrayList<>();
    }

    @Override
    public List<PatientEntity> findPatientsWithoutLocation() { // TODO - napisac query
        Query query = entityManager.createQuery(
                "select t1 from PatientEntity t1 where size(t1.addresses)=0"
        );

        return query.getResultList();
        //return null;
    }
}
