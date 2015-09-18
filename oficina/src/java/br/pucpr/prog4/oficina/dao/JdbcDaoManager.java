/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpr.prog4.oficina.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lucas.duffeck
 */
public class JdbcDaoManager implements DaoManager{
    
    Connection conexão;
    @Override
    public void iniciar() throws DaoException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url;
            url = "jdbc:mysql://localhost:3306/oficina";
            conexão = DriverManager.getConnection(url, "root", "root");
            
        }catch(Exception e){
            throw new DaoException("Ocorreu um erro ao conectar ao banco de dados!" + e.getMessage());
        }
    }

    @Override
    public void encerrar() 
    {
        try {
            if(!conexão.isClosed())
                conexão.close();
        } catch (SQLException e) {
            
        }
    }

    @Override
    public void confirmarTransação() {
        try{
            conexão.commit();
        }catch(SQLException e){
            
        }
    }

    @Override
    public void abortarTransação() {
        try{
            conexão.rollback();
        }catch(SQLException e){
            throw new DaoException("Erro ao confirmar transação!");
        }
    }

    @Override
    public VeiculoDao getVeiculoDao() {
        return new JdbcVeiculoDao(conexão);
    }
    
}
