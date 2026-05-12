package com.crm.demo.config;

import com.crm.demo.entity.Customer;
import com.crm.demo.entity.CustomerStatus;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DataInitializer {

    @Transactional
    public void onStart(@Observes StartupEvent ev) {
        if (Customer.count() == 0) {
            addCustomer("张三", "zhangsan@example.com", "13800138000", "北京科技有限公司", CustomerStatus.ACTIVE);
            addCustomer("李四", "lisi@example.com", "13800138001", "上海贸易集团", CustomerStatus.ACTIVE);
            addCustomer("王五", "wangwu@example.com", "13800138002", "广州制造企业", CustomerStatus.INACTIVE);
            addCustomer("赵六", "zhaoliu@example.com", "13800138003", "深圳互联网公司", CustomerStatus.ACTIVE);
            addCustomer("钱七", "qianqi@example.com", "13800138004", "成都软件园", CustomerStatus.ACTIVE);
        }
    }

    private void addCustomer(String name, String email, String phone, String company, CustomerStatus status) {
        Customer customer = new Customer();
        customer.name = name;
        customer.email = email;
        customer.phone = phone;
        customer.company = company;
        customer.status = status;
        customer.persist();
    }
}
