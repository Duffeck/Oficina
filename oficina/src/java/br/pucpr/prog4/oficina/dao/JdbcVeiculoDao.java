/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpr.prog4.oficina.dao;

import br.pucpr.prog4.oficina.models.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author lucas.duffeck
 */
public class JdbcVeiculoDao implements VeiculoDao{
    
    private Connection conexão;
    
    public JdbcVeiculoDao(Connection conexão){
        this.conexão = conexão;
    }
    @Override
    public Veiculo inserir(Veiculo veiculo) {
        String sql = "INSERT INTO veiculo "
                  + " (tipo, "
                  + " placa)"
                  + " VALUES "
                  + " (?, ?)";
            
            PreparedStatement ps;
        try{
            ps = conexão.prepareStatement(sql);
            ps.setString(1, veiculo.getTipo());
            ps.setString(2, veiculo.getPlaca());
            ps.executeUpdate();
            
            return veiculo;
        }catch(SQLException e){
            throw new DaoException("Erro ao inserir veículo no banco de dados!");
        }
    }
    
}
