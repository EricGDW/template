package com.itucity.dsmp.common.base;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;

import com.itucity.dsmp.common.base.impl.ProcedureParam;

/**
 * <p>Dao顶级接口，所有Dao类（接口）必须实现（继承）此接口</p>
 */
public interface IDao {


    /**
     * 通过主键查找实体对象
     * @param <T> 参数c代表的类型
     * @param c 实体类型（实体对象的Class对象）
     * @param pk 主键
     * @return 实体对象
     */
	<T> T find(Class<T> c, Serializable pk);
    
	/**
	 * 新增实体对象
	 * @param entity 实体对象
	 */
    void save(Serializable entity);
    
    /**
     * 修改实体对象
     * @param entity 实体对象
     */
    void update(Serializable entity);
    
    /**
     * 删除实体对象
     * @param entity 实体对象
     */
    void delete(Serializable entity);
    
    /** HQL查询
     * @param <E> 根据HQL返回不同的类型
     * @param hql hql查询语句
     * @return 返回一个List<Object>的集合，当无查询结果时返回size为0的List<Object>集合
     */
    <E> List<E> hqlQuery(String hql);
    
    /** HQL查询
     * @param <E> 根据HQL返回不同的类型
     * @param hql hql查询语句
     * @param parameters 参数
     * @return 返回一个List<Object>的集合，当无查询结果时返回size为0的List<Object>集合
     */
    <E> List<E> hqlQuery(String hql, Hashtable<String, Object> parameters);
    
    /** HQL增加、删除、修改
     * @param hql hql语句
     * @return 操作生效的记录数
     */
    int hqlUpdate(String hql);
    
    /**
     * HQL增加、删除、修改
     * @param hql hql语句
     * @param parameters 参数
     * @return 操作生效的记录数
     */
    int hqlUpdate(String hql, Hashtable<String,Object> parameters);
    

    /** 原生SQL查询
     * @param <E> 根据SQL返回不同的类型
     * @param sql sql查询语句
     * @return 返回一个List<Object>的集合，当无查询结果时返回size为0的List<Object>集合
     */
    <E> List<E> sqlQuery(String sql);
    
    /** 原生SQL查询
     * @param <E> 根据SQL返回不同的类型
     * @param sql sql查询语句
     * @param parameters 参数
     * @return 返回一个List<Object>的集合，当无查询结果时返回size为0的List<Object>集合
     */
    <E> List<E> sqlQuery(String sql, Hashtable<String, Object> parameters);
    
    /** 原生SQL增加、删除、修改
     * @param sql sql语句
     * @return 操作生效的记录数
     */
    int sqlUpdate(String sql);
    
    /**
     * 原生SQL增加、删除、修改
     * @param sql sql语句
     * @param parameters 参数
     * @return 操作生效的记录数
     */
    int sqlUpdate(String sql, Hashtable<String,Object> parameters);
    
    /** HQL Cache查询
     * @param <E> 根据HQL返回不同的类型
     * @param hql hql查询语句
     * @return 返回一个List<Object>的集合，当无查询结果时返回size为0的List<Object>集合
     */
    <E> List<E> hqlCacheQuery(String hql);
    
    /** HQLCache查询
     * @param <E> 根据HQL返回不同的类型
     * @param hql hql查询语句
     * @param parameters 参数
     * @return 返回一个List<Object>的集合，当无查询结果时返回size为0的List<Object>集合
     */
    <E> List<E> hqlCacheQuery(String hql, Hashtable<String, Object> parameters);

    /**
     * 调用存储过程
     * @param spName 存储过程名
     * @param params 参数列表(请使用ProcedureParam.newInParam或ProcedureParam.newOutParam创建参数)
     * @return 是否成功(out参数返回到params对应的参数中)
     */
	boolean callProc(String spName, LinkedHashMap<String, ProcedureParam> params);
    
}
