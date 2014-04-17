package com.itucity.dsmp.common.base.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.itucity.dsmp.common.Constants;
import com.itucity.dsmp.common.base.IDao;
import com.itucity.dsmp.common.exception.DBConnectException;
import com.itucity.dsmp.common.exception.InvalidArgumentsException;
import com.itucity.dsmp.common.page.PagesInfo;


/**
 * 基础Dao类（抽象类），所有Dao类必须继承此类
 */
@Transactional
public abstract class BaseDao implements IDao {
	
	@Value("${errorNoResult}")
	public String errorNoResult;

	@Autowired
	@Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    /** 
     * getSession
     * @return
     */
    public Session getSession() {
        //事务必须是开启的(Required)，否则获取不到
        return sessionFactory.getCurrentSession();
    }
    
    
    @SuppressWarnings("unchecked")
	public <T> T find(Class<T> c, Serializable pk) {
    	T obj = (T) getSession().get(c, pk);
		return obj;
	}

	public void save(Serializable entity) {
		getSession().persist(entity);
	}

	public void update(Serializable entity) {
		getSession().update(entity);
	}
	
    public void delete(Serializable entity) {
    	getSession().delete(entity);
	}
    
    public <E> List<E> hqlQuery(String hql) {
    	return hqlQuery(hql, null);
    }
    
    public <E> List<E> hqlQuery(String hql, Hashtable<String,Object> parameters){
    	
    	return this.hqlQuery(hql,parameters,0,0);
	}
    
    public <E> List<E> hqlQuery(String jpql,int firstResult,int maxResults){
		return this.hqlQuery(jpql,null,firstResult,maxResults);
	}
	
	@SuppressWarnings("unchecked")
	public <E> List<E> hqlQuery(String hql,Hashtable<String,Object> parameters,int firstResult,int maxResults){
		if(!StringUtils.hasText(hql)){
			throw new InvalidArgumentsException(Constants.SYS001);
		}
		
		try{
			List<E> list = new ArrayList<E>();
			
	    	Query query = getSession().createQuery(hql);
	    	
	    	if(parameters!=null){
	    		for (String key : parameters.keySet()) {
	    			Object parameter = parameters.get(key);
	    			if (parameter instanceof Collection) {
	    				query.setParameterList(key, (Collection<?>) parameter);
	    			} else {
	    				query.setParameter(key, parameter);
	    			}
	    		}
	    		
	    	/*	for(Map.Entry<String, Object> entry: parameters.entrySet()){
	    			
	    			query.setParameter(entry.getKey(), entry.getValue());
	    			
	    		}*/
			}
			
			if(firstResult>=0 && maxResults>0){
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);				
			}
			list = query.list();
			return list;
		}catch (IllegalArgumentException e) {
			throw new InvalidArgumentsException(Constants.SYS001,e);
		}catch (Exception e) {
			throw new DBConnectException(Constants.SYS002, e);
		}
	}
	
	public <T> PagesInfo<T> hqlPageQuery(String hql,PagesInfo<T> pagesInfo){
		return this.hqlPageQuery(hql, null, pagesInfo);
	}
	
	@SuppressWarnings("unchecked")
	public <T> PagesInfo<T> hqlPageQuery(String hql,Hashtable<String,Object> parameters,PagesInfo<T> pagesInfo){
		if(pagesInfo==null){
			pagesInfo=new PagesInfo<T>();
			pagesInfo.setCountPerPage(5);
			pagesInfo.setPageNumber(1);
		}
		if(!StringUtils.hasText(hql) || pagesInfo.getCountPerPage()<=0 || pagesInfo.getPageNumber()<=0){
			throw new InvalidArgumentsException(Constants.SYS001);
		}
		long totalRecord=pagesInfo.getTotalRecord();
		if(totalRecord==0){
			String tempJpql=hql.toLowerCase();
		
			String oldtempJpql="";
			if(tempJpql.contains("left")){
				
				if(tempJpql.contains("pkcount")){
					
					
					String countFiled =hql.toString().substring(hql.toString().trim().indexOf("left")-1,hql.toString().trim().indexOf("left"))+ hql.toString().substring(hql.toString().trim().indexOf("pkcount")+"pkcount".length()+1);
					
					hql = hql.toString().substring(0,hql.toString().trim().indexOf("pkcount"));
					
					oldtempJpql =com.itucity.dsmp.common.util.StringUtils.replace(hql,0,tempJpql.indexOf("from"),"select   count(distinct "+countFiled+") ").replace("fetch", "");
					
					
					
					
				}else{
					oldtempJpql =com.itucity.dsmp.common.util.StringUtils.replace(hql,0,tempJpql.indexOf("from"),"select   count(distinct "+hql.toString().substring(hql.toString().trim().indexOf("left")-1,hql.toString().trim().indexOf("left"))+") ").replace("fetch", "");
				}
				
				
			
			}else{
				 oldtempJpql =com.itucity.dsmp.common.util.StringUtils.replace(hql,0,tempJpql.indexOf("from"),"select count(distinct t) ").replace("fetch", "");
//				 oldtempJpql =com.itucity.dsmp.common.util.StringUtils.replace(hql,0,tempJpql.indexOf("from"),"select count(*) ").replace("fetch", "");
			}
			
			
			if(-1!=oldtempJpql.indexOf("group")){
				
				
				totalRecord=this.hqlQuery(
						oldtempJpql, 
						parameters
				   ).size();
				
			}else{
				totalRecord=(Long)this.hqlQuery(
						oldtempJpql, 
						parameters
				   ).get(0);
				
			}
			
			
			pagesInfo.setTotalRecord(totalRecord);
		}
		pagesInfo.setTotalPage(totalRecord/(pagesInfo.getCountPerPage()*1.0)>(long)(totalRecord/pagesInfo.getCountPerPage())?(long)(totalRecord/pagesInfo.getCountPerPage())+1:(long)(totalRecord/pagesInfo.getCountPerPage()));
		pagesInfo.setResultsList((List<T>)this.hqlQuery(hql, parameters, (pagesInfo.getPageNumber()-1)*pagesInfo.getCountPerPage(), pagesInfo.getCountPerPage()));
		return pagesInfo;		
	}

    public int hqlUpdate(String hql){
    	return hqlUpdate(hql, null);
    }
    
    public int hqlUpdate(String hql, Hashtable<String,Object> parameters){
    	Query query = getSession().createQuery(hql);
    	if(parameters!=null){
    		for (String key : parameters.keySet()) {
    			Object parameter = parameters.get(key);
    			if (parameter instanceof Collection) {
    				query.setParameterList(key, (Collection<?>) parameter);
    			} else {
    				query.setParameter(key, parameter);
    			}
    		}
    		/*for(Map.Entry<String, Object> entry: parameters.entrySet()){
    			query.setParameter(entry.getKey(), entry.getValue());
    		}*/
		}
    	int result = query.executeUpdate();
		return result;
	}
    
    
    public <E> List<E> sqlQuery(String sql) {
    	return sqlQuery(sql, null);
    }
    
    public <E> List<E> sqlQuery(String sql, Hashtable<String,Object> parameters){
    	return this.sqlQuery(sql,parameters,0,0);
	}
    
    public <E> List<E> sqlQuery(String sql,int firstResult,int maxResults){
		return this.sqlQuery(sql,null,firstResult,maxResults);
	}
    
    
    @SuppressWarnings("unchecked")
    public <E> List<E> sqlQuery(String sql, Hashtable<String,Object> parameters,int firstResult,int maxResults){
    	
    	if(!StringUtils.hasText(sql)){
			throw new InvalidArgumentsException(Constants.SYS001);
		}
    	try {
			
    		List<E> list = new ArrayList<E>();
        	SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        	if(parameters!=null){
        		for (String key : parameters.keySet()) {
	    			Object parameter = parameters.get(key);
	    			if (parameter instanceof Collection) {
	    				sqlQuery.setParameterList(key, (Collection<?>) parameter);
	    			} else {
	    				sqlQuery.setParameter(key, parameter);
	    			}
	    		}
        		/*for(Map.Entry<String, Object> entry: parameters.entrySet()){
        			sqlQuery.setParameter(entry.getKey(), entry.getValue());
        		}*/
    		}
        	
        	if(firstResult>=0 && maxResults>0){
        		sqlQuery.setFirstResult(firstResult);
        		sqlQuery.setMaxResults(maxResults);
    		}
        	
        	list = sqlQuery.list();
    		return list;
		} catch (Exception e) {
			throw new DBConnectException(Constants.SYS002,e);
		}
    	
    	
    }
    
    
    
    public int sqlUpdate(String sql){
    	return sqlUpdate(sql, null);
    }
    
    public int sqlUpdate(String sql, Hashtable<String,Object> parameters){
    	SQLQuery sqlQuery = getSession().createSQLQuery(sql);
    	if(parameters!=null){
    		for (String key : parameters.keySet()) {
    			Object parameter = parameters.get(key);
    			if (parameter instanceof Collection) {
    				sqlQuery.setParameterList(key, (Collection<?>) parameter);
    			} else {
    				sqlQuery.setParameter(key, parameter);
    			}
    		}
//    		for(Map.Entry<String, Object> entry: parameters.entrySet()){
//    			sqlQuery.setParameter(entry.getKey(), entry.getValue());
//    		}
		}
    	int result = sqlQuery.executeUpdate();
		return result;
	}

	@Override
	public <E> List<E> hqlCacheQuery(String hql) {
		return hqlCacheQuery(hql,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> List<E> hqlCacheQuery(String hql, Hashtable<String, Object> parameters) {
		List<E> list = new ArrayList<E>();
    	Query query = getSession().createQuery(hql);
    	query.setCacheable(true).setCacheRegion("frontpages");
    	if(parameters!=null){
    		for (String key : parameters.keySet()) {
				Object parameter = parameters.get(key);
				if (parameter instanceof Collection) {
					query.setParameterList(key, (Collection<?>) parameter);
				} else {
					query.setParameter(key, parameter);
				}
    		}
    		
//    		for(Map.Entry<String, Object> entry: parameters.entrySet()){
//    			query.setParameter(entry.getKey(), entry.getValue());
//    		}
		}
    	list = query.list();
		return list;
	}
	
	@Override
    public boolean callProc(final String spName, final LinkedHashMap<String, ProcedureParam> params) {
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				String sql="{call "+spName+"}";
				StringBuffer sqlParam=new StringBuffer("");
				if(params!=null) {
					for(Map.Entry<String, ProcedureParam> entry: params.entrySet()){
						ProcedureParam value=entry.getValue();
						if(value.getIsInParam() && value.getValue()==null) {
							if(value.getValue()==null) sqlParam.append("null,");
						} else {
							sqlParam.append("?,");
						}
					}
					sql="{call "+spName+"("+sqlParam.substring(0, sqlParam.length()-1)+")}";
				}
				//声明存储过程参数
				CallableStatement proc=connection.prepareCall(sql);
				if(params!=null){
					int index=1;
					for(Map.Entry<String, ProcedureParam> entry: params.entrySet()){
						ProcedureParam value=entry.getValue();
						if(value.getIsInParam() && value.getValue()==null) continue;
						if(value.getIsInParam()) {
							if(value.getValue() instanceof java.util.Date) 
								proc.setObject(index, new java.sql.Date(((java.util.Date)value.getValue()).getTime()));
							else proc.setObject(index, value.getValue());
						} else {
							proc.registerOutParameter(index, value.getSqlType());
						}
						index++;
					}
				}
				//调用存储过程,取出out参数
				proc.execute();
				if(params!=null){
					int index=1;
					for(Map.Entry<String, ProcedureParam> entry: params.entrySet()){
						String key=entry.getKey();
						ProcedureParam value=entry.getValue();
						if(value.getIsInParam() && value.getValue()==null) continue;
						if(!value.getIsInParam()) {
							Object paramValue=proc.getObject(index);
							value.setValue(paramValue);
							params.put(key, value);
						}
						index++;
					}
				}
			}
		});
		return true;
	}
    
	/**
	 * 生成主键方法
	 * @param ruleExpression 生成主键规则( 例如: to_char(sysdate,'yyyymmddhh24miss')||lpad(SEQ_TM_CAMP.NEXTVAL,4,0) )
	 * @return 主键值
	 */
    protected String generatePrimaryKeyBySequence(String ruleExpression) {
		List<String> list=null;
		list=this.sqlQuery("select "+ruleExpression+"||'' from dual");
		return list==null?"":list.get(0);
	}
    
	
	
	public <T> PagesInfo<T> sqlPageQuery(String sql,PagesInfo<T> pagesInfo){
		return this.sqlPageQuery(sql, null, pagesInfo);
	}
	
	@SuppressWarnings("unchecked")
	public <T> PagesInfo<T> sqlPageQuery(String sql,Hashtable<String,Object> parameterTable,PagesInfo<T> pagesInfo){
		if(pagesInfo==null){
			pagesInfo=new PagesInfo<T>();
			pagesInfo.setCountPerPage(5);
			pagesInfo.setPageNumber(1);
		}
	
		
		if(!StringUtils.hasText(sql) || pagesInfo.getCountPerPage()<=0 || pagesInfo.getPageNumber()<=0){
			throw new InvalidArgumentsException(Constants.SYS001);
		}
		
		long totalRecord=pagesInfo.getTotalRecord();
		if(totalRecord==0){
			totalRecord=((BigDecimal)this.sqlQuery("select count(distinct *) from (" + sql +")").get(0)).longValue();
//			totalRecord=((BigDecimal)this.sqlQuery("select count(*) from (" + sql +")").get(0)).longValue();
			
			pagesInfo.setTotalRecord(totalRecord);
		}
		pagesInfo.setTotalPage(totalRecord/(pagesInfo.getCountPerPage()*1.0)>(long)(totalRecord/pagesInfo.getCountPerPage())?(long)(totalRecord/pagesInfo.getCountPerPage())+1:(long)(totalRecord/pagesInfo.getCountPerPage()));
		
		pagesInfo.setResultsList((List<T>)this.sqlQuery(sql, (pagesInfo.getPageNumber()-1)*pagesInfo.getCountPerPage(), pagesInfo.getCountPerPage()));
		
		return pagesInfo;		
	}
	
	
	
}
