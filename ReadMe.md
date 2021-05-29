## Spring Cloud学习

### 服务注册和发现（eureka，zookeeper，consul)
- 创建项目 payment8001
- 创建项目 order80
  - RestTemplate 使用
- 创建项目 eureka-server7001
  - 引入eureka
  - eureka-server7001为注册中心
  - payment8001 order80注册为客户端
- 创建项目 eureka-server7002
  - eureka集群 负载均衡（@LoadBalanced）和故障容错 （互相注册，相互守望）
  - 支付微服务集群配置
  - 自我保护 不会轻易删除服务
- 创建项目 payment8003
  - Zookeeper 代替 Eureka
  - Zookeeper在服务器上 节点是临时性的，不像eureka
- 创建项目 zookeeper_order80
  - 创建服务消费者
- 创建项目 payment8005
  - 服务提供者，步骤和zookeeper一样
- 创建项目 consul_order80
  - 创建服务消费者，步骤和zookeeper一样
## restful 调试
> http://localhost:8001/payment/get/1
>
> http://eureka7001.com:7001/
>
> http://localhost/consumer/payment/get/1