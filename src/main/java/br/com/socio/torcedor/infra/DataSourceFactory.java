package br.com.socio.torcedor.infra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;
/**
 * 
 * @author Luciano
 *
 */
public class DataSourceFactory {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DataSourceFactory.class);

	public static DataSource createDataSource() {

		DataSourceConfiguration dataSource = new DataSourceConfiguration()
				.driver("com.mysql.jdbc.Driver")
				.url("jdbc:mysql://localhost:3306/desafio")
				.userData("root", "root");

		return create(dataSource);

	}

	public static DataSource create(final DataSourceConfiguration config) {

		final BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(config.getDriver());
		dataSource.setUrl(config.getUrl());
		dataSource.setUsername(config.getUsername());
		dataSource.setPassword(config.getPassword());
		
		if (LOGGER.isDebugEnabled()) {
			testConnection(dataSource);
		}

		return dataSource;
	}

	/**
	 * Efetua alguns testes no DataSource criado, verificando sua
	 * funcionalidade.
	 *
	 * @param dataSource
	 *            DataSource testado
	 * @param walletLocation
	 *            Localização da Wallet
	 * @param tnsAdmin
	 *            TNS Admin
	 */
	private static void testConnection(final DataSource dataSource) {
		LOGGER.debug("  Driver Class: " + dataSource + "  URL: " + dataSource + " ");

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			LOGGER.debug("Creating connection");

			con = DataSourceUtils.getConnection(dataSource);
			LOGGER.debug(con.toString());
			ps = con.prepareStatement("select SYSDATE from dual");
			ps.execute();
			rs = ps.getResultSet();
			rs.next();
			LOGGER.debug("Data da Conexao: " + rs.getDate(1));

			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(ps);

			LOGGER.debug(con.toString());
			ps = con.prepareStatement("select instance_name, host_name from v$instance");
			ps.execute();
			rs = ps.getResultSet();
			rs.next();
			LOGGER.debug("Conectado a instancia: " + rs.getString(1));
			LOGGER.debug("Conectado ao host: " + rs.getString(2));

		} catch (SQLException e) {
			LOGGER.error("Falha ao testar a conexao", e);

		} finally {
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(ps);
			DataSourceUtils.releaseConnection(con, dataSource);
		}
	}

	private static class DataSourceConfiguration {

		private String driver;
		private String walletLocation;
		private String tnsAdmin;
		private String url;
		private String username;
		private String password;
		public Integer maxPoolSize;
		public Integer minPoolSize;

		public String getDriver() {
			return driver;
		}

		public String getWalletLocation() {
			return walletLocation;
		}

		public String getTnsAdmin() {
			return tnsAdmin;
		}

		public String getUrl() {
			return url;
		}

		public String getUsername() {
			return username;
		}

		public String getPassword() {
			return password;
		}

		public Integer getMaxPoolSize() {
			return maxPoolSize;
		}

		public Integer getMinPoolSize() {
			return minPoolSize;
		}

		public DataSourceConfiguration driver(String driver) {
			this.driver = driver;
			return this;
		}

		public DataSourceConfiguration url(String url) {
			this.url = url;
			return this;
		}

		public DataSourceConfiguration userData(String user, String pass) {
			this.username = user;
			this.password = pass;
			return this;
		}

		public DataSourceConfiguration poolSize(Integer maxPool, Integer minPool) {
			this.maxPoolSize = maxPool;
			this.minPoolSize = minPool;
			return this;
		}

		public DataSourceConfiguration wallet(String location, String admin) {
			this.walletLocation = location;
			this.tnsAdmin = admin;
			return this;
		}

	}
}
