package org.altaprise.vawr.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PropertyFile implements Serializable {

    private PropertyData _propertyData = new PropertyData();
    private static PropertyFile _theInstance = null;

    private PropertyFile() {
    }

    public static PropertyFile getInstance() {
        if (_theInstance == null) {
            _theInstance = new PropertyFile();
            _theInstance.deSerializeIt();
        }
        return _theInstance;
    }

    public void serializeIt() {
        try {

            FileOutputStream fos = new FileOutputStream("properties.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(_theInstance._propertyData);
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deSerializeIt() {

        try {

            FileInputStream fis = new FileInputStream("properties.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            _theInstance._propertyData = (PropertyData)ois.readObject();
            ois.close();

            System.out.println(_theInstance._propertyData);
        } catch (FileNotFoundException fnfe) {
            System.out.println("file not found.");
            fnfe.printStackTrace();
        } catch (InvalidClassException ice) {
            System.out.println("property file class version mismatch.");
            ice.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            this.serializeIt();
        }
    }
    /*
    Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            System.out.println(pairs.getKey() + " = " + pairs.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
*/

    public ArrayList<DBConnectionProps> getDBConnectionProps() {
        ArrayList<DBConnectionProps> ret = new ArrayList<DBConnectionProps>();
        Iterator it = _theInstance._propertyData.dbConnectInfo.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry kvPair = (Map.Entry)it.next();
            kvPair.getKey();
            kvPair.getValue();
            ret.add((DBConnectionProps)kvPair.getValue());
            //it.remove();
        }
        return ret;

    }

    public DBConnectionProps getDBConnectionProps(String dbConnName) {

        return (DBConnectionProps)_theInstance._propertyData.dbConnectInfo.get(dbConnName);
    }

    public void addDBConnectionProps(DBConnectionProps props) {

        _theInstance._propertyData.dbConnectInfo.put(props.connectionName, props);
    }

    public void dumpProps() {
        _theInstance._propertyData.dump();
    }

    public static void main(String[] args) {
        PropertyFile propertyFile = PropertyFile.getInstance();
        DBConnectionProps dbConnProps = new DBConnectionProps();

        propertyFile.dumpProps();
        dbConnProps.setConnectionName("conn1");
        propertyFile.addDBConnectionProps(dbConnProps);
        System.out.println("list size: " +
                           propertyFile.getDBConnectionProps().size());
        propertyFile.serializeIt();

        System.out.println(propertyFile.getDBConnectionProps().size());
    }

    private class PropertyData implements Serializable {

        private HashMap dbConnectInfo = new HashMap();

        public void dump() {
            System.out.println("num db connections: " + dbConnectInfo.size());
            Iterator it = dbConnectInfo.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry kvPair = (Map.Entry)it.next();
                System.out.println(kvPair.getKey() + ", " + kvPair.getValue());
            }
        }

    }
}
