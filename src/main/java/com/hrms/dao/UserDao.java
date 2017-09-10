package com.hrms.dao;

import java.sql.SQLException;

/**
 * @author Ricky
 * This interface will be used to communicate with the
 * Database
 */
public interface UserDao
{
		public boolean isValidUser(String username, String password) throws SQLException;
}
