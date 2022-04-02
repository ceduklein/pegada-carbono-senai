package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Colaborador;
import util.ConnectionUtil;

public class ColaboradorDao {

	private static ColaboradorDao instance;
	private Connection con = ConnectionUtil.getConnection();
	
	public static ColaboradorDao getInstance() {
		if (instance == null) {
			instance = new ColaboradorDao();
		}
		return instance;
	}
	
	public void salvar(Colaborador colaborador) {
		try {
			String sql = "insert into colaborador (nome, habilitado) values (?, ?);";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, colaborador.getNome());
			pstmt.setBoolean(2, colaborador.isHabilitado());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(Colaborador colaborador) {
		try {
			String sql = "update colaborador set nome = ?, habilitado = ? where idColaborador = ?;";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, colaborador.getNome());
			pstmt.setBoolean(2, colaborador.isHabilitado());
			pstmt.setInt(3, colaborador.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(int id) {
		try {
			String sql = "delete from colaborador where idColaborador = ?;";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Colaborador> listar() {
		List<Colaborador> listaColaboradores = new ArrayList<>();
		
		try {
			String sql = "select * from colaborador;";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Colaborador c = new Colaborador();
				c.setId(rs.getInt("idColaborador"));
				c.setNome(rs.getString("nome"));
				c.setHabilitado(rs.getBoolean("habilitado"));
				listaColaboradores.add(c);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaColaboradores;
	}
	
	public Colaborador listById(int id) {
		Colaborador c = new Colaborador();
		
		try {
			String sql = "select * from colaborador where idColaborador = ?;";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				c.setId(rs.getInt("idColaborador"));
				c.setNome(rs.getString("nome"));
				c.setHabilitado(rs.getBoolean("habilitado"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
}
