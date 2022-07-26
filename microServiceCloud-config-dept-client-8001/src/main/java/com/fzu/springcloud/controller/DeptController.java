package com.fzu.springcloud.controller;

import com.fzu.springcloud.service.DeptService;
import entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService service;
    //eureka服务发现
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept)
    {
        return service.add(dept);
    }

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id)
    {
        return service.get(id);
    }

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list()
    {
        return service.list();
    }


    //eureka服务发现
    @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
    public Object discovery()
    {
        //获取eureka服务列表中所有已注册服务
        List<String> list = client.getServices();
        System.out.println("**********" + list);
        //获得MICROSERVICECLOUD-DEPT微服务 实例，微服务名字与eureka管理页面一致
        List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
        //输出MICROSERVICECLOUD-DEPT微服务 相关信息
        for (ServiceInstance element : srvList) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.client;
    }
}
