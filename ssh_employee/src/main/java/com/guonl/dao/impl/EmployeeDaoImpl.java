package com.guonl.dao.impl;

import com.guonl.dao.EmployeeDao;
import com.guonl.domain.Employee;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * 员工管理的DAO的实现类
 */
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao{
    @Override
    /**
     * DAO中根据用户名和密码查询用户的方法
     */
    public Employee findByUsernameAndPassword(Employee employee) {
        String hql = "FROM Employee WHERE username = ? AND password = ?";
        List<Employee> list = this.getHibernateTemplate().find(hql, employee.getUsername(), employee.getPassword());
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int findCount() {
        String hql = "select count(*) from Employee";
        List<Long> list = this.getHibernateTemplate().find(hql);
        if (list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<Employee> findByPage(int begin, int pageSize) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
        List<Employee> list = this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
        return list;
    }

    @Override
    //DAO中保存员工的方法
    public void save(Employee employee) {
        this.getHibernateTemplate().save(employee);
    }

    @Override
    //DAO中根据员工ID查询员工的方法
    public Employee findById(Integer eid) {
        return this.getHibernateTemplate().get(Employee.class, eid);
    }

    @Override
    //DAO中修改员工的方法
    public void update(Employee employee) {
        this.getHibernateTemplate().update(employee);
    }

    @Override
    //DAO中删除员工的方法
    public void delete(Employee employee) {
        this.getHibernateTemplate().delete(employee);
    }
}
