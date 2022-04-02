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
import model.Colaborador;
import model.Veiculo;
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
			String sql = "select * from chamado c join colaborador colab on c.colaborador_idColaborador = colab.idColaborador"
					+ " join veiculo v on c.veiculo_idVeiculo = v.idVeiculo;";
			
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

				Veiculo v = new Veiculo();
				v.setId(rs.getInt("idVeiculo"));
				v.setModelo(rs.getString("modelo"));
				v.setPlaca(rs.getString("placa"));
				v.setKmLitro(rs.getDouble("km_litro"));
				v.setDisponivel(rs.getBoolean("disponivel"));
				
				Colaborador colab = new Colaborador(rs.getString("nome"));
				colab.setHabilitado(rs.getBoolean("habilitado"));
				colab.setId(rs.getInt("idColaborador"));
				
				c.setVeiculo(v);
				c.setColaborador(colab);
				
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
			String sql = "select * from chamado c join colaborador colab on c.colaborador_idColaborador = colab.idColaborador"
					+ " join veiculo v on c.veiculo_idVeiculo = v.idVeiculo where idChamado = ?;";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				c.setId(rs.getInt("idChamado"));
				c.setDataInicio((rs.getDate("data_inicio").toLocalDate()));
				c.setConcluido(rs.getBoolean("concluido"));
				c.setEndereco(rs.getString("endereco"));
				c.setDistancia(rs.getDouble("distancia"));
				c.setPegadaCarbono(rs.getDouble("carbono"));
				
				Veiculo v = new Veiculo();
				v.setId(rs.getInt("idVeiculo"));
				v.setModelo(rs.getString("modelo"));
				v.setPlaca(rs.getString("placa"));
				v.setKmLitro(rs.getDouble("km_litro"));
				v.setDisponivel(rs.getBoolean("disponivel"));
				
				Colaborador colab = new Colaborador(rs.getString("nome"));
				colab.setHabilitado(rs.getBoolean("habilitado"));
				colab.setId(rs.getInt("idColaborador"));
				
				c.setVeiculo(v);
				c.setColaborador(colab);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
}
