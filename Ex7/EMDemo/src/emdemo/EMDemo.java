/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package emdemo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Address;
import model.AddressTable;
import model.Customer;
import model.CustomerTable;


/**
 *
 * @author DoubleX
 */
public class EMDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //createData();
        printAllCustomer();
        printCustomerByCity("Bangkok");
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EMDemoPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
        public static void createData(){
        Customer cus1 = new Customer((long) 1,"John","Henry","jh@mail.com");
        Customer cus2 = new Customer((long) 2,"Marry","Jane","mj@mail.com");
        Customer cus3 = new Customer((long) 3,"Peter","Parker","pp@mail.com");
        Customer cus4 = new Customer((long) 4,"Bruce","Wayn","bw@mail.com");
        CustomerTable.insertCustomer(cus1);
        CustomerTable.insertCustomer(cus2);
        CustomerTable.insertCustomer(cus3);
        CustomerTable.insertCustomer(cus4);
        Address add1 = new Address((long) 1,"123/4 Viphavadee Rd.","Bangkok","10900","TH",cus1);
        Address add2 = new Address((long) 2,"123/5 Viphavadee Rd.","Bangkok","10900","TH",cus2);
        Address add3 = new Address((long) 3,"543/21 Fake Rd.","Nonthaburi","20900","TH",cus3);
        Address add4 = new Address((long) 4,"678/90 Unreal Rd.","Pathumthani","30500","TH",cus4);
        AddressTable.insertAddress(add1);
        AddressTable.insertAddress(add2);
        AddressTable.insertAddress(add3);
        AddressTable.insertAddress(add4);
        
        
    }
    public static void printAllCustomer() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EMDemoPU");
        EntityManager em = emf.createEntityManager();
        List<Customer> cusList = (List<Customer>) em.createNamedQuery("Customer.findAll").getResultList();
        for(Customer customer : cusList) {
            System.out.println("First Name: " + customer.getFirstname());
            System.out.println("Last Name: " + customer.getLastname());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Street: " + customer.getAddressId().getStreet());
            System.out.println("City: " + customer.getAddressId().getCity());
            System.out.println("Country: " + customer.getAddressId().getCountry());
            System.out.println("Zip Code: " + customer.getAddressId().getZipcode());
            System.out.println("-----------------------------");
            System.out.println("-----------------------------");
        }
        em.close();
    }
    
    
    public static void printCustomerByCity(String city){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EMDemoPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Address.findByCity");
        query.setParameter("city", city);
        List<Address> addList = (List<Address>) query.getResultList();     
        for(Address address : addList) {
            System.out.println("First Name: " + address.getCustomerFk().getFirstname());
            System.out.println("Last Name: " + address.getCustomerFk().getLastname());
            System.out.println("Email: " + address.getCustomerFk().getEmail());
            System.out.println("Street: " + address.getCustomerFk().getAddressId().getStreet());
            System.out.println("City: " + address.getCustomerFk().getAddressId().getCity());
            System.out.println("Country: " + address.getCustomerFk().getAddressId().getCountry());
            System.out.println("Zip Code: " + address.getCustomerFk().getAddressId().getZipcode());
            System.out.println("-----------------------------");
            System.out.println("-----------------------------");
        }
        em.close();
    }
}
