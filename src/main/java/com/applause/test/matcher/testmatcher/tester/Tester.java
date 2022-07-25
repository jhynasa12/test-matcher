package com.applause.test.matcher.testmatcher.tester;

import com.applause.test.matcher.testmatcher.bug.Bug;
import com.applause.test.matcher.testmatcher.device.Mobile;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "testers")
public class Tester {

    @Id
    @Column(name= "tester_id")
    private Long testerId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "country")
    @Enumerated(EnumType.STRING)
    private Country country;
    @Column(name = "last_login")
    private String lastLogin;

    @OneToMany(mappedBy="tester")
    private List<Bug> bugs;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "tester_device",
            joinColumns = @JoinColumn(name = "tester_id" , referencedColumnName = "tester_id"),
            inverseJoinColumns = @JoinColumn(name = "device_id", referencedColumnName = "device_id"))
    private List<Mobile> mobileDevices;

    public Long getTesterId() {
        return testerId;
    }

    public void setTesterId(Long testerId) {
        this.testerId = testerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List<Bug> getBugs() {
        return bugs;
    }

    public void setBugs(List<Bug> bugs) {
        this.bugs = bugs;
    }

    public List<Mobile> getMobileDevices() {
        return mobileDevices;
    }

    public void setMobileDevices(List<Mobile> devices) {
        this.mobileDevices = devices;
    }
}


//public class Professor {
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "ALUNO_PROFESSOR",
//            joinColumns = @JoinColumn(name = "idProfessor", referencedColumnName = "idProfessor"),
//            inverseJoinColumns = @JoinColumn(name = "idAluno", referencedColumnName = "idAluno"))
//    private List<Aluno> alunoList;
//}
//
//public class Aluno {
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "ALUNO_PROFESSOR",
//            joinColumns = @JoinColumn(name = "idAluno", referencedColumnName = "idAluno"),
//            inverseJoinColumns = @JoinColumn(name = "idProfessor", referencedColumnName = "idProfessor"))
//    private List<Professor> professorList;
//}