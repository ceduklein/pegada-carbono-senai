package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Chamado;
import util.ConnectionUtil;

public class ChamadoDao {

	private static ChamadoDao instance;
	private Connection con = ConnectionUtil.getConnection();
	
	public static ChamadoDao getInstance() {
		if (instance == null) {
			instance = new ChamadoDao();
		}
		return instance;
	}
	
	public void criarChamado(Chamado chamado) {
		try {
			String sql = "insert into chamado (data_inicio, endereco, distancia, carbono, concluido,"
					+ "colaborador_idColaborador, veiculo_idVeiculo) values (?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, java.sql.Date.valueOf(chamado.getDataInicio()));
			pstmt.setString(2, chamado.getEndereco());
			pstmt.setDouble(3, chamado.getDistancia());
			pstmt.setDouble(4, chamado.getPegadaCarbono());
			pstmt.setBoolean(5, chamado.isConcluido());
			pstmt.setInt(6, chamado.getColaborador().getId());
			pstmt.setInt(7, chamado.getVeiculo().getId());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(Chamado chamado) {
		try {
			String sql = "update chamado set data_inicio = ?, endereco = ?, distancia = ?, carbono = ?, "
					+ "colaborador_idColaborador = ?, veiculo_idVeiculo = ?, concluido = ? where idChamado = ?;";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, Date.valueOf(chamado.getDataInicio()));
			pstmt.setString(2, chamado.getEndereco());
			pstmt.setDouble(3, chamado.getDistancia());
			pstmt.setDouble(4, chamado.getPegadaCarbono());
			pstmt.setInt(5, chamado.getColaborador().getId());
			pstmt.setInt(6, chamado.getVeiculo().getId());
			pstmt.setBoolean(7, chamado.isConcluido());
			pstmt.setInt(8, chamado.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(int id) {
		try {
			String sql = "delete from chamado where idChamado = ?;";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Chamado> listar() {
		List<Chamado> listaChamados = new ArrayList<>();
		
		try {
			String sql = "select * from chamado;";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Chamado c = new Chamado();
				c.setId(rs.getInt("idChamado"));
				c.setDataInicio((rs.getDate("data_inicio").toLocalDate()));
				c.setConcluido(rs.getBoolean("concluido"));
				c.setEndereco(rs.getString("endereco"));
				c.setDistancia(rs.getDouble("distancia"));
				c.setPegadaCarbono(rs.getDouble("carbono"));
//				c.getColaborador().setId(rs.getInt("colaborador_idColaborador"));
//				c.getVeiculo().setId(rs.getInt("veiculo_idVeiculo"));
				listaChamados.add(c);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaChamados;
	}
	
	public Chamado listById(int id) {
		Chamado c = new Chamado();
		
		try {
			String sql = "select * from chamado;";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getInt("idChamado") == id) {
					c.setId(rs.getInt("idChamado"));
					c.setDataInicio((rs.getDate("data_inicio").toLocalDate()));
					c.setConcluido(rs.getBoolean("concluido"));
					c.setEndereco(rs.getString("endereco"));
					c.setDistancia(rs.getDouble("distancia"));
					c.setPegadaCarbono(rs.getDouble("carbono"));
//					c.getColaborador().setId(rs.getInt("colaborador_idColaborador"));
//					c.getVeiculo().setId(rs.getInt("veiculo_idVeiculo"));
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
}
