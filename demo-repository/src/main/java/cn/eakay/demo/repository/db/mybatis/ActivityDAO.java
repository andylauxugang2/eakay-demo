package cn.eakay.demo.repository.db.mybatis;

import cn.eakay.commons.dao.persistence.exception.DAOException;
import cn.eakay.demo.client.dataobject.ActivityDO;

import java.util.List;

/**
 * @description 活动dao 如果需要垮库访问，可以使用tddl DBRoute 可以继承Base下的crossdb base类
 * 可以使用commons包中的公用base repository 类
 * 
 * @created by xugang on 16/4/7.
 */
public interface ActivityDAO {

    List<ActivityDO> queryActivityDOsByOrderId(Long id) throws DAOException;
}