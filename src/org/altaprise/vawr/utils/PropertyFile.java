/*******************************************************************************
 *
 * Copyright 2015 Stephen Furlong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
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
    
    public void du () {
        
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
            System.out.println("proerty file not found.");
            fnfe.printStackTrace();
        } catch (InvalidClassException ice) {
            System.out.println("property file class version mismatch. You must recreate Application Settings");
            if (SessionMetaData.getInstance().debugOn()) {
                ice.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.serializeIt();
        }
    }

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

    public void removeDBConnectionProp(String connName) {

        _theInstance._propertyData.dbConnectInfo.remove(connName);
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
