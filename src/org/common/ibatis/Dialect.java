package org.common.ibatis;

public interface Dialect {
	 //是否分页
	 public boolean supportsLimit();  
	 //分页语句的拼接
	 public String getLimitString(String sql, boolean hasOffset);  
	  
	 public String getLimitString(String sql, int offset, int limit);  
	 //获取数据总数
	 public String getCountString(String sql);
}
