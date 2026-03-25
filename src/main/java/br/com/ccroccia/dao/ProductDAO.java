package br.com.ccroccia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ccroccia.domain.Product;
import br.com.ccrocia.dao.generic.jdbc.ConnectionFactory;

public class ProductDAO implements IProductDao {

	@Override
	public Integer save(Product product) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlInsert();
			stm = connection.prepareStatement(sql);
			getInsertData(stm, product);
			return stm.executeUpdate();
		} catch(Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, null);
		}
	}

	private String getSqlInsert() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO PRODUTO (cd_produto, ds_produto, qtd_produto) ");
		sb.append("VALUES (?, ?, ?)");
		return sb.toString();
	}

	private void getInsertData(PreparedStatement stm, Product product) throws SQLException {
		stm.setInt(1, product.getId());
		stm.setString(2, product.getDescription());
		stm.setInt(3, product.getQuantity());
	}

	@Override
	public Product findById(Integer id) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Product product = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlSelect();
			stm = connection.prepareStatement(sql);
			getSelectData(stm, id);
			rs = stm.executeQuery();

			if(rs.next()) {
				product = new Product();
				product.setId(rs.getInt("cd_produto"));
				product.setDescription(rs.getString("ds_produto"));
				product.setQuantity(rs.getInt("qtd_produto"));
			}

		} catch(Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, rs);
		}
		return product;
	}

	private void getSelectData(PreparedStatement stm, Integer id) throws SQLException {
		stm.setInt(1, id);
	}

	private String getSqlSelect() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM PRODUTO ");
		sb.append("WHERE cd_produto = ?");
		return sb.toString();
	}

	@Override
	public Integer delete(Integer id) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = deleteSqlUpdate();
			stm = connection.prepareStatement(sql);
			getDeleteData(stm, id);
			return stm.executeUpdate();
		} catch(Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, null);
		}
	}

	private void getDeleteData(PreparedStatement stm, Integer id) throws SQLException {
		stm.setInt(1, id);
	}

	private String deleteSqlUpdate() {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM PRODUTO WHERE ");
		sb.append("cd_produto = ?");
		return sb.toString();
	}

	@Override
	public Integer update(Product product) throws SQLException {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlUpdate();
			stm = connection.prepareStatement(sql);
			getUpdateData(stm, product);
			return stm.executeUpdate();
		} catch(Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, null);
		}
	}

	private void getUpdateData(PreparedStatement stm, Product product) throws SQLException {
		stm.setString(1, product.getDescription());
		stm.setInt(2, product.getQuantity());
		stm.setInt(3, product.getId());
	}

	private String getSqlUpdate() {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE PRODUTO SET ");
		sb.append("ds_produto = ?, qtd_produto = ? ");
		sb.append("WHERE cd_produto = ?");
		return sb.toString();
	}

	private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) throws SQLException {
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Product> getAll() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs          = null;
		Product product       = null;
		List<Product> list    = new ArrayList<>();

		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSelectAllSql();
			stm        = connection.prepareStatement(sql);
			rs         = stm.executeQuery();
			while(rs.next()) {
				product = new Product();
				product.setId(rs.getInt("cd_produto"));
				product.setDescription(rs.getString("ds_produto"));
				product.setQuantity(rs.getInt("qtd_produto"));
				list.add(product);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, rs);
		}
		return list;
	}

	private String getSelectAllSql() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM PRODUTO");
		return sb.toString();
	}

}
