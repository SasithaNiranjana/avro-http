package com.sasitha.avrohttp;

import com.sasitha.springavrowebflux.domain.RequestHeaders;
import com.sasitha.springavrowebflux.domain.Student;
import com.sasitha.springavrowebflux.domain.StudentDomain;
import org.apache.avro.AvroRemoteException;
import org.springframework.stereotype.Component;

@Component
public class StudentDomainImpl implements StudentDomain {
    @Override
    public Student saveStudent(RequestHeaders headers, Student student) throws AvroRemoteException {
        System.out.println("Student inserted");
        return Student.newBuilder().setId(1l).setName("Sasitha").setAddress("Hougang").build();
    }

    @Override
    public Student updateStudent(RequestHeaders headers, Student student) throws AvroRemoteException {
        System.out.println("Student updated");
        return Student.newBuilder().setId(1l).setName("Sasitha").setAddress("Hougang").build();
    }

    @Override
    public Student getStudent(RequestHeaders headers, long id) throws AvroRemoteException {
        System.out.println("Student retrieved");
        return Student.newBuilder().setId(1l).setName("Sasitha").setAddress("Hougang").build();
    }

    @Override
    public Void deleteStudent(RequestHeaders headers, long id) throws AvroRemoteException {
        System.out.println("Student deleted");
        return null;
    }
}
