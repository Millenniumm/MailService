package dbtest;

import junit.framework.TestCase;

public class DBTest extends TestCase {

    public void testAddToDB(){
        Account acc = new Account();
        acc.add("Valentin", "Smirnov");
    }
    
    public void testSelectFromDB(){
        Account acc = new Account();
        acc.check("Valentin");
    }
    
    public void testDeleteFromDB(){
        Account acc = new Account();
        acc.delete("Valentin");
    }
    
    public void testUpdateDB(){
        Account acc = new Account();
        acc.newPassword("Valentin", "millennium");
    }
}
