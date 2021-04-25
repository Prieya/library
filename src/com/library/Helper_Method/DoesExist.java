package com.library.Helper_Method;

import com.library.TryConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Comparator;

public class DoesExist {
    private TryConnect database = new TryConnect();
    private Connection connect = null;
    private PreparedStatement statement = null;
    private ResultSet result = null;

    public boolean Exist(String table, int id ){
        boolean exist = false;
        try{
            connect = database.Tryconnection();
            statement = connect.prepareStatement("SELECT * FROM " + table +" WHERE id = " + id);
            result = statement.executeQuery();
            if(result.next()){
                exist = true;
            }
        }catch(Exception e){
            System.out.println("Error searching in " + table);
        }
        return exist;

    }
}
