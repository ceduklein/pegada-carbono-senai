package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Veiculo;
import util.ConnectionUtil;

public class VeiculoDao {

	private static VeiculoDao instance;
	private Connection con = ConnectionUtil.getConnection();
	
	public static VeiculoDao getInstance() {
		if (instance == null) {
			instance = new VeiculoDao();
		}
		return instance;
	}
	
	public void salvar(Veiculo veiculo) {
		try {
			String sql = "insert into veiculo (modelo, placa, km_litro, disponivel) values (?, ?, ?, ?);";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, veiculo.getModelo());
			pstmt.setString(2, veiculo.getPlaca());
			pstmt.setDouble(3, veiculo.getKmLitro());
			pstmt.setBoolean(4, veiculo.isDisponivel() );
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(Veiculo veiculo) {
		try {
			String sql = "update veiculo set modelo = ?, placa = ?, km_litro = ?, disponivel = ? where idVeiculo = ?;";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, veiculo.getModelo());
			pstmt.setString(2, veiculo.getPlaca());
			pstmt.setDouble(3, veiculo.getKmLitro());
			pstmt.setBoolean(4, veiculo.isDisponivel());
			pstmt.setInt(5, veiculo.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(int id) {
		try {
			String sql = "delete from veiculo where idVeiculo = ?;";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Veiculo> listar() {
		List<Veiculo> listaVeiculos = new ArrayList<>();
		try {
			String sql = "select * from veiculo;";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Veiculo v = new Veiculo();
				v.setId(rs.getInt("idVeiculo"));
				v.setModelo(rs.getString("modelo"));
				v.setPlaca(rs.getString("placa"));
				v.setKmLitro(rs.getDouble("km_litro"));
				v.setDisponivel(rs.getBoolean("disponivel"));
				listaVeiculos.add(v);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaVeiculos;
	}
	
	public Veiculo listById(int id) {
		Veiculo v = new Veiculo();
		try {
			String sql = "select * from veiculo where idVeiculo = ?;";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				v.setId(rs.getInt("idVeiculo"));
				v.setModelo(rs.getString("modelo"));
				v.setPlaca(rs.getString("placa"));
				v.setKmLitro(rs.getDouble("km_litro"));
				v.setDisponivel(rs.getBoolean("disponivel"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}
	
}
