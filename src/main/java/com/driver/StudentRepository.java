package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Repository
public class StudentRepository {

    HashMap<String,Student> sdb=new HashMap<>();  //sdb- student database
    HashMap<String,Teacher> tdb=new HashMap<>();  //tdb- teacher database
    HashMap<String,List<String>> stpair=new HashMap<>();
    public void addStudent(Student student) {
        sdb.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        tdb.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        List<String> stlist =new ArrayList<>();
        if(stpair.containsKey(teacher)){
            stlist=stpair.get(teacher);
            if(!stlist.contains(student)){
                stlist.add(student);
                stpair.put(teacher,stlist);
            }
        }
    }

    public Student getStudentByName(String name) {
        return sdb.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return tdb.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        List<String> l=new ArrayList<>();
        for(String s:stpair.get(teacher)){
            l.add(s);
        }
        return l;
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(sdb.keySet());
    }

    public void deleteTeacherByName(String teacher) {
        //delete teacher in stpair
        List<String> l = new ArrayList<>();
        if (stpair.containsKey(teacher)) {
            l = stpair.get(teacher);
            for (String s : l) {
                sdb.remove(s);
            }
            stpair.remove(teacher);
        }
        tdb.remove(teacher);
    }

    public void deleteAllTeachers() {
        for(String teacher:stpair.keySet()){
            List<String> slist=stpair.get(teacher);
            for(String s:slist){
                sdb.remove(s);
            }
        }
        tdb.clear();
        stpair.clear();
    }
}
