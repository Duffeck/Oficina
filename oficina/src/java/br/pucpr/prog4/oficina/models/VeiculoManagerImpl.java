/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpr.prog4.oficina.models;

import br.pucpr.prog4.oficina.dao.DaoManager;
import br.pucpr.prog4.oficina.dao.JdbcDaoManager;
import br.pucpr.prog4.oficina.dao.JdbcVeiculoDao;
import br.pucpr.prog4.oficina.dao.VeiculoDao;

/**
 *
 * @author lucas.duffeck
 */
public class VeiculoManagerImpl implements VeiculoManager{

    @Override
    public Veiculo cadastrar(Veiculo veiculo) {
        DaoManager mg;
        mg = new JdbcDaoManager();
        
        try{
            mg.iniciar();
            VeiculoDao dao = mg.getVeiculoDao();
            Veiculo veic =  dao.inserir(veiculo);
            mg.confirmarTransação();
            mg.encerrar();
            
            return veic;
        }catch(Exception e){
            mg.abortarTransação();
            throw e;
        }
    }
    
}
